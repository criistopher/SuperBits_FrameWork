/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidadorGoverno;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.UtilSBCoreFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfFabTipoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItem;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSeletorItens;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sfurbino
 */
public enum FabCampos implements ItfFabrica {

    /**
     * Indica o campo que Descreve o Item em uma ou duas palavras. --> Esta
     * anotação é obrigatória em um Item Simples
     */
    AAA_NOME,
    /**
     * Indica um nome completo do Item
     */
    AAA_NOME_LONGO,
    /**
     * Indica um campo que armazena um caminho local para uma imagem Pequena
     */
    IMG_PEQUENA,
    /**
     * Indica um campo que armazena um caminho local para uma imagem média
     */
    IMG_MEDIA,
    /**
     * Indica um campo que armazena um caminho local para uma imagem grande
     */
    IMG_GRANDE,
    /**
     * Indica um Descritivo do Item (uma explicação mais detalhada do que ele
     * signigica), para descritivo de uma ou duas palavras utiliza o AAA_NOME
     */
    AAA_DESCRITIVO,
    /**
     * Indica o ID do Item, pode ser substituído pelo @Id do javax.persistence
     */
    ID,
    /**
     * Indica a latitude para posicionamento geográfico no google maps.
     */
    LATITUDE,
    /**
     * Indica a longitude para posiscionamento geografico no google maps
     */
    Longitude,
    /**
     * Indica um local no mapa
     */
    LCLOGRADOURO,
    /**
     * Indica um campo de CEP
     */
    LCCEP,
    /**
     * Indica um Bairro
     */
    LCBairro,
    /**
     * Indica uma cidade
     */
    LCCidade,
    /**
     * Indica um estado, unidade Federativa (Item que extenda
     * ItfUnidadeFederativa)
     */
    LCUnidadeFederativa,
    /**
     * Indica um campo de senha
     */
    SENHA,
    /**
     * Indica um campo de senha com segurança máxima
     */
    SENHA_SEGURANCA_MAXIMA,
    /**
     * Indica um complemento de endereço
     */
    LCComplemeto,
    /**
     *
     */
    @Deprecated
    LCCampoAberto,
    /**
     * Indica um Código HTML
     */
    HTML,
    /**
     * Indica um valor para utilizar em gráficos
     */
    CHART_VALOR,
    /**
     * Indica um Label para gráficos
     */
    CHART_LABEL,
    /**
     * Indca uma categoria para gráficos
     */
    CHART_CATEGORIA,
    /**
     * Indica a exibição de um calendário
     */
    @Deprecated
    CALENDARIO,
    /**
     * Indica um timeStamp (no caso de Entidades Persistidas, o @Temporal deve
     * ser configurado)
     */
    DATAHORA,
    /**
     * Indica uma data (no caso de Entidades Persistidas, o @Temporal deve ser
     * configurado)
     */
    DATA,
    /**
     * Indca uma Hora (no caso de Entidades Persistidas, o @Temporal deve ser
     * configurado)
     */
    HORA,
    /**
     * Indica um telefone fixo nacional ex:(11 3224-0000)
     */
    TELEFONE_FIXO_NACIONAL,
    /**
     * Indica um telefone fixo internacional ex: (55 11 3224-0000)
     */
    TELEFONE_FIXO_INTERNACIONAL,
    /**
     * Indica um telefone celular, exemplo: 11 97777-7777
     */
    TELEFONE_CELULAR,
    /**
     * Indica um telefone Generico (aceita qualquer numero)
     */
    TELEFONE_GENERICO,
    /**
     * Indica uma moeda brasileira
     */
    MOEDA_REAL,
    /**
     * Indica dollar
     */
    MOEDA_DOLAR,
    /**
     * Indica uma quantidade
     */
    QUANTIDADE,
    /**
     * Indica uma porcentagem
     */
    PERCENTUAL,
    /**
     * Campo que apresenta um conjunto de Opçoes a serem selecionadas
     */
    OBJETO_DE_UMA_LISTA,
    /**
     * Campo que apresenta um conjunto de Opçoes a serem adicionadas em uma
     * lista
     */
    LISTA_OBJETOS,
    /**
     * Texto simples
     */
    TEXTO_SIMPLES,
    /**
     * Verdadeiro ou falso
     */
    VERDADEIRO_FALSO,
    /**
     * Indica uma cor em hexadecimal
     */
    COR,
    /**
     * Indica um e-mail
     */
    EMAIL,
    /**
     * Indica um site
     */
    SITE,
    /**
     * Indica uma url (Site com Http:// ou Https://)
     */
    URL,
    /**
     * Indica um responsável por uma empresa
     */
    RESPONSAVEL,
    /**
     * Indica um nome Completo
     */
    NOME_COMPLETO,
    /**
     * Indica um Cadastro Nacional de Pessoa Juridica
     */
    CNPJ,
    /**
     * Indica um cadastro de pessoa Física
     */
    CPF,
    /**
     * Indica ainscrição estadual de uma empresa
     */
    INSCRICAO_ESTADUAL,
    /**
     * Indica a inscrição municipal de uma empresa
     */
    INSCRIACAO_MUNICIPAL,
    /**
     * Indica um registro de Timestamp com a data da ultima alteração (caso o
     * registro seja persistido deve acompanhar a anotação @Persistence do
     * javax.persistence)
     */
    REG_DATAALTERACAO,
    /**
     * Indica um registro de Timestamp com a data de cadastro do registro
     * registro seja persistido deve acompanhar a anotação @Persistence do
     * javax.persistence)
     */
    REG_DATAINSERCAO,
    /**
     * Indica o usuário que realizou a ultima alteração neste registro
     */
    REG_USUARIO_ALTERACAO,
    /**
     * Indica o usuário que realizou o cadastro deste regsitro
     */
    REG_USUARIO_INSERCAO,
    /**
     * Boolean indicando se a Entidade está ativa ou desativada
     */
    REG_ATIVO_INATIVO,
    /**
     * Indica um código de barras
     */
    CODIGO_DE_BARRAS,
    ICONE,
    SEGURANCA_ATIVA,
    /**
     * Indica um arquivo da entidade
     */
    ARQUIVO_DE_ENTIDADE,
    /**
     * Indica um campo de Localizacao (Que implementa ItfLocalizacao)
     */
    LC_LOCALIZACAO,
    /**
     * Campo especial, que indica uma separação
     */
    CAMPO_SEPARADOR;

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
            case AAA_NOME:
                break;
            case AAA_NOME_LONGO:
                break;
            case AAA_DESCRITIVO:
                break;
            case ID:
                break;
            case LATITUDE:
                sbCampo.setDescricao("Representa a posição geografica de longitude");
                sbCampo.setFraseValidacao("A latitude deve conter um valor numérico");
                break;
            case Longitude:
                sbCampo.setDescricao("Representa a posição geografica de longitude");
                break;
            case LCLOGRADOURO:
                break;
            case LCCEP:
                sbCampo.setMascara("#####-###");
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
                sbCampo.setLabel("Tel");

