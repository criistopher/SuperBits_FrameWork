/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Mensagens;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class Mensagem implements ItfMensagem {

    private final FabTipoUsuarioInteracao TIPOdeSTINATARIO;
    private final FabMensagens tipoMensagem;
    private final String mensagem;

    public Mensagem(FabTipoUsuarioInteracao TIPOdeSTINATARIO, FabMensagens tipoMensagem, String mensagem) {
        this.TIPOdeSTINATARIO = TIPOdeSTINATARIO;
        this.tipoMensagem = tipoMensagem;
        this.mensagem = mensagem;
    }

    @Override
    public FabMensagens getTipoDeMensagem() {
        return tipoMensagem;
    }

    @Override
    public FabTipoUsuarioInteracao getTipoDestinatario() {
        return TIPOdeSTINATARIO;
    }

    @Override
    public String getMenssagem() {
        return mensagem;
    }

    @Override
    public void enviarMensagem() {
        SBCore.getCentralDeMensagens().enviaMensagem(this);
    }

    @Override
    public void enviarMensagem(Class<? extends ItfCentralMensagens> pCentral) {
        ItfCentralMensagens central;
        try {
            central = pCentral.newInstance();
            central.enviaMensagem(this);
        } catch (InstantiationException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, mensagem, ex);
        }

    }

}
