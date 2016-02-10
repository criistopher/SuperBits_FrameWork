package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Logradouro extends EntidadeSimples {

    @Id
    @GeneratedValue
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    @Column(length = 150)
    private String descricao;
    @ManyToOne(targetEntity = Bairro.class)
    private Bairro bairro;

    @Transient
    private static List<UnidadeFederativa> unidadesFederativas;

    public Logradouro() {

    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public List<UnidadeFederativa> getUnidadesFederativas() {
        if (unidadesFederativas == null) {
            unidadesFederativas = FabUnidadesFederativas.getTodos();
        }
        return unidadesFederativas;
    }

    public List<Cidade> getCidadesDisponiveis() {
        if (getBairro().getCidade().getUnidadeFederativa() == null) {
            throw new UnsupportedOperationException("Não é possível listar as cidades disponíveis antes de selecionar um Estado");
        }
        return getBairro().getCidade().getUnidadeFederativa().getCidades();

    }

    public List<Bairro> getBairrosDisponiveis() {
        if (getBairro().getCidade() == null) {
            throw new UnsupportedOperationException("Não é possível listar as cidades disponíveis antes de selecionar um Estado");
        }
        return getBairro().getCidade().getBairros();

    }

}
