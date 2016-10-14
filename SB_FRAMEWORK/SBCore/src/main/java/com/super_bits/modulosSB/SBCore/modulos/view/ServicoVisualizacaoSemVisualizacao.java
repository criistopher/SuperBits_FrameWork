/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;

/**
 *
 * @author salvioF
 */
public class ServicoVisualizacaoSemVisualizacao extends ServicoVisualizacaoAbstrato {

    public ServicoVisualizacaoSemVisualizacao() {
        super(TIPOS_COMUM_VISUALIZACAO.DESKTOP);
    }

    @Override
    public String getCaminhoXhtmlAcaoDoSistema(ItfAcaoFormulario pAcao) {
        throw new UnsupportedOperationException("Ainda não implementado"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizarFormularioGestao(ItfAcaoGerenciarEntidade acaoForm) {
        throw new UnsupportedOperationException("Ainda não implementado"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServicoVisualizacaoAbstrato.TIPOS_COMUM_VISUALIZACAO getTipoVisualizacao() {
        return TIPOS_COMUM_VISUALIZACAO.DESKTOP;
    }

    @Override
    public String getCaminhoXhtmlItem(Class pEntidade) {

        return "/resources/visualizacao/" + pEntidade + "/" + pEntidade + ".xhtml";
    }

    @Override
    public String getCaminhoLocalPastaImagem() {
        return "/naoimplementado";
    }

}
