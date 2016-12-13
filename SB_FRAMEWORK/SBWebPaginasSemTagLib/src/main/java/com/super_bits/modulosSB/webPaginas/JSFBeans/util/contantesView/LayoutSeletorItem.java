/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;

/**
 *
 * @author salvioF
 */
public class LayoutSeletorItem {

    private final ComponenteVisualSB combo = FabCompVisualSeletorItem.COMBO.getComponente();
    private final ComponenteVisualSB grade = FabCompVisualSeletorItem.GRADE.getComponente();
    private final ComponenteVisualSB autocomplete = FabCompVisualSeletorItem.AUTO_COMPLETE.getComponente();
    private final ComponenteVisualSB radio = FabCompVisualSeletorItem.RADIO.getComponente();
    private final ComponenteVisualSB carrousel = FabCompVisualSeletorItem.CARROULSEL.getComponente();
    private final ComponenteVisualSB menu = FabCompVisualSeletorItem.BOTOES_MENU.getComponente();

    public ComponenteVisualSB getCombo() {
        return combo;
    }

    public ComponenteVisualSB getGrade() {
        return grade;
    }

    public ComponenteVisualSB getAutocomplete() {
        return autocomplete;
    }

    public ComponenteVisualSB getRadio() {
        return radio;
    }

    public ComponenteVisualSB getCarrousel() {
        return carrousel;
    }

    public ComponenteVisualSB getMenu() {
        return menu;
    }

}
