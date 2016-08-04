/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

/**
 *
 * @author salvioF
 */
public interface ItfFabTipoComponenteVisual {

    public static final String JSF_COMPONENTE_NAO_IMPLEMENTADO = "sistema/visualizacaoNaoImplementada.xhtml";
    public static final String JSF_COMPONENTE_INCOMPATIVEL = "sistema/visualizacaoAlteranativaIncompativel";
    public static final String ANDROID_COMPONENTE_NAO_IMPLEMENTADO = "naoImplementado.xhtml";
    public static final String WORDPRESS_COMPONENTE_NAO_IMPLEMENTADO = "naoImplementado.xhtml";

    public static final String PASTA_TAG_LIBS = "/resources/SBComp/tagLib/tags/com/sb/";
    public static final String PASTA_TAG_LIB_ANDROID = "/resources/SBComp/tagLib/tags/com/sb/";
    public static final String PASTA_WORDPRESS = "/resources/SBComp/tagLib/tags/com/sb/";

    ;

    public FabFamiliaCompVisual getFamilia();

    public ComponenteVisualSB getComponente();

}
