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
public enum FabCompVIsualInputsLayout implements ItfFabTipoComponenteVisual {

    @InfoComponenteVisual(nome = "Resumido",
            descricao = "Exibe o campo, exibindo o label como marca-dagua, e tooltip",
            xhtmlJSF = FabCompVIsualInputsLayout.PASTA_LAYOUT_INPUTS + "resumido.xhtml",
            classesCSS = "campoResumido"
    )
    LABEL_RESUMIDO,
    @InfoComponenteVisual(nome = "Superior",
            descricao = "Exibe o nome do campo (label) acima do campo",
            xhtmlJSF = FabCompVIsualInputsLayout.PASTA_LAYOUT_INPUTS + "superior.xhtml",
            classesCSS = "campoSuperior"
    )
    LABEL_SUPERIOR,
    @InfoComponenteVisual(nome = "Esquerda",
            descricao = "Exibe o nome do campo (label) em frente ao campo",
            xhtmlJSF = FabCompVIsualInputsLayout.PASTA_LAYOUT_INPUTS + "esquerda.xhtml",
            classesCSS = "campoEsquerda"
    )
    LABEL_ESQUEDA,
    @InfoComponenteVisual(nome = "Automático",
            descricao = "Exibe o campo com layout automático, em geral o automático utiliza o superior para desktop, e resumido para mobilie",
            xhtmlJSF = FabCompVIsualInputsLayout.PASTA_LAYOUT_INPUTS + "automatico.xhtml",
            classesCSS = "campoAutomatico"
    )
    AUTOMATICO;
    public static final String PASTA_LAYOUT_INPUTS = "input/layouts/";

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.LAYOUT_INPUT;
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
