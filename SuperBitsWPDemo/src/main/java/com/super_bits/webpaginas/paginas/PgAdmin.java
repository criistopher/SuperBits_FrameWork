/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.webpaginas.paginas;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Salvio
 */
@ViewScoped
@Named
@InfoPagina(nomeCurto = "PA", recurso = "/site/admin.xhtml", tags = {"Admin"})
public class PgAdmin extends MB_PaginaConversation {

    @Override
    protected String defineTitulo() {
        return "Administração de Paginas";
    }

    @Override
    protected String defineNomeLink() {
        return "Admin";
    }

    @Override
    protected String defineDescricao() {
        return "Pagina que visa demonstrar configurações do FrameWork";
    }

    @Override
    public int getId() {
        return 3;
    }

}
