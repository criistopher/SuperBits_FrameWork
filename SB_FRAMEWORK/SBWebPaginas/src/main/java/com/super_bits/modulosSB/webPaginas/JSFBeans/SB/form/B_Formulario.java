/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.form;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Salvio
 */
public abstract class B_Formulario {

    @Inject
    private Conversation conversa;

    private String caminhoFormulario;

    private String textoAcao;

    protected abstract boolean acaoForm();

    @PostConstruct
    public void init() {
        InfoForm infoForm = this.getClass().getAnnotation(InfoForm.class);
        caminhoFormulario = infoForm.recurso();
        textoAcao = infoForm.textoAcao();
        iniciaConversa();

    }

    public void abrirFormulario() {

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("closable", false);
        options.put("contentHeight", 600);
        options.put("contentWidth", 900);

        Map<String, List<String>> pmView = new HashMap<>();
        List cid = new ArrayList();
        cid.add(conversa.getId());
        pmView.put("cid", cid);
        RequestContext.getCurrentInstance().openDialog(caminhoFormulario, options, pmView);
    }

    public void executarAcao() {
        try {
            if (acaoForm()) {
                fecharESair();
            }
        } catch (Exception e) {
            //FabMensagens.enviarMensagemSistema("Erro executando ação de formulário", FabMensagens.AVISO);

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro executando ação de formulário", e);
        }

    }

    protected void finalizaConversa() {
        // conversa.end();
    }

    protected void iniciaConversa() {
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversa.isTransient()) {
            conversa.begin();

        }
    }

    public void fecharESair() {
        //conversa.end();
        RequestContext.getCurrentInstance().closeDialog(caminhoFormulario);
    }

    public String getTextoAcao() {
        return textoAcao;
    }

}
