/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.estruturaEntidade;

/**
 *
 * Alguns metodos são especiais como:
 *
 * Metodos de valiação, são chamados antes de setar um valor a um objeto
 *
 *
 * @author salvioF
 */
public interface ItfMetodosValidacao {

    public void validar(Object... pParametros);

}
