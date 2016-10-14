/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import java.lang.reflect.Field;

/**
 *
 * @author salvioF
 */
public enum TIPO_PRIMITIVO {
    INTEIRO, LETRAS, DATAS, BOOLEAN, DECIMAL, ENTIDADE, OUTROS_OBJETOS;

    public String getDeclaracaoJava() {

        switch (this) {
            case INTEIRO:
                return "int";
            case LETRAS:
                return "String";
            case DATAS:
                return "Date";
            case BOOLEAN:
                return "boolean";
            case DECIMAL:
                return "double";
            case ENTIDADE:
                return "entidade";
            default:
                return "Outros_Objetos";
        }
    }

    public static TIPO_PRIMITIVO getTIPO_PRIMITIVO(Field campo) {
        String tipo = campo.getType().getSimpleName();
        if (tipo.equals("int")) {
            return INTEIRO;
        }
        if (tipo.equals("String")) {
            return LETRAS;
        }
        if (tipo.equals("Date")) {
            return DATAS;
        }
        if (tipo.equals("boolean")) {
            return BOOLEAN;
        }
        if (tipo.equals("double")) {
            return DECIMAL;
        }
        Class classseVinculada = UtilSBCoreReflexaoCampos.getClassePrincipalDoCampo(campo);
        if (classseVinculada.isAnnotationPresent(InfoClasse.class)) {
            return ENTIDADE;
        } else {
            return OUTROS_OBJETOS;
        }

    }

}
