/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.icones;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author salvioF
 */
public class IconeSistema extends ItemSimples implements itfIcone {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    private String descricao;
    private String tagHtml;

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

    @Override
    public String getTagHtml() {
        return tagHtml;
    }

    public void setTagHtml(String tagHtml) {
        this.tagHtml = tagHtml;
    }

}
