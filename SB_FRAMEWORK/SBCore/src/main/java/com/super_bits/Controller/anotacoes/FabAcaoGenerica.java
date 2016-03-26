/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.anotacoes;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.acoes.AcaoDeEntidade;
import com.super_bits.Controller.acoes.AcaoDeEntidadeController;
import com.super_bits.Controller.acoes.acaoDeEntidade.AcaoFormularioDeEntidade;
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
    public AcaoDeEntidade getAcaoDeEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AcaoFormularioDeEntidade getAcaoEntidadeFormulario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AcaoDeEntidadeController getAcaoEntidadeController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
