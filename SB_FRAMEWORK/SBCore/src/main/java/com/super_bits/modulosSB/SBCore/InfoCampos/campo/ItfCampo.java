/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

/**
 *
 *
 *
 *
 * @author sfurbino
 */
public interface ItfCampo {

    public static enum TIPOPRIMITIVO {

        NUMERO, LETRAS, DATAS
    }

    public FabCampos getTipoCampo();

    public TIPOPRIMITIVO getTipoValor();

    public String getTipoVisualizacao();

    public String getLabel();

    public String getDescricao();

    public String getMascara();

    public String getValorPadrao();

    public boolean isObrigatorio();

    public long getValorMaximo();

    public long getValorMinimo();

}
