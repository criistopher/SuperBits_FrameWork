/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import java.util.List;

/**
 *
 * @author Salvio
 */
public interface ItfCfgPermissoes {

    public List<ItfPermissao> configuraPermissoes();

    public List<ItfUsuario> configuraUsuarios();

    public ItfResposta ACAOCRUD(Class pEntidade, String TIPOACAO);

    public Class[] getClassesController();

}
