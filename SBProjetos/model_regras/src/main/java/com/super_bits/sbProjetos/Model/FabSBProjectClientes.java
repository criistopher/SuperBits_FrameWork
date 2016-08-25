/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.Model;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabSBProjectClientes implements ItfFabrica {
    SUPERBITS, VIP, GUIASE, SANTA_CLARA, AQUA_QUALIT;

    public static String SERVIDOR_OFICIAL_SUPER_BITS = "git@consultoriaguiase.com.br";

    @Override
    public Cliente getRegistro() {

        Cliente novoCliente = new Cliente();
        novoCliente.setId(this.ordinal());
        switch (this) {
            case SUPERBITS:
                novoCliente.setNome("Super Bits");
                novoCliente.setServicorGitRelease("");
                novoCliente.setServidorGitHomologacao("");
                break;
            case VIP:
                novoCliente.setNome("Vip");
                novoCliente.setServicorGitRelease("");
                novoCliente.setServidorGitHomologacao("");
                break;
            case GUIASE:
                novoCliente.setNome("Guia-se");
                novoCliente.setServicorGitRelease("");
                novoCliente.setServidorGitHomologacao("");
                break;
            case SANTA_CLARA:
                novoCliente.setNome("Santa Clara");
                novoCliente.setServicorGitRelease("");
                novoCliente.setServidorGitHomologacao("");
                break;
            case AQUA_QUALIT:
                novoCliente.setNome("Aqua Quality");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoCliente;

    }
}
