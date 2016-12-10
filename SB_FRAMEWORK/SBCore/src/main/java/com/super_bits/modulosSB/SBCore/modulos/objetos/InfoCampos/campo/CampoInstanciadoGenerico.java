/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.FabFamiliaCompVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualSistema;
import java.lang.reflect.Field;

/**
 *
 * @author sfurbino
 */
public abstract class CampoInstanciadoGenerico extends Campo implements ItfCampoInstanciado {

    protected final FieldComSerializacao campoReflection;
    protected final InfoCampo infoCampo;
    protected int indiceValorLista = -1;

    public CampoInstanciadoGenerico(Campo pcampo, Field pCampoReflection) {
        super(pcampo);
        campoReflection = new FieldComSerializacao(pCampoReflection);
        infoCampo = pCampoReflection.getAnnotation(InfoCampo.class);

    }

    @Override
    public String getNomeCamponaClasse() {
        return campoReflection.campo().getName();
    }

    @Override
    public FieldComSerializacao getCampoReflection() {
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
    public String getNomeUnicoParaIDHtml(ItfComponenteVisualSB pComponente) {
        if (pComponente != null) {
            return getPrefixoUnicoParaIDHtml() + "_" + pComponente.getClasseCSS();
        } else {
            return getPrefixoUnicoParaIDHtml() + "_" + campoReflection.campo().getName();
        }
    }

    @Override
    public String getPrefixoUnicoParaIDHtml() {
        return campoReflection.campo().getDeclaringClass().getSimpleName() + "_" + campoReflection.campo().getName();
    }

    @Override
    public ItfComponenteVisualSB getComponenteVisualPadrao() {

        if (getTipoCampo().getTipo_input_prime().getFamilia().equals(FabFamiliaCompVisual.INPUT)) {
            FabCompVisualInputs campoFamiliaInput = (FabCompVisualInputs) getTipoCampo().getTipo_input_prime();
            switch (campoFamiliaInput) {
                case TEXTO_COM_FORMATACAO:
                case TEXTO_SEM_FORMATACAO:
                    if (isTemMascara()) {
                        return FabCompVisualInputs.TEXTO_COM_FORMATACAO.getComponente();
                    } else {
                        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getComponente();
                    }

            }
        }

        return getTipoCampo().getTipo_input_prime().getComponente();

    }

    @Override
    public ItfComponenteVisualSB getComponenteDiferenciado(ItfComponenteVisualSB pComponente) {
        //caso não tenha sido enviado um componente diferenciado, retorna o componente do campo
        if (pComponente == null) {
            return getComponenteVisualPadrao();
        }

        // caso o componente diferenciado seja igual ao componente padrão da familia de componentes, retorna o compoenente do campo
        if (pComponente.getXhtmlJSF().equals(getTipoCampo().getTipo_input_prime().getFamilia().getXhtmlJSFPadrao())) {
            return getComponenteVisualPadrao();
        }

        ///caso o componente enviado não seja da familia retornar o xhtml campo incompativel
        if (getComponenteVisualPadrao().getFamilia().equals(pComponente.getFamilia())) {
            return pComponente;
        } else {
            return FabCompVisualSistema.INCOMPATIVEL.getComponente();
        }
    }

    @Override
    public boolean isTemDescricao() {

        return !(getDescricao() == null || getDescricao().isEmpty() || getDescricao().equals(getLabel()) || getDescricao().length() < 4);

    }

}
