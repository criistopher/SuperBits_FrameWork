/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.visualizacao;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.view.ServicoVisualizacaoAbstrato;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

/**
 *
 * @author salvioF
 */
public class ServicoVisuaslizacaoWebResponsivo extends ServicoVisualizacaoAbstrato {

    public ServicoVisuaslizacaoWebResponsivo() {
        super(TIPOS_COMUM_VISUALIZACAO.WEB_RESPONSIVO);
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
        return TIPOS_COMUM_VISUALIZACAO.WEB_RESPONSIVO;
    }

    @Override
    public String getCaminhoXhtmlItem(Class pEntidade) {
        String caminhoPersonalizado = "/resources/visualizacao/" + pEntidade.getSimpleName() + "/" + pEntidade.getSimpleName() + ".xhtml";

        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }

        return "/resources/SBComp/objeto/itemSimples.xhtml";
    }

    @Override
    public String getCaminhoLocalPastaImagem() {
        return UtilSBWPServletTools.getCaminhoLocalServletsResource();
    }

}
