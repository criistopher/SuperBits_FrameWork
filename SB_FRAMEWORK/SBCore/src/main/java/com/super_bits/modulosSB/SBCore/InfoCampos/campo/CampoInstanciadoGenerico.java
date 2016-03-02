/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import java.lang.reflect.Field;

/**
 *
 * @author sfurbino
 */
public abstract class CampoInstanciadoGenerico extends Campo implements ItfCampoInstanciado {

    protected final Field campoReflection;
    protected final InfoCampo infoCampo;

    public CampoInstanciadoGenerico(Campo pcampo, Field pCampoReflection) {
        super(pcampo);
        campoReflection = pCampoReflection;
        infoCampo = pCampoReflection.getAnnotation(InfoCampo.class);

    }

    @Override

    public String getNomeCamponaClasse() {
        return campoReflection.getName();
    }

    @Override
    public Field getCampoReflection() {
        return campoReflection;
    }

    @Override
    public InfoCampo getInfoCampo() {
        return infoCampo;
    }

    @Override
    public abstract boolean validarCampo();

    @Override
    public boolean isVazio() {
        if (getValor() == null) {
            return false;
        } else {
            return !getValor().toString().isEmpty();
        }
    }

    @Override
    public ItfBeanSimples getBeanSimplesPorNomeCampo(String pNomeCampo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
