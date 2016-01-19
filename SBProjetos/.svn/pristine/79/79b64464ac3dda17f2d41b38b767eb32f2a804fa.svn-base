/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.config.webPaginas;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_SiteMapa;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Salvio
 */
@ApplicationScoped
public class SiteMap extends MB_SiteMapa {

 	/// Injete aqui todas as Paginas que extendem o objeto MB_pagina
    @Override
    protected Map<String, ItfB_Pagina> buildPaginas() {
        Map<String, ItfB_Pagina> paginasSimples = buildSystemPages();

        return paginasSimples;

    }

}
