/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoBase;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.CentralLogEventosArqTextoGenerica;

/**
 *
 *
 *
 * @author salvioF
 */
public interface ItfConfiguradorCore {

    public ItfConfiguracaoCoreSomenteLeitura getConfiguracaoCore(SBCore.ESTADO_APP pEstadoApp);

    public boolean isIgnorarConfiguracaoPermissoes();

    public boolean isIgnorarConfiguracaoAcoesDoSistema();

    /**
     *
     * @return
     */
    public ArquivoConfiguracaoBase getArquivoConfiguradorBase();

    /**
     *
     * @return
     */
    public ArquivoConfiguracaoCliente getArquivoConfiguradorCliente();

    /**
     *
     * @return
     */
    public ArquivoConfiguracaoDistribuicao getArquivoConfiguradorDistribuicao();

    /**
     *
     * @return
     */
    public boolean isTemArqConfiguracaoBase();

    /**
     *
     * @return
     */
    public boolean isTemArqConfiguracaoCliente();

    /**
     *
     * @return
     */
    public boolean isTemArqConfiguracaoDistrivuicao();

    public class UtilConfiguracaoCore {

        public static void setclassesPadraoJar(ItfConfiguracaoCoreCustomizavel pConfig) {
            pConfig.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
            pConfig.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
            pConfig.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
            pConfig.setControleDeSessao(ControleDeSessaoPadrao.class);
        }

    }

    public class UtilConfiguracaoCoreDesktopDeveloper {

        public static void setclassesPadraoJar(ItfConfiguracaoCoreCustomizavel pConfig) {
            pConfig.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
            pConfig.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
            pConfig.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
            pConfig.setControleDeSessao(ControleDeSessaoPadrao.class);
        }

    }

}
