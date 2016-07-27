package com.super_bits.modulosSB.SBCore.InfoCampos.registro;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanContatoCorporativo;

public class ItemContatoCorporativo extends ItemEndereco implements
        ItfBeanContatoCorporativo {

    public ItemContatoCorporativo() {
        super();

    }

    @Override
    public String getSite() {

        return (String) getValorByTipoCampoEsperado(FabCampos.SITE);
    }

    @Override
    public String telefone() {
        return (String) getValorByTipoCampoEsperado(FabCampos.TELEFONE_FIXO_NACIONAL);
    }

    @Override
    public String responsavel() {
        return (String) getValorByTipoCampoEsperado(FabCampos.RESPONSAVEL);
    }

}
