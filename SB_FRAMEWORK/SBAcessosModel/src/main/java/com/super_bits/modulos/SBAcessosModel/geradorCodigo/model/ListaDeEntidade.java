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
public class ListaDeEntidade {

    public ListaDeEntidade(String nomeEnum, String descricao, EstruturaDeEntidade entidade) {
        this.nomeEnum = nomeEnum;
        this.descricao = descricao;
        this.entidadeListada = entidade;
    }

    private EstruturaDeEntidade entidadeListada;
    private String nomeEnum;
    private String descricao;

    public EstruturaDeEntidade getEntidadeListada() {
        return entidadeListada;
    }

    public void setEntidadeListada(EstruturaDeEntidade entidadeListada) {
        this.entidadeListada = entidadeListada;
    }


    public String getNomeEnum() {
        return nomeEnum;
    }

    public void setNomeEnum(String nomeEnum) {
        this.nomeEnum = nomeEnum;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
