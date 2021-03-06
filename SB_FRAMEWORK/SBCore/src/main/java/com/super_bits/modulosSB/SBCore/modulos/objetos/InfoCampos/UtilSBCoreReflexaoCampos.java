/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import static com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCamposOldReferencia.getNomeGrupoPorStrSeparador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoInstanciadoSeparador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Salvio
 */
public class UtilSBCoreReflexaoCampos {

    public static CampoNaoImplementado CAMPO_NAO_IMPLEMENTADO = new CampoNaoImplementado();
    private static final Pattern REGEX_REGISTRO_DA_LISTA = Pattern.compile("\\[(\\d+)\\]");
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
            return MapaObjetosProjetoAtual.getClasseDoObjetoByNome(nomes[0]);
        } else {
            throw new UnsupportedOperationException("O nome da classe principal não pode ser descoberto, se não for enviado na string, a string enviada foi" + pNome);
        }

    }

    public static Field getFieldByNomeCompletoCaminhoEClasse(String pCamihoCampo, Class pClasse) {
        MapaObjetosProjetoAtual.adcionarObjeto(pClasse);

        return getFieldByNomeCompletoCaminho(pCamihoCampo);
    }

    public static Class getClasseByNome(String pNome) {
        Class classe = MapaObjetosProjetoAtual.getClasseDoObjetoByNome(pNome);

        if (classe == null) {
            throw new UnsupportedOperationException("A classe não pode ser encontrada pelo nome:" + pNome + " não foi encontrada o caminho completo enviado foi ");
        }
        return classe;
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
        Class classe = null;
        try {
            classe = getClasseByNome(nomeClassePrincipal);
        } catch (Throwable t) {
            throw new UnsupportedOperationException("A classe não pode ser encontrada pelo caminho completo " + pCamihoCampo);
        }
        int i = 0;
        for (String parte : partes) {

            if (i > 0) {

                Field campo = null;
                List<Class> classesDaEntidade = UtilSBCoreReflexao.getClasseESubclassesAteClasseBaseDeEntidade(classe);
                for (Class classeAtual : classesDaEntidade) {
                    try {
                        campo = classeAtual.getDeclaredField(UtilSBCoreReflexaoCampos.getListaSemColchete(parte));
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

    /**
     *
     * Retorna a classe do campo, se for uma lista, retorna o tipo Generico
     * declarado
     *
     * @param pCampo Campo Obtido via reflexao
     * @return A classe vinculada a declaracao
     */
    public static Class getClassePrincipalDoCampo(Field pCampo) {

        if (pCampo.getType().getSimpleName().equals("List")) {
            ParameterizedType genericoTipo = (ParameterizedType) pCampo.getGenericType();
            Class tipoDaLista = (Class<?>) genericoTipo.getActualTypeArguments()[0];
            return tipoDaLista;
        } else {
            return pCampo.getType();
        }

    }

    /**
     * Retorna o generico da classe, ex: List<Tabela> retorna Tabela.class
     *
     * @param pCampoReflexao Field reflexão
     * @return
     */
    public static Class getClasseGenerica(Field pCampoReflexao) {
        return getClassePrincipalDoCampo(pCampoReflexao);
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
        try {
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
                    if (pTipoCampo.equals(FabCampos.ID)) {
                        if (field.getAnnotation(Id.class) != null) {
                            return field;
                        }
                    }

                }
                classe = classe.getSuperclass();
            }
            //   throw new UnsupportedOperationException("O campo " + pTipoCampo + " não foi encontrado na classe" + pClasse.getSimpleName());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro localizando campo do tipo" + pTipoCampo + "na classe" + pClasse, t);
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

    public static String getSEgundoCampoDoCaminho(String pCaminhoCompleto) {
        String[] partes = pCaminhoCompleto.split("\\.");

        try {
            int i = 0;
            for (String parte : partes) {
                if (!UtilSBCoreStrings.isPrimeiraApenasLetraMaiuscula(parte)) {
                    if (i >= 1) {
                        return parte;
                    }
                    i++;
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
     * Com uma entidade instanciada, ele percorre todos os campos filhos de
     * entidade, até o nivel maximo informado
     *
     * ex: pNivelMaximo 3 de uma entidade ficticia chamada avó, poderia retornar
     * seguintes caminhos: avó.mãe.filho. e avó.mãe.amante <br>
     * No pNivelMaximo 4 retornaria todos os acimas mais:
     * avó.mãe.amante.filho_do_amante;
     *
     * @param entidade A entidade instanciada (os campos nulos não serão
     * enviados)
     * @param pNivelAtual O nível atual de pesquisa, este método se auto
     * executa, incrementando o nivel a cada momento adequado
     * @param pNivelMaximo O nivel máximo, (não pode passar de 7)
     * @param listarAnterior A lista que será preenxida utilizando o formidacel
     * recurso de passagem por referencia do Java
     * @param caminhoAnterior
     * @return True caso não aconteçam erros
     */
    public static boolean buildListaSubEntidadesPersistiveis(ItfBeanSimples entidade, int pNivelAtual, int pNivelMaximo, List<CaminhoCampoReflexao> listarAnterior, String caminhoAnterior) {
        try {
            if (pNivelMaximo > 7) {
                pNivelMaximo = 7;
            }
            if (pNivelAtual >= pNivelMaximo) {
                return true;
            }
            if (caminhoAnterior == null) {
                caminhoAnterior = entidade.getClass().getSimpleName();
            }
            List<CaminhoCampoReflexao> entidadesVinculadas = entidade.getEntidadesVinculadas();
            for (CaminhoCampoReflexao caminho : entidadesVinculadas) {

                //confirmação que o item foi instanciado
                Object itemEncontrado = entidade.getValorCampoByCaminhoCampo(caminho);
                if (itemEncontrado != null) {
                    if (caminho.isUmCampoListavel()) {
                        List<ItfBeanSimples> lista = (List) itemEncontrado;
                        int ii = 0;
                        // construindo a string do caminho, como este

                        for (ItfBeanSimples item : lista) {
                            String caminhoNovoCampo = caminho.getCaminhoSemNomeClasse().replaceAll("\\[]", "[" + ii + "]");
                            CaminhoCampoReflexao novoCaminho = new CaminhoCampoReflexao(caminhoAnterior + "." + caminhoNovoCampo);
                            ii++;
                            listarAnterior.add(novoCaminho);

                            buildListaSubEntidadesPersistiveis((ItfBeanSimples) item, pNivelAtual + 1, pNivelMaximo, listarAnterior, caminhoAnterior + "." + novoCaminho.getCaminhoSemNomeClasse());
                        }
                    } else {

                        CaminhoCampoReflexao novoCaminho = new CaminhoCampoReflexao(caminhoAnterior + "." + caminho.getCaminhoSemNomeClasse());
                        listarAnterior.add(novoCaminho);
                        buildListaSubEntidadesPersistiveis((ItfBeanSimples) itemEncontrado, pNivelAtual + 1, pNivelMaximo, listarAnterior, caminhoAnterior + "." + caminho.getCaminhoSemNomeClasse());
                    }
                }

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo lista de entidades persistiveis", t);
            return false;
        }
        return true;

    }

    public static List<CaminhoCampoReflexao> getCamposDeEntidadeInstanciado(ItfBeanSimples pEntidade, int pQuantidadeSubniveis) {

        pEntidade.getEntidadesVinculadas();
        List<CaminhoCampoReflexao> lista = new ArrayList<>();
        buildListaSubEntidadesPersistiveis(pEntidade, 0, pQuantidadeSubniveis, lista, null);
        lista = Lists.reverse(lista);
        return lista;

    }

    public static TIPO_ORIGEM_VALOR_CAMPO getTipoCampoLista(String pNomeCampo) {
        if (pNomeCampo.contains("[]")) {
            return TIPO_ORIGEM_VALOR_CAMPO.VALORES_COM_LISTA;
        }

        Matcher m = REGEX_REGISTRO_DA_LISTA.matcher(pNomeCampo);
        if (m.find()) {
            return TIPO_ORIGEM_VALOR_CAMPO.REGISTRO_ESTATICO_DA_LISTA;
        }
        return TIPO_ORIGEM_VALOR_CAMPO.VALOR_COM_LISTA;

    }

    public static boolean isUmaStringNomeadaComoLista(String pLista) {

        String[] campos = pLista.split("\\.");
        String campo = campos[campos.length - 1];
        if (campo.contains("[]")) {
            return true;
        } else {
            final Matcher matcher = REGEX_REGISTRO_DA_LISTA.matcher(campo);
            return matcher.find();
        }
    }

    public static int getIdCampoDaLista(String pNomeDaLista) {
        String[] campos = pNomeDaLista.split("\\.");
        String campo = campos[campos.length - 1];
        final Matcher matcher = REGEX_REGISTRO_DA_LISTA.matcher(campo);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;

    }

    public static String getListaSemColchete(String parteNome) {
        return getListaSemIndice(parteNome).replaceAll("\\[]", "");
    }

    public static String getListaSemIndice(String parteNome) {
        final Matcher matcher = REGEX_REGISTRO_DA_LISTA.matcher(parteNome);
        String[] campos = parteNome.split("\\.");
        if (campos.length > 1) {
            throw new UnsupportedOperationException("Este método não suporta subCampos o valor enviado foi" + parteNome);
        }
        if (matcher.find()) {

            String conteudoComColchete = "\\[" + matcher.group(1) + "\\]";
            return parteNome.replaceAll(conteudoComColchete, "[]");
        } else {
            return parteNome;
            // throw new UnsupportedOperationException("O campo não parece ser uma lista com indice" + parteNome);
        }

    }

    /**
     *
     * Retorna todas as entidades diretamente declaradas na entidade,<br>
     * que não seja nula es estaja anotadas com @ManyToOne, @OneToMany, ou
     *
     * @ManyToMany
     *
     * @param pClasse
     * @return
     */
    public static Map<String, CaminhoCampoReflexao> getCamposComSubCamposDaClasse(Class pClasse) {

        if (pClasse == null) {
            throw new UnsupportedOperationException("Tentativa de obter campos Anotados com manyToOne sem enviar a classe de referencia nula");
        }
        // TODO???? este método não alcança listas e calculos (Lembrando que ele é utilizado no persistir filhos, então deve existir as 2 opções
        Map<String, CaminhoCampoReflexao> lista = new HashMap<>();
        Class classeAtual = pClasse;

        while (!isClasseBasicaSB(classeAtual)) {

            if (classeAtual == null) {
                throw new UnsupportedOperationException("Impossível percorrrer classes superiores de" + pClasse.getSimpleName());
            }

            Field[] todoscampos = classeAtual.getDeclaredFields();

            List<Field> camposEntidade = Lists.newArrayList(todoscampos);

            //  Se chegou até aqui, você deve estar pensando, porque instanciar duas vezes esta lista? <br>
            //   O que é mais importante, um código legivel e debugavel ou um código rápido? o trabalho em equipe? ou o processaodor?
            // Separando lista de campos de entidade do restante
            for (Field campoEntidade : todoscampos) {
                if (campoEntidade.isAnnotationPresent(ManyToOne.class
                ) || campoEntidade.isAnnotationPresent(OneToMany.class)
                        || campoEntidade.isAnnotationPresent(ManyToMany.class)) {
                    if (campoEntidade.isAnnotationPresent(Transient.class)) {
                        // caso seja transiente, ignora
                        camposEntidade.remove(campoEntidade);
                    }

                } else {
                    //caso não contenha as anotações de chave extrangeira remove da lista
                    camposEntidade.remove(campoEntidade);
                }
            }
            // para cada campo do tipo entidade persistivel retorna o valor
            for (Field campoEntidade : camposEntidade) {
                String caminhoCampo = null;
                try {
                    caminhoCampo = pClasse.getSimpleName() + "." + campoEntidade.getName();
                    CaminhoCampoReflexao caminhoCR = new CaminhoCampoReflexao(caminhoCampo);
                    lista.put(caminhoCR.getCaminhoCompletoString(), caminhoCR);
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando Campo: " + caminhoCampo, t);
                    throw new UnsupportedOperationException("Impossivel determinar os campos com chave extrangeira da classe");
                }
            }
            classeAtual = classeAtual.getSuperclass();
        }

        return lista;
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

        MapaObjetosProjetoAtual.configuraraTodasAsClasses(entidades);
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
    public static List<GrupoCampos> buildAgrupamentoCampos(List<CaminhoCampoReflexao> pCampos) {
        List<GrupoCampos> grupocampos = new ArrayList<>();
        if (pCampos != null) {
            if (!pCampos.isEmpty()) {
                GrupoCampos grupoatual = null;

                if (pCampos.get(0).isUmCampoSeparador()) {
                    grupoatual = new GrupoCampos(getNomeGrupoPorStrSeparador(pCampos.get(0).getCaminhoCompletoString()));
                } else {
                    grupoatual = new GrupoCampos();
                }
                int i = 0;
                for (CaminhoCampoReflexao campo : pCampos) {

                    if (UtilSBCoreReflexaoCampos.isUmCampoSeparador(campo.getCaminhoCompletoString())
                            & i != 0) {

                        grupocampos.add(grupoatual);
                        grupoatual = new GrupoCampos(getNomeGrupoPorStrSeparador(campo.getCaminhoCompletoString()));
                    } else if (!isUmCampoSeparador(campo.getCaminhoCompletoString())) {
                        grupoatual.adicionarCampo(campo);
                    }

                    if (i == pCampos.size() - 1) {
                        grupocampos.add(grupoatual);
                    }
                    i++;

                }
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
            MapaObjetosProjetoAtual.adcionarObjeto(pClase);

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
