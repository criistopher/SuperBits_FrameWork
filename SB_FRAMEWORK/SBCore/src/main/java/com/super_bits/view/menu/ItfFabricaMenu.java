/* 
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90 

 */
package com.super_bits.view.menu;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfFabricaMenu extends ItfFabrica{
    
    
    public List<MenuSBFW> getTodosMenus();
    
}
