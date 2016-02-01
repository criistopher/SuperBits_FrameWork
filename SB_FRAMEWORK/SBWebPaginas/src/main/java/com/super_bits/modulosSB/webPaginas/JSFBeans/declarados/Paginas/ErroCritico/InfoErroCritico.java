package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.ErroCritico;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.UtilSBCoreErros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class InfoErroCritico implements Serializable {

    private String tituloErro;
    private String mensagemErro;
    private String printStack;

    public InfoErroCritico() {
    }

    public InfoErroCritico(String pTituloErro, Throwable pExcept) {
        tituloErro = UtilSBCoreStrings.makeStrUrlAmigavel(pTituloErro);
        mensagemErro = pExcept.getMessage();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pExcept.printStackTrace(pw);
        pw.close();

        printStack = "RESUMO: \n " + UtilSBCoreErros.getResumoErro(pExcept) + "/n caminho completo: /n" + sw.getBuffer().toString().replace("", "");

    }

    public String getTituloErro() {
        return tituloErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public String getPrintStack() {
        return printStack;
    }

}
