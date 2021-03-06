/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Salvio
 */
@Entity
@InfoClasse(tags = {"Grupos de Usuário"}, plural = "Grupos de Usuários")
public class GrupoUsuarioSB extends EntidadeSimples implements ItfGrupoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Nome")
    @NotNull
    @Column(unique = true)
    private String nome;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "Descrição")
    @NotNull
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "modulos_grupo", uniqueConstraints = @UniqueConstraint(columnNames = {"grupo_id", "modulo_id"}),
            joinColumns = @JoinColumn(name = "grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "modulo_id")
    )
    private List<ModuloAcaoSistema> modulos;

    private boolean tipoGrupoNativo;

    private String XhtmlPaginaInicial = "/site/homeAdministrador.xhtml";
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_grupo",
            uniqueConstraints = @UniqueConstraint(name = "usuarioDuplicado", columnNames = {"usuario_id", "grupo_id"}),
            joinColumns = {
                @JoinColumn(name = "grupo_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "usuario_id",
                        nullable = false, updatable = false)}
    )
    private List<UsuarioSB> usuarios;

    @InfoCampo(tipo = FabCampos.REG_DATAALTERACAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAlteracao;
    @InfoCampo(tipo = FabCampos.REG_DATAINSERCAO)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInsersao;

    @InfoCampo(tipo = FabCampos.REG_USUARIO_INSERCAO)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioInsercao;
    @InfoCampo(tipo = FabCampos.REG_USUARIO_ALTERACAO)
    @ManyToOne(targetEntity = UsuarioSB.class)
    private UsuarioSB usuarioAlteracao;

    @InfoCampo(tipo = FabCampos.VERDADEIRO_FALSO)
    private boolean ativo = true;
    @Temporal(TemporalType.DATE)
    private Date dataHoraCriacao;

    public GrupoUsuarioSB() {
        super();
        modulos = new ArrayList<>();
    }

    public void adcionaUsuario(ItfUsuario pUsuario) {
        usuarios.add((UsuarioSB) pUsuario);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public List<ItfUsuario> getUsuarios() {
        return (List) usuarios;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    @PrePersist
    public void configuracoesInsert() {
        dataHoraCriacao = new Date();
    }

    @Override
    public String getXhtmlPaginaInicial() {
        return XhtmlPaginaInicial;
    }

    public void setXhtmlPaginaInicial(String XhtmlPaginaInicial) {
        this.XhtmlPaginaInicial = XhtmlPaginaInicial;
    }

    public boolean isTipoGrupoNativo() {
        return tipoGrupoNativo;
    }

    public void setTipoGrupoNativo(boolean tipoGrupoNativo) {
        this.tipoGrupoNativo = tipoGrupoNativo;
    }

    public List<ModuloAcaoSistema> getModulos() {
        return modulos;
    }

    public void adicionarModulo(ModuloAcaoSistema modulo) {
        if (!modulos.contains(modulo)) {
            modulos.add(modulo);
        }
    }

    public ModuloAcaoSistema getModuloPrincipal() {
        return getModulos().get(0);
    }

}
