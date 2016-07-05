/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.InfoCampos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import static com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCamposOldReferencia.getCaminhoSemNomeClasse;
import static com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCamposOldReferencia.getNomeGrupoPorStrSeparador;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoInstanciadoSeparador;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Salvio
 */
public class UtilSBCoreReflexaoCampos {

    // Tabela contendo nome da classe, e classe
    private static final Map<String, Class> CLASSE_ENTIDADE_BY_NOME = new HashMap<>();
    private static boolean TODAS_CLASSES_CONFIGURADAS = false;
    /**
     *
     * A TAG SEPARADOR É A TAG QUE IDENTIFICA UM NOME DE CAMPO COMO SEPARADOR
     *
     * A SINTAXE PARA UM CAMPO SEPARADOR É [SEPARADOR:Nome do Separador]
     *
     */
    private static final String TAG_SEPARADOR = "[separador";

    /**
     *
     * Retorna a classe principal referente ao caminho para um campo
     *
     * A classe principal é a primeira parte de um caminho completo para um
     * campo, exemplo: Cliente.endereco.bairro, (o Cliente é o endereço
     * principal)
     *
     * @param pNome
     * @return
     */
    public static Class getClassePrincipalPorNome(String pNome) {

        String[] nomes = pNome.split("\\.");
        System.out.print(".");
        if (UtilSBCoreStrings.isPrimeiraLetraMaiuscula(pNome)) {
            return CLASSE_ENTIDADE_BY_NOME.get(nomes[0]);
        } else {
            throw new UnsupportedOperationException("O nome da classe principal não pode ser descoberto, se não for enviado na string, a string enviada foi" + pNome);
        }

    }

    public static Field getFieldByNomeCompletoCaminhoEClasse(String pCamihoCampo, Class pClasse) {
        CLASSE_ENTIDADE_BY_NOME.put(pClasse.getSimpleName(), pClasse);

        return getFieldByNomeCompletoCaminho(pCamihoCampo);
    }

    public static Field getFieldByNomeCompletoCaminho(String pCamihoCampo) {

        if (pCamihoCampo == null) {
            throw new UnsupportedOperationException("Tentativa de obter field Por String com parametro nulo");
        }

        if (isUmCampoSeparador(pCamihoCampo)) {
            return null;
        }

        String[] partes = pCamihoCampo.split("\\.");

        String nomeClassePrincipal = partes[0];

        Class classe = CLASSE_ENTIDADE_BY_NOME.get(nomeClassePrincipal);

        if (classe == null) {
            throw new UnsupportedOperationException("A classe não pode ser encontrada pelo nome:" + nomeClassePrincipal + " não foi encontrada o caminho completo enviado foi " + pCamihoCampo);
        }
        int i = 0;
        for (String parte : partes) {

            if (i > 0) {

                Field campo = null;
                List<Class> classesDaEntidade = UtilSBCoreReflexao.getClasseESubclassesAteClasseBaseDeEntidade(classe);
                for (Class classeAtual : classesDaEntidade) {
                    try {
                        campo = classeAtual.getDeclaredField(parte.replace("[]", ""));
                        if (campo != null) {
                            break;
                        }
                    } catch (Throwable t) {

                    }

                }
                if (campo == null) {
                    throw new UnsupportedOperationException("Impossível obter o campo " + parte + " na classe" + classe.getSimpleName());
                }
                if (i == partes.length - 1) {
                    return campo;
                } else {
                    classe = getClassePrincipalDoCampo(campo);

                }

            }
            i++;
        }
        throw new UnsupportedOperationException("Erro obtendo field a partir do caminho " + pCamihoCampo);

    }

    public static Field getFieldByCaminho(CaminhoCampoReflexao pCaminho) {
        return getFieldByNomeCompletoCaminho(pCaminho.getCaminhoCompletoString());
    }

    public static Class getClassePrincipalDoCampo(Field pCampo) {

        if (pCampo.getType().getSimpleName().equals("List")) {
            ParameterizedType genericoTipo = (ParameterizedType) pCampo.getGenericType();
            Class tipoDaLista = (Class<?>) genericoTipo.getActualTypeArguments()[0];
            return tipoDaLista;
        } else {
            return pCampo.getType();
        }

    }

    public static boolean isUmCampoSeparador(String pNomeSeparador) {
        return pNomeSeparador.contains(TAG_SEPARADOR);
    }

    /**
     *
     *
     * REtorna o campo Pelo tipo de campo na anotação (Verificar importancia de
     * retornar campo do hibernate relacionadas a campos do framework)
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
     * Retorna a quantidade de subcampos até atingir o campo pelo caminho
     *
     * ex: Cliente.localizacao.bairro, retornaria 2 (2 caminhos percorridos para
     * chegar ao valor)
     *
     *
     * @param pCaminhoCompleto
     * @return
     */
    public static int getQuantidadeSubCampos(String pCaminhoCompleto) {

        String[] partes = pCaminhoCompleto.split("\\.");

        boolean temNomeDaClasse = UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(partes[0]);

        if (temNomeDaClasse) {

            return partes.length - 1;
        } else {
            return partes.length;
        }

    }

    /**
     *
     * Retorna um campo do tipo Separador
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     *
     * @param pSeparador Uma string no padrão separador: [separador:nome
     * separador]
     * @return Um campo Instanciado, do tipo separador
     */
    public static CampoInstanciadoSeparador getCampoSeparador(String pSeparador) {
        if (!isUmCampoSeparador(pSeparador)) {
            throw new UnsupportedOperationException("A tag" + TAG_SEPARADOR + " não foi encontrada");
        }

        return new CampoInstanciadoSeparador(getNomeGrupoPorStrSeparador(pSeparador));

    }