                sbCampo.setMascara("(##)?####-####");
                break;
            case MOEDA_REAL:
                // implementar: separadores, numero de casas decimais Sigla da moeda
                sbCampo.setSeparadorDecimal(',');
                sbCampo.setSeparadorMilhar('.');
                sbCampo.setNumeroDeCasasDecimais(2);
                break;
            case OBJETO_DE_UMA_LISTA:
                break;
            case LISTA_OBJETOS:

                break;
            case COR: //regex
                sbCampo.setLabel("Cor");
                sbCampo.setValidacaoRegex("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
                break;
            case EMAIL:
                sbCampo.setLabel("E-mail");
                sbCampo.setValorMinimo(6);
                sbCampo.setValidacaoRegex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                break;
            case SITE:
                // REGEX
                sbCampo.setLabel("Site");
                sbCampo.setValidacaoRegex("(www).?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&\\=]*)");
                break;
            case URL:
                // regex
                sbCampo.setLabel("URL");
                sbCampo.setValidacaoRegex("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&\\/=]*)");
                break;
            case RESPONSAVEL:
                //regex de nome completo
                sbCampo.setValorMinimo(8);
                sbCampo.setValidacaoRegex("^[a-z A-Z ÉÚÍÓÁÈÙÌÒÀÕÃÑÊÛÎÔÂËYÜÏÖÄ][a-zéúíóáèùìòàõãñêûîôâëyüïöä]+( [a-z A-Z ÉÚÍÓÁÈÙÌÒÀÕÃÑÊÛÎÔÂËYÜÏÖÄ][a-zéúíóáèùìòàõãñêûîôâëyüïöä]+)+$");
                break;
            case CNPJ:
                // mascara (tipo primitivo numeral)
                sbCampo.setMascara("##.###.###/####-##");
                break;
            case CPF:
                //mascara(tipo primitivo numeral)
                sbCampo.setMascara("###.###.###-##");
                sbCampo.setValorMinimo(12);
                break;
            case INSCRICAO_ESTADUAL:
                // mascara (tipo primitivo numeral)

                break;
            case INSCRIACAO_MUNICIPAL:
                // mascara (tipo primitivo numeral)
                break;

            case LCCampoAberto:
                break;
            case QUANTIDADE:
                // define tipo primitivo numerico
                break;
            case SENHA:
                // tamanho minimo 3
                sbCampo.setValorMinimo(3);
                break;
            case PERCENTUAL:
                break;

            case TELEFONE_FIXO_INTERNACIONAL:
                // MAscara e Regex
                sbCampo.setValidacaoRegex("^((55)\\s?)(\\(([1-9][0-9])\\)\\s?)(((9\\d|[2-9])\\d{3})\\-(\\d{4}))$");
                sbCampo.setMascara("##(##)a####-####");
                break;
            case TELEFONE_CELULAR:
                // MAscara e Regex
                sbCampo.setLabel("Cel.");
                sbCampo.setValidacaoRegex("^(\\(([1-9][0-9])\\)\\s?)(((9|)([6-9])\\d{3})\\-(\\d{4}))$");
                sbCampo.setMascara("(##)#####-####");
                break;
            case REG_DATAALTERACAO:
                // tipo primitivo Data mascara data
                sbCampo.setLabel("Dt. Alteração");
                sbCampo.setValidacaoRegex("^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
                sbCampo.setMascara("##/##/####");
                break;
            case REG_DATAINSERCAO:
                // tipo primitivo Data mascara data
                sbCampo.setLabel("Dt. Inclusão");
                sbCampo.setValidacaoRegex("^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
                sbCampo.setMascara("##/##/####");
                break;
            case REG_USUARIO_ALTERACAO:
                sbCampo.setLabel("Usr. Alteração");
                break;
            case REG_USUARIO_INSERCAO:
                sbCampo.setLabel("Usr. Inserção");
                break;
            case LCUnidadeFederativa:
                sbCampo.setLabel("UF");
                break;
            case MOEDA_DOLAR:
                //implementar: separadores, numero de casas decimais Sigla da moeda
                sbCampo.setSeparadorDecimal('.');
                sbCampo.setSeparadorMilhar(',');
                sbCampo.setNumeroDeCasasDecimais(2);
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
            case TELEFONE_GENERICO:
                sbCampo.setValidacaoRegex("^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$");
                sbCampo.setMascara("*##(##)#####-####");
                break;
            case CODIGO_DE_BARRAS:
                break;
            case DATAHORA:
                break;
            case DATA:
                break;
            case HORA:
                break;
            case REG_ATIVO_INATIVO:
                sbCampo.setLabel("Ativo");
                break;
            case ICONE:
                break;

            case SEGURANCA_ATIVA:
                break;
            case ARQUIVO_DE_ENTIDADE:
                sbCampo.setLabel("Arquivo: ");

                break;
            case LC_LOCALIZACAO:
                break;
            case CAMPO_SEPARADOR:
                break;

            default:
                break;
        }

        return sbCampo;

    }

    public TIPO_ORIGEM_VALOR_CAMPO getTipoOrigemPadrao() {
        switch (this) {
            case AAA_NOME:
            case AAA_NOME_LONGO:
            case AAA_DESCRITIVO:
            case ID:
            case LATITUDE:
            case Longitude:
            case LCLOGRADOURO:
            case LCCEP:
            case SENHA:
            case LCComplemeto:
            case LCCampoAberto:
            case SENHA_SEGURANCA_MAXIMA:
            case CHART_VALOR:
            case CHART_LABEL:
            case HTML:
            case CHART_CATEGORIA:
            case CALENDARIO:
            case DATAHORA:
            case DATA:
            case HORA:
            case TELEFONE_FIXO_NACIONAL:
            case TELEFONE_FIXO_INTERNACIONAL:
            case TELEFONE_GENERICO:
            case TELEFONE_CELULAR:
            case MOEDA_REAL:
            case MOEDA_DOLAR:
            case QUANTIDADE:
            case TEXTO_SIMPLES:
            case VERDADEIRO_FALSO:
            case PERCENTUAL:
            case EMAIL:
            case COR:
            case SITE:
            case URL:
            case RESPONSAVEL:
            case NOME_COMPLETO:
            case CNPJ:
            case CPF:
            case INSCRICAO_ESTADUAL:
            case INSCRIACAO_MUNICIPAL:
            case REG_DATAINSERCAO:
            case REG_USUARIO_INSERCAO:
            case REG_USUARIO_ALTERACAO:
            case REG_DATAALTERACAO:
            case REG_ATIVO_INATIVO:
            case CODIGO_DE_BARRAS:
            case ICONE:
            case SEGURANCA_ATIVA:
            case LC_LOCALIZACAO:
            case CAMPO_SEPARADOR:
                return TIPO_ORIGEM_VALOR_CAMPO.VALOR_LIVRE;
            case LCBairro:
            case LCCidade:
            case LCUnidadeFederativa:
            case OBJETO_DE_UMA_LISTA:
                return TIPO_ORIGEM_VALOR_CAMPO.VALOR_COM_LISTA;

            case LISTA_OBJETOS:

                return TIPO_ORIGEM_VALOR_CAMPO.VALORES_COM_LISTA;
            case IMG_PEQUENA:
            case ARQUIVO_DE_ENTIDADE:
            case IMG_MEDIA:
            case IMG_GRANDE:
                return TIPO_ORIGEM_VALOR_CAMPO.ARQUIVO;

        }
        return TIPO_ORIGEM_VALOR_CAMPO.VALOR_LIVRE;
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
        if (pClasse.getSimpleName().equals(Double.class.getSimpleName())) {
            return FabCampos.PERCENTUAL;
        }
        if (pClasse.getSimpleName().equals(boolean.class.getSimpleName())) {
            return FabCampos.VERDADEIRO_FALSO;
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

            if (anotacaoInfoCampo.Mask() != null) {
                if (!anotacaoInfoCampo.Mask().isEmpty()) {
                    sbCampo.setMascara(anotacaoInfoCampo.Mask());
                }
            }

            if (anotacaoInfoCampo.label().length() > 0) {
                sbCampo.setLabel(anotacaoInfoCampo.label());
            }

            sbCampo.setObrigatorio(anotacaoInfoCampo.obrigatorio());
            anotacaoInfoCampo.valoresAceitos();

            if (!anotacaoInfoCampo.fabricaDeOpcoes().equals(void.class)) {
                try {
                    sbCampo.setListaDeOpcoes(UtilSBCoreFabrica.getListaTodosRegistrosDaFabrica(anotacaoInfoCampo.fabricaDeOpcoes()));
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Impossível listar as opções de registro apartir da classe" + anotacaoInfoCampo.fabricaDeOpcoes().getSimpleName() + " verifique se esta classe extende ItfFabricaDeAções", t);
                }
            }
        }

        Annotation[] outrasAnotacoes = campo.getAnnotations();

        NotNull nulo = campo.getAnnotation(NotNull.class);
        if (nulo != null) {
            sbCampo.setObrigatorio(true);
        }

        sbCampo.setTipoDeclaracao(TIPO_DECLARACAO.SIMPLES);
        for (Annotation anotacao : outrasAnotacoes) {

            if (anotacao.toString().contains("@Calculo")) {
                sbCampo.setTipoDeclaracao(TIPO_DECLARACAO.VALOR_CALCULADO);
            }
            if (anotacao.toString().contains("@Lista")) {
                sbCampo.setTipoDeclaracao(TIPO_DECLARACAO.LISTA_DINIMICA);
            }

            // Verificar outras anotacoes
        }

        return sbCampo;

    }

    public ItfFabTipoComponenteVisual getTipo_input_prime() {
        try {
            // TIPOS DE CAMPO QUE DEVEN USAR INPUT SIMPLES COM MASCARAS E REGEX
            switch (this) {

                case AAA_NOME:
                case AAA_NOME_LONGO:
                case ID:
                case LCLOGRADOURO:
                case LCComplemeto:
                case LCCampoAberto:
                case TEXTO_SIMPLES:
                case SITE:
                case URL:
                case TELEFONE_FIXO_NACIONAL:
                case TELEFONE_FIXO_INTERNACIONAL:
                case TELEFONE_CELULAR:
                case TELEFONE_GENERICO:
                case RESPONSAVEL:
                case NOME_COMPLETO:
                case CNPJ:
                case CPF:
                case INSCRICAO_ESTADUAL:
                case CHART_LABEL:
                case CHART_CATEGORIA:
                case INSCRIACAO_MUNICIPAL:
                case CODIGO_DE_BARRAS:
                case ICONE:
                case CAMPO_SEPARADOR:
                case IMG_PEQUENA:
                case IMG_MEDIA:
                case IMG_GRANDE:
                    return FabCompVisualInputs.TEXTO_COM_FORMATACAO;
                // TIPOS DE CAMPO QUE DEVEN USAR INPUT COM BARRA CONTENDO MINIMO E MAXIMO
                case PERCENTUAL:
                    return FabCompVisualInputs.NUMERO_MINIMO_MAXIMO;
                // TIPOS DE CAMPO ONDE OPÇÕES DE ESCOLHA SERÃO EXIBIDOS
                case OBJETO_DE_UMA_LISTA:
                    return FabCompVisualSeletorItem.COMBO;
                case LISTA_OBJETOS:
                    return FabCompVisualSeletorItens.PICKLIST;
                // TIPOS DE CAMPO VALIDAÇÃO DE SENHA
                case SENHA:
                case SENHA_SEGURANCA_MAXIMA:
                    return FabCompVisualInputs.SENHA;
                // TIPOS DE CAMPO QUE DEVEN USAR INPUT CEP
                case LCCEP:
                    return FabCompVisualInputs.CEP;// TIPOS DE CAMPO QUE DEVEN USAR INPUT SELETOR DE COR
                case COR:
                    return FabCompVisualInputs.COR;
                // TIPOS DE CAMPO QUE DEVEN USAR INPUT DE HTML
                case HTML:
                    return FabCompVisualInputs.HTML;
                // TIPOS DE CAMPO QUE DEVEN USAR INPUT quantidade
                case QUANTIDADE:
                case LATITUDE:
                case CHART_VALOR:
                case Longitude:
                    return FabCompVisualInputs.QUANTIDADE;
                case AAA_DESCRITIVO:
                    return FabCompVisualInputs.TEXTMO_MULTIPLAS_LINHAS;
                case MOEDA_REAL:
                case MOEDA_DOLAR:
                    return FabCompVisualInputs.MOEDA;
                // TIPOS DE CAMPO QUE DEVEN USAR INPUT EMAIL
                case EMAIL:
                    return FabCompVisualInputs.EMAIL;
                // TIPOS DE CAMPO QUE DEVEN USAR INPUT CALENDAR  EXIBINDO APENAS DATA
                case DATA:
                case CALENDARIO:
                    return FabCompVisualInputs.DATA;
                //// TIPOS DE CAMPO QUE DEVEN USAR INPUT CALENDAR  EXIBINDO DATA E HORA
                case REG_DATAALTERACAO:
                case REG_DATAINSERCAO:
                    return FabCompVisualInputs.ENTIDADE_SIMPLES;
                case DATAHORA:
                case HORA:
                    return FabCompVisualInputs.DATA_HORA;

                case VERDADEIRO_FALSO:
                case REG_ATIVO_INATIVO:
                case SEGURANCA_ATIVA:
                    return FabCompVisualInputs.LIGADO_DESLIGADO;

                case LCUnidadeFederativa:
                case REG_USUARIO_ALTERACAO:
                case REG_USUARIO_INSERCAO:
                case ARQUIVO_DE_ENTIDADE:
                case LCBairro:
                case LCCidade:
                case LC_LOCALIZACAO:
                    return FabCompVisualInputs.ENTIDADE_SIMPLES;
                default:
                    throw new AssertionError(this.name());
            }

            //  throw new UnsupportedOperationException("a exibição para o campo do tipo" + this.toString() + "não foi prevista ainda");
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "ATENÇÃO erro determinando tipo de campo para:" + this.toString(), t);
        }
        return null;
    }

