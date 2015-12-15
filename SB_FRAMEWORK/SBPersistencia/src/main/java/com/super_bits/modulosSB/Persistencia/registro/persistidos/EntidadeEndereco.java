package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanEndereco;

public abstract class EntidadeEndereco extends EntidadeNormal implements ItfBeanEndereco {

    /**
     * TODU ajustar EntidadeEndereco
     */
    private Double latitude;
    private Double longitude;
    //private LatLng localizacao;

    public EntidadeEndereco(Class<?> pClasseModelo) {

        super(pClasseModelo);

        adcionaCampoEsperado(new CampoEsperado(FabCampos.LAT, "-19.8225864"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LONG, "-43.926274"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCCEP, ""));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCCidade, "Cidade Não Informada"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCComplemeto, ""));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCLOGRADOURO, ""));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.Telefone, ""));

    }

    private void setLongitude(Double pLongitude) {
        this.longitude = pLongitude;

    }

    private void setLatitude(Double pLatitude) {
        this.latitude = pLatitude;

    }

    private void setLocalizacao() {

        System.out.println("SETANDO LOCALIZACAO");

        if (latitude.intValue() != 0 & longitude.intValue() != 0) {
            //		localizacao = new LatLng(latitude, longitude);
        }

    }

    /**
     * public LatLng getLocalizacao() { setLatitude((Double)
     * GetValorByTipoCampoEsperado(TC.LAT) ); setLongitude((Double)
     * GetValorByTipoCampoEsperado(TC.LONG)); setLocalizacao(); return
     * localizacao; }
     */
}
