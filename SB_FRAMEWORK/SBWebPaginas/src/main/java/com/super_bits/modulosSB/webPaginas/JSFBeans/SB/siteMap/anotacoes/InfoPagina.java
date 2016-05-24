/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * //TODO renomear para InfoPaginaSiteMAP e retenção para fild, pois a anotação
 * será usada no siteMAP
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
     * Tags de acesso a Pagina
     *
     * @return tags de Aceso a Pagina
     */
    public String[] tags();

    /**
     *
     * O direito de acesso está configurado na ação vinculada à pagina
     *
     * @return
     * @deprecated
     */
    @Deprecated
    public boolean acessoLivre() default true;
}
