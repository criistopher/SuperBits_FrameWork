/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.InomeProjetoI.paginas;

import com.superBits.monitorProjeto.paginas.PgRequisitosVisaoGeral;
import com.super_bits.InomeProjetoI.paginas.testesConformidade.TestesWebAppRequisitoInomeProjetoI;
import org.junit.Test;

/**
 *
 * @author salvioF
 */
public class PgPaginaInicialRequisitoTest extends TestesWebAppRequisitoInomeProjetoI {

    public PgPaginaInicialRequisitoTest() {
    }

    @Test
    public void testeFluxo() {

        PgRequisitosVisaoGeral paginaInicial = new PgRequisitosVisaoGeral();
        paginaInicial.inicio();
//        /paginaInicial.getAmbienteDesenvolvimento().

    }

}
