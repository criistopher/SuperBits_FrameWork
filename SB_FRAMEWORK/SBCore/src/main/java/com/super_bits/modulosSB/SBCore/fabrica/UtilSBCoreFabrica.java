/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.fabrica;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBCoreFabrica {

    public static Object getTipoByString(ItfFabrica fabrica, String pString) {

        return FabCampos.CALENDARIO;

    }

    /**
     *
     * Retorna todos os registros de uma fábrica
     *
     * @param fabrica Fabrica de referencia
     * @return Todos os registros criados pela fabrica
     */
    public static List getListaTodosRegistrosDaFabrica(Class<ItfFabrica> fabrica) {
        List todos = new ArrayList<>();
        for (ItfFabrica fb : fabrica.getEnumConstants()) {
            todos.add(fb.getRegistro());
        }
        return todos;
    }

}
