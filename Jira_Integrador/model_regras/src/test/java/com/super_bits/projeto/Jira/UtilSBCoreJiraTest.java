/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.config.ConfigPersistenciaIntegrador;
import com.super_bits.config.FabConfiguracoesDeAmbienteModelExemplo;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreJiraTest {

    public UtilSBCoreJiraTest() {
    }

    @Test
    public void testCriarTarefafasDaAcao() {
    }

    @Test
    public void testCriarTarefasBancoDeDados() {
    }

    @Test
    public void testBuildAcoesJira() {
        SBCore.configurar(FabConfiguracoesDeAmbienteModelExemplo.DESENVOLVIMENTO.getConfiguracao());
        SBPersistencia.configuraJPA(new ConfigPersistenciaIntegrador());
        UtilSBCoreJira.buildAcoesJira();

    }

}
