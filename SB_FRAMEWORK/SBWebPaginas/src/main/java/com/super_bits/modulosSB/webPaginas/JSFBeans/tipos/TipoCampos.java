/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.tipos;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 *
 *
 *
 *
 *
 * @author sfurbino
 */
@ApplicationScoped
public class TipoCampos extends ConstantesWeb {

    public TipoCampos() {
        super(FabCampos.class);
    }

    @Override
    public String getPadrao() {

        FabCampos campopadrao = FabCampos.TEXTO_SIMPLES;
        return campopadrao.toString();

    }

    public static String getStrTipoCampoInput(FabCampos pCampo) {

        return pCampo.getTipo_input_prime().getComponente().toString();

    }

    public String getNomeCurto() {
        return getStrTipoCampoInput(FabCampos.AAA_NOME);
    }

    public String getNomeLongo() {
        return getStrTipoCampoInput(FabCampos.AAA_NOME);
    }

    public String getLC_CEP() {
        return getStrTipoCampoInput(FabCampos.AAA_DESCRITIVO);
    }

    public String getID() {
        return getStrTipoCampoInput(FabCampos.ID);
    }

    public String getLOOKUPMULTIPLO() {
        return getStrTipoCampoInput(FabCampos.LISTA_OBJETOS);
    }

    public String getTEXTO_SIMPLES() {
        return getStrTipoCampoInput(FabCampos.TEXTO_SIMPLES);
    }

    public String getNOME_CURTO() {
        return getStrTipoCampoInput(FabCampos.AAA_NOME);
    }

    public String getNOME_CURTO_LONGO() {
        return getStrTipoCampoInput(FabCampos.AAA_NOME_LONGO);
    }

    public String getDescritivo() {
        return getStrTipoCampoInput(FabCampos.AAA_DESCRITIVO);
    }

    public String getDataHora() {

        return getStrTipoCampoInput(FabCampos.DATAHORA);

    }

    public String getIMG_PEQUENA() {
        return getStrTipoCampoInput(FabCampos.IMG_PEQUENA);
    }

    public String getIMG_MEDIA() {
        return getStrTipoCampoInput(FabCampos.IMG_MEDIA);
    }

    public String getIMG_GRANDE() {
        return getStrTipoCampoInput(FabCampos.IMG_GRANDE);
    }

    public String getIDENTIFICADOR() {
        return getStrTipoCampoInput(FabCampos.ID);
    }

    public String getLAT() {
        return getStrTipoCampoInput(FabCampos.LATITUDE);
    }

    public String LONG() {
        return getStrTipoCampoInput(FabCampos.Longitude);
    }

    public String getLCLOGRADOURO() {
        return getStrTipoCampoInput(FabCampos.LCLOGRADOURO);
    }

    public String getLCCEP() {
        return getStrTipoCampoInput(FabCampos.LCCEP);
    }

    public String getLCBairro() {
        return getStrTipoCampoInput(FabCampos.LCBairro);
    }

    public String getLCCidade() {
        return getStrTipoCampoInput(FabCampos.LCCidade);
    }

    public String getTelefoneFixoNacional() {
        return getStrTipoCampoInput(FabCampos.TELEFONE_FIXO_NACIONAL);
    }

    public String getTelefoneComplementar() {
        return getStrTipoCampoInput(FabCampos.TELEFONE_CELULAR);
    }

    public String getLCComplemeto() {
        return getStrTipoCampoInput(FabCampos.LCComplemeto);
    }

    public String getLCCampoAberto() {
        return getStrTipoCampoInput(FabCampos.LCCampoAberto);
    }

    public String getHTML() {
        return getStrTipoCampoInput(FabCampos.HTML);
    }

    public String getCHART_VALOR() {
        return getStrTipoCampoInput(FabCampos.CHART_VALOR);
    }

    public String getCHART_LABEL() {
        return FabCampos.CHART_LABEL.toString();
    }

    public String getCHART_CATEGORIA() {
        return getStrTipoCampoInput(FabCampos.CHART_CATEGORIA);
    }

    public String getCALENDARIO() {
        return getStrTipoCampoInput(FabCampos.CALENDARIO);
    }

    public String getTELEFONE_FIXO_NACIONAL() {
        return getStrTipoCampoInput(FabCampos.TELEFONE_FIXO_NACIONAL);
    }

    public String getMOEDAReal() {
        return getStrTipoCampoInput(FabCampos.MOEDA_REAL);
    }

    public String getLOOKUP() {
        return getStrTipoCampoInput(FabCampos.OBJETO_DE_UMA_LISTA);
    }

    public String getCOR() {
        return getStrTipoCampoInput(FabCampos.COR);
    }

    public String getEMAIL() {
        return getStrTipoCampoInput(FabCampos.EMAIL);
    }

    public String getSITE() {
        return getStrTipoCampoInput(FabCampos.SITE);
    }

    public String getURL() {
        return getStrTipoCampoInput(FabCampos.URL);
    }

    public String getRESPONSAVEL() {
        return getStrTipoCampoInput(FabCampos.RESPONSAVEL);
    }

    public String getCNPJ() {
        return getStrTipoCampoInput(FabCampos.CNPJ);
    }

    public String getCPF() {
        return getStrTipoCampoInput(FabCampos.CPF);
    }

    public String getINSCRICAO_ESTADUAL() {
        return getStrTipoCampoInput(FabCampos.INSCRICAO_ESTADUAL);
    }

    public String getINSCRIACAO_MUNICIPAL() {
        return getStrTipoCampoInput(FabCampos.INSCRIACAO_MUNICIPAL);
    }

    public String getAAA_NOME_CURTO() {
        return getStrTipoCampoInput(FabCampos.AAA_NOME);
    }

    public String getAAA_NOME_LONGO() {
        return getStrTipoCampoInput(FabCampos.AAA_NOME_LONGO);
    }

    public String getAAA_DESCRITIVO() {
        return getStrTipoCampoInput(FabCampos.AAA_DESCRITIVO);
    }

    public String getQUANTIDADE() {
        return getStrTipoCampoInput(FabCampos.QUANTIDADE);
    }

    public String getPERCENTUA() {
        return getStrTipoCampoInput(FabCampos.PERCENTUAL);
    }

    public String getSenha() {
        return getStrTipoCampoInput(FabCampos.SENHA);
    }

    public String getVerdadeiroFalso() {
        return getStrTipoCampoInput(FabCampos.VERDADEIRO_FALSO);
    }

    public String getDATA() {
        return getStrTipoCampoInput(FabCampos.DATA);
    }

}
