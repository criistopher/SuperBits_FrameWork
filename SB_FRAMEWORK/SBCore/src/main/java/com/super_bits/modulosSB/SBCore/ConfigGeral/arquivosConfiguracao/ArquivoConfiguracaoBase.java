/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguradorCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreSomenteLeitura;

/**
 *
 * O arquivo de configuração base se encontra na pasta resource de cada projeto.
 *
 *
 *
 *
 *
 * @author salvioF
 */
public class ArquivoConfiguracaoBase {

    private String VERSAO;
    private String NOME_PROJETO;
    private String NOME_SOCIAL;
    private String TIPO_PROJETO;
    private String GRUPO_PROJETO;
    private String DIRETORIO_BASE;

    private String NOME_CLIENTE;

    public ArquivoConfiguracaoBase(ItfConfiguradorCore pConfigurador) throws UnsupportedOperationException, IOException {

        Properties proppriedadesBasicas = new Properties();

        String arquivoDeConfiguacao = "SBProjeto.prop";
        InputStream stream = pConfigurador.getClass().getClassLoader().getResourceAsStream(arquivoDeConfiguacao);
        if (stream == null) {
            throw new UnsupportedOperationException("Arquivo SBProjeto.prop não encontrado na pasta resources");
        }
        proppriedadesBasicas.load(stream);
        stream.close();
        VERSAO = (String) proppriedadesBasicas.get("VERSAO");
        if (VERSAO == null) {
            throw new UnsupportedOperationException("A versão do projeto não foi encontrada no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }
        NOME_PROJETO = (String) proppriedadesBasicas.get("NOME_PROJETO");
        if (NOME_PROJETO == null) {
            throw new UnsupportedOperationException("O nome  do projeto não foi encontrado no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }
        NOME_SOCIAL = (String) proppriedadesBasicas.get("NOME_SOCIAL");
        if (NOME_SOCIAL == null) {
            throw new UnsupportedOperationException("O nome social  do projeto não foi encontrado no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }
        TIPO_PROJETO = (String) proppriedadesBasicas.get("TIPO_PROJETO");
        if (TIPO_PROJETO == null) {
            throw new UnsupportedOperationException("O tipo do  do projeto ${pom.packaging} não foi encontrado no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }
        NOME_CLIENTE = (String) proppriedadesBasicas.get("NOME_CLIENTE");
        System.out.println("nome cliente" + NOME_CLIENTE);
        if (NOME_CLIENTE == null) {
            throw new UnsupportedOperationException("O nome do cliente não foi encontrado no projeto");
        }

        GRUPO_PROJETO = proppriedadesBasicas.getProperty("GRUPO_PROJETO");
        if (GRUPO_PROJETO == null) {
            throw new UnsupportedOperationException("O gropo do projeto não foi encontrado no ArquivoDeConfiguraçãoBase (SBProjeto)");
        }

        DIRETORIO_BASE = proppriedadesBasicas.getProperty("DIRETORIO_BASE");
        // diretorio base não é obrigatório...
    }

    public String getVERSAO() {
        return VERSAO;
    }

    public void setVERSAO(String VERSAO) {
        this.VERSAO = VERSAO;
    }

    public String getNOME_PROJETO() {
        return NOME_PROJETO;
    }

    public void setNOME_PROJETO(String NOME_PROJETO) {
        this.NOME_PROJETO = NOME_PROJETO;
    }

    public String getNOME_SOCIAL() {
        return NOME_SOCIAL;
    }

    public void setNOME_SOCIAL(String NOME_SOCIAL) {
        this.NOME_SOCIAL = NOME_SOCIAL;
    }

    public String getTIPO_PROJETO() {
        return TIPO_PROJETO;
    }

    public void setTIPO_PROJETO(String TIPO_PROJETO) {
        this.TIPO_PROJETO = TIPO_PROJETO;
    }

    public String getNOME_CLIENTE() {
        return NOME_CLIENTE;
    }

    public void setNOME_CLIENTE(String NOME_CLIENTE) {
        this.NOME_CLIENTE = NOME_CLIENTE;
    }

    public String getGRUPO_PROJETO() {
        return GRUPO_PROJETO;
    }

    public void setGRUPO_PROJETO(String GRUPO_PROJETO) {
        this.GRUPO_PROJETO = GRUPO_PROJETO;
    }

    public String getDIRETORIO_BASE() {
        return DIRETORIO_BASE;
    }

    public void setDIRETORIO_BASE(String DIRETORIO_BASE) {
        this.DIRETORIO_BASE = DIRETORIO_BASE;
    }

    public String getCaminhoPastaCliente() {
        return SBCore.CAMINHO_BASE_PROJETOS + "/" + getNOME_CLIENTE();
    }

    public String getCaminhoPastaClienteRelease() {
        return getCaminhoPastaCliente() + "/release";
    }

    public String getCaminhoPastaClienteSource() {
        return getCaminhoPastaCliente() + "/source";
    }

    public boolean isTemDiretorioBase() {
        if (DIRETORIO_BASE == null) {
            return false;
        }
        return DIRETORIO_BASE.length() >= 1;

    }

    public String getCaminhoPastaProjetoRelease() {
        if (isTemDiretorioBase()) {
            return getCaminhoPastaClienteRelease() + "/" + DIRETORIO_BASE + "/" + GRUPO_PROJETO + "/" + NOME_PROJETO;
        } else {
            return getCaminhoPastaClienteRelease() + "/" + GRUPO_PROJETO + "/" + NOME_PROJETO;
        }
    }

    public String getCaminhoPastaProjetoSource() {
        if (isTemDiretorioBase()) {
            return getCaminhoPastaClienteSource() + "/" + DIRETORIO_BASE + "/" + GRUPO_PROJETO + "/" + NOME_PROJETO;
        } else {
            return getCaminhoPastaClienteSource() + "/" + GRUPO_PROJETO + "/" + NOME_PROJETO;
        }
    }

}
