package com.super_bits.config.webPaginas;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_SiteMapa;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PaginaAtual extends MB_PaginaAtual implements ItfPaginaAtual {

    @Inject
    private SiteMap siteMapa;

    @Override
    public MB_SiteMapa getSiteMap() {
        return siteMapa;
    }

}
