/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.menu;

import java.util.List;

/**
 *
 * Implementa um menu com Ações do sistema e Sessões
 *
 *
 * @author desenvolvedor
 */
public interface ItfMenuSB {

    /**
     * Adiciona uma sessao ao menu
     *
     * @param pSessao Uma sessao com todas suas ações
     */
    public void addSessao(SessaoMenuSB pSessao);

    /**
     *
     * Retorna uma sessão com ações, e sub-sessoes
     *
     * @return
     */
    public List<SessaoMenuSB> getSessoes();

    public boolean isTemMenu();

}
