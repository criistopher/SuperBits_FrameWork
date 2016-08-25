/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira.Jira;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.projeto.Jira.UtilSBCoreJira;
import static com.super_bits.projeto.Jira.UtilSBCoreJira.getTarefaJiraAcaoDoSistema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class MapaTarefasProjeto {

    private static final Map<String, List<TarefaSuperBits>> TAREFAS_PROJETO_ATUAL = new HashMap<>();
    private static boolean tarefasCriadas = false;

    private static void addTarefa(ItfAcaoDoSistema pAcao) {
        try {
            String chaveAcaoGestao;
            chaveAcaoGestao = pAcao.getAcaoDeGestaoEntidade().getNomeUnico();
            if (!TAREFAS_PROJETO_ATUAL.containsKey(chaveAcaoGestao)) {
                TAREFAS_PROJETO_ATUAL.put(chaveAcaoGestao, new ArrayList<>());
            }
            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorTipoAcao(pAcao.getTipoAcaoGenerica())) {
                TarefaJira tarefa = getTarefaJiraAcaoDoSistema(tipoTarefa, pAcao);
                TarefaSuperBits novaTarefa = new TarefaSuperBits(tarefa);
                if (!MapaTarefasProjeto.TAREFAS_PROJETO_ATUAL.get(chaveAcaoGestao).contains(novaTarefa)) {
                    MapaTarefasProjeto.TAREFAS_PROJETO_ATUAL.get(chaveAcaoGestao).add(new TarefaSuperBits(tarefa));
                }

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando tarefa para ação" + pAcao.getNomeUnico(), t);
        }

    }

    private static void addTarefaBancoDeDAdos(Class pEntidade) {

        if (!TAREFAS_PROJETO_ATUAL.containsKey(pEntidade.getSimpleName())) {
            TAREFAS_PROJETO_ATUAL.put(pEntidade.getSimpleName(), new ArrayList<>());
        }

        for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorEntidade(pEntidade)) {
            TarefaJira tarefaEntidade = UtilSBCoreJira.getTarefaJiraEntidade(tipoTarefa, pEntidade);

            MapaTarefasProjeto.TAREFAS_PROJETO_ATUAL.get(pEntidade.getSimpleName()).add(new TarefaSuperBits(tarefaEntidade));
            /*  if (!UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefaEntidade, getAnalistaBancoDados())) {
                throw new UnsupportedOperationException("Erro criando ação para " + entidade.getSimpleName());
                } */
        }

    }

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
                addTarefa(acao);

            }
        }

        List<Class> entidades = UtilSBPersistencia.getTodasEntidades();
        //Percorrendo entidades criando ações refente a entidades;
        entidades.stream().forEach((entidade) -> {
            addTarefaBancoDeDAdos(entidade);

        });

        tarefasCriadas = true;
    }

    public static List<TarefaSuperBits> getTodasTarefas() {
        criarTarefas();
        List<TarefaSuperBits> todasTarefas = new ArrayList<>();
        TAREFAS_PROJETO_ATUAL.values().stream().forEach((List<TarefaSuperBits> tarefasDaGestao) -> {
            tarefasDaGestao.stream().forEach((tr) -> {
                todasTarefas.add(tr);
            });
        });

        return todasTarefas;
    }

    public static List<TarefaSuperBits> getTarefasDaGestao(ItfAcaoGerenciarEntidade pAcaoGestao) {
        criarTarefas();
        return TAREFAS_PROJETO_ATUAL.get(pAcaoGestao.getNomeUnico());
    }

    public static List<TarefaSuperBits> getTarefasDaTabela(Class pEntidade) {
        criarTarefas();
        return TAREFAS_PROJETO_ATUAL.get(pEntidade.getSimpleName());
    }

}
