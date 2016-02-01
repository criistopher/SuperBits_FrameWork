/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 *
 *
 * @author sfurbino
 */
@ApplicationScoped
public class TipoVisualCampo extends ConstantesWeb {

    @Override
    public String getPadrao() {
        FabVisualizacaoCampo tipos = FabVisualizacaoCampo.resumido;
        switch (tipos) {
            case resumido:
                break;
            case labelEsquerda:
                break;
            default:
                throw new AssertionError(tipos.name());

        }
        return tipos.toString();
    }

    public TipoVisualCampo() {
        super(FabVisualizacaoCampo.class);
    }

    public String getResumido() {
        return FabVisualizacaoCampo.resumido.toString();
    }

    public String getLabelEsquerda() {
        return FabVisualizacaoCampo.labelEsquerda.toString();
    }

    public String getLabelSuperior() {
        return FabVisualizacaoCampo.labelSuperior.toString();
    }

}
