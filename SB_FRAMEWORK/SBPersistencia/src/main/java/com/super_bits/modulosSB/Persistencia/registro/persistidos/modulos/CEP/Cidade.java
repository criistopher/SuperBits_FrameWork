package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfLocalidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the cidade database table.
 *
 */
@Entity
public class Cidade extends EntidadeSimples implements Serializable, ItfCidade {

    private static final long serialVersionUID = 1L;

    @Id
    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO, label = "Cidade")
    @NotNull
    @Column(nullable = false)
    private String nome;

    @InfoCampo(tipo = FabCampos.LOOKUP)
    @ManyToOne(targetEntity = UnidadeFederativa.class)
    private UnidadeFederativa unidadeFederativa;

    //bi-directional many-to-one association to Bairro
    @OneToMany(mappedBy = "cidade")
    private List<Bairro> bairros;

    //bi-directional many-to-one association to Localidade
    @ManyToOne(targetEntity = Localidade.class)
    @JoinColumn(name = "id_Localidade")
    private Localidade localidade;

    @NotNull
    @InfoCampo(tipo = FabCampos.VERDADEIRO_FALSO, label = "Status")
    private boolean ativo;

    public Cidade() {
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

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
