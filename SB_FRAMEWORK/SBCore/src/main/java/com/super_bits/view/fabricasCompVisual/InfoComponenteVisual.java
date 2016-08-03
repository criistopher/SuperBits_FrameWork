/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author desenvolvedor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoComponenteVisual {

    String nome() default "";

    String descricao() default "";

    String xhtmlJSF() default ItfFabTipoComponenteVisual.JSF_COMPONENTE_NAO_IMPLEMENTADO;

    String xhtmlAndroi() default ItfFabTipoComponenteVisual.ANDROID_COMPONENTE_NAO_IMPLEMENTADO;

    String htmlWordPress() default ItfFabTipoComponenteVisual.WORDPRESS_COMPONENTE_NAO_IMPLEMENTADO;

    String caminhoIdHTMLObjetoPrincipal() default "";

}
