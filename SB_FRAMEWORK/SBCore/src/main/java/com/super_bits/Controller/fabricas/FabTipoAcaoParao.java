/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.fabricas;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoAcaoParao implements ItfFabrica {

    NOVO_ABRIR_FORMULARIO, EDITAR_ABRIR_FORMULARIO, SALVAR_EDICAO, SALVAR_NOVO, SALVAR_MODO_MERGE;

    @Override
    public Object getRegistro() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
