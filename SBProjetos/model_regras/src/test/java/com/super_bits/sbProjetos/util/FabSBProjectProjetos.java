/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.util;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import com.super_bits.sbProjetos.Model.FabSBProjectClientes;
import com.super_bits.sbProjetos.Model.Projeto;

/**
 *
 * @author salvioF
 */
public enum FabSBProjectProjetos implements ItfFabrica {

    SISTEMA_SANTA_CLARA, INTRANET_CASA_NOVA, SUPER_KOMPRAS, GESTAO_ARQUIVOS;

    @Override
    public Projeto getRegistro() {
        Projeto novoProjeto = new Projeto();
        novoProjeto.setId(this.ordinal());
        switch (this) {
            case SISTEMA_SANTA_CLARA:
                novoProjeto.setNomeProjeto("Sistema Santa Clara");
                novoProjeto.setNomeComercial("Sistema de gestão funeraria, Santa Clara");
                novoProjeto.setCliente(FabSBProjectClientes.SANTA_CLARA.getRegistro());

                break;
            case INTRANET_CASA_NOVA:
                novoProjeto.setCliente(FabSBProjectClientes.CASA_NOVA_MARKETING_DIGITAL.getRegistro());
                novoProjeto.setNomeProjeto("Intranet Marketing Digiral ");
                novoProjeto.setNomeComercial("Sistema Casa Nova, Comunicação e softwares");
                break;
            case SUPER_KOMPRAS:
                novoProjeto.setCliente(FabSBProjectClientes.VIP.getRegistro());
                novoProjeto.setNomeProjeto("superkompras");
                novoProjeto.setNomeComercial("Super Kompras");
                break;
            case GESTAO_ARQUIVOS:
                novoProjeto.setCliente(FabSBProjectClientes.AQUA_QUALIT.getRegistro());
                novoProjeto.setNomeProjeto("Arquivos Compartilhados");
                novoProjeto.setNomeComercial("Sistema de gestão de Arquivos compartilhados");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoProjeto;
    }
}
