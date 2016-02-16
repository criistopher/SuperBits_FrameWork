/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ManipulaArquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cristopher
 */
public class UtilSBCoreArquivoTextoIT {

    public UtilSBCoreArquivoTextoIT() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testSomeMethod() {
    }

    @Test
    public void isTemPalavraNoArquivo() {

        String pArquivo = "/home/superBits/dados/teste";
        String pPalavra = "0";

        boolean encontrou = UtilSBCoreArquivoTexto.isTemPalavraNoArquivo(pArquivo, pPalavra);

        assertTrue("A palavra não foi encontrada no arquivo ", encontrou);
    }

}
