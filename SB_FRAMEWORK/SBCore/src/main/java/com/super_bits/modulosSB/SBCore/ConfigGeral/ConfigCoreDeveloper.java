
/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.CentramMensagemProgramadorMsgThread;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.InfoErroSB;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.SBCore.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;

/**
 *
 * @author Salvio
 */
public class ConfigCoreDeveloper implements ItfConfiguradorCore {

    @Override
    public Class<? extends ItfCentralMensagens> getCentralDeMensagens() {
        return CentramMensagemProgramadorMsgThread.class;
    }

    @Override
    public Class<? extends InfoErroSB> getClasseErro() {
        return ErroSBCoreDeveloperSopMessagem.class;
    }

    @Override
    public Class<? extends ItfControleDeSessao> getControleDeSessao() {
        return ControleDeSessaoPadrao.class;
    }

    @Override
    public String getNomeProjeto() {
        return "Projeto Super-Bits Generico";
    }

    @Override
    public SBCore.ESTADO_APP getEstadoApp() {
        return SBCore.ESTADO_APP.DESENVOLVIMENTO;
    }

    @Override
    public String getDiretorioBase() {
        return "/";
    }

    @Override
    public String getCliente() {
        return "SuperBits";
    }

    @Override
    public String getGrupoProjeto() {
        return "";
    }

    @Override
    public Class<? extends ItfCentralEventos> getCentralDeEventos() {
        return CentralLogEventosArqTextoGenerica.class;
    }

    @Override
    public Class<? extends ConfigPermissaoAbstratoSBCore> getConfigPermissoes() {
        return null;
    }

    @Override
    public Class<? extends ItfFabricaAcoes>[] getFabricaDeAcoes() {
        return new Class[]{};
    }

}
