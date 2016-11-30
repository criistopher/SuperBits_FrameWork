/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.super_bits.modulos.SBAcessosModel.UtilSBAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.AcaoComLink;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfSiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MapaDeFormularios;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.ErroCritico.InfoErroCritico;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
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

    @Inject
    private ControleDeSessaoWeb controleDeSessao;
    @Inject
    private InfoErroCritico erroCritico;
    @Inject
    private ItfSiteMapa siteMapa;
    public final static Map<String, AcaoComLink> MAPA_ACOESMANAGED_BEAN = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        // Instanciado site mapa para construção de Formulario
        siteMapa.getFabricaMenu();
        try {
            for (EstruturaDeFormulario pagina : MapaDeFormularios.getTodasEstruturas()) {
                if (pagina.getAcaoGestaoVinculada() != null) {
                    if (MAPA_ACOESMANAGED_BEAN.get(pagina.getAcaoGestaoVinculada().getNomeUnico()) != null) {
                        throw new UnsupportedOperationException("Uma ação de gestão só pode ser vinculada a uma Pgina, no entando a pagina"
                                + pagina.getAcaoGestaoVinculada() + "está vinculada a " + MAPA_ACOESMANAGED_BEAN.get(pagina.getAcaoGestaoVinculada().getNomeUnico()).getClass().getSimpleName() + " e a"
                                + pagina.getClass().getSimpleName());
                    }
                    MAPA_ACOESMANAGED_BEAN.put(pagina.getAcaoGestaoVinculada().getNomeUnico(),
                            new AcaoComLink(pagina));
                }
            }

        } catch (Throwable t) {
            FabErro.PARA_TUDO.paraSistema("Erro Criando Ações MB", t);
        }

        System.out.println("Contexto Inicializado");

    }

    public static AcaoComLink getAcaoComLinkByXHTML(String pXhtml) {

        EstruturaDeFormulario paginaVinculada = MapaDeFormularios.getEstruturaByXHTMLDeGestao(pXhtml);
        if (paginaVinculada == null) {
            throw new UnsupportedOperationException("Nenguma pagina vinculada ao xhtml" + pXhtml + "Certifique que a pagina tenha sido declarada no sitemap");
        }
        return MAPA_ACOESMANAGED_BEAN.get(paginaVinculada.getAcaoGestaoVinculada().getNomeUnico());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Iniciando servlet WP");

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
            EstruturaDeFormulario pagina = MapaDeFormularios.getPaginaBySlug(parteUrl);

            if (pagina != null) {
                try {
                    if (pagina.getAcaoGestaoVinculada() != null) {
                        recurso = pagina.getAcaoGestaoVinculada().getXhtml();
                        if (pagina.getAcaoGestaoVinculada().isPrecisaPermissao()) {
                            if (!UtilSBAcessosModel.acessoAcaoPermitido(usuario, (AcaoDoSistema) pagina.getAcaoGestaoVinculada())) {
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
