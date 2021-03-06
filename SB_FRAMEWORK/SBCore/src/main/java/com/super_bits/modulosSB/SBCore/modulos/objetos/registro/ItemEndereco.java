package com.super_bits.modulosSB.SBCore.modulos.objetos.registro;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanEndereco;

public class ItemEndereco extends ItemNormal implements ItfBeanEndereco {

    /**
     *
     */
    private Double latitude;
    private Double longitude;
    //private LatLng localizacao;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ItemEndereco() {

        super();

        adcionaCampoEsperado(new CampoEsperado(FabCampos.LATITUDE, "-19.8225864"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.Longitude, "-43.926274"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCCEP, ""), true);

        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCLOGRADOURO, ""));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.TELEFONE_FIXO_NACIONAL, ""));

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
    public String getComplemento() {

        return "numero 66 ap Teste";
    }

    @Override
    public String getCEP() {
        return (String) getValorByTipoCampoEsperado(FabCampos.LCCEP);
    }

    public String getTelefone() {
        return (String) getValorByTipoCampoEsperado(FabCampos.TELEFONE_FIXO_NACIONAL);
    }

}
