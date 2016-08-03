/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

/**
 *
 * @author salvioF
 */
public enum FabTipoVisualSeletorItem implements ItfFabTipoComponenteVisual {
    @InfoComponenteVisual(nome = "Carrousel", xhtmlJSF = "carrousel.xhmlt")
    CARROULSEL,
    @InfoComponenteVisual(nome = "Menu em Botões", xhtmlJSF = FabTipoVisualSeletorItem.DIRETORIO_SELETOR_ITEM + "meuBotoes.xhmlt")
    BOTOES_MENU,
    @InfoComponenteVisual(nome = "Auto-Complete", xhtmlJSF = FabTipoVisualSeletorItem.DIRETORIO_SELETOR_ITEM + "autoCompletar.xhmlt")
    AUTO_COMPLETE,
    @InfoComponenteVisual(nome = "Grade", xhtmlJSF = FabTipoVisualSeletorItem.DIRETORIO_SELETOR_ITEM + "grade.xhmlt")
    GRADE,
    @InfoComponenteVisual(nome = "Combo", xhtmlJSF = FabTipoVisualSeletorItem.DIRETORIO_SELETOR_ITEM + "combo.xhmlt")
    COMBO,
    @InfoComponenteVisual(nome = "radio", xhtmlJSF = FabTipoVisualSeletorItem.DIRETORIO_SELETOR_ITEM + "radio.xhmlt")
    RADIO;

    public static final String DIRETORIO_SELETOR_ITEM = "seletorItem/";

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.SELETOR_ITEM;
    }

    @Override
    public ComponenteVisualSB getComponente() {
        return UtilSBFabricaComponenteVisual.getComponenteVisual(this);
    }

}
