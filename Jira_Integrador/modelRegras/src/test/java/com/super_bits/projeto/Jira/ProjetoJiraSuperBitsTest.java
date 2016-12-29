/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.super_bits.InomeClienteI.JiraIntegradorModel.regras_de_negocio_e_controller.MODULOS.demonstracao_acesso_restrito.FabAcaoAcessoRestritoExemplo;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.projeto.Jira.Jira.MapaTarefasProjeto;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import com.super_bits.vip.superCompras.Model.Produto.Produto;
import com.super_bits.vip.superCompras.configAppp.ConfigPersistenciaSuperCompras;
import com.super_bits.vip.superCompras.configAppp.ConfiguradorCoreSuperKomprasModel;
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

            ProjetoJiraSuperBits testeOFicialProjeto = new ProjetoJiraSuperBits("salviof@gmail.com", "123321");
            //testeOFicialProjeto.buildAcoesJira();

            testeOFicialProjeto.buildAcoesJira();
            //       testeOFicialProjeto.atualizaAcoesJira();
            for (TarefaSuperBits tarefa : MapaTarefasProjeto.getTarefasDaTabela(Produto.class)) {
                System.out.println("Tarefa:" + tarefa.getTarefaJiraOrigem().getReferencia());
                for (Worklog trampo : testeOFicialProjeto.getWorkLogDaTarefa(tarefa)) {
                    System.out.println("Trampo por" + trampo.getAuthor().getDisplayName());
                    System.out.println("Minutos:" + trampo.getMinutesSpent());
                }
            }
            //    testeOFicialProjeto.atualizaAcoesJira();
            //testeOFicialProjeto.buil4dAcoesJira();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        AcaoDoSistema acaoTeste3 = (AcaoDoSistema) FabAcaoAcessoRestritoExemplo.RECURSO_RESTRITO_FRM_EDITAR.getAcaoDoSistema();
        acaoTeste3.getComoFormulario().getCampos();

        SBCore.configurar(new ConfiguradorCoreSuperKomprasModel(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistenciaSuperCompras());

    }

}
