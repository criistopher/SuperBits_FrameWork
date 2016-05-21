/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.anotacoes;

/**
 *
 *
 *
 * @author desenvolvedor
 */
public @interface InfoTipoAcaoController {

    public String nomeAcao();

    public String icone();

    public Class entidade();

    public boolean precisaPermissao();

    public String xhtmlDaAcao();

}
