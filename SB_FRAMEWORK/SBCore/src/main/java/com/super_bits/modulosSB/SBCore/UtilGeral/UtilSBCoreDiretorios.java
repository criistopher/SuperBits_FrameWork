/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.filefilter.FileFileFilter;

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

    private static List<String> getDiretoriosRecursivoOrdemMaoirArvore(List<String> listaAntiga, File pDiretorio) {
        boolean raiz = false;
        if (listaAntiga == null) {
            listaAntiga = new ArrayList<>();
            raiz = true;
            listaAntiga.add(pDiretorio.getAbsolutePath());
        }
        List<String> diretorios = new ArrayList<>();
        if (!pDiretorio.isDirectory()) {
            throw new UnsupportedOperationException("O caminho enviado não é um diretorio");
        }

        FileFileFilter filtro = new FileFileFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory(); //To change body of generated methods, choose Tools | Templates.
            }

        };

        String[] subdirs = pDiretorio.list(filtro);
        if (subdirs.length > 0) {
            for (String subdir : subdirs) {
                System.out.println("Encontrado:" + subdir);
                listaAntiga.add(pDiretorio.getAbsolutePath() + "/" + subdir);
                listaAntiga.addAll(getDiretoriosRecursivoOrdemMaoirArvore(listaAntiga, new File(pDiretorio.getAbsolutePath() + "/" + subdir)));
            }
        } else {
            return new ArrayList<>();
        }

        if (raiz) {
            return Lists.reverse(listaAntiga);
        } else {
            return new ArrayList<>();
        }

    }

    public static List<String> getDiretoriosRecursivoOrdemMaoirArvore(File pDiretorio) {

        return getDiretoriosRecursivoOrdemMaoirArvore(null, pDiretorio);

    }

}
