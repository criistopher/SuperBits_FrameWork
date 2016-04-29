package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;

public interface ItfBeanSimples extends ItfBeanSimplesSomenteLeitura,
        ItfBeanIDUnico,
        ItfBeanSimplesSemReflexao,
        ItfBeanReflexoes {

    public String getNomeUnicoSlug();

}
