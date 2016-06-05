/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.BulkOperationResult;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Transition;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class SBCoreTest {

    private static final String JIRA_URL = "http://vipsol.atlassian.net";
    private static final String JIRA_ADMIN_USERNAME = "salviof@gmail.com";
    private static final String JIRA_ADMIN_PASSWORD = "123321";

    @Test
    public void coreTest() {

        String s = "Base";
        s.substring(0, 3);
        System.out.println(s);
        s.concat("ket");
        s += "ball";
        System.out.println(s);

        final URI jiraServerUri;

        SBCore.configurar(FabConfigCoreSBCore.DESENVOLVIMENTO.getConfigurador(), true);

        try {
            jiraServerUri = new URI("https://vipsol.atlassian.net");

            final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "salviof@gmail.com", "123321");
            try {

                List<BasicProject> projetos = Lists.newArrayList(restClient.getProjectClient().getAllProjects().claim().iterator());
                BasicProject projetoVip = projetos.get(0);
                for (BasicProject proj : projetos) {
                    System.out.println(proj.getName());

                }
                Promise<Iterable<IssueType>> teste = restClient.getMetadataClient().getIssueTypes();
                List<IssueType> tiposAcoes = Lists.newArrayList(teste.claim().iterator());
                IssueType tipoTeste = tiposAcoes.get(1);
                for (IssueType tipoTarefa : tiposAcoes) {
                    System.out.println("TipoTAREFA:" + tipoTarefa.getDescription());
                    System.out.println(tipoTarefa.getIconUri());

                }
                System.out.println("");

                // let's now print all issues matching a JQL string (here: all assigned issues)
                Promise<SearchResult> pesquisa = restClient.getSearchClient().searchJql("assignee is not EMPTY");
                SearchResult resp = pesquisa.claim();
                User cristopher = resp.getIssues().iterator().next().getAssignee();
                for (Issue tarefa : resp.getIssues()) {
                    System.out.println("tarefa" + tarefa.getDescription());
                    System.out.println(tarefa.getAssignee().getAvatarUri());
                }

                // build issue input
                final String summary = "Teste criação de Tarefa por API!";
                try {
                    // create
                    final IssueInput issueInput = new IssueInputBuilder(projetoVip, tipoTeste, summary).build();
                    final BasicIssue basicCreatedIssue = restClient.getIssueClient().createIssue(issueInput).claim();
                    System.out.println("CHAVE:" + basicCreatedIssue.getKey());
                } catch (Throwable t) {
                    System.out.println(t.getMessage());
                }
                // get issue and check if everything was set as we expected
            } finally {
                try {
                    // cleanup the restClient
                    restClient.close();
                } catch (IOException ex) {
                    Logger.getLogger(SBCoreTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(SBCoreTest.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private static Transition getTransitionByName(Iterable<Transition> transitions, String transitionName) {
        for (Transition transition : transitions) {
            if (transition.getName().equals(transitionName)) {
                return transition;
            }
        }
        return null;
    }

}
