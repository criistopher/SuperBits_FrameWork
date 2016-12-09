/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.util;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
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
    GERADOR_CODIGO,
    CRM,
    FINANCEIRO,
    ELEMENTAL,
    CRIADOR_COMPONENTE_VISUAL,
    SUPER_BITS_Fw_ADMIN_TOOLS,
    INTRANET_SOS;

    @Override
    public Projeto getRegistro() {
        Projeto novoProjeto = new Projeto();
        novoProjeto.setId(this.ordinal());
        switch (this) {
            case SISTEMA_SANTA_CLARA:
                novoProjeto.setNomeProjeto("Grupo Santa Clara");
                novoProjeto.setNomeComercial("Sistema de gestão funeraria, grupo Santa Clara");
                novoProjeto.setCliente(FabSBProjectClientes.SANTA_CLARA.getRegistro());

                break;
            case INTRANET_CASA_NOVA:
                novoProjeto.setCliente(FabSBProjectClientes.CASA_NOVA_MARKETING_DIGITAL.getRegistro());
                novoProjeto.setNomeProjeto("Intranet Marketing Digital");
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
                novoProjeto.setCliente(FabSBProjectClientes.SUPERBITS_OPEN_SOURCE.getRegistro());
                novoProjeto.setNome("Controle Usuario Basico");

                break;
            case MONITOR_REQUISITOS:
                novoProjeto.setCliente(FabSBProjectClientes.SUPER_BITS_CORP.getRegistro());
                novoProjeto.setNome("Monitor de Requisitos");
                novoProjeto.setDescricao("Monitora o desenvolvimento do sistema");
                break;
            case GERADOR_CODIGO:
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
            case ELEMENTAL:

                novoProjeto.setCliente(FabSBProjectClientes.GAME_SUPERBITS.getRegistro());
                novoProjeto.setNomeProjeto("elemental");
                novoProjeto.setNomeComercial("Elemental Game Card");

                break;
            case CRIADOR_COMPONENTE_VISUAL:
                novoProjeto.setCliente(FabSBProjectClientes.SUPERBITS_OPEN_SOURCE.getRegistro());
                novoProjeto.setNome("SB CRIADOR COMPONENTE");

                novoProjeto.setDescricao("Criador componente visual - FrameWork SuperBits");
                break;
            case SUPER_BITS_Fw_ADMIN_TOOLS:
                novoProjeto.setCliente(FabSBProjectClientes.SUPERBITS_OPEN_SOURCE.getRegistro());
                novoProjeto.setNome("SB_AdminTools");

                novoProjeto.setDescricao("Ferramentas de Administração do sistema- FrameWork SuperBits");
                break;
            case INTRANET_SOS:

                novoProjeto.setNomeProjeto("Intranet SOS");
                novoProjeto.setNomeComercial("SOS Ferramentas");
                novoProjeto.setCliente(FabSBProjectClientes.SOS.getRegistro());

                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoProjeto;
    }
}
