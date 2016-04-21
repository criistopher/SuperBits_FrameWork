/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.acoesDoSistema;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoes;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 *
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.COMUNICACAO)
public enum FabAcaoSistemaSB implements ItfFabricaAcoes {

    ACESSOS_MB_GERENCIAR,
    DEMONSTRACAO_MB_COMPONENTE,
    DEMONSTRACAO_MB_VALIDACAO;

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        return getRegistro();
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        AcaoDoSistema acao = (AcaoDoSistema) UtilFabricaDeAcoes.getNovaAcao(this);
        switch (this) {
            case ACESSOS_MB_GERENCIAR:
                break;
            case DEMONSTRACAO_MB_COMPONENTE:
                acao.setPrecisaPermissao(false);
                break;
            case DEMONSTRACAO_MB_VALIDACAO:
                break;
            default:
                throw new AssertionError(this.name());
        }
        return acao;
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
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        return (ItfAcaoGerenciarEntidade) getRegistro();
    }

    @Override
    public ItfAcaoController getAcaoController() {
        return (ItfAcaoController) getRegistro();

    }

    @Override
    public Class getEntidadeDominio() {
        switch (this) {
            case ACESSOS_MB_GERENCIAR:
                break;
            case DEMONSTRACAO_MB_COMPONENTE:
                break;
            case DEMONSTRACAO_MB_VALIDACAO:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return UsuarioSB.class;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoes.getModuloByFabrica(this).getNome();
    }

}
