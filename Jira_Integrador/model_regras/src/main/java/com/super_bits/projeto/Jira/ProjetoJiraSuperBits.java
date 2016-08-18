/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.atlassian.jira.rest.client.api.domain.User;
import com.super_bits.projeto.Jira.Jira.MapaTarefasProjeto;
import com.super_bits.projeto.Jira.Jira.TarefaJira;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;

/**
 *
 * @author salvioF
 */
public class ProjetoJiraSuperBits extends ProjetoJiraSuperBitsAbstrato {

    private User analistaBancoDados;
    private User analistaTDD;
    private User analistaImplementacao;
    private User analistaTelas;
    private User analistaAndroid;

    public ProjetoJiraSuperBits(String pUsuario, String pSenha) {
        super(pUsuario, pSenha);

    }

    private User getUsuario(String pUsuario) {
        return UtilSBCoreJira.getUsuarioPorNome(getConexao(), pUsuario);
    }

    public boolean atualizarProjeto() {
        return true;
    }

    public User getAnalistaBancoDados() {
        return analistaBancoDados;
    }

    public void setAnalistaBancoDados(String analistaBancoDados) {
        this.analistaBancoDados = getUsuario(analistaBancoDados);
    }

    public User getAnalistaTDD() {
        return analistaTDD;
    }

    public void setAnalistaTDD(String analistaTDD) {
        this.analistaTDD = getUsuario(analistaTDD);
    }

    public User getAnalistaImplementacao() {
        return analistaImplementacao;
    }

    public void setAnalistaImplementacao(String implementaDor) {
        this.analistaImplementacao = getUsuario(implementaDor);
    }

    public User getAnalistaTelas() {
        return analistaTelas;
    }

    public void setAnalistaTelas(String analistaTelas) {
        this.analistaTelas = getUsuario(analistaTelas);
    }

    public User getAnalistaAndroid() {
        return analistaAndroid;
    }

    public void setAnalistaAndroid(String pAnalistaAndroid) {
        this.analistaAndroid = getUsuario(pAnalistaAndroid);
    }

    public User getUsuarioPorTipoDeAcao(TarefaJira pTarefa) {

        switch (pTarefa.getTipoTarefa().getTipoProfissional()) {
            case ANALISTA_BANCO_DE_DADOS:
                return getAnalistaBancoDados();
            case ANALISTA_LOGICA_TDD:
                return getAnalistaTDD();
            case ANALISTA_IMPLEMENTACAO:
                return getAnalistaImplementacao();
            case ANALISTA_TELAS:
                return getAnalistaTelas();
            case DESIGNER:
                return getAnalistaTelas();
            case ANALISTA_REQUISITOS:
                return getAnalistaBancoDados();
            case ANALISTA_ANDROID:
                return getAnalistaTDD();
            default:
                throw new AssertionError(pTarefa.getTipoTarefa().getTipoProfissional().name());

        }

    }

    public void atualizaAcoesJira() {

        for (TarefaSuperBits tarefa : MapaTarefasProjeto.getTodasTarefas()) {
            if (!UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefa.getTarefaJiraOrigem(), getUsuarioPorTipoDeAcao(tarefa.getTarefaJiraOrigem()))) {
                throw new UnsupportedOperationException("Erro criando ação para " + tarefa.getTarefaJiraOrigem().getAcaoVinculada().getNomeUnico());
            }
        }

        fecharConexao();

    }

    /**
     *
     */
    public void buildAcoesJira() {
        MapaTarefasProjeto.getTodasTarefas();
    }

}
