/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Controller.Interfaces.acoes;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfAcaoSessaoCategoria extends ItfAcaoDoSistema {

    public List<ItfAcaoDoSistema> getAcoes();

    public List<ItfAcaoSessaoCategoria> getSessoes();

}
