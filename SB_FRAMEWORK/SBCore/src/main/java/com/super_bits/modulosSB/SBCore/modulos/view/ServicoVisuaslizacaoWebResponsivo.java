/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizarFormularioGestao(ItfAcaoGerenciarEntidade acaoForm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServicoVisualizacaoAbstrato.TIPOS_COMUM_VISUALIZACAO getTipoVisualizacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCaminhoXhtmlItem(Class pEntidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
