/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.modulo.ItfFabricaModulo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones.FabIconeFontAwesome;

/**
 *
 * @author desenvolvedor
 */
public enum FabModulosSistemaSB implements ItfFabricaModulo {

    SEGURANCA,
    COMUNICACAO,
    PAGINAS_DO_SISTEMA,
    DOCUMENTOS_INTRANET_GESTAO,
    DOCUMENTOS_INTRANET_EXTERNO,
    ADMIN_TOOLS;

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
            case DOCUMENTOS_INTRANET_GESTAO:
                modulo.setNome("Gestão Documentos em Núvem");
                modulo.setDescricao("Gestão de arquivos compatilhados em núvem");
                modulo.setIconeDaClasse("fa fa-file-text-o");
                modulo.setUmModuloNativo(true);
                break;
            case DOCUMENTOS_INTRANET_EXTERNO:
                modulo.setNome("Documentos Compartilhados");
                modulo.setDescricao("Acesso a documentos compartilhados em núvem");
                modulo.setUmModuloNativo(true);
                modulo.setIconeDaClasse(FabIconeFontAwesome.ESCRITORIO_DOCUMENTO_TEXTO.getIcone().getTagHtml());
                break;
            case ADMIN_TOOLS:
                modulo.setNome("SB Admin Tools");
                modulo.setDescricao("Ferramentas de administração do projeto");
                modulo.setUmModuloNativo(true);
                modulo.setIconeDaClasse("fa fa-suitcase");

                break;
            default:
                throw new AssertionError(this.name());

        }
        modulo.setId(this.toString().hashCode());

        return modulo;
    }

    @Override
    public ModuloAcaoSistema getRegistro() {
        return getModulo();
    }
}
