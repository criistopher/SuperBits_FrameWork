/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.logeventos.CentralLogEventosArqTextoGenerica;

/**
 *
 * @author desenvolvedor
 */
public enum FabConfigCoreSBCore {

    DESENVOLVIMENTO, HOMOLOGACAO, PRODUCAO;

    public ItfConfiguradorCore getConfigurador() {

        ConfigCoreCustomizavel cfg = new ConfigCoreCustomizavel();
        cfg.setCliente("Super_Bits");
        cfg.setGrupoProjeto("SB_FRAMEWORK");
        cfg.setNomeProjeto("SBCore");
        cfg.setDiretorioBase("SuperBits_FrameWork");
        cfg.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
        cfg.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
        cfg.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
        cfg.setControleDeSessao(ControleDeSessaoPadrao.class);

        //      cfg.setClasseConfigPermissao(ConfigPermissoesAcessosModel.class);
        switch (this) {
            case DESENVOLVIMENTO:
                cfg.setEstadoAPP(SBCore.ESTADO_APP.DESENVOLVIMENTO);
                cfg.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
                cfg.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
                break;
            case HOMOLOGACAO:
                throw new UnsupportedOperationException("O Core não é uma aplicação, portanto só pode ser executado em modo de desenvolvimento");

            case PRODUCAO:
                throw new UnsupportedOperationException("O Core não é uma aplicação, portanto só pode ser executado em modo de desenvolvimento");
            default:
                throw new AssertionError(this.name());

        }
        return cfg;
    }

}
