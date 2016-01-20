package com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoCampo {

    // TIPO CAMPO
    FabCampos tipo() default FabCampos.TEXTO_SIMPLES;

    String label() default "";

    String Mask() default "";

    String valorPadrao() default "";

    boolean obrigatorio() default true;

    int acesso() default 1;
    
    int valorMinimo() default 0;
    
    int valorMaximo() default 0;

    ValorAceito[] valoresAceitos() default {};

}
