/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.google.common.collect.Lists;
import com.super_bits.modulos.SBAcessosModel.UtilSBAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.AcaoComLink;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.B_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_SiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.ErroCritico.InfoErroCritico;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

    private static final Map<String, String> MAPA_RECURSOS = new HashMap<>();
    private static final Map<String, ItfB_Pagina> MAPA_PAGINAS = new HashMap<>();
    private static boolean mapaConfigurado = false;

    @Inject
    private ControleDeSessaoWeb controleDeSessao;
    @Inject
    private InfoErroCritico erroCritico;
    public final static Map<String, AcaoComLink> MAPA_ACOESMANAGED_BEAN = new HashMap<>();

    public static AcaoComLink getAcaoComLinkByXHTML(String pXhtml) {
        if (!mapaConfigurado) {
            buildMapaRecurso();
        }
        ItfB_Pagina paginaVinculada = MAPA_PAGINAS.get(pXhtml);
        if (paginaVinculada == null) {
            throw new UnsupportedOperationException("Nenguma pagina vinculada ao xhtml" + pXhtml + "Certifique que a pagina tenha sido declarada no sitemap");
        }
        return MAPA_ACOESMANAGED_BEAN.get(paginaVinculada.getAcaoVinculada().getNomeUnico());
    }

    private static void buildMapaRecurso() {
        if (!mapaConfigurado) {

            try {
                System.out.println("Montando Mapa de paginas Para encaminhamento Via Servlet");
                Class sitemapClass = SBWebPaginas.getSiteMap();
                MB_SiteMapa sitemapa;
                sitemapa = (MB_SiteMapa) sitemapClass.newInstance();
                Map<String, ItfB_Pagina> paginas = sitemapa.getPaginasOffline();
                // criando mapa de recursos a partir das paginas

                System.out.println("Foram encontradas " + paginas.keySet().size() + " paginas gerenciaveis");
                for (String key : paginas.keySet()) {
                    ItfB_Pagina pg = paginas.get(key);
                    List<String> tags = pg.getTags();
                    MAPA_PAGINAS.put(pg.getRecursoXHTML(), pg);
                    List<String> tagsDaPagina = pg.getTags();
                    if (MAPA_RECURSOS.get(key) != null) {
                        throw new UnsupportedOperationException("A tag" + key + " já foi utilizada para pagina" + MAPA_RECURSOS.get(key).getClass().getSimpleName());
                    }
                    MAPA_RECURSOS.put(key, pg.getRecursoXHTML());
                    MAPA_RECURSOS.put(pg.getNomeCurto(), pg.getRecursoXHTML());
                    Logger.getGlobal().log(Level.INFO, "Pagina {0} tag{1}", new Object[]{pg.getRecursoXHTML(), pg.getNomeCurto()});
                    for (String tag : tagsDaPagina) {
                        MAPA_RECURSOS.put(UtilSBCoreStrings.makeStrUrlAmigavel(tag), pg.getRecursoXHTML());

                    }
                }

                if (!mapaConfigurado) {

                    try {
                        for (ItfB_Pagina pagina : MAPA_PAGINAS.values()) {
                            if (pagina.getAcaoVinculada() != null) {
                                if (MAPA_ACOESMANAGED_BEAN.get(pagina.getAcaoVinculada().getNomeUnico()) != null) {
                                    throw new UnsupportedOperationException("Uma ação de gestão só pode ser vinculada a uma Pgina, no entando a pagina" + pagina.getAcaoVinculada() + "está vinculada a " + MAPA_ACOESMANAGED_BEAN.get(pagina.getAcaoVinculada().getNomeUnico()).getClass().getSimpleName() + " e a" + pagina.getClass().getSimpleName());
                                }
                                MAPA_ACOESMANAGED_BEAN.put(pagina.getAcaoVinculada().getNomeUnico(), new AcaoComLink(pagina.getAcaoVinculada(), pagina));
                            }
                        }

                    } catch (Throwable t) {
                        FabErro.PARA_TUDO.paraSistema("Erro Criando Ações MB", t);
                    }

                }
                mapaConfigurado = true;
                System.out.println("Contexto Inicializado");
            } catch (InstantiationException | IllegalAccessException ex) {

                FabErro.PARA_TUDO.paraSistema("erro gerando cadastrado de recursos via siteMap", ex);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Iniciando servlet WP");
        buildMapaRecurso();
        Cookie[] teste = req.getCookies();

        for (Cookie cook : teste) {
            System.out.println("______________");
            System.out.println(cook.getDomain());
            System.out.println(cook.getName());
            System.out.println(cook.getSecure());
            System.out.println(cook.getComment());
            System.out.println(cook.getValue());
        }

        try {

            controleDeSessao.getSessaoAtual();
            System.out.println(controleDeSessao.getSessaoAtual().getUsuario().getNome());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Controle de sessão não pode ser encontrado", t);
        }

        ItfUsuario usuario = controleDeSessao.getSessaoAtual().getUsuario();

        String caminhoCOmpleto = req.getRequestURL().toString();
        String[] partes = caminhoCOmpleto.split("/");
        //String recurso = "/resources/SBComp/SBSystemPages/paginaNaoEncontrada.xhtml";
        String recurso = null;
        for (String parteUrl : partes) {

            recurso = MAPA_RECURSOS.get(parteUrl);

            if (recurso != null) {
                try {
                    B_Pagina pagina = (B_Pagina) MAPA_PAGINAS.get(recurso);

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

                    break;
                } catch (Throwable e) {

                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("erro tentnado aplicar paramentros de URL padrão", e);

                    break;
                }
            }
        }

        if (recurso == null) {
            recurso = controleDeSessao.getSessaoAtual().getUsuario().getGrupo().getXhtmlPaginaInicial();
            if (SBCore.getEstadoAPP().equals(SBCore.ESTADO_APP.HOMOLOGACAO)) {
                try {
                    throw new UnsupportedOperationException("url não asssociada a MB" + caminhoCOmpleto + "  ");

                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Endereço url não relacionado a nenhuma pagina, certifique se a pagima foi declarada no sitemap ou se o endereço foi digitado corretamente", t);
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, Lists.newArrayList(MAPA_RECURSOS.keySet()).toString(), t);
                }
            }
            if (recurso == null) {
                recurso = "/site/home.xhtml";
            }
        }
        System.out.println("ForWard para" + recurso);

        ConfiguracoesDeFormularioPorUrl configuracoes = new ConfiguracoesDeFormularioPorUrl(caminhoCOmpleto);
        req.setAttribute("CfgURLFrm", configuracoes);

        RequestDispatcher wp = req.getRequestDispatcher(recurso);

        System.out.println("Dispatcher:" + wp.toString() + "-" + wp.getClass());

        try {
            wp.forward(req, resp);

        } catch (Throwable erro) {
            System.out.println("Erro Reidenrizando pagina>>>" + recurso);
            //erroCritico.setBeanErroCritico(new InfoErroCritico("Erro gerenado" + recurso, erro));

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando forward de Recurso " + recurso, erro);
            //UtilSBWP_JSFTools.vaParaPaginadeErro("Erro reiderizando pagina");

        }

    }

}
