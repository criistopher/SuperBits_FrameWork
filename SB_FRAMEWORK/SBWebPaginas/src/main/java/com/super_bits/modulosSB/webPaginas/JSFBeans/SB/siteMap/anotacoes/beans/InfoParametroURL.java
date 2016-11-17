/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroTela.TIPO_URL;
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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoParametroURL {

    String nome();

    String valorPadrao() default "";

    TIPO_URL tipoParametro();

    Class tipoEntidade() default Void.class;

    boolean obrigatorio() default true;

}
