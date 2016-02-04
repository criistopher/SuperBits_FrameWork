/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller;

import com.super_bits.Controller.Interfaces.ItfTipoAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public class TipoAcaoPadrao implements ItfTipoAcaoDoSistema {

<<<<<<< HEAD
    @Override
    public String getIconePadrao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
    private String iconePadrao, nomePadrao, descricaoPadrao;

    @Override
    public String getIconePadrao() {
        return this.iconePadrao;
>>>>>>> 02777300af762be73db729383fe19b4a7a0c4b25
    }

    @Override
    public String getNomePadrao() {
<<<<<<< HEAD
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
        return this.nomePadrao;
>>>>>>> 02777300af762be73db729383fe19b4a7a0c4b25
    }

    @Override
    public String getDescricaoPadrao() {
<<<<<<< HEAD
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
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
>>>>>>> 02777300af762be73db729383fe19b4a7a0c4b25
    }

}
