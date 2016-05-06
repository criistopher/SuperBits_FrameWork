/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.InomeClienteI.InomeProjetoI.regras_de_negocio_e_controller;

import com.super_bits.view.menu.ItfFabricaMenu;
import com.super_bits.view.menu.MenuSBFW;
import java.util.List;

/**
 *
 *
 *
 *
 * @author desenvolvedor
 */
public enum FabMenuExemplo implements ItfFabricaMenu {
    MENU_INICIAL, MENU_RESTRITO;

    @Override
    public List<MenuSBFW> getTodosMenus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
