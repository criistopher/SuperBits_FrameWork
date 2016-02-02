/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.testes;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCorePadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 *
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 20/07/2014
 *
 */
public class TestesCore {

    public void testaConfigEErroRElat() {
        System.out.println("Teste relato de Erro");
        SBCore.configurar(new ConfigCorePadrao());
        //   SBCore.LancarErro(InfoErroSB.FabErro.USUARIO, "Executa Alerta Usuario");
        System.out.println("Relato de Erro OK");
    }

    @Test
    public void testeToString() {
        //    InfoErroSB.FabErro tipo = InfoErroSB.FabErro.ARQUIVAR_LOG;

        // System.out.println(tipo);
    }

}
