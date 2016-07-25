/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabTipoVisualBotaoAcao implements ItfFabrica {

    /**
     * Exibe um pequeno botão com o icone da ação. ->Quando possível adicionar o
     * nome do botão ao passar o mouse
     */
    APENAS_ICONE,
    /**
     * Exibe o Icone alinhado a esquerda e o nome da ação no botão ->quando
     * possível adicionar a descrição ao passar o mouse
     */
    ICONE_NOME,
    /**
     * Exibe apenas o nome no botão -> quando possível exibe a descrição da ação
     * ao passar o mouse por cima
     */
    APENAS_NOME,
    /**
     * Exibe a descrição inteira da ação no botão, quando deve ser usado este
     * tipo de visualização? um dia talvez saberemos e esta visualização será
     * implementada...
     */
    APENAS_DESCRICAO,
    /**
     * Exibe um botão enorme, desses que só podem haver dois na tela, com o
     * icone alinhado a esquerda e nome
     */
    BOTAO_GIGANTE;

    @Override
    public Object getRegistro() {
        return this.toString();
    }

}
