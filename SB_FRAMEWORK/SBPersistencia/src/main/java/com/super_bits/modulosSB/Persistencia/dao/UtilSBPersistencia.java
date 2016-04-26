/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.Persistencia.dao;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanContatoCorporativo;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanContatoPessoa;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.EntityType;
import javax.validation.constraints.NotNull;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * Conjunto de metodos estatiscos para auxiliar no acesso ao banco de dados
 *
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 19/07/2013
 *
 */
public class UtilSBPersistencia implements Serializable, ItfDados {

    private static EntityManagerFactory emFacturePadrao;
    private static final int MAXIMO_REGISTROS = 2000;

    /**
     * Tipo de aviso de resultado, pode ser: SOLICITAR_REPARO USUARIO
     * ARQUIVAR_LOG -> O SOLICITAR_REPARO SÓ VAI EMITIR AVISO QUANDO ESTIVER NO
     * MODO DESENVOLVIMENTO -> O USUARIO SEMPRE MOSTRARÁ O RESULTADO AO USUARIO
     * -> O ARQUIVAR_LOG GRAVARA UM LOG QUANDO EM HOMOLOGAÇÃO OU PRODUÇÃO, E
     * EXIBIRA MENSAGEM QUANDO EM DESENVOLVIMENTO
     */
    public static enum AVISAR {

        PROGRAMADOR, USUARIO, SISTEMA
    };

    //* TIPOS DE SELEÇÃO DE LISTAS MAIS COMUNS */
    public static enum TIPO_SELECAO_REGISTROS {

        JPQL, SQL, LIKENOMECURTO, TODOS, NAMED_QUERY, SBNQ;
    }

    //* TIPOS DE SELEÇÃO DE ITEM MAIS COMUNS */
    public static enum TipoSelecaoRegistro {

        ID, NOMECURTO, LIKENOMECURTO, SQL, JPQL, ULTIMO_REGISTRO, PRIMEIRO_REGISTRO, ENCONTRAR_EMPRESA, ENCONTRAR_PESSOA, QUANTIDADE_REGISTROS
    }

    private static Map<String, EntityManagerFactory> bancoExtra = new HashMap<String, EntityManagerFactory>();

    /**
     *
     * Cria um novo entity Manager, O entity Manager gera uma nova conexão com o
     * banco que só encerrada após o comando close
     *
     * @param pNomeBanco Caso em branco ou nulo retorna EM padrão.
     * @return Nulo se não for possível criar, e a entidade caso consiga
     */
    public synchronized static EntityManager getNovoEM(String pNomeBanco) {
        EntityManager novoEM = null;

        if (pNomeBanco == null || pNomeBanco.length() == 0) {
            try {
                return getNovoEM();
            } catch (Exception e) {
                return null;
            }
        }

        try {
            EntityManagerFactory fabrica = bancoExtra.get(pNomeBanco);

            if (fabrica == null) {
                System.out.println("Criando EMF" + pNomeBanco);
                fabrica = Persistence.createEntityManagerFactory(pNomeBanco);
                fabrica.getCache().evictAll();
                if (fabrica == null) {
                    SBCore.RelatarErro(FabErro.PARA_TUDO, " Erro criando EntityFactury" + pNomeBanco, null);
                }
                bancoExtra.put(pNomeBanco, fabrica);
            }
            if (fabrica != null) {
                fabrica.getCache().evictAll();

                return fabrica.createEntityManager();
            }
        } catch (Throwable e) {
            SBCore.RelatarErro(FabErro.ARQUIVAR_LOG, " Erro tentando Localizar o EMF:" + pNomeBanco, e);
            return null;
        }
        return null;
    }

    private static void renovarConexao() {
        emFacturePadrao = null;
        getNovoEM();
    }

    /**
     *
     * Cria um novo entity Manager, do Banco padrão entity Manager gera uma nova
     * conexão com o banco que só encerrada após o comando close
     *
     * @return Nulo se não for possível criar, e a entidade caso consiga
     */
    public static EntityManager getNovoEM() {

        try {
            if (emFacturePadrao == null) {

                emFacturePadrao = Persistence.createEntityManagerFactory(SBPersistencia.getNomeBancoPadrao());
            }
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando criar entitymanagerFacturePAdrão=" + SBPersistencia.getNomeBancoPadrao(), e);
            try {
                emFacturePadrao = Persistence.createEntityManagerFactory(SBPersistencia.getNomeBancoPadrao());
            } catch (Throwable ee) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando criar entitymanagerFActurePadrão, segunda tentativa=" + SBPersistencia.getNomeBancoPadrao(), ee);
            }
        }

