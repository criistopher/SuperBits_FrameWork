/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Salvio
 */
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "acesso_id"}))
@Entity
@InfoClasse(tags = "Usuários com acesso negado", plural = "Usuários com acesso negado")
public class Negado_Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioSB usuario;

    @ManyToOne
    private PermissaoSB acesso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioSB getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSB usuario) {
        this.usuario = usuario;
    }

    public PermissaoSB getAcesso() {
        return acesso;
    }

    public void setAcesso(PermissaoSB acesso) {
        this.acesso = acesso;
    }
}
