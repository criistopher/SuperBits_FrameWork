/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.TipoFonteUpload;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
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

    private final String nomeDoSeparador;

    public CampoInstanciadoSeparador(String pnome) {
        nomeDoSeparador = pnome;
    }

    @Override
    public Object getParent() {
        return null;
    }

    @Override
    public Object getValor() {
        return nomeDoSeparador;
    }

    @Override
    public void setValor(Object pValor) {

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
    public TIPO_PRIMITIVO getTipoPrimitivoDoValor() {
        return TIPO_PRIMITIVO.LETRAS;
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
        throw new UnsupportedOperationException("Id do componente não é suportadno em campo Instanciado separador"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescricao() {
        return "Separador para montagem de formularios";
    }

    @Override
    public String getMascara() {
        throw new UnsupportedOperationException("Mascaras não são suportadas em campos intanciado Separador"); //To change body of generated methods, choose Tools | Templates.
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
        return 0;
    }

    @Override
    public long getValorMinimo() {
        return 0;
    }

    @Override
    public List<ItfBeanSimples> getListaDeOpcoes() {
        return null;
    }

    @Override
    public String getValidacaoRegex() {
        return null;
    }

    @Override
    public boolean isTemValidacaoRegex() {
        return false;
    }

    @Override
    public boolean isTemValidacaoMinimo() {
        return false;
    }

    @Override
    public boolean isTemValidacaoMaximo() {
        return false;
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
        return null;
    }

    @Override
    public String getTipoCampoSTR() {
        return FabCampos.TEXTO_SIMPLES.toString();
    }

    @Override
    public String getFraseValidacao() {
        return null;
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
        return null;
    }

    @Override
    public void configIDPeloNome() {

    }

    @Override
    public String getNomeDoObjeto() {
        return nomeDoSeparador;
    }

    @Override
    public boolean isUmCampoNaoInstanciado() {
        return true;
    }

    @Override
    public String getLabelSlug() {
        return UtilSBCoreStrings.makeStrUrlAmigavel(getLabel());
    }

    @Override
    public int getIndiceValorLista() {
        throw new UnsupportedOperationException("Indice de calor não é compatível com separador."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIndiceValorLista(int pIndice) {

    }

    @Override
    public ItfComponenteVisualSB getComponenteDiferenciado(ItfComponenteVisualSB pComponente) {
        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getComponente();

    }

    @Override
    public ItfComponenteVisualSB getComponenteVisualPadrao() {
        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getComponente();
    }

    @Override
    public TIPO_ORIGEM_VALOR_CAMPO getOrigemValor() {
        return TIPO_ORIGEM_VALOR_CAMPO.VALOR_LIVRE;
    }

    @Override
    public boolean isUmValorLivre() {
        return true;
    }

    @Override
    public boolean iUmValorComLista() {
        return false;
    }

    @Override
    public boolean isUmValorMultiploLivre() {
        return false;
    }

    @Override
    public boolean isUmValorMultiploComLista() {
        return false;
    }

    @Override
    public String getNomeUnicoParaIDHtml(ItfComponenteVisualSB pComponente) {
        return "SEPARADOR";
    }

    @Override
    public String getPrefixoUnicoParaIDHtml() {
        return "SEPARADOR";
    }

    @Override
    public boolean isTemDescricao() {
        return false;
    }

    @Override
    public void adicionarItemNaLista(String nomeDaLista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TIPO_DECLARACAO getTipoDeclaracao() {
        return TIPO_DECLARACAO.SIMPLES;
    }

    @Override
    public String getIconeDaClasse() {
        return "fa fa-minus";
    }

}
