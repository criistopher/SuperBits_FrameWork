package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.ErroCritico.InfoErroCritico;
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
    private InfoErroCritico erroCriticoDoSistema;

    @Inject
    private ControleDeSessaoWeb controleDeSessao;

    protected abstract MB_SiteMapa getSiteMap();

    @PostConstruct
    public void startBean() {
        try {
            SBCore.soutInfoDebug("Iniciando pagina Atual");
            datahoraAbertura = new Date();
            if (infoPagina != null) {
                System.out.println("Pagina Atual infoPagina carregado");
            }
            if (infoPagina == null) {

                FacesContext contexto = FacesContext.getCurrentInstance();
                if (contexto == null) {
                    throw new UnsupportedOperationException("O contexto do XHTML não pode ser determinando em PGPaginaAtual");
                }

                String viewId = contexto.getViewRoot().getViewId();
                if (viewId == null) {
                    throw new UnsupportedOperationException("O XHTML principal do contexto atual não pode ser determinado");
                }

                try {
                    setInfoPagina(getSiteMap().getPaginaNoContexto(viewId));
                } catch (Throwable e) {
                    throw new UnsupportedOperationException("Não foi possível identificar a pagina vinculada ao xhtml:" + viewId);

                }
            }

            if (getInfoPagina() == null) {
                throw new UnsupportedOperationException("PAGINA ATUAL NÃO PODE SER DETERMINADA PELO URL DE SOLICITACAO", null);

            } else {
                infoPagina.abrePagina();
                conversa = infoPagina.getConversa();
                if (conversa == null) {
                    //    iniciaConvesa();
                }
                System.out.println("executou abre pagina pelo pagina Atual" + infoPagina.getTagUsada());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Instanciando Pagina atual", t);
            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {

                erroCriticoDoSistema.setBeanErroCritico(new InfoErroCritico("Erro criando bean Pagina Atual," + getSiteMap().getPaginasOffline(), t));
                UtilSBWP_JSFTools.vaParaPaginadeErro(t.getMessage());
            }

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
