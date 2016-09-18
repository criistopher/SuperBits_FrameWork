package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import java.lang.reflect.Field;

public class ItemSimples extends ItemGenerico implements
        ItfBeanSimples {

    public ItemSimples() {
        super();

        //	adcionaCampoEsperado(new CampoEsperado(TC.IMG_PEQUENA, CInfo.SITE_URL
        //			+CInfo.pastaImagens + "/SBPequeno.jpg"));
        //adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME_CURTO), true);
        //adcionaCampoEsperado(new CampoEsperado(FabCampos.ID), true);
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME), true);
        adcionaCampoEsperado(new CampoEsperado(FabCampos.ID), true);
    }

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("O método para obter Imagem Pequena não foi implementado");

    }

    @Override
    public String getNomeCurto() {
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME);
        /**
         *
         * TODO String nome = (String)
         * getValorByTipoCampoEsperado(FabCampos.AAA_NOME_CURTO); String
         * nomeCurto = ""; nome = nome.replace("-", " "); nome =
         * nome.replace(".", " "); for (String parte : nome.split(" ")) { if
         * (nomeCurto.length() < 25) {
         * if (nomeCurto.length() > 0) { nomeCurto = nomeCurto + " " + parte; }
         * else { nomeCurto = nomeCurto + parte; } } } return nomeCurto;
         */
    }

    public String getNomeCurtoURLAmigavel() {
        return "não implementado";
        //String nomeCurto = (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME_CURTO);
        // return UtilSBCoreStrings.makeStrUrlAmigavel(nomeCurto);
    }

    @Override
    public int getId() {
        return Integer.parseInt(getValorByTipoCampoEsperado(FabCampos.ID).toString());
    }

    public String getCampoSQLNomeCurto() {
        Field campo = getCampoByAnotacao(FabCampos.AAA_NOME);
        if (campo == null) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Nome curto não foi encontrado para obter o nome SQL do campo", null);
            return "NomeCurto não encontrado na classe" + this.getClass().getSimpleName();
        }
        return campo.getName();

    }

    public void uploadFoto(Object event) {
        //FileUploadEvent event
        //TODO MOVER DESTA PASTA
        /**
         * String categoria = (String) event.getComponent().getAttributes()
         * .get("catImagem"); ArquivosDeEntidade.SalvaIMAGEM(this,
         * event.getFile(), categoria);
         *
         */
    }

    @Override
    public String toString() {
        if (this == null) {
            return "nulo";
        }
        return getNome();
    }

    @Override
    public int hashCode() {
        if (this == null) {
            return "nulo".hashCode();
        }
        System.out.println(getNomeCurto() + "||" + getNomeCurto().hashCode());
        return getNomeCurto().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getNome() {
        return (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME);
    }

    @Override
    public void setNome(String pNome) {
        setValorByTipoCampoEsperado(FabCampos.AAA_NOME, pNome);
    }

    @Override
    public void setId(int pID) {
        setValorByTipoCampoEsperado(FabCampos.ID, pID);
    }

    @Override
    public String getNomeUnicoSlug() {
        return getNomeCurto() + "-" + getId();
    }

    @Override
    public String getIconeDaClasse() {
        return UtilSBCoreReflexao.getIconeDoObjeto(this.getClass());
    }

}
