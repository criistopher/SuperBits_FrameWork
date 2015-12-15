/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *
 * Classe erro básica que extende todos os erros dos projetos Super-Bits
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 24/05/2014
 *
 */
public abstract class InfoErroSB implements ItfInfoErroSB {

    private ItfMensagem mensagemDoDesenvolvedor;
    private FabErro tipoErro;
    private Throwable erroJavaGerado;
    private boolean configurado;

    public InfoErroSB() {

    }

    protected abstract void alertarResponsavel();

    protected abstract void lancarExcecao();

    protected abstract void lancarPane();

    protected abstract void registrarErro();

    private void checarConfiguracao() {
        if (!configurado) {
            System.out.println("O que você está fazendo ? Vai com calma amiguinho "
                    + "os erros devem ser Lançados utilizando a FabricaDeErros, "
                    + "ou SBCore, não é possícel acessr uma informação do erro sem configura-lo ;) ");
            pararSistem();
        }
    }

    @Override
    public void executarErro() {
        checarConfiguracao();
        sytemOutDoErro();

        switch (tipoErro) {
            case LANCAR_EXCECÃO:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        lancarExcecao();

                        break;
                    case PRODUCAO:
                        lancarExcecao();

                        break;
                    case HOMOLOGACAO:
                        registrarErro();
                        lancarExcecao();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }

                break;
            case PANE_NO_SISTEMA:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        alertarResponsavel();
                        break;
                    case PRODUCAO:
                        registrarErro();
                        alertarResponsavel();
                        break;
                    case HOMOLOGACAO:
                        registrarErro();
                        alertarResponsavel();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }

                break;
            case ARQUIVAR_LOG:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        registrarErro();
                        alertarResponsavel();
                        break;
                    case PRODUCAO:
                        registrarErro();
                        break;
                    case HOMOLOGACAO:
                        registrarErro();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }

                SBCore.getCentralDeEventos().registrarLogDeEvento(FabMensagens.ERRO, mensagemDoDesenvolvedor.getMenssagem(), -1);
                break;
            case SOLICITAR_REPARO:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        alertarResponsavel();
                        break;
                    case PRODUCAO:
                        alertarResponsavel();
                        break;
                    case HOMOLOGACAO:
                        alertarResponsavel();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }
                break;
            case PARA_TUDO:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        pararSistem();
                        break;
                    case PRODUCAO:
                        registrarErro();
                        break;
                    case HOMOLOGACAO:
                        pararSistem();
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());
                }

                break;
            default:
                throw new AssertionError(tipoErro.name());

        }

    }

    @Override
    public String getMsgDesenvolvedorLancou() {
        checarConfiguracao();
        return mensagemDoDesenvolvedor.getMenssagem();
    }

    @Override
    public List<String> getCaminhoStackTraceCompleto() {
        checarConfiguracao();
        return getCaminhoStackTraceContendoIsto(null);
    }

    @Override
    public List<String> getCaminhoStackTraceResumido() {
        checarConfiguracao();
        return getCaminhoStackTraceContendoIsto("super_bits");

    }

    @Override
    public String getCaminhoStackTraceCompletoStr() {
        checarConfiguracao();
        return UtilSBCoreStrings.getStringDaListaComBarraN(getCaminhoStackTraceCompleto());
    }

    @Override
    public String getCaminhoStackTraceResumidoStr() {
        checarConfiguracao();
        return UtilSBCoreStrings.getStringDaListaComBarraN(getCaminhoStackTraceResumido());
    }

    @Override
    public List<String> getCaminhoStackTraceContendoIsto(String pIsto) {
        checarConfiguracao();
        List<String> stackTraceResumido = new ArrayList<>();

        if (erroJavaGerado.getStackTrace() != null) {

            for (StackTraceElement etapa : erroJavaGerado.getStackTrace()) {
                if (pIsto == null) {
                    pIsto = "@#$#$$%#$$$$$$$$$$$$$!@#!@#/_)))";
                }
                if (etapa.getClassName().contains(pIsto)) {
                    stackTraceResumido.add(etapa.getClassName() + "->" + etapa.getMethodName() + "-Linha" + etapa.getLineNumber());
                }
            }
        }
        return stackTraceResumido;

    }

    @Override
    public Throwable getErroGerado() {
        checarConfiguracao();
        return erroJavaGerado;
    }

    @Override
    public List<String> causas() {
        checarConfiguracao();
        List<String> causas = new ArrayList<>();
        for (String causa : ExceptionUtils.getRootCauseStackTrace(erroJavaGerado)) {
            causas.add(causa);
        }
        return causas;

    }

    @Override
    public String getCausaInicial() {
        checarConfiguracao();
        String mensagem = "Causa inicial não encontrada";
        Throwable causa = ExceptionUtils.getRootCause(erroJavaGerado);
        if (causa != null) {
            mensagem = ExceptionUtils.getRootCause(erroJavaGerado).getMessage();
        }
        return mensagem;
    }

    @Override
    public String getCausaFinal() {
        checarConfiguracao();
        return erroJavaGerado.getCause().getMessage();
    }

    @Override
    public void configurar(ItfMensagem pMensagemDoDesenvolvedor, FabErro pTipoErrp, Throwable pErroGerado) {
        this.mensagemDoDesenvolvedor = pMensagemDoDesenvolvedor;
        this.tipoErro = pTipoErrp;
        this.erroJavaGerado = pErroGerado;
        configurado = true;
    }

    protected String getMensagemGenericaFormatada(String cabecalho) {
        String texto = cabecalho;
        if (cabecalho == null) {
            texto = "";
        }
        if (mensagemDoDesenvolvedor.getMenssagem() != null) {
            texto += (cabecalho).toUpperCase() + "\n";
        }
        if (erroJavaGerado == null) {
            return "ocorreu um erro, a exception não foi gerada para realizar o StackTrace, e a mensagem enviada foi " + mensagemDoDesenvolvedor.getMenssagem();
        }

        String mensagem = "";
        String causa = "";
        if (erroJavaGerado.getMessage() != null) {
            mensagem = "MENSAGEM: \n" + getMsgDesenvolvedorLancou();
        } else {
            mensagem = "MENSAGEM: \n" + getErroGerado().getMessage();
        }
        if (erroJavaGerado.getCause() != null) {
            causa = "CAUSA_FINAL: \n" + getErroGerado().getCause();

        }
        texto += "\n\n" + mensagem + "\n\n" + causa;

        texto += "\n CAUSA_INICIAL \n" + getCausaInicial();

        texto += "\n [LOCAL_PROVAVEL_PARA_CORREÇÃO:] \n";

        texto += "\n" + getCaminhoStackTraceResumidoStr();

        texto = UtilSBCoreStrings.quebrarStringEmLinhas(texto, 100);
        return texto;

    }

    /**
     *
     * O Conceito Geral para notificação de Erros é dividido entre:
     *
     *
     * ALERTA_SISTEMA: CRIA UM LOG DO ERRO --------------------------------
     * ALERTA USUARIO: EXIBE A MENSAGEM PARA O USUARIO------------------------
     * ALERTA SOLICITAR_REPARO: EXIBE NO SYSOUT UM ALERTA QUANDO EM
     * DESENVOLVIMENTO, SALVA EM LOG QUANDO EM TESTE, E NÃO FAZ NADA QUANDO EM
     * PRODUÇÃO PARA TUDO: EXECUTA O SYSTEM.OUT(0) CAUSANDO A PARALIZAÇÃO DO
     * ARQUIVAR_LOG QUANDO EM DESENVOLVIMENTO
     *
     * A forma de exibir a mensagem para o usuário ou para o programador, ou
     * arquivar um log de sistemas pode ser alterado de acordo com a aplicação
     * alterando a centralDeMensagens
     *
     */
    private void sytemOutDoErro() {

        try {

            if (erroJavaGerado == null) {
                try {
                    System.out.println("Erro não enviado gerando nova exceção para" + mensagemDoDesenvolvedor.getMenssagem());
                    throw new Exception("Erro não enviado gerando nova exceção para:" + mensagemDoDesenvolvedor.getMenssagem());
                } catch (Throwable e) {
                    erroJavaGerado = e;
                }
            }

            System.err.println();
            System.err.println();
            System.err.println();
            System.err.println("________________________________________________________________________________________________________________________________________________________");
            System.err.println("________________________________________________________________________________________________________________________________________________________");
            System.err.println("ERRO[" + tipoErro + "]:" + getMsgDesenvolvedorLancou());
            System.err.println("[MENSAGEM JAVA]");
            System.err.println(erroJavaGerado.getMessage());
            System.err.println("[CAUSA JAVA]");
            System.err.print(erroJavaGerado.getCause());
            System.err.println();
            System.err.println("[Mensagem Localizada]");
            System.err.println(erroJavaGerado.getLocalizedMessage());
            System.err.println("[CAMINHO]");
            erroJavaGerado.printStackTrace();
            System.err.println("________________________________________________________________________________________________________________________________________________________");
            System.err.println("________________________________________________________________________________________________________________________________________________________");
            System.err.println();
            System.err.println();
            System.err.println();

        } catch (Throwable erro) {
            System.out.println("Ouve um Inception,Aconteceu um erro dentro do lançamento de erros, isto precisa ser corrigido, que a força esteja com você.");
            pararSistem();
        }

    }

    protected void pararSistem() {
        System.err.println("_____________________________________________________________________");
        System.err.println(" ESTE ERRO PRECISA SER CORRIGIDO ANTES DE CONTINUAR O DESENVOLVIMENTO");
        System.err.println("_____________________________________________________________________");
        System.exit(0);
    }

}
