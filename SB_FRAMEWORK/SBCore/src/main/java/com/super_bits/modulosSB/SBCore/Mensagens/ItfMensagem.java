/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Mensagens;

/**
 *
 * @author sfurbino
 */
public interface ItfMensagem {

    public FabMensagens getTipoDeMensagem();

    public FabTipoUsuarioInteracao getTipoDestinatario();

    public String getMenssagem();

    public void enviarMensagem();

    public void enviarMensagem(Class<? extends ItfCentralMensagens> pCentral);

}
