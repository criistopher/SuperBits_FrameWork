/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel;

import aux.ConfiguradorCoreSBAcessosModelTestes;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import config.ConfigPersistenciaTestesAcesso;

import javax.persistence.EntityManager;
import org.junit.Test;

/**
 *
 *
 * @author desenvolvedor
 */
public class TesteAcessosModelPadrao extends TesteJunitSBPersistencia {

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreSBAcessosModelTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        //  SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }

    public void teste() {
        EntityManager em = getEmTeste();
        ControllerAbstratoSBPersistencia.reloadAcessos();
        //  UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(FabAcaoSeguranca.class, getEmTeste(), UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ORDEM_DE_DECLARCAO);
    }

}
