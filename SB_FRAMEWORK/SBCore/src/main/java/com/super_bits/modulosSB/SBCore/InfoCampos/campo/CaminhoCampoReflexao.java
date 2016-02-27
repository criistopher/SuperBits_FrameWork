/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class CaminhoCampoReflexao {

    private List<String> caminho;
    private String caminhoComleto;
    private Field campo;

    public CaminhoCampoReflexao() {
        caminho = new ArrayList<>();
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
        this.campo = campo;
    }

    /**
     *
     *
     * Executa um Split a partir dos pontos e adciona na lista do caminho
     *
     *
     * @param caminho
     */
    public final void setCaminho(String caminho) {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    /**
     *
     * Adiciona uma parte do caminho para o campo caminho.add(Parate)
     *
     * @param pParteCaminho
     */
    public void addParteCaminho(String pParteCaminho) {
        throw new UnsupportedOperationException("Ainda não implementado");

    }

    /**
     *
     * Retorna o Campo do java Reflection, exexuta um throw caso o field seja
     * nulo
     *
     * @return
     */
    public String getCaminhoField() {
        throw new UnsupportedOperationException("Ainda não implementado");

    }

    /**
     *
     * @return O caminho em formato de uma string, exemplo:
     * usuario.localizacao.bairro
     */
    public String getCaminhoString() {
        return caminhoComleto;
    }

    public List<String> getCaminho() {
        return caminho;
    }

    public Field getCampo() {
        return campo;
    }

}
