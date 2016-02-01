/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

/**
 *
 * @author sfurbino
 */
public abstract class UtilSBCoreValidacao {

    public static boolean ValidaInfoCampo(Campo pCampo, Object instancia) {
        throw new UnsupportedOperationException("Ainda não foi implementado");
    }

    public static boolean validaCampoPorTipo(Object pValor, FabCampos pCampo) {
        switch (pCampo) {
            case TEXTO_SIMPLES:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case IMG_PEQUENA:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case IMG_MEDIA:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case IMG_GRANDE:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case AAA_NOME_CURTO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case AAA_NOME_LONGO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case AAA_DESCRITIVO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case ID:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LAT:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LONG:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LCLOGRADOURO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LCCEP:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LCBairro:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LCCidade:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case Telefone:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LCComplemeto:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case HTML:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CHART_VALOR:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CHART_LABEL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CHART_CATEGORIA:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CALENDARIO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case TELEFONE_FIXO_NACIONAL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case MOEDA:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LOOKUP:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case LOOKUPMULTIPLO:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case COR:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case EMAIL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case SITE:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case URL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case RESPONSAVEL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");
            case CNPJ:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case CPF:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case INSCRICAO_ESTADUAL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            case INSCRIACAO_MUNICIPAL:
                throw new UnsupportedClassVersionError("Ainda Não implementado");

            default:
                throw new AssertionError(pCampo.name());

        }
        //throw new UnsupportedClassVersionError("Ainda Não implementado");

    }

}
