/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import config.ConfigPersistenciaTestesAcesso;
import config.FabConfiguracoesCoreAcessosModel;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class PermissaoSBTest extends TesteJunitSBPersistencia {

    public PermissaoSBTest() {
    }

    @Test
    public void testGetId() {

        PermissaoSB pm = new PermissaoSB();
        List<CaminhoCampoReflexao> teste = pm.getEntidadesVinculadas();
        for (CaminhoCampoReflexao cm : teste) {
            System.out.println(cm.getCaminhoString());
        }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesCoreAcessosModel.DESENVOLVIMENTO.getConfigurador());
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }

}
