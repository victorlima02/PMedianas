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

import java.util.ArrayList;
import java.util.List;

/**
 * Grafo completo não orientado.
 *
 * @author Victor de Lima Soares
 * @param <T> Tipo pré-definido para os vértices do grafo.
 */
public class Grafo<T> {

    private final List<T> vertices;
    private final double[][] matrizDeAdjacência;

    /**
     * Construtor.
     *
     * @param nNos Número de nós no grafo.
     */
    public Grafo(int nNos) {

        vertices = new ArrayList<>(nNos);

        matrizDeAdjacência = new double[nNos][nNos];
    }

    /**
     * Adiciona um nó ao grafo.
     *
     * @param no
     */
    void addVertice(T no) {
        vertices.add(no);
    }

    /**
     * Adiciona uma aresta ao grafo.
     *
     * @param indiceI Índice do primeiro nó.
     * @param indiceJ Índice do segundo nó.
     * @param peso Peso da aresta.
     */
    void addAresta(int indiceI, int indiceJ, double peso) {
        matrizDeAdjacência[indiceI][indiceJ] = peso;
        matrizDeAdjacência[indiceJ][indiceI] = peso;
    }

    /**
     * Retorna o peso da aresta.
     *
     * @param indiceI Índice do primeiro nó.
     * @param indiceJ Índice do segundo nó.
     * @return Peso da aresta
     */
    double getAresta(int indiceI, int indiceJ) {
        return matrizDeAdjacência[indiceI][indiceJ];
    }

    /**
     * Retorna o nó especificado.
     *
     * @param indice
     * @return Nó localizado pelo índice.
     */
    T getNo(int indice) {
        return vertices.get(indice);
    }

    Double getDistanciaComMaisProximo(int indiceReferencia, List<Integer> indices) {
        Integer i = getNoMaisProximo(indiceReferencia, indices);
        return getAresta(indiceReferencia, i);
    }

    Integer getNoMaisProximo(int indiceReferencia, List<Integer> indices) {

        Double distanciaMin = getAresta(indiceReferencia, indices.get(0));
        Integer indiceMin = 0;

        Double distanciaTmp;

        for (int i = 1; i < indices.size(); i++) {

            distanciaTmp = getAresta(indiceReferencia, indices.get(i));

            if (distanciaTmp < distanciaMin) {
                distanciaMin = distanciaTmp;
                indiceMin = i;
            }
        }

        return indices.get(indiceMin);
    }

    public List<T> getVertices() {
        return vertices;
    }
     
    

}
