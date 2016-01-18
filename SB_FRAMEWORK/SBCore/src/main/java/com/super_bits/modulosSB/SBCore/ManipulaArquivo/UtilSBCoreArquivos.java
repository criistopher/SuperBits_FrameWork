/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * Funcões Basicas Relativas a manipulação de Arquivos
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 19/07/2014
 *
 */
public abstract class UtilSBCoreArquivos {

    public static boolean compactarParaZip(String arqSaida, String arqEntrada) {
        int TAMANHO_BUFFER = 4096; // 4kb
        int cont;
        byte[] dados = new byte[TAMANHO_BUFFER];

        BufferedInputStream origem = null;
        FileInputStream streamDeEntrada = null;
        FileOutputStream destino = null;
        ZipOutputStream saida = null;
        ZipEntry entry = null;

        try {
            destino = new FileOutputStream(new File(arqSaida));
            saida = new ZipOutputStream(new BufferedOutputStream(destino));
            File file = new File(arqEntrada);
            streamDeEntrada = new FileInputStream(file);
            origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
            entry = new ZipEntry(file.getName());
            saida.putNextEntry(entry);

            while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                saida.write(dados, 0, cont);
            }
            origem.close();
            saida.close();
            return true;
        } catch (IOException e) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro compactando arquivos" + arqSaida, e);
            return false;

        }
    }

    public static boolean descompactar(String arquivoZip, String pastaDestino) {
        byte[] buffer = new byte[1024];

        try {

            //Cria a Pasta de Destino
            File folder = new File(arquivoZip);
            if (!folder.exists()) {
                folder.mkdir();
            }

            //Obtem o conteúdo do arquivo
            ZipInputStream zis
                    = new ZipInputStream(new FileInputStream(arquivoZip));
            // Obtendo a lista de arquivos
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {

                String fileName = ze.getName();
                File newFile = new File(pastaDestino + File.separator + fileName);

                System.out.println("file unzip : " + newFile.getAbsoluteFile());

                //Criando todas as pastas não existentes
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Fim descompactação");
            return true;
        } catch (IOException ex) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro descompactando arquivo" + pastaDestino, ex);
            return false;
        }
    }

    public static boolean copiarArquivos(String origem, String destino) {

        File src = new File(origem);
        File dest = new File(destino);

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                System.out.println("Diretório sendo copiado de "
                        + src + "  para: " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                if (!copiarArquivos(srcFile.getAbsolutePath(), destFile.getAbsolutePath())) {
                    return false;
                }
            }

        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in;
            try {
                in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dest);
                byte[] buffer = new byte[1024];

                int length;
                //copy the file content in bytes
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

                in.close();
                out.close();
                System.out.println("Arquivo copiado de " + src + " para " + dest);
            } catch (Exception ex) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando arquivo" + "Erro copiando arquivo de" + src + "para " + dest, ex);

                return false;
            }

        }
        return true;
    }
}
