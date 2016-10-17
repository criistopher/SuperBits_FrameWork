/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.FabFamiliaCompVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.InfoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfFabTipoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.UtilSBFabricaComponenteVisual;

/**
 *
 *
 *
 * @author salvioF
 */
public enum FabCompVisualMenu implements ItfFabTipoComponenteVisual {

    @InfoComponenteVisual(nome = "Menu básico icones FontAnwsome",
            classesCSS = "menuSimples"
    )
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
