/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import aux.FabConfigCoreSBSBAcessosModel;
import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import config.ConfigPersistenciaTestesAcesso;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class PermissaoSBTest extends TesteJunitSBPersistencia {

    public PermissaoSBTest() {
    }

    @Test
    public void testgetEntidadesVinvuladas() {

        AcaoDoSistema acaoDoSistema = FabAcaoSeguranca.GRUPO_FRM_NOVO.getAcaoDoSistema();

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfigCoreSBSBAcessosModel.DESENVOLVIMENTO.getConfigurador());
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }

}
