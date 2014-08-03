/*
 * The MIT License
 *
 * Copyright 2014 Victor de Lima Soares.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pMedianas;

import ic.ce.populacional.Ambiente;
import ic.ce.populacional.algoritmo.AlgoritmoPopulacional;
import ic.ce.populacional.algoritmo.operadores.Gerador;
import ic.ce.populacional.algoritmo.operadores.Mutador;
import ic.ce.populacional.algoritmo.operadores.Seletor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import pMedianas.ambiente.MapaDeDistribuicao;
import pMedianas.ambiente.Ponto;
import pMedianas.ambiente.PopulacaoDeMapas;
import pMedianas.ambiente.Regiao;
import pMedianas.operadores.Geracao;
import pMedianas.operadores.Mutacao;
import pMedianas.operadores.Recombinacao;
import pMedianas.operadores.Selecao;

/**
 * p-Medianas: AGS.
 *
 * <p>
 * A localização de instalações para distribuição de bens e serviços é um
 * problema crítico para empresas públicas e privadas, onde se visa obter um
 * melhor posicionamento para minimizar a distância entre clientes e as
 * facilidades que os atendem – dada uma restrição ao número de instalações.
 * </p>
 *
 * <p>
 * O problema das p-Mediana é descrito pela problematização cujo objetivo é
 * localizar um posicionamento ideal para <i>p</i> facilidades, de modo a
 * minimizar a soma das distâncias entre clientes e as estações que os atendem.
 * Restringindo-se o atendimento a uma única localização por cliente.
 * </p>
 *
 * <h3>Enunciado:</h3>
 * <p>
 * Em um grafo completo e não orientado <i>G = (V;E)</i>, em que
 * <i>V</i> é o conjunto de <i>n</i> vértices e <i>E</i> o conjunto de arestas,
 * busca-se um subconjunto <i>Vp</i> com cardinalidade <i>p</i>, tal que
 * <i>Vp</i> represente o conjunto de medianas do problema, de modo que a soma
 * das distâncias entre os vértices pertencentes a <i>{V - Vp}</i> e o vértice
 * mais próximo em <i>Vp</i> seja a menor possível.
 * </p>
 *
 * <h3>Restrições:</h3>
 * <ul>
 * <li>Um vértice somente poderá ser atendido por uma mediana;</li>
 * <li>Um vértice somente poderá ser atendido por uma mediana já instalada,
 * pertencente a <i>Vp</i>;</li>
 * <li>Existirão exatamente <i>p</i> medianas.</li>
 * </ul>
 *
 * <p>
 * Recombinação PMX.</p>
 *
 * @author Victor de Lima Soares
 */
public class MainAGS {

    private int nLocalidades;
    private int nMedianas;
    private Regiao regiao;

    static int nTestes = 1;
    static double somaDistancias;
    static double somaTempo;
    static double somaIteracoes;
    static double melhorDistancia = Double.NEGATIVE_INFINITY;
    static String melhorRelatorio;

    /**
     * Função principal: AGS.
     *
     * @param args Parâmetros da linha de comando: Não Usados.
     * 
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        MainAGS programaBase = new MainAGS();
        programaBase.readFile("./data/pmedian324.txt");
        //programaBase.readFile("./data/pmedian818.txt");
        //programaBase.readFile("./data/pmedian3282.txt");
        
        for (int teste = 0; teste < nTestes; teste++) {

            Integer maxIteracoes = 1000;
            Integer maxIndividuos = 100;
            Double probabilidaDeCruzamento = 0.8;
            Double probabilidadeDeMutacao = 0.5;

            Ambiente<Double, MapaDeDistribuicao> ambiente = programaBase.regiao;
            PopulacaoDeMapas populacao = new PopulacaoDeMapas(ambiente, maxIndividuos);

            Gerador gerador = new Geracao(programaBase.nMedianas, programaBase.nLocalidades);
            Mutador mutador = new Mutacao(probabilidadeDeMutacao);
            Recombinacao recombinador = new Recombinacao(probabilidaDeCruzamento, programaBase.nMedianas);
            Seletor seletor = new Selecao();

            populacao.setIndividuos(gerador.getNAleatorios(maxIndividuos));

            AlgoritmoPopulacional algoritmo = new Algoritmo();

            algoritmo.setAmbiente(ambiente);
            algoritmo.setPopulacao(populacao);
            algoritmo.setMaxIteracoes(maxIteracoes);

            algoritmo.setGerador(gerador);
            algoritmo.setMutador(mutador);
            algoritmo.setRecombinador(recombinador);
            algoritmo.setSeletor(seletor);

            algoritmo.run();

            somaDistancias += (Double) algoritmo.getMelhorSer().getGrauDeAdaptacao();
            somaTempo += algoritmo.getTempoDeExecucaoSeg();
            somaIteracoes += algoritmo.getContadorDeIteracoes();

            if (((Double) algoritmo.getMelhorSer().getGrauDeAdaptacao()) > melhorDistancia) {
                melhorDistancia = (Double) algoritmo.getMelhorSer().getGrauDeAdaptacao();
                melhorRelatorio = algoritmo.relatorio();
            }
        }
        System.out.println("Número de testes:\t"+nTestes);
        System.out.println("Instância:\t324");
        System.out.println("Média de tempo:\t" + somaTempo / nTestes);
        System.out.println("Média de iterações:\t" + somaIteracoes / nTestes);
        System.out.println("Média de distância:\t" + somaDistancias / nTestes);
        System.out.println("Melhor distância:\t" + melhorDistancia);
        System.out.println(melhorRelatorio);
    }

    public void readFile(String file) throws FileNotFoundException, IOException {
        try (BufferedReader bufferParametros = new BufferedReader(new FileReader(file))) {

            String buffer = bufferParametros.readLine();

            nLocalidades = Integer.parseInt(buffer.split(" ")[0]);
            nMedianas = Integer.parseInt(buffer.split(" ")[1]);

            regiao = new Regiao(nLocalidades);

            while ((buffer = bufferParametros.readLine()) != null) {
                int x = Integer.parseInt(buffer.split(" ")[0]);
                int y = Integer.parseInt(buffer.split(" ")[1]);
                Ponto pto = new Ponto(x, y);
                regiao.addPonto(pto);
            }
            regiao.updateDistancias();

        } catch (IOException ioe) {
            String erro = "Erro na leitura do arquivo CSV.";
            throw new IOException(erro, ioe);
        }
    }

}
