/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.controller;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 * @author salvioF
 */
public enum FabAcaoTemporariaTestes implements ItfFabricaAcoes {

    @InfoTipoAcaoGestaoEntidade()
    TESTE_TEMPORARIO_MB_GESTAO_TESTES_TEMPORARIOS,
    TESTE_TEMPORARIO_FRM_EDITAR,
    TESTE_TEMPORARIO_FRM_NOVO;
    //todo CRIAR UM DE CADA PARA TESTES

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {

        return null;
    }

    @Override
    public Class getEntidadeDominio() {
        return Object.class;
    }

    @Override
    public String getNomeModulo() {
        return "testes";
    }

    @Override
    public Object getRegistro() {
        return getAcaoDoSistema();
    }
}
