/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ModuloPrevisto extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    private final List<PrevisaoGestaoEntidade> gestoesPrevistas;
    private final List<PrevisaoEntidade> entidadesPrevistas;

    public ModuloPrevisto(List<PrevisaoGestaoEntidade> gestoesPrevistas, List<PrevisaoEntidade> entidadesPrevistas) {
        this.gestoesPrevistas = gestoesPrevistas;
        this.entidadesPrevistas = entidadesPrevistas;
        nome = getModuloAssociado().getNome();
        id = nome.hashCode();
    }

    public List<PrevisaoGestaoEntidade> getGestoesPrevistas() {
        return gestoesPrevistas;
    }

    public List<PrevisaoEntidade> getEntidadesPrevistas() {
        return entidadesPrevistas;
    }

    public final ItfModuloAcaoSistema getModuloAssociado() {
        PrevisaoGestaoEntidade gestaoQualquer = getGestoesPrevistas().get(0);
        if (gestaoQualquer == null) {
            return FabModulosSistemaSB.PAGINAS_DO_SISTEMA.getRegistro();
        } else {
            return gestaoQualquer.getModulo();
        }
    }

}
