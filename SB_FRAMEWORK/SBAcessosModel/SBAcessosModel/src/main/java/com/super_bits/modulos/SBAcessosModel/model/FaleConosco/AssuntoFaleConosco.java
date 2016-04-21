/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.FaleConosco;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Assunto"})
public class AssuntoFaleConosco extends EntidadeNormal {

    @Id
    private int id;

    @InfoCampo(tipo = FabCampos.VERDADEIRO_FALSO)
    @NotNull
    private boolean ativo;

    @NotNull
    @Column(length = 200, nullable = false)
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String assunto;

    @NotNull
    @Column(length = 100, nullable = false)
    private String destinatario;

    @InfoCampo(tipo = FabCampos.EMAIL)
    private String email;

    @NotNull
    private Integer tempoResposta;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Integer getTempoResposta() {
        return tempoResposta;
    }

    public void setTempoResposta(Integer tempoResposta) {
        this.tempoResposta = tempoResposta;
    }

    public AssuntoFaleConosco() {
        super(AssuntoFaleConosco.class);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
