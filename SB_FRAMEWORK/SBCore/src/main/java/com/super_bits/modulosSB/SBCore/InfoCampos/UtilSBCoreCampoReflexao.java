/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.InfoCampos;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import java.lang.reflect.Field;

/**
 *
 * @author Salvio
 */
public class UtilSBCoreCampoReflexao {

    public static boolean isClasseBasicaSB(Class pClasse) {
        // todo Tratar EntidadeGenerica gerar trhow se Chegar no Object
        return (pClasse == ItemGenerico.class || pClasse == Object.class);

    }

    public static Field getSBCampobyTipoCampo(Class pClasse, FabCampos pTipoCampo) {

        Class classe = pClasse;

        while (!isClasseBasicaSB(classe)) {

            Field[] fields = classe.getDeclaredFields();

            for (Field field : fields) {
                InfoCampo annotationField = field.getAnnotation(InfoCampo.class);

                if (annotationField != null) {

                    if (annotationField.tipo().equals(pTipoCampo)) {
                        return field;
                    }

                }
            }

            classe = classe.getSuperclass();

        }
        return null;
    }

    public static Field getCampoByClasseAnotacao(Class pClasse, Class anotacao) {
        Class classe = pClasse;

        while (!isClasseBasicaSB(classe)) {

            Field[] fields = classe.getDeclaredFields();

            for (Field field : fields) {
                Object campoAnotado = field.getAnnotation(anotacao);

                if (campoAnotado != null) {

                    return field;

                }
            }

            classe = classe.getSuperclass();

        }
        return null;
    }

}
