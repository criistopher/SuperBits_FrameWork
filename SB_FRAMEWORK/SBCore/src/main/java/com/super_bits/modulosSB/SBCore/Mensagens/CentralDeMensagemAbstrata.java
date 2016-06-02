/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Mensagens;

/**
 *
 * @author desenvolvedor
 */
public abstract class CentralDeMensagemAbstrata implements ItfCentralMensagens {

    @Override
    public void enviarMsgAvisoAoUsuario(String pMensagem) {

        Mensagem novamensagem = new Mensagem(FabTipoUsuarioInteracao.USUARIO, FabMensagens.AVISO, pMensagem);
        enviaMensagem(novamensagem);
    }

    @Override
    public void enviarMsgAlertaAoUsuario(String pMensagem) {
        Mensagem novamensagem = new Mensagem(FabTipoUsuarioInteracao.USUARIO, FabMensagens.ALERTA, pMensagem);
        enviaMensagem(novamensagem);
    }

    @Override
    public void enviarMsgErroAoUsuario(String pMensagem) {
        Mensagem novamensagem = new Mensagem(FabTipoUsuarioInteracao.USUARIO, FabMensagens.ERRO, pMensagem);
        enviaMensagem(novamensagem);
    }

    @Override
    public void enviarMsgAvisoAoDesenvolvedor(String pMensagem) {
        Mensagem novamensagem = new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.AVISO, pMensagem);
        enviaMensagem(novamensagem);
    }

    @Override
    public void enviarMsgAlertaAoDesenvolvedor(String pMensagem) {
        Mensagem novamensagem = new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.ALERTA, pMensagem);
        enviaMensagem(novamensagem);
    }

    @Override
    public void enviarMsgErroAoDesenvolvedor(String pMensagem) {
        Mensagem novamensagem = new Mensagem(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.ERRO, pMensagem);
        enviaMensagem(novamensagem);
    }

}
