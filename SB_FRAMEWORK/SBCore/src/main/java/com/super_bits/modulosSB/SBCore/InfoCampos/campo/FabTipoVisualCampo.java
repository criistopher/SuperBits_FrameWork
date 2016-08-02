/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

/**
 *
 * @author salvioF
 */
public enum FabTipoVisualCampo {

    TEXTO_COM_FORMATACAO,
    TEXTMO_MULTIPLAS_LINHAS,
    NUMERO_MINIMO_MAXIMO,
    LISTAGEM,
    SENHA,
    CEP,
    COR,
    HTML,
    QUANTIDADE,
    MOEDA,
    EMAIL,
    DATA,
    DATA_HORA,
    LIGADO_DESLIGADO,
    ENTIDADE_SIMPLES;

    public static final String PASTA_INPUTS = "/resources/SBComp/tagLib/tags/com/sb/";
    public static final String CAMPO_INPUT_COM_MASCARA = PASTA_INPUTS + "inputSimples.xhtml";

    @Deprecated
    public String getStrOldStyle() {
        switch (this) {
            case TEXTO_COM_FORMATACAO:
                return FabCampos.TEXTO_SIMPLES.toString();
            case TEXTMO_MULTIPLAS_LINHAS:
                return FabCampos.AAA_DESCRITIVO.toString();
            case NUMERO_MINIMO_MAXIMO:
                return NUMERO_MINIMO_MAXIMO.toString();
            case LISTAGEM:
                return FabCampos.LOOKUP.toString();
            case SENHA:
                return SENHA.toString();
            case CEP:
                return FabCampos.LCCEP.toString();
            case COR:
                return FabCampos.COR.toString();
            case HTML:
                return HTML.toString();
            case QUANTIDADE:
                return FabCampos.QUANTIDADE.toString();
            case MOEDA:
                return FabCampos.MOEDA_REAL.toString();
            case EMAIL:
                return FabCampos.EMAIL.toString();
            case DATA:
                return FabCampos.DATA.toString();
            case DATA_HORA:
                return FabCampos.DATAHORA.toString();
            case LIGADO_DESLIGADO:
                return FabCampos.VERDADEIRO_FALSO.toString();
            case ENTIDADE_SIMPLES:
                return FabCampos.LOOKUP.toString();

            default:
                throw new AssertionError(this.name());

        }

    }

    public String getXhtml() {
        switch (this) {
            case TEXTO_COM_FORMATACAO:
                break;
            case TEXTMO_MULTIPLAS_LINHAS:
                break;
            case NUMERO_MINIMO_MAXIMO:
                break;
            case LISTAGEM:
                break;
            case SENHA:
                break;
            case CEP:
                break;
            case COR:
                return PASTA_INPUTS + "cor.xhtml";
            case HTML:
                break;
            case QUANTIDADE:
                break;
            case MOEDA:
                break;
            case EMAIL:
                break;
            case DATA:
                break;
            case DATA_HORA:
                break;
            case LIGADO_DESLIGADO:
                break;
            case ENTIDADE_SIMPLES:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return CAMPO_INPUT_COM_MASCARA;
    }

    public String getIdPadraoComponente() {
        switch (this) {

            case LISTAGEM:
                return "componenteInputDado:inputSB:seletor";

            default:
                return "componenteInputDado";

        }
    }
}
