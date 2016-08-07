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
 * @author salvioF
 */
public enum FabCompVisualItem implements ItfFabTipoComponenteVisual {
    @InfoComponenteVisual(nome = "Texto Com Formatacao",
            xhtmlJSF = FabCompVisualItem.PATA_ITEM + "nomeEImagem.xhtml")
    NOME_E_IMAGEM;
    public static final String PATA_ITEM = "item/";

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.ITEM_BEAN_SIMPLES;
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
