/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

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

    public Throwable getErro() {
        FacesContext context = FacesContext.getCurrentInstance();

        Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
        for (String atributo : requestMap.keySet()) // Fetch the exception
        {
            System.out.println("Atributo:" + atributo + " " + requestMap.get(atributo).getClass().getSimpleName());
        }
        return (Throwable) requestMap.get("javax.servlet.error.exception");

    }
}
