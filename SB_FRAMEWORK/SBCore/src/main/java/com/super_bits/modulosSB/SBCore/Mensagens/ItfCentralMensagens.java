/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.Mensagens;

/**
 *
 * A Central de mensagens controla o envio de mensagens em 3 níves de interações
 * possíveis conforme a espeficação do TipoDEUsuarioInteração.
 *
 * @see FabTipoUsuarioInteracao
 *
 *
 * @author Salvio
 */
public interface ItfCentralMensagens {

    /**
     *
     * Envia uma mensagem do tipo MensagemSB, destinada ao Usuario,
     * Desenvolvedor, ou sistema
     *
     * Envia Mensagem
     *
     * @param pMensagem
     */
    public void enviaMensagem(ItfMensagem pMensagem);

    /**
     *
     * Envia uma mensagem do tipo aviiso ao usuário
     *
     * @param pMensagem Mensagem que será enviada
     */
    public void enviarMsgAvisoAoUsuario(String pMensagem);

    /**
     *
     * Envia uma mensagem do tipo alerta ao usuário
     *
     * @param pMensagem
     */
    public void enviarMsgAlertaAoUsuario(String pMensagem);

    /**
     *
     * Envia uma mensagem de erro ao usuário
     *
     * @param pMensagem
     */
    public void enviarMsgErroAoUsuario(String pMensagem);

    /**
     *
     * Envia um aviso ao Desenvolvedor
     *
     * @param pMensagem
     */
    public void enviarMsgAvisoAoDesenvolvedor(String pMensagem);

    /**
     *
     * Envia uma mensagem de alerta ao DEsenvolvedor
     *
     * @param pMensagem Mensagem enviada
     */
    public void enviarMsgAlertaAoDesenvolvedor(String pMensagem);

    /**
     * Envia uma mensagem de erro ao desenvolvedor
     *
     * @param pMensagem Mensagem enviada
     */
    public void enviarMsgErroAoDesenvolvedor(String pMensagem);

}
