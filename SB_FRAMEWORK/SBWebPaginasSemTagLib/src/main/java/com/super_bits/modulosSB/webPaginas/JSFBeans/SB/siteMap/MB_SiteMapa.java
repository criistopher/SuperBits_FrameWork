package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import static com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController.getFabricaAcaoByClasse;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.sistema.PgAcessoNegado;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.sistema.PgErroCRitico;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.sistema.PgErroSQLDeveloper;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.sistema.PgViewExPirou;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public abstract class MB_SiteMapa implements ItfSiteMapa {

    private static final Map<String, Field> paginasInjetadas = new HashMap<>();

    @Inject
    private PgErroCRitico erroCritico;
    @Inject
    private PgErroSQLDeveloper erroSQLDevloper;
    @Inject
    private PgAcessoNegado erroAcessoNegado;
    @Inject
    private PgViewExPirou expirou;

    private ItfSiteMapa siteMapa;
    private static boolean siteMapaCriado = false;

    public MB_SiteMapa() {
        System.out.println("");
        if (!siteMapaCriado) {
            try {

                List<Field> camposInjetados = UtilSBWPServletTools.getCamposReflexcaoInjetados(this.getClass());
                camposInjetados.addAll(UtilSBWPServletTools.getCamposReflexcaoInjetados(MB_SiteMapa.class));
                ItfFabricaAcoes fabrica = null;
                List<Class> classesDeFormuario = new ArrayList<>();
                for (Field campo : camposInjetados) {
                    try {

                        fabrica = (ItfFabricaAcoes) getFabricaAcaoByClasse(campo.getType());
                        if (fabrica == null) {
                            throw new UnsupportedOperationException("Impossível determinar a fabrica de ação para" + campo.getType() + " é Obrigatorio criar uma anotação nas classes de pagina com o metodo acao() retornando um ItfFabricaDeAcao");
                        }

                        ItfAcaoDoSistema acao = fabrica.getAcaoDoSistema();

                        if (acao != null) {
                            paginasInjetadas.put(acao.getComoGestaoEntidade().getXhtml(), campo);
                            classesDeFormuario.add(campo.getType());

                        } else {
                            throw new UnsupportedOperationException("A fabrica da ação foi encontrada, mas a ação retornou nulo verifique o retorno da ação " + fabrica);
                        }
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro adicionando campo de sitemap>>>" + campo.getName() + " ->" + campo.getType() + " com anotação: " + fabrica, t);
                    }
                }
                MapaDeFormularios.buildEstrutura(classesDeFormuario);

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro construindo o mapa de paginas do Sitemap", t);
            }
        }
        siteMapaCriado = true;
    }

    @Override
    public ItfB_Pagina getPaginaNoContexto(String xhtmlGerenciarPG) throws UnsupportedOperationException {

        Field campo = paginasInjetadas.get(xhtmlGerenciarPG);
        ItfB_Pagina pagina;
        if (campo == null) {

            throw new UnsupportedOperationException("a pagina vinculada ao recurso não foi localizada no sitemap do projeto , o recurso enviado foi:[" + xhtmlGerenciarPG + "]");

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
