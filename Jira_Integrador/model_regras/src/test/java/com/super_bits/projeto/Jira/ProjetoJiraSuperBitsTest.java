/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.super_bits.InomeClienteI.JiraIntegradorModel.regras_de_negocio_e_controller.MODULOS.demonstracao_acesso_restrito.FabAcaoAcessoRestritoExemplo;
import com.super_bits.config.ConfigPersistenciaIntegrador;
import com.super_bits.config.FabConfiguracoesDeAmbienteModelExemplo;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.projeto.Jira.Jira.TarefaJira;
import org.junit.Test;

/**
 *
 * @author salvioF
 */
public class ProjetoJiraSuperBitsTest extends TesteJunitSBPersistencia {

    public ProjetoJiraSuperBitsTest() {
    }

    @Test
    public void testeCriacaoProjeto() {
        try {

            JiraRestClient conexao = UtilSBCoreJira.criarConexaoJira("salviof@gmail.com", "123321");
            Class entidade = ModuloAcaoSistema.class;
            for (UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA tipoTarefa : UtilSBCoreJira.getTiposTarefaPorEntidade(entidade)) {

                TarefaJira tarefaEntidade = UtilSBCoreJira.getTarefaJiraEntidade(tipoTarefa, entidade);
                UtilSBCoreJira.criarTarefafasDaAcao(conexao, tarefaEntidade);
                System.out.println(tarefaEntidade.getReferencia());
            }

            ProjetoJiraSuperBits testeOFicialProjeto = new ProjetoJiraSuperBits("salviof@gmail.com", "123321");
            //testeOFicialProjeto.buildAcoesJira();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        AcaoDoSistema acaoTeste3 = (AcaoDoSistema) FabAcaoAcessoRestritoExemplo.RECURSO_RESTRITO_FRM_EDITAR.getAcaoDoSistema();
        acaoTeste3.comoFormulario().getCampos();

        SBCore.configurar(FabConfiguracoesDeAmbienteModelExemplo.DESENVOLVIMENTO.getConfiguracao());
        SBPersistencia.configuraJPA(new ConfigPersistenciaIntegrador());

    }

}
