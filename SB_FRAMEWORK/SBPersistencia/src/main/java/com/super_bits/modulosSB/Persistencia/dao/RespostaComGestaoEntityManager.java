/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.dao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author desenvolvedor
 */
public abstract class RespostaComGestaoEntityManager implements ItfResposta {

    private final ItfResposta resp;
    private EntityManager em;

    public RespostaComGestaoEntityManager(ItfResposta pResp) {

        resp = pResp;
        if (pResp.isSucesso()) {
            executarAcao();
        }

    }

    public void atualizarEntidade(Object pObjeto) {
        merge(pObjeto);
    }

    public void criaNovaEntidade(Object pObjeto) {
        if (UtilSBPersistencia.persistirRegistro(pObjeto, em)) {
            throw new UnsupportedOperationException("Erro Criando" + pObjeto);
        }
    }

    public void excluirEntidade(Object pObjeto) {

        if (!UtilSBPersistencia.exluirRegistro(pObjeto, em)) {
            throw new UnsupportedOperationException("Erro excluindo Entidade" + pObjeto);
        }

    }

    public synchronized void merge(Object pObjeto) {
        try {
            Object retornoMerge = UtilSBPersistencia.mergeRegistro(pObjeto, em);
            if (retornoMerge == null) {
                throw new UnsupportedOperationException("Erro gravando " + pObjeto + " na ação " + resp.getAcaoVinculada().getNomeAcao());
            }
            pObjeto = retornoMerge;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.LANCAR_EXCECÃO, "Erro Executando merge em " + pObjeto, t);
            throw new UnsupportedOperationException("Erro gravando " + pObjeto + " na ação " + resp.getAcaoVinculada().getNomeAcao());
        }

    }

    private void executarAcao() {
        try {
            try {
                em = UtilSBPersistencia.getNovoEM();

                if (em == null) {
                    resp.addErro("Não foi possível estabelecer uma conexão com o banco de dados");
                    return;
                }

                if (!UtilSBPersistencia.iniciarTransacao(em)) {
                    resp.addErro("Erro iniciando uma Transação no banco de dados");
                    return;
                }

                regraDeNegocio();

                if (!UtilSBPersistencia.finalizarTransacao(em)) {

                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }

                    throw new UnsupportedOperationException("Ao ao finalizar tranzação");
                } else {

                    em.close();

                }

            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ero inesperado executando " + resp.getAcaoVinculada(), t);
                addErro("Ero inesperado executando " + resp.getAcaoVinculada());
                if (em != null) {
                    if (em.isOpen()) {
                        em.close();
                    }
                }
                if (resp != null) {
                    resp.addMensagemErroDisparaERetorna("Ocorreu um erro inesperado  executando" + resp.getAcaoVinculada().getNomeAcao());
                }
            }
        } finally {
            if (em != null) {
                if (em.getTransaction().isActive()) {
                    try {
                        em.getTransaction().rollback();
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro executando roolback, em transação ativa em " + resp.getAcaoVinculada(), t);
                    }
                }
                if (em.isOpen()) {
                    try {
                        em.close();
                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro finalizando entitymanager não finalizado em " + resp.getAcaoVinculada(), t);
                    }

                }

            }
        }

    }

    protected EntityManager getEmResposta() {
        return em;
    }

    public abstract void regraDeNegocio();

    @Override
    public ItfResposta.Resultado getResultado() {
        return resp.getResultado();
    }

    @Override
    public List<ItfMensagem> getMensagens() {
        return resp.getMensagens();
    }

    @Override
    public Class getTipoRetorno() {
        return resp.getTipoRetorno();
    }

    @Override
    public Object getRetorno() {
        return resp.getRetorno();
    }

    @Override
    public ItfResposta setRetornoDisparaERetorna(Object pObjetoResultante) {
        return resp.setRetornoDisparaERetorna(pObjetoResultante);
    }

    @Override
    public ItfResposta addMensagemAvisoDisparaERetorna(String pMensagem) {
        return resp.addMensagemErroDisparaERetorna(pMensagem);
    }

    @Override
    public ItfResposta addMensagemDisparaERetorna(ItfMensagem pMensagem) {
        return resp.addMensagemDisparaERetorna(pMensagem);
    }

    @Override
    public ItfResposta addMensagemErroDisparaERetorna(String pMensagem) {
        return resp.addMensagemErroDisparaERetorna(pMensagem);
    }

    @Override
    public ItfResposta addMensagemAlertaDisparaERetorna(String pMensagem) {
        return resp.addMensagemAlertaDisparaERetorna(pMensagem);
    }

    @Override
    public ItfResposta dispararMensagens() {
        return resp.dispararMensagens();
    }

    @Override
    public ItfResposta addMensagem(ItfMensagem pMensagem) {
        return resp.addMensagem(pMensagem);
    }

    @Override
    public ItfResposta addAlerta(String Pmensagem) {
        return resp.addAlerta(Pmensagem);
    }

    @Override
    public ItfResposta addAviso(String Pmensagem) {
        return resp.addErro(Pmensagem);
    }

    @Override
    public ItfResposta addErro(String Pmensagem) {
        return resp.addErro(Pmensagem);
    }

    @Override
    public ItfResposta setProximoFormulario(ItfAcaoFormulario pFormulario) {
        return resp.setProximoFormulario(pFormulario);
    }

    @Override
    public ItfAcaoFormulario getAcaoProximoFormulario() {
        return resp.getAcaoProximoFormulario();
    }

    @Override
    public boolean isTemFormulario() {
        return resp.isTemFormulario();
    }

    @Override
    public boolean isSucesso() {
        return resp.isSucesso();
    }

    @Override
    public ItfAcaoDoSistema getAcaoVinculada() {
        return resp.getAcaoVinculada();
    }

}
