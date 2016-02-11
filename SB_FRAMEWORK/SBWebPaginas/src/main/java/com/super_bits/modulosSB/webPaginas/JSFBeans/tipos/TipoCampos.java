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
            case TELEFONE_FIXO_NACIONAL:
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
            case LCUnidadeFederativa:
                break;
            case SENHA:
                break;
            case SENHA_SEGURANCA_MAXIMA:
                break;
            case TELEFONE_FIXO_INTERNACIONAL:
                break;
            case TELEFONE_CELULAR:
                break;
            case MOEDA_REAL:
                break;
            case MOEDA_DOLAR:
                break;
            case VERDADEIRO_FALSO:
                break;
            case NOME_COMPLETO:
                break;
            case REG_DATAALTERACAO:
                break;
            case REG_DATAINSERCAO:
                break;
            case REG_USUARIO_ALTERACAL:
                break;
            case REG_USUARIO_INSERCAO:
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

    public String getIMG_PEQUENA() {
        return FabCampos.IMG_PEQUENA.name();
    }

    public String getIMG_MEDIA() {
        return FabCampos.IMG_MEDIA.name();
    }

    public String getIMG_GRANDE() {
        return FabCampos.IMG_GRANDE.name();
    }

    public String getIDENTIFICADOR() {
        return FabCampos.ID.name();
    }

    public String getLAT() {
        return FabCampos.LAT.name();
    }

    public String LONG() {
        return FabCampos.LONG.name();
    }

    public String getLCLOGRADOURO() {
        return FabCampos.LCLOGRADOURO.name();
    }

    public String getLCCEP() {
        return FabCampos.LCCEP.name();
    }

    public String getLCBairro() {
        return FabCampos.LCBairro.name();
    }

    public String getLCCidade() {
        return FabCampos.LCCidade.name();
    }

    public String getTelefoneFixoNacional() {
        return FabCampos.TELEFONE_FIXO_NACIONAL.name();
    }

    public String getTelefoneComplementar() {
        return FabCampos.TELEFONE_CELULAR.name();
    }

    public String getLCComplemeto() {
        return FabCampos.LCComplemeto.name();
    }

    public String getLCCampoAberto() {
        return FabCampos.LCCampoAberto.name();
    }

    public String getHTML() {
        return FabCampos.HTML.toString();
    }

    public String getCHART_VALOR() {
        return FabCampos.CHART_VALOR.toString();
    }

    public String getCHART_LABEL() {
        return FabCampos.CHART_LABEL.toString();
    }

    public String getCHART_CATEGORIA() {
        return FabCampos.CHART_CATEGORIA.toString();
    }

    public String getCALENDARIO() {
        return FabCampos.CALENDARIO.toString();
    }

    public String getTELEFONE_FIXO_NACIONAL() {
        return FabCampos.TELEFONE_FIXO_NACIONAL.toString();
    }

    public String getMOEDAReal() {
        return FabCampos.MOEDA_REAL.name();
    }

    public String getLOOKUP() {
        return FabCampos.LOOKUP.toString();
    }

    public String getCOR() {
        return FabCampos.COR.name();
    }

    public String getEMAIL() {
        return FabCampos.EMAIL.toString();
    }

    public String getSITE() {
        return FabCampos.SITE.toString();
    }

    public String getURL() {
        return FabCampos.URL.toString();
    }

    public String getRESPONSAVEL() {
        return FabCampos.RESPONSAVEL.name();
    }

    public String getCNPJ() {
        return FabCampos.CNPJ.name();
    }

    public String getCPF() {
        return FabCampos.CPF.name();
    }

    public String getINSCRICAO_ESTADUAL() {
        return FabCampos.INSCRICAO_ESTADUAL.name();
    }

    public String getINSCRIACAO_MUNICIPAL() {
        return FabCampos.INSCRIACAO_MUNICIPAL.name();
    }

    public String getAAA_NOME_CURTO() {
        return FabCampos.AAA_NOME_CURTO.name();
    }

    public String getAAA_NOME_LONGO() {
        return FabCampos.AAA_NOME_LONGO.name();
    }

    public String getAAA_DESCRITIVO() {
        return FabCampos.AAA_DESCRITIVO.name();
    }

    public String getQUANTIDADE() {
        return FabCampos.QUANTIDADE.name();
    }

    public String getPERCENTUA() {
        return FabCampos.PERCENTUAL.name();
    }

    public String getSenha() {
        return FabCampos.SENHA.name();
    }

}
