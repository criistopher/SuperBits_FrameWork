/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.InfoCampos;

import com.google.common.collect.HashBiMap;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
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

    /**
     *
     * Retorna os objtetos Filds do Reflectio de todos os Itens Simples da
     * classe
     *
     * @param pClasse
     * @return
     */
    public static List<Field> getTodosCamposTipoItensSimplesDoItem(Class pClasse) {

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
     * Retorna os objtetos Filds do Reflectio de todos os Itens Simples da
     * classe
     *
     * @param pClasse
     * @return
     */
    public static List<CaminhoCampoReflexao> getTodosCamposTipoItemSimplesDoItem(Class pClasse, String pCaminho) {

        List<CaminhoCampoReflexao> lista = new ArrayList<>();

        while (!isClasseBasicaSB(pClasse)) {
            List<Field> itensdoNivel = new ArrayList<>();

            Field[] fields = pClasse.getDeclaredFields();

            for (Field campo : fields) {
                Class classeDoCampo = campo.getType();
                if (classeDoCampo.isAssignableFrom(ItfBeanSimples.class)) {

                    lista.add(new CaminhoCampoReflexao(pCaminho, campo));

                }

            }

            pClasse = pClasse.getSuperclass();

        }

        return lista;

    }

    private static List<CaminhoCampoReflexao> getCamposReflectionFilho(List<CaminhoCampoReflexao> listaAnterior, String pCaminhoAnterior) {

        for (CaminhoCampoReflexao cm : listaAnterior) {
            List<CaminhoCampoReflexao> camposEncontrados = getTodosCamposTipoItemSimplesDoItem(cm.getCampo().getType(), pCaminhoAnterior + "." + cm.getCampo().getName());
            listaAnterior.add(cm);
            if (!camposEncontrados.isEmpty()) {
                listaAnterior = getCamposReflectionFilho(listaAnterior, pCaminhoAnterior + "." + cm.getCampo().getName());
            }
        }
        return listaAnterior;

    }

    /**
     *
     * Retorna os Fields que sejam compativeis com ItfItemSimples em ordem de
     * Itens filho para pai
     *
     * @param pClasse classe que será analizada
     * @return Fileds Do tipo item Simples
     */
    public static List<CaminhoCampoReflexao> getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(Class pClasse) {

        Map<Integer, List<CaminhoCampoReflexao>> itensEncontrados = new TreeMap<>();

        int nivel = 0;
        Class classe = pClasse;

        List<CaminhoCampoReflexao> camposEncontrados = getTodosCamposTipoItemSimplesDoItem(pClasse, "");
        // adiciona campos no nivel 0
        if (!camposEncontrados.isEmpty()) {
            camposEncontrados = getCamposReflectionFilho(camposEncontrados, pClasse.getSimpleName());
        }
        // para cada campo encontrado no nivel 0

        return camposEncontrados;

    }

    public static Field getSBCampobyTipoCampo(Class pClasse, FabCampos pTipoCampo) {

        Class classe = pClasse;

        while (!isClasseBasicaSB(classe)) {

            Field[] fields = classe.getDeclaredFields();

            for (Field field : fields) {
                InfoCampo annotationField = field.getAnnotation(InfoCampo.class
                );

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
