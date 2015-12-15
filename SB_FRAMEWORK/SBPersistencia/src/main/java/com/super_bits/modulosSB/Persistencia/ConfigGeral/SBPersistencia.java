/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
/**
 * Configurações gerais do modulo de Persistencia
 */
package com.super_bits.modulosSB.Persistencia.ConfigGeral;

import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;

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

    public static void configuraJPA(ItfConfigSBPersistencia configurador) {
        nomeFactureManager = configurador.bancoPrincipal();
        formatoDataBanco = configurador.formatoDataBanco();
        formatoDataUsuario = configurador.formatoDataUsuario();
        pastaImagensJPA = configurador.pastaImagensJPA();
        configurado = true;
    }

    /**
     * Valida se foi realizada a configuração do APP
     */
    private static void validaConfigurado() {
        if (configurado) {
            return;
        }
        System.out.println("CONFIG DO SBPERSISTENCIA NAO DEFINIDO");
        System.exit(0);
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
