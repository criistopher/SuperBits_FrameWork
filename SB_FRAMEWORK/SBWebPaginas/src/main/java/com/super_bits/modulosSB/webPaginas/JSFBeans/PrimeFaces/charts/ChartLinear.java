package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces.charts;

import com.super_bits.modulosSB.Persistencia.dao.DaoGenerico;
import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.Persistencia.dao.SBNQ.TipoObj;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.charts.ItfBeanSimpleChart;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

public class ChartLinear<T extends ItfBeanSimples> {

    private CartesianChartModel dadosGrafico;
    private String nomeGrafico = "";
    private double minimo = 0;
    private double maximo = 0;
    private Class<?> classe;

    public ChartLinear(String pNome, List<ItfBeanSimpleChart>[] listas,
            Class<? extends ItfBeanSimpleChart> tipo) {
        classe = tipo;
        nomeGrafico = pNome;
        setDadosGrafico(new CartesianChartModel());
        for (List<ItfBeanSimpleChart> lista : listas) {
            adcionaSerie(lista);
        }
    }

    public ChartLinear(String pNome,
            Class<? extends ItfBeanSimpleChart> tipo) {
        classe = tipo;
        nomeGrafico = pNome;
        setDadosGrafico(new CartesianChartModel());
    }

    public void adcionaSerie(List<? extends ItfBeanSimpleChart> listacat) {
        if (listacat != null & listacat.size() > 0) {
            ChartSeries serie = new ChartSeries();
            serie.setLabel(listacat.get(0).getCategoria());
            for (ItfBeanSimpleChart item : listacat) {
                if (item.getValor() < getMinimo()) {
                    setMinimo(item.getValor());
                }
                if (item.getValor() > getMaximo()) {
                    setMaximo(item.getValor());
                }
                serie.set(item.getLabel(), item.getValor());
            }// para cada registro
            getDadosGrafico().addSeries(serie);
        }// se tiver algo na lista
    }

    public void adcionaSeries(List<? extends ItfBeanSimpleChart> plistas[]) {
        for (List<? extends ItfBeanSimpleChart> serie : plistas) {
            adcionaSerie(serie);
        }
    }

    public void adcionaSerie(SBNQ qr) {

        DaoGenerico<T> daoRe = new DaoGenerico<T>(classe);
        List<ItfBeanSimpleChart>[] catgraficoLiner = new ArrayList[1];

        if (qr.getTipolista() == TipoObj.ENTIDADE) {
            catgraficoLiner = new ArrayList[1];
            catgraficoLiner[0] = (List<ItfBeanSimpleChart>) daoRe.achaItemPorSBNQ(qr);
        }
        if (qr.getTipolista() == TipoObj.SBQUERY) {

            if (qr.isMinMedMaximo()) {
                catgraficoLiner = new ArrayList[3];
                catgraficoLiner[0] = (List<ItfBeanSimpleChart>) qr.getSbQuery()
                        .getMinimo();
                catgraficoLiner[1] = (List<ItfBeanSimpleChart>) qr.getSbQuery()
                        .getMedia();
                catgraficoLiner[2] = (List<ItfBeanSimpleChart>) qr.getSbQuery()
                        .getMaximo();
            } else {
                catgraficoLiner = new ArrayList[1];
                catgraficoLiner[0] = (List<ItfBeanSimpleChart>) qr.getSbQuery()
                        .retorno();
            }

        }
        for (List<ItfBeanSimpleChart> serie : catgraficoLiner) {
            adcionaSerie(serie);
        }
    }

    public ChartLinear(String pNome, SBNQ[] dados,
            Class<? extends ItfBeanSimpleChart> tipo) {
        classe = tipo;
        setDadosGrafico(new CartesianChartModel());
        nomeGrafico = pNome;

        for (SBNQ qr : dados) {
            adcionaSerie(qr);
        }
    }

    public CartesianChartModel getDadosGrafico() {
        return dadosGrafico;
    }

    public void setDadosGrafico(CartesianChartModel dadosGrafico) {
        this.dadosGrafico = dadosGrafico;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public String getNomeGrafico() {
        return nomeGrafico;
    }

    public void setNomeGrafico(String nomeGrafico) {
        this.nomeGrafico = nomeGrafico;
    }

}
