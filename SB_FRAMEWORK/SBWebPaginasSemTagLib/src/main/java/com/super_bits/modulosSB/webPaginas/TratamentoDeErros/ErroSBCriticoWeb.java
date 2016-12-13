package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;

public class ErroSBCriticoWeb extends ErroSBGenericoWeb {

    public ErroSBCriticoWeb(String pMsg) {

        super(pMsg);
        System.out.println(pMsg);
        UtilSBWP_JSFTools.vaParaPaginadeErro(pMsg);
    }

}