        //for (String propriedade : emFacturePadrao.getProperties().keySet()) {
        //   System.out.println("Propriedade" + propriedade + "Tipo Objeto:" + emFacturePadrao.getProperties().get(propriedade).getClass().getSimpleName()
        //          + "=" + emFacturePadrao.getProperties().get(propriedade).toString());
        // }
        try {

            try {
                if (emFacturePadrao == null) {
                    throw new Exception("Erro tentando criar EntityManager, a Fabrica de EM não pode ser construída");
                }

                EntityManager emcriada = emFacturePadrao.createEntityManager();

                if (emcriada == null || !emFacturePadrao.isOpen() || !emcriada.isOpen()) {
                    throw new Exception("Erro tentando criar EntityManager");
                }
                return emcriada;
            } catch (Throwable e) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando criar entitymanager" + SBPersistencia.getNomeBancoPadrao(), e);
                System.out.println("Recirando Factury na tentativa de gerar um novo EM");
                emFacturePadrao = Persistence.createEntityManagerFactory(SBPersistencia.getNomeBancoPadrao());
                return emFacturePadrao.createEntityManager();
            }

        } finally {
            emFacturePadrao.getCache().evictAll();
        }
    }

    public static EntityManager getNovoEMIniciandoTransacao() {
        EntityManager em = getNovoEM();
        em.getTransaction().begin();
        return em;
    }

    public static boolean finzalizaTransacaoEFechaEM(@NotNull EntityManager em) {
        try {
            if (em == null) {
                throw new UnsupportedOperationException("O entity manager está nulo");

            }
            if (!em.isOpen()) {
                throw new UnsupportedOperationException("O entity manager está fechado");
            }
            em.getTransaction().commit();

            em.close();
            return true;
        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Ocorreu um erro ao finalizar a tranzação", t);
            return false;
        }

    }

    public static boolean iniciarTransacao(@NotNull EntityManager pEM) {

        try {
            if (pEM == null) {
                throw new UnsupportedOperationException("O entity manager está nulo");

            }
            if (!pEM.isOpen()) {
                throw new UnsupportedOperationException("O entity manager está fechado");
            }
            pEM.getTransaction().begin();

            return true;
        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Ocorreu um erro ao finalizar a tranzação", t);
            return false;
        }

    }

    public static boolean finalizarTransacao(@NotNull EntityManager em) {

        try {
            if (em == null) {
                throw new UnsupportedOperationException("O entity manager está nulo");

            }
            if (!em.isOpen()) {
                throw new UnsupportedOperationException("O entity manager está fechado");
            }
            em.getTransaction().commit();

            return true;
        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Ocorreu um erro ao finalizar a tranzação", t);
            return false;
        }

    }

    public UtilSBPersistencia() {

        if (emFacturePadrao == null) {
            emFacturePadrao = Persistence.createEntityManagerFactory(SBPersistencia.getNomeBancoPadrao());
            emFacturePadrao.getCache().evictAll();
        }

    }

    public static EntityManagerFactory getEmfabricaPadrao() {
        if (emFacturePadrao == null) {
            emFacturePadrao = Persistence.createEntityManagerFactory(SBPersistencia.getNomeBancoPadrao());
            emFacturePadrao.getCache().evictAll();
        }

        return emFacturePadrao;
    }

    /**
     * Define qual EM será utilizada de acordo com os parametros enviados
     */
    static private EntityManager defineEM(EntityManager pEmEnviada, String pNomeEntityManager) {

        try {
            if (pEmEnviada != null) {
                return pEmEnviada;
            } else {
                return getNovoEM(pNomeEntityManager);
            }

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro selecionando o tipo de entidade a ser retornada", e);
            return null;
        }
    }

    /**
     * Função que executa Alterações em Banco de Dados
     */
    private static Object executaAlteracaoEmBancao(InfoPerisistirEntidade pInfoEntidadesPersistencia) {

        Object pEntidade = pInfoEntidadesPersistencia.getpEntidade();
        List<Object> pEntidades = pInfoEntidadesPersistencia.getpEntidades();
        EntityManager pEM = pInfoEntidadesPersistencia.getpEM();
        FabInfoPersistirEntidade pTipoAlteracao = pInfoEntidadesPersistencia.getTipoAlteracao();

        try {
            if (!pInfoEntidadesPersistencia.isTemRegistroParaPersistir()) {
                throw new UnsupportedOperationException("Favor informar ao menos uma entidade para persistir, é possível que você esteja tentando salvar um registro = a nulo");
            }

            System.out.println("executando alteração do tipo" + pTipoAlteracao);
            if (SBCore.isControleDeAcessoDefinido()) {
                if (!SBCore.getConfiguradorDePermissao().ACAOCRUD(pEntidade.getClass(), pTipoAlteracao.toString()).isSucesso()) {
                    FabMensagens.enviarMensagemUsuario("Ação não permita para este usuário, solicite permição ao Administrador", FabMensagens.AVISO);
                    if (pTipoAlteracao == FabInfoPersistirEntidade.INSERT) {
                        return false;
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor(" Erro Verificando permissoes de CRUD " + e.getMessage(), e);

            if (e.getCause() != null) {
                if (e.getCause().getClass().getSimpleName().equals(JDBCConnectionException.class.getSimpleName())) {
                    renovarConexao();
                }
            }
            return null;

        }

        boolean entityManagerPaiEnviada = false;
        if (pEM != null) {
            entityManagerPaiEnviada = true;
        }

        try {

            EntityManager em = defineEM(pEM, null);
            if (em == null) {
                throw new Exception("Impossível definir o entityManager ");
            }

            List<Object> objetosPersistidos = new ArrayList<>();
            if (pEntidade != null) {
                objetosPersistidos.add(pEntidade);
            }
            if (pEntidades != null) {
                objetosPersistidos = pEntidades;
            }

            try {

                try {
                    boolean controleTranzacaoExterno = false;
                    if (!em.getTransaction().isActive()) {
                        em.getTransaction().begin();
                        controleTranzacaoExterno = true;
                    }

                    boolean sucesso = false;

                    Object novoRegistro = objetosPersistidos.get(0);
                    for (Object entidade : objetosPersistidos) {
                        sucesso = false;
                        novoRegistro = entidade;
                        switch (pTipoAlteracao) {

                            case DELETE:

                                em.remove(em.getReference(entidade.getClass(), ((ItfBeanSimples) entidade).getId()));

                                sucesso = true;
                                break;
                            case INSERT:

                                em.persist(entidade);
                                sucesso = true;
                                break;
                            case MERGE:

                                novoRegistro = em.merge(entidade);
                                entidade = novoRegistro;
                                sucesso = true;
                                break;
                            default:
                                break;

                        }
                    }

                    if (controleTranzacaoExterno) {
                        em.getTransaction().commit();

                    }
                    if (pTipoAlteracao == FabInfoPersistirEntidade.MERGE) {
                        if (sucesso) {
                            /// FabMensagens.enviarMensagemUsuario("Registro Alterado com Sucesso", FabMensagens.AVISO);
                            return novoRegistro;
                        } else {
                            FabMensagens.enviarMensagemUsuario("Ocorreu um erro Ao Atualizar o registro", FabMensagens.ERRO);
                            return null;
                        }
                    } else {
                        if (sucesso) {
                            //  FabMensagens.enviarMensagemUsuario("Registro Cadastrado com sucesso", FabMensagens.AVISO);
                        } else {
                            FabMensagens.enviarMensagemUsuario("Ocorreu um erro Ao Inserir o registro", FabMensagens.ERRO);
                        }
                        return sucesso;
                    }
                } catch (Exception e) {

                    FabMensagens.enviarMensagemUsuario(new Date() + "Erro executando alteração no banco do tipo" + pTipoAlteracao + pEntidade.getClass().getSimpleName() + e.getMessage(), FabMensagens.ERRO);
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro executando alteração em Banco" + pInfoEntidadesPersistencia.toString(), e);

                    if (!em.getTransaction().isActive()) {
                        try {
                            em.getTransaction().rollback();
                        } catch (Exception e2) {
                            System.out.println("Erro executando roolback devido a falha na inserção");
                        }
                    }

                    if (pTipoAlteracao == FabInfoPersistirEntidade.MERGE) {
                        return null;
                    } else {
                        return false;
                    }
                }

            } finally {
                if (entityManagerPaiEnviada == false) {
                    if (em != null) {
                        em.close();
                    }
                }
            }

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando obter Entity Manager (EM)", e);
            if (pTipoAlteracao == FabInfoPersistirEntidade.MERGE) {
                return null;
            } else {
                return false;
            }
        }
    }

    /**
     * Codigo padrão para realizar Select de Registros
     *
     *
     * @param pNomeEM Nome do entity maneger extra
     * @param pEM Entity manager a ser utilizado
     * @param pSQL sql que será executado
     * @param pPQL PQL (Persistence query Language)
     * @param maximo Maximo de registros retornados (equivale a limit x) no fim
     * do select
     * @param tipoRegisto Tipo de registro selecionado
     * @param pTipoSelecao
     * @param parametros
     * @return Uma seleção de regostros encontrados, ou uma lista sem registro
     * caso Não encontre nada ou aconteça um erro
     */
    private static List<?> selecaoRegistros(EntityManager pEM, String pSQL, String pPQL, Integer maximo, Class tipoRegisto, TIPO_SELECAO_REGISTROS pTipoSelecao, Object... parametros) {
        // todo Se origem for uma MBPAGINA  utilizar o EntityManager da pagina
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String nomeMetodo = stackTraceElements[3].getMethodName();
        String nomeClasse = stackTraceElements[3].getClassName();
        String[] classeFull = nomeClasse.split("\\.");

        if (maximo == null) {
            maximo = -1;
        }
        try {
            Boolean entityManagerPaiEnviado = false;
            EntityManager em = null;
            if (pEM == null) {
                em = getNovoEM();
            } else {
                em = pEM;
                entityManagerPaiEnviado = true;
            }

            if (maximo > MAXIMO_REGISTROS) {
                maximo = MAXIMO_REGISTROS;
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Voce selecionou mais registros que o configurado como permitido pelo sistema  ", null);
            }
            // Configura o numero de parametros nativos = 0, campo importante para
            // ignorar a atribuição de parametros dinamicos nos casos de  querys pre definindas como like nome curto
            int numeroParamentrosNativos = 0;
            try {
                try {
                    String sql = "";
                    switch (pTipoSelecao) {
                        case LIKENOMECURTO:

                            ItfBeanSimples registro = (ItfBeanSimples) tipoRegisto.newInstance();
                            String campoNomeCurto = registro.getNomeCampo(FabCampos.AAA_NOME);
                            String parametro = (String) parametros[0];
                            sql = "from " + tipoRegisto.getSimpleName() + " where "
                                    + campoNomeCurto + " like '%" + parametro + "%'";
                            numeroParamentrosNativos = 1;
                            break;
                        case JPQL:
                            sql = pPQL;
                            break;
                        case SQL:
                            sql = pSQL;
                            break;
                        case TODOS:
                            sql = "from " + tipoRegisto.getSimpleName();
                            break;
                        default:
                            break;
                    }
                    Query consulta = null;

                    if (pTipoSelecao == TIPO_SELECAO_REGISTROS.SQL) {
                        consulta = em.createNativeQuery(sql);
                    } else {
                        consulta = em.createQuery(sql);
                    }

                    if (maximo != -1 && maximo != 0) {
                        System.out.println("SetMaximo=" + maximo);
                        consulta.setMaxResults(maximo);
                    }

                    if (parametros != null && parametros.length > numeroParamentrosNativos) {
                        int i = 1;
                        for (Object pr : parametros) {
                            consulta.setParameter(i, pr);
                            i++;
                        }
                    }
                    List resultado = consulta.getResultList();
                    if (resultado.size() > MAXIMO_REGISTROS) {
                        System.out.println("este select retorna mais de" + MAXIMO_REGISTROS + "o sistema não deixará de executar, mas não posso deixar de perguntar Isto está certo ??");
                        System.out.println("sql");
                    }
                    return resultado;
                } catch (Throwable e) {
                    if (e.getCause() != null) {
                        if (e.getCause().getClass().getSimpleName().equals(JDBCConnectionException.class.getSimpleName())) {
                            renovarConexao();
                        }
                    }

                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando ou executando uma query", e);
                    return new ArrayList();
                }

            } finally {
                if (!entityManagerPaiEnviado) {
                    System.out.println("ENtity não Enviado=" + entityManagerPaiEnviado + " Fechando conexao");
                    if (em != null && !entityManagerPaiEnviado) {
                        if (em.isOpen()) {
                            em.close();
                        }
                    }
                }
            }

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor(pPQL, e);
            return new ArrayList();
        }

    }

    /**
     *
     * @param pNomeEM Nome do Entity Manager especial (Não é obrigatório)
     * @param pEM Entity Manager que será utilizado (Não é obrigatório)
     * @param pSQL Sql a ser executado
     * @param pPQL Persistence Query Linguage
     * @param maximo Maximo
     * @param tipoRegisto Tipo de registro
     * @param pTipoSelecao Tipo de seleção
     * @param parametros Parametros
     * @return O registro encontrado, ou um null caso não encontre ou aconteça
     * algum erro em sql
     */
    private static Object selecaoRegistro(EntityManager pEM, String pSQL, String pPQL, Class pClasseRegisto, TipoSelecaoRegistro pTipoSelecao, Object... parametros) {
        boolean entityManagerPaiEnviado = false;
        try {
            EntityManager em;
            if (pEM != null) {
                em = pEM;
                entityManagerPaiEnviado = true;
            } else {
                em = getNovoEM();
            }
            try {
                try {
                    Query consulta;
                    String sql = "";
                    switch (pTipoSelecao) {
                        case ID:
                            Object resposta = em.find(pClasseRegisto, parametros[0]);
                            return resposta;

                        case NOMECURTO:
                            ItfBeanSimples registroNC = (ItfBeanSimples) pClasseRegisto.newInstance();
                            String campoNomeCurtoNC = registroNC.getNomeCampo(FabCampos.AAA_NOME);
                            String parametroNC = (String) parametros[0];
                            sql = "from " + pClasseRegisto.getSimpleName() + " where "
                                    + campoNomeCurtoNC + " = '" + parametroNC + "'";

                            break;
                        case LIKENOMECURTO:
                            ItfBeanSimples registro = (ItfBeanSimples) pClasseRegisto.newInstance();
                            String campoNomeCurto = registro.getNomeCampo(FabCampos.AAA_NOME);
                            String parametro = (String) parametros[0];
                            sql = "from " + pClasseRegisto.getSimpleName() + " where "
                                    + campoNomeCurto + " like '" + parametro + "'";

                        case PRIMEIRO_REGISTRO:
                            sql = "from " + pClasseRegisto.getSimpleName();
                            break;
                        case SQL:
                            sql = pSQL;
                            break;
                        case ULTIMO_REGISTRO:
                            throw new UnsupportedOperationException("A busca por Ultimo Registro ainda não foi implementada");

                        case JPQL:
                            sql = pPQL;
                            break;
                        case ENCONTRAR_EMPRESA:

                            ItfBeanContatoCorporativo registroCorporativo = (ItfBeanContatoCorporativo) pClasseRegisto.newInstance();
                            String telefone = registroCorporativo.getNomeCampo(FabCampos.TELEFONE_FIXO_NACIONAL);

                            String pr = (String) parametros[0];
                            sql = "from " + pClasseRegisto.getSimpleName() + " where "
                                    + telefone + " like '" + pr + "'";

                            break;
                        case ENCONTRAR_PESSOA:
                            ItfBeanContatoPessoa buscaPessoaFisica;
                            throw new UnsupportedOperationException("A busca de pessoa ainda não foi implementada");

                        case QUANTIDADE_REGISTROS:

                            CriteriaBuilder qb = em.getCriteriaBuilder();
                            CriteriaQuery<Long> cq = qb.createQuery(Long.class);
                            cq.select(qb.count(cq.from(pClasseRegisto)));
                            return em.createQuery(cq).getSingleResult();

                        default:
                            throw new AssertionError(pTipoSelecao.name());
                    }
                    if (pTipoSelecao == TipoSelecaoRegistro.SQL) {
                        if (pClasseRegisto == null) {
                            System.out.println("Criando Query por string:" + sql);
                            consulta = em.createNativeQuery(sql);
                        } else {
                            System.out.println("Criando JPQL por string:" + sql);
                            consulta = em.createNativeQuery(sql, pClasseRegisto);
                        }

                    } else if (pClasseRegisto == null) {
                        consulta = em.createQuery(sql);
                    } else {
                        consulta = em.createQuery(sql, pClasseRegisto);
                    }
                    consulta.setMaxResults(1);

                    List<?> resposta = consulta.getResultList();
                    if (resposta.isEmpty()) {
                        return null;
                    }
                    return resposta.get(0);

                } catch (Throwable e) {
                    if (e.getCause() != null) {
                        if (e.getCause().getClass().getSimpleName().equals(JDBCConnectionException.class.getSimpleName())) {
                            renovarConexao();
                        }
                    }
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando ou executando uma query de registro unico", e);
                    return null;
                }

            } finally {
                if (!entityManagerPaiEnviado) {
                    if (em != null) {
                        em.close();
                    }
                }
            }

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro definindo EM para select de registro unico", e);
            return null;
        }
    }

    /**
     * Realizar Merg de Objeto (cria se o key primario não existir, e atualiza
     * caso exista).
     *
     * @param obj Objeto que será salvo em banco
     * @param pEm Entity manager que será utilizado
     * @return Objeto atualizado apos ser persistido em banco,e nulo caso ocorra
     * falha
     */
    public static Object mergeRegistro(Object obj, EntityManager pEm) {

        return executaAlteracaoEmBancao(new InfoPerisistirEntidade(obj, null, pEm, FabInfoPersistirEntidade.MERGE));

    }

    /**
     * Realizar Merg de Objeto (cria se o primario não existir, e atualiza caso
     * exista).
     *
     * @param object
     * @return Objeto atualizado apos ser persistido em banco,e nulo caso ocorra
     * falha
     */
    public static Object mergeRegistro(Object object) {
        if (object == null) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("O registro enviado para Persistencia é nulo ", null);
            return null;
        }
        return executaAlteracaoEmBancao(new InfoPerisistirEntidade(object, null, null, FabInfoPersistirEntidade.MERGE));

    }

    /**
     *
     * Cria uma conexão. inicia uma transação Executa o merge em cada objeto
     * enviado. só efetua o commit caso todos tenham sido atualizados, a nao ser
     * que o reverterem caso de falha senja falso Neste caso, cria-se uma
     * conexão para cada update
     *
     * @param pObjetos
     * @param pReverterEmCasoDeFalha
     * @return Um array de obejtos apenas com os objetos que foram salvos, um
     * array em branco caso nenhum tenha sido salvo
     */
    public static Object[] MergeListaRegistros(Object[] pObjetos, boolean pReverterEmCasoDeFalha) {
        //TODO Edu
        throw new UnsupportedOperationException("Não implementado ainda");

    }

    public static Object[] MergeListaRegistros(Object[] pObjetos) {
        return MergeListaRegistros(pObjetos, false);

    }

    /**
     * Realizar INSERT persistindo o Objeto no Banco
     *
     * @param pObj Objeto a ser persistido em banco
     * @param pEM Entidade utilizada
     * @return retorna True se persistido e False se algum erro acontecer
     */
    public static boolean persistirRegistro(Object pObj, EntityManager pEM) {
        return (boolean) executaAlteracaoEmBancao(new InfoPerisistirEntidade(pObj, null, pEM, FabInfoPersistirEntidade.INSERT));
    }

    /**
     *
     * @param pObj
     * @param pEM
     * @return True caso consiga salvar a alteração, ou false caso não consiga
     * executar o insert
     */
    public static boolean persistirRegistro(Object pObj) {
        return (boolean) executaAlteracaoEmBancao(new InfoPerisistirEntidade(pObj, null, null, FabInfoPersistirEntidade.INSERT));
    }

    /**
     *
     * @param pObj Registro que será excluído
     * @return true se conseiguir exculir, false se não conseguir
     */
    public static boolean exluirRegistro(Object pObj) {
        return (boolean) executaAlteracaoEmBancao(new InfoPerisistirEntidade(pObj, null, null, FabInfoPersistirEntidade.DELETE));
    }

    /**
     *
     * @param pObj Registro que será excluído
     * @return true se conseiguir exculir, false se não conseguir
     */
    public static boolean exluirRegistro(Object pObj, EntityManager em) {
        return (boolean) executaAlteracaoEmBancao(new InfoPerisistirEntidade(pObj, null, em, FabInfoPersistirEntidade.DELETE));
    }

    /**
     * @param pNomeCurto String Que deseja localizar no Banco
     * @param classe Classe utilizada
     * @return Lista com registros like nomecurto
     */
    public static List getListaRegistrosLikeNomeCurto(String pNomeCurto, Class classe) {
        return selecaoRegistros(null, null, null, null, classe, TIPO_SELECAO_REGISTROS.LIKENOMECURTO, pNomeCurto);
    }

    /**
     *
     *
     * @see #getListaRegistrosLikeNomeCurto(java.lang.String, java.lang.Class)
     *
     *
     * @param pNomeCurto
     * @param pClasse
     * @param pEM
     * @return
     */
    public static List getListaRegistrosLikeNomeCurto(String pNomeCurto, Class pClasse, EntityManager pEM) {
        return selecaoRegistros(pEM, null, null, null, pClasse, TIPO_SELECAO_REGISTROS.LIKENOMECURTO, pNomeCurto);
    }

    public static List getListaTodos(Class pClasse) {

        if (pClasse == null) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("A classe não foi especificada em getLista Todos", null);
            return null;
        }

        return selecaoRegistros(null, null, null, null, pClasse, TIPO_SELECAO_REGISTROS.TODOS);
    }

    public static List getListaTodos(Class pClasse, EntityManager pEm) {

        if (pClasse == null) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("A classe não foi especificada em getListaTodos", null);
            return null;
        }

        return selecaoRegistros(pEm, null, null, null, pClasse, TIPO_SELECAO_REGISTROS.TODOS);
    }

    /**
     * @param pHQl HQO (Comando no formato Hibernate query language)
     * @param pMaximo
     * @param parametros parametros
     * @return lista de registros
     */
    public static List getListaRegistrosByHQL(String pHQl, int pMaximo, Object... parametros) {
        return selecaoRegistros(null, null, pHQl, pMaximo, null, UtilSBPersistencia.TIPO_SELECAO_REGISTROS.JPQL, parametros);
    }

    /**
     *
     *
     * @param pHQl HQO (Comando no formato Hibernate query language)
     * @param pMaximo
     * @param pEM Entity manager utilizado
     * @param parametros parametros
     * @return lista de registros
     */
    public static List getListaRegistrosByHQL(String pHQl, int pMaximo, EntityManager pEM, Object... parametros) {
        return selecaoRegistros(pEM, null, pHQl, pMaximo, null, UtilSBPersistencia.TIPO_SELECAO_REGISTROS.JPQL, parametros);
    }

    /**
     * @param pHQl HQO (Comando no formato Hibernate query language)
     * @param pMaximo
     * @param parametros parametros
     * @return lista de registros
     */
    public static List getListaRegistrosBySQL(String pSQl, int pMaximo, Object... parametros) {
        return selecaoRegistros(null, pSQl, null, pMaximo, null, UtilSBPersistencia.TIPO_SELECAO_REGISTROS.SQL, parametros);
    }

    public static List getListaRegistrosBySQL(EntityManager pEm, String pSQl, int pMaximo, Object... parametros) {
        return selecaoRegistros(pEm, pSQl, null, pMaximo, null, UtilSBPersistencia.TIPO_SELECAO_REGISTROS.SQL, parametros);
    }

    /**
     *
     * @param pClasse Classe referente
     * @param parametro nome curto que será localizado
     * @return registro encontrado
     */
    public static Object getRegistroByNomeCurto(Class pClasse, String parametro) {
        return selecaoRegistro(null, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.NOMECURTO, parametro);
    }

    public static Object getRegistroByLikeNomeCurto(Class pClasse, String parametro) {
        return selecaoRegistro(null, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.LIKENOMECURTO, parametro);
    }

    public static Object getRegistroByLikeNomeCurto(Class pClasse, String parametro, EntityManager pEm) {
        return selecaoRegistro(pEm, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.LIKENOMECURTO, parametro);
    }

    /**
     *
     * @param pClasse Classe referente
     * @param parametro nome curto localizado
     * @param pNomeEM Entity Manager especial (diferente da entidade padrão)
     * @return registro encontrado
     */
    public static Object getRegistroByNomeCurto(Class pClasse, String parametro, EntityManager pEM) {
        return selecaoRegistro(pEM, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.NOMECURTO, parametro);
    }

    /**
     *
     * @param pSQL String SQL que será executado
     * @param pNomeEM Registro pelo sql
     * @return
     */
    public static Object getRegistroBySQL(String pSQL, EntityManager pEM) {
        return selecaoRegistro(pEM, pSQL, null, null, TipoSelecaoRegistro.SQL);
    }

    /**
     *
     * @param pSQL String SQL que será executado
     * @return
     */
    public static Object getRegistroBySQL(String pSQL) {
        return selecaoRegistro(null, pSQL, null, null, TipoSelecaoRegistro.SQL);
    }

    /**
     *
     * @param pSQL Stirng com JPQLsql
     * @param pNomeEM Nome do entity Manager
     * @return
     */
    public static Object getRegistroByJPQL(String pSQL, EntityManager pEM) {
        return selecaoRegistro(pEM, null, pSQL, null, TipoSelecaoRegistro.JPQL);
    }

    /**
     *
     * @param pSQL Sting com sql
     * @param limite limite de registros
     * @return
     */
    public static Object getRegistroByJPQL(String pSQL) {
        return selecaoRegistro(null, null, pSQL, null, TipoSelecaoRegistro.JPQL);
    }

    public static Object getRegistroByJPQL(String pSQL, Class pClasse) {
        return selecaoRegistro(null, null, pSQL, pClasse, TipoSelecaoRegistro.JPQL);
    }

    public static Object getRegistroByJPQL(String pSQL, Class pClasse, EntityManager pEM) {
        return selecaoRegistro(pEM, null, pSQL, pClasse, TipoSelecaoRegistro.JPQL);
    }

    /**
     *
     * @param pClasse Classe do registro
     * @param id id do registro
     * @param pNomeEM nome da entidade
     * @return registro encontrado
     */
    public static Object getRegistroByID(Class pClasse, int id, EntityManager pEM) {
        return selecaoRegistro(pEM, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.ID, id);

    }

    /**
     *
     * Carrega uma entidade apartir de um Bean Simples
     *
     * -> Um objeto que ainda não foi carregado pelo hibernate possui as funções
     * referentes a lista limitadas, para casos onde o Objeto é construido fora
     * do banco de dados
     *
     * @param pBeanSimples O bean que será carregado
     * @param pEM Entity manager utlizado
     * @return
     */
    public static Object loadEntidade(ItfBeanSimples pBeanSimples, EntityManager pEM) {
        try {
            if (pBeanSimples == null) {
                throw new UnsupportedOperationException("Tentativa de carregar o Registro JPA enviando o valor nulo");
            }
            return selecaoRegistro(pEM, null, null, pBeanSimples.getClass(), UtilSBPersistencia.TipoSelecaoRegistro.ID, pBeanSimples.getId());
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Tentativa de carregar entidade apartide de um bean nulo", t);
        }
        return null;

    }

    /**
     *
     * Retorna o primeiro registro da tabela
     *
     * @param pClasse Classe Entity que representa a tabela
     * @param pEM
     * @return
     */
    public static Object getRegistroByPrimeiro(Class pClasse, EntityManager pEM) {
        return selecaoRegistro(pEM, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.PRIMEIRO_REGISTRO, null);

    }

    /**
     *
     * Localiza um único registro do tipo empresa procurando por: _____________
     * Em caso de numero: Telefones CNPJ e ID __1______________________________
     * Em caso de String pelo nome, site, e e-mail
     *
     * @param pClasse
     * @param pParametro
     * @param pEM
     * @return
     */
    public static Object getEmpresa(Class pClasse, String pParametro, EntityManager pEM) {
        return selecaoRegistro(pEM, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.ENCONTRAR_EMPRESA, pParametro);

    }

    public static List getEmpresas(Class pClasse, String pParametro, EntityManager pEM) {
        boolean isNumerico = false;
        try {
            Integer.parseInt(pParametro);
        } catch (Throwable t) {
            isNumerico = false;
        }
        if (isNumerico) {
            List resposta = new ArrayList();
            Object empresa = UtilSBPersistencia.getRegistroByID(pClasse, Integer.parseInt(pParametro), pEM);
            if (empresa != null) {
                resposta.add(empresa);
            }
            return resposta;
        } else {
            return UtilSBPersistencia.getListaRegistrosLikeNomeCurto(pParametro, pClasse, pEM);
        }

    }

    public static Object getPessoa(Class pClasse, String pParametro, EntityManager pEM) {
        throw new UnsupportedOperationException("Ainda não implementado");

    }

    public static List<Object> getPessoas(Class pClasse, String pParametro, EntityManager pEM) {
        throw new UnsupportedOperationException("Ainda não implementado");

    }

    /**
     *
     *
     * @param pClasse Classe do registro
     * @param id id do registro
     * @return regustro encontrado
     */
    public static Object getRegistroByID(Class pClasse, int id) {
        return selecaoRegistro(null, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.ID, id);
    }

    public static Class<?> getEntityByTag(String pTag) {
        EntityManager em = UtilSBPersistencia.getNovoEM();
        //((Dados) BeansUtil.getAppBean("dados")).getEm();
        Set<EntityType<?>> lista = em.getMetamodel().getEntities();
        for (EntityType<?> teste : lista) {
            System.out.println(teste.getJavaType().toString());
            Class<?> classe = teste.getJavaType();
            System.out.println(teste.getClass().getName());
            return classe;
        }
        em.close();
        return null;
    }

    public static List<?> getListaBySBNQ(SBNQ pSBNQ) {
        return pSBNQ.getQueryHibernate().getResultList();
    }

    private static boolean executaSQLcmd(EntityManager pEm, String pSQl) {
        EntityManager em = pEm;
        if (em == null) {
            em = getNovoEM();
        }
        try {

            try {
                em.getTransaction().begin();
                Query q = em.createNativeQuery(pSQl);
                q.executeUpdate();
                em.getTransaction().commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
            return true;
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro executando comando SQL", e);
            return false;
        }
    }

    public static boolean executaSQL(String pSql) {
        return executaSQLcmd(null, pSql);
    }

    public static boolean executaSQL(EntityManager pEm, String pSql) {
        return executaSQLcmd(pEm, pSql);
    }

    public static Long getQuantidadeRegistrosNaTabela(Class pClasse) {
        return (Long) selecaoRegistro(null, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.QUANTIDADE_REGISTROS, null);
    }

    public static Long getQuantidadeRegistrosNaTabela(Class pClasse, EntityManager pEM) {
        return (Long) selecaoRegistro(pEM, null, null, pClasse, UtilSBPersistencia.TipoSelecaoRegistro.QUANTIDADE_REGISTROS, null);
    }

    public static Object superMerge(ItfBeanSimples pEntidade, EntityManager em) {

        throw new UnsupportedOperationException();

    }

}
