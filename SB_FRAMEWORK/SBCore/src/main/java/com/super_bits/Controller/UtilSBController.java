/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * Metodos estaticos de suporte a camada Controler da aplicação
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 19/12/2015
 * @version 1.0
 *
 */
public class UtilSBController {

    /**
     * public static ItfAcaoDoSistema obterAcaoByMethodo(Method pMetodo) {
     * Annotation[] anotacoes = pMetodo.getAnnotations();
     *
     * for (Annotation an : anotacoes) {
     *
     * Method[] metodos = an.annotationType().getDeclaredMethods(); for (Method
     * fld : metodos) { if (fld.getName().equals("acao")) {
     *
     * try { ItfFabricaModulos fabrica = (ItfFabricaModulos) fld.invoke(an);
     * ItfAcaoDoSistema acao = fabrica.getAcaoDoSistema();
     * acao.setId(UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo));
     * acao.setIdMetodo(UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo));
     *
     * return acao;
     *
     * } catch (Throwable t) { FabErro.PARA_TUDO.paraSistema("A acao do metodo"
     * + pMetodo.getName() + " não contem uma anotação extendendo Itffabrica,
     * contendo o campo acao, desta forma não é possível determinar o nível de
     * permissão deste método", t);
     *
     * }
     * }
     * }
     * }
     * return null; }
     */
    /**
     *
     * Cria um id para o metodo basado no nome da classe, nome do metodo e
     * HaschCode
     *
     * @param pMeThod O Metodo que criará a identificação
     * @return O hash da String Classe+nomeMetodo
     */
    public static Integer gerarIDMetodoAcaoDoSistema(Method pMeThod) {
        String classe = pMeThod.getDeclaringClass().getName();
        String metodo = pMeThod.getName();
        return (classe + metodo).hashCode();
    }

    public static Integer gerarIDAcaoDoSistema(ItfFabricaAcoes pAcao) {
        String nomeModulo = pAcao.getClass().getSimpleName();
        String nomeAcao = pAcao.toString();
        return (nomeModulo + nomeAcao).hashCode();
    }

    /**
     *
     * Utiliza o Factury da constante de acao anotada para obter a Ação do
     * sistema
     *
     * Explicação tecnica: A função localiza anotacoes que contenham um campo do
     * tipo enum chamdo acao, e supoe que este campo é um enum implementando
     * ItffabricaModulos de ações do sistema, tendando obter assim a Ação de
     * sistema a partir deste enum
     *
     * @param pMetodo O Metodo estático da ação
     * @param pararSistemaCasoNaoEncontre :P
     * @return A ação vinculada ao método estático
     */
    public static ItfAcaoDoSistema getAcaoByMetodo(Method pMetodo, boolean pararSistemaCasoNaoEncontre) {
        try {
            ItfFabricaAcoes acao = getFabricaAcaoByMetodo(pMetodo);
            ItfAcaoDoSistema acaoSisTema = (ItfAcaoDoSistema) acao.getRegistro();
            acaoSisTema.setIdMetodo(UtilSBController.gerarIDMetodoAcaoDoSistema(pMetodo));

            return acaoSisTema;
        } catch (Throwable t) {
            FabErro.PARA_TUDO.paraSistema("Erro PT obtendo ação pelo id do metodo", t);
            return null;
        }
    }

    public static ItfFabricaAcoes getFabricaAcaoByMetodo(Method pMetodo) {

        Annotation[] anotacoes = pMetodo.getAnnotations();
        if (anotacoes != null) {
            for (Annotation a : anotacoes) {

                try {

                    Method metodo = a.getClass().getMethod("acao");
                    try {

                        ItfFabricaAcoes acao = (ItfFabricaAcoes) metodo.invoke(a);
                        return acao;

                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        FabErro.PARA_TUDO.paraSistema("Erro tentando obeter a Fabrica de acao a partir do metodo", ex);
                    }
                } catch (NoSuchMethodException | SecurityException ex) {
                    FabErro.PARA_TUDO.paraSistema("MÉTODO AÇÃO NÃO ENCONTADO NA ANOTAÇÃO DE METODO DE AÇÃO DO SISTEMA", ex);
                }
            }

        }

        FabErro.PARA_TUDO.paraSistema("Erro tentando obeter a Fabrica de acao a partir do metodo", null);
        return null;

    }

    public static ItfFabricaAcoes getFabricaAcaoByClasse(Class pClasse) {

        Annotation[] anotacoes = pClasse.getAnnotations();
        if (anotacoes != null) {
            for (Annotation a : anotacoes) {

                try {

                    Method metodo = a.getClass().getMethod("acao");
                    if (metodo == null) {
                        System.out.println("A classe" + pClasse + "Não possui uma anotação de ação vinculada ");
                        return null;
                    }
                    try {

                        ItfFabricaAcoes acao = (ItfFabricaAcoes) metodo.invoke(a);
                        return acao;

                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.out.println("A classe" + pClasse + "Não possui uma anotação de ação ");
                        //  FabErro.PARA_TUDO.paraSistema("Erro tentando obeter a Fabrica de acao a partir do metodo", ex);
                    }
                } catch (NoSuchMethodException | SecurityException ex) {
                    System.out.println("A classe" + pClasse + "Não possui uma anotação de ação ");
                    //   FabErro. .paraSistema("MÉTODO AÇÃO NÃO ENCONTADO NA ANOTAÇÃO DE METODO DE AÇÃO DO SISTEMA", ex);
                }
            }

        }

        //   FabErro.PARA_TUDO.paraSistema("Erro tentando obeter a Fabrica de acao a partir do metodo", null);
        return null;

    }

    public static ItfAcaoDoSistema getAcaoByClasse(Class pClasse) {

        ItfFabricaAcoes acao = (ItfFabricaAcoes) getFabricaAcaoByClasse(pClasse);
        if (acao == null) {
            return null;
        }
        ItfAcaoDoSistema acaoSisTema = (ItfAcaoDoSistema) acao.getRegistro();

        return acaoSisTema;

        //   FabErro.PARA_TUDO.paraSistema("Erro tentando obeter a Fabrica de acao a partir do metodo", null);
    }

}
