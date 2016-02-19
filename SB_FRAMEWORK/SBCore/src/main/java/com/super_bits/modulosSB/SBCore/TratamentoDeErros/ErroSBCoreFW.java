package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

public class ErroSBCoreFW extends InfoErroSB {

    @Override
    protected void alertarResponsavel() {
        System.out.println("TODO Alerta de usuario");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void lancarExcecao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void lancarPane() {
        System.out.println("Pane lançada no sistema");

    }

    @Override
    protected void registrarErro() {

        System.out.println("Este erro deveria ser persistido de alguma forma, mas não será poruqe o método registrar erro não foi implementado");

    }

}
