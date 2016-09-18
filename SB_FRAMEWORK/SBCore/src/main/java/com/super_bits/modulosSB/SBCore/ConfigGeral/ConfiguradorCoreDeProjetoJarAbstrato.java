/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoBase;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoCliente;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import java.io.IOException;

/**
 *
 * @author salvioF
 */
public abstract class ConfiguradorCoreDeProjetoJarAbstrato implements ItfConfiguradorCore {

    private boolean ignorarConfiguracaoPermissoes = false;
    private boolean ignorarConfiguracaoAcoesDoSistema = false;

    private ArquivoConfiguracaoBase arquivoConfiguracaoBase;
    private ArquivoConfiguracaoCliente arquivoCliente;
    private ArquivoConfiguracaoDistribuicao arquivoCOnfiguracaoDistribuicao;

    public abstract void defineFabricasDeACao(ItfConfiguracaoCoreCustomizavel pConfig);

    private void configurarImplantacao() {
        try {
            ArquivoConfiguracaoDistribuicao distro = new ArquivoConfiguracaoDistribuicao(arquivoConfiguracaoBase);
            arquivoCOnfiguracaoDistribuicao = distro;
            System.out.println(arquivoCOnfiguracaoDistribuicao);
        } catch (Throwable t) {
            SBCore.soutInfoDebug("O arquivo de distribuição não foi encontrado, você não precisa se preocupar com isso caso não pretenda executar este projeto fora do ambiente de desenvolvimento");
        }
    }

    public ConfiguradorCoreDeProjetoJarAbstrato() {
        try {
            arquivoConfiguracaoBase = new ArquivoConfiguracaoBase(this);
            arquivoCliente = new ArquivoConfiguracaoCliente(arquivoConfiguracaoBase);
            configurarImplantacao();
        } catch (UnsupportedOperationException | IOException ex) {
            System.out.println("SBCoreInfo: Erro lendo propriedade do arquivo de configuração ");
            System.out.println("SBCoreInfo: certifique que o arquivo SBProjeto.prop esteja na pasta src/main/resource do projeto, e que o seu arquivo pom contenha as tags de resources configuradas no build");
            System.out.println("SBCoreInfo: duvidas utilize o projeto SBCore como referencia");
            System.out.println("SBCoreInfo: *** Sem o arquivo de configuração basico, não é possível carregar o arquivo de configuração de produção");
        }
    }

    protected void configurarArquivosConfiguracao(ItfConfiguracaoCoreCustomizavel pConfigurador) {

        if (isTemArqConfiguracaoBase()) {
            pConfigurador.setNomeProjeto(getArquivoConfiguradorBase().getNOME_PROJETO());
            pConfigurador.setDiretorioBase(getArquivoConfiguradorBase().getDIRETORIO_BASE());
            pConfigurador.setCliente(getArquivoConfiguradorBase().getNOME_CLIENTE());
            pConfigurador.setGrupoProjeto(getArquivoConfiguradorBase().getGRUPO_PROJETO());
            pConfigurador.setNomeSocial(getArquivoConfiguradorBase().getNOME_SOCIAL());

        }
        if (isTemArqConfiguracaoCliente()) {

        }

        if (isTemArqConfiguracaoDistrivuicao()) {

        }
        defineFabricasDeACao(pConfigurador);

    }

    @Override
    public ItfConfiguracaoCoreSomenteLeitura getConfiguracaoCore(SBCore.ESTADO_APP pEstadoApp) {
        ConfigCoreCustomizavel configuracao = new ConfigCoreCustomizavel();
        configurarArquivosConfiguracao(configuracao);
        configuracao.setEstadoAPP(pEstadoApp);
        UtilConfiguracaoCore.setclassesPadraoJar(configuracao, pEstadoApp);

        return configuracao;
    }

    @Override
    public boolean isIgnorarConfiguracaoPermissoes() {
        return ignorarConfiguracaoPermissoes;
    }

    public final void setIgnorarConfiguracaoPermissoes(boolean ignorarConfiguracaoPermissoes) {
        this.ignorarConfiguracaoPermissoes = ignorarConfiguracaoPermissoes;
    }

    @Override
    public boolean isIgnorarConfiguracaoAcoesDoSistema() {
        return ignorarConfiguracaoAcoesDoSistema;
    }

    public final void setIgnorarConfiguracaoAcoesDoSistema(boolean ignorarConfiguracaoAcoesDoSistema) {
        this.ignorarConfiguracaoAcoesDoSistema = ignorarConfiguracaoAcoesDoSistema;
    }

    @Override
    public ArquivoConfiguracaoBase getArquivoConfiguradorBase() {
        return arquivoConfiguracaoBase;
    }

    @Override
    public ArquivoConfiguracaoCliente getArquivoConfiguradorCliente() {
        return arquivoCliente;
    }

    @Override
    public ArquivoConfiguracaoDistribuicao getArquivoConfiguradorDistribuicao() {
        return arquivoCOnfiguracaoDistribuicao;
    }

    @Override
    public boolean isTemArqConfiguracaoBase() {
        return arquivoConfiguracaoBase != null;
    }

    @Override
    public boolean isTemArqConfiguracaoCliente() {
        return arquivoCliente != null;
    }

    @Override
    public boolean isTemArqConfiguracaoDistrivuicao() {
        return arquivoCOnfiguracaoDistribuicao != null;
    }

}
