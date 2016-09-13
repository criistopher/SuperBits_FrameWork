/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

/**
 *
 * @author desenvolvedor
 */
public class EstruturaCampo extends Campo {

    private String nomeDeclarado;
    private EstruturaDeEntidade estruturaPai;

    public EstruturaCampo(Campo pTipo, EstruturaDeEntidade pEstrutura) {
        super(pTipo);
        estruturaPai = pEstrutura;
    }

    public EstruturaDeEntidade getEstruturaPai() {
        return estruturaPai;
    }

    public void setEstruturaPai(EstruturaDeEntidade estruturaPai) {
        this.estruturaPai = estruturaPai;
    }

    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

}
