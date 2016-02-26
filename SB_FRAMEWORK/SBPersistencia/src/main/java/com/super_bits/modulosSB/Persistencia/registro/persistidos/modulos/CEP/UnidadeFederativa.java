/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
@Entity
public class UnidadeFederativa extends EntidadeSimples implements ItfUnidadeFederativa {

    @Id
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME_LONGO, label = "Estado")
    @NotNull
    private String nome;
    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO, label = "Estado")
    private String UF;
    @OneToMany(targetEntity = Cidade.class)
    private List<Cidade> cidades;

    public UnidadeFederativa() {

    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    @Override
    public List<ItfCidade> getCidades() {
        return (List) cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

}
