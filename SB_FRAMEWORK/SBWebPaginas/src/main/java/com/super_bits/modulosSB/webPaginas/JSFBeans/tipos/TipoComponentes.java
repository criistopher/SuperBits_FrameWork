/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.constanteComponentes.CompWebCamposInput;
import com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.constanteComponentes.CompWebSeletorItem;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
public class TipoComponentes {

    @Inject
    private CompWebCamposInput inputs;
    @Inject
    private CompWebSeletorItem seletorItem;

    public CompWebCamposInput getInputs() {
        return inputs;
    }

    public CompWebSeletorItem getSeletorItem() {
        return seletorItem;
    }

}
