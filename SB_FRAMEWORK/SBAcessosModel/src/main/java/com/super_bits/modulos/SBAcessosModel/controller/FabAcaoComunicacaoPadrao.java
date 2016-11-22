/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.model.FaleConosco.FaleConosco;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.COMUNICACAO)
public enum FabAcaoComunicacaoPadrao implements ItfFabricaAcoes {

    FALE_CONOSCO_MB_GERENCIAR,
    FALE_CONOSCO_FRM_MODAL_RESPONDER,
    FALE_CONOSCO_FRM_LISTAR,
    FALE_CONOSCO_FRM_VISUALIZAR,
    FALE_CONOSCO_CTR_ALTERAR_STATUS,
    FALE_CONOSCO_CAD_MB,
    FALE_CONOSCO_CAD_FRM_NOVO,
    FALE_CONOSCO_CAD_CTR_SALVAR_MEGE,
    ASSUNTO_FALE_CONOSCO_FRM_LISTAR,
    ASSUNTO_FALE_CONOSCO_FRM_VISUALIZAR,
    ASSUNTO_FALE_CONOSCO_CTR_ALTERAR_STATUS,
    ASSUNTO_FALE_CONOSCO_CTR_SALVAR_MERGE,
    ASSUNTO_FALE_CONOSCO_MB_GERENCIAR,
    ASSUNTO_FALE_CONOSCO_GERENCIAR,
    ASSUNTO_FALE_CONOSCO_NOVO,
    ASSUNTO_FALE_CONOSCO_LISTAR,
    ASSUNTO_FALE_CONOSCO_VISUALIZAR,
    ASSUNTO_FALE_CONOSOCO_EDITAR;

    @Override
    public AcaoDoSistema getRegistro() {
        return (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
    }

    @Override
    public AcaoDoSistema getAcaoDoSistema() {
        return getRegistro();
    }

    @Override
    public Class getEntidadeDominio() {
        return FaleConosco.class;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }
}
