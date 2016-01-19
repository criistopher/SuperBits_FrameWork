/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.config.webPaginas;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_SiteMapa;
import com.super_bits.webpaginas.paginas.PgAdmin;
import com.super_bits.webpaginas.paginas.PgCadastroDesenvolvedor;
import com.super_bits.webpaginas.paginas.PgVisaoGeral;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Salvio
 */
@ApplicationScoped
public class SiteMapSBProject extends MB_SiteMapa implements Serializable {

    @Inject
    private PgVisaoGeral vg;
    @Inject
    private PgCadastroDesenvolvedor cadastro;
    @Inject
    private PgAdmin admin;

    @Override
    protected Map<String, ItfB_Pagina> buildPaginas() {
        Map<String, ItfB_Pagina> paginasSimples = buildSystemPages();
        return paginasSimples;

    }

}
