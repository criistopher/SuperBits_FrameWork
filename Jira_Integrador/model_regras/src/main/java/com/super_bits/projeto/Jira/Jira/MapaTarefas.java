/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira.Jira;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaDeAcoes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.projeto.Jira.UtilSBCoreJira;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class MapaTarefas {

    private final Map<String, List<TarefaSuperBits>> TAREFAS_PROJETO_ATUAL = new HashMap<>();

    public MapaTarefas(MapaDeAcoes acoes, String nomePersistenceUnit) {

        // percorrendo todas as ações
        for (ItfAcaoDoSistema acao : acoes.getListaTodasAcoes()) {
            if (acao.getTipoAcaoGenerica() == null) {
                throw new UnsupportedOperationException("A ação generica para" + acao.getNomeUnico() + " não foi especificada");
            }

            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorTipoAcao(acao.getTipoAcaoGenerica())) {
                // identificando Ação de Gestão
                addTarefa(acao);
            }
        }
        List<Class> entidades = UtilSBPersistencia.getTodasEntidades(nomePersistenceUnit);
        //Percorrendo entidades criando ações refente a entidades;
        entidades.stream().forEach((entidade) -> {
            addTarefaBancoDeDAdos(entidade);

        });

    }

    public MapaTarefas(MapaDeAcoes acoes) {
        Map<String, Class> mapaClasse = new HashMap();
        for (ItfAcaoDoSistema acao : acoes.getListaTodasAcoes()) {
            if (acao.getTipoAcaoGenerica() == null) {
                throw new UnsupportedOperationException("A ação generica para" + acao.getNomeUnico() + " não foi especificada");
            }
            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorTipoAcao(acao.getTipoAcaoGenerica())) {
                addTarefa(acao);
            }
            mapaClasse.put(acao.getEnumAcaoDoSistema().getEntidadeDominio().getSimpleName(), acao.getEnumAcaoDoSistema().getEntidadeDominio());
        }
        mapaClasse.values().stream().forEach((entidade) -> {
            addTarefaBancoDeDAdos(entidade);
        });
    }

    private void addTarefa(ItfAcaoDoSistema pAcao) {
        try {
            String chaveAcaoGestao;
            chaveAcaoGestao = pAcao.getAcaoDeGestaoEntidade().getNomeUnico();
            if (!TAREFAS_PROJETO_ATUAL.containsKey(chaveAcaoGestao)) {
                TAREFAS_PROJETO_ATUAL.put(chaveAcaoGestao, new ArrayList<>());
            }
            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorTipoAcao(pAcao.getTipoAcaoGenerica())) {
                TarefaJira tarefa = UtilSBCoreJira.getTarefaJiraAcaoDoSistema(tipoTarefa, pAcao);
                TarefaSuperBits novaTarefa = new TarefaSuperBits(tarefa);
                if (!TAREFAS_PROJETO_ATUAL.get(chaveAcaoGestao).contains(novaTarefa)) {
                    TAREFAS_PROJETO_ATUAL.get(chaveAcaoGestao).add(new TarefaSuperBits(tarefa));
                }

            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando tarefa para ação" + pAcao.getNomeUnico(), t);
        }

    }

    private void addTarefaBancoDeDAdos(Class pEntidade) {

        if (!TAREFAS_PROJETO_ATUAL.containsKey(pEntidade.getSimpleName())) {
            TAREFAS_PROJETO_ATUAL.put(pEntidade.getSimpleName(), new ArrayList<>());
        }

        for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorEntidade(pEntidade)) {
            TarefaJira tarefaEntidade = UtilSBCoreJira.getTarefaJiraEntidade(tipoTarefa, pEntidade);

            TAREFAS_PROJETO_ATUAL.get(pEntidade.getSimpleName()).add(new TarefaSuperBits(tarefaEntidade));
            /*  if (!UtilSBCoreJira.criarTarefafasDaAcao(getConexao(), tarefaEntidade, getAnalistaBancoDados())) {
                throw new UnsupportedOperationException("Erro criando ação para " + entidade.getSimpleName());
                } */
        }

    }

    public List<TarefaSuperBits> getTodasTarefas() {

        List<TarefaSuperBits> todasTarefas = new ArrayList<>();
        TAREFAS_PROJETO_ATUAL.values().stream().forEach((List<TarefaSuperBits> tarefasDaGestao) -> {
            tarefasDaGestao.stream().forEach((tr) -> {
                todasTarefas.add(tr);
            });
        });

        return todasTarefas;
    }

    public List<TarefaSuperBits> getTarefasDaGestao(ItfAcaoGerenciarEntidade pAcaoGestao) {
        return TAREFAS_PROJETO_ATUAL.get(pAcaoGestao.getNomeUnico());
    }

    public List<TarefaSuperBits> getTarefasDaTabela(Class pEntidade) {
        return TAREFAS_PROJETO_ATUAL.get(pEntidade.getSimpleName());
    }

}
