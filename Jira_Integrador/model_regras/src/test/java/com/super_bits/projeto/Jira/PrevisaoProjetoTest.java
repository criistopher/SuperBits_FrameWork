/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.config.ConfigPersistenciaIntegrador;
import com.super_bits.config.FabConfiguracoesDeAmbienteModelExemplo;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.projeto.Jira.Jira.MapaTarefasProjeto;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class PrevisaoProjetoTest extends TesteJunitSBPersistencia {

    public PrevisaoProjetoTest() {
    }

    @Test
    public void testCalcularValores() {
        System.out.println("Existem " + MapaTarefasProjeto.getTodasTarefas().size() + " tarefas cadastradas");
        PrevisaoProjeto novaPrevisao = new PrevisaoProjeto(MapaTarefasProjeto.getTodasTarefas());

        for (ModuloPrevisto mp : novaPrevisao.getModuloPrevistosPorModulo().values()) {
            for (PrevisaoEntidade previsao : mp.getEntidadesPrevistas()) {
                System.out.println("Tarefas da entidade:" + previsao.getEntidadeVinculada());

                // for (TarefaSuperBits tf : previsao.getTarefasVinculadas()) {
                //   System.out.println("Tarefa de entidade :" + tf.getTarefaJiraOrigem().getNomeTarefa());
                //}
            }
            for (PrevisaoGestaoEntidade gestao : mp.getGestoesPrevistas()) {
                System.out.println("Tarefas da gestao" + gestao.getGestao().getNome());

                for (TarefaSuperBits tf : gestao.getTarefasVinculadas()) {
                    System.out.println("Tarefa de gestao:" + tf.getTarefaJiraOrigem().getAcaoVinculada().getNomeAcao());
                }
            }
        }

        novaPrevisao.calcularValores();
        System.out.println("Horas Total proxima Versao " + novaPrevisao.getCustoProjetoProximaVersao().getHorasTotalAlanlistaTelas());
        System.out.println("Horas total tudo" + novaPrevisao.getCustoProjetoCompleto().getHorasTotal());

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesDeAmbienteModelExemplo.DESENVOLVIMENTO.getConfiguracao(), false);
        SBPersistencia.configuraJPA(new ConfigPersistenciaIntegrador());
    }

}
