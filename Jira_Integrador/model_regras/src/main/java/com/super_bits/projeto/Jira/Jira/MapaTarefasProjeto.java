/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira.Jira;

import com.atlassian.jira.rest.client.api.domain.User;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.projeto.Jira.UtilSBCoreJira;
import static com.super_bits.projeto.Jira.UtilSBCoreJira.getTarefaJiraAcaoDoSistema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class MapaTarefasProjeto {

    private static final Map<String, TarefaSuperBits> TAREFAS_PROJETO_ATUAL = new HashMap<>();
    private static boolean tarefasCriadas = false;

    private static synchronized void criarTarefas() {
        if (tarefasCriadas) {
            return;
        }

        // percorrendo todas as ações
        for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {
            if (acao.getTipoAcaoGenerica() == null) {
                throw new UnsupportedOperationException("A ação generica para" + acao.getNomeUnico() + " não foi especificada");
            }

            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorTipoAcao(acao.getTipoAcaoGenerica())) {
                // identificando Ação de Gestão
                TarefaJira tarefa = getTarefaJiraAcaoDoSistema(tipoTarefa, acao);
                MapaTarefasProjeto.TAREFAS_PROJETO_ATUAL.put(
                        tarefa.getAcaoVinculada().getComoSecundaria().getAcaoPrincipal().getNomeUnico(),
                        new TarefaSuperBits(tarefa));
            }
        }

        List<Class> entidades = UtilSBPersistencia.getTodasEntidades();

        for (Class entidade : entidades) {

            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorEntidade(entidade)) {

                TarefaJira tarefaEntidade = UtilSBCoreJira.getTarefaJiraEntidade(tipoTarefa, entidade);

                /*  if (!UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefaEntidade, getAnalistaBancoDados())) {

                    throw new UnsupportedOperationException("Erro criando ação para " + entidade.getSimpleName());
                } */
            }

        }

        tarefasCriadas = true;
    }

    public static List<TarefaSuperBits> getTodasTarefas() {
        criarTarefas();
        return null;
    }

}
