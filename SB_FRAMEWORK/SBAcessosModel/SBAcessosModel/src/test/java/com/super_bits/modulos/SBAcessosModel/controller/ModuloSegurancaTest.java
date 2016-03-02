/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabSegurancaGruposPadrao;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;
import config.ConfigPersistenciaTestesAcesso;
import config.FabConfiguracoesCoreAcessosModel;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class ModuloSegurancaTest extends TesteJunitSBPersistencia {

    public ModuloSegurancaTest() {
    }

    @Test
    public void testListarAcoesDoGrupo() {
    }

    @Test
    public void testGrupoAlterarStatus() {
    }

    @Test
    public void testGrupoDeUsuariosSalvarAlteracoes() {
    }

    @Test
    public void testUsuarioAlterarStatus() {
        ItfControleDeSessao controleDeSessao = SBCore.getControleDeSessao();
        ItfSessao sessao = controleDeSessao.getSessaoAtual();
        ItfUsuario usuario = sessao.getUsuario();
        controleDeSessao.logarEmailESenha("root@superBits.com", "senh@Screta");
        System.out.println("Usuário logado=" + usuario.getNome());

        ModuloSeguranca.grupoAlterarStatus(FabSegurancaGruposPadrao.GRUPO_ADMINISTRADOR.getRegistro());
    }

    @Test
    public void testUsuarioPersistirAlteracoes() {
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesCoreAcessosModel.DESENVOLVIMENTO.getConfigurador());
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }

}
