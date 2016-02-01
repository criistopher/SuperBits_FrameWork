/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Mensagens;

import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;

/**
 *
 * @author Salvio
 */
public class CentramMensagemProgramadorMsgThread implements ItfCentralMensagens {

    @Override
    public void enviaMensagem(ItfMensagem pMensagem) {

        UTILSBCoreDesktopApp.showMessage(pMensagem);
    }

}
