package com.super_bits.modulosSB.Persistencia.Campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;

public class CampoMask extends Campo {

    private String mascara;
    private String regex;

    public CampoMask(String pNome, String pMascara, String pRegex) {
        super(FabCampos.TEXTO_SIMPLES);
        mascara = pMascara;
        regex = pRegex;

    }

    @Override
    public String getMascara() {
        return mascara;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

}
