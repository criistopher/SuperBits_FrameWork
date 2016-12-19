/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.configSBFW.acessos;

import com.super_bits.InomeClienteI.InomeProjetoI.regras_de_negocio_e_controller.MODULOS.demonstracao_acesso_restrito.FabAcaoAcessoRestritoExemplo;
import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 * @author salvioF
 */
public abstract class UtilInomeProjetoIGlobalVar {

    public static Class<? extends ItfFabricaAcoes>[] pAcoesDoSistema() {
        return new Class[]{FabAcaoSeguranca.class, FabAcaoAcessoRestritoExemplo.class};
    }

}
