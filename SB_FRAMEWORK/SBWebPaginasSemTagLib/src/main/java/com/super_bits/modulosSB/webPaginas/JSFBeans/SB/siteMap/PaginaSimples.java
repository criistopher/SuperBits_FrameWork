package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;

@Deprecated
public class PaginaSimples extends B_Pagina {

    public PaginaSimples(String pNomeCurto, String pRecurso, String[] pTags) {

    }

    @Override
    public void abrePagina() {
        super.abrePagina();

    }

    @Override
    public void fecharPagina() {
        super.fecharPagina();

    }

    @Override
    public String defineTitulo() {
        return SBWebPaginas.getTituloApp();
    }

    @Override
    public String defineNomeLink() {
        return "nomelink";
    }

    @Override
    public String defineDescricao() {
        return "Descricao";
    }

    @Override
    public int getId() {
        return 99;
    }

}
