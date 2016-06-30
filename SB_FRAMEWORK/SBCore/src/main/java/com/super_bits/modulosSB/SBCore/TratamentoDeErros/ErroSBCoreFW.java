package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

public class ErroSBCoreFW extends InfoErroSBComAcoes {

    @Override
    public void alertarResponsavel() {
        System.out.println("TODO Alerta de usuario");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lancarExcecao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lancarPane() {
        System.out.println("Pane lançada no sistema");

    }

    @Override
    public void registrarErro() {

        System.out.println("Este erro deveria ser persistido de alguma forma, mas não será poruqe o método registrar erro não foi implementado");

    }

}
