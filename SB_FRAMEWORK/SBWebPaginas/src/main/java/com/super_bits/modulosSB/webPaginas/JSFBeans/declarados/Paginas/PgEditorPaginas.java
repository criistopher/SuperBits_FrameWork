/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;

/**
 *
 * @author Marcos Vinicius
 */
@InfoPagina(nomeCurto = "EP", tags = {"EditorDePaginas"}, recurso = "/resources/SBComp/SBSystemPages/editorPagina.xhtml", acessoLivre = false)
public class PgEditorPaginas extends MB_PaginaConversation {

    private String recurso;

    @Override
    protected String defineTitulo() {
        return "Super-Bits XHTML editor";
    }

    @Override
    protected String defineNomeLink() {
        return "Editor HTML";
    }

    @Override
    protected String defineDescricao() {
        return "Pagina, Edição HTML";
    }

    @Override
    public int getId() {
        return 0;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

}
