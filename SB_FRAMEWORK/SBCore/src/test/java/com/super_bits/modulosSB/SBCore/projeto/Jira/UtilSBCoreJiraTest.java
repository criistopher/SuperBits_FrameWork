/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.projeto.Jira;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabConfigCoreSBCore;
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

        SBCore.configurar(FabConfigCoreSBCore.DESENVOLVIMENTO.getConfigurador(), true);

        UtilSBCoreJira.criarTarefafasDaAcao(null);
    }

    @Test
    public void testCriarTarefasBancoDeDados() {
    }

}
