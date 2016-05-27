package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@SuppressWarnings("serial")
public abstract class MB_PaginaAtual implements Serializable {

    private String nomePagina;
    private Date datahoraAbertura;
    private ItfB_Pagina infoPagina;
    private Conversation conversa;

    @Inject
    private ControleDeSessaoWeb controleDeSessao;

    protected abstract MB_SiteMapa getSiteMap();

    @PostConstruct
    public void startBean() {
        SBCore.soutInfoDebug("Iniciando pagina Atual");
        datahoraAbertura = new Date();
        if (infoPagina != null) {
            System.out.println("Pagina Atual infoPagina carregado");
        }
        if (infoPagina == null) {
            SBCore.soutInfoDebug("PaginaAtual sem  infoPagina Carregado, obtendo infoPagina por Recurso");
            String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            SBCore.soutInfoDebug("Recurso encontrado:" + viewId);
            try {
                setInfoPagina(getSiteMap().getPaginaNoContexto(viewId));
            } catch (Throwable e) {
                FabErro.PARA_TUDO.paraSistema("Erro Obtendo pagina Atual por viewID, verifique a declaração no sitemap e anotações View:" + viewId, e);
            }
        }

        if (getInfoPagina() == null) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("PAGINA ATUAL NÃO PODE SER DETERMINADA PELO URL DE SOLICITACAO", null);
            UtilSBWP_JSFTools.vaParaPaginadeErro("impossível determinar a Pagina atual pela URL");
        } else {
            infoPagina.abrePagina();
            conversa = infoPagina.getConversa();
            if (conversa == null) {
                //    iniciaConvesa();
            }
            System.out.println("executou abre pagina pelo pagina Atual" + infoPagina.getTagUsada());
        }

    }

    public void iniciaConvesa() {
        try {
            FacesContext contexto = FacesContext.getCurrentInstance();

            if (contexto == null) {
                if (conversa == null) {
                    conversa.begin();
                }
            } else if (!contexto.isPostback() && conversa.isTransient()) {
                conversa.begin();
            }

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro iniciando conversa de:" + this.getClass().getSimpleName(), e);
        }

    }

    public String getpLocalidade() {

        return UtilSBWPServletTools.getRequestParametro("pLocalidade").toString();
    }

    public void fechaPagina() {
        getInfoPagina().fecharPagina();
    }

    public void validaUrl() {

        System.out.println("TTTTTTEEESSSTTEEE:" + nomePagina);

    }

    public ItfB_Pagina getInfoPagina() {
        return infoPagina;
    }

    public void setInfoPagina(ItfB_Pagina infoPagina) {
        this.infoPagina = infoPagina;
    }

    public void mudarDePaginaPorEntidade(ActionEvent event) {
        String pPagina = UtilSBWPServletTools.getRequestParametro("pPagina");
        ItfBeanSimples pEntidade = UtilSBWPServletTools.getActionBeanSimples(event, "registro");

        // ItfB_Pagina novaPg = getSiteMap().getPaginaByTagOuNome(pPagina);
        //   novaPg.getParametrobyTipoEntidade(pEntidade.getClass().getSimpleName()).setValor(pEntidade.getNomeCurto());
        fechaPagina();
        //  UtilSBWP_JSFTools.vaParaPagina(novaPg.getUrlPadrao());
        throw new UnsupportedOperationException("Ainda não implementado, mas estamos quase lá, devido ao mapa de ações do sistema");
    }

    public void mudarDePagina() {

        fechaPagina();
    }

    public ItfSessao getSessao() {
        return controleDeSessao.getSessaoAtual();
    }

    public ItfControleDeSessao getControleDeSessao() {
        return controleDeSessao;
    }

    public String getEnderecoSite() {
        return SBWebPaginas.getSiteURL();
    }

    public String getNomePagina() {
        return nomePagina;
    }

    public void setNomePagina(String nomePagina) {
        this.nomePagina = nomePagina;
    }

    public Date getDatahoraAbertura() {
        return datahoraAbertura;
    }

    public void setDatahoraAbertura(Date datahoraAbertura) {
        this.datahoraAbertura = datahoraAbertura;
    }

}
