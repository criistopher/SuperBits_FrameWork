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
public enum FabCompVisualBotaoAcao implements ItfFabTipoComponenteVisual {

    @InfoComponenteVisual(nome = "Icone",
            classesCSS = "btnIcone",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "btnIcone"
    )
    ICONE,
    @InfoComponenteVisual(nome = "Icone e Nome",
            classesCSS = "btnIconeENome",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "btnIconeENome")
    ICONE_E_NOME,
    @InfoComponenteVisual(nome = "Nome",
            classesCSS = "btnNome",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "btnNome"
    )
    NOME,
    @InfoComponenteVisual(nome = "Icone Gigante",
            classesCSS = "iconeGrande",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "btnIconeGrande")
    ICONE_GIGANTE,
    @InfoComponenteVisual(nome = "Botao Card Responsivo",
            classesCSS = "btnCardResoponsivo",
            xhtmlJSF = FabCompVisualBotaoAcao.PASTA_BOTOES + "btnCardResoponsivo")
    BOTAO_CARD_RESPONSIVO;
    public static final String PASTA_BOTOES = "botaoAcao/";

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.BOTAO_DE_ACAO;
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
