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
public enum FabTipoAcaoParao implements ItfFabrica {

    NOVO_ABRIR_FORMULARIO,
    EDITAR_ABRIR_FORMULARIO,
    SALVAR_EDICAO,
    SALVAR_NOVO,
    SALVAR_MODO_MERGE,
    ATIVAR_DESATIVAR,
    ATIVAR,
    DESATIVAR,
    VISUALIZAR_ABRIR_FORMULARIO;

    @Override
    public TipoAcaoPadrao getRegistro() {

        TipoAcaoPadrao acaoPadrao = new TipoAcaoPadrao();

        switch (this) {
            case NOVO_ABRIR_FORMULARIO:
                acaoPadrao.setNomePadrao("Criar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de novo registro");
                acaoPadrao.setIconePadrao("fa fa-plus");
                break;
            case EDITAR_ABRIR_FORMULARIO:
                acaoPadrao.setNomePadrao("Editar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de edicao de registro existente");
                acaoPadrao.setIconePadrao("fa fa-pencil");
                break;
            case VISUALIZAR_ABRIR_FORMULARIO:
                acaoPadrao.setNomePadrao("Visualizar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de visualizacao de registro");
                acaoPadrao.setIconePadrao("fa fa-list-alt");
                break;
            case SALVAR_EDICAO:
                acaoPadrao.setNomePadrao("Salvar registro editado");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao do registro editado ");
                acaoPadrao.setIconePadrao("fa fa-edit (alias)");
                break;
            case SALVAR_NOVO:
                acaoPadrao.setNomePadrao("Salvar registro criado");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao do registro criado");
                acaoPadrao.setIconePadrao("fa-save (alias)");
                break;
            case SALVAR_MODO_MERGE:
                acaoPadrao.setNomePadrao("Atualizar registro");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao");
                acaoPadrao.setIconePadrao("fa fa-random");
                break;
            case ATIVAR_DESATIVAR:
                acaoPadrao.setNomePadrao("Alterador de status");
                acaoPadrao.setDescricaoPadrao("Ativa ou desativa conforme o status atual");
                acaoPadrao.setIconePadrao("fa fa-retweet");
                break;
            case ATIVAR:
                acaoPadrao.setNomePadrao("Ativador");
                acaoPadrao.setDescricaoPadrao("Define status como ativo");
                acaoPadrao.setIconePadrao("fa fa-check");
                break;
            case DESATIVAR:
                acaoPadrao.setNomePadrao("Desativador");
                acaoPadrao.setDescricaoPadrao("Define status como desativado");
                acaoPadrao.setIconePadrao("fa-close (alias)");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return acaoPadrao;
    }
}