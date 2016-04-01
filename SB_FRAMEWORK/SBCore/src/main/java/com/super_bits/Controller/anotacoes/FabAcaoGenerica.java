/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.anotacoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;

/**
 *
 * AÇÃO GENERICA QUE SERVE DE EXEMPLO PARA CRIAÇÃO DE FABRICA DE AÇÕES EM
 * SISTEMAS SISTEMAS
 *
 *
 *
 * @author sfurbino
 */
@InfoModulo(nomeDoModulo = "Ações genericas no sistema", descricao = "Ações genericas do sistema")
public enum FabAcaoGenerica implements ItfFabricaAcoes {

    INCLUIR, ALTERAR, SALVAR, EXCLUIR;

    @Override
    public List<ItfGrupoUsuario> getAcessoGruposLiberadosPadrao() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public ItfAcaoDoSistema getAcaoDoSistema() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormulario getAcaoEntidadeFormulario(ItfAcaoDoSistema acaoPrincipal, Class classeRelacionada, String pXhtml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoController getAcaoEntidadeController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
