/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.InomeClienteI.InomeProjetoI.configAppp;

import com.super_bits.sbProjetos.configAppp.ConfigPersistenciaInomeProjetoI;
import com.super_bits.sbProjetos.configAppp.ConfigCoreInomeProjetoI;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreDeveloper;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class ConfigPersistenciaInomeProjetoITest {

    public ConfigPersistenciaInomeProjetoITest() {
    }

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreInomeProjetoI());
        SBCore.configurar(new ConfigCoreDeveloper());
        SBPersistencia.configuraJPA(new ConfigPersistenciaInomeProjetoI());
    }

    /**
     * Test of bancoPrincipal method, of class ConfigPersistenciaInomeProjetoI.
     */
    @Test
    public void testBancoPrincipal() {

    }

}