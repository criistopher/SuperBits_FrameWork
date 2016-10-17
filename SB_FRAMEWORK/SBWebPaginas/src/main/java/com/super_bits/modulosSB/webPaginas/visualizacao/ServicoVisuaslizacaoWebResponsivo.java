/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.visualizacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.view.ServicoVisualizacaoAbstrato;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe para configuração de exibição padrão de Objetos do sistema
 *
 * Cada objeto deve conter uma classe de visualização, caso não contenha é
 * utilizado uma visulização padrão baseada na interface BeanSimples
 *
 *
 * @author salvioF
 */
public class ServicoVisuaslizacaoWebResponsivo extends ServicoVisualizacaoAbstrato {

    private static final String CAMINHO_ITEM_SIMPLES = "/resources/SBComp/objeto/itemSimples.xhtml";
    private static final String CAMINHO_ITEM_NORMAL = "/resources/SBComp/objeto/itemNormal.xhtml";
    public static final String CAMINHO_ITEM_SIMPLES_NULO = "/resources/SBComp/objeto/itemSimplesNulo.xhtml";
    public static final String CAMINHO_ITEM_NORMAL_NULO = "/resources/SBComp/objeto/itemNormalNulo.xhtml";

    public ServicoVisuaslizacaoWebResponsivo() {
        super(TIPOS_INTERFACES_COMUM_VISUALIZACAO.WEB_RESPONSIVO);
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
        return TIPOS_INTERFACES_COMUM_VISUALIZACAO.WEB_RESPONSIVO;
    }

    private String buildArqXHTML(Class pEntidade) {
        return buildArqXHTML(pEntidade, "Card");
    }

    private String buildArqXHTML(Class pEntidade, String ptipoVisualizacao) {
        return UtilSBCoreStrings.getPrimeiraLetraMinuscula(pEntidade.getSimpleName()) + ptipoVisualizacao + ".xhtml";
    }

    private String buildCaminhoPastaView(Class pEntidade) {
        return "/resources/objeto/" + pEntidade.getSimpleName();
    }

    private String buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM pTipoVisualizacao, Class pEntidade) {
        return buildCaminhoXHTML(pTipoVisualizacao, pEntidade, "Card");
    }

    private String buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM pTipoVisualizacao, Class pEntidade, String pTipoEspecial) {

        switch (pTipoVisualizacao) {
            case LABORATORIO:

                return buildCaminhoPastaView(pEntidade) + "/laboratorio/" + buildArqXHTML(pEntidade);

            case PUBLICADO:
                return buildCaminhoPastaView(pEntidade) + "/publicado/" + buildArqXHTML(pEntidade);
            default:
                throw new AssertionError(pTipoVisualizacao.name());

        }

    }

    @Override
    public String getCaminhoXhtmlItemCard(Class pEntidade) {
        String caminhoPersonalizado = buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM.PUBLICADO, pEntidade);
        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }
        System.out.println(caminhoPersonalizado + " - Nâo existe");
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public String getCaminhoLocalPastaImagem() {
        return UtilSBWPServletTools.getCaminhoLocalServletsResource();
    }

    @Override
    public String getRemotoPastaImagem() {
        return SBWebPaginas.getSiteURL() + "/resources";
    }

    @Override
    public String getCaminhoXhtmlItemCardLab(Class pEntidade) {
        String caminhoPersonalizado = buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM.LABORATORIO, pEntidade);
        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public String getCaminhoXhtmlItemAlternativo(Class pEntidade, String pNoneAlternativo) {
        String caminhoPersonalizado = buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM.LABORATORIO, pEntidade, pNoneAlternativo);
        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public String getCaminhoXhtmlItemAlternativoLab(Class pEntidade, String pNomeAlternativo) {
        String caminhoPersonalizado = buildCaminhoXHTML(TIPO_VISUALIZACAO_ITEM.LABORATORIO, pEntidade, pNomeAlternativo);
        if (UtilSBWP_JSFTools.isExisteEsteFormulario(caminhoPersonalizado)) {
            return caminhoPersonalizado;
        }
        return CAMINHO_ITEM_SIMPLES;
    }

    @Override
    public List<String> getTodasVisualizacoes(Class pEntidade) {
        return new ArrayList<>();
    }

}
