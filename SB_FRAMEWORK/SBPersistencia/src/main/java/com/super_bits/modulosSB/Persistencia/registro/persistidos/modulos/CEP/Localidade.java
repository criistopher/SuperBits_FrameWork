package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeNormal;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfLocalidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The persistent class for the localidade database table.
 *
 */
@Entity
public class Localidade extends EntidadeNormal implements ItfLocalidade {

    public static Localidade grandeBH = new Localidade(1, "Grande BH", "Belo horizonte, Contagem, Betim e Região");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;
    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nome;

    //bi-directional many-to-one association to Cidade
    @OneToMany(mappedBy = "localidade")
    private List<Cidade> cidades;

    public Localidade() {
        super(Localidade.class);
    }

    public Localidade(int pID, String pNome, String pDescricao) {
        super(Localidade.class);

        id = pID;
        nome = pNome;
        descricao = pDescricao;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cidade> getCidades() {
        return this.cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Cidade addCidade(Cidade cidade) {
        getCidades().add(cidade);
        cidade.setLocalidade(this);

        return cidade;
    }

    public Cidade removeCidade(Cidade cidade) {
        getCidades().remove(cidade);
        cidade.setLocalidade(null);

        return cidade;
    }

}
