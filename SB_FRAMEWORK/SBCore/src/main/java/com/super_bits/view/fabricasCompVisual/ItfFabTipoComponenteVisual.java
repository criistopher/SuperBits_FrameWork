/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;

/**
 *
 * @author salvioF
 */
public interface ItfFabTipoComponenteVisual extends ItfFabrica {

    public static final String PASTA_TAG_LIBS = "resources/tagLib/tags/com/sb/";
    public static final String PASTA_TAG_LIB_ANDROID = "resources/tagLib/tags/com/sb/";
    public static final String PASTA_WORDPRESS = "resources/tagLib/tags/com/sb/";

    ;

    public FabFamiliaCompVisual getFamilia();

    public ComponenteVisualSB getComponente();

    @Override
    public ComponenteVisualSB getRegistro();

}
