/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual.componentes;

import com.super_bits.view.fabricasCompVisual.FamiliaComponente;
import com.super_bits.view.fabricasCompVisual.InfoComponenteVisual;
import com.super_bits.view.fabricasCompVisual.ItfFabFamiliaComponenteVisual;

/**
 *
 * @author salvioF
 */
public enum FabCompVisualBotaoAcao implements ItfFabFamiliaComponenteVisual {

    @InfoComponenteVisual(nome = "Link",
            classesCSS = "btnLinkSB",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "/btnLinkSB.xhtml"
    )
    LINK,
    @InfoComponenteVisual(nome = "Icone",
            classesCSS = "btnIcone",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "/btnIcone"
    )
    ICONE,
    @InfoComponenteVisual(nome = "Icone e Nome",
            classesCSS = "btnIconeENome",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "/btnIconeENome")
    ICONE_E_NOME,
    @InfoComponenteVisual(nome = "Nome",
            classesCSS = "btnNome",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "/btnNome"
    )
    NOME,
    @InfoComponenteVisual(nome = "Icone Gigante",
            classesCSS = "icone Gigante",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "/btnIconeGrande.xhtml")
    ICONE_GIGANTE;
    public static final String PASTA_BOTOES = "botaoAcao/";

    @Override
    public Class getInterfaceCompativel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeFAmilia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class getFabricaCamposPadrao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FamiliaComponente getRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
