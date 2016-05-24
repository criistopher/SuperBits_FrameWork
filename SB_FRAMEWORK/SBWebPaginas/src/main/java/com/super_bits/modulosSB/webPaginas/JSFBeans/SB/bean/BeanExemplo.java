/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.bean;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeContatoCorporativo;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localizacao;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 30/12/2015
 * @version 1.0
 *
 */
public class BeanExemplo
        extends EntidadeContatoCorporativo {

    @InfoCampo(tipo = FabCampos.ID)
    @NotNull
    private int id;

    @InfoCampo(tipo = FabCampos.EMAIL)
    @NotNull
    private String email = "emailteste@teste.com";

    @InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL)
    @NotNull
    private String telefone = "313017-7334";

    @InfoCampo(tipo = FabCampos.TELEFONE_CELULAR)
    @NotNull
    private String celular;

    @InfoCampo(tipo = FabCampos.TELEFONE_FIXO_INTERNACIONAL)
    @NotNull
    private String telInternacional;

    @InfoCampo(tipo = FabCampos.CPF)
    @NotNull
    private String cpf;

    @InfoCampo(tipo = FabCampos.CNPJ)
    @NotNull
    private String cnpj;

    @InfoCampo(tipo = FabCampos.INSCRICAO_ESTADUAL)
    @NotNull
    private String inscricaoEstadual;

    @InfoCampo(tipo = FabCampos.INSCRIACAO_MUNICIPAL)
    @NotNull
    private String inscricaoMunicipal;

    @InfoCampo(tipo = FabCampos.COR)
    @NotNull
    private String cor;

    @InfoCampo(Mask = "")
    @NotNull
    private String MascaraEspecial;

    @InfoCampo(tipo = FabCampos.HTML)
    @NotNull
    private String HTML;

    @InfoCampo(tipo = FabCampos.SITE, obrigatorio = true)
    @NotNull
    private String site;

    @InfoCampo(tipo = FabCampos.URL)
    private String url;

    @InfoCampo(tipo = FabCampos.SENHA)
    @NotNull
    private String senha;

    @NotNull
    @InfoCampo(tipo = FabCampos.QUANTIDADE)
    private int quantidade;

    @NotNull
    @InfoCampo(tipo = FabCampos.PERCENTUAL)
    private int porcentagem;

    @NotNull
    @InfoCampo(tipo = FabCampos.MOEDA_REAL)
    private Double dinheiro;

    @NotNull
    @InfoCampo(tipo = FabCampos.REG_DATAALTERACAO)
    private Date dataAlteracao;

    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String apelido;

    @NotNull
    @InfoCampo(tipo = FabCampos.AAA_NOME_LONGO)
    private String nome;

    @InfoCampo(tipo = FabCampos.LCComplemeto)
    @NotNull
    private String complemento;

    @InfoCampo(tipo = FabCampos.LCCEP, obrigatorio = true)
    @NotNull
    private String cep;

    @InfoCampo(tipo = FabCampos.DATAHORA)
    @NotNull
    private String dataHora;

    @InfoCampo(tipo = FabCampos.ARQUIVO_DE_ENTIDADE)
    @NotNull
    private String arquivo;

    @InfoCampo(tipo = FabCampos.LC_LOCALIZACAO)
    @NotNull
    private Localizacao localizacao;

    public BeanExemplo() {
        super(BeanExemplo.class);

        apelido = "testeeeeeeee";

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelInternacional() {
        return telInternacional;
    }

    public void setTelInternacional(String telInternacional) {
        this.telInternacional = telInternacional;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMascaraEspecial() {
        return MascaraEspecial;
    }

    public void setMascaraEspecial(String MascaraEspecial) {
        this.MascaraEspecial = MascaraEspecial;
    }

    public String getHTML() {
        return HTML;
    }

    public void setHTML(String HTML) {
        this.HTML = HTML;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Double getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

}
