/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * w
 *
 *
 *
 * @author desenvolvedor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoTipoAcaoController {

    public String nomeAcao() default "";

    public String icone() default "";

    public Class entidade() default Object.class;

    public boolean precisaPermissao() default false;

    public String xhtmlDaAcao() default "";

    public String descricao() default "";

    public String codigoJira() default "";

}
