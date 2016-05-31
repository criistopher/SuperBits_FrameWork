/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.anotacoes;

/**
 * w
 *
 *
 *
 * @author desenvolvedor
 */
public @interface InfoTipoAcaoController {

    public String nomeAcao() default "";

    public String icone() default "";

    public Class entidade() default Object.class;

    public boolean precisaPermissao() default false;

    public String xhtmlDaAcao() default "";

    public String descricao() default "";

    public String codigoJira() default "";

}
