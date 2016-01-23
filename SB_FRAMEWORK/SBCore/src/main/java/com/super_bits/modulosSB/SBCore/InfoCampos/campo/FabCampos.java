/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sfurbino
 */
public enum FabCampos implements ItfFabrica {

    AAA_NOME_CURTO,
    AAA_NOME_LONGO,
    IMG_PEQUENA,
    NOME_CURTO_COMPLETO,
    IMG_MEDIA,
    IMG_GRANDE,
    AAA_DESCRITIVO,
    ID,
    LAT,
    LONG,
    LCLOGRADOURO,
    LCCEP,
    LCBairro,
    LCCidade,
    SENHA,
    Telefone,
    TelefoneComplementar,
    TelefoneCelularComplementar,
    TelefoneCelular,
    LCComplemeto,
    LCCampoAberto,
    HTML,
    CHART_VALOR,
    CHART_LABEL,
    CHART_CATEGORIA,
    CALENDARIO,
    TELEFONE_FIXO_NACIONAL,
    TELEFONE_FIXO_INTERNACIONAL,
    TELEFONE_CELULAR,
    MOEDA,
    QUANTIDADE,
    PERCENTUAL,
    /**
     * Campo que apresenta um conjunto de Opçoes a serem selecionadas
     */
    LOOKUP,
    /**
     * Campo que apresenta um conjunto de Opçoes a serem adicionadas em uma
     * lista
     */
    LOOKUPMULTIPLO,
    TEXTO_SIMPLES,
    COR,
    EMAIL,
    SITE,
    URL,
    RESPONSAVEL,
    CNPJ,
    CPF,
    INSCRICAO_ESTADUAL,
    INSCRIACAO_MUNICIPAL,
    REG_DATAALTERACAO,
    REG_DATAINSERCAO,
    REG_USUARIO_ALTERACAL,
    REG_USUARIO_INSERCAO;

    @Override
    public Campo getRegistro() {
        Campo sbCampo = new Campo(this);
        switch (this) {
            case TEXTO_SIMPLES:
                break;
            case IMG_PEQUENA:
                break;
            case IMG_MEDIA:
                break;
            case IMG_GRANDE:
                break;
            case AAA_NOME_CURTO:
                break;
            case AAA_NOME_LONGO:
                break;
            case AAA_DESCRITIVO:
                break;
            case ID:
                break;
            case LAT:
                sbCampo.setDescricao("Representa a posição geografica de longitude");
                break;
            case LONG:
                sbCampo.setDescricao("Representa a posição geografica de longitude");
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
                sbCampo.setValorMaximo(10);
                break;
            case LCComplemeto:
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
            case MOEDA:
                break;
            case LOOKUP:
                break;
            case LOOKUPMULTIPLO:

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
            case TelefoneComplementar:
                break;
            case TelefoneCelularComplementar:
                break;
            case TelefoneCelular:
                break;
            case LCCampoAberto:
                break;
            case QUANTIDADE:
                break;
            case SENHA:
                break;
            case PERCENTUAL:
                break;
            case NOME_CURTO_COMPLETO:
                break;
            case TELEFONE_FIXO_INTERNACIONAL:
                break;
            case TELEFONE_CELULAR:
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
                break;
        }

        return sbCampo;

    }

    /**
     *
     * Retorna um tipo de campo padrão de acordo com a classe.
     *
     * @param pClasse
     * @return
     */
    public static FabCampos getTipoPadraoByClasse(Class pClasse) {

        if (pClasse.getSimpleName().equals("Date")) {
            return FabCampos.CALENDARIO;
        }

        if (pClasse.getSimpleName().equals(Integer.class.getSimpleName())) {
            return FabCampos.QUANTIDADE;
        }

        if (pClasse.getSimpleName().equals(Double.class.getSimpleName())) {
            return FabCampos.MOEDA;
        }

        return FabCampos.TEXTO_SIMPLES;
    }

    public static Campo getCampoByAnotacoesSimplesSemPersistencia(Field campo) {

        InfoCampo anotacaoInfoCampo = campo.getAnnotation(InfoCampo.class);
        FabCampos tipoDoCampo;
        Campo sbCampo;
        if (anotacaoInfoCampo != null) {
            tipoDoCampo = anotacaoInfoCampo.tipo();

        } else {
            tipoDoCampo = getTipoPadraoByClasse(campo.getType());

        }

        sbCampo = tipoDoCampo.getRegistro();
        sbCampo.setLabel(campo.getName());
        if (anotacaoInfoCampo != null) {

            sbCampo.setMascara(anotacaoInfoCampo.Mask());
            if (anotacaoInfoCampo.label().length() > 0) {
                sbCampo.setLabel(anotacaoInfoCampo.label());
            }
            sbCampo.setObrigatorio(anotacaoInfoCampo.obrigatorio());
            anotacaoInfoCampo.valoresAceitos();
        }

        Annotation[] outrasAnotacoes = campo.getAnnotations();

        NotNull nulo = campo.getAnnotation(NotNull.class);
        if (nulo != null) {
            sbCampo.setObrigatorio(true);
        }

        for (Annotation anotacao : outrasAnotacoes) {
            // Verificar outras anotacoes
        }

        return sbCampo;

    }
}
