package com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoTipoAcaoFormulario {

    public String nomeAcao() default "";

    public String icone() default "";

    public Class entidade() default Object.class;

    public boolean precisaPermissao() default false;

    public String xhtmlDaAcao() default "";

    public String[] campos() default {};

    public String descricao() default "";

    public String codigoJira() default "";

}
