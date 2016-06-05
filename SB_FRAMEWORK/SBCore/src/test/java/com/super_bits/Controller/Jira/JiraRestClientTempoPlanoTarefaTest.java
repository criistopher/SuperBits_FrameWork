/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Jira;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class JiraRestClientTempoPlanoTarefaTest {

    public JiraRestClientTempoPlanoTarefaTest() {
    }

    @Test
    public void testGetCurrentSession() {

        try {
            FabricaJiraClientExtendido fabricaExtendida = new FabricaJiraClientExtendido();
            URI jiraServerUri = new URI("https://vipsol.atlassian.net");
            JiraRestClientExtendido jiraExtendido = fabricaExtendida.getJiraClientExtendido(jiraServerUri, "salviof@gmail.com", "123321");
            JiraRestClientTempoPlanoTarefa testeTempoPLanoTarefa = jiraExtendido.getClientPlanTime();
            testeTempoPLanoTarefa.cadastrarPlanosDeTarefa();
        } catch (URISyntaxException ex) {
            Logger.getLogger(JiraRestClientTempoPlanoTarefaTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
