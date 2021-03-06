/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorCoreAbstrato;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ControleDeSessaoPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoBase;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.CentralComunicacaoDesktop;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.webPaginas.arquivosDoProjeto.CentralDeArquivosWebApp;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.util.CentralDeMensagensJSFAPP;
import com.super_bits.modulosSB.webPaginas.visualizacao.ServicoVisuaslizacaoWebResponsivo;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 *
 * @author salvioF
 */
public abstract class ConfiguradorCoreDeProjetoWebWarAbstrato extends ConfiguradorCoreAbstrato {

    public static ServletContext contextoDoServlet;

    public ConfiguradorCoreDeProjetoWebWarAbstrato(ServletContext contexto) {
        super(false);

        try {

            arquivoConfiguradorBase = new ArquivoConfiguracaoBase(getPropriedadesArquivoConfiguracaoWar(contexto));
            //O arquivo cliente não é nessessário em caso de distribuicao
            arquivoConfiguradorDistribuicao = new ArquivoConfiguracaoDistribuicao(arquivoConfiguradorBase);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "O Core não pôde ser configurado", t);
        }

    }

    public ConfiguradorCoreDeProjetoWebWarAbstrato(boolean modoDesenvolvimento) {

        super(modoDesenvolvimento);
    }

    protected final Properties getPropriedadesArquivoConfiguracaoWar(ServletContext contexto) {

        try {
            Properties pPropriedadesArquivoSBProjeto = new Properties();
            InputStream input = contexto.getResourceAsStream("/WEB-INF/classes/SBProjeto.prop");
            pPropriedadesArquivoSBProjeto.load(input);
            return pPropriedadesArquivoSBProjeto;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro tentando encontrar arquivo SBProjeto no war, certifique a conformidade dos parametros de buid\resources do seu pom, e a existencia do mesmo na pasta main/src/resourceresource do projeto", t);
            return null;
        }
    }

    @Override
    public void defineClassesBasicas(ItfConfiguracaoCoreCustomizavel pConfiguracao) {
        aplicarDadosArquivoConfiguracao(pConfiguracao);
        pConfiguracao.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
        pConfiguracao.setServicoVisualizacao(ServicoVisuaslizacaoWebResponsivo.class);
        pConfiguracao.setCentralDeArquivos(CentralDeArquivosWebApp.class);
        pConfiguracao.setCentralComunicacao(CentralComunicacaoDesktop.class);

        switch (pConfiguracao.getEstadoApp()) {
            case DESENVOLVIMENTO:
                pConfiguracao.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
                pConfiguracao.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
                pConfiguracao.setControleDeSessao(ControleDeSessaoPadrao.class);
                break;
            case PRODUCAO:
                pConfiguracao.setClasseErro(ErroSBCoreFW.class);
                pConfiguracao.setControleDeSessao(ControleDeSessaoWeb.class);
                pConfiguracao.setCentralMEnsagens(CentralDeMensagensJSFAPP.class);
                break;
            case HOMOLOGACAO:
                pConfiguracao.setCentralMEnsagens(CentralDeMensagensJSFAPP.class);
                pConfiguracao.setControleDeSessao(ControleDeSessaoWeb.class);
                pConfiguracao.setCentralMEnsagens(CentralDeMensagensJSFAPP.class);
                pConfiguracao.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
                break;
            default:
                throw new AssertionError(pConfiguracao.getEstadoApp().name());

        }
    }

}
