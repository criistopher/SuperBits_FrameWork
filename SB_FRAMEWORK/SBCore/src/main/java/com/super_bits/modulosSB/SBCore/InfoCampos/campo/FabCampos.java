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
    LCUnidadeFederativa,
    SENHA,
    SENHA_SEGURANCA_MAXIMA,
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
    MOEDA_REAL,
    MOEDA_DOLAR,
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
    VERDADEIRO_FALSO,
    COR,
    EMAIL,
    SITE,
    URL,
    RESPONSAVEL,
    NOME_COMPLETO,
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
                sbCampo.setMascara("999-9999");
                break;
            case LCBairro:
                break;
            case LCCidade:
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
                // tipo primitivo Data mascara data
                sbCampo.setValidacaoRegex("^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
                sbCampo.setMascara("##/##/####");
                break;
            case TELEFONE_FIXO_NACIONAL:
                break;
            case MOEDA_REAL:
                // implementar: separadores, numero de casas decimais Sigla da moeda

                break;
            case LOOKUP:
                break;
            case LOOKUPMULTIPLO:

                break;
            case COR: //regex
                sbCampo.setValidacaoRegex("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
                break;
            case EMAIL:
                sbCampo.setValidacaoRegex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                break;
            case SITE:
                // REGEX
                break;
            case URL:
                // regex
                sbCampo.setValidacaoRegex("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&\\/=]*)");
                break;
            case RESPONSAVEL:
                //regex de nome completo
                sbCampo.setValidacaoRegex("^[a-z A-Z ÉÚÍÓÁÈÙÌÒÀÕÃÑÊÛÎÔÂËYÜÏÖÄ][a-zéúíóáèùìòàõãñêûîôâëyüïöä]+( [a-z A-Z ÉÚÍÓÁÈÙÌÒÀÕÃÑÊÛÎÔÂËYÜÏÖÄ][a-zéúíóáèùìòàõãñêûîôâëyüïöä]+)+$");
                break;
            case CNPJ:
                // mascara (tipo primitivo numeral)
                sbCampo.setMascara("##.###.###/####-##");
                sbCampo.setTipoValor(ItfCampo.TIPOPRIMITIVO.NUMERO);
                break;
            case CPF:
                //mascara(tipo primitivo numeral)
                sbCampo.setMascara("###-###-###-##");
                sbCampo.setTipoValor(ItfCampo.TIPOPRIMITIVO.NUMERO);
                break;
            case INSCRICAO_ESTADUAL:
                // mascara (tipo primitivo numeral)
                sbCampo.setMascara("###-###-###-##");
                sbCampo.setTipoValor(ItfCampo.TIPOPRIMITIVO.NUMERO);
                break;
            case INSCRIACAO_MUNICIPAL:
                // mascara (tipo primitivo numeral)
                sbCampo.setMascara("###-###-###-##");
                sbCampo.setTipoValor(ItfCampo.TIPOPRIMITIVO.NUMERO);
                break;

            case LCCampoAberto:
                break;
            case QUANTIDADE:
                // define tipo primitivo numerico
                sbCampo.setTipoValor(ItfCampo.TIPOPRIMITIVO.NUMERO);
                break;
            case SENHA:
                // tamanho minimo 3

                break;
            case PERCENTUAL:
                break;

            case TELEFONE_FIXO_INTERNACIONAL:
                // MAscara e Regex
                break;
            case TELEFONE_CELULAR:
                // MAscara e Regex
                break;
            case REG_DATAALTERACAO:
                // tipo primitivo Data mascara data
                sbCampo.setTipoValor(ItfCampo.TIPOPRIMITIVO.NUMERO);
                break;
            case REG_DATAINSERCAO:
                // tipo primitivo Data mascara data
                sbCampo.setTipoValor(ItfCampo.TIPOPRIMITIVO.NUMERO);
                break;
            case REG_USUARIO_ALTERACAL:

                break;
            case REG_USUARIO_INSERCAO:
                break;
            case LCUnidadeFederativa:

                break;
            case MOEDA_DOLAR:
                //implementar: separadores, numero de casas decimais Sigla da moeda
                break;
            case VERDADEIRO_FALSO:
                break;
            case SENHA_SEGURANCA_MAXIMA:
                // minimo 8
                sbCampo.setValorMinimo(8);
                break;
            case NOME_COMPLETO:
                // regex
                sbCampo.setValidacaoRegex("^[a-z A-Z ÉÚÍÓÁÈÙÌÒÀÕÃÑÊÛÎÔÂËYÜÏÖÄ][a-zéúíóáèùìòàõãñêûîôâëyüïöä]+( [a-z A-Z ÉÚÍÓÁÈÙÌÒÀÕÃÑÊÛÎÔÂËYÜÏÖÄ][a-zéúíóáèùìòàõãñêûîôâëyüïöä]+)+$");
                sbCampo.setValorMinimo(8);
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
            return FabCampos.MOEDA_REAL;
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
