/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author Salvio
 */
public interface ItfControlerAPP {

    public boolean possuiEstaAcao(ItfAcaoDoSistema permissao);

    public boolean isAcessoPermitido(ItfUsuario pUsuario, ItfAcaoDoSistema permissao);

}
