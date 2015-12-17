/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller.anotacoes;

import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaModulos;
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
@InfoModulo(nomeDoModulo = "Ações genericas no sistema")
public enum FabAcaoGenerica implements ItfFabricaModulos {

    INCLUIR, ALTERAR, SALVAR, EXCLUIR;

    @Override
    public List<ItfGrupoUsuario> acessoGruposLiberadosPadrao() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        throw new UnsupportedOperationException("O METODO AINDA n\u00e3o FOI IMPLEMENTADO.");
    }
}
