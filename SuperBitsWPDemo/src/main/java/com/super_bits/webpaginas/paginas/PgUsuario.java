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
@InfoPagina(acessoLivre = false, nomeCurto = "PU", tags = {"Pagina Usuarios"}, recurso = "/site/usuario.xhtml")
public class PgUsuario extends MB_PaginaConversation {

    @Override
    protected String defineTitulo() {
        return "Pagina do usuario";
    }

    @Override
    protected String defineNomeLink() {
        return "usuarios";
    }

    @Override
    protected String defineDescricao() {
        return "Pagina  do usuário, demonstrando as funcionalidades de input";
    }

    @Override
    public int getId() {
        return 2;
    }

}
