/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanGenerico;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreJira {

    public static enum TIPOS_DE_TAREFA_JIRA {

        ACAO_TESTE_MANAGED_BEAN,
        ACAO_IMPLEMENTACAO_MANAGED_BEAN,
        ACAO_CRIAR_FORMULARIO,
        ACAO_CRIAR_FORMULARIO_COMPLEXO,
        ACAO_TESTES_AMBIENTE_DE_DADOS,
        ACAO_BANCO_IMPLEMENTACAO_AMBIENTE_DE_DADOS,
        ACAO_BANCO_TESTES_TIPOS,
        ACAO_BANCO_IMPLEMENTACAO_TIPOS,
        ACAO_TESTES_BANCO_CALCULO,
        ACAO_TESTES_BANCO_LISTSTAS,
        ACAO_IMPLEMENTAR_CONTROLLER,
        ACAO_TESTE_CONTROLLER;

        public Issue getTarefaInssueJira() {

            switch (this) {
                case ACAO_TESTE_MANAGED_BEAN:
                    break;
                case ACAO_IMPLEMENTACAO_MANAGED_BEAN:
                    break;

                case ACAO_TESTES_AMBIENTE_DE_DADOS:
                    break;
                case ACAO_BANCO_IMPLEMENTACAO_AMBIENTE_DE_DADOS:
                    break;
                case ACAO_BANCO_TESTES_TIPOS:
                    break;
                case ACAO_BANCO_IMPLEMENTACAO_TIPOS:
                    break;
                case ACAO_TESTES_BANCO_CALCULO:
                    break;
                case ACAO_TESTES_BANCO_LISTSTAS:
                    break;
                case ACAO_IMPLEMENTAR_CONTROLLER:
                    break;
                case ACAO_TESTE_CONTROLLER:
                    break;
                default:
                    throw new AssertionError(this.name());
            }

            return null;
        }

        public List<Issue> getIssueByTarefa(ItfAcaoDoSistema pAcaoDoSistema) {

            switch (pAcaoDoSistema.getTipoAcaoGenerica()) {
                case FORMULARIO_NOVO_REGISTRO:
                case FORMULARIO_EDITAR:

                case FORMULARIO_VISUALIZAR:
                case FORMULARIO_LISTAR:
                case FORMULARIO_MODAL:
                case SELECAO_DE_ACAO:

                    Issue acaoCriarFormulario = ACAO_CRIAR_FORMULARIO.getTarefaInssueJira();
                    // setar DAta e hora
                    break;
                case FORMULARIO_PERSONALIZADO:

                    break;

                case CONTROLLER_SALVAR_EDICAO:

                case CONTROLLER_SALVAR_NOVO:

                case CONTROLLER_SALVAR_MODO_MERGE:

                case CONTROLLER_ATIVAR_DESATIVAR:

                case CONTROLLER_ATIVAR:

                case CONTROLLER_REMOVER:

                case CONTROLLER_DESATIVAR:
                    Issue acaoControllerGenericoTestar = ACAO_TESTE_CONTROLLER.getTarefaInssueJira();
                    Issue acaoControllerGenericoImplementar = ACAO_IMPLEMENTAR_CONTROLLER.getTarefaInssueJira();
                    break;
                case CONTROLLER_PERSONALIZADO:
                    break;
                case GERENCIAR_DOMINIO:
                    break;
                default:

            }

            return null;
        }

        public List<Issue> getIssuesByTabela(ItfBeanGenerico pEntidade) {
            return null;

        }
    }

    private static JiraRestClient criarConexaoJira() {
        if (SBCore.getUrlJira().startsWith("https")) {
            throw new UnsupportedOperationException("A URL Jira precisa ser uma conexão segura com https");
        }
        try {
            URI jiraServerUrl = new URI(SBCore.getUrlJira());
            final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUrl, "salviof@gmail.com", "123321");
            if (restClient.getProjectClient().getAllProjects().claim().iterator().hasNext()) {
                throw new UnsupportedOperationException("A URL Jira precisa ser uma conexão segura com https");
            }
            return restClient;
        } catch (URISyntaxException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Impossível conectar com o servidor Jira", ex);
            return null;
        }

    }

    public static boolean criarTarefafasDaAcao(ItfAcaoDoSistema pAcao) {
        JiraRestClient conexao = null;

        try {
            conexao = criarConexaoJira();
            //      List<Issue> tarefas =

        } catch (Throwable t) {

        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (IOException ex) {
                    Logger.getLogger(UtilSBCoreJira.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;
    }

    public static boolean criarTarefasBancoDeDados(ItfBeanSimples pAcao) {

        return false;
    }

    private static boolean criarTarfaAcaoGestao() {
        return false;
    }

    private static boolean criarAcaoController() {
        return false;
    }

    private static boolean criarAcaoTabela() {
        return false;
    }

}
