/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
     * org.Reflections. <br>
     * !!! Alerta !!!!!!!!!!!!! cuidado ao Utilizar este comando pois a <br>
     * performance não é imediata, Utilize intefaces com services para carregar
     * <br>
     * classes dinamicas de nameira rápida, o ideal é utilizar este código<br>
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

        try {
            ItfAcaoController acaocontroller = (ItfAcaoController) pAcaoDoSistema;
            Method metodo = SBCore.getConfiguradorDePermissao().getMetodoByAcao(pAcaoDoSistema);
            if (metodo == null) {
                throw new Throwable("Método não foi encontrado para a ação" + pAcaoDoSistema.getNomeUnico());
            }
            return metodo;
        } catch (Throwable t) {
            String nomeAcao = "Acao não enviada";
            if (pAcaoDoSistema != null) {
                nomeAcao = pAcaoDoSistema.getNomeUnico();
            }

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando obterMetodo por ação, Verifique se existe o método implementado para a ação " + nomeAcao, t);
        }
        return null;

    }

    public static String getNomeDoObjeto(Class pClasse) {

        try {
            if (!pClasse.isAnnotationPresent(InfoClasse.class)) {
                throw new UnsupportedOperationException("A classe " + pClasse + " não foi anotada com InfoClasse");
            }

            InfoClasse info = (InfoClasse) pClasse.getAnnotation(InfoClasse.class);
            return info.tags()[0];
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.LANCAR_EXCECÃO, "", t);
            return null;
        }

    }

    public static String getIconeDoObjeto(Class pClasse) {

        try {
            if (!pClasse.isAnnotationPresent(InfoClasse.class)) {
                throw new UnsupportedOperationException("A classe " + pClasse + " não foi anotada com InfoClasse");
            }

            InfoClasse info = (InfoClasse) pClasse.getAnnotation(InfoClasse.class);
            return info.icone();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.LANCAR_EXCECÃO, "", t);
            return null;
        }

    }

    /**
     *
     *
     *
     * Retorna uma fabrica, de acordo com o nome do parametro da anotação.
     *
     * Exemplo: uma classe anotada com
     *
     * @InfoModulo(modulo=MinhaFabricaDeModulos.moduloPrincipal)
     *
     * Retornaria o fabrica do moduloPrincipal, quando pesquisado
     * pNomemetodoAnotacao=modulo
     *
     *
     * @param pClasse A classe onde a anotação será pesquisada
     * @param pNomeMetodoAnotacao O nome do atributo que será pesquisado
     * @param pararSistemaCasoNaoEncontre boolean informando se um paratudo deve
     * ser gerado em caso de erro
     * @return
     */
    public static ItfFabrica getFabricaDaClasseByAnotacao(Class pClasse, String pNomeMetodoAnotacao, boolean pararSistemaCasoNaoEncontre) {
        try {

            Annotation[] anotacoes = pClasse.getAnnotations();
            try {
                if (anotacoes != null) {

                    for (Annotation a : anotacoes) {

                        try {

                            Method metodo = a.getClass().getMethod(pNomeMetodoAnotacao);
                            try {

                                ItfFabrica fabrica = (ItfFabrica) metodo.invoke(a);
                                return fabrica;

                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

                            }
                        } catch (NoSuchMethodException | SecurityException ex) {

                        }
                    }

                    throw new UnsupportedOperationException("Anotação de ação não foi encontrada no método" + pClasse.getName());

                }
            } catch (Throwable t) {
                FabErro.PARA_TUDO.paraSistema("Erro tentando obeter a Fabrica a partir da classe certifique que a classe tenha uma anotação informando o parametro " + pNomeMetodoAnotacao + " vinculado a" + pClasse.getName(), t);
            }
            return null;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação por Método", t);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação para o  Método" + pClasse.getName() + " em " + pClasse.getDeclaringClass().getSimpleName(), t);
            FabErro.PARA_TUDO.paraSistema("Erro Para Tudo obtendo ação pelo id do metodo" + pClasse.getName(), t);
            return null;
        }
    }

    /**
     *
     *
     *
     * Retorna uma fabrica, de acordo com o nome do parametro da anotação.
     *
     * Exemplo: um metodo anotado com
     *
     * @InfoModulo(modulo=MinhaFabricaDeModulos.moduloPrincipal)
     *
     * Retornaria o fabrica do moduloPrincipal, quando pesquisado
     * pNomemetodoAnotacao=modulo
     *
     *
     * @param pMetodo O Método onde a anotação será pesquisada
     * @param pNomeMetodoAnotacao O nome do atributo que será pesquisado
     * @param pararSistemaCasoNaoEncontre boolean informando se um paratudo deve
     * ser gerado em caso de erro
     * @return
     */
    public static ItfFabrica getFabricaDoMetodoByAnotacao(Method pMetodo, String pNomeMetodoAnotacao, boolean pararSistemaCasoNaoEncontre) {
        try {

            Annotation[] anotacoes = pMetodo.getAnnotations();
            try {
                if (anotacoes != null) {

                    for (Annotation a : anotacoes) {

                        try {

                            Method metodo = a.getClass().getMethod(pNomeMetodoAnotacao);
                            try {

                                ItfFabrica fabrica = (ItfFabrica) metodo.invoke(a);
                                return fabrica;

                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {

                            }
                        } catch (NoSuchMethodException | SecurityException ex) {

                        }
                    }

                    throw new UnsupportedOperationException("Anotação de ação não foi encontrada no método" + pMetodo.getName());

                }
            } catch (Throwable t) {
                FabErro.PARA_TUDO.paraSistema("Erro tentando obeter a Fabrica de acao a partir do metodo certifique que os metodos da classe de controler tenha uma anotação informando o parametro " + pNomeMetodoAnotacao + " no metodo " + pMetodo.getName(), null);
            }
            return null;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação por Método", t);
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo Ação para o  Método" + pMetodo.getName() + " em " + pMetodo.getDeclaringClass().getSimpleName(), t);
            FabErro.PARA_TUDO.paraSistema("Erro Para Tudo obtendo ação pelo id do metodo" + pMetodo.getName(), t);
            return null;
        }
    }

}
