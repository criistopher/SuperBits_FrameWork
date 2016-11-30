/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.google.common.collect.Lists;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ParametroURL;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.sistema.PgAcessoNegado;
import com.super_bits.modulosSB.webPaginas.util.UtillSBWPReflexoesWebpaginas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class EstruturaDeFormulario {

    private final AcaoGestaoEntidade acaoGestaoVinculada;
    private final Map<String, ParametroURL> parametrosURL;
    private final List<String> tagsPalavraChave;

    private final int quantidadeParametrosObrigatorios;
    private final boolean umaPaginaCadastroEntidade;
    private final String nomeCurto;
    private final boolean acessoLivre;
    private final String urlPadrao;
    private final String recursoXHTML;
    private boolean permitirSelecaoRegistroPorURL;

    public EstruturaDeFormulario(Class pClassePAgina) {
        parametrosURL = new HashMap<>();
        tagsPalavraChave = new ArrayList();
        int qtdObrigatorio = 0;
        for (ParametroURL pr : UtillSBWPReflexoesWebpaginas.buildParametrosDaPagina(pClassePAgina)) {
            if (pr.isParametroObrigatorio()) {
                qtdObrigatorio++;
            }
            parametrosURL.put(pr.getNome(), pr);
        }
        quantidadeParametrosObrigatorios = qtdObrigatorio;

        umaPaginaCadastroEntidade = UtilSBCoreReflexao.isInterfaceImplementadaNaClasse(pClassePAgina, ItfPaginaGerenciarEntidade.class);

        try {
            InfoPagina anotacaoInfopagina = (InfoPagina) pClassePAgina.getAnnotation(InfoPagina.class);
            if (anotacaoInfopagina == null) {
                throw new UnsupportedOperationException("A pagina" + pClassePAgina.getSimpleName() + " não foi anotada com Info-Pagina");
            }

            if (anotacaoInfopagina.nomeCurto() == null) {
                throw new UnsupportedOperationException(" O nome curto na anotação InfoPagina da Pagina:" + pClassePAgina.getName() + "  não foi configurado");
            }
            //TODO tratar duplicidade de TAGS e Existencia do recurso

            nomeCurto = (anotacaoInfopagina.nomeCurto());
            permitirSelecaoRegistroPorURL = anotacaoInfopagina.permitirSelecaoPorURL();
            acessoLivre = anotacaoInfopagina.acessoLivre();

            for (String tg : anotacaoInfopagina.tags()) {
                tagsPalavraChave.add(tg);
            }
        } catch (Exception e) {
            FabErro.PARA_TUDO.paraSistema("Erro configurando anotações de infoPagina da pagina" + pClassePAgina, e);
            throw new UnsupportedOperationException("Não  foi possível determinar as palavras chave para a classe" + pClassePAgina.getSimpleName());
        }

        acaoGestaoVinculada = (AcaoGestaoEntidade) UtilSBController.getAcaoByClasse(pClassePAgina);

        try {
            if (acaoGestaoVinculada == null) {
                throw new UnsupportedOperationException("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN" + pClassePAgina.toString());
            }
            urlPadrao = gerarURL(acaoGestaoVinculada);
            recursoXHTML = acaoGestaoVinculada.getXhtml();

            if (urlPadrao == null) {
                throw new UnsupportedOperationException("Impossível determinar a Url da pagina" + pClassePAgina.getSimpleName());
            }

        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN " + this.getClass().getSimpleName(), t);
            FabErro.PARA_TUDO.paraSistema("NÃO EXITE AÇÃO VINCULADA AO MANAGED_BEAN" + pClassePAgina.getSimpleName(), t);
            throw new UnsupportedOperationException("Não  foi possível determinar as palavras chave para a classe" + pClassePAgina.getSimpleName());
        }

        if (tagsPalavraChave == null) {
            throw new UnsupportedOperationException("Não  foi possível determinar as palavras chave para a classe" + pClassePAgina.getSimpleName());
        }

    }

    public AcaoGestaoEntidade getAcaoGestaoVinculada() {
        return acaoGestaoVinculada;
    }

    public List<ParametroURL> getParametrosURL() {
        return Lists.newArrayList(parametrosURL.values());
    }

    public int getQuantidadeParametrosObrigatorios() {
        return quantidadeParametrosObrigatorios;
    }

    public String getUrlPadrao() {
        return gerarUrlPorParametro(Lists.newArrayList(parametrosURL.values()), acaoGestaoVinculada, null);
    }

    private String gerarBaseURL(String tagUsada) {
        String url = SBWebPaginas.getURLBase();
        String tagURL = tagUsada;
        if (tagURL == null) {
            tagURL = tagsPalavraChave.get(0);
        }

        return url + "/" + UtilSBCoreStrings.makeStrUrlAmigavel(tagURL);
    }

    public String gerarUrlPorParametro(List<ParametroURL> parametros, ItfAcaoDoSistema pAcao, String tagUsada) {
        String url = gerarBaseURL(tagUsada);

        for (ParametroURL pr : parametros) {

            url += "/" + pr.getSlugValorPadrao();

        }

        String urlAcao = "";
        if (pAcao != null) {
            if (!pAcao.isUmaAcaoGestaoDominio()) {
                urlAcao = "/ac-" + UtilSBCoreStrings.makeStrUrlAmigavel(pAcao.getNomeAcao());
            }
        }

        return url + urlAcao + "/.wp";

    }

    public String gerarUrlPorValorParametro(ItfAcaoDoSistema pAcao, String tagUsada, Object... parametros) {

        Map<String, Object> mapaParametrosEnviados;

        mapaParametrosEnviados = new HashMap<>();
        if (parametros != null) {

            for (Object obj : parametros) {
                mapaParametrosEnviados.put(obj.getClass().getSimpleName(), obj);
            }

        }

        List<ParametroURL> novosParametros = new ArrayList<>();
        parametrosURL.values().stream().forEach((pr) -> {
            ParametroURL novoParametro = new ParametroURL(pr);
            String strTipoObjeto = pr.getClasseObjetoValor().getSimpleName();
            Object prEnviadoRelacionado = mapaParametrosEnviados.get(strTipoObjeto);

            if (prEnviadoRelacionado == null) {
                novosParametros.add(novoParametro);
            } else {
                novoParametro.setValor(prEnviadoRelacionado);
                novosParametros.add(novoParametro);
            }
        });

        return gerarUrlPorParametro(novosParametros, pAcao, tagUsada);

    }

    private String gerarURL(ItfAcaoDoSistema pAcao) {
        return gerarUrlPorParametro(Lists.newArrayList(parametrosURL.values()), pAcao, null);
    }

    public boolean isAcessoLivre() {
        return acessoLivre;
    }

    public String getNomeCurto() {
        return nomeCurto;
    }

    public boolean isPermitirSelecaoRegistroPorURL() {
        return permitirSelecaoRegistroPorURL;
    }

    public String getRecursoXHTML() {
        return recursoXHTML;
    }

    public List<String> getTagsPalavraChave() {
        return tagsPalavraChave;
    }

    public boolean isUmaPaginaCadastroEntidade() {
        return umaPaginaCadastroEntidade;
    }

}
