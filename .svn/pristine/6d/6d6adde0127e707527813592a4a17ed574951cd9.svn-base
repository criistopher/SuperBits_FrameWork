/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.Mensagens.ItfMensagem;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public interface ItfResposta {

    public static enum Resultado {

        SUCESSO, ALERTA, FALHOU
    }

    public Resultado getResultado();

    public List<ItfMensagem> getMensagens();

    public Class getTipoRetorno();

    public Object getRetorno();

    public ItfResposta setRetornoDisparaERetorna(Object pObjetoResultante);

    public ItfResposta addMensagemAvisoDisparaERetorna(String pMensagem);

    public ItfResposta addMensagemDisparaERetorna(ItfMensagem pMensagem);

    public ItfResposta addMensagemErroDisparaERetorna(String pMensagem);

    public ItfResposta addMensagemAlertaDisparaERetorna(String pMensagem);

    public void dispararMensagens();

    public void addMensagem(ItfMensagem pMensagem);

    public void addAlerta(String Pmensagem);

    public void addAviso(String Pmensagem);

    public boolean isSucesso();

    public void addErro(String Pmensagem);

}
