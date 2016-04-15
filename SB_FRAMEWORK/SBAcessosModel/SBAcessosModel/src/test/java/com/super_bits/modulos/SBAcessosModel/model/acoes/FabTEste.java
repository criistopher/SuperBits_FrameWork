/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoModulo(descricao = "Modulo teste", nomeDoModulo = "Nome do modulo")
public enum FabTEste implements ItfFabricaAcoes {

    OBJETO_FRM_NOVO,
    OBJETO_MB_GERENCIAR,
    OBJETO_CTR_ALTERAR_STATUS,
    USUARIO_TESTE_MB_GERENCIAR,
    USUARIO_TESTE_FRM_NOVO,
    USUARIO_TESTE_FRM_EDITAR;

    @Override
    public List<ItfGrupoUsuario> getGruposVinculadosAoModulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        return (ItfAcaoDoSistema) getRegistro();
    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        return (ItfAcaoEntidade) getRegistro();
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario() {
        return (ItfAcaoFormularioEntidade) getRegistro();
    }

    @Override
    public ItfAcaoControllerEntidade getAcaoEntidadeController() {
        return (ItfAcaoControllerEntidade) getRegistro();
    }

    @Override
    public ItfAcaoController getAcaoController() {
        return (ItfAcaoController) getRegistro();
    }

    @Override
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        return (ItfAcaoGerenciarEntidade) getRegistro();
    }

    @Override
    public Class getDominio() {

        switch (this) {
            case OBJETO_FRM_NOVO:
            case OBJETO_MB_GERENCIAR:
            case OBJETO_CTR_ALTERAR_STATUS:
                return GrupoUsuarioSB.class;
            case USUARIO_TESTE_MB_GERENCIAR:
            case USUARIO_TESTE_FRM_NOVO:
            case USUARIO_TESTE_FRM_EDITAR:
                return UsuarioSB.class;
            default:
                throw new AssertionError(this.name());

        }

    }

    @Override
    public Object getRegistro() {
        ItfAcaoDoSistema acao = UtilFabricaDeAcoes.getNovaAcao(this);
        ItfAcaoFormularioEntidade acaoform = null;
        if (acao.isUmaAcaoFormulario()) {
            acaoform = (ItfAcaoFormularioEntidade) acao;
        }

        switch (this) {
            case OBJETO_FRM_NOVO:
                acao.setNomeAcao("Teste");
                break;
            case OBJETO_MB_GERENCIAR:
                break;
            case OBJETO_CTR_ALTERAR_STATUS:

//                acaoform.setXhtml("asdfasdf");
                break;
            case USUARIO_TESTE_MB_GERENCIAR:
                break;
            case USUARIO_TESTE_FRM_NOVO:
                break;
            case USUARIO_TESTE_FRM_EDITAR:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return acao;
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoes.getModuloByFabrica(this).getNome();
    }

}
