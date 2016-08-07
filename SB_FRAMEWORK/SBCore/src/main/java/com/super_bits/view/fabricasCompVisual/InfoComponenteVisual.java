/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.view.fabricasCompVisual.componentes.FabCompVisualSistema;
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

    public String nome() default "";

    public String descricao() default "";

    public String xhtmlJSF() default FabCompVisualSistema.JSF_COMPONENTE_NAO_IMPLEMENTADO;

    public String xhtmlAndroi() default FabCompVisualSistema.ANDROID_COMPONENTE_NAO_IMPLEMENTADO;

    public String htmlWordPress() default FabCompVisualSistema.WORDPRESS_COMPONENTE_NAO_IMPLEMENTADO;

    public String classesCSS() default "";

    public String caminhoIdHTMLObjetoPrincipal() default "";

}
