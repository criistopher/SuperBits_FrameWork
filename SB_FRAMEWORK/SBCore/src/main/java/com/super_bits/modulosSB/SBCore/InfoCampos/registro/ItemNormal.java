package com.super_bits.modulosSB.SBCore.InfoCampos.registro;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanNormal;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.util.Date;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public abstract class ItemNormal extends ItemSimples implements ItfBeanNormal {

    public ItemNormal(Class<?> pClasseModelo) {
        super();

        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.IMG_GRANDE, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME_LONGO, getNomeCurto()));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_DATAINSERCAO, null));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_DATAALTERACAO, null));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_USUARIO_ALTERACAO, null));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.REG_USUARIO_INSERCAO, null));

        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_DESCRITIVO, UtilSBCoreStrings.getLorenIpsilum(UtilSBCoreStrings.TIPO_LOREN.PARAGRAFO)));

    }

    @Override
    public String getNomeLongo() {
        camposEsperados.getCampo(FabCampos.AAA_NOME_LONGO).setValorPadrao(getNomeCurto());
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME_LONGO);

    }

    @Override
    public String getDescritivo() {
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_DESCRITIVO);
    }

    @Override
    public String getImgGrande() {

        return (String) getValorByTipoCampoEsperado(FabCampos.IMG_GRANDE);
        //	return OrganizadorDeArquivos.getURLImagem(this, TC.IMG_GRANDE);
    }

    @Override
    public String getImgMedia() {

        return (String) getValorByTipoCampoEsperado(FabCampos.IMG_MEDIA);
        //return OrganizadorDeArquivos.getURLImagem(this, TC.IMG_MEDIA);
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getGaleria() {
        return null;// OrganizadorDeArquivos.getURLImagens(this, "galeria");
    }

    @Override
    public Date getDataHoraAlteracao() {
        return (Date) getValorByTipoCampoEsperado(FabCampos.REG_DATAALTERACAO);
    }

    @Override
    public Date getDataHoraInsercao() {
        return (Date) getValorByTipoCampoEsperado(FabCampos.REG_DATAINSERCAO);
    }

    @Override
    public ItfUsuario getUsuarioInsersao() {
        return (ItfUsuario) getValorByTipoCampoEsperado(FabCampos.REG_USUARIO_INSERCAO);
    }

    @Override
    public ItfUsuario getUsuarioAlteracao() {
        return (ItfUsuario) getValorByTipoCampoEsperado(FabCampos.REG_USUARIO_ALTERACAO);
    }

    @Override
    public void setAtivo(boolean pAtivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAtivo() {
        return (Boolean) getValorByTipoCampoEsperado(FabCampos.RET_ATIVO_INATIVO);
    }

}
