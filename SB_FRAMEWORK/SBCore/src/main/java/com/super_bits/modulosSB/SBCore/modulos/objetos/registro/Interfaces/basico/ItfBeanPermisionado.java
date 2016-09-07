/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico;

import java.util.Date;

/**
 *
 * @author desenvolvedor
 */
public interface ItfBeanPermisionado extends ItfBeanIDUnico {

    public Date getDataHoraAlteracao();

    public Date getDataHoraInsercao();

    public ItfUsuario getUsuarioAlteracao();

    public ItfUsuario getUsuarioInsersao();
}
