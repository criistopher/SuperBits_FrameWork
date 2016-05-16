package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfParametroTela;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;

import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.PgAcessos;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.view.menu.ItfFabricaMenu;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

@SuppressWarnings("serial")
public abstract class MB_SiteMapa implements Serializable {

    // injetar PAginas aqui
    private Map<String, ItfB_Pagina> paginasOnline = new HashMap<String, ItfB_Pagina>();
    private Map<String, ItfB_Pagina> paginasDoSistema = new HashMap<String, ItfB_Pagina>();
    private Map<String, ItfB_Pagina> paginasOffline = new HashMap<String, ItfB_Pagina>();
    private Map<String, ItfB_Pagina> paginasPorRecurso = new HashMap<String, ItfB_Pagina>();
    private static boolean paginasDoSistemaConstruidas = false;
    private static boolean paginasOnlineConstruidas = false;
    private static boolean paginasOffilineConstruidas = false;

    private boolean foiInjetado = false;

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
        if (paginasDoSistemaConstruidas) {
            return paginasDoSistema;
        }
        paginasDoSistemaConstruidas = true;
        paginasDoSistema = new HashMap<>();
        String[] tags = {"erro-Critico"};
        B_Pagina erroCritico = new PaginaSimples("EC",
                "/resources/SBComp/SBSystemPages/erroCriticoDeSistema.xhtml", tags);
        erroCritico.addTag("erroCritico");
        erroCritico.addParametro(new ParametroURL("mensagem",
                "Ocorreu um erro Crítico de sistema", ItfParametroTela.TIPO_URL.TEXTO));
        paginasDoSistema.put(erroCritico.getNomeCurto(), erroCritico);

        String[] tagsErroSQL = {"erro-SQL"};
        B_Pagina erroSQL = new PaginaSimples("ESQL",
                "/resources/SBComp/SBSystemPages/erroSQLInfo.xhtml", tagsErroSQL);
        erroSQL.addTag("erroSQLInfo");

        erroSQL.addParametro(new ParametroURL("mensagem",
                "ocorreu um erro de informações de SQL", ItfParametroTela.TIPO_URL.TEXTO));
        paginasDoSistema.put(erroSQL.getNomeCurto(), erroSQL);

        String[] tagsPrime = {"teste-prime"};
        B_Pagina testePrime = new PaginaSimples("TP",
                "/resources/SBComp/SBSystemPages/testePrime.xhtml", tagsPrime);
        testePrime.addTag("Testes PrimeFaces");
        paginasDoSistema.put(testePrime.getNomeCurto(), testePrime);

        String[] tagsNegado = {"Negado"};
        B_Pagina acessoNegado = new PaginaSimples("AN", "/resources/SBComp/SBSystemPages/acessoNegado.xhtml", tagsNegado);
        paginasDoSistema.put(acessoNegado.getNomeCurto(), acessoNegado);

