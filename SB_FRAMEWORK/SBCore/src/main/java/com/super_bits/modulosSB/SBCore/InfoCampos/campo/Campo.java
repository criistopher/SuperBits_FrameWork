package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.io.Serializable;
import java.util.List;

public class Campo implements Serializable, ItfCampo {

    private FabCampos tipoCampo;

    private TIPOPRIMITIVO tipoValor;

    private String tipoVisualizacao;

    private String mascara;

    private String valorPadrao;

    private String label;

    private String descricao;

    public int[] seguranca = {1};

    public List<ItfBeanSimples> listaDeOpcoes;

    protected boolean obrigatorio;

    private long valorMaximo;

    private long valorMinimo;

    private String validacaoRegex;

    private char separadorDeciamal, separadorMilhar;

    private int numCasasDecimais;

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
        setListaDeOpcoes(pCampo.getListaDeOpcoes());

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

    @Override
    public List<ItfBeanSimples> getListaDeOpcoes() {
        return listaDeOpcoes;
    }

    public void setListaDeOpcoes(List<ItfBeanSimples> pLista) {
        listaDeOpcoes = pLista;
    }

    @Override
    public String getValidacaoRegex() {
        return validacaoRegex;
    }

    public void setValidacaoRegex(String validacaoRegex) {
        this.validacaoRegex = validacaoRegex;
    }

    @Override
    public boolean isTemValidacaoRegex() {
        if (validacaoRegex == null) {
            return false;
        }
        if (validacaoRegex.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isTemValidacaoMinimo() {

        if (valorMinimo <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        if (valorMaximo <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isTemMascara() {
        if (mascara == null) {
            return false;
        }
        if (mascara.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isNumeral() {
        if (tipoValor != TIPOPRIMITIVO.NUMERO) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isMoeda() {
        if (tipoCampo != FabCampos.MOEDA_REAL && tipoCampo != FabCampos.MOEDA_DOLAR) {
            return false;
        }
        return true;
    }

    @Override
    public char getSeparadorDecimal() {
        return this.separadorDeciamal;
    }

    public void setSeparadorDecimal(char pSeparadorDecimal) {
        this.separadorDeciamal = pSeparadorDecimal;
    }

    @Override
    public char getSeparadorMilhar() {
        return separadorMilhar;
    }

    public void setSeparadorMilhar(char pSeparadorMilhar) {
        this.separadorMilhar = pSeparadorMilhar;
    }

    @Override
    public int getNumeroDeCasasDecimais() {
        return numCasasDecimais;
    }

    public void setNumeroDeCasasDecimais(int pNumeroDeCasasDecimais) {
        this.numCasasDecimais = pNumeroDeCasasDecimais;
    }

}
