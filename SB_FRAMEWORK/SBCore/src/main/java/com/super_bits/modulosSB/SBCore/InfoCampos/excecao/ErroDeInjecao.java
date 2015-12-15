package com.super_bits.modulosSB.SBCore.InfoCampos.excecao;

public class ErroDeInjecao extends Exception {

	private static final long serialVersionUID = 810546994379030324L;

	public ErroDeInjecao() {
    }

    public ErroDeInjecao(String s) {
        super(s);
    }

    public ErroDeInjecao(Throwable throwable) {
        super(throwable);
    }
    
    public ErroDeInjecao(String s, Throwable throwable) {
        super(s, throwable);
    }
	
}
