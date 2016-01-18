package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public abstract class MensagensJSF {

    private static void mensagemEmSout(FacesMessage pFaceMsg) {
        System.out.println("FACECONTEXTO NÃO PODE SER OBTIDO, MENSAGEM DE USUÁRIO ENVIADA EM SYSTE.OUT");
        System.out.println(pFaceMsg.getDetail() + pFaceMsg.getSummary() + pFaceMsg.getSeverity());
    }

    public static void infoMensagem(String message) {

        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
        if (addMessageToJsfContext(facesMessage)) {
            UtilSBWP_JSFTools.atualizaPorId("mensagemUsuario");
        }
    }

    public static void alertaMensagem(String message) {

        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN, message);
        if (addMessageToJsfContext(facesMessage)) {
            UtilSBWP_JSFTools.atualizaPorId("mensagemUsuario");
        }
    }

    public static void erroMensagem(String message) {

        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_ERROR, message);
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        if (addMessageToJsfContext(facesMessage)) {
            UtilSBWP_JSFTools.atualizaPorId("mensagemUsuario");
        }
    }

    private static FacesMessage createMessage(Severity severity, String mensagemErro) {
        return new FacesMessage(severity, mensagemErro, mensagemErro);
    }

    private static boolean addMessageToJsfContext(FacesMessage facesMessage) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        if (contexto == null) {
            mensagemEmSout(facesMessage);
            return false;
        }

        contexto.addMessage(null, facesMessage);
        return true;

    }
}
