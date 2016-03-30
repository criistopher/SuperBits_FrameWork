/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.Controller.anotacoes.AcaoGenerica;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.ConfigPersistenciaExemplo;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import config.ConfigPersistenciaTestesAcesso;
import config.FabConfiguracoesCoreAcessosModel;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

        List<AcaoGenerica> acoes = UtilSBPersistencia.getListaTodos(AcaoGenerica.class, getEmTeste());
        ControllerAppAbstratoSBCore.reloadAcessos();
        System.out.println("Acoes encontradas=" + acoes.size());
        for (AcaoGenerica ac : acoes) {
            System.out.println(ac.getNomeAcao());
        }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesCoreAcessosModel.DESENVOLVIMENTO.getConfigurador());
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }
}
