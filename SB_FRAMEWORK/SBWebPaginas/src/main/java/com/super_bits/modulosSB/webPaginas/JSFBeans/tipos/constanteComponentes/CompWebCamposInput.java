/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.constanteComponentes;

import com.super_bits.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.view.fabricasCompVisual.FabTipoVisualCampo;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
public class CompWebCamposInput implements Serializable {

    public ComponenteVisualSB padrao() {
        return FabTipoVisualCampo.TEXTO_SEM_FORMATACAO.getComponente();
    }

    public ComponenteVisualSB getTextoSemFormatacao() {
        return FabTipoVisualCampo.TEXTO_SEM_FORMATACAO.getComponente();
    }

}
