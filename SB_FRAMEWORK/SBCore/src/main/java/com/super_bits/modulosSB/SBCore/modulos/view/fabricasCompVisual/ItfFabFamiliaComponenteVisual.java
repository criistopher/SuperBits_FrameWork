/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public interface ItfFabFamiliaComponenteVisual extends ItfFabrica {

    public Class getInterfaceCompativel();

    public String getNomeFAmilia();

    public Class getFabricaCamposPadrao();

    @Override
    public FamiliaComponente getRegistro();

}
