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
public class CalculoDeEntidade {

    public CalculoDeEntidade(String nomeEnum, String descricao, String tipoRetorno) {
        this.nomeEnum = nomeEnum;
        this.descricao = descricao;
        this.tipoRetorno = tipoRetorno;
    }

    private String tipoRetorno;
    private String nomeEnum;
    private String descricao;

    public String getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(String tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
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
