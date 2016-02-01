/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import java.util.List;

/**
 *
 *
 *
 *
 * @author sfurbino
 */
public interface ItfCampo {

    public static enum TIPOPRIMITIVO {

        NUMERO, LETRAS, DATAS
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
     * @return DEscrição do campo (para formulários de cadastro-> usado como dicas sobre o campo
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
     * @return Valor maximo do campo (numero de caracteres para letras, e valor para numeros)
     */
    public long getValorMaximo();

    /**
     *
     * @return Valor minino (numero de caracters para letras, e valor para números)
     */
    public long getValorMinimo();
    
    /**
     *
     * Retorna uma lista de opções para o caso de ManyToOne
     * 
     * @return Lista com opções de seleção
     */
    public List<ItfBeanSimples> getListaDeOpcoes();

}
