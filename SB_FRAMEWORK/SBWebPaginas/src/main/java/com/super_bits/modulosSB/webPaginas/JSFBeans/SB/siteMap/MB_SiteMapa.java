package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfParametroTela;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.UtilSBController;
import static com.super_bits.Controller.UtilSBController.getFabricaAcaoByClasse;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.view.menu.ItfFabricaMenu;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class MB_SiteMapa implements Serializable {

    private final Map<String, ItfB_Pagina> paginasDoSistema = new HashMap<>();
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
        if (paginasDoSistemaConstruidas) {
            return paginasDoSistema;
        }
        paginasDoSistemaConstruidas = true;

        String[] tags = {"erro-Critico"};
        B_Pagina erroCritico = new PaginaSimples("EC",
                "/resources/SBComp/SBSystemPages/erroCriticoDeSistema.xhtml", tags);
        erroCritico.addTag("erroCritico");
        erroCritico.addParametro(new ParametroURL("mensagem",
                "Ocorreu um erro Crítico de sistema", ItfParametroTela.TIPO_URL.TEXTO));
        paginasDoSistema.put(erroCritico.getRecursoXHTML(), erroCritico);

        String[] tagsErroSQL = {"erro-SQL"};
        B_Pagina erroSQL = new PaginaSimples("ESQL",
                "/resources/SBComp/SBSystemPages/erroSQLInfo.xhtml", tagsErroSQL);
        erroSQL.addTag("erroSQLInfo");

        erroSQL.addParametro(new ParametroURL("mensagem",
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
        return paginasDoSistema;

    }

    public MB_SiteMapa() {
        try {
            paginasOffline = buildPaginas();

            List<Field> camposInjetados = UtilSBWPServletTools.getCamposReflexcaoInjetados(this.getClass());

            for (Field campo : camposInjetados) {
                try {

                    ItfFabricaAcoes fabrica = (ItfFabricaAcoes) getFabricaAcaoByClasse(campo.getType());
                    ItfAcaoGerenciarEntidade acao = fabrica.geAcaoGerenciarEntidade();

                    if (acao != null) {
                        paginasInjetadas.put(acao.getXhtml(), campo);
                        paginasOffline.put(acao.getXhtml(), (ItfB_Pagina) campo.getType().newInstance());
                    } else {
                        throw new UnsupportedOperationException("No siteMap só devem ser injetadas paginas com ação vinculada");
                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro adicionando campo de sitemap>>>" + campo.getName() + " ->" + campo.getType(), t);
                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro construindo o mapa de paginas do Sitemap", t);
        }

    }

    protected void addPaginaOnEOFFLine(B_Pagina pPagina) {

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

        if (campo == null) {
            throw new UnsupportedOperationException("a pagina vinculada ao recurso não foi localizada no sitemap do projeto , o recurso enviado foi:[" + xhtmlGerenciarPG + "]");
        }
        ItfB_Pagina pagina;
        campo.setAccessible(true);
        try {
            pagina = (ItfB_Pagina) campo.get(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new UnsupportedOperationException("Impossível obter o Campo ", ex);
        }

        return pagina;

    }

}
