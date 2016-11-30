/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.modulos.view.menu.ItfFabricaMenu;
import java.io.Serializable;

/**
 *
 * @author desenvolvedor
 */
public interface ItfSiteMapa extends Serializable {

    /**
     *
     * Uma fabrica de menu é uma classe do tipo enum, contendo os diversos menus
     * do sistema
     *
     * @return Fabrica de menus do sistema
     */
    Class<? extends ItfFabricaMenu> getFabricaMenu();

    ItfB_Pagina getPaginaNoContexto(String xhtmlGerenciarPG) throws UnsupportedOperationException;

}
