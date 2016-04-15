/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import config.ConfigPersistenciaTestesAcesso;
import config.FabConfiguracoesCoreAcessosModel;
import java.util.List;
import org.junit.Test;
import static com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos.getTodosCamposAnotadosComManyToOne;

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
        List<CaminhoCampoReflexao> cps = getTodosCamposAnotadosComManyToOne(PermissaoSB.class, "PermissaoSB.class");
        List<CaminhoCampoReflexao> teste = acaoDoSistema.getEntidadesVinculadas();
        System.out.println("Inicio print Entidades Vinculadas");
        for (CaminhoCampoReflexao cm : teste) {
            System.out.println(cm.getCaminhoString());

            for (String parteCaminho : cm.getPartesCaminho()) {
                System.out.println("ParteCaminho:" + parteCaminho);
            }
            System.out.println("Valor do campo=" + acaoDoSistema.getItemPorCaminhoCampo(cm).getNomeCurto());
        }
        System.out.println("Fim print Entidades Vinculadas");

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfiguracoesCoreAcessosModel.DESENVOLVIMENTO.getConfigurador());
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());
    }

}