    public TIPO_PRIMITIVO getTipoPrimitivo() {

        switch (this) {
            case AAA_NOME:
            case AAA_NOME_LONGO:
            case AAA_DESCRITIVO:
            case LCLOGRADOURO:
            case LCCEP:
            case LCBairro:
            case LCCidade:
            case LCUnidadeFederativa:
            case SENHA:
            case SENHA_SEGURANCA_MAXIMA:
            case LCComplemeto:
            case LCCampoAberto:
            case HTML:
            case CHART_LABEL:
            case CHART_CATEGORIA:
            case TELEFONE_FIXO_NACIONAL:
            case TELEFONE_FIXO_INTERNACIONAL:
            case TELEFONE_CELULAR:
            case TELEFONE_GENERICO:
            case COR:
            case EMAIL:
            case SITE:
            case URL:
            case RESPONSAVEL:
            case TEXTO_SIMPLES:
            case NOME_COMPLETO:
            case CNPJ:
            case CPF:
            case INSCRICAO_ESTADUAL:
            case INSCRIACAO_MUNICIPAL:
            case CAMPO_SEPARADOR:
            case ICONE:
            case ARQUIVO_DE_ENTIDADE:
            case IMG_PEQUENA:
            case IMG_MEDIA:
            case IMG_GRANDE:
                return TIPO_PRIMITIVO.LETRAS;

            case ID:
            case LATITUDE:
            case Longitude:
            case CHART_VALOR:
            case QUANTIDADE:
            case CODIGO_DE_BARRAS:
                return TIPO_PRIMITIVO.INTEIRO;

            case CALENDARIO:
            case DATAHORA:
            case DATA:
            case HORA:
            case REG_DATAALTERACAO:
            case REG_DATAINSERCAO:
                return TIPO_PRIMITIVO.DATAS;

            case MOEDA_REAL:
            case MOEDA_DOLAR:
            case PERCENTUAL:
                return TIPO_PRIMITIVO.DECIMAL;

            case VERDADEIRO_FALSO:
            case REG_ATIVO_INATIVO:
            case SEGURANCA_ATIVA:
                return TIPO_PRIMITIVO.BOOLEAN;

            case REG_USUARIO_ALTERACAO:
            case REG_USUARIO_INSERCAO:
            case LC_LOCALIZACAO:
            case OBJETO_DE_UMA_LISTA:

                return TIPO_PRIMITIVO.ENTIDADE;

            default:
                throw new AssertionError(this.name());

        }

    }

