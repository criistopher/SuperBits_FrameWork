/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Jira;

import com.atlassian.jira.rest.client.api.ComponentRestClient;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.MetadataRestClient;
import com.atlassian.jira.rest.client.api.ProjectRestClient;
import com.atlassian.jira.rest.client.api.ProjectRolesRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.SessionRestClient;
import com.atlassian.jira.rest.client.api.UserRestClient;
import com.atlassian.jira.rest.client.api.VersionRestClient;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class JiraClientExtenssion implements JiraRestClient {

    private JiraRestClient jiraClient;

    public void cadastrarPlanejamentoDeTrabalho(List<String> teste) {

    }

    public JiraClientExtenssion(JiraRestClient pJiraClient) {
        jiraClient = pJiraClient;
    }

    @Override
    public IssueRestClient getIssueClient() {
        return jiraClient.getIssueClient();
    }

    @Override
    public SessionRestClient getSessionClient() {
        return jiraClient.getSessionClient();
    }

    @Override
    public UserRestClient getUserClient() {
        return jiraClient.getUserClient();
    }

    @Override
    public ProjectRestClient getProjectClient() {
        return jiraClient.getProjectClient();
    }

    @Override
    public ComponentRestClient getComponentClient() {
        return jiraClient.getComponentClient();
    }

    @Override
    public MetadataRestClient getMetadataClient() {
        return jiraClient.getMetadataClient();
    }

    @Override
    public SearchRestClient getSearchClient() {
        return jiraClient.getSearchClient();
    }

    @Override
    public VersionRestClient getVersionRestClient() {
        return jiraClient.getVersionRestClient();
    }

    @Override
    public ProjectRolesRestClient getProjectRolesRestClient() {
        return jiraClient.getProjectRolesRestClient();
    }

    @Override
    public void close() throws IOException {
        jiraClient.close();
    }

}
