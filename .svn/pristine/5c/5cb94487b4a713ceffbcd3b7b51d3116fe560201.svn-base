/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;

/**
 *
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÓ SÃO OBRIGATÓRIOS QUANDO NÃO EXISTIR UMA INTERFACE DOCUMENTADA, DESCREVA DE
 * FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE UMA EQUIPE.
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 18/03/2015
 * @version 1.0
 */
public class UtilSBCoreNumeros {

    public static Integer getLpadZero(Integer pValor, int pCasas) {
        try {

            return Integer.parseInt(String.format("%" + pCasas + "s", pValor.toString()).replace(" ", "0"));

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro ao formatar Lpad", e);

            return pValor;
        }

    }

    public static Integer getConcatenados(int... pNumeros) {
        StringBuilder sb = new StringBuilder(pNumeros.length);
        for (int digito : pNumeros) {
            sb.append(digito);
        }
        return Integer.getInteger(sb.toString());
    }

}
