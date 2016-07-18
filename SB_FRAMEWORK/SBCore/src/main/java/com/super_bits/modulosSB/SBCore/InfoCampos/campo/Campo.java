package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.io.Serializable;
import java.util.List;

public class Campo implements Serializable, ItfCampo {

    @InfoCampo(tipo = FabCampos.ID)
    private FabCampos tipoCampo;

    private TIPOPRIMITIVO tipoValor;

    private String tipoVisualizacao;

    private String mascara;

    private String valorPadrao;

    @InfoCampo(tipo = FabCampos.AAA_NOME)
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

    private String fraseValidacao;

    public Campo(FabCampos pTipo) {

        tipoCampo = pTipo;
        tipoVisualizacao = pTipo.toString().toUpperCase();

    }

    public Campo(Campo pCampo) {
        if (pCampo == null) {
            throw new UnsupportedOperationException("Era esperado um Atributo campo no constructor");
        }
        setDescricao(pCampo.getDescricao());
        setLabel(pCampo.getLabel());
        setMascara(pCampo.getMascara());
        setTipoCampo(pCampo.getTipoCampo());
        setObrigatorio(pCampo.isObrigatorio());
        setListaDeOpcoes(pCampo.getListaDeOpcoes());
        setValidacaoRegex(pCampo.getValidacaoRegex());
        setFraseValidacao(pCampo.getFraseValidacao());

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
        return tipoCampo.getTipoPrimitivo();
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

    public final void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    @Override
    public final String getIdComponente() {
        return UtilSBCoreStrings.makeStrUrlAmigavel(getLabel());
    }

    @Override
    public final List<ItfBeanSimples> getListaDeOpcoes() {
        return listaDeOpcoes;
    }

    public final void setListaDeOpcoes(List<ItfBeanSimples> pLista) {
        listaDeOpcoes = pLista;
    }

    @Override
    public final String getValidacaoRegex() {
        return validacaoRegex;
    }

    public final void setValidacaoRegex(String validacaoRegex) {
        this.validacaoRegex = validacaoRegex;
    }

    @Override
    public boolean isTemValidacaoRegex() {
        if (validacaoRegex == null) {
            return false;
        }
        return !validacaoRegex.isEmpty();
    }

    @Override
    public boolean isTemValidacaoMinimo() {

        return valorMinimo > 0;
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        return valorMaximo > 0;
    }

    @Override
    public boolean isTemMascara() {
        if (mascara == null) {
            return false;
        }
        return !mascara.isEmpty();
    }

    @Override
    public boolean isNumeral() {
        return tipoValor == TIPOPRIMITIVO.INTEIRO;
    }

    @Override
    public boolean isMoeda() {
        return !(tipoCampo != FabCampos.MOEDA_REAL && tipoCampo != FabCampos.MOEDA_DOLAR);
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

    @Override
    public String getMascaraJqueryMode() {
        return UtilSBCoreStrings.getMascaraJavaMaskParaJQueryMask(mascara);
    }

    @Override
    public String getTipoCampoSTR() {
        return tipoCampo.toString();
    }

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeCurto() {
        return tipoCampo.toString();
    }

    @Override
    public int getId() {
        return tipoCampo.toString().hashCode();
    }

    @Override
    public String getNome() {
        return label;
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configIDPeloNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeDoObjeto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public final String getFraseValidacao() {
        return fraseValidacao;
    }

    public final void setFraseValidacao(String fraseValidacao) {
        this.fraseValidacao = fraseValidacao;
    }

    public String getStrTipoInput() {
        return tipoCampo.getStrTipoInputCampo();
    }

}
