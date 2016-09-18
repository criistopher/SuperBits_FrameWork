/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Mensagens;

/**
 *
 * @author sfurbino
 */
public class MensagemProgramador extends Mensagem {

    public MensagemProgramador(String pMensagem) {
        super(FabTipoUsuarioInteracao.DESENVOLVEDOR, FabMensagens.AVISO, pMensagem);
    }

}
