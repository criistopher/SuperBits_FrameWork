/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.beans.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author salvioF
 */
@Deprecated
public class UtilSBCoreReflexaoCamposOldReferencia {

    // Tabela contendo nome da classe, e classe
    private static final Map<String, Class> CLASSE_ENTIDADE_BY_NOME = new HashMap<>();

    @Deprecated
    private static final Map<Class, Map<String, CaminhoCampoReflexao>> CAMPOS_DA_CLASSE = new HashMap<>();
    @Deprecated
    private static final Map<String, CaminhoCampoReflexao> CAMINHO_CAMPO_POR_NOME = new HashMap<>();//ok
    @Deprecated
    private static final Map<Class, Map<String, CaminhoCampoReflexao>> ENTIDADES_DA_CLASSE = new HashMap<>();//ok
    @Deprecated
    private static final Map<String, Class> CLASSE_ENTIDADE_BY_CAMINHO = new HashMap<>();//ok
    @Deprecated
    private static final Map<Class, Boolean> CLASSE_CONFIGURADA = new HashMap<>();//ok
    @Deprecated
    private static final int LIMITE_SEQUENCIA_CAMPO = 6;

    /**
     *
     * A TAG SEPARADOR É A TAG QUE IDENTIFICA UM NOME DE CAMPO COMO SEPARADOR
     *
     * A SINTAXE PARA UM CAMPO SEPARADOR É [SEPARADOR:Nome do Separador]
     *
     */
    private static final String TAG_SEPARADOR = "[separador";
    @Deprecated
    private static boolean todasClassesConfiguradas = false;

    private static boolean isTodasClassesConfiguradas() {
        return todasClassesConfiguradas;

    }

    public static boolean isUmCampoSeparador(String pNomeSeparador) {
        return pNomeSeparador.contains(TAG_SEPARADOR);
    }

    /**
     *
     *
     * Obteem o nome do grupo pela String do tipo Separador, ou seja:
     *
     * uma string com este valor [separador:Dados Simples], retorna Dados
     * Simmples
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     *
     *
     * @param pSeparador String contendo campo Separador
     * @return
     */
    public static String getNomeGrupoPorStrSeparador(String pSeparador) {
        if (!isUmCampoSeparador(pSeparador)) {
            throw new UnsupportedOperationException("A string não parece ser referente a um separador, ->" + pSeparador);
        }

        String[] partes = pSeparador.split(":");
        if (partes.length < 2) {
            return null;
        }
        return partes[1].replace("]", "");

    }

    /**
     * Verifica se a classe já foi utilizada no caminho
     */
    @Deprecated
    private static boolean isUmaEntidadeJaAlcancadaNoCaminho(CaminhoCampoReflexao pCaminho) {

        Class classe = pCaminho.getTipoCampo();
        List<Class> classesEncontradas = new ArrayList<>();
        for (String parte : pCaminho.getTodosCaminhosPossiveisSemUltimoParametro()) {

            Class classeDaParte = CLASSE_ENTIDADE_BY_CAMINHO.get(parte);
            if (classeDaParte != null) {
                if (classesEncontradas.contains(classeDaParte)) {
                    return true;
                }

                if (classeDaParte.getSimpleName().equals(classe.getSimpleName())) {
                    return true;
                }
                classesEncontradas.add(classeDaParte);
            } else {
                throw new UnsupportedOperationException("Impossivel determinar a classe para [" + parte + "]");
            }
        }

        return false;
    }

