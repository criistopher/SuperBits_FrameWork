/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.fabricas;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcaoProjetoSB implements ItfFabricaAcoes {

    /**
     *
     * Managed Bean responsável por exibir o projeto e seu status de
     * desenvolvimento
     *
     */
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/projeto/gerenciarProjeto.xhtml",
            icone = "fa fa-codepen")
    PROJETO_GERENCIAR_MB,
    /**
     * Formulário que exibe todas as ações do sistema
     */
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/projeto/pontosDeFuncao.xhtml",
            icone = "fa fa-bars")
    PROJETO_FRM_VISUALIZAR_ACOES,
    /**
     *
     * Formulário para exibição do banco no formato UML
     *
     */
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/projeto/umlBancoDeDados.xhtml",
            icone = "fa fa-bars")
    PROJETO_FRM_VISUALIZAR_BANCO_DE_DADOS,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/projeto/infoTabela.xhtml",
            icone = "fa fa-bars")
    PROJETO_FRM_VISUALIZAR_TABELA;

    @Override
    public Object getRegistro() {
        return UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
    }

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        return (ItfAcaoDoSistema) getRegistro();
    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        return (ItfAcaoEntidade) getRegistro();
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario() {
        return (ItfAcaoFormularioEntidade) getRegistro();
    }

    @Override
    public ItfAcaoControllerEntidade getAcaoEntidadeController() {
        return (ItfAcaoControllerEntidade) getRegistro();
    }

    @Override
    public ItfAcaoController getAcaoController() {
        return (ItfAcaoController) getRegistro();
    }

    @Override
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        return (ItfAcaoGerenciarEntidade) getRegistro();
    }

    @Override
    public Class getEntidadeDominio() {
        return ProjetoAtual.class;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

}
