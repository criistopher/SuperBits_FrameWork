/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.view.fabricasCompVisual.FabTipoVisualCampo;
import com.super_bits.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.view.fabricasCompVisual.ItfFabTipoComponenteVisual;
import java.lang.reflect.Field;

/**
 *
 * @author sfurbino
 */
public abstract class CampoInstanciadoGenerico extends Campo implements ItfCampoInstanciado {

    protected final Field campoReflection;
    protected final InfoCampo infoCampo;
    protected int indiceValorLista = -1;

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
    public boolean isUmCampoNaoInstanciado() {

        return getLabel().equals(CampoNaoImplementado.LABEL_NAO_IMPLEMENTADO);

    }

    @Override
    public String getLabelSlug() {
        return UtilSBCoreStrings.makeStrUrlAmigavel(getLabel());
    }

    @Override
    public int getIndiceValorLista() {
        return indiceValorLista;
    }

    @Override
    public void setIndiceValorLista(int pIndice) {
        indiceValorLista = pIndice;
    }

    @Override
    public String getNomeUnicoParaIDHtml() {
        return campoReflection.getDeclaringClass().getSimpleName() + "_" + campoReflection.getName();
    }

    @Override
    public String getXhtmlInput() {
        switch (getTipoCampo().getTipo_input_prime()) {
            case TEXTO_COM_FORMATACAO:
            case TEXTO_SEM_FORMATACAO:
                if (isTemMascara()) {
                    return FabTipoVisualCampo.TEXTO_COM_FORMATACAO.getComponente().getXhtmlJSF();
                } else {
                    return FabTipoVisualCampo.TEXTO_SEM_FORMATACAO.getComponente().getXhtmlJSF();
                }
            default:
                return getTipoCampo().getTipo_input_prime().getComponente().getXhtmlJSF();
        }
    }

    @Override
    public String getXhtmlDiferenciado(ItfComponenteVisualSB pComponente) {
        //caso não tenha sido enviado um componente diferenciado, retorna o componente do campo
        if (pComponente == null) {
            return getTipoCampo().getTipo_input_prime().getComponente().getXhtmlJSF();
        }

        // caso o componente diferenciado seja igual ao componente padrão da familia de componentes, retorna o compoenente do campo
        if (pComponente.getXhtmlJSF().equals(getTipoCampo().getTipo_input_prime().getFamilia().getXhtmlJSFPadrao())) {
            return getTipoCampo().getTipo_input_prime().getComponente().getXhtmlJSF();
        }

        ///caso o componente enviado não seja da familia retornar o xhtml campo incompativel
        if (getTipoCampo().getTipo_input_prime().getFamilia().equals(pComponente.getFamilia())) {
            return pComponente.getXhtmlJSF();
        } else {
            return ItfFabTipoComponenteVisual.PASTA_TAG_LIBS + ItfFabTipoComponenteVisual.JSF_COMPONENTE_INCOMPATIVEL;
        }
    }

}
