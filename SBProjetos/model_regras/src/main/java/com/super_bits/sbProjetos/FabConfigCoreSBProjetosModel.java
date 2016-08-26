/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.configSBFW.acessos.ConfigAcessos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguradorCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.sbProjetos.Model.FabSBProjectClientes;
import com.super_bits.sbProjetos.controller.getaoProjeto.FabAcaoGestaoProjetos;

/**
 *
 * @author salvioF
 */
public enum FabConfigCoreSBProjetosModel {

    DESENVOLVIMENTO, HOMOLOGACAO, PRODUCAO;

    public ItfConfiguradorCore getConfiguracao() {

        ConfigCoreCustomizavel novaConfig = new ConfigCoreCustomizavel(
                ConfigCoreCustomizavel.TIPOS_CONFIG.CORE_DEVELOPER_PADRAO,
                FabSBProjectClientes.SUPERBITS.getRegistro().getNome(),
                "model_regras", "SBProjetos", ConfigAcessos.class, FabAcaoGestaoProjetos.class
        );

        switch (this) {
            case DESENVOLVIMENTO:
                novaConfig.setEstadoAPP(SBCore.ESTADO_APP.DESENVOLVIMENTO);
                break;
            case HOMOLOGACAO:
                novaConfig.setEstadoAPP(SBCore.ESTADO_APP.DESENVOLVIMENTO);
                break;
            case PRODUCAO:
                novaConfig.setEstadoAPP(SBCore.ESTADO_APP.DESENVOLVIMENTO);
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novaConfig;
    }

}
