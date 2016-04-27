package com.super_bits.Controller.anotacoes;

import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InfoAcaoGestaoEntidade {

    public String nomeAcao();

    public String icone();

    public Class entidade();

    public boolean precisaPermissao();

    public String xhtmlDaAcao();

}
