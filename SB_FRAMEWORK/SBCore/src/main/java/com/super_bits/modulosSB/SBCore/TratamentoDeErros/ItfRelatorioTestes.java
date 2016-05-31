/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfRelatorioTestes {

    public List<ItfInfoErroSB> executarTestes();

    public void exibirRelatorio();

}
