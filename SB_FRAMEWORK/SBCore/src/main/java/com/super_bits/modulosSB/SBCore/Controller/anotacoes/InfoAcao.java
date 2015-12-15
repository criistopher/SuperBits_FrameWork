/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Anotação para açoes da camada de controle
 *
 * @author Salvio
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InfoAcao {

    public boolean padraoBloqueado() default false;

    @Deprecated
    public String nomeAmigavel();

    /**
     * ToString do Tipo da acao facture pelo nome
     *
     * @return
     */
    public String nomeAcaoFacture() default "NaoEspecificado";

}
