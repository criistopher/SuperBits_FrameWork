/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;

/**
 *
 * @author sfurbino
 */
public class ErroSBCoreDeveloperSopMessagem extends InfoErroSB {

    @Override
    protected void alertarResponsavel() {

        UTILSBCoreDesktopApp.showMessageStopProcess(FabMensagens.AVISO.getMsgDesenvolvedor(getMensagemGenericaFormatada("Alertando Responsavel")));
    }

    @Override
    protected void lancarExcecao() {

        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            System.out.println("Se você está vendo este erro é porque utilizou um lançamento de exceção, sem especificar o Throw no fim do método, Isto é uma má pratica que desabona a foça Jedi dos programadores Ninjas");
            pararSistem();
        }
        throw new UnsupportedOperationException("Um Pedido de Lançamento de Exceção foi gerado, ao chamar este método utilize Throwable"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void lancarPane() {
        UTILSBCoreDesktopApp.showMessageStopProcess(FabMensagens.AVISO.getMsgDesenvolvedor(getMensagemGenericaFormatada("VOCÊ GEROU UMA PANE NO SISTEMA!  (Parabéns irônico pra você)")));
    }

    @Override
    protected void registrarErro() {
        UTILSBCoreDesktopApp.showMessageStopProcess(FabMensagens.AVISO.getMsgDesenvolvedor(getMensagemGenericaFormatada("Atenção um pedido de log foi gerado para este erro:")));
    }

}
