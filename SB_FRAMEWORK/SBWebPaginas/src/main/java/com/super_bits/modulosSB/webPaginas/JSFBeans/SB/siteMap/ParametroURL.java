package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfParametroTela;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroSBCriticoWeb;
import java.io.Serializable;

/**
 *
 * @author Salvio
 */
public class ParametroURL implements ItfParametroTela {

    private Object valor;
    private Object valorPadrao;
    private String nome;
    private TIPOURL tipoParametro;
    private Class tipoEntidade;

    /**
     *
     * @param pNome Nome do Parametro
     * @param pValorPadrao Valor padrão
     * @param ptipo TIPO: Entidade, STring simples e Outros
     * @param pEntidade Classe que representa a Entidade
     */
    public ParametroURL(String pNome, Object pValorPadrao, TIPOURL ptipo, Class pEntidade) {
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
    public ParametroURL(String pNome, Object pValorPadrao, TIPOURL ptipo) {
        setNome(pNome);
        setValor(pValorPadrao);
        setValorPadrao(pValorPadrao);
        setTipoParametro(ptipo);
        if (ptipo == TIPOURL.ENTIDADE) {

            try {
                throw new ErroSBCriticoWeb("Criação de parametro de URL do tipo entidade sem especificar a Classe");
            } catch (ErroSBCriticoWeb e) {
                FabErro.PARA_TUDO.paraSistema("Constructor Parametro URL" + pNome, e);
            }

        }

    }

    @Override
    public Object getValor() {
        return valor;
    }

    @Override
    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public Object getValorPadrao() {
        return valorPadrao;
    }

    @Override
    public void setValorPadrao(Object valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public TIPOURL getTipoParametro() {
        return tipoParametro;
    }

    @Override
    public void setTipoParametro(TIPOURL tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    @Override
    public Class getTipoEntidade() {
        return tipoEntidade;
    }

    @Override
    public void setTipoEntidade(Class<?> tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }

}
