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
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ItfInfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreShellBasico;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public abstract class SBPersistencia {
    // VARIAVEIS DE SISTEMA

    /**
     * Informa o nome do PersistenceUnit padrão do sistema
     */
    private enum TipoBanco {

        MYSQL, ORACLE12
    }

    private static String nomeFactureManager = "UCPL";
    private static String formatoDataBanco = "yyy-MM-dd";
    private static String formatoDataUsuario = "dd/MM/yy";
    private static String pastaImagensJPA = "/img";
    private static boolean configurado = false;
    private static TipoBanco tipoBanco = TipoBanco.MYSQL;
    private static Class<? extends ItfFabrica>[] fabricasRegistrosIniciais;

    public static void configuraJPA(ItfConfigSBPersistencia configurador) {
        configuraJPA(configurador, true);
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
        if (pCriarTodosCampos) {
            UtilSBCoreReflexaoCampos.configurarTodasAsClasses(classesDeEntidades);

            System.out.println("Campos de entidade configurados com sucesso" + classesDeEntidades);

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
        String respApagaBanco = UtilSBCoreShellBasico.executeCommand(SBCore.getCaminhoGrupoProjeto() + "/apagaBanco.sh");
        if (!respApagaBanco.contains("dropped")) {
            throw new UnsupportedOperationException("A palavra dropped não apareceu no retorno do comando apagaBanco.sh que integra as boas práticas de Devops do frameWork" + respApagaBanco);
        }
        UtilSBPersistencia.renovarFabrica();
        criarRegistrosIniciais();
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
            throw new UnsupportedOperationException("Erro o config do core não foi defido");
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
            e.printStackTrace();
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

}
