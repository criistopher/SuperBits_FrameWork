/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CaminhoCampoReflexao {

    private List<String> partesCaminho;
    private String caminhoComleto;
    private Field campoFieldReflection;

    public CaminhoCampoReflexao() {
        partesCaminho = new ArrayList<>();
    }

    private void makePartesCaminho() {

        if (caminhoComleto == null) {
            throw new UnsupportedOperationException("Impossivel criar o caminho, antes de definir as partes do caminho");
        }
        if (caminhoComleto.isEmpty()) {
            throw new UnsupportedOperationException("Impossivel criar o caminho, antes de definir as partes do caminho");

        }

        String[] partes = caminhoComleto.split("\\.");
        partesCaminho.clear();
        partesCaminho.addAll(Arrays.asList(partes));

    }

    private void makeCaminhoCompleto() {

        if (caminhoComleto == null) {
            throw new UnsupportedOperationException("Impossivel criar as partes do caminho, antes de definir o caminho completo");
        }
        if (caminhoComleto.isEmpty()) {
            throw new UnsupportedOperationException("Impossivel criar as partes do caminho, antes de definir o caminho completo");

        }

        String[] partes = caminhoComleto.split("\\.");
        partesCaminho.clear();
        partesCaminho.addAll(Arrays.asList(partes));

    }

    /**
     *
     * @param pCaminho Caminho para encontrar o Campo, separado por . exemplo:
     * usuario.localizacao.bairro
     * @param campo O Field obtido por reflecion
     */
    public CaminhoCampoReflexao(String pCaminho, Field campo) {
        this();
        //setCaminho(pCaminho);
        caminhoComleto = pCaminho;
        this.campoFieldReflection = campo;
        makePartesCaminho();
    }

    public CaminhoCampoReflexao(List<String> pPartesCaminho, Field campo) {
        this();
        //setCaminho(pCaminho);
        partesCaminho = pPartesCaminho;
        this.campoFieldReflection = campo;
        makeCaminhoCompleto();
    }

    /**
     *
     * Adiciona uma parte do caminho para o campo caminho.add(Parate)
     *
     * @param pParteCaminho
     */
    public void addParteCaminho(String pParteCaminho) {
        partesCaminho.add(pParteCaminho);
    }

    /**
     *
     * @return O caminho em formato de uma string, exemplo:
     * usuario.localizacao.bairro
     */
    public String getCaminhoString() {
        return caminhoComleto;
    }

    public List<String> getPartesCaminho() {
        return partesCaminho;
    }

    public Field getCampoFieldReflection() {
        return campoFieldReflection;
    }

    @Override
    public String toString() {
        return getCaminhoString();
    }

}
