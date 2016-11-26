package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfParametroTela;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroSBCriticoWeb;
import java.io.Serializable;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoParametroURL;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPSiteMapa;

/**
 *
 * @author Salvio
 */
public final class ParametroURL implements ItfParametroTela {

    private Object valor;
    private Object valorPadrao;
    private String slugValorPadrao;
    private String slugValorParametro;
    private String nome;
    private TIPO_URL tipoParametro;
    private Class tipoEntidade;
    private final boolean parametroObrigatorio;

    /**
     *
     * @param pInfo
     * @param pObrigatorio
     *
     */
    public ParametroURL(InfoParametroURL pInfo) {
        setNome(pInfo.nome());
        slugValorPadrao = pInfo.valorPadrao();
        setTipoParametro(pInfo.tipoParametro());
        if (!pInfo.tipoEntidade().equals(Void.class)) {
            setTipoEntidade(pInfo.tipoEntidade());
        }

        parametroObrigatorio = pInfo.obrigatorio();
        if (tipoParametro == TIPO_URL.ENTIDADE && tipoEntidade == null) {

            try {
                throw new ErroSBCriticoWeb("Você precisa especificar o tipo de entidade para este tipo de parametro");
            } catch (ErroSBCriticoWeb e) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Constructor Parametro URL" + pInfo.nome(), e);
            }

        }

    }

    public Class getClasseObjetoValor() {
        try {
            switch (tipoParametro) {
                case TEXTO:
                    return String.class;

                case ENTIDADE:
                    return getTipoEntidade();

                case OBJETO_COM_CONSTRUCTOR:
                    break;
                default:
                    throw new AssertionError(tipoParametro.name());

            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando tipo de classe do parametro" + nome, t);
        }
        return null;
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

    public String getSlugValorPadrao() {

        return slugValorPadrao;
    }

    public String getSlugValorParametro() {

        if (valor == null) {
            return slugValorPadrao;
        } else {
            switch (tipoParametro) {
                case TEXTO:
                    return (String) valor;

                case ENTIDADE:
                    return UtilSBWPSiteMapa.getSlugDoObjeto((ItfBeanSimples) valor);

                case OBJETO_COM_CONSTRUCTOR:
                    break;
                default:
                    throw new AssertionError(tipoParametro.name());

            }
        }

        return slugValorParametro;
    }

}
