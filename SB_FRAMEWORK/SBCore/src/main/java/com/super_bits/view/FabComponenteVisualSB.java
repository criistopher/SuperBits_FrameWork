/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view;

import com.super_bits.view.fabricasCompVisual.InfoComponenteVisual;

/**
 *
 * @author desenvolvedor
 */
public enum FabComponenteVisualSB {

    @InfoComponenteVisual(nome = "Input", descricao = "Campo para input de Inforação", xhtmlJSF = "input.xhml")
    INPUT,
    @InfoComponenteVisual(nome = "Endereço", descricao = "Campo para input de Inforação", xhtmlJSF = "input.xhml")
    ENDERECO,
    @InfoComponenteVisual(nome = "Grafico", descricao = "Campo para input de Inforação", xhtmlJSF = "input.xhml")
    GRAFICO,
    @InfoComponenteVisual(nome = "Painal", descricao = "Campo para input de Inforação", xhtmlJSF = "enderecp.xhml")
    PAINEL,
    @InfoComponenteVisual(nome = "Menu", descricao = "Campo para input de Inforação", xhtmlJSF = "menu.xhml")
    MENU,
    @InfoComponenteVisual(nome = "Overlay ", descricao = "Componente para exibição em cima de outra tela", xhtmlJSF = "overlay.xhtml")
    OVERLAY;

}
