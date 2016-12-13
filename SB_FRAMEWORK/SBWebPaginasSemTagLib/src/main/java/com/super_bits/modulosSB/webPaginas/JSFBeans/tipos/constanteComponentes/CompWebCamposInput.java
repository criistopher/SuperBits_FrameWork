/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.constanteComponentes;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
public class CompWebCamposInput implements Serializable {

    public ComponenteVisualSB padrao() {
        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getComponente();
    }

    public ComponenteVisualSB getTextoSemFormatacao() {
        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getComponente();
    }

}
