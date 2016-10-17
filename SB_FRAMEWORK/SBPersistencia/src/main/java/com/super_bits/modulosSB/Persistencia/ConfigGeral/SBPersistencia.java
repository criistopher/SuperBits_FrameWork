/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
/**
 * Configurações gerais do modulo de Persistencia
 */
package com.super_bits.modulosSB.Persistencia.ConfigGeral;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaFabricas;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreResources;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreShellBasico;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ItfInfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.UtilSBCoreReflexaoCampos;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;

public abstract class SBPersistencia {
    // VARIAVEIS DE SISTEMA

    /**
     * Informa o nome do PersistenceUnit padrão do sistema
     */
    private enum TipoBanco {

        MYSQL, ORACLE12
    }

    private static String nomeFactureManager = "UCPL";
    private static String formatoDataBanco = "yyyy-MM-dd";
    private static String formatoDataUsuario = "dd/MM/yy";
    private static String pastaImagensJPA = "/img";
    private static boolean configurado = false;
    private static final TipoBanco TIPO_BANCO = TipoBanco.MYSQL;

    private static final String ARQUIVO_COMPILA_BANCO = "compilaBanco.sh";
    private static final String ARQUIVO_APAGA_BANCO = "apagaBanco.sh";
    private static final String ARQUIVO_CONFIGURACOES = "SBProjeto.prop";
    private static final String ARQUIVO_CARREGA_BANCO = "carregaBanco.sh";
    private static final String ARQUIVO = "hashbanco.info";
    private static final String DESTINO_ARQUIVO_CARREGA_BANCO = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + ARQUIVO_CARREGA_BANCO;
    private static final String DESTINO_ARQUIVO_COMPILA_BANCO = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + ARQUIVO_COMPILA_BANCO;
    private static final String DESTINO_ARQUIVO_APAGA_BANCO = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + ARQUIVO_APAGA_BANCO;
    private static final String DESTINO_ARQUIVO_CONFIGURACOES = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + ARQUIVO_CONFIGURACOES;
    private static final String DESTINO_ARQUIVO_HASH_BANCO = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + ARQUIVO;

    private static Class<? extends ItfFabrica>[] fabricasRegistrosIniciais;

    public static void configuraJPA(ItfConfigSBPersistencia configurador) {
        configuraJPA(configurador, true);
    }

