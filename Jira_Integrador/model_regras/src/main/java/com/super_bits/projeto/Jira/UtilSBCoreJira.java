/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.projeto.Jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.BasicPriority;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.Priority;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.google.common.collect.Lists;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.projeto.Jira.Jira.TarefaJira;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreJira {

    public static enum TIPO_GRUPO_TAREFA {
        TELA_GESTAO_ENTIDADE,
        MODULO_CONTROLLER,
        MODELAGEM_TABELA,
        ACAO_BANCO_AMBIENTE_E_ADEQUACAO
    }

    public static TIPOS_DE_TAREFA_JIRA[] getTipoTarefaPorTipoAcao(FabTipoAcaoSistemaGenerica pTipoAcaoDoSistemaGenerica) {
        switch (pTipoAcaoDoSistemaGenerica) {
            case FORMULARIO_NOVO_REGISTRO:

            case FORMULARIO_EDITAR:

            case FORMULARIO_VISUALIZAR:

            case FORMULARIO_LISTAR:

            case FORMULARIO_MODAL:

            case SELECAO_DE_ACAO:

                return new TIPOS_DE_TAREFA_JIRA[]{
                    TIPOS_DE_TAREFA_JIRA.ACAO_CRIAR_FORMULARIO};

            case FORMULARIO_PERSONALIZADO:

                return new TIPOS_DE_TAREFA_JIRA[]{
                    TIPOS_DE_TAREFA_JIRA.ACAO_CRIAR_FORMULARIO_COMPLEXO};

            case CONTROLLER_SALVAR_EDICAO:

                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_CONTROLLER,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTAR_CONTROLLER};

            case CONTROLLER_SALVAR_NOVO:
                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_CONTROLLER,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTAR_CONTROLLER};

            case CONTROLLER_SALVAR_MODO_MERGE:
                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_CONTROLLER,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTAR_CONTROLLER};

            case CONTROLLER_ATIVAR_DESATIVAR:
                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_CONTROLLER,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTAR_CONTROLLER};

            case CONTROLLER_ATIVAR:
                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_CONTROLLER,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTAR_CONTROLLER};

            case CONTROLLER_REMOVER:
                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_CONTROLLER,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTAR_CONTROLLER};

            case CONTROLLER_DESATIVAR:
                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_CONTROLLER,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTAR_CONTROLLER};

            case CONTROLLER_PERSONALIZADO:
                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_CONTROLLER_COMPLEXO,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTAR_CONTROLLER_COMPLEXO};

            case GERENCIAR_DOMINIO:
                return new TIPOS_DE_TAREFA_JIRA[]{TIPOS_DE_TAREFA_JIRA.ACAO_TESTE_MANAGED_BEAN,
                    TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTACAO_MANAGED_BEAN};

            default:
                throw new UnsupportedOperationException("O tipo de ação Jira não foi definido para:" + pTipoAcaoDoSistemaGenerica + " ");
        }

    }

    public static TIPOS_DE_TAREFA_JIRA getTipoTarefaPorEntidade(Class pClasseEntidade) {

        return TIPOS_DE_TAREFA_JIRA.ACAO_TESTES_BANCO_CALCULO;

    }

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
        ACAO_SUB_CRIAR_CALCULO,
        ACAO_SUB_CRIAR_LISTA,
        ACAO_IMPLEMENTAR_CONTROLLER,
        ACAO_TESTE_CONTROLLER,
        ACAO_IMPLEMENTAR_CONTROLLER_COMPLEXO,
        ACAO_TESTE_CONTROLLER_COMPLEXO;

        public TarefaJira getTarefaInssueJira() {
            TarefaJira tarefa = new TarefaJira();
            switch (this) {

                case ACAO_TESTE_MANAGED_BEAN:
                    tarefa.setTempoEsperado("6 h");
                    break;
                case ACAO_IMPLEMENTACAO_MANAGED_BEAN:
                    tarefa.setTempoEsperado("12 h");
                    break;

                case ACAO_TESTES_AMBIENTE_DE_DADOS:
                    tarefa.setTempoEsperado("6 h");

                    break;
                case ACAO_BANCO_IMPLEMENTACAO_AMBIENTE_DE_DADOS:
                    tarefa.setTempoEsperado("12 h");
                    break;
                case ACAO_BANCO_TESTES_TIPOS:
                    tarefa.setTempoEsperado("12 h");
                    break;
                case ACAO_BANCO_IMPLEMENTACAO_TIPOS:
                    tarefa.setTempoEsperado("6 h");
                    break;
                case ACAO_TESTES_BANCO_CALCULO:
                    tarefa.setTempoEsperado("6 h");
                    break;
                case ACAO_TESTES_BANCO_LISTSTAS:
                    tarefa.setTempoEsperado("6 h");
                    break;
                case ACAO_IMPLEMENTAR_CONTROLLER:
                    tarefa.setTempoEsperado("12 h");
                    break;
                case ACAO_TESTE_CONTROLLER:
                    tarefa.setTempoEsperado("6 h");
                    break;
                case ACAO_CRIAR_FORMULARIO:
                    tarefa.setTempoEsperado("6 h");
                    break;
                case ACAO_CRIAR_FORMULARIO_COMPLEXO:
                    tarefa.setTempoEsperado("12 h");
                    break;
                case ACAO_SUB_CRIAR_CALCULO:
                    tarefa.setTempoEsperado("40 m");
                    break;
                case ACAO_SUB_CRIAR_LISTA:
                    tarefa.setTempoEsperado("40 m");
                    break;
                case ACAO_IMPLEMENTAR_CONTROLLER_COMPLEXO:
                    break;
                case ACAO_TESTE_CONTROLLER_COMPLEXO:
                    break;
                default:
                    throw new AssertionError(this.name());
            }

            return tarefa;
        }

        public TIPO_GRUPO_TAREFA getGrupoTarefaInssueJira() {
            switch (this) {
                case ACAO_TESTE_MANAGED_BEAN:
                case ACAO_IMPLEMENTACAO_MANAGED_BEAN:
                case ACAO_CRIAR_FORMULARIO:
                case ACAO_CRIAR_FORMULARIO_COMPLEXO:
                    return TIPO_GRUPO_TAREFA.TELA_GESTAO_ENTIDADE;

                case ACAO_TESTES_AMBIENTE_DE_DADOS:
                case ACAO_BANCO_IMPLEMENTACAO_TIPOS:
                case ACAO_BANCO_TESTES_TIPOS:
                case ACAO_BANCO_IMPLEMENTACAO_AMBIENTE_DE_DADOS:
                    return TIPO_GRUPO_TAREFA.ACAO_BANCO_AMBIENTE_E_ADEQUACAO;

                case ACAO_TESTES_BANCO_CALCULO:
                case ACAO_TESTES_BANCO_LISTSTAS:
                case ACAO_SUB_CRIAR_CALCULO:
                case ACAO_SUB_CRIAR_LISTA:
                    return TIPO_GRUPO_TAREFA.MODELAGEM_TABELA;

                case ACAO_IMPLEMENTAR_CONTROLLER:
                case ACAO_TESTE_CONTROLLER:
                case ACAO_IMPLEMENTAR_CONTROLLER_COMPLEXO:
                case ACAO_TESTE_CONTROLLER_COMPLEXO:
                    return TIPO_GRUPO_TAREFA.MODULO_CONTROLLER;
                default:
                    throw new AssertionError(this.name());

            }
        }

    }

    public static JiraRestClient criarConexaoJira(String pUsuario, String pSenha) {
        if (!SBCore.getUrlJira().startsWith("https")) {
            throw new UnsupportedOperationException("A URL Jira precisa ser uma conexão segura com https");
        }
        try {
            URI jiraServerUrl = new URI(SBCore.getUrlJira());
            final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUrl, pUsuario, pSenha);

            return restClient;
        } catch (URISyntaxException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Impossível conectar com o servidor Jira", ex);
            return null;
        }

    }

    private static BasicProject getProjetoPrincipal(JiraRestClient conexao) {
        List<BasicProject> lista = Lists.newArrayList(conexao.getProjectClient().getAllProjects().claim());
        if (lista.size() > 1) {
            throw new UnsupportedOperationException("Foram encontrados mais de um projeto vinculado a este usuário, impossívcel determinar o projeto ");
        }
        return lista.get(0);
    }

    private static IssueType getTipoIssueTarefa(JiraRestClient conexao) {

        List<IssueType> tiposAcoes = Lists.newArrayList(conexao.getMetadataClient().getIssueTypes().claim().iterator());
        for (IssueType tipoAcao : tiposAcoes) {
            if (tipoAcao.getName().contains("Task")) {
                return tipoAcao;
            }

        }
        throw new UnsupportedOperationException("Issue do tipo Task não encontrada");

    }

    private static BasicPriority getPrioridadeMaxiama(JiraRestClient conexao) {

        List<Priority> prioridades = Lists.newArrayList(conexao.getMetadataClient().getPriorities().claim().iterator());
        for (Priority prioridae : prioridades) {
            if (prioridae.getDescription().contains("Major")) {
                return prioridae;
            }
        }
        throw new UnsupportedOperationException("Alta Prioridade não encontrada");

    }

    private static IssueType getTipoIssueTarefaSecundaria(JiraRestClient conexao) {

        List<IssueType> tiposAcoes = Lists.newArrayList(conexao.getMetadataClient().getIssueTypes().claim().iterator());
        for (IssueType tipoAcao : tiposAcoes) {
            if (tipoAcao.getName().contains("Sub-Task")) {
                return tipoAcao;
            }

        }
        throw new UnsupportedOperationException("Issue do tipo Task não encontrada");

    }

    public static TarefaJira buildTarefaPrincipal(JiraRestClient conexao, TarefaJira pTarefa) {
        TarefaJira tarefaPrincipal = new TarefaJira();
        IssueType tipoTarefaPrincipal = getTipoIssueTarefa(conexao);
        switch (pTarefa.getTipoTarefa().getGrupoTarefaInssueJira()) {
            case TELA_GESTAO_ENTIDADE:
                String idGrupo = pTarefa.getCodigoTarefa();
                ItfAcaoDoSistema acaoPrincipal = null;
                if (pTarefa.getAcaoVinculada().isUmaAcaoGestaoDominio()) {
                    acaoPrincipal = pTarefa.getAcaoVinculada();
                } else {
                    acaoPrincipal = pTarefa.getAcaoVinculada().comoSecundaria().getAcaoPrincipal();
                }

                tarefaPrincipal.setAcaoVinculada(acaoPrincipal);
                tarefaPrincipal.setNomeTarefa("Implementar Requisito de Gestão para" + acaoPrincipal.getNomeAcao());
                tarefaPrincipal.setDescricaoTarefa("Um requisto de gestão de entidade, implementa uma Listagem da entidade com botões para todas as ações vinculadas a ela.");
                return tarefaPrincipal;

            case MODULO_CONTROLLER:
                break;
            case MODELAGEM_TABELA:
                break;
            case ACAO_BANCO_AMBIENTE_E_ADEQUACAO:
                break;
            default:
                throw new AssertionError(pTarefa.getTipoTarefa().getGrupoTarefaInssueJira().name());

        }
        return null;

    }

    public static String getIdTarefa(JiraRestClient conexao, TarefaJira pTarefa) {

        Promise<SearchResult> pesquisa = conexao.getSearchClient().searchJql("Summary ~ \"" + pTarefa.getNomeTarefa() + "\"");
        SearchResult resp = pesquisa.claim();
        List<Issue> resplist = Lists.newArrayList(resp.getIssues().iterator());
        if (resplist.size() > 0) {
            return resplist.get(0).getId().toString();
        }
        return null;
    }

    public static boolean criarTarefafasDaAcao(JiraRestClient conexao, TarefaJira pTarefa) {

        try {

            BasicProject projeto = getProjetoPrincipal(conexao);
            IssueType tipoTarefaSecundaria = getTipoIssueTarefaSecundaria(conexao);
            IssueType tipoTarefaPrincipal = getTipoIssueTarefa(conexao);
            BasicPriority prioridadeAlta = getPrioridadeMaxiama(conexao);

            TarefaJira tarefaPrincipal = buildTarefaPrincipal(conexao, pTarefa);
            String idTarefaPrincipal = getIdTarefa(conexao, tarefaPrincipal);
            IssueInput issuePrincipal = tarefaPrincipal.getIssueGrupoAcoes(projeto, tipoTarefaPrincipal, prioridadeAlta);
            BasicIssue tarefaPrincipalCriada = null;
            if (idTarefaPrincipal == null) {
                tarefaPrincipalCriada = conexao.getIssueClient().createIssue(issuePrincipal).claim();
            } else {
                conexao.getIssueClient().updateIssue(idTarefaPrincipal, issuePrincipal);
                tarefaPrincipalCriada = conexao.getIssueClient().getIssue(idTarefaPrincipal).claim();
            }
            String idTarefa = getIdTarefa(conexao, pTarefa);
            IssueInput issueTarefa = pTarefa.getIssue(projeto, tipoTarefaSecundaria, prioridadeAlta, tarefaPrincipalCriada);
            if (idTarefa == null) {
                BasicIssue tarefaCriada = conexao.getIssueClient().createIssue(issueTarefa).claim();
            } else {
                conexao.getIssueClient().updateIssue(idTarefa, issueTarefa);
            }
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro cadastrando tarefa", t);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();

                } catch (IOException ex) {
                    Logger.getLogger(UtilSBCoreJira.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;
    }

    public static TarefaJira getTarefaJiraAcaoDoSistema(TIPOS_DE_TAREFA_JIRA pTipoTarefaJira, ItfAcaoDoSistema pAcao) {
        TarefaJira tarefa = pTipoTarefaJira.getTarefaInssueJira();
        tarefa.setAcaoVinculada(pAcao);
        tarefa.setTipoTarefa(pTipoTarefaJira);
        switch (pTipoTarefaJira) {
            case ACAO_TESTE_MANAGED_BEAN:
                tarefa.setNomeTarefa("Criar Teste Managed Bean para " + pAcao.getNomeAcao());
                tarefa.setDescricaoTarefa("Simular todo o Fluxo nescessário para utilização do XHTML em" + pAcao.getNomeAcao());
                break;
            case ACAO_IMPLEMENTACAO_MANAGED_BEAN:
                tarefa.setNomeTarefa("Implementar Managed Bean." + pAcao.getNomeAcao());
                tarefa.setDescricaoTarefa("Implementar o código atendendo os requisitos do teste");
                break;
            case ACAO_CRIAR_FORMULARIO:
                tarefa.setNomeTarefa("Criar formulario " + pAcao.getNomeAcao());
                tarefa.setDescricaoTarefa("Criar o XHTML para " + pAcao.getDescricao() + " <br> não esqueça da responsividade,"
                        + " e da criatividade do usuário que clicará em tudo e ficará triste com erros, deixe o usuário ser feliz!");
                break;
            case ACAO_CRIAR_FORMULARIO_COMPLEXO:

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
            case ACAO_SUB_CRIAR_CALCULO:
                break;
            case ACAO_SUB_CRIAR_LISTA:
                break;
            case ACAO_IMPLEMENTAR_CONTROLLER:
                break;
            case ACAO_TESTE_CONTROLLER:
                break;
            case ACAO_IMPLEMENTAR_CONTROLLER_COMPLEXO:
                break;
            case ACAO_TESTE_CONTROLLER_COMPLEXO:
                break;
            default:
                throw new AssertionError("A regra de negocio para O tipo de tarefa:" + pTipoTarefaJira.name() + "não foi definda");

        }
        return tarefa;

    }

    public static TarefaJira getTarefaJiraDoBancoDeDados(TIPOS_DE_TAREFA_JIRA pTipoTarefaJira, Object pEntidade) {
        TarefaJira tarefa = pTipoTarefaJira.getTarefaInssueJira();
        return tarefa;
    }

    public static void buildAcoesJira() {

        MapaAcoesSistema mapaAcoes;
        List<Class> entidades = UtilSBPersistencia.getTodasEntidades();

        for (Class entidade : entidades) {
            System.out.println(entidade.getSimpleName());
        }

        for (ItfAcaoDoSistema acao : MapaAcoesSistema.getListaTodasAcoes()) {

            TIPOS_DE_TAREFA_JIRA[] tarefas = getTipoTarefaPorTipoAcao(acao.getTipoAcaoGenerica());

            for (TIPOS_DE_TAREFA_JIRA tipoTarefa : tarefas) {

                TarefaJira tarefa = getTarefaJiraAcaoDoSistema(tipoTarefa, acao);

            }

        }

    }

}
