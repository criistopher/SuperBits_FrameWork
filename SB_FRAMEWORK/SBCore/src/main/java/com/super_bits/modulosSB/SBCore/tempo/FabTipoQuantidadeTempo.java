/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.tempo;

import java.util.Date;

/**
 *
 * @author salvioF
 */
public enum FabTipoQuantidadeTempo {

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

    public long calcularQuantidade(Long valor, FabTipoQuantidadeTempo tipoBaseCalculo, boolean ignorarSemanas) {

        switch (this) {
            case ANOS:
                // A base de Calculos sempre será anual

                break;
            case MESES:
                // Caso a base de calulos seja abaixo de Anos, não dividir por 12

                break;
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
                break;
            case HORAS:
                //caso a base de calculo for :
                //Anos: dividir apenas por 365
                //Meses:
                //Semanas:
                //dias:
                //Minutos:  (não dividir por minutos)
                //Segundos: (dividir por tudo)

                break;
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

}
