/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package config;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.modulos.SBAcessosModel.ConfigPermissoesAcessoModelAbstrato;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguradorCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.logeventos.CentralLogEventosArqTextoGenerica;

/**
 *
 * @author desenvolvedor
 */
public enum FabConfiguracoesCoreAcessosModel {

    DESENVOLVIMENTO, HOMOLOGACAO, PRODUCAO;

    public ItfConfiguradorCore getConfigurador() {

        ConfigCoreCustomizavel cfg = new ConfigCoreCustomizavel();
        cfg.setCliente("vip");
        cfg.setGrupoProjeto("superkompras");
        cfg.setNomeProjeto("SB_FRAMEWORK");
        cfg.setDiretorioBase("/SuperBits_FrameWork");
        cfg.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
        cfg.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
        cfg.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
        cfg.setClasseConfigPermissao(ConfigPermissoesAcessosModel.class);

        switch (this) {
            case DESENVOLVIMENTO:
                cfg.setEstadoAPP(SBCore.ESTADO_APP.DESENVOLVIMENTO);
                cfg.setCentralMEnsagens(CentramMensagemProgramadorMsgStop.class);
                cfg.setClasseErro(ErroSBCoreDeveloperSopMessagem.class);
                break;
            case HOMOLOGACAO:
                cfg.setEstadoAPP(SBCore.ESTADO_APP.HOMOLOGACAO);
                break;
            case PRODUCAO:
                cfg.setEstadoAPP(SBCore.ESTADO_APP.PRODUCAO);
                break;
            default:
                throw new AssertionError(this.name());

        }
        return cfg;
    }

}
