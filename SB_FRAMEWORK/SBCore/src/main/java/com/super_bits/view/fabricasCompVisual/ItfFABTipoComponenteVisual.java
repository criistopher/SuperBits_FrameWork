/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

/**
 *
 * @author salvioF
 */
public interface ItfFABTipoComponenteVisual {

    public static final String PASTA_INPUTS = "/resources/SBComp/tagLib/tags/com/sb/input/";
    public static final String PASTA_TAG_LIBS = "/resources/SBComp/tagLib/tags/com/sb";
    public static final String CAMPO_INPUT_SIMPLES = PASTA_INPUTS + "inputSimples.xhtml";

    public String getXhtmlJsf();

    public String getXhtmlAndroid();

    public String getHtmlWordPress();

    public String getNomeComponente();

    public FabFamiliaCompVisual getFamilia();

}
