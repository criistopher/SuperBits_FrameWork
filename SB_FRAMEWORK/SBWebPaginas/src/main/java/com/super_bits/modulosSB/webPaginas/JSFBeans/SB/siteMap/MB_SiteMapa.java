package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroTela;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController.getFabricaAcaoByClasse;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ItfFabricaMenu;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class MB_SiteMapa implements Serializable {

    private final Map<String, ItfB_Pagina> paginasDoSistema = new HashMap<>();

    @Deprecated//Subistrituir o ItrB_pagina por EstruturaFormulario
    private Map<String, ItfB_Pagina> paginasOffline = new HashMap<>();
    private final Map<String, Field> paginasInjetadas = new HashMap<>();
    private boolean paginasDoSistemaConstruidas = false;

    /**
     *
     * @return Paginas do sistema personalizadas (não Gerenciaveis via Named
     */
    protected abstract Map<String, ItfB_Pagina> buildPaginas();

    /**
     *
     * Uma fabrica de menu é uma classe do tipo enum, contendo os diversos menus
     * do sistema
     *
     * @return Fabrica de menus do sistema
     */
    public abstract Class<? extends ItfFabricaMenu> getFabricaMenu();

    /**
     * Método que cria paginas do Sistemas
     *
     * @return Mapa com as Paginas do Sistema
     */
    protected Map<String, ItfB_Pagina> buildSystemPages() {
        try {
            if (paginasDoSistemaConstruidas) {
                return paginasDoSistema;
            }
            paginasDoSistemaConstruidas = true;

            String[] tags = {"erro-Critico"};
            B_Pagina erroCritico = new PaginaSimples("EC",
                    "/resources/SBComp/SBSystemPages/erroCriticoDeSistema.xhtml", tags);
            erroCritico.addTag("erroCritico");
            erroCritico.addParametro(new ParametroURL(true, "mensagem",
                    "Ocorreu um erro Crítico de sistema", ItfParametroTela.TIPO_URL.TEXTO));
            paginasDoSistema.put(erroCritico.getRecursoXHTML(), erroCritico);

            String[] tagsErroSQL = {"erro-SQL"};
            B_Pagina erroSQL = new PaginaSimples("ESQL",
                    "/resources/SBComp/SBSystemPages/erroSQLInfo.xhtml", tagsErroSQL);
            erroSQL.addTag("erroSQLInfo");

            erroSQL.addParametro(new ParametroURL(true, "mensagem",
                    "ocorreu um erro de informações de SQL", ItfParametroTela.TIPO_URL.TEXTO));
            paginasDoSistema.put(erroSQL.getRecursoXHTML(), erroSQL);

            String[] tagsPrime = {"teste-prime"};
            B_Pagina testePrime = new PaginaSimples("TP",
                    "/resources/SBComp/SBSystemPages/testePrime.xhtml", tagsPrime);
            testePrime.addTag("Testes PrimeFaces");
            paginasDoSistema.put(testePrime.getRecursoXHTML(), testePrime);

            String[] tagsNegado = {"Negado"};
            B_Pagina acessoNegado = new PaginaSimples("AN", "/resources/SBComp/SBSystemPages/acessoNegado.xhtml", tagsNegado);
            paginasDoSistema.put(acessoNegado.getRecursoXHTML(), acessoNegado);

            //   String[] tagsAcessos = {"acessos"};
            //     PgAcessos acessos = new PgAcessos();
            //    systemPages.put(acessos.getNomeCurto(), acessos);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro criando paginas básicas do sistema", t);
        }
        return paginasDoSistema;

    }

    public MB_SiteMapa() {
        try {

            try {
                paginasOffline = buildPaginas();
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro gerando paginas básicas do sistema (Aquelas que não foram injetadas pois tem apenas as propriedades basicas,  mas foram delclaradas no sitemap atraves do metodo BuildPaginas", t);
                paginasOffline = buildSystemPages();
            }

            List<Field> camposInjetados = UtilSBWPServletTools.getCamposReflexcaoInjetados(this.getClass());
            ItfFabricaAcoes fabrica = null;
            for (Field campo : camposInjetados) {
                try {

                    fabrica = (ItfFabricaAcoes) getFabricaAcaoByClasse(campo.getType());
                    if (fabrica == null) {
                        throw new UnsupportedOperationException("Impossível determinar a fabrica de ação para" + campo.getType() + " é Obrigatorio criar uma anotação nas classes de pagina com o metodo acao() retornando um ItfFabricaDeAcao");
                    }

                    ItfAcaoDoSistema acao = fabrica.getAcaoDoSistema();

                    if (acao != null) {
                        paginasInjetadas.put(acao.getComoGestaoEntidade().getXhtml(), campo);

                        if (paginasOffline.get(acao.getComoFormulario().getXhtml()) != null) {
                            throw new UnsupportedOperationException("Uma pagina vinculada a ação "
                                    + acao.getNomeUnico() + "Já foi adicionda");
                        }
                        paginasOffline.put(acao.getComoGestaoEntidade().getXhtml(), (ItfB_Pagina) campo.getType().newInstance());

                    } else {
                        throw new UnsupportedOperationException("A fabrica da ação foi encontrada, mas a ação retornou nulo verifique o retorno da ação " + fabrica);
                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro adicionando campo de sitemap>>>" + campo.getName() + " ->" + campo.getType() + " com anotação: " + fabrica, t);
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro construindo o mapa de paginas do Sitemap", t);
        }

    }

    public Collection<ItfB_Pagina> getPaginasOfflineEmLista() {
        return paginasOffline.values();
    }

    public Map<String, ItfB_Pagina> getPaginasOffline() {
        return paginasOffline;
    }

    public void setPaginasOffline(Map<String, ItfB_Pagina> paginasOffline) {
        this.paginasOffline = paginasOffline;
    }

    public ItfB_Pagina getPaginaNoContexto(String xhtmlGerenciarPG) throws UnsupportedOperationException {

        Field campo = paginasInjetadas.get(xhtmlGerenciarPG);
        ItfB_Pagina pagina;
        if (campo == null) {

            // verificando se não é um xhtml de paginaSimples
            pagina = paginasOffline.get(xhtmlGerenciarPG);
            SBCore.soutInfoDebug(xhtmlGerenciarPG + " encontrado como pagina simples sem Inject");
            if (pagina == null) {
                throw new UnsupportedOperationException("a pagina vinculada ao recurso não foi localizada no sitemap do projeto , o recurso enviado foi:[" + xhtmlGerenciarPG + "]");
            }

        } else {
            SBCore.soutInfoDebug(xhtmlGerenciarPG + " encontrado por injeção, retornando PG vinculado");
            campo.setAccessible(true);
            try {
                pagina = (ItfB_Pagina) campo.get(this);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                throw new UnsupportedOperationException("Impossível obter o Campo ", ex);
            }
        }

        return pagina;

    }

}
