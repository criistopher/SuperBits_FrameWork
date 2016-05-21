/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.fabricas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

/**
 *
 * @author desenvolvedor
 */
public class ProjetoAtual {

    private String nomeProjeto;
    private String nomeCliente;

    public String getNomeProjeto() {
        return SBCore.getNomeProjeto();
    }

    public String getNomeCliente() {
        return SBCore.getCliente();
    }

}
