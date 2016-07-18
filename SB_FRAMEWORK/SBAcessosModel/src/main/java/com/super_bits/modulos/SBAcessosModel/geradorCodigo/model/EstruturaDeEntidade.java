/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class EstruturaDeEntidade {

    private String nomeEntidade;

    private List<EstruturaCampo> campos;

    private List<EstruturaDeEntidade> muitosParaUm;

    private List<EstruturaDeEntidade> umParaMuitos;

    private List<String> tags;

    private String plural;

    public EstruturaDeEntidade() {
        campos = new ArrayList<>();

    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public List<EstruturaCampo> getCampos() {
        return campos;
    }

    public void setCampos(List<EstruturaCampo> campos) {
        this.campos = campos;
    }

    public List<EstruturaDeEntidade> getMuitosParaUm() {
        return muitosParaUm;
    }

    public void setMuitosParaUm(List<EstruturaDeEntidade> muitosParaUm) {
        this.muitosParaUm = muitosParaUm;
    }

    public List<EstruturaDeEntidade> getUmParaMuitos() {
        return umParaMuitos;
    }

    public void setUmParaMuitos(List<EstruturaDeEntidade> umParaMuitos) {
        this.umParaMuitos = umParaMuitos;
    }

}