        //   String[] tagsAcessos = {"acessos"};
        //     PgAcessos acessos = new PgAcessos();
        //    systemPages.put(acessos.getNomeCurto(), acessos);
        return paginasDoSistema;

    }

    private void makePAginasPorRecurso() {
        for (ItfB_Pagina pg : paginasOnline.values()) {
            paginasPorRecurso.put(pg.getRecursoXHTML(), pg);
        }
    }

    @PostConstruct
    private void initBean() {
        addpgInjectOnline();
        makePAginasPorRecurso();
    }

    public MB_SiteMapa() {
        paginasOffline = buildPaginas();
        addpgInjectOffline();

    }

    private void addpgInjectOffline() {
        if (paginasOffilineConstruidas) {
            return;
        }
        paginasOffilineConstruidas = true;

        List<ItfB_Pagina> listaOffline = (List<ItfB_Pagina>) UtilSBWPServletTools
                .getObjetosInjetadosModoOffline(MB_Pagina.class, this);
        for (ItfB_Pagina pg : listaOffline) {
            addPaginaOffline(pg);
        }

    }

    private void addpgInjectOnline() {
        if (paginasOnlineConstruidas) {
            return;
        }
        paginasOnlineConstruidas = true;
        List<ItfB_Pagina> listaOnline = (List<ItfB_Pagina>) UtilSBWPServletTools.getObjetosInjetados(MB_Pagina.class, this);
        for (ItfB_Pagina pg : buildPaginas().values()) {
            addPaginaOnline(pg);
        }

        for (ItfB_Pagina pg : listaOnline) {

            addPaginaOnline(pg);

        }

        for (ItfB_Pagina pg : buildSystemPages().values()) {
            addPaginaOnline(pg);
        }

    }

    protected void addPaginaOnEOFFLine(B_Pagina pPagina) {

        paginasOnline.put(pPagina.getNomeCurto(), pPagina);
        paginasOffline.put(pPagina.getNomeCurto(), pPagina);
    }

    protected void addPaginaOffline(ItfB_Pagina pPagina) {

        if (paginasOffline.get(pPagina.getNomeCurto()) != null) {
            throw new UnsupportedOperationException("A pagina "
                    + pPagina.getClass().getSimpleName()
                    + " não pôde ser adicionada pois a tag: "
                    + pPagina.getNomeCurto()
                    + " já foi utilizada em " + paginasOffline.get(pPagina.getNomeCurto()).getClass().getSimpleName()
                    + " -->" + paginasOffline.get(pPagina.getNomeCurto()).getTitulo());

        }

        paginasOffline.put(pPagina.getNomeCurto(), pPagina);
    }

    protected void addPaginaOnline(ItfB_Pagina pPagina) {
        String classePagina = "";
        try {
            classePagina = pPagina.getClass().getSimpleName();

            paginasOnline.put(pPagina.getNomeCurto(), pPagina);
        } catch (Exception e) {

            FabErro.PARA_TUDO.paraSistema("ERRO ADCIONANDO PAGINA " + classePagina + " INJETADA(ONLINE) NO SITEMAP", e);
        }
    }

    public boolean isReferenciaAPagina(String pTag) {
        if (procuraPaginaOnlineByTagOuNome(pTag, true) != null) {
            return true;
        } else {
            return false;
        }
    }

    public ItfB_Pagina getPaginaByTagOuNome(String pTag) {
        return procuraPaginaOnlineByTagOuNome(pTag, false);
    }

    private ItfB_Pagina procuraPaginaOnlineByTagOuNome(String pTag,
            boolean permiteRetornoNulo) {

        if (pTag.equals(B_Pagina.PAGINAINICIALID)) {
            return paginasOffline.get(B_Pagina.PAGINAINICIALID);
        }
        // verificando Tentativa por Nome CUrto
        ItfB_Pagina pg = paginasOnline.get(pTag);
        if (pg != null) {
            pg.setTagUsada(pTag);
            if (UtilSBCoreStrings.makeStrUrlAmigavel(pg.getNomeCurto()).equals(pTag)) {
                pg.setTagUsada(pg.getTags().get(0));
                return pg;
            }
        }
        for (ItfB_Pagina pgt : paginasOffline.values()) {
            // verificando tentatia por Tag
            for (String tagpg : pgt.getTags()) {
                if (UtilSBCoreStrings.makeStrUrlAmigavel(pTag).equals(
                        UtilSBCoreStrings.makeStrUrlAmigavel(tagpg))) {
                    pgt.setTagUsada(tagpg);
                    paginasOnline.get(pgt.getNomeCurto()).setTagUsada(tagpg);
                    return paginasOnline.get(pgt.getNomeCurto());
                }
            }
        }

        if (!permiteRetornoNulo) {
            UtilSBWP_JSFTools.mensagens().erroSistema("Não encontrou a pagina  pelo nome ou TAG: ("
                    + pTag + ") ");
        }

        return null;

    }

    public Collection<ItfB_Pagina> getPaginasOnlineEmLista() {
        return paginasOnline.values();
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

    public Map<String, ItfB_Pagina> getPaginasOnline() {
        return paginasOnline;
    }

    public void setPaginasOnline(Map<String, ItfB_Pagina> paginasOnline) {
        this.paginasOnline = paginasOnline;
    }

    public Map<String, ItfB_Pagina> getPaginasPorRecurso() {
        return paginasPorRecurso;
    }

}
