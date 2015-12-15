/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Salvio Furbino
 */
@Entity
public class AcessoSBWebPaginas extends EntidadeSimples {

    @Id
    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @Column(unique = true)
    private String recurso;
    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nomePagina;

    private boolean acessoLiberado;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "AcessosSBWebPaginas_UsuarioSB", joinColumns = {
        @JoinColumn(name = "AcessosSBWebPaginas_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "UsuarioSB_ID",
                        nullable = false, updatable = false)})
    private List<UsuarioSB> usuarios;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "AcessosSBWebPaginas_GrupoUsuarioSB", joinColumns = {
        @JoinColumn(name = "AcessosSBWebPaginas_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "GrupoUsuarioSB_ID",
                        nullable = false, updatable = false)})
    private List<GrupoUsuarioSB> grupoUsuarios;

    public AcessoSBWebPaginas() {
        super();
    }

    public AcessoSBWebPaginas(String pRecurso, String pNomePagina, Boolean pAcessoLiberado) {
        id = pRecurso.hashCode();
        recurso = pRecurso;
        nomePagina = pNomePagina;
        acessoLiberado = pAcessoLiberado;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public List<UsuarioSB> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioSB> usuarios) {
        this.usuarios = usuarios;
    }

    public List<GrupoUsuarioSB> getGrupoUsuarios() {
        return grupoUsuarios;
    }

    public void setGrupoUsuarios(List<GrupoUsuarioSB> grupoUsuarios) {
        this.grupoUsuarios = grupoUsuarios;
    }

    public String getNomePagina() {
        return nomePagina;
    }

    public void setNomePagina(String nomePagina) {
        this.nomePagina = nomePagina;
    }

    public boolean isAcessoLiberado() {
        return acessoLiberado;
    }

    public void setAcessoLiberado(boolean acessoLiberado) {
        this.acessoLiberado = acessoLiberado;
    }

}
