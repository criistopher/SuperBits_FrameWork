/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringNomeArquivosEDiretorios;
import java.io.File;

/**
 *
 * @author desenvolvedor
 */
public class MapaSubstituicaoArquivo extends MapaSubstituicao {

    public MapaSubstituicaoArquivo(File arquivo) {
        adicionarPalavraChave("nomeArquivo", UtilSBCoreStringNomeArquivosEDiretorios.getNomeArquivo(arquivo.getName()));

    }

}
