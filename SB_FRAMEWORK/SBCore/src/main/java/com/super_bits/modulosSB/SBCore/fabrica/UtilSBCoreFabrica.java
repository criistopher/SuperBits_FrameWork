/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.fabrica;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBCoreFabrica {

    public static Object getTipoByString(ItfFabrica fabrica, String pString) {

        return FabCampos.CALENDARIO;

    }

}
