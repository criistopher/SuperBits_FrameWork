/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.testesFW.TesteAcoesDoSistema;
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
public class FabAcaoSegurancaTest extends TesteAcoesDoSistema {

    public FabAcaoSegurancaTest() {
        super(false);
    }

    @Test
    public void testGetRegistro() {
        testesBasicosDeAcoes(FabAcaoSeguranca.class);
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesCoreAcessosModel.DESENVOLVIMENTO.getConfigurador());
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());

    }

}
