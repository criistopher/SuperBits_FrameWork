package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class Campo implements Serializable, ItfCampo {

    private FabCampos tipoCampo;

    private TIPOPRIMITIVO tipoValor;

    private String tipoVisualizacao;

    private String mascara;

    private String valorPadrao;

    private String label;

    private String descricao;

    public int[] seguranca = {1};

    protected boolean obrigatorio;

    private long valorMaximo;

    private long valorMinimo;

    public Campo(FabCampos pTipo) {
        tipoCampo = pTipo;
        tipoVisualizacao = pTipo.toString().toUpperCase();
    }

    public Campo(Campo pCampo) {
        setDescricao(pCampo.getDescricao());
        setLabel(pCampo.getLabel());
        setMascara(pCampo.getMascara());
        setTipoCampo(pCampo.getTipoCampo());
        setObrigatorio(pCampo.isObrigatorio());

    }

    @Override
    public FabCampos getTipoCampo() {
        return tipoCampo;
    }

    public final void setTipoCampo(FabCampos tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    @Override
    public TIPOPRIMITIVO getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(TIPOPRIMITIVO tipoValor) {
        this.tipoValor = tipoValor;
    }

    @Override
    public String getTipoVisualizacao() {
        return tipoVisualizacao;
    }

    public void setTipoVisualizacao(String tipoVisualizacao) {
        this.tipoVisualizacao = tipoVisualizacao;
    }

    @Override
    public String getMascara() {
        return mascara;
    }

    public final void setMascara(String mascara) {
        this.mascara = mascara;
    }

    @Override
    public String getValorPadrao() {
        return valorPadrao;
    }

    public void setValorPadrao(String valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public int[] getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(int[] seguranca) {
        this.seguranca = seguranca;
    }

    @Override
    public long getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(long valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    @Override
    public long getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(long valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    @Override
    public boolean isObrigatorio() {
        return obrigatorio;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public final void setLabel(String pLabel) {
        this.label = pLabel;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    public final void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    @Override
    public String getIdComponente() {
        return UtilSBCoreStrings.makeStrUrlAmigavel(getLabel());
    }

}
