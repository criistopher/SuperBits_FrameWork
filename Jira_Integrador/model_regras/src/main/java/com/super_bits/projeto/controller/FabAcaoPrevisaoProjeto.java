/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.controller;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.projeto.Jira.PrevisaoProjeto;

/**
 *
 * @author salvioF
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcaoPrevisaoProjeto implements ItfFabricaAcoes {

    /**
     * Gestão da previsão do projeto
     */
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Requisitos do sitema",
            icone = "fa fa-codepen",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "gerenciarProjetoAtual.xhtml")
    ACAO_PREVISAO_MB_GESTAO,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Resumo Geral",
            icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "resumoGeral.xhtml"
    )
    ACAO_PREVISAO_FRM_RESUMO_GERAL,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Ambiente de Desenvolvimento",
            icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "ambiente.xhtml"
    )
    ACAO_PREVISAO_FRM_AMBIENTE_DESENVOLVIMENTO,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Banco de Dados", icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "visaoGeralBanco.xhtml"
    )
    ACAO_PREVISAO_FRM_VISAO_GERAL_BANCO_DE_DADOS,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Gestão do Sistema", icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "visaoGeralGestao.xhtml"
    )
    ACAO_PREVISAO_FRM_VISAO_GERAL_GESTAO,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Modulos", icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "visaoGeralModulos.xhtml"
    )
    ACAO_PREVISAO_FRM_VISAO_GERAL_MODULOS,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Detalhes ação do sistema", icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "acaoControllerDetalhes.xhtml"
    )
    ACAO_PREVISAO_FRM_DETALHES_ACAO_CONTROLLER,
    @InfoTipoAcaoFormulario(nomeAcao = "Detalhes ação do sistema", icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "acaoControllerDetalhes.xhtml"
    )

    ACAO_PREVISAO_FRM_DETALHES_ACAO_NOVA_TECNOLOGIA,
    ACAO_PREVISAO_FRM_DETALHES_ACAO_INTEGRACAO,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Detalhes sistema de gestão", icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "acaoGestaoDetalhes.xhtml"
    )
    ACAO_PREVISAO_FRM_DETALHES_ACAO_GESTAO,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Detalhes sistema do Modulo", icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "detalhesModulo.xhtml"
    )
    ACAO_PREVISAO_FRM_DETALHES_MODULO,
    ACAO_PREVISAO_FRM_DETALHES_TABELA_BANCO_DADDOS,
    /**
     *
     */
    @InfoTipoAcaoFormulario(nomeAcao = "Detalhes do formulário", icone = "fa fa-bars",
            xhtmlDaAcao = FabAcaoPrevisaoProjeto.ARQUIVOS_DE_FORMULARIO + "acaoFormularioDetalhes.xhtml"
    )
    ACAO_PREVISAO_FRM_DETALHES_ACAO_FORMULARIO;
    public static final String ARQUIVOS_DE_FORMULARIO = "/site/modulos/projetoAtual/";

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        AcaoDoSistema acao = (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);

        return acao;
    }

    @Override
    public Class getEntidadeDominio() {
        return PrevisaoProjeto.class;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        return getAcaoDoSistema();
    }
}
