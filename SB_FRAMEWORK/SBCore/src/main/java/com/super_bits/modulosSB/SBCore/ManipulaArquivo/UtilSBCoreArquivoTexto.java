/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Conjunto de Utilitáros para manipulação de arquivos Texto
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 25/05/2014
 *
 */
public abstract class UtilSBCoreArquivoTexto {

    public static boolean criarSeArquivoSeNaoExistir(String pCaminhoArquivo) throws IOException {
        try {
            File arquivo = new File(pCaminhoArquivo);

            if (!arquivo.exists()) {

                File pasta = new File(arquivo.getParent());
                pasta.mkdirs();

                arquivo.createNewFile();
                return true;

            }
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando criar:" + pCaminhoArquivo, t);
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
            criarSeArquivoSeNaoExistir(pCaminhoArquivo);
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
            criarSeArquivoSeNaoExistir(pArquivo);
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
            criarSeArquivoSeNaoExistir(pArquivo);
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
            criarSeArquivoSeNaoExistir(pCaminhoArquivo);
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
        String linhaAtual;
        File arquivo = new File(pArquivo);
        try {
            FileReader leitor = new FileReader(arquivo);
            BufferedReader operador = new BufferedReader(leitor);
            while (operador.ready()) {
                linhaAtual = operador.readLine().toLowerCase();
                if (linhaAtual.contains(pPalavra.toLowerCase())) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilSBCoreArquivoTexto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilSBCoreArquivoTexto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
