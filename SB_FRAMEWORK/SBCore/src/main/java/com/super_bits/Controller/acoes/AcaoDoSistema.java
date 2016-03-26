/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author desenvolvedor
 */
@MappedSuperclass
public class AcaoDoSistema extends ItemSimples implements ItfAcaoDoSistema {

    private final FabTipoAcaoSistema tipoAcao;
    protected FabTipoAcaoSistemaGenerica acaoGenerica;
    protected String icone;

    public void copiarDadosDaAcao(ItfAcaoDoSistema pAcaoOriginal) {

    }

    public boolean isUmaAcaoGenerica() {
        return acaoGenerica != null;
    }

    public AcaoDoSistema(FabTipoAcaoSistema pTipoAcao) {
        tipoAcao = pTipoAcao;
    }

    public FabTipoAcaoSistema getTipoAcao() {
        return tipoAcao;
    }

    @Override
    public String getNomeAcao() {
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME);
    }

    @Override
    public String getIconeAcao() {
        return (String) getValorByTipoCampoEsperado(FabCampos.ICONE);
    }

    @Override
    public String getCor() {
        return (String) getValorByTipoCampoEsperado(FabCampos.COR);
    }

    @Override
    public String getDescricao() {
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_DESCRITIVO);
    }

    @Override
    public boolean isPrecisaPermissao() {
        return (boolean) getValorByTipoCampoEsperado(FabCampos.SEGURANCA_ATIVA);
    }

    @Override
    public void setId(int pId) {
        setValorByTipoCampoEsperado(FabCampos.ID, pId);
    }

    @Override
    public void setIcone(String pIcone) {
        setValorByTipoCampoEsperado(FabCampos.ICONE, pIcone);
    }

    @Override
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo) {
        setValorByTipoCampoEsperado(FabCampos.LCCEP, this);
    }

    @Override
    public boolean isConfigurado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeUnico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeEnumOriginal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FabTipoAcaoSistemaGenerica getTipoAcaoSistema() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
