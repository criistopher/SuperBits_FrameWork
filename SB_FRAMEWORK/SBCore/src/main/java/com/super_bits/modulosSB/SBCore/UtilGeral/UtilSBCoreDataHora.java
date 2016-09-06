/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.lang3.time.DateUtils;

/**
 * Classe de UTILITÀRIOS (Métodos EStáticos commmente Utilizados)____________
 * Funções relativas a data e hora dos aplicativos
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 25/05/2014
 *
 */
public class UtilSBCoreDataHora {

    public static enum FORMATO_TEMPO {

        DATA_SISTEMA,
        DATA_USUARIO,
        HORA_SISTEMA,
        HORA_USUARIO,
        DATA_HORA_USUARIO,
        DATA_HORA_SISTEMA,
        DATA_HORA_AMERICANO,
        DATA_SEM_SEPARADOR,
        HORA_SEM_SEPARADOR,
        PERSONALIZADO

    }

    public static SimpleDateFormat datahoraSistemaFr = new SimpleDateFormat("dd-MM-yy [HH-mm-ssss]");
    public static SimpleDateFormat horaUsuarioFr = new SimpleDateFormat("HH:mm:ss");

    public static String getDataSTRFormatoUsuario(Date pData) {
        return getDatePaternByFormatoTempo(FORMATO_TEMPO.DATA_USUARIO).format(pData);
    }

    public static int segundosEntre(Date pDatainicial, Date pDatafinal) {

        return 1;
    }

    public static int mileSegundosEntre(Date pDatainicial, Date pDatafinal) {
        return 1;
    }

    private static SimpleDateFormat getDatePaternByFormatoTempo(FORMATO_TEMPO pFormato) {
        return getDatePaternByFormatoTempo(pFormato, null);

    }

    private static SimpleDateFormat getDatePaternByFormatoTempo(FORMATO_TEMPO pFormato, String pPersonalizado) {
        switch (pFormato) {
            case DATA_HORA_SISTEMA:
                return new SimpleDateFormat("dd-MM-yy");
            case DATA_HORA_USUARIO:
                return new SimpleDateFormat("dd/MM/yy");
            case DATA_SISTEMA:
                return new SimpleDateFormat("[HH-mm-ss]");
            case DATA_USUARIO:
                return new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            case HORA_SISTEMA:
                return new SimpleDateFormat("[HH-mm-ss]");
            case HORA_USUARIO:
                return new SimpleDateFormat("[HH-mm-ss]");
            case DATA_HORA_AMERICANO:
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            case DATA_SEM_SEPARADOR:
                return new SimpleDateFormat("ddMMyyyy");
            case HORA_SEM_SEPARADOR:
                return new SimpleDateFormat("HHmmss");
            case PERSONALIZADO:

                if (pPersonalizado == null) {
                    throw new UnsupportedOperationException("Criou formato de data personalizado sem espeficar o formato");
                }
                try {
                    return new SimpleDateFormat(pPersonalizado);
                } catch (Throwable e) {

                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("erro criando um SimpleDateFormat no UtilCoreDAtaHora", e);
                }
            default:
                throw new AssertionError(pFormato.name());

        }

    }

    /**
     *
     * Retorna uma String com o horário do momento, de acordo com o formato
     * escolhido
     *
     * @param pFormato Formato Padronizado para Retorno de datas
     * @return o registro temporal do momento em string de acordo com o formato
     * solicitado
     */
    public static String getAgoraString(FORMATO_TEMPO pFormato) {
        SimpleDateFormat sdf = getDatePaternByFormatoTempo(pFormato);
        return sdf.format(new Date());

    }

    /**
     *
     * Retorna o horario do momento em um formato personalizado
     *
     * @param pformatoPersonalizado
     * @return
     */
    public static String getAgoraString(String pformatoPersonalizado) {
        SimpleDateFormat sdf = getDatePaternByFormatoTempo(FORMATO_TEMPO.PERSONALIZADO, pformatoPersonalizado);

        return sdf.format(new Date());

    }

    /**
     *
     *
     * @see
     * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">Veja
     * os Parametros Possíveis</a>
     * @serialData
     * @param pDAta
     * @param pformatoPersonalizado
     * @return
     */
    public static String getDataHoraString(Date pDAta, String pformatoPersonalizado) {
        SimpleDateFormat sdf = getDatePaternByFormatoTempo(FORMATO_TEMPO.PERSONALIZADO, pformatoPersonalizado);

        return sdf.format(pDAta);

    }

    public static String getDataHoraString(Date pDAta, FORMATO_TEMPO pFormato) {
        SimpleDateFormat sdf = getDatePaternByFormatoTempo(pFormato);

        return sdf.format(pDAta);

    }

    /**
     *
     * @return Um new Date();
     */
    public static Date gethoje() {

        return new Date();

    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Retorna a quantidade de mileSegundos entre um tempo e outro
     */
    public static Long intervaloTempoMileSegundos(Date pDatahoraInicio, Date pDatahoraFim) {
        if (pDatahoraInicio == null || pDatahoraFim == null) {
            return null;

        }
        return pDatahoraFim.getTime() - pDatahoraInicio.getTime();
    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Retorna a quantidade de segundos entre um tempo e outro
     */
    public static Long intervaloTempoSegundos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);
        if (diferenca != null) {
            return diferenca / 1000;
        } else {
            return null;
        }

    }

