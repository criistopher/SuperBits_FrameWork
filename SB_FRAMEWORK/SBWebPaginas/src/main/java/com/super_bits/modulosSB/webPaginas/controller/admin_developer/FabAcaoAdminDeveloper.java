/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.admin_developer;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcaoAdminDeveloper implements ItfFabricaAcoes {

    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.SISTEMA_CARTAO_DE_ACESSO,
            nomeAcao = "Manutenção", descricao = "Manutenção e Desenvolvimento do Projeto"
    )
    DEV_PROJETO_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Ferramentas", descricao = "Ferramentas Disponíveis para Gestão do Sistema")
    DEV_PROJETO_FRM_FERRAMENTAS,
    @InfoTipoAcaoFormulario(nomeAcao = "Selecionar Objeto", xhtmlDaAcao = "selecaoObjeto.xhtml")
    DEV_PROJETO_SELECAO_OBJETOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Selecionar Ações", xhtmlDaAcao = "selecionarAcoes.xhtml")
    DEV_PROJETO_SELECAO_ACAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Selecionar Campo", xhtmlDaAcao = "selecionarCampo.xhtml")
    DEV_PROJETO_SELECAO_CAMPO,
    @InfoTipoAcaoFormulario(nomeAcao = "Criar Ação", xhtmlDaAcao = "criarAcao.xhtml")
    FERRAMENTAS_ACAO_MB_CRIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Definir Slug Ação", xhtmlDaAcao = "nvAcaoDefSlug.xhtml")
    FERRAMENTAS_ACAO_FRM_DEF_NOME_SLUG_ACAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Definir Icone ação", xhtmlDaAcao = "nvAcaoDefIcone.xhtml")
    FERRAMENTAS_ACAO_FRM_ICONE_ACAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Definir Icone ação", xhtmlDaAcao = "nvAcaoDefXHTML.xhtml")
    FERRAMENTAS_ACAO_FRM_XHTML_ACAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Definir Objeto ação", xhtmlDaAcao = "nvAcaoDefObj.xhtml")
    FERRAMENTAS_ACAO_FRM_OBJETO_ACAO,
    @InfoTipoAcaoFormulario(nomeAcao = "Definir Nome e Descrição da ação", xhtmlDaAcao = "nvAcaoDefNomeDescri.xhtml")
    FERRAMENTAS_ACAO_FRM_NOME_E_DESCRICAO,
    @InfoTipoAcaoController(nomeAcao = "Instanciar no pacote")
    FERRAMENTAS_ACAO_CTR_CRIAR,
    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Variaveis de Ambiente", xhtmlDaAcao = "gestaoConfiguracoesSistema.xhtml")
    FERRAMENTAS_CONFIG_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "SBProjeto", xhtmlDaAcao = "editarSBProjeto.xhtml")
    FERRAMENTAS_CONFIG_FRM_SBPROJETO,
    @InfoTipoAcaoFormulario(nomeAcao = "Cofigurações gerais", xhtmlDaAcao = "congigGeral.xhtml")
    FERRAMENTAS_CONFIG_FRM_CONFIGURACOES_GERAIS,
    @InfoTipoAcaoFormulario(nomeAcao = "Logs", xhtmlDaAcao = "logs.xhtml")
    FERRAMENTAS_LOGS_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Banco de Dados", xhtmlDaAcao = "bancoDEDadosGestao.xhtml")
    FERRAMENTAS_BANCO_MB,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar Dados", xhtmlDaAcao = "listarDadosBanco.xhtml")
    FERRAMENTAS_BANCO_FRM_LISTAR_DADOS,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar SQL", xhtmlDaAcao = "editarSQL.xhtml")
    FERRAMENTAS_BANCO_FRM_EDITAR_SQL,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar HQL", xhtmlDaAcao = "editarHQL.xhtml")
    FERRAMENTAS_BANCO_FRM_EDITAR_HQL,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar via SQL")
    FERRAMENTAS_BANCO_CTR_LISTAR_SQL,
    @InfoTipoAcaoFormulario(nomeAcao = "Listar via HQL")
    FERRAMENTAS_BANCO_CTR_LISTAR_HQL,
    @InfoTipoAcaoFormulario(nomeAcao = "Criar Bean de Consulta", xhtmlDaAcao = "BeamDeConsulta.xhtml")
    FERRAMENTAS_BANCO_FRM_CRIAR_CONSULTA;

    @Override
    public AcaoDoSistema getAcaoDoSistema() {

        AcaoDoSistema acao = (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);

        return acao;

    }

    @Override
    public Class getEntidadeDominio() {
        return AcaoDoSistema.class;

    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

    @Override
    public AcaoDoSistema getRegistro() {
        return getAcaoDoSistema();
    }

}
