/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreDiretorios {

    public enum TIPO_ESTRUTURA_DIRETORIO {
        WINDOWS,
        LINUX
    }

    /**
     *
     * Ex: /home/superBits/superBitsDevops numero de casas = 1 retornaria
     * /home/superBits/
     *
     * @param pDiretorio
     * @param numeroCasas
     * @return
     */
    public static String getDiretorioMenosXCasas(String pDiretorio, int numeroCasas) {
        //#todo compatibilidade com diretorios windows

        String[] partes = pDiretorio.split("/");
        if (partes.length < numeroCasas) {
            return pDiretorio;
        }
        String resultado = "";
        int i = 0;
        for (int j = 0; j < partes.length - numeroCasas; j++) {
            if (j > 0) {
                resultado += "/" + partes[j];

            } else {
                resultado += partes[j];
            }

        }
        return resultado;
    }

    public static String getNomeArquivo(String pDiretorio) {
        //#todo compatibilidade com diretorios windows

        String[] partes = pDiretorio.split("/");

        return partes[partes.length - 1];

    }

}