    /**
     *
     * @param pDatahoraInicio
     * @param pDatahoraFim
     * @return
     */
    public static Long intervaloTempoMinutos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);
        if (diferenca != null) {
            return diferenca / 1000 % 60;
        } else {
            return null;
        }

    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Retorna a quantidade de horas
     */
    public static Long intervaloTempoHoras(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);
        if (diferenca != null) {
            return diferenca / 1000 % 60;
        } else {
            return null;
        }

    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return Retorna o intervalo de tempo em dias de uma data inicial até uma
     * data final
     */
    public static long intervaloTempoDias(Date pDataInicial, Date pDataFinal) {
        Date dataInicial = pDataInicial;
        Date dataFinal = pDataFinal;
        long diferencaEmDias = (dataFinal.getTime() - dataInicial.getTime()) / (1000 * 60 * 60 * 24);
        return diferencaEmDias;

    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Rorna um Array com de integer sendo dias->[0] horas->=[1] e assim
     * sucessivamente
     */
    public static Integer[] intervaloTempoDiasHorasMinitosSegundos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);
        if (diferenca != null) {

            Integer[] resp = new Integer[4];

            resp[3] = new Long(diferenca / 1000 % 60).intValue(); // segundos
            resp[2] = new Long(diferenca / (60 * 1000) % 60).intValue(); // Minutos
            resp[1] = new Long(diferenca / (60 * 60 * 1000) % 24).intValue(); // Horas
            resp[0] = new Long(diferenca / (24 * 60 * 60 * 1000)).intValue();// Dias

            return resp;
        } else {
            return null;
        }

    }

    /**
     *
     * Decrementa a quantidade de minutos especificada nos parametros apartir de
     * um objeto Date
     *
     * @param pData Data referencia
     * @param pMinutos Quantidade de minutos para decrementar
     * @return
     */
    public static Date decrementaMinutos(Date pData, int pMinutos) {
        if (pMinutos == 0) {
            return pData;
        }
        long novadata;

        novadata = pData.getTime() - pMinutos * 60 * 1000;
        return new Date(novadata);
    }

    /**
     *
     * Decrementa a partir de uma Data obtida, o número de dias enviado,
     * preservando os minutos, e segundos
     *
     * @param pData Data de Referencia
     * @param pDias Número de dias que será decrementado
     * @return A data decrementada
     */
    public static Date decrementarDias(Date pData, int pDias) {
        if (pDias == 0) {
            return pData;
        }
        long novadata;

        novadata = novadata = pData.getTime() - pDias * 24 * 60 * 60 * 1000;
        return new Date(novadata);

    }

    /**
     *
     * @param pData Data de referência
     * @param pNumeroDias numero de dias para incrementar
     * @return
     */
    public static Date incrementaDias(Date pData, int pNumeroDias) {
        if (pNumeroDias == 0) {
            return pData;
        }
        long novadata;

        novadata = pData.getTime() + pNumeroDias * 24 * 60 * 60 * 1000;
        return new Date(novadata);
    }

    /**
     *
     * @param pData Data referência
     * @param pSegundos Segundos para incrementar
     * @return
     */
    public static Date incrementaSegundos(Date pData, int pSegundos) {
        if (pSegundos == 0) {
            return pData;
        }
        long novadata;

        novadata = pData.getTime() + pSegundos * 1000;
        return new Date(novadata);
    }

    /**
     *
     * @param pData Data referência
     * @param pHoras Quantidade de horas para incrementar
     * @return
     */
    public static Date incrementaHoras(Date pData, int pHoras) {
        if (pHoras == 0) {
            return pData;
        }
        long novadata;

        novadata = pData.getTime() + pHoras * 60 * 60 * 1000;
        return new Date(novadata);
    }

    /**
     *
     * @param pData Data referencia
     * @param pMinutos Quantidade de minutos para incrementar
     * @return
     */
    public static Date incrementaMinutos(Date pData, int pMinutos) {
        if (pMinutos == 0) {
            return pData;
        }
        long novadata;

        novadata = pData.getTime() + pMinutos * 60 * 1000;
        return new Date(novadata);
    }

    /**
     *
     * @param pData Data de referencia
     * @param pDiaHoraMinutoSegundo Matriz de inteiros, contendo nesta ordem 0->
     * Dias 1 Horas e assim sucessivamente
     * @return Nova data com incremento
     */
    public static Date incrementaDiaHorasMinutosSegundo(Date pData, Integer[] pDiaHoraMinutoSegundo) {
        Date novadata = pData;
        System.out.println("incrementando" + novadata + " dia" + pDiaHoraMinutoSegundo[0] + " horas" + pDiaHoraMinutoSegundo[1] + "minutos" + pDiaHoraMinutoSegundo[2] + "segundos" + pDiaHoraMinutoSegundo[3]);
        novadata = incrementaDias(novadata, pDiaHoraMinutoSegundo[0]);
        novadata = incrementaHoras(novadata, pDiaHoraMinutoSegundo[1]);
        novadata = incrementaMinutos(novadata, pDiaHoraMinutoSegundo[2]);
        novadata = incrementaSegundos(novadata, pDiaHoraMinutoSegundo[3]);
        return novadata;
    }

    public static Date incrementaDiaHorasMinutosSegundosDiasUteis(Date pData, Integer[] pDiaHoraMinutoSegundo) {

        Date novadata = pData;

        novadata = incrementaDiaHorasMinutosSegundo(pData, pDiaHoraMinutoSegundo);
        int feriados = 0;
        for (Date dataRef = pData; dataRef.getTime() < novadata.getTime(); dataRef = incrementaDias(pData, 1)) {
            if (dataRef.getDay() == 0 || dataRef.getDay() == 6) {
                feriados++;
            }
        }
        novadata = incrementaDias(pData, feriados);
        return novadata;
    }

}
