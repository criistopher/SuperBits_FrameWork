/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ItfFabricaModulo;

/**
 *
 * @author desenvolvedor
 */
public enum FabModulosSistemaSB implements ItfFabricaModulo {
    SEGURANCA, COMUNICACAO, PAGINAS_DO_SISTEMA, DOCUMENTOS_INTRANET;

    @Override
    public ModuloAcaoSistema getModulo() {

        ModuloAcaoSistema modulo = new ModuloAcaoSistema();
        modulo.setUmModuloNativo(true);
        switch (this) {
            case SEGURANCA:
                modulo.setNome("Segurança");
                modulo.setDescricao("Define as configurações de segurança do sistema");
                modulo.setIconeDaClasse("fa fa-key");
                modulo.setUmModuloNativo(true);
                break;
            case COMUNICACAO:
                modulo.setNome("Comunicação");
                modulo.setDescricao("Módulo de  comunicação básico do sistema");
                modulo.setIconeDaClasse("fa fa-comments-o");
                modulo.setUmModuloNativo(true);
                break;
            case PAGINAS_DO_SISTEMA:
                modulo.setNome("Paginas do Sistema");
                modulo.setDescricao("Paginas Genericas do sistema, como Login, acesso negado, e outros");
                modulo.setIconeDaClasse("fa fa-cogs");
                modulo.setUmModuloNativo(true);
                break;
            case DOCUMENTOS_INTRANET:
                modulo.setNome("Documentos em nuvem");
                modulo.setDescricao("Documentos disponibilizados para acesso via nuvem");
                modulo.setIconeDaClasse("fa fa-file-text-o");
                modulo.setUmModuloNativo(true);
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
