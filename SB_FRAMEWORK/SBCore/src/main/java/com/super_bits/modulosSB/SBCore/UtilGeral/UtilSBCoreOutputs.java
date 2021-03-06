/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreOutputs {

    public static boolean salvarArquivoInput(InputStream pInput, String pCaminhoLocal) {
        Path destination = Paths.get(pCaminhoLocal);
        try {
            File diretorio = new File(pCaminhoLocal.substring(0, pCaminhoLocal.lastIndexOf(File.separator)));
            diretorio.mkdirs();
            Files.copy(pInput, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (Throwable e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro salvando arquivo a partir de imput em:" + pCaminhoLocal, e);
            return false;

        }
        return true;
    }

    public static boolean salvarArquivoBfInput(BufferedInputStream pInput, String pCaminhoLocal) {
        return salvarArquivoInput(pInput, pCaminhoLocal);

    }

    public static boolean copiarArquivo(BufferedInputStream pInput, File teste) {
        return false;
    }

}
