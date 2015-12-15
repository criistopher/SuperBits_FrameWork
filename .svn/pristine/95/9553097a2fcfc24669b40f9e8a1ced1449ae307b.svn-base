/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller.comunicacao;

import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public class Resposta implements ItfResposta {

    private Resultado resultado;

    private List<ItfMensagem> mensagens;

    private final Class tipoRetorno;

    private Object retorno;

    private void calculaResultados() {
        resultado = Resultado.SUCESSO;
        for (ItfMensagem msg : mensagens) {
            switch (msg.getTipoDeMensagem()) {
                case AVISO:
                    break;
                case ALERTA:
                    if (resultado != Resultado.FALHOU) {
                        resultado = Resultado.ALERTA;
                    }
                    break;
                case ERRO:
                    resultado = Resultado.FALHOU;
                    break;
                case ERRO_FATAL:
                    resultado = Resultado.FALHOU;
                    break;
                default:
                    throw new AssertionError(msg.getTipoDeMensagem().name());

            }
        }
    }

    public Resposta(Class pTipoRetorno) {
        UtilSBCoreReflexao.instanciarListas(this);
        tipoRetorno = pTipoRetorno;
    }

    @Override
    public Resultado getResultado() {
        return resultado;
    }

    @Override
    public Class getTipoRetorno() {
        return tipoRetorno;
    }

    @Override
    public Object getRetorno() {
        return retorno;
    }

    @Override
    public List<ItfMensagem> getMensagens() {
        return mensagens;
    }

    @Override
    public ItfResposta addMensagemDisparaERetorna(ItfMensagem pMensagem) {
        mensagens.add(pMensagem);
        dispararMensagens();
        return this;
    }

    @Override
    public void addMensagem(ItfMensagem pMensagem) {
        mensagens.add(pMensagem);
    }

    @Override
    public void dispararMensagens() {
        for (ItfMensagem msg : getMensagens()) {
            msg.getTipoDeMensagem().enviaMensagemUsuario(msg.getMenssagem());
        }
    }

    @Override
    public ItfResposta addMensagemAvisoDisparaERetorna(String pMensagem) {
        ItfMensagem msg = FabMensagens.AVISO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        dispararMensagens();
        return this;
    }

    @Override
    public ItfResposta addMensagemErroDisparaERetorna(String pMensagem) {
        ItfMensagem msg = FabMensagens.ERRO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        dispararMensagens();
        return this;

    }

    @Override
    public ItfResposta addMensagemAlertaDisparaERetorna(String pMensagem) {
        ItfMensagem msg = FabMensagens.ALERTA.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        dispararMensagens();
        return this;
    }

    @Override
    public ItfResposta setRetornoDisparaERetorna(Object pObjetoResultante) {
        if (pObjetoResultante == null) {
            retorno = null;
            dispararMensagens();
            return this;
        }

        if (pObjetoResultante.getClass().getSimpleName().equals(tipoRetorno.getClass().getSimpleName())) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("O tipo de retorno diverge do tipo Especificado", null);
        }
        retorno = pObjetoResultante;
        dispararMensagens();
        return this;

    }

    @Override
    public void addAlerta(String pMensagem) {
        ItfMensagem msg = FabMensagens.ALERTA.getMsgUsuario(pMensagem);
        mensagens.add(msg);
    }

    @Override
    public void addAviso(String pMensagem) {
        ItfMensagem msg = FabMensagens.AVISO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
    }

    @Override
    public void addErro(String pMensagem) {
        ItfMensagem msg = FabMensagens.ERRO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
    }

    @Override
    public boolean isSucesso() {
        return resultado == Resultado.SUCESSO;
    }

}
