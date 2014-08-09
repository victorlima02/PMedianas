package pMedianas.operadores;

import ic.ce.seres.permutacoes.SerPermutacao;
import ic.ce.seres.permutacoes.recombinadores.PMX;

/**
 *
 * @author Victor de Lima Soares
 * @version 1.0
 *
 * @param <G> Classe do retorno da função objetivo (Grau de adaptação):
 * AtomicInteger, AtomicLong, BigDecimal, BigInteger, Byte, Double, Float,
 * Integer, Long, Short.
 * @param <S> Classe dos Seres.
 */
public class Recombinacao<G extends Number & Comparable<G>, S extends SerPermutacao<G>> extends PMX<G, S> {

    public Recombinacao(Double probabilidadeDeRecombinacao, Integer nMedianas) {
        super(probabilidadeDeRecombinacao);
        setLimites(0, nMedianas / 2, true);
    }

}
