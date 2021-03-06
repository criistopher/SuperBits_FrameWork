package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.chart;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.charts.ItfBeanSimpleChart;

@SuppressWarnings({"serial", "rawtypes"})
public class B_ChartSimples<T> extends EntidadeSimples implements ItfBeanSimpleChart {

    public B_ChartSimples() {
        super();
        adcionaCampoEsperado(new CampoEsperado(FabCampos.CHART_LABEL));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.CHART_VALOR));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.CHART_CATEGORIA));
    }

    public String getLabel() {

        return (String) getValorByTipoCampoEsperado(FabCampos.CHART_LABEL);

    }

    public Double getValor() {

        Double resposta = Double.valueOf(getValorByTipoCampoEsperado(FabCampos.CHART_VALOR).toString());
        return resposta;
    }

    public String getCategoria() {

        return (String) getValorByTipoCampoEsperado(FabCampos.CHART_CATEGORIA);
    }

}
