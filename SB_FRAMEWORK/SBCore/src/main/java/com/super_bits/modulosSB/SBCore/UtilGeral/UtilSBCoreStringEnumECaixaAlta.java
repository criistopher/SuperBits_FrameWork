/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

/**
 *
 * Utilitários para interpretar nomes em caixa alta, principalemte de nomeação
 * de enuns que seguem as boas práticas descritas em
 *
 * http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 *
 *
 *
 * @author salvioF
 */
public class UtilSBCoreStringEnumECaixaAlta {

    /**
     *
     * NOME_LEGA_DO_MEU_ENUM_TESTE retornaria a string teste
     *
     * @return A ultima parte do enum
     */
    public static String getUltimaParteNomeEnumEmMinusculo(Enum valor) {
        String[] partes = valor.toString().split("_");
        if (partes.length > 1) {
            return partes[partes.length - 1].toLowerCase();
        } else {
            return valor.toString().toLowerCase();
        }

    }

    /**
     *
     * NOME_LEGA_DO_MEU_ENUM_TESTE retornaria a string teste
     *
     * @return A ultima parte do enum
     */
    public static String getUltimaParteNomeEnumPrimeiraEmMaiusculo(Enum valor) {
        String[] partes = valor.toString().split("_");
        if (partes.length > 1) {
            return UtilSBCoreStrings.getPrimeiraLetraMaiuscula(partes[partes.length - 1]);
        } else {
            return UtilSBCoreStrings.getPrimeiraLetraMaiuscula(valor.toString());
        }
    }

}
