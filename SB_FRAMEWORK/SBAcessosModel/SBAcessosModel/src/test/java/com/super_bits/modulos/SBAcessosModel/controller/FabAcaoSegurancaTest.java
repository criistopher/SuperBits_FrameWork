/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import aux.FabConfigCoreSBSBAcessosModel;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.testesFW.TesteAcaoDoSistema;
import config.ConfigPersistenciaTestesAcesso;

import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class FabAcaoSegurancaTest extends TesteAcaoDoSistema {

    public FabAcaoSegurancaTest() {
        super(false);
    }

    @Test
    public void testGetRegistro() {
        testesBasicosDeAcoes(FabAcaoSeguranca.class);
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfigCoreSBSBAcessosModel.DESENVOLVIMENTO.getConfigurador(), true);
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());

    }

}
