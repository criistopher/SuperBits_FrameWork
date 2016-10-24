/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 * \
 *
 * @author salvioF
 */
public class TipoProfissional extends ItemSimples implements ItfBeanSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    @InfoCampo(tipo = FabCampos.AAA_DESCRITIVO)
    private String descricao;
    private int valorHoraTecnica;
    private final FabTipoProfissional fabrica;

    public TipoProfissional(FabTipoProfissional fabrica) {
        id = fabrica.ordinal();
        this.fabrica = fabrica;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValorHoraTecnica() {
        return valorHoraTecnica;
    }

    public void setValorHoraTecnica(int valorHoraTecnica) {
        this.valorHoraTecnica = valorHoraTecnica;
    }

    public FabTipoProfissional getFabrica() {
        return fabrica;
    }

    @Override
    public String getImgPequena() {
        String pastaImagens = "/resources/img/profissionais/";
        return pastaImagens + this.getFabrica().name() + ".svg";
    }

}