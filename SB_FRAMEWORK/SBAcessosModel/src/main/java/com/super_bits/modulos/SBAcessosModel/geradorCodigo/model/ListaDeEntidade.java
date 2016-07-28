/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo.model;

/**
 *
 * @author desenvolvedor
 */
public class ListaDeEntidade extends EstruturaDeEntidade {

    public ListaDeEntidade(String nomeEnum, String nomeDeclaracao, String nomeEntidade, String nomeObjetoListado) {
        this.nomeEnum = nomeEnum;
        this.nomeDeclaracao = nomeDeclaracao;
        this.nomeEntidade = nomeEntidade;
        this.nomeObjetoListado = nomeObjetoListado;
    }

    private String nomeEntidade;
    private String nomeEnum;
    private String nomeDeclaracao;
    private String nomeObjetoListado;

    public String getNomeObjetoListado() {
        return nomeObjetoListado;
    }

    public void setNomeObjetoListado(String nomeObjetoListado) {
        this.nomeObjetoListado = nomeObjetoListado;
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    public String getNomeEnum() {
        return nomeEnum;
    }

    public void setNomeEnum(String nomeEnum) {
        this.nomeEnum = nomeEnum;
    }

    public String getNomeDeclaracao() {
        return nomeDeclaracao;
    }

    public void setNomeDeclaracao(String nomeDeclaracao) {
        this.nomeDeclaracao = nomeDeclaracao;
    }

}
