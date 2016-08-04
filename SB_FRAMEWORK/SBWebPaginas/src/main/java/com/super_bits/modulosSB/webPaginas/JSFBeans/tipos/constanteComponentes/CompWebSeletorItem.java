/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.constanteComponentes;

import com.super_bits.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.view.fabricasCompVisual.FabTipoVisualSeletorItem;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
public class CompWebSeletorItem implements Serializable {

    public ComponenteVisualSB getPadrao() {
        return FabTipoVisualSeletorItem.COMBO.getComponente();
    }

    public ComponenteVisualSB getCombo() {
        return FabTipoVisualSeletorItem.COMBO.getComponente();
    }

    public ComponenteVisualSB getBotoesMenu() {
        return FabTipoVisualSeletorItem.BOTOES_MENU.getComponente();
    }
}
