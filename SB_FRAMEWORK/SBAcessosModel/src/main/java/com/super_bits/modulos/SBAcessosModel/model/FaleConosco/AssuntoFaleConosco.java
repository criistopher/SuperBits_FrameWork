/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.FaleConosco;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Assunto"}, plural = "Assuntos")
public class AssuntoFaleConosco extends EntidadeNormal {

    @Id
    @InfoCampo(tipo = FabCampos.ID, label = "ID", descricao = "Identificação do assunto")
    private int id;

    @InfoCampo(tipo = FabCampos.REG_ATIVO_INATIVO, label = "Status", descricao = "Status do assunto(ativo/inativo)")
    @NotNull
    private boolean ativo;

    @NotNull
    @Column(length = 200, nullable = false)
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "assunto", descricao = "Assunto que será cadastrado para ser mostrado no fale conosco")
    private String assunto;

    @NotNull
    @Column(length = 200, nullable = false)
    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Remetente", descricao = "Remetente que utilizou o fale-conosco")
    private String remetente;

    @Temporal(javax.persistence.TemporalType.DATE)
    @InfoCampo(tipo = FabCampos.REG_DATAINSERCAO, label = "Data Inclusão", descricao = "Data de inclusão do assunto")
    private Date dataInclusao;

    @Temporal(javax.persistence.TemporalType.DATE)
    @InfoCampo(tipo = FabCampos.REG_DATAINSERCAO, label = "Data Alteração", descricao = "Data de alteração do assunto")
    private Date dataAlteracao;

    @NotNull
    @Column(length = 100, nullable = false)
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "Destinatário", descricao = "Destinatário do fale-conosco")
    private String destinatario;

    @InfoCampo(tipo = FabCampos.EMAIL, label = "E-mail", descricao = "E-mail do Destinatário")
    private String email;

    @NotNull
    @InfoCampo(tipo = FabCampos.QUANTIDADE)
    private Integer tempoResposta;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
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
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

}
