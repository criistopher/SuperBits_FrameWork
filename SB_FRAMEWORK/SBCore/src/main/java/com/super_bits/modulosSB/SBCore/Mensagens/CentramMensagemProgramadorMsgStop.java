/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Mensagens;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;

/**
 *
 * @author Salvio
 */
public class CentramMensagemProgramadorMsgStop implements ItfCentralMensagens {

    @Override
    public void enviaMensagem(ItfMensagem pMensagem) {
        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            if (pMensagem.getTipoDestinatario() == FabTipoUsuarioInteracao.USUARIO) {
                String jogoDaVelhinhaCega = "#####################";

                System.out.println(jogoDaVelhinhaCega + jogoDaVelhinhaCega + jogoDaVelhinhaCega);
                System.out.println(jogoDaVelhinhaCega + " MENSAGEM AO USUÁRIO " + jogoDaVelhinhaCega);
                System.out.println(jogoDaVelhinhaCega + pMensagem.getTipoDeMensagem().toString().toUpperCase() + jogoDaVelhinhaCega);
                System.out.println("");
                System.out.println("");
                System.out.println(pMensagem.getMenssagem());
                System.out.println("");
                System.out.println("");
                System.out.println(jogoDaVelhinhaCega + jogoDaVelhinhaCega + jogoDaVelhinhaCega);
                return;
            }
        }
        UTILSBCoreDesktopApp.showMessageStopProcess(pMensagem);

    }

}
