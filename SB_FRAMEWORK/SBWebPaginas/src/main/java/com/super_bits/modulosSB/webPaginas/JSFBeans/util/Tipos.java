/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.TipoCampos;
import com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.TipoVisualBotao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.TipoVisualCampo;
import com.super_bits.modulosSB.webPaginas.JSFBeans.tipos.TipoVisualFormulario;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 *
 *
 *
 * @author sfurbino
 */
@ApplicationScoped
@Named
public class Tipos implements Serializable {

    @Inject
    private TipoCampos tipoCampo;
    @Inject
    private TipoVisualCampo tipoVisualCampo;
    @Inject
    private TipoVisualBotao tipoVisualBotao;

    @Inject
    private TipoVisualFormulario tipoVisualFormulario;

    public TipoCampos getTipoCampo() {
        return tipoCampo;
    }

    public TipoVisualCampo getTipoVisualCampo() {
        return tipoVisualCampo;
    }

    public TipoVisualBotao getTipoVisualBotao() {
        return tipoVisualBotao;
    }

    public TipoVisualFormulario getTipoVisualFormulario() {
        return tipoVisualFormulario;
    }

}
