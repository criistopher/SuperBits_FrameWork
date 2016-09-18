/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

/**
 *
 * @author salvioF
 */
public enum TIPO_PRIMITIVO {
    INTEIRO, LETRAS, DATAS, BOOLEAN, DECIMAL, ENTIDADE;

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
                throw new AssertionError(this.name());
        }
    }

}
