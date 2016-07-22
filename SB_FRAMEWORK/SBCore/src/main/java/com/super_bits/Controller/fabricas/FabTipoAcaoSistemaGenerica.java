/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.fabricas;

import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoAcaoSistemaGenerica implements ItfFabrica {

    FORMULARIO_NOVO_REGISTRO,
    FORMULARIO_EDITAR,
    FORMULARIO_PERSONALIZADO,
    FORMULARIO_VISUALIZAR,
    FORMULARIO_LISTAR,
    FORMULARIO_MODAL,
    SELECAO_DE_ACAO,
    CONTROLLER_SALVAR_EDICAO,
    CONTROLLER_SALVAR_NOVO,
    CONTROLLER_SALVAR_MODO_MERGE,
    CONTROLLER_PERSONALIZADO,
    CONTROLLER_ATIVAR_DESATIVAR,
    CONTROLLER_ATIVAR,
    CONTROLLER_REMOVER,
    CONTROLLER_DESATIVAR,
    GERENCIAR_DOMINIO;

    @Override
    public TipoAcaoPadrao getRegistro() {

        TipoAcaoPadrao acaoPadrao = new TipoAcaoPadrao();

        acaoPadrao.setNomePadrao(this.getNomePadrao());
        acaoPadrao.setDescricaoPadrao(this.getDescricaoPadrao());
        acaoPadrao.setIconePadrao(this.getIconePadrao());

        return acaoPadrao;
    }

    public String getIconePadrao() {

        switch (this) {
            case FORMULARIO_NOVO_REGISTRO:
                return "fa fa-plus";
            case FORMULARIO_EDITAR:
                return "fa fa-pencil";
            case FORMULARIO_PERSONALIZADO:
                break;
            case FORMULARIO_VISUALIZAR:
                return "fa fa-eye";
            case FORMULARIO_LISTAR:
                return "fa fa-list-alt";
            case FORMULARIO_MODAL:
                return "fa fa-gear";
            case SELECAO_DE_ACAO:
                return "fa-map-signs";
            case CONTROLLER_SALVAR_EDICAO:
                return "fa fa-save ";
            case CONTROLLER_SALVAR_NOVO:
                return "fa fa-save ";
            case CONTROLLER_SALVAR_MODO_MERGE:
                return "fa fa-save ";
            case CONTROLLER_PERSONALIZADO:
                return "fa fa-gear";
            case CONTROLLER_ATIVAR_DESATIVAR:
                return "fa fa-retweet";
            case CONTROLLER_ATIVAR:
                return "fa fa-check";
            case CONTROLLER_REMOVER:
                return "fa fa-minus";
            case CONTROLLER_DESATIVAR:
                return "fa - close";
            case GERENCIAR_DOMINIO:
                break;
            default:
                return "fa fa-gear";

        }
        return null;
    }

    public String getNomeFormularioPadrao() {
        switch (this.getAcaoBase()) {
            case FORMULARIO:
                return "formulario";
            case GESTAO:
                return "gestao";
            case CONTROLLER:
                return "modal";
            default:
                throw new AssertionError(this.getAcaoBase().name());

        }

    }

    public String getNomePadrao() {
        switch (this) {
            case FORMULARIO_NOVO_REGISTRO:
                return "Novo ";
            case FORMULARIO_EDITAR:
                return "Editar";
            case FORMULARIO_PERSONALIZADO:
                return "?????";
            case FORMULARIO_VISUALIZAR:
                return "visualizar";
            case FORMULARIO_LISTAR:
                return "Listar";
            case FORMULARIO_MODAL:
                return "????";
            case SELECAO_DE_ACAO:
                return "Escolha";
            case CONTROLLER_SALVAR_EDICAO:
                return "Salvar Alterações";
            case CONTROLLER_SALVAR_NOVO:
                return "Criar Registro";
            case CONTROLLER_SALVAR_MODO_MERGE:
                return "Salvar";
            case CONTROLLER_PERSONALIZADO:
                return "??????";
            case CONTROLLER_ATIVAR_DESATIVAR:
                return "Alterar Status";
            case CONTROLLER_ATIVAR:
                return "Ativar";
            case CONTROLLER_REMOVER:
                return "Remover";
            case CONTROLLER_DESATIVAR:
                return "Desativar";
            case GERENCIAR_DOMINIO:
                return "Gerenciar";
            default:
                throw new AssertionError(this.name());

        }

    }

    public String getDescricaoPadrao() {
        switch (this) {
            case FORMULARIO_NOVO_REGISTRO:
                return "Abre um formulário para criação";
            case FORMULARIO_EDITAR:
                return "Abre um formulário para editar";
            case FORMULARIO_PERSONALIZADO:
                return "?????";
            case FORMULARIO_VISUALIZAR:
                return "Abre um formulário para visualizar ";
            case FORMULARIO_LISTAR:
                return "Abre um formulário listando";
            case FORMULARIO_MODAL:
                return "????";
            case SELECAO_DE_ACAO:
                return "Escolha entre as opções disponíveis";
            case CONTROLLER_SALVAR_EDICAO:
                return "Salvar Alterações";
            case CONTROLLER_SALVAR_NOVO:
                return "Criar Registro";
            case CONTROLLER_SALVAR_MODO_MERGE:
                return "Salva ou cria um registro caso não exista um com este id";
            case CONTROLLER_PERSONALIZADO:
                return "??????";
            case CONTROLLER_ATIVAR_DESATIVAR:
                return "Alterar Status entre Ativo e Inativo";
            case CONTROLLER_ATIVAR:
                return "Marca o registro como ativo";
            case CONTROLLER_REMOVER:
                return "Remove o registro de maneira irreversível";
            case CONTROLLER_DESATIVAR:
                return "Marca o registro como desativado";
            case GERENCIAR_DOMINIO:
                return "Gerencia os registros";
            default:
                throw new AssertionError(this.name());

        }
    }

    public FabTipoAcaoBase getAcaoBase() {
        switch (this) {
            case FORMULARIO_NOVO_REGISTRO:
            case FORMULARIO_EDITAR:
            case FORMULARIO_PERSONALIZADO:
            case FORMULARIO_VISUALIZAR:
            case FORMULARIO_LISTAR:
            case FORMULARIO_MODAL:

            case SELECAO_DE_ACAO:
                return FabTipoAcaoBase.FORMULARIO;
            case CONTROLLER_SALVAR_EDICAO:
            case CONTROLLER_SALVAR_NOVO:

            case CONTROLLER_SALVAR_MODO_MERGE:

            case CONTROLLER_PERSONALIZADO:

            case CONTROLLER_ATIVAR_DESATIVAR:

            case CONTROLLER_ATIVAR:

            case CONTROLLER_REMOVER:

            case CONTROLLER_DESATIVAR:
                return FabTipoAcaoBase.CONTROLLER;
            case GERENCIAR_DOMINIO:
                return FabTipoAcaoBase.GESTAO;
            default:
                throw new AssertionError(this.name());

        }
    }

}
