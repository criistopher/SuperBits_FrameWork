/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Transient;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;

/**
 *
 * Métodos Staticos para auxiliar na Reflexão de Classes
 *
 * @author Salvio
 */
public abstract class UtilSBCoreReflexao {

    /**
     *
     * Retorna as instancias encontradas em uma classe que forem de determinado
     * tipo
     *
     * @param instancia
     * @param tipoProcurado
     * @return
     */
    public static List<?> procuraInstanciasDeCamposPorTipo(Object instancia, Class tipoProcurado) {
        Class classe = instancia.getClass();
        Field[] fields = classe.getDeclaredFields();

        List<Object> resposta = new ArrayList<>();
        for (Field campo : fields) {
            if (campo.getType().getName().equals(tipoProcurado.getName())) {
                campo.setAccessible(true);
                try {
                    resposta.add(campo.get(instancia));
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(UtilSBCoreReflexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                System.out.println("nao encontrou em:" + campo.getType().getName() + "--" + campo.getName() + " o tipo: " + tipoProcurado.getName());
            }
        }
        return resposta;
    }

    /**
     *
     * Retorna todos os Filds de uma classe que forem de um tipo determinado
     *
     * @param instancia
     * @param tipoProcurado
     * @return
     */
    public static List<Field> procuraCamposPorTipo(Object instancia, Class tipoProcurado) {
        Class classe = instancia.getClass();
        Field[] fields = classe.getDeclaredFields();

        List<Field> resposta = new ArrayList<>();
        for (Field campo : fields) {
            if (campo.getType().getName().equals(tipoProcurado.getName())) {
                campo.setAccessible(true);
                try {
                    resposta.add(campo);
                } catch (Throwable ex) {
                    Logger.getLogger(UtilSBCoreReflexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                System.out.println("nao encontrou em:" + campo.getType().getName() + "--" + campo.getName() + " o tipo: " + tipoProcurado.getName());
            }
        }
        return resposta;
    }

    /**
     *
     * Percorre todos os atributos da classe, e instancia todos os List, com
     * newArrayList facilitando o constructor de classes e evitando nullpoint
     * exceptions
     *
     * @param instancia
     * @return
     */
    public static List<Field> instanciarListas(Object instancia) {
        Class classe = instancia.getClass();
        Field[] fields = classe.getDeclaredFields();

        List<Field> resposta = new ArrayList<>();
        for (Field campo : fields) {
            if (campo.getType().getName().equals(List.class.getName())) {
                campo.setAccessible(true);
                try {
                    campo.set(instancia, new ArrayList<>());

                    //System.out.println("Lista Auto Instanciada" + campo.getName());
                } catch (Throwable ex) {
                    Logger.getLogger(UtilSBCoreReflexao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resposta;
    }

    /**
     * Retorna o Siple-name da classe (Nome dela sem o caminho completo)
     *
     * @param pNomeCompleto
     * @return
     */
    public static String getSimpleNamebyString(String pNomeCompleto) {
        return getCaminhoParcialFinalByFullName(pNomeCompleto, 1);
    }

    /**
     *
     * * Retorna final do Camino da Classe segundo o numero de camadas
     * solicitada
     *
     * @param nomecompleto
     * @param pNCasas
     * @return
     */
    public static String getCaminhoParcialFinalByFullName(String nomecompleto, int pNCasas) {
        String[] casas = nomecompleto.split("\\.");
        int ultimacasa = casas.length - 1;
        String resposta = "";
        for (int i = ultimacasa; i > ultimacasa - pNCasas; i--) {
            if (i == ultimacasa) {
                resposta = casas[i];
            } else {
                resposta = casas[i] + "." + resposta;
            }
        }
        return resposta;

    }

    /**
     *
     * Retorna inicio do Camino da Classe segundo o numero de camadas solicitada
     *
     * @param nomecompleto
     * @param pNCasas
     * @return
     */
    public static String getCaminhoParcialInicialByFullName(String nomecompleto, int pNCasas) {

        String[] casas = nomecompleto.split("\\.");
        int ultimacasa = casas.length - 1;
        String resposta = "";
        for (int i = 0; i <= pNCasas - 1; i++) {
            if (i == 0) {
                resposta = casas[i];
            } else {
                resposta = resposta + "." + casas[i];
            }
        }
        return resposta;

    }

    /**
     * Retorna todas as classes do Pacote Utilizando a Biblioteca
     * org.Reflections. #######################################################
     * !!! Alerta !!!!!!!!!!!!! cuidado ao Utilizar este comando pois a
     * performance não é imediata, Utilize intefaces com services para carregar
     * classes dinamicas de nameira rápida, o ideal é utilizar este código
     * apenas em inicializações de sistemas
     *
     * @param CaminhoPacote
     * @return
     */
    public static List<Class> getClassesDoPacote(String pCaminhoPacote) {

        /**
         * Informações úteis sobre a biblioteca: // O parâmetro new
         * SubTypesScanner(false) permite a listagem de classes através
         * deObject.class // `, isto é, classes que não tem herança
         * explícita.Caso contrário essas classes seriam ignoradas // .
         *
         *
         * parâmetro ClasspathHelper.forClassLoader() lista todas as classes do
         * Class Loader atual, caso contrário seriam listadas apenas as classes
         * do projeto/jar atual.
         *
         *
         * Somente tenha cuidado se alguma classe que não puder ser carregada
         * (talvez por falta de uma dependência), senão você acaba com uma
         * exceção como essa:
         *
         * at org.reflections.ReflectionUtils.forName(ReflectionUtils.java:378)
         * at org.reflections.ReflectionUtils.forNames(ReflectionUtils.java:387)
         * at org.reflections.Reflections.getSubTypesOf(Reflections.java:338) at
         * snippet.ListClasses.main(ListClasses.java:15)
         */
        Reflections r = new Reflections(
                pCaminhoPacote,
                new SubTypesScanner(false),
                ClasspathHelper.forClassLoader()
        );
        Set<Class<?>> classes = r.getSubTypesOf(Object.class);
        List<Class> resposta = new ArrayList<>();
        resposta.addAll(classes);
        //exibe a lista classes
        for (Class<?> c : classes) {
            System.out.println(c.getName());
        }
        return resposta;
    }

    /**
     *
     *
     * Retorna a classe que extende determinado tipo de classe
     *
     * TODO: DISPARAR ERRO QUANDO ENCONTRAR MAIS DE UMA
     *
     * @param pTipo
     * @param pCaminhoPacote
     * @return
     */
    public static Class getClasseQueEstendeIsto(Class pTipo, String pCaminhoPacote) {

        /**
         * Informações úteis sobre a biblioteca: // O parâmetro new
         * SubTypesScanner(false) permite a listagem de classes através
         * deObject.class // `, isto é, classes que não tem herança
         * explícita.Caso contrário essas classes seriam ignoradas // .
         *
         *
         * parâmetro ClasspathHelper.forClassLoader() lista todas as classes do
         * Class Loader atual, caso contrário seriam listadas apenas as classes
         * do projeto/jar atual.
         *
         *
         * Somente tenha cuidado se alguma classe que não puder ser carregada
         * (talvez por falta de uma dependência), senão você acaba com uma
         * exceção como essa:
         *
         * at org.reflections.ReflectionUtils.forName(ReflectionUtils.java:378)
         * at org.reflections.ReflectionUtils.forNames(ReflectionUtils.java:387)
         * at org.reflections.Reflections.getSubTypesOf(Reflections.java:338) at
         * snippet.ListClasses.main(ListClasses.java:15)
         */
        Reflections r = new Reflections(
                pCaminhoPacote,
                new SubTypesScanner(true),
                ClasspathHelper.forClassLoader()
        );
        Set<Class<?>> classes = r.getSubTypesOf(pTipo);

        //exibe a lista classes
        for (Class<?> c : classes) {
            return c;
        }
        return null;

    }

    /**
     *
     * Verifica se classe ou algum superType da classe implementa uma interface
     *
     * @param pClass Classe onde a interface é implementada
     * @param pInterface Interface que está sendo procurada
     * @return
     */
    @Transient
    public static boolean isInterfaceImplementadaNaClasse(Class pClass, Class pInterface) {
        /// NÃO FUNCIONA, PRECISA DE MELHORIAS
        boolean temMaisClasse = true;
        Class classe = pClass;
        while (temMaisClasse) {

            if (classe.isAssignableFrom(pInterface)) {
                return true;
            }
            if (classe.getSimpleName().equals(ItemSimples.class.getSimpleName())
                    || classe.getSimpleName().equals(Object.class.getSimpleName())) {
                temMaisClasse = false;
            }
            classe = classe.getSuperclass();
            if (classe == null) {
                return false;
            }
        }
        return false;
    }

    public static List<Class> getClassesComEstaAnotacao(Class pAnotacao, String pCaminhoPacote) {

        List<Class> lista = new ArrayList<>();

        Reflections reflections = new Reflections(pCaminhoPacote);

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(pAnotacao);

        //exibe a lista classes
        for (Class<?> c : annotated) {
            lista.add(c);
        }
        return lista;
    }

    public static Method getMetodoByAcao(ItfAcaoController pAcaoDoSistema) {
        ItfAcaoController acaocontroller = (ItfAcaoController) pAcaoDoSistema;
        Method metodo = SBCore.getConfiguradorDePermissao().getMetodoByAcao(pAcaoDoSistema);
        return metodo;
    }

    public static String getNomeDoObjeto(Class pClasse) {
        throw new UnsupportedOperationException("Nome do Objeto por Reflexão não foi implementado");
    }

}
