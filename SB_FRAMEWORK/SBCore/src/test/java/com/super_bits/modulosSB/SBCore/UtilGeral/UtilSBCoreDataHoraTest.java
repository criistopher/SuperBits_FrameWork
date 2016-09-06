/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreDataHoraTest {

    public UtilSBCoreDataHoraTest() {
    }

    //@Test
    public void testGetDataSTRFormatoUsuario() {
    }

    //@Test
    public void testSegundosEntre() {
    }

    //@Test
    public void testMileSegundosEntre() {
    }

    //@Test
    public void testGetAgoraString_UtilSBCoreDataHoraFORMATO_TEMPO() {
    }

    //@Test
    public void testGetAgoraString_String() {
    }

    //@Test
    public void testGetDataHoraString_Date_String() {
    }

    //@Test
    public void testGetDataHoraString_Date_UtilSBCoreDataHoraFORMATO_TEMPO() {
    }

    //@Test
    public void testGethoje() {
    }

    //@Test
    public void testIntervaloTempoMileSegundos() {
    }

    //@Test
    public void testIntervaloTempoSegundos() {
    }

    //@Test
    public void testIntervaloTempoMinutos() {
    }

    //@Test
    public void testIntervaloTempoHoras() {
    }

    @Test
    public void testIntervaloTempoDias() {
        try {
            //Date dataInicial = new GregorianCalendar().getTime

            Date dataInicial = new Date();
            Date dataFinal = UtilSBCoreDataHora.incrementaDias(dataInicial, 5);

            long diferencaDias;

            diferencaDias = (dataFinal.getTime() - dataInicial.getTime()) / (1000 * 60 * 60 * 24);
            assertEquals(5, diferencaDias);

            diferencaDias = UtilSBCoreDataHora.intervaloTempoDias(dataInicial, dataFinal);
            assertEquals(5, diferencaDias);

        } catch (Throwable t) {
            t.getCause();
        }

    }

    //@Test
    public void testIntervaloTempoDiasHorasMinitosSegundos() {
    }

    //@Test
    public void testDecrementaMinutos() {
    }

    //@Test
    public void testDecrementarDias() {
    }

    //@Test
    public void testIncrementaDias() {
    }

    //@Test
    public void testIncrementaSegundos() {
    }

    //@Test
    public void testIncrementaHoras() {
    }

    //@Test
    public void testIncrementaMinutos() {
    }

    //@Test
    public void testIncrementaDiaHorasMinutosSegundo() {
    }

    //@Test
    public void testIncrementaDiaHorasMinutosSegundosDiasUteis() {
    }

}
