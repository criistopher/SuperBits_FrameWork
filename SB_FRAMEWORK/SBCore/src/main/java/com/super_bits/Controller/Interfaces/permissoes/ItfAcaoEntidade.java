/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Controller.Interfaces.permissoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public interface ItfAcaoEntidade extends ItfAcaoDoSistema {

    public Class getClasseRelacionada();

    public void setClasseRelacionada(Class classeRelacionada);

}
