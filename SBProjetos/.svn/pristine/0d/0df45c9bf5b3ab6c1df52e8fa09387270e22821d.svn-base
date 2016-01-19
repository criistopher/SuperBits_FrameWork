/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.Model;

import com.super_bits.modulosSB.Persistencia.anotacoes.InfoCampo;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Salvio
 */
@Entity
public class PreRequisito extends EntidadeSimples {

    @Id
    @InfoCampo(tipo = InfoCampo.TC.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @InfoCampo(tipo = InfoCampo.TC.NOME_CURTO)
    private String nomePreRequisito;
    private String justificativa;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataSolicitacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataLimite;
    @ManyToOne
    private Projeto projeto;
    private Boolean atendido = false;

    public Boolean getAtendido() {
        return atendido;
    }

    public void setAtendido(Boolean atendido) {
        this.atendido = atendido;
    }

    public String getNomePreRequisito() {
        return nomePreRequisito;
    }

    public void setNomePreRequisito(String nomePreRequisito) {
        this.nomePreRequisito = nomePreRequisito;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

}
