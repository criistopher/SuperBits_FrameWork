/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.Mensagens.FabTipoUsuarioInteracao;
import com.super_bits.modulosSB.SBCore.Mensagens.Mensagem;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UTILSBCoreDesktopAppTest {

    public UTILSBCoreDesktopAppTest() {
    }

    @Test
    public void testShowMessage() {
        UTILSBCoreDesktopApp.showMessage(new Mensagem(FabTipoUsuarioInteracao.USUARIO, FabMensagens.ALERTA, "teste"));
    }

    @Test
    public void testShowMessageStopProcess() {
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoUsuarioInteracao.USUARIO, FabMensagens.ALERTA, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoUsuarioInteracao.USUARIO, FabMensagens.AVISO, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoUsuarioInteracao.USUARIO, FabMensagens.ERRO, "teste"));

        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.ALERTA, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.AVISO, "teste"));
        UTILSBCoreDesktopApp.showMessageStopProcess(new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.ERRO, "teste"));
    }

}
