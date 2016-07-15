/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.controller;

import com.super_bits.Controller.Interfaces.permissoes.ItfPermissao;
import java.util.List;

/**
 *
 * @author Salvio
 */
public interface SBAcessosModelController {

    public List<ItfPermissao> getAcessosSistema();

}
