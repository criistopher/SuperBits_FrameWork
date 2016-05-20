package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

public interface ItfBeanSimples extends ItfBeanSimplesSomenteLeitura,
        ItfBeanIDUnico,
        ItfBeanSimplesSemReflexao,
        ItfBeanReflexoes {

    public String getNomeUnicoSlug();

}
