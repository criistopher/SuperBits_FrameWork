package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;

public abstract class MB_PaginaSession extends MB_Pagina {

    protected Map<String, String> idsGerenciaveis = new HashMap<String, String>();
    private String urlAcessada;

    @PostConstruct
    private void initBean() {
        System.out.println("Iniciando InitBeanDePagina Session Scoped" + this.getClass().getSimpleName());
        //   foiInjetado = true;
        //   carregarAnotacoes();

    }

    @Override
    public void abrePagina() {
        super.abrePagina();

    }

    private Map<String, SBNQ> listas;

    public MB_PaginaSession() {
        super();
    }

    public Map<String, SBNQ> getListas() {
        return listas;
    }

    @Override
    public void fecharPagina() {
        try {
            System.out.println("Executando predestroy de " + this.getClass().getSimpleName());
            super.fecharPagina();

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro fechando pagina " + this.getClass().getSimpleName(), e);
        }
    }

    @Override
    public Conversation getConversa() {
        return null;
    }

}
