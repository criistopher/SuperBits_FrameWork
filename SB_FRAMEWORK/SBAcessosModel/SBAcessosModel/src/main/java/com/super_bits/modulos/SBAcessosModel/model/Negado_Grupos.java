/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Salvio
 */
@Entity
public class Negado_Grupos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private GrupoUsuarioSB grupo;

    @ManyToOne(fetch = FetchType.EAGER)
    private PermissaoSB acesso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GrupoUsuarioSB getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoUsuarioSB grupo) {
        this.grupo = grupo;
    }

    public PermissaoSB getAcesso() {
        return acesso;
    }

    public void setAcesso(PermissaoSB acesso) {
        this.acesso = acesso;
    }
}
