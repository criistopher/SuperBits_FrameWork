/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivoTexto;
import java.io.File;
import java.util.Properties;

/**
 *
 * @author salvioF
 */
public class ArquivoConfiguracaoDistribuicao {

    public static String CAMINHO_PASTA_DISTRIBUICOES = "/home/git/publicados";

    private boolean temArquivoImplantacao;
    private boolean temArquivoDesenvolvimento;
    private String SERVIDOR_HOMOLOGACAO;
    private String SERVIDOR_REQUISITOS;
    private String PASTA_GIT_RELEASE;
    private String PASTA_GIT_SOURCE;
    private String NOME_PASTA_PROJETO;

    private ArquivoConfiguracaoBase configuracaoBase = null;

    private boolean emAmbienteDeProducao;

    public ArquivoConfiguracaoDistribuicao(ArquivoConfiguracaoBase pConfigBase) {

        configuracaoBase = pConfigBase;
        checaArquivoReleaseImplantado();
        checaArquivoReleaseLocal();
        Properties propriedadesImplantacao = null;
        if (temArquivoImplantacao) {
            propriedadesImplantacao = UtilSBCoreArquivoTexto.getPropriedadesNoArquivo(getCaminhoArquivoReleaseImplantado());
            emAmbienteDeProducao = true;
        } else {
            emAmbienteDeProducao = false;
            if (temArquivoDesenvolvimento) {
                propriedadesImplantacao = UtilSBCoreArquivoTexto.getPropriedadesNoArquivo(getCaminhoArquivoReleaseLocal());
            }
        }

        if (propriedadesImplantacao == null) {
            throw new UnsupportedOperationException("Nenhum arquivo de implantação foi encontrado, isto não é importante se você não pretende implantar este projeto, caso prentenda certifique de configurar o arquivo" + getCaminhoArquivoReleaseLocal());
        }
        SERVIDOR_HOMOLOGACAO = propriedadesImplantacao.getProperty("SERVIDOR_HOMOLOGACAO");
        if (SERVIDOR_HOMOLOGACAO == null) {
            throw new UnsupportedOperationException("A propriedade SERVIDOR_HOMOLOGACAO não foi implementada");
        }

        SERVIDOR_REQUISITOS = propriedadesImplantacao.getProperty("SERVIDOR_REQUISITOS");

        PASTA_GIT_RELEASE = propriedadesImplantacao.getProperty("PASTA_GIT_RELEASE");
        if (PASTA_GIT_RELEASE == null) {
            throw new UnsupportedOperationException("A propriedade PASTA_GIT_RELEASE não foi implementada");
        }
        PASTA_GIT_SOURCE = propriedadesImplantacao.getProperty("PASTA_GIT_SOURCE");
        if (PASTA_GIT_SOURCE == null) {
            throw new UnsupportedOperationException("A propriedade PASTA_GIT_SOURCE não foi implementada");
        }
        NOME_PASTA_PROJETO = propriedadesImplantacao.getProperty("NOME_PASTA_PROJETO");
        if (NOME_PASTA_PROJETO == null) {
            throw new UnsupportedOperationException("A propriedade NOME_PASTA_PROJETO não foi implementada");
        }

    }

    private String getCaminhoArquivoReleaseImplantado() {
        return CAMINHO_PASTA_DISTRIBUICOES + "/" + configuracaoBase.getNOME_PROJETO() + "/" + configuracaoBase.getNOME_PROJETO() + ".info";
    }

    private String getCaminhoArquivoReleaseLocal() {
        return configuracaoBase.getCaminhoPastaProjetoRelease() + "/" + configuracaoBase.getNOME_PROJETO() + ".info";

    }

    private boolean checaArquivoReleaseLocal() {
        File arquivoRelease = new File(getCaminhoArquivoReleaseLocal());
        return arquivoRelease.exists();
    }

    private void checaArquivoReleaseImplantado() {
        File arquivoRelease = new File(getCaminhoArquivoReleaseImplantado());
        temArquivoImplantacao = arquivoRelease.exists();
    }

    public boolean isTemArquivoImplantacao() {
        return temArquivoImplantacao;
    }

    public boolean isTemArquivoDesenvolvimento() {
        return temArquivoDesenvolvimento;
    }

    public String getSERVIDOR_HOMOLOGACAO() {
        return SERVIDOR_HOMOLOGACAO;
    }

    public String getSERVIDOR_REQUISITOS() {
        return SERVIDOR_REQUISITOS;
    }

    public String getPASTA_GIT_RELEASE() {
        return PASTA_GIT_RELEASE;
    }

    public String getPASTA_GIT_SOURCE() {
        return PASTA_GIT_SOURCE;
    }

    public String getNOME_PASTA_PROJETO() {
        return NOME_PASTA_PROJETO;
    }

    public ArquivoConfiguracaoBase getConfiguracaoBase() {
        return configuracaoBase;
    }

    public boolean isEmAmbienteDeProducao() {
        return emAmbienteDeProducao;
    }
}
