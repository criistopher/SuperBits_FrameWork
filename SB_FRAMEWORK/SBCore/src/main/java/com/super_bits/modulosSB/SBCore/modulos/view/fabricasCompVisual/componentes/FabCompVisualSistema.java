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
 * @author salvioF
 */
public enum FabCompVisualSistema implements ItfFabTipoComponenteVisual {
    @InfoComponenteVisual(nome = "Componente não Implementado",
            xhtmlJSF = FabCompVisualSistema.JSF_COMPONENTE_NAO_IMPLEMENTADO,
            htmlWordPress = FabCompVisualSistema.WORDPRESS_COMPONENTE_NAO_IMPLEMENTADO,
            xhtmlAndroi = FabCompVisualSistema.ANDROID_COMPONENTE_NAO_IMPLEMENTADO,
            classesCSS = "componenteNaoImplementado"
    )
    NAO_IMPLEMENTADO,
    @InfoComponenteVisual(
            xhtmlJSF = FabCompVisualSistema.JSF_COMPONENTE_INCOMPATIVEL,
            htmlWordPress = FabCompVisualSistema.WORDPRESS_COMPONENTE_NAO_IMPLEMENTADO,
            xhtmlAndroi = FabCompVisualSistema.ANDROID_COMPONENTE_NAO_IMPLEMENTADO,
            classesCSS = "componenteIncompativel"
    )
    INCOMPATIVEL;
    public static final String JSF_COMPONENTE_NAO_IMPLEMENTADO = "sistema/componenteIncompativel.xhtml";
    public static final String JSF_COMPONENTE_INCOMPATIVEL = "sistema/componenteIncompativel.xhtml";
    public static final String ANDROID_COMPONENTE_NAO_IMPLEMENTADO = "sitema/naoImplementado.xhtml";
    public static final String ANDROID_COMPONENTE_INCOMPATIVEL = "sitema/naoImplementado.xhtml";
    public static final String WORDPRESS_COMPONENTE_NAO_IMPLEMENTADO = "sistema/naoImplementado.html";
    public static final String WORDPRESS_COMPONENTE_INCOMPATIVEL = "sistema/componenteIncompativel.xhtml";

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.COMPONENTE_SISTEMA;
    }

    @Override
    public ComponenteVisualSB getComponente() {
        return UtilSBFabricaComponenteVisual.getComponenteVisual(this);
    }

    @Override
    public ComponenteVisualSB getRegistro() {
        return UtilSBFabricaComponenteVisual.getComponenteVisual(this);
    }

}
