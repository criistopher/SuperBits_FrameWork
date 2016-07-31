/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimplesSomenteLeitura;
import java.util.List;

/**
 *
 *
 *
 * TODO: Analizar desacoblar BeanSimples E criar uma Entidade Campo com
 * decorator
 *
 * @author sfurbino
 */
public interface ItfCampo extends ItfBeanSimplesSomenteLeitura {

    public static enum TIPOPRIMITIVO {

        INTEIRO, LETRAS, DATAS, BOOLEAN, DECIMAL, ENTIDADE;

        public String getDeclaracaoJava() {

            switch (this) {
                case INTEIRO:
                    return "int";
                case LETRAS:
                    return "String";
                case DATAS:
                    return "Date";
                case BOOLEAN:
                    return "boolean";
                case DECIMAL:
                    return "double";
                case ENTIDADE:
                    return "entidade";
                default:
                    throw new AssertionError(this.name());
            }
        }
    }

    /**
     *
     * @return enum do Tipo de campo conhecido (em FabCampo)
     */
    public FabCampos getTipoCampo();

    /**
     *
     * @return Retorna o Valor do Campo
     */
    public TIPOPRIMITIVO getTipoValor();

    /**
     *
     * @return Tipo de visualização do campo
     */
    public String getTipoVisualizacao();

    /**
     *
     * @return Label do campo (para formulários de cadastro)
     */
    public String getLabel();

    /**
     *
     * @return Retorna o Label, formatado sem espaço e caracteres especiais
     */
    public String getIdComponente();

    /**
     *
     * @return DEscrição do campo (para formulários de cadastro-> usado como
     * dicas sobre o campo
     */
    public String getDescricao();

    /**
     *
     * @return MAscara do campo (Utiliza regex para criar uma mascara)
     */
    public String getMascara();

    /**
     *
     * @return Valor padrão ..
     */
    public String getValorPadrao();

    /**
     *
     * @return Verdadeiro se o campo for obrigatório e falso caso contrário
     */
    public boolean isObrigatorio();

    /**
     *
     * @return Valor maximo do campo (numero de caracteres para letras, e valor
     * para numeros)
     */
    public long getValorMaximo();

    /**
     *
     * @return Valor minino (numero de caracters para letras, e valor para
     * números)
     */
    public long getValorMinimo();

    /**
     *
     * Retorna uma lista de opções para o caso de ManyToOne
     *
     * @return Lista com opções de seleção
     */
    public List<ItfBeanSimples> getListaDeOpcoes();

    /**
     *
     *
     *
     * @return Campo regex para validação de valores
     */
    public String getValidacaoRegex();

    /**
     *
     * Verifica se o regex é diferente de nulo ou em branco e retorna true caso
     * tenha alguma
     *
     * @return
     */
    public boolean isTemValidacaoRegex();

    /**
     *
     * Verifica se o campo minimo
     *
     * @return
     */
    public boolean isTemValidacaoMinimo();

    /**
     * Verifica se o campo maximo possui valor maior que 0 e retorna true
     *
     * @return
     */
    public boolean isTemValidacaoMaximo();

    /**
     * Verifica se a mascara é nula ou isEmpy e retorna true no caso de possuir
     * alguma mascara
     *
     * @return
     */
    public boolean isTemMascara();

    /**
     * retorna se é um numeral de acordo com o tipo primitivo
     *
     * @return
     */
    public boolean isNumeral();

    /**
     * Retorna se é moeda *
     *
     * @return
     */
    public boolean isMoeda();

    /**
     * Retorna o caracter com separador decimal *
     *
     *
     * @return
     */
    public char getSeparadorDecimal();

    /**
     * REtornar o separador de milhar exemplo 1.000 Separador->'.' *
     *
     *
     * @return
     */
    public char getSeparadorMilhar();

    /**
     *
     * Retorna a quantidade de casas decimais (após a virgula) 10,000 ->3 casas
     * *
     *
     * @return
     */
    public int getNumeroDeCasasDecimais();

    /**
     *
     * @return A mascara do campo substituindo # por 0, e '?' , 'u' e 'l' por a
     *
     */
    public String getMascaraJqueryMode();

    /**
     *
     * @return O Tipo Campo no formato String
     */
    public String getTipoCampoSTR();

    /**
     *
     *
     * @return A frase que vai aparecer caso a informação não seja validada
     */
    public String getFraseValidacao();

}
