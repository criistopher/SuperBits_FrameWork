/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author sfurbino
 */
@ApplicationScoped
public enum FabVisualizacaoCampo {

    resumido, labelEsquerda, labelSuperior;

    public static FabVisualizacaoCampo getLabelEsquerda() {
        return labelEsquerda;
    }

    public static FabVisualizacaoCampo getResumido() {
        return resumido;
    }

    public static FabVisualizacaoCampo getLabelSuperior() {
        return labelSuperior;
    }

}
