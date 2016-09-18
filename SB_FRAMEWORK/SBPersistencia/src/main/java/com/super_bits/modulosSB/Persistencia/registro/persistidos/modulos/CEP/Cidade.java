package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocalidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the cidade database table.
 *
 */
@Entity
@InfoClasse(tags = {"Cidade"}, plural = "Cidades")
public class Cidade extends EntidadeNormal implements Serializable, ItfCidade {

    private static final long serialVersionUID = 1L;

    @Id
    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Cidade", descricao = "Nome da Cidade")
    @NotNull
    @Column(nullable = false)
    private String nome;

    @InfoCampo(tipo = FabCampos.OBJETO_DE_UMA_LISTA, label = "Estado", descricao = "Estado(ex:MG)")
    @ManyToOne(targetEntity = UnidadeFederativa.class)
    private UnidadeFederativa unidadeFederativa;

    //bi-directional many-to-one association to Bairro
    @InfoCampo(tipo = FabCampos.LISTA_OBJETOS, label = "Bairros", descricao = "Bairros da Cidade")
    @OneToMany(mappedBy = "cidade")
    private List<Bairro> bairros;

    //bi-directional many-to-one association to Localidade
    @InfoCampo(tipo = FabCampos.OBJETO_DE_UMA_LISTA, label = "Localidade", descricao = "Localização no Estado")
    @ManyToOne(targetEntity = Localidade.class)
    @JoinColumn(name = "id_Localidade")
    private Localidade localidade;

    @NotNull
    @InfoCampo(tipo = FabCampos.REG_ATIVO_INATIVO, label = "Status", descricao = "Status da Cidade")
    private boolean ativo;

    @Temporal(javax.persistence.TemporalType.DATE)
    @InfoCampo(tipo = FabCampos.REG_DATAALTERACAO, label = "Data alteração", descricao = "Data de alteração da cidade")
    private Date dataAlteracao = new Date();

    @Temporal(javax.persistence.TemporalType.DATE)
    @InfoCampo(tipo = FabCampos.REG_DATAINSERCAO, label = "Data criação", descricao = "Data de Criação da Cidade")
    private Date dataCriacao = new Date();

    public Cidade() {
        super();
        unidadeFederativa = new UnidadeFederativa();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public List<ItfBairro> getBairros() {
        return (List) this.bairros;
    }

    public void setBairros(List<Bairro> bairros) {
        this.bairros = bairros;
    }

    public Bairro addBairro(Bairro bairro) {
        getBairros().add(bairro);
        bairro.setCidade(this);

        return bairro;
    }

    public Bairro removeBairro(Bairro bairro) {
        getBairros().remove(bairro);
        bairro.setCidade(null);

        return bairro;
    }

    @Override
    public Localidade getLocalidade() {
        return this.localidade;
    }

    @Override
    public void setLocalidade(ItfLocalidade localidade) {
        this.localidade = (Localidade) localidade;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    @Override
    public String getEstadoPontoNomeCidade() {

        return getUnidadeFederativa() + getNome();

    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