    /**
     *
     *
     *
     * @param pCaminhoCompleto
     * @return
     */
    public static String getStrPrimeiroCampoDoCaminhoCampo(String pCaminhoCompleto) {

        String[] partes = pCaminhoCompleto.split("\\.");

        try {
            for (String parte : partes) {
                if (!UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(parte)) {
                    return parte;
                }
            }
            throw new UnsupportedOperationException("Não foi possível determinar a primeira parte do caminho para o campo " + pCaminhoCompleto);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
        }
        return null;
    }

    /**
     *
     *
     *
     * @param pCaminhoCompleto
     * @return
     */
    public static String getStrCaminhoCampoSemPrimeiroCampo(String pCaminhoCompleto) {
        String[] partes = pCaminhoCompleto.split("\\.");
        String caminhoParcial = "";
        int i = 0;
        try {
            for (String parte : partes) {
                if (!UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(parte)) {

                    if (i > 0) {
                        if (caminhoParcial.length() > 0) {
                            caminhoParcial += "." + parte;
                        } else {
                            caminhoParcial = parte;

                        }
                    }
                    i++;
                } else {

                }

            }

            if (caminhoParcial.length() < 1) {
                throw new UnsupportedOperationException("Não foi possível determinar a primeira parte do caminho para o campo " + pCaminhoCompleto);
            }

            return caminhoParcial;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "", t);
        }
        return null;
    }

    /**
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     * @param pClasse
     * @return
     */
    public static Map<String, CaminhoCampoReflexao> getTodosCamposItensSimplesDoItemEFilhosOrdemFilhoParaPai(Class pClasse) {
        try {
            return UtilSBCoreReflexaoCamposOldReferencia.makeCaminhosDeCamposDoTipoEntidadePossiveisDaClasse(pClasse);
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo Campos de entidade relacionados a classe " + pClasse, t);
            return null;
        }

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

    /**
     *
     *
     *
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

    /**
     *
     * Cria um mapeamento entre nome da classe de entidade, e a classe.
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     * @param entidades a lista de entidades que a lista deve ser criada
     */
    public static void configurarTodasAsClasses(List<Class> entidades) {

        try {
            if (!TODAS_CLASSES_CONFIGURADAS) {
                for (Class entidade : entidades) {
                    System.out.println("Configurando campos de:" + entidade.getSimpleName());
                    if (!entidade.getSimpleName().contains("Acao")) {

                        CLASSE_ENTIDADE_BY_NOME.put(entidade.getSimpleName(), entidade);
                    }

                }
            } else {
                throw new UnsupportedOperationException("As classes já foram configuras");
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve um erro configurando todas as classes", t);
            TODAS_CLASSES_CONFIGURADAS = false;
        }

        TODAS_CLASSES_CONFIGURADAS = true;
    }

    /**
     *
     * Retorna uma lista de grupo de campos, separando os grupos a cada campo do
     * tipo separador, caso não tenha nenhum separador, cria um úníco grupo
     *
     *
     * @deprecated Utilize GetCaminho por
     * @see UtilSBCoreReflexaoCampos#getCaminhoCAmpoByString(java.lang.String)
     *
     * @param pCampos Campos listados
     * @return Grupo Uma lista de grupos com os campos
     */
    @Deprecated
    public static List<GrupoCampos> buildAgrupamentoCampos(List<CaminhoCampoReflexao> pCampos) {
        List<GrupoCampos> grupocampos = new ArrayList<>();
        if (!pCampos.isEmpty()) {
            GrupoCampos grupoatual = null;

            if (pCampos.get(0).isUmCampoSeparador()) {
                grupoatual = new GrupoCampos(getCaminhoSemNomeClasse(pCampos.get(0).toString()));
            } else {
                grupoatual = new GrupoCampos();
            }
            int i = 0;
            for (CaminhoCampoReflexao campo : pCampos) {

                if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(campo.getCaminhoCompletoString())
                        & i != 0) {
                    grupocampos.add(grupoatual);
                    grupoatual = new GrupoCampos(getNomeGrupoPorStrSeparador(campo.getCaminhoCompletoString()));
                } else {
                    grupoatual.adicionarCampo(campo);
                }

                if (i == pCampos.size() - 1) {
                    grupocampos.add(grupoatual);
                }
                i++;

            }
        }
        return grupocampos;
    }

    public static CaminhoCampoReflexao getCaminhoByStringRelativaEClasse(String pCaminho, Class pClase) {

        try {
            if (isUmCampoSeparador(pCaminho)) {
                String caminhoCompleto = pClase.getSimpleName() + "." + pCaminho;
                return new CaminhoCampoReflexao(caminhoCompleto);
            }
            CLASSE_ENTIDADE_BY_NOME.put(pClase.getSimpleName(), pClase);

            //Confgurando caminho completo
            String caminhoCompleto = pCaminho;
            if (!UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(pCaminho)) {
                caminhoCompleto = pClase.getSimpleName() + "." + pCaminho;
            }

            return new CaminhoCampoReflexao(caminhoCompleto, pClase);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo caminho do campo a partir de caminho relativo:-->" + pCaminho + "<-- na classe:-->" + pClase.getSimpleName(), t);

        }
        return null;
    }

}
