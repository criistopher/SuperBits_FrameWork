/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos;

import com.super_bits.sbProjetos.configAppp.ConfigPersistenciaInomeProjetoI;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreDeveloper;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Controller.ControllerAppAbstratoSBCore;

/**
 *
 * Funções estaticas de acesso ao banco de dados da camada Controladora
 *
 * @author Salvio
 */
public class AcoesInomeProjetoI extends ControllerAppAbstratoSBCore {

    public static void configCoreEPersistenciaBasicos() {
        SBCore.configurar(new ConfigCoreDeveloper());
        SBPersistencia.configuraJPA(new ConfigPersistenciaInomeProjetoI());
    }

}
