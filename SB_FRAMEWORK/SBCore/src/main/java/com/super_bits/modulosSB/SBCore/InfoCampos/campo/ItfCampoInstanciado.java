/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import java.lang.reflect.Field;

/**
 *
 * @author sfurbino
 */
public interface ItfCampoInstanciado extends ItfCampo {

    public Object getParent();

    public Object getValor();

    public void setValor(Object pValor);

    public String getNomeCamponaClasse();

    public Field getCampoReflection();

    public InfoCampo getInfoCampo();

    public boolean validarCampo();

    public boolean isVazio();

    public boolean isUmCampoNaoInstanciado();

    public String getLabelSlug();

    public int getIndiceValorLista();

    public void setIndiceValorLista(int pIndice);

    public String getNomeUnicoParaIDHtml();

    public String getXhtmlInput();

}
