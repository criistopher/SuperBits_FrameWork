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

        switch (this) {
            case FORMULARIO_NOVO_REGISTRO:
                acaoPadrao.setNomePadrao("Criar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de novo registro");
                acaoPadrao.setIconePadrao("fa fa-plus");
                break;
            case FORMULARIO_EDITAR:
                acaoPadrao.setNomePadrao("Editar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de edicao de registro existente");
                acaoPadrao.setIconePadrao("fa fa-pencil");
                break;
            case FORMULARIO_VISUALIZAR:
                acaoPadrao.setNomePadrao("Visualizar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de visualizacao de registro");
                acaoPadrao.setIconePadrao("fa fa-eye");
                break;
            case CONTROLLER_SALVAR_EDICAO:
                acaoPadrao.setNomePadrao("Salvar registro editado");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao do registro editado ");
                acaoPadrao.setIconePadrao("fa fa-edit (alias)");
                break;
            case CONTROLLER_SALVAR_NOVO:
                acaoPadrao.setNomePadrao("Salvar registro criado");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao do registro criado");
                acaoPadrao.setIconePadrao("fa fa-save (alias)");
                break;
            case CONTROLLER_SALVAR_MODO_MERGE:
                acaoPadrao.setNomePadrao("Atualizar registro");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao");
                acaoPadrao.setIconePadrao("fa fa-save");
                break;
            case FORMULARIO_LISTAR:
                acaoPadrao.setNomePadrao("Listar registro");
                acaoPadrao.setDescricaoPadrao("Exibe os registros existentes");
                acaoPadrao.setIconePadrao("fa fa-list-alt");
                break;
            case CONTROLLER_ATIVAR_DESATIVAR:
                acaoPadrao.setNomePadrao("Alterador de status");
                acaoPadrao.setDescricaoPadrao("Ativa ou desativa conforme o status atual");
                acaoPadrao.setIconePadrao("fa fa-retweet");
                break;
            case CONTROLLER_ATIVAR:
                acaoPadrao.setNomePadrao("Ativador");
                acaoPadrao.setDescricaoPadrao("Define status como ativo");
                acaoPadrao.setIconePadrao("fa fa-check");
                break;
            case CONTROLLER_DESATIVAR:
                acaoPadrao.setNomePadrao("Desativar");
                acaoPadrao.setDescricaoPadrao("Define status como desativado");
                acaoPadrao.setIconePadrao("fa-close (alias)");
                break;
            case FORMULARIO_PERSONALIZADO:
                break;
            case SELECAO_DE_ACAO:
                acaoPadrao.setNomePadrao("Selecione uma direção");
                acaoPadrao.setDescricaoPadrao("Ação para seleção de ação a ser executada");
                break;
            case FORMULARIO_MODAL:
                break;
            case GERENCIAR_DOMINIO:

                break;
            case CONTROLLER_REMOVER:
                acaoPadrao.setNomePadrao("Excluir");
                acaoPadrao.setDescricaoPadrao("Exlui o registro do banco de dados");
                acaoPadrao.setIconePadrao("fa-close (alias)");
                break;
            case CONTROLLER_PERSONALIZADO:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return acaoPadrao;
    }
}
