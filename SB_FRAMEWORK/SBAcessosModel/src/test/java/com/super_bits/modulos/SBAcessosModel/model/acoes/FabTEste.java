/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosTestes(modulo = FabModulosTestes.MODULO_TESTE)
public enum FabTEste implements ItfFabricaAcoes {

    OBJETO_FRM_NOVO,
    OBJETO_MB_GERENCIAR,
    OBJETO_CTR_ALTERAR_STATUS,
    USUARIO_TESTE_MB_GERENCIAR,
    USUARIO_TESTE_FRM_NOVO,
    USUARIO_TESTE_FRM_EDITAR;

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        return (ItfAcaoDoSistema) getRegistro();
    }

    @Override
    public Class getEntidadeDominio() {

        switch (this) {
            case OBJETO_FRM_NOVO:
            case OBJETO_MB_GERENCIAR:
            case OBJETO_CTR_ALTERAR_STATUS:
                return GrupoUsuarioSB.class;
            case USUARIO_TESTE_MB_GERENCIAR:
            case USUARIO_TESTE_FRM_NOVO:
            case USUARIO_TESTE_FRM_EDITAR:
                return UsuarioSB.class;
            default:
                throw new AssertionError(this.name());

        }

    }

    @Override
    public Object getRegistro() {
        ItfAcaoDoSistema acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
        ItfAcaoFormularioEntidade acaoform = null;
        if (acao.isUmaAcaoFormulario()) {
            acaoform = (ItfAcaoFormularioEntidade) acao;
        }

        switch (this) {
            case OBJETO_FRM_NOVO:
                acao.setNomeAcao("Teste");
                break;
            case OBJETO_MB_GERENCIAR:
                break;
            case OBJETO_CTR_ALTERAR_STATUS:

//                acaoform.setXhtml("asdfasdf");
                break;
            case USUARIO_TESTE_MB_GERENCIAR:
                break;
            case USUARIO_TESTE_FRM_NOVO:
                break;
            case USUARIO_TESTE_FRM_EDITAR:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return acao;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

}
