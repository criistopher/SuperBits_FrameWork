package com.super_bits.modulosSB.webPaginas.JSFBeans.SB;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.ClasseTipada;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.MensagensJSF;
import java.lang.reflect.ParameterizedType;
import org.primefaces.context.RequestContext;

public abstract class B_ItemGenerico<T> extends ClasseTipada {

    private static final String DEIXAR_CX_DIALOGO_ABERTA = "KEEP_DIALOG_OPENED";
    protected Class<T> tipo;
    protected T registroAtual;
    protected String XHTML;

    public Class<T> getTipoEscolhido() {
        // Funcao que determina que tipo de classe Foi Setada

        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) superclass.getActualTypeArguments()[0];
    }

    public B_ItemGenerico() {
        super();

    }

    public B_ItemGenerico(Class<?> pClasse) {
        super(pClasse);
        //setTipo(getTipoEscolhido());

    }

    public void reset() {
        try {

            setRegistroAtual((T) getTipo().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {

            System.out.println("Erro criando Objeto MB_CRUD");
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro executando Reset de item generico" + XHTML, e
            );
        }
    }

    public void setRegistroAtual(T pEntidade) {
        this.registroAtual = pEntidade;
    }

    public T getRegistroAtual() {
        if (registroAtual == null) {
            reset();
        }
        return registroAtual;
    }

    public String getXHTML() {
        return XHTML;
    }

    protected void mostrarErro(String message) {

        MensagensJSF.erroMensagem(message);
    }

    protected void mostrarAviso(String pMensagem) {
        MensagensJSF.infoMensagem(pMensagem);

    }

    protected void fecharCaixaDeDialogo() {
        getRequestContext().addCallbackParam(DEIXAR_CX_DIALOGO_ABERTA, false);
    }

    protected void manterCaixaDeDialogo() {
        getRequestContext().addCallbackParam(DEIXAR_CX_DIALOGO_ABERTA, true);
    }

    protected RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }

    protected void setTipo(Class<T> tipo) {
        this.tipo = tipo;
    }

}
