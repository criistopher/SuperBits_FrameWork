package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroSBCriticoWeb;
import java.io.Serializable;

/**
 *
 * @author Salvio
 */
public class ParametroURL implements Serializable {

    public static enum tipoPrURL {

        TEXTO, ENTIDADE, OBJETO_COM_CONSTRUCTOR
    }
    private Object valor;
    private Object valorPadrao;
    private String nome;
    private tipoPrURL tipoParametro;
    private Class tipoEntidade;

    /**
     *
     * @param pNome Nome do Parametro
     * @param pValorPadrao Valor padrão
     * @param ptipo TIPO: Entidade, STring simples e Outros
     * @param pEntidade Classe que representa a Entidade
     */
    public ParametroURL(String pNome, Object pValorPadrao, tipoPrURL ptipo, Class pEntidade) {
        setNome(pNome);
        setValor(pValorPadrao);
        setValorPadrao(pValorPadrao);
        setTipoParametro(ptipo);
        setTipoEntidade(pEntidade);
    }

    /**
     *
     * @param pNome nome do parametro
     * @param pValorPadrao Valor Padrão
     * @param ptipo Tipo de parametro (string ou Entidade)
     */
    public ParametroURL(String pNome, Object pValorPadrao, tipoPrURL ptipo) {
        setNome(pNome);
        setValor(pValorPadrao);
        setValorPadrao(pValorPadrao);
        setTipoParametro(ptipo);
        if (ptipo == tipoPrURL.ENTIDADE) {

            try {
                throw new ErroSBCriticoWeb("Criação de parametro de URL do tipo entidade sem especificar a Classe");
            } catch (ErroSBCriticoWeb e) {
                FabErro.PARA_TUDO.paraSistema("Constructor Parametro URL" + pNome, e);
            }

        }

    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Object getValorPadrao() {
        return valorPadrao;
    }

    public void setValorPadrao(Object valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public tipoPrURL getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(tipoPrURL tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public Class getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(Class<?> tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }

}
