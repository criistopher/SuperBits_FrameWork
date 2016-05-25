/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package aux;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ControleDeSessaoPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguradorCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.CentramMensagemProgramadorMsgStop;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.webPaginas.util.CentralDeMensagensJSFAPP;

/**
 *
 * @author desenvolvedor
 */
public enum FabConfigCoreSBSBWebPaginas {

    DESENVOLVIMENTO, HOMOLOGACAO, PRODUCAO;

    public ItfConfiguradorCore getConfigurador() {

        ConfigCoreCustomizavel cfg = new ConfigCoreCustomizavel();
        cfg.setCliente("Super_Bits");
        cfg.setGrupoProjeto("SuperBits_FrameWork");
        cfg.setNomeProjeto("SBWebPaginas");
        cfg.setDiretorioBase("SB_FRAMEWORK");
        cfg.setCentralDeEventos(CentralLogEventosArqTextoGenerica.class);
        cfg.setCentralMEnsagens(CentralDeMensagensJSFAPP.class);
        cfg.setClasseErro(ErroSBCoreFW.class);
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
