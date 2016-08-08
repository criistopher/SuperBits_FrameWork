/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.super_bits.modulos.SBAcessosModel.UtilSBAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.AcaoComLink;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_SiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ParametroURL;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.ErroCritico.InfoErroCritico;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 *
 * @author Salvio
 */
//@WebServlet("*.wp")
public class WebPaginasServlet extends HttpServlet implements Serializable {

    private Map<String, String> mapaRecursos;
    private Map<String, ItfB_Pagina> mapaPaginas;

    @Inject
    private ControleDeSessaoWeb controleDeSessao;
    @Inject
    private InfoErroCritico erroCritico;
    @Inject
    private InfoWebApp infoAplicacao;

    public void buildMapaRecurso() {
        if (mapaPaginas != null) {
            return;
        }

        try {
            System.out.println("Montando Mapa de paginas Para encaminhamento Via Servlet");

            Class sitemapClass = SBWebPaginas.getSiteMap();
            MB_SiteMapa sitemapa;
            sitemapa = (MB_SiteMapa) sitemapClass.newInstance();

            Map<String, ItfB_Pagina> paginas = sitemapa.getPaginasOffline();
            // criando mapa de recursos a partir das paginas
            mapaRecursos = new HashMap<>();
            mapaPaginas = new HashMap<>();
            for (String key : paginas.keySet()) {
                ItfB_Pagina pg = paginas.get(key);
                List<String> tags = pg.getTags();
                mapaPaginas.put(pg.getRecursoXHTML(), pg);
                List<String> tagsDaPagina = pg.getTags();
                mapaRecursos.put(key, pg.getRecursoXHTML());
                mapaRecursos.put(pg.getNomeCurto(), pg.getRecursoXHTML());
                for (String tag : tagsDaPagina) {
                    mapaRecursos.put(UtilSBCoreStrings.makeStrUrlAmigavel(tag), pg.getRecursoXHTML());
                }
            }

            if (!infoAplicacao.isAceosMBConfiguradas()) {

                try {
                    for (ItfB_Pagina pagina : mapaPaginas.values()) {
                        if (pagina.getAcaoVinculada() != null) {
                            infoAplicacao.putNovoManagedBen(pagina.getAcaoVinculada(), new AcaoComLink(pagina.getAcaoVinculada(), pagina));
                        }
                    }

                } catch (Throwable t) {
                    FabErro.PARA_TUDO.paraSistema("Erro Criando Ações MB", t);
                }

            }

            System.out.println("Contexto Inicializado");
        } catch (InstantiationException | IllegalAccessException ex) {

            FabErro.PARA_TUDO.paraSistema("erro gerando cadastrado de recursos via siteMap", ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        buildMapaRecurso();

        ItfUsuario usuario = null;
        if (controleDeSessao != null) {

            System.out.println("CONTROLE DE SESSAO ENCONTRADO NO SERVLET");
            System.out.println("Usuario:" + controleDeSessao.getSessaoAtual().getUsuario().getEmail());
            usuario = controleDeSessao.getSessaoAtual().getUsuario();

        }

        System.out.println("Iniciando DO Get");
        String caminhoCOmpleto = req.getRequestURL().toString();
        String[] partes = caminhoCOmpleto.split("/");
        //String recurso = "/resources/SBComp/SBSystemPages/paginaNaoEncontrada.xhtml";
        String recurso = null;
        for (String parteUrl : partes) {

            recurso = mapaRecursos.get(parteUrl);
            if (recurso != null) {
                try {
                    B_Pagina pagina = (B_Pagina) mapaPaginas.get(recurso);
                    List<ParametroURL> parametros = pagina.getParametrosURL();

                    if (pagina.getAcaoVinculada() != null) {
                        if (pagina.getAcaoVinculada().isPrecisaPermissao()) {
                            if (!UtilSBAcessosModel.acessoAcaoPermitido(usuario, (AcaoDoSistema) pagina.getAcaoVinculada())) {
                                RequestDispatcher wp = req.getRequestDispatcher("/resources/SBComp/SBSystemPages/acessoNegado.xhtml");
                                wp.forward(req, resp);
                                return;
                            }
                        }
                    }

                    int idParametro = 0;

                    for (String valorParametro : UtilSBWPServletTools.getParametrosDaPagina(caminhoCOmpleto)) {
                        System.out.println("localizando Parametro por String" + valorParametro);
                        String nomeParametro = pagina.getNomeParametroById(idParametro);
                        if (nomeParametro != null) {
                            System.out.println("Adcionando no request:" + nomeParametro + " valor:" + valorParametro);
                            req.setAttribute(nomeParametro, valorParametro);
                        }

                        idParametro++;
                    }
                    break;
                } catch (Throwable e) {

                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("erro tentnado aplicar paramentros de URL padrão", e);
                    break;
                }
            }
        }

        if (recurso == null) {
            recurso = controleDeSessao.getSessaoAtual().getUsuario().getGrupo().getXhtmlPaginaInicial();
        }
        System.out.println("ForWard para" + recurso);
        RequestDispatcher wp = req.getRequestDispatcher(recurso);

        System.out.println("Dispatcher:" + wp.toString() + "-" + wp.getClass());

        try {
            wp.forward(req, resp);

        } catch (Throwable erro) {
            System.out.println("Erro Reidenrizando pagina>>>" + recurso);
            erroCritico.setBeanErroCritico(new InfoErroCritico("Erro gerenado" + recurso, erro));

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando forward de Recurso " + recurso, erro);
            //    UtilSBWP_JSFTools.vaParaPaginadeErro("Erro reiderizando pagina");
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro executando forward de Recurso" + recurso, erro);
        }

    }

}
