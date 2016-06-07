/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Jira;

import com.atlassian.httpclient.api.HttpClient;
import com.atlassian.jira.rest.client.internal.async.AbstractAsynchronousRestClient;
import com.atlassian.jira.rest.client.internal.json.UserJsonParser;
import java.net.URI;

/**
 *
 * @author salvioF
 */
public class JiraRestClientTempoPlanoTarefa extends AbstractAsynchronousRestClient {

    private static final String USER_URI_PREFIX = "user";
    private final UserJsonParser userJsonParser = new UserJsonParser();

    public JiraRestClientTempoPlanoTarefa(final URI baseUri, final HttpClient client) {
        super(client);
    }

}
