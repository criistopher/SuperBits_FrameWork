/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.Model;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Salvio
 */
@Entity
public class Cliente extends EntidadeSimples implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    private String servidorGitCodigoFonte;
    private String servidorGitHomologacao;
    private String servicorGitRelease;

    @OneToMany(mappedBy = "cliente")
    private List<Projeto> projetos;

    public Cliente() {
        super();
        projetos = new ArrayList<>();
    }

    public Cliente(int id, String nome, List<Projeto> projetos) {
        this();
        this.id = id;
        this.nome = nome;
        this.projetos = projetos;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public String getServidorGitCodigoFonte() {
        return servidorGitCodigoFonte;
    }

    public void setServidorGitCodigoFonte(String servidorGitCodigoFonte) {
        this.servidorGitCodigoFonte = servidorGitCodigoFonte;
    }

    public String getServidorGitHomologacao() {
        return servidorGitHomologacao;
    }

    public void setServidorGitHomologacao(String servidorGitHomologacao) {
        this.servidorGitHomologacao = servidorGitHomologacao;
    }

    public String getServicorGitRelease() {
        return servicorGitRelease;
    }

    public void setServicorGitRelease(String servicorGitRelease) {
        this.servicorGitRelease = servicorGitRelease;
    }

}
