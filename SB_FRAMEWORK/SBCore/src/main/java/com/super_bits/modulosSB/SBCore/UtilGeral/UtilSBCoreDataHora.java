/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe de UTILITÀRIOS (Métodos EStáticos commmente Utilizados)____________
 * Funções relativas a data e hora dos aplicativos
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 25/05/2014
 *
 */
public class UtilSBCoreDataHora {

    public final static Long HORASDIA = 24L;
    public final static Long MESESANO = 12L;
    public final static Long MINUTOSHORA = 60L;
    public final static Long SEGUNDOSMINUTO = 60L;
    public final static Long QTDMILISEGUNDOSSEGUNDO = 1000L;
    public final static Long DIASMES = 30L;

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
    public static long intervaloTempoSegundos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);

        if (diferenca != null) {
            return intervaloTempoSegundos(diferenca);
        } else {
            return 0;
        }

    }

    /**
     *
     * @param pIntervaloTempo
     * @return Ingeter de um intervalo de tempo em segundos
     */
    public static long intervaloTempoSegundos(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / QTDMILISEGUNDOSSEGUNDO;
            return intervalo;
        }
        return 0;
    }

    /**
     *
     * @param pDatahoraInicio
     * @param pDatahoraFim
     * @return intervalo entre duas datas em minutos
     */
    public static long intervaloTempoMinutos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);

        if (diferenca != null) {
            return intervarlTempoMinutos(diferenca);
        } else {
            return 0;
        }

    }

    /**
     *
     * @param pIntervaloTempo
     * @return Inteiro de quantidade de tempo em minutos da um intervalo de
     * tempo entre duas datas
     */
    public static long intervarlTempoMinutos(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO);
            return intervalo;
        }
        return 0;

    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Retorna a quantidade de horas entre duas datas
     */
    public static long intervaloTempoHoras(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);

        if (diferenca != null) {
            return intervaloTempoHoras(diferenca);
        } else {
            return 0;
        }

    }

    /**
     *
     * @param pIntervaloTempo
     * @return Um valor inteiro de horas que um intervalor de tempo Long
     * representa
     *
     */
    public static long intervaloTempoHoras(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA);
            return intervalo;
        }
        return 0;

    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return Retorna o intervalo de tempo em dias de uma data inicial até uma
     * data final
     */
    public static long intervaloTempoDias(Date pDataInicial, Date pDataFinal) {

        Long diferenca = intervaloTempoMileSegundos(pDataInicial, pDataFinal);
        if (diferenca != null) {
            return intervaloTempoDias(diferenca);
        } else {
            return 0;
        }

    }

    /**
     *
     * @param pIntervaloTempo
     * @return Um valor inteiro de dias que correspondente a um intervalo de
     * tempo Long informado no parâmetro
     */
    public static long intervaloTempoDias(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA);
            return intervalo;
        }
        return 0;
    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return quantidade de meses entre duas datas
     */
    public static long intervaloTempoMeses(Date pDataInicial, Date pDataFinal) {

        Long diferenca = intervaloTempoMileSegundos(pDataInicial, pDataFinal);
        if (diferenca != null) {
            return intervaloTempoMeses(diferenca);
        } else {
            return 0;
        }
    }

    /**
     *
     * @param pIntervaloTempo
     * @return Valor inteiro que corresponde a Meses de um intervalo de tempo
     * Long passado como parâmetro
     */
    public static long intervaloTempoMeses(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = pIntervaloTempo / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES;
            return intervalo;
        }
        return 0;

    }

    /**
     *
     * @param pDataInicial
     * @param pDataFinal
     * @return quantidade de meses entre duas datas
     */
    public static long intervaloTempoAnos(Date pDataInicial, Date pDataFinal) {

        Long diferenca = intervaloTempoMileSegundos(pDataInicial, pDataFinal);
        if (diferenca != null) {
            return intervaloTempoAnos(diferenca);
        } else {
            return 0;
        }
    }

    /**
     *
     * @param pIntervaloTempo
     * @return Um valor Inteiro correspondente a anos de um intervalo de tempo
     * Long passado como parâmetro
     */
    public static long intervaloTempoAnos(Long pIntervaloTempo) {

        Long intervalo;
        if (pIntervaloTempo != null) {
            intervalo = ((pIntervaloTempo) / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES) / MESESANO;
            return intervalo;
        }
        return 0;
    }

    public static List<Long> intervaloTempoBaseAnos(Long pIntervaloTempo) {

        // Dúvida, se este método é necessário ou se posso utilizar os resultados dos outros métodos e calcular sobre estes os valores que preciso
        List<Long> intevalos = new ArrayList<>();

        Long meses = (intervaloTempoAnos(pIntervaloTempo) % MESESANO) + intervaloTempoMeses(pIntervaloTempo);
        intevalos.add(meses);
        return intevalos;
    }

    /**
     *
     * @param pDatahoraInicio Data inicial (que será subitraida da Data Final)
     * @param pDatahoraFim Data Final
     * @return Rorna um Array com de integer sendo anos->[0] meses->=[1] e assim
     * sucessivamente
     */
    public static Integer[] intervaloTempoDiasHorasMinitosSegundos(Date pDatahoraInicio, Date pDatahoraFim) {

        Long diferenca = intervaloTempoMileSegundos(pDatahoraInicio, pDatahoraFim);
        if (diferenca != null) {

            Integer[] resp = new Integer[6];

            resp[5] = new Long((diferenca / QTDMILISEGUNDOSSEGUNDO) % MINUTOSHORA).intValue(); // segundos
            resp[4] = new Long(diferenca / (SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) % MINUTOSHORA).intValue(); // Minutos
            resp[3] = new Long(diferenca / (SEGUNDOSMINUTO * MINUTOSHORA * QTDMILISEGUNDOSSEGUNDO) % HORASDIA).intValue(); // Horas
            resp[2] = new Long(diferenca / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO)).intValue();// Dias
            resp[1] = new Long(diferenca / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES).intValue(); // Meses
            resp[0] = new Long(((diferenca) / (HORASDIA * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO) / DIASMES) / MESESANO).intValue(); // Anos
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

        novadata = pData.getTime() - pMinutos * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO;
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

        novadata = novadata = pData.getTime() - pDias * (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA);
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

        long dataAntiga = pData.getTime();
        long incremento = (pNumeroDias * (QTDMILISEGUNDOSSEGUNDO * SEGUNDOSMINUTO * MINUTOSHORA * HORASDIA));
        novadata = (dataAntiga + incremento);
        Date dataRetorno = new Date(novadata);
        return dataRetorno;
    }

    public static long interTempContRegSegundos(Date pDataInicial, Date pDataFinal) {

        long diferencaSegundos = intervaloTempoSegundos(pDataInicial, pDataFinal);
        while (diferencaSegundos > 59 || diferencaSegundos % 60 == 0) {
            diferencaSegundos--;
        }
        return diferencaSegundos;
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

        novadata = pData.getTime() + pSegundos * QTDMILISEGUNDOSSEGUNDO;
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

        novadata = pData.getTime() + pHoras * MINUTOSHORA * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO;
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

        novadata = pData.getTime() + pMinutos * SEGUNDOSMINUTO * QTDMILISEGUNDOSSEGUNDO;
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
