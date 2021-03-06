/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.Model;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author sfurbino
 */
public enum FabSBProjectClientes implements ItfFabrica {
    SUPERBITS_OPEN_SOURCE,
    SUPER_BITS_CORP,
    FACULDADE_JAVA,
    VIP,
    GUIASE,
    SANTA_CLARA,
    AQUA_QUALIT,
    CASA_NOVA_MARKETING_DIGITAL,
    MINAS_DRILL,
    ACOMAR,
    GAME_SUPERBITS,
    SOS;

    public static String SERVIDOR_RELEASE_OFICIAL_SUPER_BITS = "ssh://git@consultoriaguiase.com.br/home/git/gitServer/release";
    public static String SERVIDOR_SOURCE_OFICIAL_SUPER_BITS = "ssh://git@consultoriaguiase.com.br/home/git/gitServer/source";
    public static String SERVIDOR_SOURCE_OFICIAL_SUPER_BITS_ORG = "https://github.com/salviof/";
    public static String SERVIDOR_SOURCE_GAME_SUPERBITS_ELEMENTAL_CRISTOPHER = "https://github.com/CristopherAmaral";

    @Override
    public Cliente getRegistro() {

        Cliente novoCliente = new Cliente();
        novoCliente.setId(this.ordinal());
        novoCliente.setServicorGitRelease(SERVIDOR_RELEASE_OFICIAL_SUPER_BITS);
        novoCliente.setServidorGitCodigoFonte(SERVIDOR_SOURCE_OFICIAL_SUPER_BITS);

        switch (this) {
            case SUPERBITS_OPEN_SOURCE:
                novoCliente.setNome("Super Bits");
                novoCliente.setServidorGitCodigoFonte(SERVIDOR_SOURCE_OFICIAL_SUPER_BITS_ORG);

                break;
            case VIP:
                novoCliente.setNome("vip");
                novoCliente.setServicorGitRelease(SERVIDOR_RELEASE_OFICIAL_SUPER_BITS);
                novoCliente.setServidorGitCodigoFonte("https://bitbucket.org/superkompras");
                break;
            case GUIASE:
                novoCliente.setNome("Guia-se");
                break;
            case SANTA_CLARA:
                novoCliente.setNome("Santa Clara");
                break;
            case AQUA_QUALIT:
                novoCliente.setNome("Aqua Quality");
                break;
            case CASA_NOVA_MARKETING_DIGITAL:
                novoCliente.setNome("Casa Nova");

                break;
            case MINAS_DRILL:
                novoCliente.setNome("Minas drill");
                break;
            case ACOMAR:
                novoCliente.setNome("Açomar");
                break;
            case FACULDADE_JAVA:
                novoCliente.setNome("Faculdade Java");

                break;
            case SUPER_BITS_CORP:
                novoCliente.setNome("superBitsCorp");

                break;
            case GAME_SUPERBITS:

                novoCliente.setNome("GAME_SUPERBITS_ELEMENTAL");
                novoCliente.setServicorGitRelease(SERVIDOR_RELEASE_OFICIAL_SUPER_BITS);
                novoCliente.setServidorGitCodigoFonte(SERVIDOR_SOURCE_GAME_SUPERBITS_ELEMENTAL_CRISTOPHER);

                break;
            case SOS:
                novoCliente.setNome("SOS");
                // USANDO DADOS PADRÃO
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoCliente;

    }
}
