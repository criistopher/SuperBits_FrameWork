/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.sessao;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;

/**
 *
 * @author sfurbino
 */
public interface ItfTipoView {

    public String getDispositivo();

    public String getAplicativo();

    public String getVersaoAplicativo();

    public int getX();

    public int getY();

    public int getNumeroMaximoColunas();

    public boolean isUmMobile();

    public ComponenteVisualSB getComponenteListaGem();

}
