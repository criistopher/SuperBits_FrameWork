package com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoTipoAcaoGestaoEntidade {

    public String nomeAcao() default "";

    public String icone() default "";

    public Class entidade() default Object.class;

    public boolean precisaPermissao() default false;

    public String xhtmlDaAcao() default "";

    public String descricao() default "";

    public String codigoJira() default "";

    public FabIconeFontAwesome iconeFonteAnsowame() default FabIconeFontAwesome.ICONE_PERSONALIZADO;

}
