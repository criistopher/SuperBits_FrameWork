/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Jira;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.httpclient.api.Request;
import com.atlassian.httpclient.api.ResponsePromise;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.SessionRestClient;
import com.atlassian.jira.rest.client.api.domain.Session;
import com.atlassian.jira.rest.client.internal.async.AbstractAsynchronousRestClient;
import com.atlassian.jira.rest.client.internal.json.SessionJsonParser;
import com.atlassian.jira.rest.client.internal.json.UserJsonParser;
import com.atlassian.util.concurrent.Promise;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author salvioF
 */
public class JiraRestClientTempoPlanoTarefa extends AbstractAsynchronousRestClient implements SessionRestClient {

    private final SessionJsonParser sessionJsonParser = new SessionJsonParser();
    private final URI serverUri;
    private final HttpClient cliente;
    private static final String JSON_CONTENT_TYPE = "application/json";

    public JiraRestClientTempoPlanoTarefa(final URI serverUri, final HttpClient client) {
        super(client);
        cliente = client;
        this.serverUri = serverUri;
    }

    @Override
    public Promise<Session> getCurrentSession() throws RestClientException {
        return getAndParse(UriBuilder.fromUri(serverUri).path("rest/tempo-planning/1/allocation").build(), sessionJsonParser);
    }

    public void cadastrarPlanosDeTarefa() {
        Request requisicao = cliente.newRequest(UriBuilder.fromUri(serverUri).path("rest/tempo-planning/1/allocation").build());
        requisicao.setContentType(JSON_CONTENT_TYPE);
        requisicao.trace();
        requisicao.get();

    }

}
