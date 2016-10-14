/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.AcaoComLink;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaSession;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author salvioF
 */
@SessionScoped
@Named
public class PgMapaDoSite extends MB_PaginaSession {

    @Inject
    private InfoWebApp infoWeb;
    private List<AcaoComLink> managedBeans;

    @PostConstruct
    public void inicio() {
        managedBeans = infoWeb.getAcoesMB();
    }

}
