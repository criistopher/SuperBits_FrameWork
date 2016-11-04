/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public class MapaSubstituicao {

    private final Map<String, String> mapaSubstituicao = new HashMap<>();

    public void substituirEmArquivo(String caminhoArquivo) {

    }

    public String substituirEmString(String pString) {
        String novaString = pString;
        for (String palavraChave : mapaSubstituicao.keySet()) {
            System.out.println("Substituindo [" + palavraChave + "] por" + mapaSubstituicao.get(palavraChave));
            novaString = novaString.replaceAll("[" + palavraChave + "]", mapaSubstituicao.get(palavraChave));
        }
        return novaString;

    }

    public final void adicionarPalavraChave(String palavra, String valor) {
        mapaSubstituicao.put(palavra, valor);
    }

    /**
     *
     * @return Lista de Palavra chave dispon
     */
    public List<String> getpalavrasChave() {
        return Lists.newArrayList(mapaSubstituicao.keySet().iterator());
    }

}
