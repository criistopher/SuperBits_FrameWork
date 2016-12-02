/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_DECLARACAO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_ORIGEM_VALOR_CAMPO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class EstruturaCampo extends ItemSimples implements ItfCampo {

    private final Campo campoVinculado;
    @InfoCampo(tipo = FabCampos.ID)
    private final int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nomeDeclarado;
    private EstruturaDeEntidade estruturaPai;

    public EstruturaCampo(Campo pCampo, EstruturaDeEntidade pEstrutura) {
        campoVinculado = pCampo;
        estruturaPai = pEstrutura;

        id = (estruturaPai.getNome() + pCampo.getNome()).hashCode();
    }

    public EstruturaDeEntidade getEstruturaPai() {
        return estruturaPai;
    }

    public void setEstruturaPai(EstruturaDeEntidade estruturaPai) {
        this.estruturaPai = estruturaPai;
    }

    public String getNomeDeclarado() {
        return nomeDeclarado;
    }

    public void setNomeDeclarado(String nomeDeclarado) {
        this.nomeDeclarado = nomeDeclarado;
    }

    @Override
    public FabCampos getTipoCampo() {
        return campoVinculado.getTipoCampo();
    }

    @Override
    public TIPO_PRIMITIVO getTipoPrimitivoDoValor() {
        return campoVinculado.getTipoPrimitivoDoValor();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getOrigemValor() {
        return campoVinculado.getOrigemValor();
    }

    @Override
    public TIPO_DECLARACAO getTipoDeclaracao() {
        return campoVinculado.getTipoDeclaracao();
    }

    @Override
    public String getTipoVisualizacao() {
        return campoVinculado.getTipoVisualizacao();
    }

    @Override
    public String getLabel() {
        return campoVinculado.getLabel();
    }

    @Override
    public String getIdComponente() {
        return campoVinculado.getIdComponente();
    }

    @Override
    public String getDescricao() {
        return campoVinculado.getDescricao();
    }

    @Override
    public String getMascara() {
        return campoVinculado.getMascara();
    }

    @Override
    public String getValorPadrao() {
        return campoVinculado.getValorPadrao();
    }

    @Override
    public boolean isObrigatorio() {
        return campoVinculado.isObrigatorio();
    }

    @Override
    public long getValorMaximo() {
        return campoVinculado.getValorMaximo();
    }

    @Override
    public long getValorMinimo() {
        return campoVinculado.getValorMinimo();
    }

    @Override
    public List<ItfBeanSimples> getListaDeOpcoes() {
        return campoVinculado.getListaDeOpcoes();
    }

    @Override
    public String getValidacaoRegex() {
        return campoVinculado.getValidacaoRegex();
    }

    @Override
    public boolean isTemValidacaoRegex() {
        return campoVinculado.isTemValidacaoRegex();
    }

    @Override
    public boolean isTemValidacaoMinimo() {
        return campoVinculado.isTemValidacaoMinimo();
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        return campoVinculado.isTemValidacaoMaximo();
    }

    @Override
    public boolean isTemMascara() {
        return campoVinculado.isTemMascara();
    }

    @Override
    public boolean isNumeral() {
        return campoVinculado.isNumeral();
    }

    @Override
    public boolean isMoeda() {
        return campoVinculado.isMoeda();
    }

    @Override
    public char getSeparadorDecimal() {
        return campoVinculado.getSeparadorDecimal();
    }

    @Override
    public char getSeparadorMilhar() {
        return campoVinculado.getSeparadorMilhar();
    }

    @Override
    public int getNumeroDeCasasDecimais() {
        return campoVinculado.getNumeroDeCasasDecimais();
    }

    @Override
    public String getMascaraJqueryMode() {
        return campoVinculado.getMascaraJqueryMode();
    }

    @Override
    public String getTipoCampoSTR() {
        return campoVinculado.getTipoCampoSTR();
    }

    @Override
    public String getFraseValidacao() {
        return campoVinculado.getFraseValidacao();
    }

    @Override
    public boolean isUmValorLivre() {
        return campoVinculado.isUmValorLivre();
    }

    @Override
    public boolean iUmValorComLista() {
        return campoVinculado.isUmValorMultiploComLista();
    }

    @Override
    public boolean isUmValorMultiploLivre() {
        return campoVinculado.isUmValorMultiploLivre();
    }

    @Override
    public boolean isUmValorMultiploComLista() {
        return campoVinculado.isUmValorMultiploComLista();
    }

    public void setLabel(String pLabel) {
        campoVinculado.setLabel(pLabel);
    }

    public void setDescricao(String pDescricao) {
        campoVinculado.setDescricao(pDescricao);
    }

}
