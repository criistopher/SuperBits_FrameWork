/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.controller.servletes.WebPaginasServlet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
@Named
public class InfoWebApp implements Serializable {

    /**
     *
     * @param pAcao
     * @return Ação managed Ben contendo a URL para acesso
     */
    public AcaoComLink getAcaoComLink(ItfAcaoDoSistema pAcao) {

        for (String chave : WebPaginasServlet.MAPA_ACOESMANAGED_BEAN.keySet()) {
            System.out.println(chave);
        }
        if (pAcao.isUmaAcaoGestaoDominio()) {
            //     System.out.println("ação de dominio, retornando url para" + pAcao.getNomeUnico());
            return WebPaginasServlet.MAPA_ACOESMANAGED_BEAN.get(pAcao.getNomeUnico());
        } else {
            //   System.out.println("ação secundaria, retornando url para" + pAcao.getComoSecundaria().getAcaoPrincipal().getNomeUnico());
            return WebPaginasServlet.MAPA_ACOESMANAGED_BEAN.get(pAcao.getComoSecundaria().getAcaoPrincipal().getNomeUnico());
        }
    }

    public AcaoComLink getAcaoNoCotexto(ItfAcaoDoSistema pAcao) {
        return getAcaoComLink(pAcao);
    }

    public boolean isAceosMBConfiguradas() {
        return !WebPaginasServlet.MAPA_ACOESMANAGED_BEAN.isEmpty();
    }

    public List<AcaoComLink> getAcoesMB() {
        List<AcaoComLink> acoes = new ArrayList<>();
        for (AcaoComLink acao : WebPaginasServlet.MAPA_ACOESMANAGED_BEAN.values()) {
            acoes.add(acao);
        }
        return acoes;
    }

    public String getUrlPagina() {
        return SBWebPaginas.getSiteURL();
    }

}
