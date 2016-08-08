/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import com.super_bits.view.fabricasCompVisual.componentes.FabCompVIsualInputsLayout;
import com.super_bits.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import com.super_bits.view.fabricasCompVisual.componentes.FabCompVisualItem;
import com.super_bits.view.fabricasCompVisual.componentes.FabCompVisualItens;
import com.super_bits.view.fabricasCompVisual.componentes.FabCompVisualMenu;
import com.super_bits.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import com.super_bits.view.fabricasCompVisual.componentes.FabCompVisualSistema;
import java.util.List;

/**
 *
 * @author salvioF
 */
public enum FabFamiliaCompVisual implements ItfFabFamiliaComponenteVisual, ItfFabrica {

    INPUT,
    LAYOUT_INPUT,
    MENU,
    SELETOR_ITEM,
    SELETOR_ITENS,
    ITEM_BEAN_SIMPLES,
    ITENS_BEAN_SIMPLES,
    COMPONENTE_SISTEMA,
    BOTAO_DE_ACAO;

    public String getXhtmlJSFPadrao() {

        return getComponentePadrao().getXhtmlJSF();
    }

    public ComponenteVisualSB getComponentePadrao() {
        switch (this) {
            case INPUT:
                return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getComponente();
            case MENU:
                return FabCompVisualMenu.MENU_SIMPLES_FONTANSOME.getComponente();
            case SELETOR_ITEM:
                return FabCompVisualSeletorItem.COMBO.getComponente();
            case SELETOR_ITENS:
                return FabCompVisualItens.LISTAGEM_EM_GRADE_NOME_E_IMAGEM.getComponente();
            case LAYOUT_INPUT:
                break;
            case ITEM_BEAN_SIMPLES:
                return FabCompVisualItem.NOME_E_IMAGEM.getComponente();

            case ITENS_BEAN_SIMPLES:
                return FabCompVisualItens.LISTAGEM_EM_GRADE_NOME_E_IMAGEM.getRegistro();
            case COMPONENTE_SISTEMA:
                return FabCompVisualSistema.NAO_IMPLEMENTADO.getRegistro();
            default:
                return FabCompVisualItem.NOME_E_IMAGEM.getComponente();

        }
        return null;
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

    @Override
    public FamiliaComponente getRegistro() {

        FamiliaComponente familia = new FamiliaComponente();
        familia.setId(this.ordinal());
        familia.setNome(this.getNomeFAmilia());
        familia.setFabrica(this);
        return familia;

    }

    @Override
    public Class getFabricaCamposPadrao() {
        switch (this) {
            case INPUT:
                return FabCompVisualInputs.class;
            case LAYOUT_INPUT:
                return FabCompVIsualInputsLayout.class;
            case MENU:
                return FabCompVisualMenu.class;
            case SELETOR_ITEM:
                return FabCompVisualItem.class;
            case SELETOR_ITENS:
                return FabCompVisualItens.class;
            case ITEM_BEAN_SIMPLES:
                return FabCompVisualItem.class;
            case ITENS_BEAN_SIMPLES:
                return FabCompVisualItens.class;
            default:
                throw new AssertionError(this.name());

        }
    }
}
