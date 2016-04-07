/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.anotacoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
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

    INCLUIR, ALTERAR, SALVAR, EXCLUIR, ACAO_INTERNA_DO_SISTEMA;

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
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
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
    public ItfAcaoController getAcaoController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
