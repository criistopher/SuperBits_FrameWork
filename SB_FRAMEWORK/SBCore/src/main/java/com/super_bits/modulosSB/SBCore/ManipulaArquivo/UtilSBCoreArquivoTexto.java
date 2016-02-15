/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * Conjunto de Utilitáros para manipulação de arquivos Texto
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 25/05/2014
 *
 */
public abstract class UtilSBCoreArquivoTexto {

    public static boolean criarSeNaoExistir(String pCaminhoArquivo) throws IOException {
        File arquivo = new File(pCaminhoArquivo);

        if (!arquivo.exists()) {

            File pasta = new File(arquivo.getParent());
            pasta.mkdirs();

            arquivo.createNewFile();
            return true;

        } else {
            return false;
        }
    }

    /**
     *
     * Cria uma nova linha no arquivo, e escreve o conteúdo possui tratamento
     * para criação do arquivo caso não exista
     *
     * @param pCaminhoArquivo Caminho do arquivo
     * @param pLinha Conteúdo da linha que será escrita
     * @return Verdadeiro -> suecesso da operação, Falso falha na operação
     *
     */
    public synchronized static boolean escreverEmArquivo(String pCaminhoArquivo, String pLinha) {

        try {
            criarSeNaoExistir(pCaminhoArquivo);
        } catch (IOException ex) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor(pLinha, ex);
            return false;
        }

        System.out.println("Escrevendo em arquivo" + pCaminhoArquivo);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pCaminhoArquivo, true)))) {
            out.println(pLinha);
            out.close();
            return true;
        } catch (IOException e) {
            System.out.println("errp" + e.getMessage());
            return false;
        }
    }

    /**
     *
     * Cria uma nova linha e escreve a String no arquivo
     *
     * @param linha linha adicionada
     * @param pArquivo Caminho do arquivo
     * @return
     */
    public static synchronized boolean printLnNoArquivo(String linha, String pArquivo) {

        try {
            criarSeNaoExistir(pArquivo);
        } catch (IOException ex) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando arquivo" + pArquivo, ex);
            return false;
        }

        PrintWriter output;

        try {
            output = new PrintWriter(new FileWriter(pArquivo, true));
            output.println(linha);
            output.flush();
            output.close();
            return true;

        } catch (IOException ex) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando arquivo" + pArquivo, ex);
            return false;
        }

    }

    /**
     *
     * @param pArquivo
     * @param pLinhas
     * @return
     */
    public static synchronized boolean escreveLinhasNoArquivo(String pArquivo, List<String> pLinhas) {

        try {
            criarSeNaoExistir(pArquivo);
        } catch (IOException ex) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando arquivo" + pArquivo, ex);
            return false;
        }

        PrintWriter output;

        try {
            output = new PrintWriter(new FileWriter(pArquivo, true));
            for (String linha : pLinhas) {
                output.println(linha);
            }

            output.flush();
            output.close();
            return true;

        } catch (IOException ex) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando arquivo" + pArquivo, ex);
            return false;
        }

    }

    /**
     *
     * Limpa o contaúdo do arquivo
     *
     * @param pCaminhoArquivo caminho absoluto do arquivo
     * @return verdadeiro se executar com sucesso, falso se falhar na operação
     */
    public synchronized static boolean limparArquivoTexto(String pCaminhoArquivo) {
        try {
            criarSeNaoExistir(pCaminhoArquivo);
        } catch (IOException ex) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando Arquivo" + pCaminhoArquivo, ex);
            return false;
        }

        File arquivo = new File(pCaminhoArquivo);
        PrintWriter writer;
        try {
            writer = new PrintWriter(arquivo);
            writer.print("");
            writer.close();

            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }

    }

    public static boolean isTemPalavraNoArquivo(String pArquivo, String pPalavra) {
        throw new UnsupportedOperationException("Procura de palavras em arquivo ainda não implementado");
    }

}
