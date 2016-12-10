/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 *
 * @author desenvolvedor
 */
public class FieldComSerializacao implements Serializable {

    private final Field campo;

    public FieldComSerializacao(Field campo) {
        this.campo = campo;
    }

    public Field campo() {
        return campo;
    }

}
