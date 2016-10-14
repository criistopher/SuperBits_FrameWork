/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.componenteObjeto;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.UtilSBCoreReflexaoObjetoSuperBits;

/**
 *
 * @author salvioF
 */
public class ComponenteVisualObjetoSB {

    private String xhtml;
    private Class objeto;

    public ComponenteVisualObjetoSB(Class classeObjeto) {
        xhtml = SBCore.getCentralVisualizacao().getCaminhoXhtmlItem(objeto);
    }

    public String getXhtml() {
        return xhtml;
    }

    public void setXhtml(String xhtml) {
        this.xhtml = xhtml;
    }

    public Class getObjeto() {
        return objeto;
    }

    public void setObjeto(Class objeto) {
        this.objeto = objeto;
    }

}
