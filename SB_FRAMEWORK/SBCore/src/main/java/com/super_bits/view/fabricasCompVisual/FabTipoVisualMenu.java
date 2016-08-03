/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

/**
 *
 *
 *
 * @author salvioF
 */
public enum FabTipoVisualMenu implements ItfFabTipoComponenteVisual {

    @InfoComponenteVisual(nome = "Menu básico icones FontAnwsome")
    MENU_SIMPLES_FONTANSOME;

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.MENU;
    }

    @Override
    public ComponenteVisualSB getComponente() {
        return UtilSBFabricaComponenteVisual.getComponenteVisual(this);
    }

}
