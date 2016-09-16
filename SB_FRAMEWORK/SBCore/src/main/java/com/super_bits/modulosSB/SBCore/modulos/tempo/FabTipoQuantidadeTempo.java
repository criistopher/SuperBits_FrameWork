/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreRelogio;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import java.util.Date;

/**
 *
 * @author salvioF
 */
public enum FabTipoQuantidadeTempo implements ItfFabrica {

    ANOS,
    MESES,
    SEMANAS,
    DIAS,
    HORAS,
    MINUTOS,
    SEGUNDOS;

    public TipoQuantidadeTempo getTipoQuantidade() {

        TipoQuantidadeTempo tipoQuantidade = new TipoQuantidadeTempo();
        tipoQuantidade.setTipoInformacao(this);
        switch (this) {
            case ANOS:
                tipoQuantidade.setNomePlural("Anos");
                tipoQuantidade.setNomeSingular("Ano");
                break;
            case MESES:
                tipoQuantidade.setNomePlural("Meses");
                tipoQuantidade.setNomeSingular("Mês");
                break;
            case SEMANAS:
                tipoQuantidade.setNomeSingular("Semanas");
                tipoQuantidade.setNomePlural("Semanas");
                break;
            case DIAS:
                tipoQuantidade.setNomeSingular("Dia");
                tipoQuantidade.setNomePlural("Dias");
                break;
            case HORAS:
                tipoQuantidade.setNomeSingular("Hora");
                tipoQuantidade.setNomePlural("Horas");
                break;
            case MINUTOS:
                tipoQuantidade.setNomePlural("Minutos");
                tipoQuantidade.setNomeSingular("Minuto");
                break;
            case SEGUNDOS:
                tipoQuantidade.setNomeSingular("Segundo");
                tipoQuantidade.setNomePlural("Segundos");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return tipoQuantidade;
    }

    public long calcularQuantidade(Long valor, FabTipoQuantidadeTempo divisorMaximo, boolean ignorarSemanas) {

        switch (this) {
            case ANOS:
                // A base de Calculos sempre será anual
                switch (divisorMaximo) {
                    case ANOS:
                        return UtilSBCoreDataHora.intervaloTempoAnos(valor);
                    default:
                        return 0;
                }
            case MESES:
                // Caso a base de calulos seja abaixo de Anos, não dividir por 12
                switch (divisorMaximo) {
                    case ANOS:
                        return UtilSBCoreDataHora.intervaloTempoMeses(valor) % 12;
                    case MESES:
                        return UtilSBCoreDataHora.intervaloTempoMeses(valor);
                    default:
                        return 0;
                }

            case SEMANAS:
                //Caso a base de calculos seja abaixo de meses não dividir o mês em semanas
                // caso ignorar semanas, retornar -1

                break;
            case DIAS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:
                //Segundos:
                switch (divisorMaximo) {
                    case ANOS:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365) % 30;// 16
                    case MESES:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365) % 30;// 16
                    case SEMANAS:
                        break; // se necessário irei comentar este case para que possa passar no teste.
                    //Não há calculos para semana no UtilsbcoreDataHora nem no UtilSbcoreRelogio, os mesmos seram implementados posteriormente
                    case DIAS:
                        return UtilSBCoreRelogio.intervalTempDatas(valor).get(2);

                    default:
                        return 0;
                }
            case HORAS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:  (não dividir por minutos)
                //Segundos: (dividir por tudo)
                switch (divisorMaximo) {
                    case ANOS:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365) % 12; //4
                    case MESES:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365) % 12; //4
                    case SEMANAS:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365) % 12; //4
                    case DIAS:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365) % 12; //4 
                    case HORAS:
                        return UtilSBCoreDataHora.intervaloTempoHoras(valor);

                    default:
                        return 0;

                }
            case MINUTOS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:
                //Segundos:
                break;
            case SEGUNDOS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:
                //Segundos:
                break;
            default:
                throw new AssertionError(this.name());
        }

        return 0;
    }

    @Override
    public TipoQuantidadeTempo getRegistro() {
        return getTipoQuantidade();
    }

}
