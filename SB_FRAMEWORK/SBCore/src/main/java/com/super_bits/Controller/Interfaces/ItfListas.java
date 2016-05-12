/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfListas {

    public List getLista(ItfBeanSimples entidade);

    public Class getClasse();

}
