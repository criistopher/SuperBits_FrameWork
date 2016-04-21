/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoModulo(descricao = "Comunicação do sistema", nomeDoModulo = "Módulo Comunicação")
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
    public ItfAcaoDoSistema getAcaoDoSistema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoControllerEntidade getAcaoEntidadeController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoController getAcaoController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class getDominio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeModulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
