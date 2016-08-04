/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.List;

/**
 *
 * @author salvioF
 */
public enum FabFamiliaCompVisual implements ItfFabFamiliaComponenteVisual {

    INPUT,
    MENU,
    SELETOR_ITEM,
    SELETOR_ITENS,
    ITEM_BEAN_SIMPLES,
    ITENS_BEAN_SIMPLES;

    public String getXhtmlJSFPadrao() {

        return getComponentePadrao().getXhtmlJSF();
    }

    public ComponenteVisualSB getComponentePadrao() {
        switch (this) {
            case INPUT:
                return FabTipoVisualCampo.TEXTO_SEM_FORMATACAO.getComponente();
            case MENU:
                return FabTipoVisualMenu.MENU_SIMPLES_FONTANSOME.getComponente();
            case SELETOR_ITEM:
                return FabTipoVisualSeletorItem.COMBO.getComponente();
            case SELETOR_ITENS:
                return FabTipoVisualItens.PICKLIST.getComponente();
            default:
                throw new AssertionError(this.name());

        }
    }

    @Override
    public Class getInterfaceCompativel() {

        switch (this) {
            case INPUT:
                return ItfCampoInstanciado.class;
            case MENU:
                return ItfCampoInstanciado.class;
            case SELETOR_ITEM:
                return ItfCampoInstanciado.class;
            case SELETOR_ITENS:
                return ItfCampoInstanciado.class;
            case ITEM_BEAN_SIMPLES:
                return ItfBeanSimples.class;
            case ITENS_BEAN_SIMPLES:
                return List.class;
            default:
                return ItfCampoInstanciado.class;

        }

    }

    @Override
    public String getNomeFAmilia() {
        return this.toString();
    }
}
