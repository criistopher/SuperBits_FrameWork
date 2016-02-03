package com.super_bits.modulosSB.Persistencia.registro.persistidos;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoLookUp;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;

public class EntidadeLookUp extends EntidadeSimples {

    public EntidadeLookUp() {
        super();
        InfoLookUp info = this.getClass().getAnnotation(InfoLookUp.class);

        if (info == null) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Info lookup não anotado para" + this.getClass().getName(), null);
        }

    }

}
