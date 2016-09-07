/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.fabricas;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabSegurancaGruposPadrao implements ItfFabrica {

    GRUPO_ADMINISTRADOR, GRUPO_USUARIOS, GRUPO_USUARIO_AVANCADO;

    @Override
    public GrupoUsuarioSB getRegistro() {
        GrupoUsuarioSB novoGrupo = new GrupoUsuarioSB();
        novoGrupo.setId(this.ordinal());
        novoGrupo.setNome(this.name());
        switch (this) {
            case GRUPO_ADMINISTRADOR:

                break;
            case GRUPO_USUARIOS:
                break;
            case GRUPO_USUARIO_AVANCADO:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoGrupo;
    }

}
