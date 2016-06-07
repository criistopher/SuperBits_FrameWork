/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.config.ConfigPersistenciaIntegrador;
import com.super_bits.config.FabConfiguracoesDeAmbienteModelExemplo;
import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.MensagemProgramador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;
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

            AcaoDoSistema acaoTeste = (AcaoDoSistema) FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema().comoSecundaria().getAcaoPrincipal();

            AcaoDoSistema acaoTeste2 = (AcaoDoSistema) FabAcaoSeguranca.USUARIO_CTR_ALTERAR_STATUS.getAcaoDoSistema().comoSecundaria().getAcaoPrincipal();

            //       TarefaJira tarefa = UtilSBCoreJira.getTarefaJiraAcaoDoSistema(UtilSBCoreJira.TIPOS_DE_TAREFA_JIRA.ACAO_IMPLEMENTACAO_MANAGED_BEAN, FabAcaoProjetoSB.PROJETO_GERENCIAR_MB.getAcaoDoSistema());
            UTILSBCoreDesktopApp.showMessage(new MensagemProgramador("Ação principal" + acaoTeste2));
            UTILSBCoreDesktopApp.showMessage(new MensagemProgramador("Ação principal" + acaoTeste));
            ProjetoJiraSuperBits testeOFicialProjeto = new ProjetoJiraSuperBits("salviof@gmail.com", "123321");
            testeOFicialProjeto.buildAcoesJira();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        AcaoDoSistema acaoTeste3 = (AcaoDoSistema) FabAcaoSeguranca.ACAO_CTR_INTERNA_DO_SISTEMA.getAcaoDoSistema();
        FabTipoAcaoSistemaGenerica acaoGenerica = acaoTeste3.getTipoAcaoGenerica();

        SBCore.configurar(FabConfiguracoesDeAmbienteModelExemplo.DESENVOLVIMENTO.getConfiguracao());
        SBPersistencia.configuraJPA(new ConfigPersistenciaIntegrador());

    }

}
