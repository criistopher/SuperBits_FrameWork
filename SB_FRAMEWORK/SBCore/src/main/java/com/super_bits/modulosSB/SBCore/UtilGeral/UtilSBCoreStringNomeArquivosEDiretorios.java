/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import java.io.File;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreStringNomeArquivosEDiretorios {

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
        return UtilSBCoreDiretorios.getDiretorioMenosXCasas(pDiretorio, numeroCasas);
    }

    /**
     * *(ele não certifica se o ultimo nome é um arquivo ou diretorio,
     * simplismente retorna a ultima parte do diretorio)
     *
     * A ideia do metodo é ser simples e rápido, pois um simples objeto File
     * atenderia a solução
     *
     * @param pDiretorio
     * @return O nome do arquivo sem o diretorio
     */
    public static String getNomeArquivo(String pDiretorio) {
        return UtilSBCoreDiretorios.getNomeArquivo(pDiretorio);
    }

    /**
     * *(ele não certifica se o ultimo nome é um arquivo ou diretorio,
     * simplismente retorna a ultima parte do diretorio)
     *
     * @param pCaminhoCompleto
     * @return O diretorio do arquivo Sem o nome do arquivo
     */
    public static String getDiretorioArquivo(String pCaminhoCompleto) {
        return UtilSBCoreDiretorios.getNomeArquivo(pCaminhoCompleto);
    }

    /**
     *
     * Retira a extenção do nome do arquivo exmplo: meusSegredos.txt retornaria
     * meusSegredos
     *
     * @param pNomeArquivo nome completo do arquivo
     * @return nome do arquivo sem extenção
     */
    public static String getNomeDoArquivoSemExtencao(String pNomeArquivo) {
        return pNomeArquivo.lastIndexOf('.') > pNomeArquivo.lastIndexOf(File.separatorChar)
                ? pNomeArquivo.substring(0, pNomeArquivo.lastIndexOf('.'))
                : pNomeArquivo;
    }

    /**
     *
     * Retorna a exetenção de um nome de arquivo, exemplo: senhaDoBanco.txt
     * retorna txt
     *
     *
     * @param pNomeArquivo Nome do arquivo parametrizado
     * @return exetenção do arquivo
     */
    public static String getExtencaoNomeArquivo(String pNomeArquivo) {
        return pNomeArquivo.lastIndexOf('.') > pNomeArquivo.lastIndexOf(File.separatorChar)
                ? pNomeArquivo.substring(pNomeArquivo.lastIndexOf('.'), pNomeArquivo.length())
                : "";
    }

}
