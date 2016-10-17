/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.menu;

/**
 *
 * @author desenvolvedor
 */
public class MenusDaSessao {

    private MenuSBFW menuPrincipal;

    private MenuSBFW menuAvancado;

    /**
     *
     * @param menuPrincipal Menu com atalhos principais
     * @param menuSecundario Menu avançado
     */
    public MenusDaSessao(MenuSBFW menuPrincipal, MenuSBFW menuSecundario) {

        this.menuPrincipal = menuPrincipal;
        this.menuAvancado = menuSecundario;

    }

    public MenusDaSessao(MenuSBFW menuPrincipal) {

        this.menuPrincipal = menuPrincipal;

    }

    public boolean isTemMenuAvancado() {
        return menuAvancado != null;
    }

    public MenuSBFW getMenuPrincipal() {
        return menuPrincipal;
    }

    public void setMenuPrincipal(MenuSBFW menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public MenuSBFW getMenuAvancado() {
        return menuAvancado;
    }

    public void setMenuAvancado(MenuSBFW menuAvancado) {
        this.menuAvancado = menuAvancado;
    }

}