    /**
     * Cria uma lista com todas as possibilidades de informação para acesso a
     * campos possíveis
     *
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     * @param entidades a lista de entidades que a lista deve ser criada
     */
    @Deprecated()
    private static void configurarTodasAsClasses(List<Class> entidades) {
        configurarTodasAsClasses(entidades);
        if (true) {
            return;
        }
        try {
            if (!todasClassesConfiguradas) {
                for (Class entidade : entidades) {
                    System.out.println("Configurando campos de:" + entidade.getSimpleName());
                    if (!entidade.getSimpleName().contains("Acao")) {

                        buildCamposDaClasse(entidade);
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
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     *
     * @param pListaAtualizada
     * @param pListaNova
     * @param pCaminhoAnterior
     * @return
     */
    @Deprecated
    private static Map<String, CaminhoCampoReflexao> getCamposReflectionFilho(
            Map<String, CaminhoCampoReflexao> pListaAtualizada,
            Map<String, CaminhoCampoReflexao> pListaNova,
            String pCaminhoAnterior) {
        if (pListaAtualizada == null) {
            pListaAtualizada = new HashMap<>();
        }

        // PARA CADA CAMPO DA LISTA NOVA ADICIONA O CAMPO NA LISTA ATUALIZADA
        for (CaminhoCampoReflexao cm : pListaNova.values()) {
            if (cm.getPartesCaminho().size() < LIMITE_SEQUENCIA_CAMPO && !isUmaEntidadeJaAlcancadaNoCaminho(cm)) {
                pListaAtualizada.put(cm.getCaminhoCompletoString(), cm);

                // Verificando se a entidade já foi alcaçada neste caminho para evitar loop
                Class classe = cm.getTipoCampo();

                // caso a entidade não tenha sido alcançada neste caminho adiciona como um caminho possivel
                //  System.out.println("Configurando SubCampos Para o campo:" + cm.getCaminhoCompletoString());
                Map<String, CaminhoCampoReflexao> novosCamposEncontrados = new HashMap<>();

                novosCamposEncontrados = getCamposComSubCamposDaClasse(classe, pCaminhoAnterior + "." + cm.getUtimoNome());
                if (!novosCamposEncontrados.isEmpty()) {
                    pListaAtualizada.putAll(getCamposReflectionFilho(pListaAtualizada, novosCamposEncontrados, pCaminhoAnterior + "." + cm.getUtimoNome()));
                }

            }

        }

        // PARA CADA CAMPO DA LISTA NOVA VERIFICA SE A ENTIDADE JÁ FOI ADICIONADA NO CAMINHO DO CAMPO E ADICIONA O NOVO CAMINHO
        return pListaAtualizada;

    }

    /**
     *
     * Retorna os Fields que sejam compativeis com ItfItemSimples em ordem de
     * Itens filho para pai (Retorna também os Campos do tipo lista que sejam
     * tipados com ItfBeanSimples
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     *
     * @param pClasse classe que será analizada
     * @return Fileds Do tipo item Simples
     */
    @Deprecated
    public static Map<String, CaminhoCampoReflexao> makeCaminhosDeCamposDoTipoEntidadePossiveisDaClasse(Class pClasse) {

        //cria Campos Encontrados para retorno final
        Map<String, CaminhoCampoReflexao> camposEncontrados = new HashMap<>(); // adiciona campos no nivel 0

        // Pega todos os campos que são outras tabelas do nivel 0
        Map<String, CaminhoCampoReflexao> camposdeEntidadeEncontradosNivel0 = getCamposComSubCamposDaClasse(pClasse, pClasse.getSimpleName());
        // adicionando Campos de entidade do tipo Lista

        Map<String, CaminhoCampoReflexao> camposDonivel;

        // se tiver subCampos de outras tabelas
        if (!camposdeEntidadeEncontradosNivel0.isEmpty()) {

            // para cada campo do tipo tabela do nivel zero
            for (CaminhoCampoReflexao campoFilho : camposdeEntidadeEncontradosNivel0.values()) {

                Class classeAtual = campoFilho.getTipoCampo();

                // pega os campos do tipo Tabela do campo
                Map<String, CaminhoCampoReflexao> camposDoFilho = getCamposComSubCamposDaClasse(classeAtual, campoFilho.getCaminhoCompletoString());

                // adicionando Campos de entidade do tipo Lista
                // paga campos e subcampos do filho
                Map<String, CaminhoCampoReflexao> novosCampos = getCamposReflectionFilho(null, camposDoFilho, campoFilho.getCaminhoCompletoString());
                camposDonivel = novosCampos;
                camposEncontrados.put(campoFilho.getCaminhoCompletoString(), campoFilho);
                camposEncontrados.putAll(camposDonivel);
            }
        }
        // para cada campo encontrado no nivel 0
        Collections.reverse(Lists.newArrayList(camposEncontrados.values()));

        return camposEncontrados;

    }

    /**
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     * @param pClasse
     */
    @Deprecated
    private static void checaEControi(Class pClasse) {
        if (CLASSE_CONFIGURADA.get(pClasse) == null) {
            buildCamposDaClasse(pClasse);
            return;
        }
        if (!CLASSE_CONFIGURADA.get(pClasse)) {
            buildCamposDaClasse(pClasse);
        }
    }

    /**
     * Pesquisa todos os campos da classe e seus sub-campos
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     *
     * @param pClasse
     * @param caminho
     * @return
     */
    @Deprecated
    private static Map<String, CaminhoCampoReflexao> buildCamposNivel1(Class pClasse, String caminho) {

        String inicio = caminho;

        List<Class> classesComCampos = new ArrayList<>();

        boolean chegouAoFim = false;
        Class classeatualiza = pClasse;

        // Listando todas as classes com a anotação Entity que extendem esta classe
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

        Map<String, CaminhoCampoReflexao> camposEncontrados = new HashMap<>();
        for (Class classeCampos : classesComCampos) {
            Field[] camposDaClasse = classeCampos.getDeclaredFields();

            for (Field campoReflecxao : camposDaClasse) {

                String caminhostr = inicio + "." + campoReflecxao.getName();
                CaminhoCampoReflexao cm = new CaminhoCampoReflexao(caminhostr);
                CAMINHO_CAMPO_POR_NOME.put(caminhostr, cm);
                camposEncontrados.put(cm.getCaminhoCompletoString(), cm);

                //              System.out.println("Add Campo da classe:" + caminhostr);
            }
        }
        return camposEncontrados;
    }

    /**
     *
     * Constroi o mapeamento de todos os campos possíveis obtidos através da
     * entidade
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     * @param pClasse
     * @return
     */
    @Deprecated
    private static Map<String, CaminhoCampoReflexao> buildCamposDaClasse(Class pClasse) {

        try {
            if (CLASSE_CONFIGURADA.get(pClasse) != null) {
                if (CLASSE_CONFIGURADA.get(pClasse)) {
                    return CAMPOS_DA_CLASSE.get(pClasse);
                }
            }
            CLASSE_ENTIDADE_BY_CAMINHO.put(pClasse.getSimpleName(), pClasse);
            // Lista todas as entidades ligadas a entidade principal, e o caminho para chegar até ela
            Map<String, CaminhoCampoReflexao> listaEntidadesPossiveis = makeCaminhosDeCamposDoTipoEntidadePossiveisDaClasse(pClasse);

            //
            ENTIDADES_DA_CLASSE.put(pClasse, listaEntidadesPossiveis);

            //
            CLASSE_ENTIDADE_BY_NOME.put(pClasse.getSimpleName(), pClasse);

            //
            Map<String, CaminhoCampoReflexao> listaCaminhosCampoDaClasse = new HashMap<>();
            //
            listaCaminhosCampoDaClasse.putAll(buildCamposNivel1(pClasse, pClasse.getSimpleName()));
            //
            for (CaminhoCampoReflexao caminho : listaEntidadesPossiveis.values()) {

                Class classeAtual = caminho.getTipoCampo();
                //
                listaCaminhosCampoDaClasse.put(caminho.getCaminhoCompletoString(), caminho);
                //
                listaCaminhosCampoDaClasse.putAll(buildCamposNivel1(classeAtual, caminho.getCaminhoCompletoString()));

            }
            //
            CLASSE_CONFIGURADA.put(pClasse, true);
            //
            CAMPOS_DA_CLASSE.put(pClasse, listaCaminhosCampoDaClasse);
            return listaCaminhosCampoDaClasse;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro construindo ", t);
            return null;
        }
    }

    /**
     *
     * Retorna o caminho de acesso de todos os campos e subcampos de um classe
     *
     * @param pClasse Classe referencia
     * @return Todos os campos da classe referenciada
     */
    @Deprecated
    public static List<CaminhoCampoReflexao> getCamposDeEntidadeDaClasse(Class pClasse) {
        checaEControi(pClasse);
        return Lists.newArrayList(CAMPOS_DA_CLASSE.get(pClasse).values());

    }

    /**
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     * @param pClasse
     * @return
     */
    @Deprecated
    private static Map<String, CaminhoCampoReflexao> getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(Class pClasse) {
        try {
            checaEControi(pClasse);

            Map<String, CaminhoCampoReflexao> retorno = ENTIDADES_DA_CLASSE.get(pClasse);
            if (retorno == null) {
                throw new UnsupportedOperationException("As entidades da classe" + pClasse + " não foram encontradas na constante ENTIDADES_DA_CLASSE");
            }
            return retorno;
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Campos de entidade da classe " + pClasse, t);
            return null;
        }

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

    /**
     *
     * O CaminhoCampoReflexão, possui uma string de referencia e o Campo Field
     * do java reflection referente a ele
     *
     * @param pCaminho
     * @return Retorna o Caminho campo da classe, ou gera um erro caso o
     * endereço não exista
     */
    private static CaminhoCampoReflexao getCaminhoCAmpoByString(String pCaminho) {
        try {
            //Valida se é uma String refente a uma separação de campo (do tipo [separador:NomeGrupo] (utilizado para agrupar os campos consecultivos de uma sequencia)
            if (isUmCampoSeparador(pCaminho)) {
                String caminhoCompleto = pCaminho;
                return new CaminhoCampoReflexao(caminhoCompleto);
            }
            /// aqui pode conter um erro (alterado ao desabilitar os metodos)
            Class entidade = CLASSE_ENTIDADE_BY_NOME.get(pCaminho);
            if (entidade == null) {
                throw new UnsupportedOperationException("A classe principal para " + pCaminho + "não foi encontrada");
            }

            CaminhoCampoReflexao caminho = CAMPOS_DA_CLASSE.get(entidade).get(pCaminho);

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

    /**
     *
     * O CaminhoCampoReflexão, possui uma string de referencia e o Campo Field
     * do java reflection referente a ele
     *
     * @param pClasse
     * @param pCaminho
     * @return Retorna o Caminho campo da classe, ou gera um erro caso o
     * endereço não exista
     */
    public static CaminhoCampoReflexao getCaminhoCAmpoByString(Class pClasse, String pCaminho) {

        try {

            String strCaminho = pCaminho;
            if (!UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(pCaminho)) {
                strCaminho = pClasse.getSimpleName() + "." + pCaminho;
            }

            CaminhoCampoReflexao caminho = CAMPOS_DA_CLASSE.get(pClasse).get(strCaminho);

            if (caminho != null) {
                return caminho;
            } else {
                throw new UnsupportedOperationException("O caminho" + pCaminho + "não foi encontrado");
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo caminho do campo a partir da String : [" + pCaminho + "] e classe" + pClasse.getSimpleName(), t);
        }
        return null;

    }

    private static Class getClassePrincipalPorNome(String nomeClasse) {
        return null;
    }

    /**
     *
     * Retorna todos os campos possíveis a partir do nome de uma classe
     *
     * @param nomeClasse O nome da classe referente aos campos que serão
     * retornados
     * @return Os campos da Classe
     */
    public static List<CaminhoCampoReflexao> getCamposDaClasse(String nomeClasse) {
        Class entidade = getClassePrincipalPorNome(nomeClasse);
        if (entidade == null) {
            throw new UnsupportedOperationException("A classe principal para " + nomeClasse + "não foi encontrada");
        }

        return Lists.newArrayList(CAMPOS_DA_CLASSE.get(entidade).values());
    }

    public static CaminhoCampoReflexao getCaminhoByStringRelativaEClasse(String pCaminho, Class pClase) {

        buildCamposDaClasse(pClase);

        try {
            if (isUmCampoSeparador(pCaminho)) {
                String caminhoCompleto = pClase.getSimpleName() + "." + pCaminho;
                return new CaminhoCampoReflexao(caminhoCompleto);
            }

            if (getClassePrincipalPorNome(pClase.getSimpleName()) == null) {
                throw new UnsupportedOperationException("A classe principal para " + pCaminho + "não foi encontrada");
            }

            //Confgurando caminho completo
            String caminhoCompleto = pCaminho;
            if (!UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(pCaminho)) {
                caminhoCompleto = pClase.getSimpleName() + "." + pCaminho;
            }

            CaminhoCampoReflexao caminho = CAMPOS_DA_CLASSE.get(pClase).get(caminhoCompleto);

            if (caminho == null) {
                for (String partes : caminhoCompleto.split("\\.")) {

                }
            }

            if (caminho == null) {
                throw new UnsupportedOperationException("Não foi possivel encontrar o campo pelo caminho [" + caminhoCompleto + "] na classe [" + pClase.getSimpleName() + "] ");
            }

            return caminho;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo caminho do campo a partir de caminho relativo:-->" + pCaminho + "<-- na classe:-->" + pClase.getSimpleName(), t);

        }
        return null;
    }

    public static boolean isClasseBasicaSB(Class pClasse) {

        if (pClasse == null) {
            throw new UnsupportedOperationException("enviado null para verificação de classe Basica");
        }

        // todo Tratar EntidadeGenerica gerar trhow se Chegar no Object
        return (pClasse == ItemGenerico.class
                || pClasse == Object.class
                || pClasse.getSimpleName().startsWith("Entidade"));

    }

    public static String getCaminhoSemNomeClasse(String pCaminho) {

        if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(pCaminho)) {
            return pCaminho;
        }
        //todo
        // split caminho
        // verfica se a a primeira letra do primeiro campo é maiusculo
        // retorna tudo menos o primeiro campo
        throw new UnsupportedOperationException("Não implementado, o nome da classe deve ser maiusculo, uma string retirando o primeiro nome do caminho deve ser enviada, caso a primeira leta não seja maiuscula, deve gerar uma execao");
    }

    public static List<CaminhoCampoReflexao> getTodosCamposDaEntidade(Class pclasse) {
        checaEControi(pclasse);
        return Lists.newArrayList(CAMPOS_DA_CLASSE.get(pclasse).values());
    }

    public static CaminhoCampoReflexao getCaminhoCampoByString(Class pClasse, String caminho) {
        checaEControi(pClasse);
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
        checaEControi(pClasse);
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
    public static Map<String, CaminhoCampoReflexao> getCamposAnotadosComManyToOneNaClasse(Class pClasse, String pCaminho) {

        if (pClasse == null) {
            throw new UnsupportedOperationException("Tentativa de obter campos Anotados com manyToOne sem enviar a classe de referencia nula com caminho: " + pCaminho);
        }

        Map<String, CaminhoCampoReflexao> lista = new HashMap<>();
        Class classeAtual = pClasse;

        while (!isClasseBasicaSB(classeAtual)) {

            if (classeAtual == null) {
                throw new UnsupportedOperationException("Erro obtendo classe superior em reflexao para obter campos manytone da classe" + pClasse);
            }

            Field[] fields = classeAtual.getDeclaredFields();

            for (Field campo : fields) {
                if (campo.isAnnotationPresent(ManyToOne.class
                )) {
                    String caminhocampo = pCaminho + "." + campo.getName();
                    CaminhoCampoReflexao caminhoCR = new CaminhoCampoReflexao(caminhocampo);
                    lista.put(caminhoCR.getCaminhoCompletoString(), caminhoCR);
                    //       CLASSE_ENTIDADE_BY_CAMINHO.put(caminhocampo, campo.getType());

                }
            }

            classeAtual = classeAtual.getSuperclass();
        }

        return lista;
    }

    public static Map<String, CaminhoCampoReflexao> getCamposComSubCamposDaClasse(Class pClasse, String pCaminho) {

        if (pClasse == null) {
            throw new UnsupportedOperationException("Tentativa de obter campos Anotados com manyToOne sem enviar a classe de referencia nula com caminho: " + pCaminho);
        }

        Map<String, CaminhoCampoReflexao> lista = new HashMap<>();
        Class classeAtual = pClasse;

        while (!isClasseBasicaSB(classeAtual)) {

            if (classeAtual == null) {
                throw new UnsupportedOperationException("Impossível percorrrer classes superiores de" + pClasse + " com caminho base=" + pCaminho);
            }

            Field[] fields = classeAtual.getDeclaredFields();

            for (Field campo : fields) {
                if (campo.isAnnotationPresent(ManyToOne.class
                ) || campo.isAnnotationPresent(OneToMany.class)
                        || campo.isAnnotationPresent(ManyToMany.class)) {
                    if (!campo.isAnnotationPresent(Transient.class)) {
                        String caminhocampo = pCaminho + "." + campo.getName();
                        CaminhoCampoReflexao caminhoCR = new CaminhoCampoReflexao(caminhocampo);
                        lista.put(caminhoCR.getCaminhoCompletoString(), caminhoCR);
                        CLASSE_ENTIDADE_BY_CAMINHO.put(caminhoCR.getCaminhoCompletoString(), caminhoCR.getTipoCampo());
                    }
                }

            }

            classeAtual = classeAtual.getSuperclass();
        }

        return lista;

    }

    /**
     *
     *
     *
     * @param pClasse
     * @param pCaminho
     * @return
     */
    public static Map<String, CaminhoCampoReflexao> getTodosCamposAnotadosComOneToManyOuManyToMany(Class pClasse, String pCaminho) {

        Map<String, CaminhoCampoReflexao> lista = new HashMap<>();
        while (!isClasseBasicaSB(pClasse)) {
            List<Field> itensdoNivel = new ArrayList<>();
            Field[] fields = pClasse.getDeclaredFields();

            for (Field campo : fields) {
                if (campo.isAnnotationPresent(OneToMany.class
                ) || campo.isAnnotationPresent(ManyToMany.class)) {
                    String caminhocampo = pCaminho + "." + campo.getName();
                    CaminhoCampoReflexao caminhoCR = new CaminhoCampoReflexao(caminhocampo);
                    lista.put(caminhoCR.getCaminhoCompletoString(), caminhoCR);
                    //      CLASSE_ENTIDADE_BY_CAMINHO.put(caminhocampo, campo.getType());

                }
            }

            pClasse = pClasse.getSuperclass();
        }

        return lista;
    }

    /**
     *
     * @param pClasse
     * @param pCaminho
     * @return
     */
    public static Map<String, CaminhoCampoReflexao> getTodosCamposDoTipoListaDaClasse(Class pClasse, String pCaminho) {

        Map<String, CaminhoCampoReflexao> lista = new HashMap<>();
        while (!isClasseBasicaSB(pClasse)) {
            List<Field> itensdoNivel = new ArrayList<>();
            Field[] fields = pClasse.getDeclaredFields();

            for (Field campo : fields) {
                if (campo.getType().getSimpleName().equals("List")) {
                    String caminhocampo = pCaminho + "." + campo.getName() + "[]";
                    CaminhoCampoReflexao caminhoCR = new CaminhoCampoReflexao(caminhocampo);
                    lista.put(caminhoCR.getCaminhoCompletoString(), caminhoCR);
                    //   CLASSE_ENTIDADE_BY_CAMINHO.put(caminhocampo, campo.getType());

                }
            }

            pClasse = pClasse.getSuperclass();
        }

        return lista;
    }

    /**
     *
     * Caso possua, retira o nome da classe principal do caminho para o campo
     *
     * @param pCaminhoCompleto
     * @return
     */
    public static String getStrCaminhoCampoSemNomeClassePrincipal(String pCaminhoCompleto) {

        return null;
    }

    /**
     *
     * Caso possua, retira o nome da classe principal do camiho para o campo,
     * caso possua uma classe principal no nome, e o nome da classe não seja o
     * enviado como referencia, gera um erro
     *
     * @param pCaminhoCompleto
     * @param pClasseReferencia
     * @return
     */
    public static String getStrCaminhoCampoSemNomeClassePrincipal(String pCaminhoCompleto, Class pClasseReferencia) {
        String[] partes = pCaminhoCompleto.split("\\.");

        boolean temNomeDaClasse = UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(partes[0]);

        if (temNomeDaClasse) {

            String nome = partes[0];
            if (!pClasseReferencia.getSimpleName().equals(nome)) {
                throw new UnsupportedOperationException("A classe principal encontrada na string [" + pCaminhoCompleto + "]" + " não confere com a classe Referencia: " + pClasseReferencia.getSimpleName());
            }
            String novoCaminho = "";
            int i = 0;
            for (String parte : partes) {
                if (i > 0) {
                    if (novoCaminho.length() > 0) {
                        novoCaminho += novoCaminho + "." + parte;
                    } else {
                        novoCaminho = parte;
                    }
                }

                i++;
            }

            return novoCaminho;
        } else {
            return pCaminhoCompleto;
        }
    }

}
