/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import java.lang.reflect.Method;

/**
 *
 * @author sfurbino
 */
public interface ItfAcaoDoSistema extends ItfBeanSimples {

    public String getNomeAcao();

    public String getIconeAcao();

    public String getCor();

    public String getDescricao();

    public Method getMetodoExecucao();

    public String getUrlAction();

}
