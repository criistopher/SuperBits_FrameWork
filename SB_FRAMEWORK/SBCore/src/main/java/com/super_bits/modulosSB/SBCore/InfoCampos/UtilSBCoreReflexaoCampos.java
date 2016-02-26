/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.InfoCampos;

import com.google.common.collect.HashBiMap;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Salvio
 */
public class UtilSBCoreReflexaoCampos {

    public static boolean isClasseBasicaSB(Class pClasse) {
        // todo Tratar EntidadeGenerica gerar trhow se Chegar no Object
        return (pClasse == ItemGenerico.class || pClasse == Object.class);

    }

    public static List<Field> getTodosItensSimplesDoItem(Class pClasse) {

        List<Field> lista = new ArrayList<>();

        while (!isClasseBasicaSB(pClasse)) {
            List<Field> itensdoNivel = new ArrayList<>();

            Field[] fields = pClasse.getDeclaredFields();

            for (Field field : fields) {

                if (field.getClass().isAssignableFrom(ItfBeanSimples.class)) {

                    lista.add(field);

                }

            }

            pClasse = pClasse.getSuperclass();

        }

        return lista;

    }

    /**
     *
     * Retorna os Fields que sejam compativeis com ItfItemSimples em ordem de
     * Itens filho para pai
     *
     * @param pClasse classe que será analizada
     * @return Fileds Do tipo item Simples
     */
    public static List<Field> getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(Class pClasse) {

        Map<Integer, List<Field>> itensEncontrados = new TreeMap<>();

        while (!isClasseBasicaSB(pClasse)) {
            List<Field> itensdoNivel = new ArrayList<>();

            Field[] fields = pClasse.getDeclaredFields();

            for (Field field : fields) {

                if (field.getType().isAssignableFrom(ItfBeanSimples.class)) {

                    boolean temMaisFilho = true;
                    Class classeFilho = field.getClass();
                    int nivelFilho = 0;
                    while (temMaisFilho) {
                        nivelFilho++;
                        Field[] camposFilho = classeFilho.getDeclaredFields();
                        for (Field campo : camposFilho) {
                            boolean encontrouFilho;
                            if (field.getType().isAssignableFrom(ItfBeanSimples.class)) {
                                campo.get
                            }
                        }

                    }

                }

            }

            pClasse = pClasse.getSuperclass();
            if (!itensdoNivel.isEmpty()) {
                itensEncontrados.put(nivelAtual, itensdoNivel);

            }

        }
        ArrayList<Integer> keys = new ArrayList<Integer>(itensEncontrados.keySet());
        List<Field> camposEncontrados = new ArrayList<>();

        for (int i = keys.size() - 1; i >= 0; i--) {
            for (Field cp : itensEncontrados.get(keys.get(i))) {
                camposEncontrados.add(cp);
            }
        }
        return camposEncontrados;

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
