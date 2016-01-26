/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.testesFW;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * Classe para executar testes em ações do sistemas
 *
 * @author sfurbino
 */
public abstract class TesteAcoesDoSistema extends TesteJunit {

    private boolean validarAcoesNaoConfiguradas = false;

    public TesteAcoesDoSistema(boolean pValidarAcoesNaoCOnfiguradas) {
        validarAcoesNaoConfiguradas = pValidarAcoesNaoCOnfiguradas;
    }

    public void testesBasicosDeAcoes(Class pFabricaDeAcoes) {
        boolean proseguiuSemErro = true;

        for (Object obj : pFabricaDeAcoes.getEnumConstants()) {
            try {
                ItfAcaoDoSistema novaAcao = ((ItfFabricaAcoes) obj).getAcaoDoSistema();
                novaAcao.validarAcao(validarAcoesNaoConfiguradas);
            } catch (Throwable t) {
                proseguiuSemErro = false;
                lancarErroJUnit(t);
            }

            assertTrue("Aconteceu um erro na ação " + obj, proseguiuSemErro);

        }

    }

}
