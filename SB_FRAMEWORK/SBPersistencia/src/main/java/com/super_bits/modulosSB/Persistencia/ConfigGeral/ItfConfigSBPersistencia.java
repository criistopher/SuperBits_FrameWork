/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.Persistencia.ConfigGeral;

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

    public void criarBancoInicial();

}
