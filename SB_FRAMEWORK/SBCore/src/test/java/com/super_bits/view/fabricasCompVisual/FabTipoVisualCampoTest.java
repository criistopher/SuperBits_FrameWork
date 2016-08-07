/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class FabTipoVisualCampoTest {

    public FabTipoVisualCampoTest() {

    }

    @Test
    public void teste() {
        for (FabCompVisualInputs fabComponente : FabCompVisualInputs.class.getEnumConstants()) {
            ComponenteVisualSB cp = fabComponente.getComponente();
            System.out.println(cp.getId());
            System.out.println(cp.getNome());
            System.out.println(cp.getNomeComponente());
            System.out.println(cp.getXhtmlJSF());
            System.out.println(cp.getFamilia());
            System.out.println(cp.getHtmlWordPress());
            System.out.println(cp.getDescricao());
            System.out.println(cp.getParametros());
            System.out.println();

        }
    }

}
