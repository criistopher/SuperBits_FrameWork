/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.constanteComponentes;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author salvioF
 */
@ApplicationScoped
public class CompsWeb implements Serializable {

    @Inject
    private CompWebCamposInput camposComponentes;
    @Inject
    private CompWebSeletorItem seletorItemComponentes;

    public CompWebCamposInput getCamposComponentes() {
        return camposComponentes;
    }

    public CompWebSeletorItem getSeletorItemComponentes() {
        return seletorItemComponentes;
    }

}
