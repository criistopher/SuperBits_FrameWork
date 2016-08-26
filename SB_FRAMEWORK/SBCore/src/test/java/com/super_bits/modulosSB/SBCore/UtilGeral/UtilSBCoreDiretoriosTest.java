/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreDiretoriosTest {

    public UtilSBCoreDiretoriosTest() {
    }

    //@Test
    public void testGetDiretorioMenosXCasas() {
        String teste
                = "resources/SBComp/tagLib/tags/com/sb/botaoAcao";

        System.out.println("Resultado=" + UtilSBCoreDiretorios.getDiretorioMenosXCasas(teste, 1));
        System.out.println("Resultado=" + UtilSBCoreDiretorios.getNomeArquivo(teste));

    }

    @Test
    public void testeSubdiretorios() {
        List<String> lista = UtilSBCoreDiretorios.getDiretoriosRecursivoOrdemMaoirArvore(new File("/home/superBits/projetos"));
        for (String arq : lista) {
            System.out.println("Arquivo===" + arq);
        }
    }

}
