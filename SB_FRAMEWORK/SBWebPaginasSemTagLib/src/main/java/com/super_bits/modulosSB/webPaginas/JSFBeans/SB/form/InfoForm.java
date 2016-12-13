/* 
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90 

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.form;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @author Salvio
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoForm {

    String recurso();

    String textoAcao() default "Fechar";

}
