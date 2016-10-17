/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.sessao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.modulos.sessao.ControleDeSessaoAbstratoSBCore;
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
        if (sessaoAtual.isIdentificado()) {
            UtilSBWP_JSFTools.executarJavaScript("location.reload();");
        }
        SBCore.getConfiguradorDePermissao().configuraPermissoes();
        ItfGrupoUsuario grupoUsuarioLogado = sessaoAtual.getUsuario().getGrupo();
        sessaoAtual.setMenusDaSessao(SBCore.getConfiguradorDePermissao().definirMenu(grupoUsuarioLogado));
    }

    public void esqueceuaSenha() {
        System.out.println("Esqueceu senha" + usuarioLogar);
        enviarSenhaParaEmail(usuarioLogar);
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

    @Override
    public void logarComoRoot() {

        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("O método logar como Root não é suportado no ambiente web, por questões de segurança");
        } else {
            getSessaoAtual().setUsuario(new UsuarioSistemaRoot());
        }
    }

    @Override
    public void logarComoAnonimo() {
        efetuarLogOut();
    }

}
