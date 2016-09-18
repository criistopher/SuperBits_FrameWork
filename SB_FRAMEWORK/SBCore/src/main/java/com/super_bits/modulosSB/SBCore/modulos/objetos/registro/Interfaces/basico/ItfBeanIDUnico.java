/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico;

/**
 *
 * Itf comummente utilizada em registros de banco de dados, que possui um campo
 * de identificação
 *
 * @author desenvolvedor
 */
public interface ItfBeanIDUnico extends ItfBeanIDUnicoSomenteLeitura {

    public void setId(int pID);

}
