/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

/**
 *
 * @author sfurbino
 */
public enum FabErro {

    /**
     *
     * Lança uma Exceção para tratamento
     *
     */
    LANCAR_EXCECÃO,
    /**
     *
     *
     * UMA PANE NO SISTEMA DEVE EXIBIR UM AVISO DE MANEIRA VEEMENTE, ONDE A
     * ÚNICA INFORMAÇÃO DISPONÍVEL SEJA ESTE ERRO ACONTECIDO
     *
     */
    PANE_NO_SISTEMA,
    /**
     *
     * Faz um Log Apenas
     *
     */
    ARQUIVAR_LOG,
    /**
     *
     *
     * SOLICITA REPARO AO RESPONSÁVEL
     *
     */
    SOLICITAR_REPARO,
    /**
     * Paraliza a execução do sistema (Utilize com muita cautela, apenas onde
     * pré-configurações importantes do núcleo não tenham sido definidas)
     */
    PARA_TUDO;

    /*
     *
     * EXIBE A MENSAGEM DE ERRO PARA O USUÁRIO, NÃO GRAVA LOG, PODE OU NÃO
     * MANIFESTAR NO MODO EM DESENVOLVIMENTO
     */
    public void paraUsuario(String pTexto, Throwable pErro) {
        if (this == PARA_TUDO) {
            throw new UnsupportedOperationException("O erro do tipo Para tudo deve ser direcionado ao Sistema");
        }

        SBCore.RelatarErro(this, pTexto, pErro);
    }

    /**
     * APLICAÇÃO RECOMENDADA: EXECUTA UM SYSTEM.EXIT()
     *
     * @param pTexto TExto explicativo
     * @param pErro Objeto Throable que jerou a situação
     */
    public void paraSistema(String pTexto, Throwable pErro) {
        SBCore.RelatarErro(this, pTexto, pErro);
    }

    /**
     * APLICAÇÃO RECOMENDADA: NÃO GERA LOG, E SÓ MANIFESTA NO MODO
     * DESENVOLVIMENTO
     *
     * @param pTexto Texto que gerou o disparo
     * @param pErro Erro gerado
     */
    public void paraDesenvolvedor(String pTexto, Throwable pErro) {
        if (this == PARA_TUDO) {
            throw new UnsupportedOperationException(" O erro do tipo Para tudo deve ser direcionado ao Sistema ");
        }
        SBCore.RelatarErro(this, pTexto, pErro);
    }

}
