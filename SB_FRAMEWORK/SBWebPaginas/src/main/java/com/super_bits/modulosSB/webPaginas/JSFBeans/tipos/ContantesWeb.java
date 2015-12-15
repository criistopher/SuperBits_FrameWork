/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

/**
 *
 * @author sfurbino
 */
public abstract class ContantesWeb implements ItfConstantesWeb {

    Object opcoes;

    public ContantesWeb(Class classe) {

        opcoes = classe.getEnumConstants();

        // TODO OBRIGAR REGISTRO DE TODAS AS OPÇÕES
    }

}
