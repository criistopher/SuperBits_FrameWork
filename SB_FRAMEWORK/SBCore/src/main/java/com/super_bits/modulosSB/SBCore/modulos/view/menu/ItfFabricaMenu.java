/* 
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90 

 */
package com.super_bits.modulosSB.SBCore.modulos.view.menu;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfFabricaMenu extends ItfFabrica{
    
    
    public List<MenuSBFW> getTodosMenus();
    
}
