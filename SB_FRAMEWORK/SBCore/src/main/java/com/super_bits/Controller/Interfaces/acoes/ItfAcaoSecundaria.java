/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces.acoes;

import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;

/**
 *
 * @author desenvolvedor
 */
public interface ItfAcaoSecundaria extends ItfAcaoDoSistema, ItfAcaoEntidade {

    public ItfAcaoDoSistema getAcaoPrincipal();

}
