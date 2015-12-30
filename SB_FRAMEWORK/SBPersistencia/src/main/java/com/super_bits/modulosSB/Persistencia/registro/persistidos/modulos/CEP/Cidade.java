package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * The persistent class for the cidade database table.
 *
 */
@Entity
public class Cidade extends EntidadeSimples implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nome;

    //bi-directional many-to-one association to Bairro
    @OneToMany(mappedBy = "cidade")
    private List<Bairro> bairros;

    //bi-directional many-to-one association to Localidade
    @ManyToOne
    @JoinColumn(name = "id_Localidade")
    private Localidade localidade;

    public Cidade() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Bairro> getBairros() {
        return this.bairros;
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

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

}
