/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.fabricas.acoesDemonstracao;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 *
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.COMUNICACAO)
public enum FabAcaoDemonstracaoSB implements ItfFabricaAcoes {

    DEMONSTRACAO_MB_COMPONENTE,
    DEMONSTRACAO_MB_VALIDACAO,
    @InfoTipoAcaoGestaoEntidade(xhtmlDaAcao = "/resources/SBComp/debug/conformidadeInputCampo.xhtml", icone = "fa fa-pencil-square-o")
    TESTES_CAMPO_MB,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/debug/fichaTecnica.xhtml", icone = "fa fa-cogs")
    TESTES_CAMPO_FRM_FICHATECNICA,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/debug/verCampo.xhtml", icone = "fa fa-eye")
    TESTES_CAMPO_FRM_VER_CAMPO,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/debug/verCampoTodosFormatos.xhtml", icone = "fa fa-eye")
    TESTES_CAMPO_FRM_VER_CAMPO_EM_TODOS_FORMATOS,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/debug/onChange.xhtml", icone = "fa fa-retweet")
    TESTES_CAMPO_FRM_TESTAR_ONCHANGE,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/debug/laboratorio.xhtml", icone = "fa fa-flask")
    TESTES_CAMPO_FRM_LABORARATORIO,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/debug/instrucoes.xhtml", icone = "fa fa-question-circle")
    TESTES_CAMPO_FRM_INSTRUCOES,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/debug/camposDaAcao.xhtml", icone = "fa fa-question-circle")
    TESTES_CAMPO_FRM_VER_CAMPOS_DA_ACAO_FORMULARIO;

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        return getRegistro();
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        AcaoDoSistema acao = (AcaoDoSistema) UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
        switch (this) {

            case DEMONSTRACAO_MB_COMPONENTE:
                ItfAcaoGerenciarEntidade acaoDemoComponent = (ItfAcaoGerenciarEntidade) acao;
                acaoDemoComponent.setXhtml("/resources/SBComp/SBSystemPages/exemplos/demoSuperBitsFrameWork.xhtml");
                acao.setPrecisaPermissao(false);
                acao.setIconeAcao("fa fa-heart-o");

                break;
            case DEMONSTRACAO_MB_VALIDACAO:
                ItfAcaoGerenciarEntidade acaoDemoValidacao = (ItfAcaoGerenciarEntidade) acao;
                acaoDemoValidacao.setXhtml("/resources/SBComp/SBSystemPages/exemplosAdamantium/validacao.xhtml");
                acao.setIconeAcao("fa fa-heart-o");
                acao.setPrecisaPermissao(false);
                break;

        }

        return acao;
        // Dica: è possivel que este código pareça estranho para voce.
        // afinal estamos alterando a acaoDemoComponent, e retornando ação,
        // mas acredite, isso funciona,  devido a  um dos motivos do Java ser especial,
        // A passagem de valor no java é por referencia(Pequise sobre isso), exceto em tipos primitivos e String (A passagem por referencia é bem popular em  linguagens que valorizam a programação orientadas a objeto)
    }

    @Override
    public Class getEntidadeDominio() {

        return UsuarioSB.class;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

}
