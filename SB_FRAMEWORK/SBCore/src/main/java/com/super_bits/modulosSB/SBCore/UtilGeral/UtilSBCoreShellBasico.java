/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * Funcções basicas relativas ao Shell, para funções mais avançadas importe o
 * SuperBits Shell Command
 *
 * @author desenvolvedor
 */
public abstract class UtilSBCoreShellBasico {

    public static String executeCommand(String pComando) {
        System.out.println("Executando comando" + pComando);
        StringBuilder output = new StringBuilder();

        UtilSBCoreDiretorios.getNomeArquivo(pComando);
        Process processo;
        try {

            if (pComando.split("/").length > 1) {

                String diretorio = UtilSBCoreDiretorios.getDiretorioArquivo(pComando) + "/";
                String comando = "./" + UtilSBCoreDiretorios.getNomeArquivo(pComando);
                processo = Runtime.getRuntime().exec(comando, null, new File(diretorio));
            } else {
                processo = Runtime.getRuntime().exec(pComando);
            }
            processo.waitFor();
            int valorSaida = processo.exitValue();
            if (valorSaida != 0) {
                System.out.println("Ouve um erro executando,saida" + valorSaida + " para o comando" + pComando);
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(processo.getErrorStream()));

                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                    System.out.println(line);
                }
                throw new UnsupportedOperationException("ERRO executando script:[" + pComando + "] ->" + output.toString());

            } else {
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(processo.getInputStream()));

                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                    System.out.println(line);
                }
            }
        } catch (IOException | InterruptedException e) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, pComando, e);
        }

        return output.toString();

    }

    public static void abrirScriptEmConsole(String... pScripteParametros) {

        File arquivo = new File(pScripteParametros[0]);
        String comandoCompleto = "";
        int i = 0;
        for (String linha : pScripteParametros) {
            if (i > 0) {
                comandoCompleto += " " + linha;
            } else {
                comandoCompleto = linha;
            }
            i++;
        }
        if (!arquivo.exists()) {
            throw new UnsupportedOperationException("O script " + arquivo + " não foi encontrado ");
        }
        System.out.println("Executando " + comandoCompleto + " em console");
        String command[] = {"/bin/sh", "-c",
            "gnome-terminal -e \"" + comandoCompleto + "\""};
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(command);

        } catch (Exception ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando executar" + pScripteParametros, ex);
        }

    }

}
