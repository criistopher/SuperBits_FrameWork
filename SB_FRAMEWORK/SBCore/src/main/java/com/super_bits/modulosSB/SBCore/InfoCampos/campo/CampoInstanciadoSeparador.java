/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.TipoFonteUpload;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 *
 *
 *
 * @author desenvolvedor
 */
public class CampoInstanciadoSeparador implements ItfCampoInstanciado {

    private String nomeDoSeparador;

    public CampoInstanciadoSeparador(String pnome) {
        nomeDoSeparador = pnome;
    }

    @Override
    public Object getParent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValor(Object pValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeCamponaClasse() {
        return nomeDoSeparador;
    }

    @Override
    public Field getCampoReflection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InfoCampo getInfoCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validarCampo() {
        return true;
    }

    @Override
    public boolean isVazio() {
        return false;
    }

    @Override
    public FabCampos getTipoCampo() {
        return FabCampos.CAMPO_SEPARADOR;
    }

    @Override
    public TIPOPRIMITIVO getTipoValor() {
        return TIPOPRIMITIVO.LETRAS;
    }

    @Override
    public String getTipoVisualizacao() {
        return "linha";
    }

    @Override
    public String getLabel() {
        return nomeDoSeparador;
    }

    @Override
    public String getIdComponente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescricao() {
        return "Separador para montagem de formularios";
    }

    @Override
    public String getMascara() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValorPadrao() {
        return nomeDoSeparador;
    }

    @Override
    public boolean isObrigatorio() {
        return false;
    }

    @Override
    public long getValorMaximo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getValorMinimo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItfBeanSimples> getListaDeOpcoes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValidacaoRegex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTemValidacaoRegex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTemValidacaoMinimo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTemMascara() {
        return false;
    }

    @Override
    public boolean isNumeral() {
        return false;
    }

    @Override
    public boolean isMoeda() {
        return false;
    }

    @Override
    public char getSeparadorDecimal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char getSeparadorMilhar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumeroDeCasasDecimais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMascaraJqueryMode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTipoCampoSTR() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFraseValidacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeCurto() {
        return nomeDoSeparador;
    }

    @Override
    public String getNome() {
        return nomeDoSeparador;
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso) {

    }

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void configIDPeloNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomeDoObjeto() {
        return nomeDoSeparador;
    }

}
