/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.Persistencia.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author Salvio
 */
public interface ItfConfigSBPersistencia {

    public String bancoPrincipal();

    public String[] bancosExtra();

    public String formatoDataBanco();

    public String formatoDataUsuario();

    public String pastaImagensJPA();

    public Class<? extends ItfFabrica>[] fabricasRegistrosIniciais();

    public void criarBancoInicial();

}