    /**
     *
     * Encontra o arquivo Java referente a esta classe na pasta modelRegras
     *
     * O método não Gera erro caso o arquivo não exista
     *
     * @param pClasseEntidade
     * @return
     */
    public static String getCaminhoArquivoModelJavaByClasse(Class pClasseEntidade) {
        try {
            if (!SBCore.isEmModoDesenvolvimento()) {
                throw new UnsupportedOperationException("O caminho do arquivo model só pode ser obtido em modo desenvolvimento");
            }

            return SBCore.getCaminhoGrupoProjetoSource() + "/modelRegras/src/main/java/" + pClasseEntidade.getCanonicalName().replaceAll("\\.", "/") + ".java";
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     *
     * @param pClasse
     * @return Hash do arquivo javam ou 0 caso o arquivo não seja encontrado
     */
    public static long getHashCaminhoArquivoModelJava(Class pClasse) {
        try {
            String caminhoArquivp = getCaminhoArquivoModelJavaByClasse(pClasse);
            if (!new File(caminhoArquivp).exists()) {
                //  System.out.println("Nao encontrado [" + caminhoArquivp);
                return 0;
            }
            InputStream teste = UTilSBCoreInputs.getStreamByLocalFile(caminhoArquivp);

            return (Arrays.hashCode(IOUtils.toByteArray(teste)));
        } catch (Throwable ex) {
            return 0;
        }

    }

    private static void configurarCamposDeEntidade() {
        try {
            EntityManager teste = UtilSBPersistencia.getNovoEM();
            Metamodel mm = teste.getEntityManagerFactory().getMetamodel();
            Set<EntityType<?>> entidades = mm.getEntities();
            List<Class> classesDeEntidades = new ArrayList<>();
            System.out.println("Configurando Campos de entidades");
            for (EntityType<?> entidade : entidades) {
                if (!entidade.getJavaType().getSimpleName().contains("Acao")) {
                    classesDeEntidades.add(entidade.getJavaType());
                }
            }

            UtilSBCoreReflexaoCampos.configurarTodasAsClasses(classesDeEntidades);
            System.out.println("Campos de entidade configurados com sucesso" + classesDeEntidades);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve um erro configurando os campos de entidade ", t);
        }
    }

    private static boolean houveAlteracaoHomologacaoBanco(ItfConfigSBPersistencia configurador) {
        long codigoAlteracao = 0;
        for (Class entidade : UtilSBPersistencia.getTodasEntidades()) {
            codigoAlteracao += UtilSBCoreResources.getHashCodeClasseDoPacote(entidade);
        }
        for (Class fabrica : fabricasRegistrosIniciais) {
            codigoAlteracao += UtilSBCoreResources.getHashCodeClasseDoPacote(fabrica);
        }
        codigoAlteracao += UtilSBCoreResources.getHashCodeClasseDoPacote(configurador.getClass());

        if (!new File(DESTINO_ARQUIVO_HASH_BANCO).exists()) {
            UtilSBCoreArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(DESTINO_ARQUIVO_HASH_BANCO, "0000");
        }
        //      UtilSBCoreArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(DESTINO_ARQUIVO_HASH_BANCO, provaTXT);
        String alteracaoAnterior = UTilSBCoreInputs.getStringByArquivoLocal(DESTINO_ARQUIVO_HASH_BANCO);

        Long altAnterior = NumberUtils.toLong(alteracaoAnterior);
        System.out.println("Hash   anterior " + altAnterior + "  em" + DESTINO_ARQUIVO_HASH_BANCO);
        System.out.println("Hash Atualizado " + codigoAlteracao);
        codigoAlteracao = Math.abs(codigoAlteracao);
        long diferenca = codigoAlteracao - altAnterior;
        if (diferenca == 0) {
            return false;
        } else {
            UtilSBCoreArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(DESTINO_ARQUIVO_HASH_BANCO, String.valueOf(codigoAlteracao));
            return true;
        }

    }

    public static void defineFacturyPadrao() {

    }

    public static void criaScriptsBancoDeDAdos(ItfConfigSBPersistencia pConfigurador) {
        //UtilSBCore String caminhosScript = (SBCore.getCaminhoGrupoProjetoSource() + "/compilaBanco.sh");

        File arquivoApaBanco = new File(DESTINO_ARQUIVO_APAGA_BANCO);
        if (!arquivoApaBanco.exists()) {
            Class classeDoResource = pConfigurador.getClass();

            UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, ARQUIVO_CONFIGURACOES, DESTINO_ARQUIVO_CONFIGURACOES);
            UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, ARQUIVO_APAGA_BANCO, DESTINO_ARQUIVO_APAGA_BANCO);
            UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, ARQUIVO_COMPILA_BANCO, DESTINO_ARQUIVO_COMPILA_BANCO);
            UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, ARQUIVO_CARREGA_BANCO, DESTINO_ARQUIVO_CARREGA_BANCO);

