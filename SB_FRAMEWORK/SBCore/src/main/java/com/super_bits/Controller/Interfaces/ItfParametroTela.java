/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces;

import java.io.Serializable;

/**
 *
 * @author desenvolvedor
 */
public interface ItfParametroTela extends Serializable {

    public static enum TIPOURL {

        TEXTO, ENTIDADE, OBJETO_COM_CONSTRUCTOR
    }

    String getNome();

    Class getTipoEntidade();

    TIPOURL getTipoParametro();

    Object getValor();

    Object getValorPadrao();

    void setNome(String nome);

    void setTipoEntidade(Class<?> tipoEntidade);

    void setTipoParametro(TIPOURL tipoParametro);

    void setValor(Object valor);

    void setValorPadrao(Object valorPadrao);

}
