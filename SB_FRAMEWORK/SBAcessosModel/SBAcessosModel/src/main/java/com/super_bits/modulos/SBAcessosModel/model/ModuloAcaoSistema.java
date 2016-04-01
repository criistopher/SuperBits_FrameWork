/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * Representa o modulo da aplicação
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 19/12/2015
 * @version 1.0
 *
 */
@Entity
public class ModuloAcaoSistema extends EntidadeSimples implements ItfModuloAcaoSistema {

    @Id
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;
    @OneToMany(mappedBy = "modulo")
    private List<AcaoDoSistema> acoes;
    @Temporal(TemporalType.DATE)
    private Date dataHoraCriacao;

    
    
    @Transient
    private List<AcaoDoSistema> selecaoAcoes;

    public List<AcaoDoSistema> getSelecaoAcoes() {
        return selecaoAcoes;
    }

    public ModuloAcaoSistema() {
    super();
    }

    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return
     */
    public List<ItfAcaoDoSistema> getAcoes() {
        return (List) acoes;
    }

    public void setAcoes(List<AcaoDoSistema> acoes) {
        this.acoes = acoes;
    }

    @PrePersist
    public void configuracoesInsert() {
        dataHoraCriacao = new Date();
    }

    @PreUpdate
    public void configuracoesMerge() {
        if (dataHoraCriacao == null) {
            dataHoraCriacao = new Date();
        }
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public void setSelecaoAcoes(List<AcaoDoSistema> SelecaoAcoes) {
        this.selecaoAcoes = SelecaoAcoes;
    }

}
