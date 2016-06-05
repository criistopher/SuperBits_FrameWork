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

    /**
     *
     */
    public void buildAcoesJira() {

        MapaAcoesSistema mapaAcoes;
<<<<<<< HEAD
        List<Class> entidades = UtilSBPersistencia.getTodasEntidades();
=======
>>>>>>> 3b0a65d3903c6b0c576a2d6148cb2886de6f63af

        for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {

            if (acao.getTipoAcaoGenerica() == null) {

                throw new UnsupportedOperationException("A ação generica para" + acao.getNomeUnico() + " não foi especificada");
            }
            User usuarioDaTarefa = getAnalistaTDD();
            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorTipoAcao(acao.getTipoAcaoGenerica())) {

                TarefaJira tarefa = getTarefaJiraAcaoDoSistema(tipoTarefa, acao);
                switch (tarefa.getTipoTarefa()) {

                    case ACAO_IMPLEMENTACAO_MANAGED_BEAN:
                    case ACAO_CRIAR_FORMULARIO:
                    case ACAO_CRIAR_FORMULARIO_COMPLEXO:
                    case ACAO_IMPLEMENTAR_CONTROLLER:
                    case ACAO_IMPLEMENTAR_CONTROLLER_COMPLEXO:
                    case ACAO_BANCO_IMPLEMENTACAO_TIPOS:
                        usuarioDaTarefa = getAnalistaImplementacao();
                        break;

                    case ACAO_TESTES_ENTIDADE_CALCULO:
                    case ACAO_TESTE_MANAGED_BEAN:
                    case ACAO_TESTES_ENTIDADE_LISTAS:
                    case ACAO_TESTE_CONTROLLER:
                    case ACAO_TESTE_CONTROLLER_COMPLEXO:
                        usuarioDaTarefa = getAnalistaTDD();
                        break;
                    default:

                }

                if (!UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefa, usuarioDaTarefa)) {

                    throw new UnsupportedOperationException("Erro criando ação para " + acao.getNomeUnico());
                }
            }

        }
<<<<<<< HEAD
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
=======
        List<Class> entidades = UtilSBPersistencia.getTodasEntidades();
>>>>>>> 3b0a65d3903c6b0c576a2d6148cb2886de6f63af

        for (Class entidade : entidades) {

            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorEntidade(entidade)) {

                TarefaJira tarefaEntidade = UtilSBCoreJira.getTarefaJiraEntidade(tipoTarefa, entidade);

                if (!UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefaEntidade, getAnalistaBancoDados())) {

                    throw new UnsupportedOperationException("Erro criando ação para " + entidade.getSimpleName());
                }
            }

        }

        fecharConexao();
    }

}
