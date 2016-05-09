/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.InfoCampos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author Salvio
 */
public class UtilSBCoreReflexaoCampos {

    public static final Map<Class, List<CaminhoCampoReflexao>> CAMPOS_DA_CLASSE = new HashMap<>();
    public static final Map<String, Class> CLASSE_ENTIDADE_BY_NOME = new HashMap<>();
    public static final Map<String, CaminhoCampoReflexao> CAMINHO_CAMPO_POR_NOME = new HashMap<>();//ok
    public static final Map<Class, List<CaminhoCampoReflexao>> ENTIDADES_DA_CLASSE = new HashMap<>();//ok
    public static final Map<String, Class> CLASSE_ENTIDADE_BY_CAMINHO = new HashMap<>();//ok
    public static final Map<Class, Boolean> CLASSE_CONFIGURADA = new HashMap<>();//ok
    private static boolean todasClassesConfiguradas = false;

    public static boolean isTodasClassesConfiguradas() {
        return todasClassesConfiguradas;

    }

    public static void configurarTodasAsClasses(List<Class> entidades) {
        try {
            if (!todasClassesConfiguradas) {
                for (Class entidade : entidades) {
                    System.out.println("Configurando campos de:" + entidade.getSimpleName());
                    if (!entidade.getSimpleName().contains("Acao")) {

                        UtilSBCoreReflexaoCampos.buildCamposDaClasse(entidade);
                    }

                }
            } else {
                throw new UnsupportedOperationException("As classes já foram configuras");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve um erro configurando todas as classes", t);
            todasClassesConfiguradas = false;
        }

        todasClassesConfiguradas = true;
    }

    /**
     *
     * Retorna os Fields que sejam compativeis com ItfItemSimples em ordem de
     * Itens filho para pai
     *
     * @param pClasse classe que será analizada
     * @return Fileds Do tipo item Simples
     */
    private static List<CaminhoCampoReflexao> makeCaminhosDeCamposDoTipoEntidadePossiveisDaClasse(Class pClasse) {

        //cria Campos Encontrados para retorno final
        List<CaminhoCampoReflexao> camposEncontrados = new ArrayList<>(); // adiciona campos no nivel 0

        // Pega todos os campos que são outras tabelas do nivel 0
        List<CaminhoCampoReflexao> camposEncontradosNivel0 = getTodosCamposAnotadosComManyToOne(pClasse, pClasse.getSimpleName());

        List<CaminhoCampoReflexao> camposDonivel = camposEncontradosNivel0;
        // se tiver subCampos de outras tabelas
        if (!camposEncontradosNivel0.isEmpty()) {
            // para cada campo do tipo tabela do nivel zero
            for (CaminhoCampoReflexao campoFilho : camposEncontradosNivel0) {
                // pega os campos do tipo Tabela do campo
                List<CaminhoCampoReflexao> camposDoFilho = getTodosCamposAnotadosComManyToOne(campoFilho.getCampoFieldReflection().getType(), campoFilho.getCaminhoString());
                // paga campos e subcampos do filho
                camposDonivel = getCamposReflectionFilho(null, camposDoFilho, campoFilho.getCaminhoString(), pClasse);
                camposEncontrados.addAll(camposDonivel);
            }
        }
        // para cada campo encontrado no nivel 0
        Collections.reverse(camposEncontradosNivel0);

        return camposEncontrados;

    }

    private static void checaEControi(Class pClasse) {
        if (!CLASSE_CONFIGURADA.get(pClasse)) {
            buildCamposDaClasse(pClasse);
        }
    }

    private static List<CaminhoCampoReflexao> buildCamposNivel1(Class pClasse, String caminho) {

        String inicio = caminho;
        boolean raiz = false;
        if (!inicio.contains(".")) {
            raiz = true;
        }

        List<Class> classesComCampos = new ArrayList<>();
        boolean chegouAoFim = false;
        Class classeatualiza = pClasse;
        List<CaminhoCampoReflexao> camposEncontrados = new ArrayList<>();
        // Obtendo todos os campos com subcampos
        while (!chegouAoFim) {
            classesComCampos.add(classeatualiza);
            if (isClasseBasicaSB(pClasse)) {
                chegouAoFim = true;
            }
            if (classeatualiza.getAnnotation(javax.persistence.Entity.class) == null
                    && (classeatualiza.getAnnotation(Embeddable.class) == null)) {
                chegouAoFim = true;
            }
            classeatualiza = classeatualiza.getSuperclass();
        }

        for (Class classeCampos : classesComCampos) {
            Field[] camposDaClasse = classeCampos.getDeclaredFields();

            for (Field campo : camposDaClasse) {
                String caminhostr = inicio + "." + campo.getName();
                CaminhoCampoReflexao cm = new CaminhoCampoReflexao(caminhostr, campo);
                CAMINHO_CAMPO_POR_NOME.put(caminhostr, cm);
                camposEncontrados.add(cm);
                //              System.out.println("Add Campo da classe:" + caminhostr);
            }
        }
        return camposEncontrados;
    }

    /**
     *
     * Constroi o mapeamento de todos os campos de classe do sistema
     *
     * @param pClasse
     */
    public static void buildCamposDaClasse(Class pClasse) {

        try {
            if (CLASSE_CONFIGURADA.get(pClasse) != null) {
                if (CLASSE_CONFIGURADA.get(pClasse)) {
                    return;
                }
            }
            List<CaminhoCampoReflexao> lista = makeCaminhosDeCamposDoTipoEntidadePossiveisDaClasse(pClasse);

            ENTIDADES_DA_CLASSE.put(pClasse, lista);
            CLASSE_ENTIDADE_BY_NOME.put(pClasse.getSimpleName(), pClasse);

            if (pClasse.getSimpleName().equals("Fornecedor")) {
                System.out.println("Fornecedor");
            }
            List<CaminhoCampoReflexao> caminhosCampoDaClasse = new ArrayList<>();
            caminhosCampoDaClasse.addAll(buildCamposNivel1(pClasse, pClasse.getSimpleName()));

            for (CaminhoCampoReflexao caminho : lista) {

                //         List<CaminhoCampoReflexao> //List<CaminhoCampoReflexao> camposDaClasse = new ArrayList<>();
                Class classeAtual = CLASSE_ENTIDADE_BY_CAMINHO.get(caminho.getCaminhoString());
                //     System.out.println("Add entidade da classe:" + caminho.getCaminhoString());
                caminhosCampoDaClasse.addAll(buildCamposNivel1(classeAtual, caminho.getCaminhoString()));

            }
            CLASSE_CONFIGURADA.put(pClasse, true);
            CAMPOS_DA_CLASSE.put(pClasse, caminhosCampoDaClasse);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro construindo ", t);
        }
    }

    /**
     *
     * Retorna o caminho de acesso de todos os campos e subcampos de um classe
     *
     * @param pClasse Classe referencia
     * @return Todos os campos da classe referenciada
     */
    public static List<CaminhoCampoReflexao> getCamposDeEntidadeDaClasse(Class pClasse) {
        checaEControi(pClasse);
        return CAMPOS_DA_CLASSE.get(pClasse);

    }

    public static List<CaminhoCampoReflexao> getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(Class pClasse) {
        try {
            checaEControi(pClasse);

            List<CaminhoCampoReflexao> retorno = ENTIDADES_DA_CLASSE.get(pClasse);
            if (retorno == null) {
                throw new UnsupportedOperationException("As entidades da classe" + pClasse + " não foram encontradas na constante ENTIDADES_DA_CLASSE");
            }
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Campos de entidade da classe " + pClasse, t);
        }
        return null;
    }

    private static Class getclassePorNome(String nome) {
        try {

            Class retorno = CLASSE_ENTIDADE_BY_NOME.get(nome);
            if (retorno == null) {
                if (todasClassesConfiguradas) {
                    throw new UnsupportedOperationException("A classe não foi encontrada, a função Configurar Todas as classe não foi executada");
                }
            } else {
                return retorno;
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtando classe por nome", t);
        }
        return null;
    }

    public static Class getClassePrincipalPorNome(String pNome) {
        String[] nomes = pNome.split("/.");
        System.out.println("Obtendo classe por nome" + pNome);
        if (nomes.length < 1) {

            return getclassePorNome(pNome);
        } else {
            return getclassePorNome(nomes[0]);
        }

    }

    public static CaminhoCampoReflexao getCaminhoCAmpoByString(String pCaminho) {
        try {
            if (getClassePrincipalPorNome(pCaminho) == null) {
                throw new UnsupportedOperationException("A classe principal para " + pCaminho + "não foi encontrada");
            }

            CaminhoCampoReflexao caminho = CAMINHO_CAMPO_POR_NOME.get(pCaminho);

            if (caminho != null) {
                return caminho;
            } else {
                throw new UnsupportedOperationException("O caminho" + pCaminho + "não foi encontrado");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo caminho do campo a partir da String :" + pCaminho, t);
        }
        return null;
    }

    public static CaminhoCampoReflexao getCaminhoByStringRelativaEClasse(String pCaminho, Class pClase) {
        try {
            if (getClassePrincipalPorNome(pClase.getSimpleName()) == null) {
                throw new UnsupportedOperationException("A classe principal para " + pCaminho + "não foi encontrada");
            }

            String caminhoCompleto = pClase.getSimpleName() + "." + pCaminho;
            CaminhoCampoReflexao caminho = CAMINHO_CAMPO_POR_NOME.get(caminhoCompleto);

            if (caminho == null) {
                throw new UnsupportedOperationException("Não foi possivel encontrar o campo pelo caminho " + pCaminho + " na classe " + pClase.getSimpleName());
            }
            return caminho;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo caminho do campo a partir de caminho relativo:" + pCaminho + " na classe" + pClase.getSimpleName(), t);

        }
        return null;
    }

    public static boolean isClasseBasicaSB(Class pClasse) {
        // todo Tratar EntidadeGenerica gerar trhow se Chegar no Object
        return (pClasse == ItemGenerico.class
                || pClasse == Object.class);

    }

    public static String getCaminhoSemNomeClasse(String pCaminho) {
        throw new UnsupportedOperationException("");
    }

    public static List<CaminhoCampoReflexao> getTodosCamposDaEntidade(Class pclasse) {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static CaminhoCampoReflexao getCaminhoCampoByString(Class pClasse, String caminho) {
        return getCaminhoByStringRelativaEClasse(caminho, pClasse);
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
                if (field.getClass().isAssignableFrom(ItfBeanSimples.class
                )) {
                    lista.add(field);

                }
            }
            pClasse = pClasse.getSuperclass();
        }
        return lista;
    }

    /**
     *
     * Retorna os campos da classe que são compatíveis com IItfItemSimples
     * encontrados na classe
     *
     * @param pClasse
     * @param pCaminho
     * @return
     */
    public static List<CaminhoCampoReflexao> getTodosCamposAnotadosComManyToOne(Class pClasse, String pCaminho) {

        List<CaminhoCampoReflexao> lista = new ArrayList<>();
        while (!isClasseBasicaSB(pClasse)) {
            List<Field> itensdoNivel = new ArrayList<>();
            Field[] fields = pClasse.getDeclaredFields();

            for (Field campo : fields) {
                if (campo.isAnnotationPresent(ManyToOne.class
                )) {
                    String caminhocampo = pCaminho + "." + campo.getName();
                    CaminhoCampoReflexao caminhoCR = new CaminhoCampoReflexao(caminhocampo, campo);
                    lista.add(caminhoCR);
                    CLASSE_ENTIDADE_BY_CAMINHO.put(caminhocampo, campo.getType());

                }
            }

            pClasse = pClasse.getSuperclass();
        }

        return lista;
    }

    private static List<CaminhoCampoReflexao> getCamposReflectionFilho(List<CaminhoCampoReflexao> pListaAtualizada, List<CaminhoCampoReflexao> pListaNova, String pCaminhoAnterior, Class pClasePrincipal) {
        if (pListaAtualizada == null) {
            pListaAtualizada = new ArrayList<>();
        }
        //     System.out.println("Caminho anterior:" + pCaminhoAnterior);
        for (CaminhoCampoReflexao cm : pListaNova) {
            pListaAtualizada.add(cm);
        }

        for (CaminhoCampoReflexao cm : pListaNova) {
            Class classe = cm.getCampoFieldReflection().getType();
            if (!classe.getSimpleName().equals(pClasePrincipal.getSimpleName())) {
                List<CaminhoCampoReflexao> novosCamposEncontrados = getTodosCamposAnotadosComManyToOne(classe, pCaminhoAnterior + "." + cm.getCampoFieldReflection().getName());
                return getCamposReflectionFilho(pListaAtualizada, novosCamposEncontrados, pCaminhoAnterior + "." + cm.getCampoFieldReflection().getName(), pClasePrincipal);
            }

        }
        return pListaAtualizada;

    }

    /**
     *
     * @param pClasse Classe referenciada
     * @param pTipoCampo Tipo de campo refereniado
     * @return Primeiro Field (pacote Reflection) que tenha a anotação deste
     * tipo de campo
     */
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

    /**
     *
     * @param pClasse Classe onde a anotação será procurada
     * @param anotacao Anotação Referenciada
     * @return O primeiro Field( pacote Reflection) encontrado com esta anotação
     */
    public static Field getFieldByClasseAnotacao(Class pClasse, Class anotacao) {
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
