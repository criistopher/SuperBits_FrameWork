/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.fabricas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
@InfoClasse(tags = {"Projeto Atual"}, plural = "Projetos Atuais")
public class ProjetoAtual extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nomeProjeto;
    private String nomeCliente;

    public String getNomeProjeto() {
        return SBCore.getNomeProjeto();
    }

    public String getNomeCliente() {
        return SBCore.getInfoAplicacao().getCliente();
    }

}
