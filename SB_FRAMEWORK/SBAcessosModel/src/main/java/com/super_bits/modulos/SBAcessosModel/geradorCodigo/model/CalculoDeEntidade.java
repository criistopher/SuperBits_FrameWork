/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;

/**
 *
 * @author desenvolvedor
 */
public class CalculoDeEntidade extends EstruturaCampo {

    public CalculoDeEntidade(String nomeEnum, String descricao, String tipoRetorno, Campo pCampo, EstruturaDeEntidade pEstrutura, String javaDoc) {

        super(pCampo, pEstrutura);

        this.nomeEnum = nomeEnum;
        this.descricao = descricao;
        this.tipoRetorno = tipoRetorno;
        this.javaDoc = javaDoc;

    }

    private String tipoRetorno;
    private String nomeEnum;
    private String descricao;
    private String javaDoc;


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

    public String getJavaDoc() {
        return javaDoc;
    }

    public void setJavaDoc(String javaDoc) {
        this.javaDoc = javaDoc;
    }


}
