package com.super_bits.config.webPaginas;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_SiteMapa;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PaginaAtual extends MB_PaginaAtual {

    @Inject
    private SiteMap siteMapa;

    @Produces
    @Named(value = "testee2")
    public String getTeste() {
        return "colé doidin";
    }

    @Override
    protected MB_SiteMapa getSiteMap() {
        return siteMapa;
    }

}
