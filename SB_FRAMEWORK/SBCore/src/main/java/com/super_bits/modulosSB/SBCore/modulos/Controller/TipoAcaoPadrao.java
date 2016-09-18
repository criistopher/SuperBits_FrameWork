/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfTipoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public class TipoAcaoPadrao implements ItfTipoAcaoDoSistema {

    private String iconePadrao, nomePadrao, descricaoPadrao;

    @Override
    public String getIconePadrao() {
        return this.iconePadrao;

    }

    @Override
    public String getNomePadrao() {

        return this.nomePadrao;

    }

    @Override
    public String getDescricaoPadrao() {

        return this.descricaoPadrao;
    }

    public void setIconePadrao(String iconePadrao) {
        this.iconePadrao = iconePadrao;
    }

    public void setNomePadrao(String nomePadrao) {
        this.nomePadrao = nomePadrao;
    }

    public void setDescricaoPadrao(String descricaoPadrao) {
        this.descricaoPadrao = descricaoPadrao;
    }

}
