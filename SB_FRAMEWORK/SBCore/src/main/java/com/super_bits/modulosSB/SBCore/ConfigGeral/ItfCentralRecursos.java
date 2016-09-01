/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

/**
 *
 * @author salvioF
 */
public interface ItfCentralRecursos {

    public enum TIPO_ORIGEM {
        REMOTO_VIA_URL,
        LOCAL_DESKTOP_APP,
        LOCAL_APP_MOBILE,
        LOCAL_DEVELOPER,
        REMOTO_VIA_WEB_SERVICE;

    }

    public enum TIPO_ARMAZENAMENTO {
        PASTA_RESOURCE_WEB,
        PASTA_RESOURCE_JAR_FILE_DO_PROJETO,
        PASTA_RESOURCE_JAR_FILE_PROJETO_EXTERNO,
        PASTA_RESOURCE_WEB_ENTIDADE,
        PASTA_RESOURCE_APP_ENTIDADE;

    }

    public String getCaminhoParaRecurso();

}
