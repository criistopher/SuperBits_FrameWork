/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.modulos.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.controller.FabAcaoTemporariaTestes;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.CentralLogEventosArqTextoGenerica;

/**
 *
 * @author desenvolvedor
 */
public class ConfiguradorProjetoSBCore extends ConfiguradorCoreDeProjetoJarAbstrato {

    public ConfiguradorProjetoSBCore() {
        super();
        setIgnorarConfiguracaoAcoesDoSistema(true);
        setIgnorarConfiguracaoPermissoes(true);
    }

    @Override
    public ItfConfiguracaoCoreSomenteLeitura getConfiguracaoCore(SBCore.ESTADO_APP pEstadoApp) {
        ItfConfiguracaoCoreCustomizavel cfg = (ItfConfiguracaoCoreCustomizavel) super.getConfiguracaoCore(pEstadoApp);
        cfg.setCliente("Super_Bits");
        cfg.setGrupoProjeto("SB_FRAMEWORK");
        cfg.setNomeProjeto("SBCore");
        cfg.setDiretorioBase("SuperBits_FrameWork");
        cfg.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
        cfg.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
        cfg.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
        cfg.setControleDeSessao(ControleDeSessaoPadrao.class);
        cfg.setUrlJira("https://vipsol.atlassian.net");
        cfg.setFabricaDeAcoes(new Class[]{FabAcaoTemporariaTestes.class});
//      cfg.setClasseConfigPermissao(ConfigPermissoesAcessosModel.class);

        cfg.setEstadoAPP(SBCore.ESTADO_APP.DESENVOLVIMENTO);
        cfg.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
        cfg.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
        return cfg;
    }

    @Override
    public void defineFabricasDeACao(ItfConfiguracaoCoreCustomizavel pConfig) {

    }

}
