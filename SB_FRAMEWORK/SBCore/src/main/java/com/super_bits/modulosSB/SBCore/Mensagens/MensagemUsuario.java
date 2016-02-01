/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Mensagens;

/**
 *
 * @author sfurbino
 */
public class MensagemUsuario extends Mensagem {

    public MensagemUsuario(String mensagem) {
        super(FabTipoUsuarioInteracao.USUARIO, FabMensagens.ALERTA, mensagem);
    }

}
