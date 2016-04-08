/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces.acoes;

import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfAcaoController extends ItfAcaoDoSistema {

    public int getIdMetodo();

    public boolean isTemParametroExtra();

    public void setIdMetodo(Method pMetodo);

    public List<ParametroDeAcaoController> getParametros();
}
