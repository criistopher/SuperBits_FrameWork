/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

/**
 *
 * @author salvioF
 */
public enum FabTipoVisualSeletorItem implements ItfFABTipoComponenteVisual {
    TIPO_VIEW_CAROUSEL,
    TIPO_VIEW_MENUBUTTON,
    TIPO_VIEW_AUTO_COMPLETE,
    TIPO_VIEW_GRADE,
    TIPO_VIEW_COMBO,
    TIPO_VIEW_RADIO;
    public static String DIRETORIO_SELETOR_ITEM = PASTA_TAG_LIBS + "seletorItem/";

    @Override
    public String getXhtmlJsf() {
        switch (this) {
            case TIPO_VIEW_CAROUSEL:
                return DIRETORIO_SELETOR_ITEM + "carrousel.xhtml";
            case TIPO_VIEW_MENUBUTTON:
                return DIRETORIO_SELETOR_ITEM + "meuBotoes.xhtml";
            case TIPO_VIEW_AUTO_COMPLETE:
                return DIRETORIO_SELETOR_ITEM + "autoCompletar.xhtml";
            case TIPO_VIEW_GRADE:
                return DIRETORIO_SELETOR_ITEM + "grade.xhtml";
            case TIPO_VIEW_COMBO:
                return DIRETORIO_SELETOR_ITEM + "combo.xhtml";
            case TIPO_VIEW_RADIO:
                return DIRETORIO_SELETOR_ITEM + "radio.xhtml";
            default:
                return DIRETORIO_SELETOR_ITEM + "combo.xhtml";

        }
    }

    @Override
    public String getXhtmlAndroid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHtmlWordPress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeComponente() {
        return this.toString();
    }

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.SELETOR_ITEM;
    }

}
