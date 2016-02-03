/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.view;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Salvio
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoPagina {

    /**
     * Atalho de acesso a Pagina
     *
     * @return Siglas de atalho para aceso a Pagina
     */
    public String nomeCurto();

    /**
     * Caminho do recurso xhtml
     *
     * @return Caminho do recurso xhtml
     */
    public String recurso();

    /**
     * Tags de acesso a Pagina
     *
     * @return tags de Aceso a Pagina
     */
    public String[] tags();

    public boolean acessoLivre() default true;
}