            String retorno = UtilSBCoreShellBasico.executeCommand("chmod +x " + getPastaExecucaoScriptsSQL() + "/*.sh");
            System.out.println("Retorno CHNOD +X-->" + retorno);
        }
    }

    public static void carregaBanco() {
        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("o carregamento automatico do banco só pode ser realizado em modo desenvolvimento");
        }
        //  IO.co tring teste;
        File script = new File(DESTINO_ARQUIVO_APAGA_BANCO);
        if (!script.exists()) {
            throw new UnsupportedOperationException("O arquivo de script para apagar banco não foi encontrado em " + script);
        }
        String retornoCarrregaBanco = UtilSBCoreShellBasico.executeCommand(DESTINO_ARQUIVO_CARREGA_BANCO);
        System.out.println("Retorno Carrega Banco" + retornoCarrregaBanco);

    }

    public static void compilaBanco() {

        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("A compilação do banco só pode ser realizada em modo desenvolvimento");
        }
        //  IO.co tring teste;
        File script = new File(DESTINO_ARQUIVO_APAGA_BANCO);
        if (!script.exists()) {
            throw new UnsupportedOperationException("O arquivo de script para apagar banco não foi encontrado em " + script);
        }
        String retornoCompilaBanco = UtilSBCoreShellBasico.executeCommand(DESTINO_ARQUIVO_COMPILA_BANCO);
        System.out.println("Retorno Compila Banco->" + retornoCompilaBanco);

    }

    /**
     *
     * @param configurador
     * @param pCriarTodosCampos Indica se o caminho para todos os campos deve
     * ser préconfigurado
     */
    public static void configuraJPA(ItfConfigSBPersistencia configurador, boolean pCriarTodosCampos) {
        nomeFactureManager = configurador.bancoPrincipal();
        formatoDataBanco = configurador.formatoDataBanco();
        formatoDataUsuario = configurador.formatoDataUsuario();
        pastaImagensJPA = configurador.pastaImagensJPA();
        fabricasRegistrosIniciais = configurador.fabricasRegistrosIniciais();
        configurado = true;
        Map<String, Object> propriedades = new HashMap<>();
        criaScriptsBancoDeDAdos(configurador);
        if (SBCore.getEstadoAPP().equals(SBCore.ESTADO_APP.DESENVOLVIMENTO)) {
            // desabilitando criação de banco de dados no início caso o banco seja o mesmo
            propriedades.put("hibernate.hbm2ddl.auto", null);
            // Mostrar SQL
            propriedades.put("hibernate.show_sql", true);
            //Mostrar SQL formatado
            propriedades.put("hibernate.format_sql", true);
            //Mostrar comentários explicativos
            propriedades.put("hibernate.use_sql_comments", true);
            EntityManagerFactory emFacturePadrao = Persistence.createEntityManagerFactory(nomeFactureManager, propriedades);
            UtilSBPersistencia.defineFabricaEntityManager(emFacturePadrao, propriedades);
            if (houveAlteracaoHomologacaoBanco(configurador)) {
                try {
                    UtilSBPersistencia.getEmfabricaPadrao().close();
                } catch (Throwable t) {
                    System.out.println("Erro tentnaod fechar entitymanager factury para criação de novo banco");
                }
                limparBanco();
                propriedades.put("hibernate.hbm2ddl.auto", "create-drop");
                emFacturePadrao = Persistence.createEntityManagerFactory(nomeFactureManager, propriedades);
                UtilSBPersistencia.defineFabricaEntityManager(emFacturePadrao, propriedades);

                EntityManager executor = UtilSBPersistencia.getNovoEM();

                if (fabricasRegistrosIniciais != null) {
                    for (Class classe : fabricasRegistrosIniciais) {
                        UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(classe, executor, UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ORDEM_DE_DECLARCAO);
                    }
                }
                executor.close();
                configurador.criarBancoInicial();
                compilaBanco();

                //senão houve alterção no banco
            } else {
                limparBanco();
                carregaBanco();
                UtilSBPersistencia.renovarFabrica();
            }

            // SENÃO (ESTÁDO DIFERENTE DE EM DESENVOLVIMENTO): (A FUNÇÃO DE LIMPAR E SUBIR O BANCO CABE AO SCRIPT DE IMPLANTAÇÃO , E NAÕ DURANTE EXECUÇÃO DO CODIGO)
            // TODO remover essa caixa alta..
        } else {
            // desabilitando hbm2dllauto por segurança
            propriedades.put("hibernate.hbm2ddl.auto", null);
            // Mostrar SQL
            propriedades.put("hibernate.show_sql", false);
            //Mostrar SQL formatado
            propriedades.put("hibernate.format_sql", true);
            //Mostrar comentários explicativos
            propriedades.put("hibernate.use_sql_comments", false);
            EntityManagerFactory emFacturePadrao = Persistence.createEntityManagerFactory(nomeFactureManager, propriedades);
            UtilSBPersistencia.defineFabricaEntityManager(emFacturePadrao, propriedades);
        }

        if (pCriarTodosCampos) {
            configurarCamposDeEntidade();
        }

    }

    public static void criarRegistrosIniciais() {
        validaConfigurado();

        if (fabricasRegistrosIniciais != null) {
            for (Class classe : fabricasRegistrosIniciais) {
                UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(classe, UtilSBPersistencia.getNovoEM(), UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ID);
            }
        }
    }

    public static void limparBanco() {

        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("A limpeza do banco só pode ser realizada em modo desenvolvimento");
        }
        String caminhosScript = (SBCore.getCaminhoGrupoProjetoSource() + "/apagaBanco.sh");
        File script = new File(caminhosScript);
        if (!script.exists()) {
            throw new UnsupportedOperationException("O arquivo de script para apagar banco não foi encontrado em " + script);
        }
        String retornoApagaBanco = UtilSBCoreShellBasico.executeCommand(caminhosScript);
        System.out.println("Retorno Apaga banco=" + retornoApagaBanco);
        if (!retornoApagaBanco.contains("dropped")) {
            throw new UnsupportedOperationException("A palavra dropped não apareceu no retorno do comando apagaBanco.sh que integra as boas práticas de Devops do frameWork" + retornoApagaBanco);
        }

        //criarRegistrosIniciais();
    }

    /**
     * Valida se foi realizada a configuração do APP
     */
    private static void validaConfigurado() {
        if (configurado) {
            return;
        }
        try {
            System.out.println("CONFIG DO SBPERSISTENCIA NAO DEFINIDO !!");
            throw new UnsupportedOperationException("Erro o config da persistencia não foi defido");
        } catch (Throwable t) {
            ItfInfoErroSBComAcoes erro = (InfoErroSBComAcoes) new ErroSBCoreDeveloperSopMessagem();
            erro.configurar(FabMensagens.ERRO.getMsgDesenvolvedor(t.getMessage()), FabErro.PARA_TUDO, t);
            erro.executarErro();

        }

        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            System.exit(0);
        } else {
            System.out.println("A configuração da persistencia não foi definida");
        }
        //    configCoreNaoDefinido.Alerta(ErroSB.TIPO_ERRO.PARA_TUDO, "CONFIG DO CORE NAO DEFINIDO");
    }

    public static String getNomeBancoPadrao() {
        validaConfigurado();
        return nomeFactureManager;
    }

    public static String getFormatoDataBanco() {
        validaConfigurado();
        return nomeFactureManager;
    }

    public static String getFormatoDataUsuario() {
        validaConfigurado();
        return nomeFactureManager;
    }

    public static String getPastaImagensJPA() {
        validaConfigurado();
        return pastaImagensJPA;
    }

    public static Date getDataFormatoBanco(String databanco) {
        validaConfigurado();
        SimpleDateFormat sd = new SimpleDateFormat(getFormatoDataBanco());
        try {
            Date data = sd.parse(databanco);
            return data;
        } catch (ParseException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo data no formato do banco" + databanco, e);
        }

        return null;
    }

    /**
     * Realiza o backup do banco principal, analiza o tipo de banco pela
     * variavel tipoBanco e executa as rotinas nescessárias
     *
     * A estrutura de pasta do arquivo é:
     * SBCore.getDiretorioBackup()/ano/mes/semana[1,2,3,4]/diadaSemana/banco.zip
     *
     * O arquivo de backup será criado com um nome temporário, após a
     * confirmação do sucesso seu nome é renomeado configurado
     *
     * As imagens da pastaImagensJPA também são copiadas em arquivo separado na
     * mesma pasta ImagensJPA.zip
     *
     *
     *
     * @return True se realizado com sucesso, False se não realizado com sucesso
     */
    public static synchronized boolean realizarBackupBancoPrincial() {
        EntityManager emBackup = UtilSBPersistencia.getNovoEM();
        throw new UnsupportedOperationException("Ainda não implementado");//TODO Edu
    }

    public static String getPastaExecucaoScriptsSQL() {
        if (SBCore.getEstadoAPP().equals(SBCore.ESTADO_APP.DESENVOLVIMENTO)
                || SBCore.getEstadoAPP().equals(SBCore.ESTADO_APP.HOMOLOGACAO)) {
            return SBCore.getCaminhoGrupoProjetoSource();
        } else {
            return "/home/git/publicados/" + SBCore.getGrupoProjeto() + "/";
        }

    }

}
