/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.Controller.Interfaces;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import java.util.List;

/**
 *
 * @author Salvio
 */
public interface ItfConfiguradorDeAcessos {

    public List<ItfAcesso> configuraAcessos();

    public List<ItfUsuario> configuraUsuarios();

    public boolean ACAOCRUD(Class pEntidade, String TIPOACAO);

    public Class[] getClassesController();

}
