package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroTela;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroSBCriticoWeb;
import java.io.Serializable;

/**
 *
 * @author Salvio
 */
public final class ParametroURL implements ItfParametroTela {

    private Object valor;
    private Object valorPadrao;
    private String nome;
    private TIPO_URL tipoParametro;
    private Class tipoEntidade;
    private final boolean parametroObrigatorio;

    /**
     *
     * @param pNome Nome do Parametro
     * @param pValorPadrao Valor padrão
     * @param ptipo TIPO: Entidade, STring simples e Outros
     * @param pEntidade Classe que representa a Entidade
     */
    public ParametroURL(boolean pObrigatorio, String pNome, Object pValorPadrao, TIPO_URL ptipo, Class pEntidade) {
        setNome(pNome);
        setValor(pValorPadrao);
        setValorPadrao(pValorPadrao);
        setTipoParametro(ptipo);
        setTipoEntidade(pEntidade);
        parametroObrigatorio = pObrigatorio;
    }

    /**
     *
     * @param pObrigatorio
     * @param pNome nome do parametro
     * @param pValorPadrao Valor Padrão
     * @param ptipo Tipo de parametro (string ou Entidade)
     */
    public ParametroURL(boolean pObrigatorio, String pNome, Object pValorPadrao, TIPO_URL ptipo) {
        setNome(pNome);
        setValor(pValorPadrao);
        setValorPadrao(pValorPadrao);
        setTipoParametro(ptipo);
        parametroObrigatorio = pObrigatorio;
        if (ptipo == TIPO_URL.ENTIDADE) {

            try {
                throw new ErroSBCriticoWeb("Criação de parametro de URL do tipo entidade sem especificar a Classe, Utilize o outro construtor deste objeto");
            } catch (ErroSBCriticoWeb e) {
                FabErro.SOLICITAR_REPARO.paraSistema("Constructor Parametro URL" + pNome, e);
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
    public TIPO_URL getTipoParametro() {
        return tipoParametro;
    }

    @Override
    public void setTipoParametro(TIPO_URL tipoParametro) {
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

    public boolean isParametroObrigatorio() {
        return parametroObrigatorio;
    }

}
