/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public interface ItfAcaoController extends ItfAcaoDoSistema {

    int getIdMetodo();

    boolean isTemParametroExtra();

}
