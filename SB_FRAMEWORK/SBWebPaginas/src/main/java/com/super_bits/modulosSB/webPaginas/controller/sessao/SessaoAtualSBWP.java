/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.SessaoOffline;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.sessao.ItfTipoView;
import com.super_bits.view.menu.MenuSBFW;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Salvio
 */
@SessionScoped
@Named
public class SessaoAtualSBWP extends SessaoOffline implements ItfSessao, Serializable {

    private boolean tipoViewDefinido = false;
    private ItfTipoView tipoView;

    private MenuSBFW menuSessao;

    public MenuSBFW getMenuSessao() {
        return menuSessao;
    }

    public void setMenuSessao(MenuSBFW menuSessao) {
        this.menuSessao = menuSessao;
    }

    public void defineInfoTela() {
        String infoTela = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactfrm:clientinfo");
        tipoView = new TelaWP(infoTela);
        tipoViewDefinido = true;

    }

    @Override
    public ItfTipoView getTipoView() {
        return tipoView;
    }

    @Override
    public boolean isTipoViewDefinido() {
        return tipoViewDefinido;
    }

}
