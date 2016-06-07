/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.testesFW;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import org.junit.Before;

/**
 *
 * @author sfurbino
 */
public abstract class TesteJunit extends org.junit.Assert {

    protected abstract void configAmbienteDesevolvimento();

    private static boolean inicializou = false;

    @Before
    public void initPadrao() {

        if (!inicializou) {
            configAmbienteDesevolvimento();
            System.out.println("INIT PADRAO");
        }

        inicializou = true;
    }

    protected void configApenasLog() {
        throw new UnsupportedOperationException("O config de logs Ainda não foi implementado");
    }

    protected void lancarErroJUnit(Throwable erro) {
        // TODO MELHORAR NULL POINT EXCEPTION
        FabErro.SOLICITAR_REPARO.paraDesenvolvedor("ERRO EM TESTE: \n" + erro.getMessage(), erro);
        throw new UnknownError("Aconteceu um erro na execução do teste" + erro.getMessage());

    }

}
