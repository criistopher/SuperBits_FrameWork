/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.acoesDoSistema;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;

/**
 *
 *
 *
 * @author desenvolvedor
 */
@InfoModulo(nomeDoModulo = "Sistema", descricao = "Ações basicas do sistema")
public enum FabAcaoSistemaSB implements ItfFabricaAcoes {

    ACESSOS_GERENCIAR,
    DEMONSTRACAO_COMPONENTE,
    DEMONSTRACAO_VALIDACAO;

    @Override
    public List<ItfGrupoUsuario> getGruposVinculadosAoModulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        return getRegistro();
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        AcaoDoSistema acao = new AcaoDoSistema();
        switch (this) {
            case ACESSOS_GERENCIAR:
                break;
            case DEMONSTRACAO_COMPONENTE:
                acao.setPrecisaPermissao(false);
                break;
            case DEMONSTRACAO_VALIDACAO:
                break;
            default:
                throw new AssertionError(this.name());
        }
        return acao;
    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoControllerEntidade getAcaoEntidadeController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoController getAcaoController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class getDominio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
