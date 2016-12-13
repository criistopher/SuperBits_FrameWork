/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema;

import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;

/**
 *
 * @author desenvolvedor
 *
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcaoPaginasDoSistema implements ItfFabricaAcoes {

    @InfoTipoAcaoGestaoEntidade(xhtmlDaAcao = "/site/home.xhtml", icone = "fa fa-heart-o", precisaPermissao = false)
    PAGINA_MB_HOME,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-lock", precisaPermissao = false, xhtmlDaAcao = "/resources/SBComp/SBSystemPages/acessoNegado.xhtml")
    PAGINA_MB_ACESSO_NEGADO,
    @InfoTipoAcaoGestaoEntidade(iconeFonteAnsowame = FabIconeFontAwesome.REG_ATUALIZAR, precisaPermissao = false, xhtmlDaAcao = "/resources/SBComp/SBSystemPages/viewExpirou.xhtml")
    PAGINA_MB_VIEW_EXPIROU,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = "/resources/SBComp/SBSystemPages/login.xhtml")
    PAGINA_MB_LOGIN,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-key", precisaPermissao = false, xhtmlDaAcao = "/resources/SBComp/SBSystemPages/erroGenerico.xhtml")
    PAGINA_MB_ERRO_CRITICO;

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
