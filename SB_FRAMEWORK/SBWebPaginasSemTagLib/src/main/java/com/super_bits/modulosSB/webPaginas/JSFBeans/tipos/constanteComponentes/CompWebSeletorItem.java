/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.constanteComponentes;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
public class CompWebSeletorItem implements Serializable {

    public ComponenteVisualSB getPadrao() {
        return FabCompVisualSeletorItem.COMBO.getComponente();
    }

    public ComponenteVisualSB getCombo() {
        return FabCompVisualSeletorItem.COMBO.getComponente();
    }

    public ComponenteVisualSB getBotoesMenu() {
        return FabCompVisualSeletorItem.BOTOES_MENU.getComponente();
    }
}
