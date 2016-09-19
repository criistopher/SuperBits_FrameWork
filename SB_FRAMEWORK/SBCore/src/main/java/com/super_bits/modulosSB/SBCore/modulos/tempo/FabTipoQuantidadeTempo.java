/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.tempo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

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
                        return UtilSBCoreDataHora.intervaloTempoMeses(valor) % 12L;
                    case MESES:
                        return UtilSBCoreDataHora.intervaloTempoMeses(valor);
                    default:
                        return 0;
                }

            case SEMANAS:
                //Caso a base de calculos seja abaixo de meses não dividir o mês em semanas
                // caso ignorar semanas, retornar -1
                switch (divisorMaximo) {
                    case ANOS://2
                        Long semanas = 52L;
                        Long intervaloSemanas = UtilSBCoreDataHora.intervaloTempoSemanas(valor);
                        Long dif = intervaloSemanas - semanas;
                        return UtilSBCoreDataHora.intervaloTempoSemanas(valor) % 3;
                    case MESES:
                        return UtilSBCoreDataHora.intervaloTempoSemanas(valor) % 3;
                    case SEMANAS:
                        return UtilSBCoreDataHora.intervaloTempoSemanas(valor);
                    default:
                        return 0L;
                }

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
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 30L;// 16
                    case MESES:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 30L;// 16
                    case SEMANAS:
                        //
                        break;
                    case DIAS:
                        return UtilSBCoreDataHora.intervaloTempoDias(valor);
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
                        //Long diasAno = 365L; dias em um ano
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 24L; //4
                    case MESES:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 24L; //4
                    case SEMANAS:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 24L; //4
                    case DIAS:
                        return (UtilSBCoreDataHora.intervaloTempoDias(valor) - 365L) % 24L; //4
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
                switch (divisorMaximo) {
                    case ANOS: // 2
                        // Long minutosAno = 525600L; valor referência para somente um ano
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case MESES:
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case SEMANAS:
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case DIAS:
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case HORAS:
                        return (UtilSBCoreDataHora.intervarlTempoMinutos(valor) - 525600L) % 12; //2
                    case MINUTOS:
                        return UtilSBCoreDataHora.intervarlTempoMinutos(valor);
                    default:
                        return 0;
                }

            case SEGUNDOS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:
                //Segundos:
                switch (divisorMaximo) {
                    case ANOS: // 15
                        //Long segundosAno = 31536000L; // valor referência de segundos somente para um ano
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case MESES:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case SEMANAS:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case DIAS:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case HORAS:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case MINUTOS:
                        return (UtilSBCoreDataHora.intervaloTempoSegundos(valor) - 31536000L) % 60; //15
                    case SEGUNDOS:
                        return UtilSBCoreDataHora.intervaloTempoSegundos(valor);
                    default:
                        return 0L;

                }

            default:
                throw new AssertionError(this.name());
        }

    }

    @Override
    public TipoQuantidadeTempo getRegistro() {
        return getTipoQuantidade();
    }

}
