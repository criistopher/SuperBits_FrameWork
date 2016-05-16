package com.super_bits.modulosSB.webPaginas.TratamentoDeErros;

public class ErroSBGenerico extends Exception {

    public ErroSBGenerico(Throwable throwable) {
        super(throwable);

    }

    public ErroSBGenerico(String s, Throwable throwable) {
        super(s, throwable);

    }

    public ErroSBGenerico(String pMsg) {
        super(pMsg);

    }
}
