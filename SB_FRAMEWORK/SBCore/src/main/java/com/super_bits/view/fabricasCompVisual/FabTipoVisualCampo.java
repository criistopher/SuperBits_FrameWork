/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

/**
 *
 * @author salvioF
 */
public enum FabTipoVisualCampo implements ItfFABTipoComponenteVisual {

    TEXTO_COM_FORMATACAO,
    TEXTO_SEM_FORMATACAO,
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
            case TEXTO_SEM_FORMATACAO:
                return FabCampos.TEXTO_SIMPLES.toString();

            default:
                throw new AssertionError(this.name());

        }

    }

    public String getXhtml() {
        switch (this) {
            case TEXTO_COM_FORMATACAO:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "inputMascara.xhtml";
            case TEXTMO_MULTIPLAS_LINHAS:
            case NUMERO_MINIMO_MAXIMO:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "minimoEMaximo.xhtml";
            case LISTAGEM:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "seletor.xhtml";
            case SENHA:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "senha.xhtml";
            case CEP:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "cep.xhtml";
            case COR:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "cor.xhtml";
            case HTML:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "html.xhtml";
            case QUANTIDADE:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "quantidade.xhtml";
            case MOEDA:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "moeda.xhtml";
            case EMAIL:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "email.xhtml";
            case DATA:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "data.xhtml";
            case DATA_HORA:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "dataHora.xhtml";
            case LIGADO_DESLIGADO:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "verdadeiroOuFalso.xhtml";
            case ENTIDADE_SIMPLES:
                return ItfFABTipoComponenteVisual.PASTA_INPUTS + "entidadeSimples.xhtml";
            case TEXTO_SEM_FORMATACAO:

            default:
                return ItfFABTipoComponenteVisual.CAMPO_INPUT_SIMPLES;

        }

    }

    public String getIdPadraoComponente() {
        switch (this) {

            case LISTAGEM:
                return "componenteInputDado:inputSB:seletor";

            default:
                return "componenteInputDado";

        }
    }

    @Override
    public String getXhtmlJsf() {
        return getXhtml();
    }

    @Override
    public String getXhtmlAndroid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHtmlWordPress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeComponente() {
        return this.toString();
    }

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.INPUT;
    }

}
