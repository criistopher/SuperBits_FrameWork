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

    SISTEMA_SANTA_CLARA,
    INTRANET_CASA_NOVA,
    INTRANET_AQUA_QUALITY,
    SUPER_KOMPRAS,
    GESTAO_ARQUIVOS,
    CONTROLE_USUARIOS_BASICO,
    MONITOR_REQUISITOS,
    CRIADOR_CODIGO,
    CRM,
    FINANCEIRO,;

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
                novoProjeto.setCliente(FabSBProjectClientes.SUPER_BITS_CORP.getRegistro());
                novoProjeto.setNomeProjeto("Arquivos Compartilhados");
                novoProjeto.setNomeComercial("Sistema de gestão de Arquivos compartilhados");

                break;
            case CONTROLE_USUARIOS_BASICO:
                novoProjeto.setCliente(FabSBProjectClientes.SUPER_BITS_CORP.getRegistro());
                novoProjeto.setNome("Controle usuário Basico");

                break;
            case MONITOR_REQUISITOS:
                novoProjeto.setCliente(FabSBProjectClientes.SUPER_BITS_CORP.getRegistro());
                novoProjeto.setNome("Monitor de Requisitos");
                novoProjeto.setDescricao("Monitora o desenvolvimento do sistema");
                break;
            case CRIADOR_CODIGO:
                novoProjeto.setCliente(FabSBProjectClientes.SUPER_BITS_CORP.getRegistro());
                novoProjeto.setNome("SBUsuariosPermissao");
                novoProjeto.setDescricao("Cria, Fabricas de Ações, Estruturas de Tabela, e gera código referente");
                break;
            case CRM:
                novoProjeto.setCliente(FabSBProjectClientes.SUPER_BITS_CORP.getRegistro());
                novoProjeto.setNome("SB_CRM");
                novoProjeto.setDescricao("Controle de CRM");
                break;
            case FINANCEIRO:
                novoProjeto.setCliente(FabSBProjectClientes.SUPER_BITS_CORP.getRegistro());
                novoProjeto.setNome("SB_Financeiro");
                novoProjeto.setDescricao("Financeiro");
                break;
            case INTRANET_AQUA_QUALITY:
                novoProjeto.setCliente(FabSBProjectClientes.AQUA_QUALIT.getRegistro());
                novoProjeto.setNomeProjeto("Intranet Aqua Quality");
                novoProjeto.setNomeComercial("Sistema de gestão de Arquivos compartilhados");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoProjeto;
    }
}
