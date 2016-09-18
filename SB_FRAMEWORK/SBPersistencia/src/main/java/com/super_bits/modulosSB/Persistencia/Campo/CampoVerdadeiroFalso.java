package com.super_bits.modulosSB.Persistencia.Campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import java.lang.reflect.Field;

public class CampoVerdadeiroFalso extends Campo {

    public CampoVerdadeiroFalso(
            FabCampos pTipo,
            Field campo) {
        super(pTipo.TEXTO_SIMPLES);
        // TODO Implementar Verdadeiro ou Falso
    }

}
