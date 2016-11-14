/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.util.Calendar;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreComunicacao {

    public static String getSaudacao() {
        long horario = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (horario > 0 && horario < 4) {
            return "Boa Madrugada";
        }
        if (horario > 5 & horario < 12) {
            return "Bom dia";
        }
        if (horario >= 12 & horario < 18) {
            return "Boa tarde";
        }
        if (horario >= 18) {
            return "Boa Noite";
        }
        return "Olá";
    }

}
