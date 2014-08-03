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

import ic.ce.populacional.Caracteristica;
import ic.ce.populacional.seres.permutacoes.LocusPermutacao;
import ic.ce.populacional.seres.permutacoes.SerPermutacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor de Lima Soares
 */
public class MapaDeDistribuicao extends SerPermutacao<Double> {

    int nMedianas;

    /**
     * Construtor do Ser: <code>MapaDeDistribuicao</code>.
     *
     * Ser com nLocalidades características.
     *
     * @param nMedianas Número de medianas no mapa(primeiros genes do ser).
     * @param nLocalidades Número de localidades no mapa.
     */
    public MapaDeDistribuicao(int nMedianas, int nLocalidades) {
        super(0,nLocalidades);
        this.nMedianas = nMedianas;
    }
    
    public int getNMedianas() {
        return nMedianas;
    }

    public List<Caracteristica> getMedianas() {
        return getCaracteristicas().subList(0, getNMedianas());
    }

    public List<Integer> getMedianasIndices() {
        List<Integer> indices = new ArrayList<>(getNMedianas());
        for (Caracteristica locus : getMedianas()) {
            indices.add(((LocusPermutacao) locus).getValor());
        }
        return indices;
    }

}
