package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

public abstract class MB_Pagina extends B_Pagina {

    protected Map<String, String> idsGerenciaveis = new HashMap<String, String>();
    private String urlAcessada;

    public MB_Pagina() {
        super();
    }

    @PostConstruct
    private void initBean() {
        System.out.println("Iniciando InitBeanDePagina" + this.getClass().getSimpleName());
        foiInjetado = true;
        configParametros();

    }

    @Override
    public void abrePagina() {
        super.abrePagina();

    }

    private Map<String, SBNQ> listas;

    public Map<String, SBNQ> getListas() {
        return listas;
    }

}
