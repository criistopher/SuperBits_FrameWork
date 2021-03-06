package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@InfoClasse(tags = {"Localização"}, plural = "Localização")
public class Localizacao extends EntidadeSimples implements ItfLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Nome Logradouro")
    @Column(length = 150)
    @NotNull
    private String nome;

    @ManyToOne(targetEntity = Bairro.class)
    private Bairro bairro;

    @Transient
    private static List<UnidadeFederativa> unidadesFederativas;

    @InfoCampo(tipo = FabCampos.Longitude)
    private long longitude;

    @InfoCampo(tipo = FabCampos.Longitude)
    private long latitude;

    @InfoCampo(tipo = FabCampos.LCComplemeto)
    private String complemento;

    public Localizacao() {
        super();
        bairro = new Bairro();

    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Bairro getBairro() {
        return bairro;
    }

    public List<UnidadeFederativa> getUnidadesFederativas() {
        if (unidadesFederativas == null) {
            unidadesFederativas = FabUnidadesFederativas.getTodos();
        }
        return unidadesFederativas;
    }

    public List<ItfCidade> getCidadesDisponiveis() {
        if (getBairro().getCidade().getUnidadeFederativa() == null) {
            throw new UnsupportedOperationException("Não é possível listar as cidades disponíveis antes de selecionar um Estado");
        }
        return getBairro().getCidade().getUnidadeFederativa().getCidades();

    }

    public List<Bairro> getBairrosDisponiveis() {
        if (getBairro().getCidade() == null) {
            throw new UnsupportedOperationException("Não é possível listar as cidades disponíveis antes de selecionar um Estado");
        }
        return (List) getBairro().getCidade().getBairros();

    }

    @Override
    public long getLongitude() {

        return this.longitude;

    }

    @Override
    public long getLatitude() {

        return this.latitude;

    }

    @Override
    public void setLatitude(long pLatitude) {

        this.latitude = pLatitude;

    }

    @Override
    public void setLongitude(long pLongitude) {

        this.longitude = pLongitude;

    }

    @Override
    public void setBairro(ItfBairro pBairro) {

        this.bairro = (Bairro) pBairro;

    }

    @Override
    public String getNome() {

        return this.nome;

    }

    @Override
    public void setNome(String pNome) {

        this.nome = pNome;

    }

    @Override
    public String getComplemento() {

        return this.complemento;

    }

    @Override
    public void setComplemento(String pComplemento) {

        this.complemento = pComplemento;

    }

}
