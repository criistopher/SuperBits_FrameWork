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

/**
 *
 * @author Salvio
 */
public class UtilSBCoreReflexaoCampos {

    public static final Map<Class, List<CaminhoCampoReflexao>> CAMPOS_DA_CLASSE = new HashMap<>();
    public static final Map<String, CaminhoCampoReflexao> CAMINHO_CAMPO_POR_NOME = new HashMap<>();
    public static final Map<Class, List<CaminhoCampoReflexao>> ENTIDADES_DA_CLASSE = new HashMap<>();//ok
    public static final Map<String, Class> CLASSE_POR_CAMINHO = new HashMap<>();//ok
    public static final Map<Class, Boolean> CLASSE_CONFIGURADA = new HashMap<>();//ok

    private static List<CaminhoCampoReflexao> buildEntidadesDaClasse() {
        throw new UnsupportedOperationException("");
    }

    public static void buildCamposDaClasse(Class pClasse) {

        try {
            if (CLASSE_CONFIGURADA.get(pClasse) != null) {
                if (CLASSE_CONFIGURADA.get(pClasse)) {
                    return;
                }
            }
            List<CaminhoCampoReflexao> lista = getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(pClasse);
            ENTIDADES_DA_CLASSE.put(pClasse, lista);
            for (CaminhoCampoReflexao cp : lista) {
                System.out.println("Entidade" + cp.getCaminhoString());
            }
            for (CaminhoCampoReflexao caminho : lista) {
                //         List<CaminhoCampoReflexao> //List<CaminhoCampoReflexao> camposDaClasse = new ArrayList<>();
                String inicio = caminho.getCaminhoString();
                boolean raiz = false;
                if (!inicio.contains(".")) {
                    raiz = true;
                }
                Class classeAtual = CLASSE_POR_CAMINHO.get(caminho.getCaminhoString());
                System.out.println("Add entidade da classe:" + caminho.getCaminhoString());
                List<Class> classesComCampos = new ArrayList<>();
                boolean chegouAoFim = false;
                Class classeatualiza = classeAtual;
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
                        CAMINHO_CAMPO_POR_NOME.put(caminhostr, caminho);
                        System.out.println("Add Campo da classe:" + caminhostr);
                    }
                }
                CLASSE_CONFIGURADA.put(pClasse, true);
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro construindo ", t);
        }
    }

    private static void getCamposDeEntidadeDaClasse(Class pClasse) {

        if (ENTIDADES_DA_CLASSE.get(pClasse) == null) {
            List<CaminhoCampoReflexao> lista = getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(pClasse);

        }

    }

    public static boolean isClasseBasicaSB(Class pClasse) {
        // todo Tratar EntidadeGenerica gerar trhow se Chegar no Object
        return (pClasse == ItemGenerico.class || pClasse == Object.class);

    }

    public static String getCaminhoSemNomeClasse(String pCaminho) {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static List<CaminhoCampoReflexao> getTodosCamposDaEntidade(Class pclasse) {
        throw new UnsupportedOperationException("Ainda não implementado");
    }

    public static CaminhoCampoReflexao getCaminhoCampoByString(Class pClasse, String caminho) throws InstantiationException, IllegalAccessException {
        throw new UnsupportedOperationException("Ainda não implementado");
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
     * Retorna os campos da classe que são compatíveis com IItfItemSimples
     * encontrados na classe
     *
     * @param pClasse
     * @param pCaminho
     * @return
     */
    public static List<CaminhoCampoReflexao> getTodosCamposAnotadosComManyToOne(Class pClasse, String pCaminho) {

        if (pClasse.getSimpleName().equals("DocumentoFornecedor")) {
            System.out.println("DocumentoFornecedor");
        }

        List<CaminhoCampoReflexao> lista = new ArrayList<>();
        while (!isClasseBasicaSB(pClasse)) {
            List<Field> itensdoNivel = new ArrayList<>();
            Field[] fields = pClasse.getDeclaredFields();
            for (Field campo : fields) {
                if (campo.isAnnotationPresent(ManyToOne.class)) {
                    String caminhocampo = pCaminho + "." + campo.getName();
                    CaminhoCampoReflexao caminhoCR = new CaminhoCampoReflexao(caminhocampo, campo);
                    lista.add(caminhoCR);
                    CLASSE_POR_CAMINHO.put(caminhocampo, campo.getType());

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
        System.out.println("Caminho anterior:" + pCaminhoAnterior);
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
     * Retorna os Fields que sejam compativeis com ItfItemSimples em ordem de
     * Itens filho para pai
     *
     * @param pClasse classe que será analizada
     * @return Fileds Do tipo item Simples
     */
    public static List<CaminhoCampoReflexao> getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(Class pClasse) {
        List<CaminhoCampoReflexao> camposEncontradosNivel0 = getTodosCamposAnotadosComManyToOne(pClasse, pClasse.getSimpleName());

        List<CaminhoCampoReflexao> camposEncontrados = new ArrayList<>(); // adiciona campos no nivel 0
        List<CaminhoCampoReflexao> camposDonivel = camposEncontradosNivel0;
        if (!camposEncontradosNivel0.isEmpty()) {

            for (CaminhoCampoReflexao camposFilho : camposEncontradosNivel0) {
                camposDonivel = getCamposReflectionFilho(null, camposDonivel, pClasse.getSimpleName(), pClasse);
                camposEncontrados.addAll(camposDonivel);
            }
        }
        // para cada campo encontrado no nivel 0
        Collections.reverse(camposEncontradosNivel0);

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

    /**
     *
     * Retorna o Valor da propriedade de acordo com a anotação
     *
     * @param tipoCampo Tipo do campo procurado
     * @return Valor da propriedade pojo anotada com o campo procurado
     */
    protected Object getValorByTipoCampoEsperado(FabCampos tipoCampo, Object instancia) {
        throw new UnsupportedOperationException();
    }

}
