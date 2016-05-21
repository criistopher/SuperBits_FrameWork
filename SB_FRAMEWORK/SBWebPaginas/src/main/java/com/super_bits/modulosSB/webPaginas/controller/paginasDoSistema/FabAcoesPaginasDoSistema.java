/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.paginasDoSistema;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcoesPaginasDoSistema implements ItfFabricaAcoes {

    @InfoAcaoGestaoEntidade(xhtmlDaAcao = "/site/home.xhtml", icone = "fa fa-heart-o", precisaPermissao = false)
    PAGINA_MB_HOME,
    @InfoAcaoGestaoEntidade(icone = "fa fa-lock", precisaPermissao = true)
    PAGINA_MB_ACESSO_NEGADO,
    @InfoAcaoGestaoEntidade(icone = "fa fa-key ", precisaPermissao = true)
    PAGINA_MB_LOGIN;

    @Override
    public AcaoDoSistema getAcaoDoSistema() {

        AcaoDoSistema acao = (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);

        return acao;

    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {

        try {
            return (ItfAcaoEntidade) getAcaoDoSistema();
        } catch (Throwable t) {
            String tipo = t.getStackTrace()[0].getMethodName();
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo " + tipo, t);
            return null;
        }

    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario() {
        try {
            return (ItfAcaoFormularioEntidade) getAcaoDoSistema();
        } catch (Throwable t) {
            String tipo = t.getStackTrace()[0].getMethodName();
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo " + tipo, t);
            return null;
        }

    }

    @Override
    public ItfAcaoControllerEntidade getAcaoEntidadeController() {
        try {
            return (ItfAcaoControllerEntidade) getAcaoDoSistema();
        } catch (Throwable t) {
            String tipo = t.getStackTrace()[0].getMethodName();
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo " + tipo, t);
            return null;
        }
    }

    @Override
    public ItfAcaoController getAcaoController() {
        try {
            return (ItfAcaoController) getAcaoDoSistema();
        } catch (Throwable t) {
            String tipo = t.getStackTrace()[0].getMethodName();
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo " + tipo, t);
            return null;
        }
    }

    @Override
    public AcaoGestaoEntidade geAcaoGerenciarEntidade() {
        try {
            return (AcaoGestaoEntidade) getAcaoDoSistema();
        } catch (Throwable t) {
            String tipo = t.getStackTrace()[0].getMethodName();
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo " + tipo, t);
            return null;
        }

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
