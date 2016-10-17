/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfSessao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.sessao.ItfTipoView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Salvio
 */
public class SessaoOffline implements ItfSessao {

    private ItfUsuario usuarioLogado;
    private final Date dataInicial;
    private Date dataFinal;
    private final List<ItfPermissao> acoesRealizadas;

    public SessaoOffline() {
        this.acoesRealizadas = new ArrayList();
        this.usuarioLogado = new UsuarioAnonimo();
        this.dataInicial = new Date();

    }

    @Override
    public ItfUsuario getUsuario() {
        return usuarioLogado;
    }

    @Override
    public Date getInicio() {
        return dataInicial;
    }

    @Override
    public Date getFim() {
        return dataFinal;
    }

    @Override
    public List<ItfPermissao> getAcoesRealizadas() {
        return acoesRealizadas;
    }

    @Override
    public void setUsuario(ItfUsuario pUsuario) {
        System.out.println("Usuario " + pUsuario.getNome() + "Vinculado  a SessaoAtual iniciada em:" + getInicio());
        usuarioLogado = pUsuario;
    }

    @Override
    public boolean isIdentificado() {
        if (usuarioLogado == null || usuarioLogado.equals(new UsuarioAnonimo())) {
            System.out.println("id Usuario logado está nulo");
            return false;
        }
        if (usuarioLogado.getId() == 0) {
            System.out.println("id Usuario logado = 0" + usuarioLogado.getNome());
            return false;
        }
        return true;
    }

    @Override
    public ItfTipoView getTipoView() {
        throw new UnsupportedOperationException("Tipo view deste tipo de sessão não foi desenvolvido"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTipoViewDefinido() {
        throw new UnsupportedOperationException("Tipo view deste tipo de sessão não foi desenvolvido"); //To change body of generated methods, choose Tools | Templates.
    }

}
