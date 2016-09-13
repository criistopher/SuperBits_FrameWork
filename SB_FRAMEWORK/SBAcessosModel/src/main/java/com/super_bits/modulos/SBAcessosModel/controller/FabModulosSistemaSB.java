/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.Controller.Interfaces.modulo.ItfFabricaModulo;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;

/**
 *
 * @author desenvolvedor
 */
public enum FabModulosSistemaSB implements ItfFabricaModulo {
    SEGURANCA, COMUNICACAO, PAGINAS_DO_SISTEMA;

    @Override
    public ModuloAcaoSistema getModulo() {

        ModuloAcaoSistema modulo = new ModuloAcaoSistema();
        modulo.setUmModuloNativo(true);
        switch (this) {
            case SEGURANCA:
                modulo.setNome("Segurança");
                modulo.setDescricao("Define as configurações de segurança do sistema");
                modulo.setIconeDaClasse("fa fa-key");
                break;
            case COMUNICACAO:
                modulo.setNome("Comunicação");
                modulo.setDescricao("Módulo de  comunicação básico do sistema");
                modulo.setIconeDaClasse("fa fa-comments-o");
                break;
            case PAGINAS_DO_SISTEMA:
                modulo.setNome("Paginas do Sistema");
                modulo.setDescricao("Paginas Genericas do sistema, como Login, acesso negado, e outros");
                modulo.setIconeDaClasse("fa fa-cogs");
                break;
            default:
                throw new AssertionError(this.name());

        }

        return modulo;
    }

    @Override
    public ModuloAcaoSistema getRegistro() {
        return getModulo();
    }
}
