/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.contantesView;

import com.super_bits.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.view.fabricasCompVisual.componentes.FabCompVIsualInputsLayout;

/**
 *
 * @author salvioF
 */
public class LayoutInputCampo {

    private final ComponenteVisualSB resumido = FabCompVIsualInputsLayout.LABEL_RESUMIDO.getComponente();
    private final ComponenteVisualSB esquerda = FabCompVIsualInputsLayout.LABEL_ESQUEDA.getComponente();
    private final ComponenteVisualSB superior = FabCompVIsualInputsLayout.LABEL_SUPERIOR.getComponente();
    private final ComponenteVisualSB automatico = FabCompVIsualInputsLayout.AUTOMATICO.getComponente();

    public ComponenteVisualSB getResumido() {
        return resumido;
    }

    public ComponenteVisualSB getEsquerda() {
        return esquerda;
    }

    public ComponenteVisualSB getSuperior() {
        return superior;
    }

    public ComponenteVisualSB getAutomatico() {
        return automatico;
    }

}
