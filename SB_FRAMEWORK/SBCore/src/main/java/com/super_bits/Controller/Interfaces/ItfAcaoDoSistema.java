/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author sfurbino
 */
public interface ItfAcaoDoSistema extends ItfBeanSimples {

    public String getNomeAcao();

    public String getIconeAcao();

    public String getCor();

    public String getDescricao();

    public String getXHTMLAcao();

    public ItfModuloAcaoSistema getModulo();

    public int getIdMetodo();

    public boolean isPrecisaPermissao();

    public void setId(int pId);

    public void setIdMetodo(int pID);

    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo);

}
