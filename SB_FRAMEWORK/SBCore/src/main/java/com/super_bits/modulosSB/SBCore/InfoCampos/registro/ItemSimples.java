package com.super_bits.modulosSB.SBCore.InfoCampos.registro;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.lang.reflect.Field;

@SuppressWarnings("serial")
public abstract class ItemSimples extends ItemGenerico implements
        ItfBeanSimples {

    public ItemSimples() {
        super();

        //	adcionaCampoEsperado(new CampoEsperado(TC.IMG_PEQUENA, CInfo.SITE_URL
        //			+CInfo.pastaImagens + "/SBPequeno.jpg"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.AAA_NOME_CURTO), true);
        adcionaCampoEsperado(new CampoEsperado(FabCampos.ID), true);

    }

    @Override
    public String getImgPequena() {
        throw new UnsupportedOperationException("O método para obter Imagem Pequena não foi implementado");

    }

    @Override
    public String getNomeCurto() {
        String nome = (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME_CURTO);
        String nomeCurto = "";
        nome = nome.replace("-", " ");
        nome = nome.replace(".", " ");
        for (String parte : nome.split(" ")) {
            if (nomeCurto.length() < 25) {
                if (nomeCurto.length() > 0) {
                    nomeCurto = nomeCurto + " " + parte;
                } else {
                    nomeCurto = nomeCurto + parte;
                }
            }
        }
        return nomeCurto;
    }

    public String getNomeCurtoURLAmigavel() {
        String nomeCurto = (String) getValorByTipoCampoEsperado(FabCampos.AAA_NOME_CURTO);
        return UtilSBCoreStrings.makeStrUrlAmigavel(nomeCurto);
    }

    public int getId() {
        return Integer.parseInt(getValorByTipoCampoEsperado(FabCampos.ID).toString());
    }

    public String getCampoSQLNomeCurto() {
        Field campo = getCampoByAnotacao(FabCampos.AAA_NOME_CURTO);
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
        return getNomeCurto();
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
        if (obj.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

}
