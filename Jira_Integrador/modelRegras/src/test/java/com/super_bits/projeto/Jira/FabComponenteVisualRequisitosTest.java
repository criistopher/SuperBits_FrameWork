/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvioF
 */
public class FabComponenteVisualRequisitosTest {

    public FabComponenteVisualRequisitosTest() {
    }

    @Test
    public void testValues() {
        ComponenteVisualSB cv = FabComponenteVisualRequisitos.DESCRICAO_ELEMENTO_VINCULADO.getComponente();
        System.out.println(cv.getId());
    }

    @Test
    public void testValueOf() {
    }

    @Test
    public void testGetFamilia() {
    }

    @Test
    public void testGetComponente() {
    }

    @Test
    public void testGetRegistro() {
    }

}
