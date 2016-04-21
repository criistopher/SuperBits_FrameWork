/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.FaleConosco;

import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoComunicacaoPadrao;
import com.super_bits.modulos.SBAcessosModel.controller.InfoAcaoComunicacao;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cristopher
 */
@Entity()
@DiscriminatorColumn(name = "tipoFaleConosco")
@InfoClasse(tags = {"Fale Conosco"}, icone = "fa fa-bullhorn")
@InfoAcaoComunicacao(acao = FabAcaoComunicacaoPadrao.ASSUNTO_FALE_CONOSCO_CTR_SALVAR_MERGE)
public class FaleConosco extends EntidadeNormal {

    public FaleConosco() {
        super(FaleConosco.class);
    }

    @Id
    @InfoCampo(tipo = FabCampos.ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    @InfoCampo(tipo = FabCampos.REG_DATAINSERCAO)
    private Date dataInclusao;

    @InfoCampo(tipo = FabCampos.REG_USUARIO_ALTERACAO)
    @Temporal(TemporalType.DATE)
    private Date dataResposta;

    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String remetente;

    private String tipoFaleConosco;

    @InfoCampo(tipo = FabCampos.EMAIL)
    private String email;

    @InfoCampo(tipo = FabCampos.EMAIL)
    private String emailDestinatario;

    @InfoCampo(tipo = FabCampos.QUANTIDADE)
    private int numeroCampanha;

    @InfoCampo(tipo = FabCampos.QUANTIDADE)
    private int numeroPedido;

    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String assunto;

    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String mensagem;

    @InfoCampo(tipo = FabCampos.RET_ATIVO_INATIVO)
    private boolean ativo;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public int getNumeroCampanha() {
        return numeroCampanha;
    }

    public void setNumeroCampanha(int numeroCampanha) {
        this.numeroCampanha = numeroCampanha;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipoFaleConosco() {
        return tipoFaleConosco;
    }

    public void setTipoFaleConosco(String tipoFaleConosco) {
        this.tipoFaleConosco = tipoFaleConosco;
    }

}
