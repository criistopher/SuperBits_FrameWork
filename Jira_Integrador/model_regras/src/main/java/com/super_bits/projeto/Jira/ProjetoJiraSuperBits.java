/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.atlassian.jira.rest.client.api.domain.User;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.projeto.Jira.Jira.TarefaJira;
import static com.super_bits.projeto.Jira.UtilSBCoreJira.getTarefaJiraAcaoDoSistema;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class ProjetoJiraSuperBits extends ProjetoJiraSuperBitsAbstrato {

    private User analistaBancoDados;
    private User analistaTDD;
    private User implementaDor;
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

    public User getImplementaDor() {
        return implementaDor;
    }

    public void setImplementaDor(String implementaDor) {
        this.implementaDor = getUsuario(implementaDor);
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

    /**
     *
     */
    public void buildAcoesJira() {

        MapaAcoesSistema mapaAcoes;
        List<Class> entidades = UtilSBPersistencia.getTodasEntidades();

        for (Class entidade : entidades) {

            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorEntidade(entidade)) {

                TarefaJira tarefaEntidade = UtilSBCoreJira.getTarefaJiraEntidade(tipoTarefa, entidade);
                if (!UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefaEntidade)) {

                    throw new UnsupportedOperationException("Erro criando ação para " + entidade.getSimpleName());
                }
            }

        }

        for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {

            if (acao.getTipoAcaoGenerica() == null) {

                throw new UnsupportedOperationException("A ação generica para" + acao.getNomeUnico() + " não foi especificada");
            }

            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorTipoAcao(acao.getTipoAcaoGenerica())) {

                TarefaJira tarefa = getTarefaJiraAcaoDoSistema(tipoTarefa, acao);

                if (!UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefa)) {

                    throw new UnsupportedOperationException("Erro criando ação para " + acao.getNomeUnico());
                }
            }

        }
        /**
         * for (Class entidade : UtilSBPersistencia.getTodasEntidades()) {
         *
         * UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA[] tarefas =
         * UtilSBCoreJira.getTipoTarefaPorEntidade(entidade);
         *
         * for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : tarefas) {
         *
         * TarefaJira tarefa =
         * UtilSBCoreJira.getTarefaJiraDoBancoDeDados(tipoTarefa, entidade);
         *
         * UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefa); }
         */
        fecharConexao();
    }

}
