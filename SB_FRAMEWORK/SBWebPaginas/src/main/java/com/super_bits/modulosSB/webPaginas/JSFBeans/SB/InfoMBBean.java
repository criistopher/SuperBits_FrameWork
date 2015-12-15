/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB;

import java.io.Serializable;

/**
 *
 * @author Salvio
 */
public class InfoMBBean implements Serializable {

    String classe;
    String descricao;
    String exemplo;
    String chamada;

    public String getChamada() {
        return chamada;
    }

    public InfoMBBean(String pClasse, String pDescricao, String pExemplo, String pChamada) {
        classe = pClasse;
        descricao = pDescricao;
        exemplo = pExemplo;
        chamada = pChamada;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getExemplo() {
        return exemplo;
    }

    public void setExemplo(String exemplo) {
        this.exemplo = exemplo;
    }

}
