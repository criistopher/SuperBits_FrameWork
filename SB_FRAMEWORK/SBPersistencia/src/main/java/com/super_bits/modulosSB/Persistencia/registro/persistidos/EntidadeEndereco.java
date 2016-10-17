package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Localizacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoEsperado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanEndereco;

public abstract class EntidadeEndereco extends EntidadeNormal implements ItfBeanEndereco {

    /**
     * TODU ajustar EntidadeEndereco
     */
    private Double latitude;
    private Double longitude;

    //private LatLng localizacao;
    public EntidadeEndereco() {

        super();

        adcionaCampoEsperado(new CampoEsperado(FabCampos.LATITUDE, "-19.8225864"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.Longitude, "-43.926274"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCCEP, ""));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCCidade, "Cidade Não Informada"));
        adcionaCampoEsperado(new CampoEsperado(FabCampos.LCComplemeto, ""));
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

    @Override
    public String getCEP() {

        return (String) getValorByTipoCampoEsperado(FabCampos.LCCEP);
    }

    @Override
    public String getComplemento() {
        return (String) getValorByTipoCampoEsperado(FabCampos.LCComplemeto);
    }

    public Localizacao getLogradouro() {
        Localizacao endereco = (Localizacao) getValorByTipoCampoEsperado(FabCampos.LCLOGRADOURO);
        if (endereco == null) {
            ItfCampoInstanciado campoEndereco = getCampoByNomeOuAnotacao(FabCampos.LCLOGRADOURO.toString());
            {
                campoEndereco.setValor(new Localizacao());
            }

        }
        return (Localizacao) getValorByTipoCampoEsperado(FabCampos.LCLOGRADOURO);
    }

    @Override
    public String getTelefone() {
        return (String) getValorByTipoCampoEsperado(FabCampos.TELEFONE_FIXO_NACIONAL);
    }

    /**
     * public LatLng getLocalizacao() { setLatitude((Double)
     * GetValorByTipoCampoEsperado(TC.LAT) ); setLongitude((Double)
     * GetValorByTipoCampoEsperado(TC.LONG)); setLocalizacao(); return
     * localizacao; }
     */
}
