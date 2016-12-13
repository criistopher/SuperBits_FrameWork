/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBWPSiteMapa {

    public static String getUrlParaAcao(ItfAcaoDoSistema pAcao) {
        return null;
    }

    /**
     *
     * @param objSimples
     * @return
     */
    public static String getSlugDoObjeto(ItfBeanSimples objSimples) {
        return UtilSBCoreStrings.makeStrUrlAmigavel(objSimples.getNomeUnicoSlug())
                + UtilSBCoreStrings.makeStrUrlAmigavel("-" + objSimples.getId());
    }

}