    public ItfResposta validar(Object pValor, ItfResposta pResp) {

        switch (this) {
            case AAA_NOME:
                break;
            case AAA_NOME_LONGO:
                break;
            case IMG_PEQUENA:
                break;
            case IMG_MEDIA:
                break;
            case IMG_GRANDE:
                break;
            case AAA_DESCRITIVO:
                break;
            case ID:
                break;
            case LATITUDE:
                break;
            case Longitude:
                break;
            case LCLOGRADOURO:
                break;
            case LCCEP:
                break;
            case LCBairro:
                break;
            case LCCidade:
                break;
            case LCUnidadeFederativa:
                break;
            case SENHA:
                break;
            case SENHA_SEGURANCA_MAXIMA:
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
            case DATAHORA:
                break;
            case DATA:
                break;
            case HORA:
                break;
            case TELEFONE_FIXO_NACIONAL:
                break;
            case TELEFONE_FIXO_INTERNACIONAL:
                break;
            case TELEFONE_CELULAR:
                break;
            case TELEFONE_GENERICO:
                break;
            case MOEDA_REAL:
                break;
            case MOEDA_DOLAR:
                break;
            case QUANTIDADE:
                break;
            case PERCENTUAL:
                break;
            case OBJETO_DE_UMA_LISTA:
                break;
            case LISTA_OBJETOS:
                break;
            case TEXTO_SIMPLES:
                break;
            case VERDADEIRO_FALSO:
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
            case NOME_COMPLETO:
                break;
            case CNPJ:
                if (!UtilSBCoreValidadorGoverno.validaCNPJ((String) pValor)) {
                    pResp.addErro("Digito Verificador Não confere");
                }
                break;
            case CPF:
                break;
            case INSCRICAO_ESTADUAL:
                break;
            case INSCRIACAO_MUNICIPAL:
                break;
            case REG_DATAALTERACAO:
                break;
            case REG_DATAINSERCAO:
                break;
            case REG_USUARIO_ALTERACAO:
                break;
            case REG_USUARIO_INSERCAO:
                break;
            case REG_ATIVO_INATIVO:
                break;
            case CODIGO_DE_BARRAS:
                break;
            case ICONE:
                break;
            case SEGURANCA_ATIVA:
                break;
            case ARQUIVO_DE_ENTIDADE:
                break;
            case LC_LOCALIZACAO:
                break;
            case CAMPO_SEPARADOR:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return pResp;
    }
}
