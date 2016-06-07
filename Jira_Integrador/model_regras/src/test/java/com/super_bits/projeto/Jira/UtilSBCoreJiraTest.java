/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.super_bits.config.ConfigPersistenciaIntegrador;
import com.super_bits.config.FabConfiguracoesDeAmbienteModelExemplo;
import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.projeto.Jira.Jira.TarefaJira;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreJiraTest extends TesteJunitSBPersistencia {

    public UtilSBCoreJiraTest() {
    }

    @Test
    public void testGetTarefaJiraAcaoDoSistema() {

        JiraRestClient conexao = UtilSBCoreJira.criarConexaoJira("salviof@gmail.com", "123321");
        TarefaJira tarefa = UtilSBCoreJira.getTarefaJiraAcaoDoSistema(UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTACAO_MANAGED_BEAN, FabAcaoProjetoSB.PROJETO_GERENCIAR_MB.getAcaoDoSistema());
        UtilSBCoreJira.criarTarefafasDaAcao(conexao, tarefa);

    }

    @Test
    public void testGetTarefaJiraAcaoDoBancoDeDados() {
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesDeAmbienteModelExemplo.DESENVOLVIMENTO.getConfiguracao());
        SBPersistencia.configuraJPA(new ConfigPersistenciaIntegrador());
    }

}
