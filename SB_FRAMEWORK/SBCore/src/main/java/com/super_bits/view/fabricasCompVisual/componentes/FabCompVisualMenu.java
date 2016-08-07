/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual.componentes;

import com.super_bits.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.view.fabricasCompVisual.FabFamiliaCompVisual;
import com.super_bits.view.fabricasCompVisual.InfoComponenteVisual;
import com.super_bits.view.fabricasCompVisual.ItfFabTipoComponenteVisual;
import com.super_bits.view.fabricasCompVisual.UtilSBFabricaComponenteVisual;

/**
 *
 *
 *
 * @author salvioF
 */
public enum FabCompVisualMenu implements ItfFabTipoComponenteVisual {

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

    @Override
    public ComponenteVisualSB getRegistro() {
        return getComponente();
    }

}
