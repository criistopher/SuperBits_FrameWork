package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

public abstract class MB_PaginaConversation extends MB_Pagina implements Serializable {

    protected Map<String, String> idsGerenciaveis = new HashMap<String, String>();
    private String urlAcessada;

    @PostConstruct
    private void initBean() {
        System.out.println("Iniciando InitBeanDePagina Conversation" + this.getClass().getSimpleName());
        //     foiInjetado = true;
        //     carregarAnotacoes();
        iniciaConvesa();
    }

    @Override
    public void abrePagina() {
        super.abrePagina();

    }

    private Map<String, SBNQ> listas;

    public void iniciaConvesa() {
        try {
            //   if (!FacesContext.getCurrentInstance().isPostback() //                    && conversation.isTransient()) {
            //           //           conversation.begin();
            //       }
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro iniciando conversa de:" + this.getClass().getSimpleName(), e);

        }

    }

    public void terminaConvesa() {
        // if (conversation != null) {
        try {
            //           if (!getConversation().isTransient()) {
            //             conversation.end();
            // }
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Encerrando Conversation", e);
        }
        //   }
    }

    public MB_PaginaConversation() {
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

            terminaConvesa();
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro fechando pagina " + this.getClass().getSimpleName(), e);
        }
    }

}
