/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 *
 *
 *
 *
 *
 * @author sfurbino
 */
@ApplicationScoped
public class TipoCampos extends ConstantesWeb {

    public TipoCampos() {
        super(FabCampos.class);
    }

    @Override
    public String getPadrao() {

        FabCampos campopadrao = FabCampos.TEXTO_SIMPLES;

        switch (campopadrao) {
            case IMG_PEQUENA:
                break;
            case IMG_MEDIA:
                break;
            case IMG_GRANDE:
                break;

            case ID:
                break;
            case LAT:
                break;
            case LONG:
                break;
            case LCLOGRADOURO:
                break;
            case LCCEP:
                break;
            case LCBairro:
                break;
            case LCCidade:
                break;
            case Telefone:
                break;
            case TelefoneComplementar:
                break;
            case TelefoneCelularComplementar:
                break;
            case TelefoneCelular:
                break;
            case LCComplemeto:
                break;
            case LCCampoAberto:
                break;
            case HTML:
                break;
            case CHART_VALOR:
                break;
            case CHART_LABEL:
                break;
            case CHART_CATEGORIA:
                break;
            case CALENDARIO:
                break;
            case TELEFONE:
                break;
            case MOEDA:
                break;
            case LOOKUP:
                break;
            case LOOKUPMULTIPLO:
                break;
            case TEXTO_SIMPLES:
                break;
            case COR:
                break;
            case EMAIL:
                break;
            case SITE:
                break;
            case URL:
                break;
            case RESPONSAVEL:
                break;
            case CNPJ:
                break;
            case CPF:
                break;
            case INSCRICAO_ESTADUAL:
                break;
            case INSCRIACAO_MUNICIPAL:
                break;
            case AAA_NOME_CURTO:
                break;
            case AAA_NOME_LONGO:
                break;
            case AAA_DESCRITIVO:
                break;
            case QUANTIDADE:
                break;
            case PERCENTUAL:
                break;
            default:
                throw new AssertionError(campopadrao.name());

        }
        return campopadrao.toString();

    }

    public String getNomeCurto() {
        return FabCampos.AAA_NOME_CURTO.toString();
    }

    public String getNomeLongo() {
        return FabCampos.AAA_NOME_CURTO.toString();
    }

    public String getLC_CEP() {
        return FabCampos.AAA_DESCRITIVO.toString();
    }

    public String getID() {
        return FabCampos.ID.toString();
    }

    public String getLOOKUPMULTIPLO() {
        return FabCampos.LOOKUPMULTIPLO.toString();
    }

    public String getTEXTO_SIMPLES() {
        return FabCampos.TEXTO_SIMPLES.toString();
    }

    public String getNOME_CURTO() {
        return FabCampos.AAA_NOME_CURTO.toString();
    }

    public String getNOME_CURTO_LONGO() {
        return FabCampos.AAA_NOME_LONGO.toString();
    }

    public String getDescritivo() {
        return FabCampos.AAA_DESCRITIVO.toString();
    }

    public String getDataHora() {
        return FabCampos.CALENDARIO.toString();
    }

}
