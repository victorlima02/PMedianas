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
package pMedianas.ambiente;

import ic.ce.base.Ambiente;
import java.util.List;

/**
 *
 * @author Victor de Lima Soares
 */
public class Regiao extends Ambiente<Double, MapaDeDistribuicao> {

    Grafo<Ponto> regiao;

    public Regiao(int nLocalidades) {
        super(Modo.MINIMIZACAO);
        regiao = new Grafo<>(nLocalidades);
    }

    public void addPonto(Ponto p) {
        regiao.addVertice(p);
    }

    public void updateDistancias() {
        List<Ponto> vertices = regiao.getVertices();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i; j < vertices.size(); j++) {
                regiao.addAresta(i, j, vertices.get(i).distancia(vertices.get(j)));
            }
        }

    }

    @Override
    public Double avalia(MapaDeDistribuicao individuo) {

        Double somaDistancias = 0.0;

        List<Integer> medianas = individuo.getMedianasIndices();

        for (int i = individuo.getNMedianas(); i < individuo.getSize(); i++) {
            somaDistancias += regiao.getDistanciaComMaisProximo(i, medianas);
        }

        return somaDistancias;
    }

}
