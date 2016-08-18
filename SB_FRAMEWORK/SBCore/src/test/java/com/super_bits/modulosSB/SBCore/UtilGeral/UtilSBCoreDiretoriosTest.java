/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreDiretoriosTest {

    public UtilSBCoreDiretoriosTest() {
    }

    @Test
    public void testGetDiretorioMenosXCasas() {
        String teste
                = "resources/SBComp/tagLib/tags/com/sb/botaoAcao";

        System.out.println("Resultado=" + UtilSBCoreDiretorios.getDiretorioMenosXCasas(teste, 1));
        System.out.println("Resultado=" + UtilSBCoreDiretorios.getNomeArquivo(teste));

    }

}
