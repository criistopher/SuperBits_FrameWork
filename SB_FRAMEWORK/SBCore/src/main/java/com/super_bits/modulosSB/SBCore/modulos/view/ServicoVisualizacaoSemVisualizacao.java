/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class ServicoVisualizacaoSemVisualizacao extends ServicoVisualizacaoAbstrato {

    public ServicoVisualizacaoSemVisualizacao() {
        super(TIPOS_INTERFACES_COMUM_VISUALIZACAO.DESKTOP);
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
    public ServicoVisualizacaoAbstrato.TIPOS_INTERFACES_COMUM_VISUALIZACAO getTipoVisualizacao() {
        return TIPOS_INTERFACES_COMUM_VISUALIZACAO.DESKTOP;
    }

    @Override
    public String getCaminhoXhtmlItemCard(Class pEntidade) {

        return "/resources/visualizacao/" + pEntidade + "/" + pEntidade + ".xhtml";
    }

    @Override
    public String getCaminhoLocalPastaImagem() {
        return "/naoimplementado";
    }

    @Override
    public String getRemotoPastaImagem() {
        return "http://sistemanaoImplementado.superbits.org";
    }

    @Override
    public String getCaminhoXhtmlItemCardLab(Class pEntidade) {
        return "/naoimplementado";
    }

    @Override
    public String getCaminhoXhtmlItemAlternativo(Class pEntidade, String nomeAlternativo) {
        return "/naoimplementado";
    }

    @Override
    public String getCaminhoXhtmlItemAlternativoLab(Class pEntidade, String nomeAlternativo) {
        return "/naoimplementado";
    }

    @Override
    public List<String> getTodasVisualizacoes(Class pEntidade) {
        return new ArrayList<>();
    }

}
