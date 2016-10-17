/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ItfFabricaModulo;

/**
 *
 * @author desenvolvedor
 */
public enum FabModulosTestes implements ItfFabricaModulo {
    MODULO_TESTE;

    @Override
    public ItfModuloAcaoSistema getModulo() {
        ModuloAcaoSistema modulo = new ModuloAcaoSistema();
        modulo.setNome("Modulo teste");
        modulo.setDescricao("Modulo criado para testes");
        return modulo;
    }

    @Override
    public Object getRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
