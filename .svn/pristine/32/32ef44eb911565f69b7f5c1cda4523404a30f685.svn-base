/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.Mensagens.ItfMensagem;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public interface ItfInfoErroSB {

    /**
     *
     * ISTO:
     *
     * @see ItfInfoErroSB#getCaminhoStackTraceCompleto() No formato String
     *
     * @return
     */
    public String getCaminhoStackTraceCompletoStr();

    public String getCaminhoStackTraceResumidoStr();

    /**
     *
     * Obtem a tragetoria completa hierarquica de chamadas de método até
     * acontecer este erro
     *
     * @return
     */
    public List<String> getCaminhoStackTraceCompleto();

    /**
     *
     * ISTO:
     *
     * @see ItfInfoErroSB#getCaminhoStackTraceCompleto() Onde o Classhpath do
     * metodo contenha super_bits
     *
     *
     * @return
     */
    public List<String> getCaminhoStackTraceResumido();

    /**
     *
     * Obtem:
     *
     * @see ItfInfoErroSB#getCaminhoStackTraceCompleto()
     *
     * Onde o método contenha uma palavra específica em seu classpach
     *
     * @param pIsto String com a palavra que deve estar no classpach do metodo
     * @return Caminho de metodos contendo isto no Classpath
     */
    public List<String> getCaminhoStackTraceContendoIsto(String pIsto);

    /**
     * Objeto que extende Throwable que originou o relato de erro
     *
     * @return objeto Throwable origem
     */
    public Throwable getErroGerado();

    /**
     *
     * Mensagem enviada pelo desenvolvedor ao lançar este erro, seja camarada e
     * maroto para pensar em dicas eficases para seu coleguinha, ele e nós
     * merecemos, e queremos trabalhar menos e ganhar mais.
     *
     * @return Mensagem criada pelo desenvolvedor ao lançar o erro
     */
    public String getMsgDesenvolvedorLancou();

    /**
     *
     *
     *
     * Executa uma ação de acordo com as informações do Erro
     *
     * Cada Ambiente de execução pode ter um tratamento diferente O Ambiente de
     * execução é configurado Chamando SBCore.configurar
     *
     *
     * ###ATENÇÃO### E execução de sistemas SuperBits sem executar esta
     * configuração gera um erro to tipo PARATUDO que executa um system.out!
     *
     */
    public void executarErro();

    public List<String> causas();

    public String getCausaInicial();

    public String getCausaFinal();

    public void configurar(ItfMensagem pMensagemDoDesenvolvedor, FabErro pTipoErrp, Throwable pErroGerado);

}
