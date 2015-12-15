/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Mensagens;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

/**
 *
 * @author sfurbino
 */
public enum FabMensagens {

    AVISO, ALERTA, ERRO, ERRO_FATAL;

    public ItfMensagem getMsgUsuario(String pMensagem) {
        return new Mensagem(FabTipoUsuarioInteracao.USUARIO, this, pMensagem);
    }

    public ItfMensagem getMsgDesenvolvedor(String pMensagem) {
        return new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, ALERTA, pMensagem);
    }

    public ItfMensagem getMsgSistema(String pMensagem) {
        return new Mensagem(FabTipoUsuarioInteracao.SISTEMA, ALERTA, pMensagem);
    }

    public void enviaMensagemUsuario(String pMensagem) {
        FabMensagens.enviarMensagemUsuario(pMensagem, this);
    }

    public void enviaMensagemSistema(String pMensagem) {
        FabMensagens.enviarMensagemSistema(pMensagem, this);
    }

    public void enviaMensagemDesenvolvedor(String pMensagem) {
        FabMensagens.enviarMensagemDesenvolvedor(pMensagem, this);
    }

    public static void enviarMensagem(ItfMensagem mensagem) {

        SBCore.getCentralDeMensagens().enviaMensagem(mensagem);
    }

    public static void enviarMensagemUsuario(String pmensagem, FabMensagens pTipoMensagem) {
        ItfMensagem mensagem = new Mensagem(FabTipoUsuarioInteracao.USUARIO, pTipoMensagem, pmensagem);
        SBCore.getCentralDeMensagens().enviaMensagem(mensagem);
    }

    public static void enviarMensagemSistema(String pmensagem, FabMensagens pTipoMensagem) {
        ItfMensagem mensagem = new Mensagem(FabTipoUsuarioInteracao.SISTEMA, pTipoMensagem, pmensagem);
        SBCore.getCentralDeMensagens().enviaMensagem(mensagem);
    }

    public static void enviarMensagemDesenvolvedor(String pmensagem, FabMensagens pTipoMensagem) {
        ItfMensagem mensagem = new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, pTipoMensagem, pmensagem);
        SBCore.getCentralDeMensagens().enviaMensagem(mensagem);
    }

}
