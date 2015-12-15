/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreDeveloperStopMessage;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class ErroSBCoreDeveloperSopMessagemTest {

    public ErroSBCoreDeveloperSopMessagemTest() {
    }

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreDeveloperStopMessage());
    }

    @Test
    public void testSomeMethod() {
        try {
            throw new UnsupportedClassVersionError("marcando bobs aí...");
        } catch (Throwable e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor( "Erroo teste", e);
        }
    }

}
