/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes;

import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;

/**
 *
 * Uma ação de controller é uma ação do sistema que pode ou não conter
 * parametros, e deve retornar algo.
 *
 *
 * @author desenvolvedor
 */
public class AcaoController extends AcaoDoSistema {

    public AcaoController() {
        super(FabTipoAcaoSistema.ACAO_CONTROLLER);
    }

}
