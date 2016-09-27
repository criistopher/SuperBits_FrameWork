/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import org.junit.Test;

/**
 *
 * @author salvioF
 */
public class UtilSBCoreResourcesTest {

    @Test
    public void testGetFile() {

        System.out.println("SERIAL:[" + UtilSBCoreResources.getHashCodeClasseDoPacote(ItemSimples.class) + "]");
    }

}
