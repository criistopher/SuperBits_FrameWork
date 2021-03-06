/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.contantesView;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualBotaoAcao;

/**
 *
 * @author salvioF
 */
public class LayoutBotoes {

    private final ComponenteVisualSB icone = FabCompVisualBotaoAcao.ICONE.getComponente();
    private final ComponenteVisualSB iconeENome = FabCompVisualBotaoAcao.ICONE_E_NOME.getComponente();
    private final ComponenteVisualSB nome = FabCompVisualBotaoAcao.NOME.getComponente();
    private final ComponenteVisualSB gigante = FabCompVisualBotaoAcao.ICONE_GIGANTE.getComponente();
    private final ComponenteVisualSB cardResponsivo = FabCompVisualBotaoAcao.BOTAO_CARD_RESPONSIVO.getComponente();

    public ComponenteVisualSB getIcone() {
        return icone;
    }

    public ComponenteVisualSB getIconeENome() {
        return iconeENome;
    }

    public ComponenteVisualSB getNome() {
        return nome;
    }

    public ComponenteVisualSB getGigante() {
        return gigante;
    }

    public ComponenteVisualSB getCardResponsivo() {
        return cardResponsivo;
    }

}
