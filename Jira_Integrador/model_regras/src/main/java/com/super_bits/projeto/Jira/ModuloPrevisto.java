/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ModuloPrevisto {

    private final List<PrevisaoGestaoEntidade> gestoesPrevistas;
    private final List<PrevisaoEntidade> entidadesPrevistas;

    public ModuloPrevisto(List<PrevisaoGestaoEntidade> gestoesPrevistas, List<PrevisaoEntidade> entidadesPrevistas) {
        this.gestoesPrevistas = gestoesPrevistas;
        this.entidadesPrevistas = entidadesPrevistas;
    }

    public List<PrevisaoGestaoEntidade> getGestoesPrevistas() {
        return gestoesPrevistas;
    }

    public List<PrevisaoEntidade> getEntidadesPrevistas() {
        return entidadesPrevistas;
    }

}
