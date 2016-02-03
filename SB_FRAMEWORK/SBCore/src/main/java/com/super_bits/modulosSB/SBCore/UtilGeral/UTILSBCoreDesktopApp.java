/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.Mensagens.ItfMensagem;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Salvio
 */
public class UTILSBCoreDesktopApp {

    static class EnviaMensagemThread implements Runnable {

        ItfMensagem mensagem;

        public EnviaMensagemThread(ItfMensagem pMensagem) {
            mensagem = pMensagem;
        }

        @Override
        public void run() {
            System.out.println("..");
            // todo Verificar nescessidade do Jframe para exibição da mensagem
            JFrame janela = new JFrame("teste");
            janela.setVisible(true);
            JOptionPane.showMessageDialog(new JFrame(mensagem.getTipoDeMensagem().toString()), mensagem.getMenssagem());
        }

    }

    /**
     *
     * Envia uma mensagem utilizando Jpanel em uma Thread Separada, para
     * permitir o fluxo continuo da execução do sistema.
     *
     * @param pMensagem
     */
    public static void showMessage(ItfMensagem pMensagem) {
        new Thread(new EnviaMensagemThread(pMensagem)).start();
    }

    /**
     *
     * Envia a mensagem, e continua a execução do código depois do OK
     *
     * @param pMensagem
     */
    public static void showMessageStopProcess(ItfMensagem pMensagem) {

        // todo Verificar nescessidade do Jframe para exibição da mensagem
        JFrame janela = new JFrame("teste");
        janela.setVisible(true);
        JOptionPane.showMessageDialog(new JFrame(pMensagem.getTipoDeMensagem().toString()), pMensagem.getMenssagem());

    }

}
