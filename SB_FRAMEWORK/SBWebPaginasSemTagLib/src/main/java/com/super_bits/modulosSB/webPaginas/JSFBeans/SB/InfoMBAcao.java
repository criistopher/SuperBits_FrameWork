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
public class InfoMBAcao implements Serializable {

    private final String chamada;
    private final String descricao;

    public String getChamada() {
        return chamada;
    }

    public String getDescricao() {
        return descricao;
    }

    public InfoMBAcao(String pChamada, String pDescricao) {
        chamada = pChamada;
        descricao = pDescricao;
    }

}
