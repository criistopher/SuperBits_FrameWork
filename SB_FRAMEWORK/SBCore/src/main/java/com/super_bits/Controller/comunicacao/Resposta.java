/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.comunicacao;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfResposta;
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

    private Resultado resultado = Resultado.SUCESSO;

    private List<ItfMensagem> mensagens;

    private Class tipoRetorno;

    private Object retorno;

    private final ItfAcaoDoSistema acaoDosistema;

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

    @SuppressWarnings("LeakingThisInConstructor")
    public Resposta(Class pTipoRetorno, ItfAcaoDoSistema pAcaoDoSistema) {
        UtilSBCoreReflexao.instanciarListas(this);
        tipoRetorno = pTipoRetorno;
        acaoDosistema = pAcaoDoSistema;

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
    public ItfResposta addMensagem(ItfMensagem pMensagem) {
        mensagens.add(pMensagem);
        return this;
    }

    @Override
    public ItfResposta dispararMensagens() {
        calculaResultados();
        if (getMensagens().isEmpty()) {
            if (acaoDosistema != null) {
                addAviso(" A ação: " + acaoDosistema.getNomeAcao() + " foi realizada com sucesso!");
            } else {
                addAviso("Operação realizada com sucesso");
            }
        }

        for (ItfMensagem msg : getMensagens()) {
            msg.getTipoDeMensagem().enviaMensagemUsuario(msg.getMenssagem());
        }
        return this;
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
            addMensagemAlertaDisparaERetorna("A ação não produziu o resultado esperado");
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
    public ItfResposta addAlerta(String pMensagem) {
        ItfMensagem msg = FabMensagens.ALERTA.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        return this;
    }

    @Override
    public ItfResposta addAviso(String pMensagem) {
        ItfMensagem msg = FabMensagens.AVISO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        return this;
    }

    @Override
    public ItfResposta addErro(String pMensagem) {
        ItfMensagem msg = FabMensagens.ERRO.getMsgUsuario(pMensagem);
        mensagens.add(msg);
        return this;
    }

    @Override
    public boolean isSucesso() {
        calculaResultados();
        return (!resultado.equals(resultado.FALHOU));
    }

    public void setTipoRetorno(Class tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

}
