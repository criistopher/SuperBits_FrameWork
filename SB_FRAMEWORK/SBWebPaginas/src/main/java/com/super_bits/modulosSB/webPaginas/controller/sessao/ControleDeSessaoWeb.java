/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.sessao.ControleDeSessaoAbstratoSBCore;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPServletTools;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 *
 *
 *
 * @author Salvio
 */
@RequestScoped
@Named
public class ControleDeSessaoWeb extends ControleDeSessaoAbstratoSBCore implements Serializable {

    @Inject
    private SessaoAtualSBWP sessaoAtual;

    private String usuarioLogar;
    private String senhaLogar;

    public ControleDeSessaoWeb() {

    }

    @Override
    public ItfSessao getSessaoAtual() {

        if (sessaoAtual == null) {
            sessaoAtual = UtilSBWPServletTools.getSessaoAtual();
            if (sessaoAtual != null) {
                return sessaoAtual;
            }

        } else {
            return sessaoAtual;

        }

        FabErro.PARA_TUDO.paraUsuario("ERRO-SESSAO ATUAL NULA, O CONTRLOE DE SESSAO DEVERIA ESTAR SENDO REQUISITADO VIA CDI", null);
        return null;
    }

    public void recarregarPermissoes() {
        ControllerAppAbstratoSBCore.reloadAcessos();
    }

    @Override
    public void efetuarLogIn() {
        logarEmailESenha(usuarioLogar, senhaLogar);
        UtilSBWP_JSFTools.atualizaPorId("infoLoginSB");
        SBCore.getConfiguradorDePermissao().configuraPermissoes();
        sessaoAtual.setMenuSessao(SBCore.getConfiguradorDePermissao().definirMenu(sessaoAtual.getUsuario().getGrupo()));
    }

    @Override
    public void efetuarLogOut() {
        getSessaoAtual().setUsuario(new UsuarioAnonimo());
        UtilSBWP_JSFTools.vaParaPaginaInicial();
    }

    public String getUsuarioLogar() {
        return usuarioLogar;
    }

    public void setUsuarioLogar(String usuario) {
        this.usuarioLogar = usuario;
    }

    public String getSenhaLogar() {
        return senhaLogar;
    }

    public void setSenhaLogar(String senha) {
        this.senhaLogar = senha;
    }

    public String getSessionID() {

        try {
            FacesContext fCtx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
            return session.getId();
        } catch (Exception e) {
            System.out.println("FaceCOntexto não encontrado, retornando sessao 00000");
            return "00000000000000000000000000000000";
        }
    }

}
