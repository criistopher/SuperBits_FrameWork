/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoNaoImplementado;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class CampoNaoImplementadoTest {

    public CampoNaoImplementadoTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testValidarCampo() {

        CampoNaoImplementado teste = new CampoNaoImplementado();

        System.out.println(teste.getLabel());
        System.out.println(teste.getTipoCampoSTR());
    }

    @Test
    public void testGetParent() {
    }

    @Test
    public void testGetValor() {
    }

    @Test
    public void testSetValor() {
    }

}
