/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

/**
 *
 *
 *
 * @author salvioF
 */
public enum FabTipoVisualCampo implements ItfFabTipoComponenteVisual {

    @InfoComponenteVisual(nome = "Texto Com FOrmatacao", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "inputMascara.xhtml")
    TEXTO_COM_FORMATACAO,
    @InfoComponenteVisual(nome = "Texto Sem Formatacao", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "inputSimples.xhtml")
    TEXTO_SEM_FORMATACAO,
    @InfoComponenteVisual(nome = "Texto Grande com Formatacao", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "minimoEMaximo.xhtml")
    TEXTO_GRANDE_COM_FORMATACAO,
    @InfoComponenteVisual(nome = "Texto multiplas Linhas", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "descritivo.xhtml")
    TEXTMO_MULTIPLAS_LINHAS,
    @InfoComponenteVisual(nome = "Valor com mínimo e Maximo", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "minimoEMaximo.xhtml")
    NUMERO_MINIMO_MAXIMO,
    @InfoComponenteVisual(nome = "Lista", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "seletorItens.xhtml")
    LISTA_DE_ITENS,
    @InfoComponenteVisual(nome = "Bem simples a artir de Lista", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "seletor.xhtml")
    LOOKUP_DE_ITEM,
    @InfoComponenteVisual(nome = "Senha", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "senha.xhtml")
    SENHA,
    @InfoComponenteVisual(nome = "Cep", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "cep.xhtml")
    CEP,
    @InfoComponenteVisual(nome = "Cor", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "cor.xhtml")
    COR,
    @InfoComponenteVisual(nome = "HTML", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "html.xhtml")
    HTML,
    @InfoComponenteVisual(nome = "Quantidade", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "quantidade.xhtml")
    QUANTIDADE,
    @InfoComponenteVisual(nome = "Moeda", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "moeda.xhtml")
    MOEDA,
    @InfoComponenteVisual(nome = "Email", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "email.xhtml")
    EMAIL,
    @InfoComponenteVisual(nome = "Data", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "data.xhtml")
    DATA,
    @InfoComponenteVisual(nome = "Data Hora", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "dataHora.xhtml")
    DATA_HORA,
    @InfoComponenteVisual(nome = "Ligado ou Desligado", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "verdadeiroOuFalso.xhtml")
    LIGADO_DESLIGADO,
    @InfoComponenteVisual(nome = "Entidade Simples", xhtmlJSF = FabTipoVisualCampo.PASTA_CAMPOS + "entidadeSimples.xhtml")
    ENTIDADE_SIMPLES;
    public static final String PASTA_CAMPOS = "input/";

    @Deprecated
    public String getStrOldStyle() {
        switch (this) {
            case TEXTO_COM_FORMATACAO:
                return FabCampos.TEXTO_SIMPLES.toString();
            case TEXTMO_MULTIPLAS_LINHAS:
                return FabCampos.AAA_DESCRITIVO.toString();
            case NUMERO_MINIMO_MAXIMO:
                return NUMERO_MINIMO_MAXIMO.toString();
            case LISTA_DE_ITENS:
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
            case TEXTO_GRANDE_COM_FORMATACAO:
                break;
            case LOOKUP_DE_ITEM:
                return FabCampos.LOOKUP.toString();

            default:
                return FabCampos.TEXTO_SIMPLES.toString();

        }
        return FabCampos.TEXTO_SIMPLES.toString();
    }

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.INPUT;
    }

    @Override
    public ComponenteVisualSB getComponente() {
        return UtilSBFabricaComponenteVisual.getComponenteVisual(this);
    }

}
