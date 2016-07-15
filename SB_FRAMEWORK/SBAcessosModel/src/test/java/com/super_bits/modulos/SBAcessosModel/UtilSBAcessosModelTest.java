/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class UtilSBAcessosModelTest {

    public UtilSBAcessosModelTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of criarNovosAcessosNoBanco method, of class UtilSBAcessosModel.
     */
    @Test
    public void testCriarNovosAcessosNoBanco() {
        System.out.println("criarNovosAcessosNoBanco");
        EntityManager em = null;
        UtilSBAcessosModel.criarNovosAcessosNoBanco(em);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of acessoAPaginaPermitido method, of class UtilSBAcessosModel.
     */
    @Test
    public void testAcessoAPaginaPermitido() {
        System.out.println("acessoAPaginaPermitido");
        ItfUsuario pUsuario = null;
        String pRecurso = "";
        boolean expResult = false;
//        boolean result = UtilSBAcessosModel.acessoAPaginaPermitido(pUsuario, pRecurso);
        //   assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criaAcessoWebPaginas method, of class UtilSBAcessosModel.
     */
    @Test
    public void testCriaAcessoWebPaginas() {
        System.out.println("criaAcessoWebPaginas");
        //     List<AcessoSBWebPaginas> pRecursoPagina = null;
        //    UtilSBAcessosModel.criaAcessoWebPaginas(pRecursoPagina);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
