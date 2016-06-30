/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.application.exceptionhandler.ExceptionInfo;

/**
 *
 * Bean do tipo request Scoped para ajudar a exibir erros
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 15/01/2015
 * @version 1.0
 */
@RequestScoped
@Named
public class InfoErro implements Serializable {

    public static enum ORIGEM_ERRO_WEBPAGINA {
        SERVLET, PRIMEFACES, ERRO_CRITICO_FRAMEWORK, JSF_GENERICO, MULTIPLOS, ERRO_NAO_ENCONTRADO
    }

    private Throwable erroServlet;
    private ExceptionInfo erroPrimeFaces;

    @PostConstruct
    public void inicio() {
        buildErroServlet();
        buildErroPrimeFaces();
    }

    private void buildErroServlet() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
            for (String atributo : requestMap.keySet()) // Fetch the exception
            {
                System.out.println("Atributo:" + atributo + " " + requestMap.get(atributo).getClass().getSimpleName());
            }
            erroServlet = (Throwable) requestMap.get("javax.servlet.error.exception");
        } catch (Throwable t) {
            System.out.println("Impossível obter erro do tipo Servlet");
        }
    }

    private void buildErroPrimeFaces() {
        FacesContext context = FacesContext.getCurrentInstance();
        erroPrimeFaces = context.getApplication().evaluateExpressionGet(context, "#{pfExceptionHandler}", ExceptionInfo.class);
    }

    private ORIGEM_ERRO_WEBPAGINA getTipoOrigem() {

        boolean temErroServlet = erroServlet != null;
        boolean temErroPrime = erroServlet != null;

        if (temErroPrime & temErroServlet) {
            return ORIGEM_ERRO_WEBPAGINA.MULTIPLOS;
        }
        if (temErroPrime) {
            return ORIGEM_ERRO_WEBPAGINA.PRIMEFACES;
        }
        if (temErroServlet) {
            return ORIGEM_ERRO_WEBPAGINA.JSF_GENERICO;
        }

        return ORIGEM_ERRO_WEBPAGINA.ERRO_NAO_ENCONTRADO;

    }

    public Throwable getErro() {

        switch (getTipoOrigem()) {
            case SERVLET:

                break;
            case PRIMEFACES:

                break;
            case ERRO_CRITICO_FRAMEWORK:

                break;
            case JSF_GENERICO:

                break;
            case MULTIPLOS:

                break;
            case ERRO_NAO_ENCONTRADO:

                break;
            default:
                throw new AssertionError(getTipoOrigem().name());

        }

        return erroServlet;

    }

    public ExceptionInfo getErroPrimefaces() {
        return erroPrimeFaces;

    }

    public Throwable getErroServlet() {
        return erroServlet;
    }

    public ExceptionInfo getErroPrimeFaces() {
        return erroPrimeFaces;
    }

}
