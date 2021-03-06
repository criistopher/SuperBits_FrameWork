/*
 *   Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;

/**
 *
 * Funcões Basicas Relativas a manipulação de Arquivos
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 19/07/2014
 *
 */
public abstract class UtilSBCoreArquivos {

    /**
     *
     * Recebe um caminho complento de arquivo como
     * /home/supeBits/meuArquivo.txt, e cria o diretorio deste arquivo (no vaso
     * /home/superBits seria criado
     *
     * @param pCaminhoArquivoOuDiretorio
     * @return
     */
    public static boolean criarDiretorioParaArquivo(String pCaminhoArquivoOuDiretorio) {

        String arquivo = UtilSBCoreStringNomeArquivosEDiretorios.getDiretorioArquivo(pCaminhoArquivoOuDiretorio);
        File arq = new File(arquivo);
        if (arq.exists()) {
            return true;
        }
        return arq.mkdirs();
    }

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

    /**
     *
     * @param origem Arquivo Origem
     * @param destino Destino do arquivo que será copiado
     * @return Verdadeiro se a operação acontecer com sucesso
     */
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
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro copiando arquivo de" + src + "para " + dest, ex);

                return false;
            }

        }
        return true;
    }

    public static boolean isArquivoExiste(String pString) {
        File f = new File(pString);
        return f.exists();
    }

    /**
     *
     * Retorna os arquivos de um diretorio
     *
     * @param pDiretorio
     * @return
     */
    public static List<String> getArquivosDoDiretorio(String pDiretorio) {
        List<String> arquivos = new ArrayList<>();
        try {
            File f = new File(pDiretorio);

            if (!f.isDirectory()) {
                throw new UnsupportedOperationException("A solicitação de arquivos do diretorio precisa receber um diretorio existente como parametro \n " + pDiretorio);
            }
            File[] listaCompleta = f.listFiles();
            for (File arq : listaCompleta) {
                if (arq.isFile()) {
                    arquivos.add(arq.getName());
                }
            }
        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro lendo arquivos do diretorio:" + pDiretorio, t);
        }
        return arquivos;
    }

    /**
     *
     * Copia um arquivo do pacote jar para um destino externo
     *
     *
     * @param classeDoResource Classe referencia para encontrar o arquivo
     * (Qualquer classe que esteja dentro do mesmo pacote .Jar)
     * @param caminhoArquivoResource Caminho relativo a pasta resource
     * @param destinoArquivo destino onde o arquivo será copiado
     * @return
     */
    public static boolean copiarArquivoResourceJar(Class classeDoResource, String caminhoArquivoResource, String destinoArquivo) {
        try {
            InputStream streamCompilaBanco = classeDoResource.getClassLoader().getResourceAsStream(caminhoArquivoResource);
            FileUtils.copyInputStreamToFile(streamCompilaBanco, new File(destinoArquivo));
            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, destinoArquivo, t);
            return false;
        }

    }
}
