package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfBairro;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the bairro database table.
 *
 */
@Entity
@InfoClasse(tags = {"Bairro"}, plural = "Bairros")
public class Bairro extends EntidadeSimples implements ItfBairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Bairro")
    @NotNull
    private String nome;

    @InfoCampo(tipo = FabCampos.LCCidade)
    @ManyToOne(targetEntity = Cidade.class)
    @JoinColumn(name = "id_Cidade")
    private Cidade cidade;

    private String coordenadas;

    public Bairro() {
        cidade = new Cidade();
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

    @Override
    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public List<Long> getCordenadas() {
        throw new UnsupportedOperationException("Ainda não foi implementado");

    }

}
