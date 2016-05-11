/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import aux.FabConfigCoreSBSBAcessosModel;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import config.ConfigPersistenciaTestesAcesso;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class AcaoDoSistemaTest extends TesteJunitSBPersistencia {

    public AcaoDoSistemaTest() {
    }

    @Before
    public void setUp() {

    }

    @Test
    public void testGetNomeAcao() {

        //  List<AcaoGenerica> acoes = UtilSBPersistencia.getListaTodos(AcaoGenerica.class, getEmTeste());
        // ControllerAppAbstratoSBCore.reloadAcessos();
        //  System.out.println("Acoes encontradas=" + acoes.size());
        //   for (AcaoGenerica ac : acoes) {
        //       System.out.println(ac.getNomeAcao());
        //   }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfigCoreSBSBAcessosModel.DESENVOLVIMENTO.getConfigurador());
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }
}
