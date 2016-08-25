/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.configAppp;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.configSBFW.acessos.ConfigAcessos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ControleDeSessaoPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguradorCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.CentralMensagemArqTexto;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;

import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.SBCore.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;
import com.super_bits.sbProjetos.controller.getaoProjeto.FabAcaoGestaoProjetos;

/**
 *
 * @author Salvio
 */
public class ConfigCoreInomeProjetoI implements ItfConfiguradorCore {

    @Override
    public Class<? extends ItfCentralMensagens> getCentralDeMensagens() {
        return CentralMensagemArqTexto.class;
    }

    @Override
    public Class<? extends ErroSBCoreFW> getClasseErro() {
        return ErroSBCoreFW.class;
    }

    @Override
    public Class<? extends ItfControleDeSessao> getControleDeSessao() {
        return ControleDeSessaoPadrao.class;
    }

    @Override
    public Class<? extends ItfCentralEventos> getCentralDeEventos() {
        return CentralLogEventosArqTextoGenerica.class;
    }

    @Override
    public String getNomeProjeto() {
        return "superBitsWPDemo";
    }

    @Override
    public String getDiretorioBase() {
        return "/";
    }

    @Override
    public String getCliente() {
        return "super_bits";
    }

    @Override
    public String getGrupoProjeto() {
        return "modulos";
    }

    @Override
    public Class<? extends ConfigPermissaoAbstratoSBCore> getConfigPermissoes() {
        return ConfigAcessos.class;
    }

    @Override
    public SBCore.ESTADO_APP getEstadoApp() {
        return SBCore.ESTADO_APP.DESENVOLVIMENTO;
    }

    @Override
    public Class<? extends ItfFabricaAcoes>[] getFabricaDeAcoes() {
        return new Class[]{FabAcaoGestaoProjetos.class};
    }

    @Override
    public String getUrlJira() {
        return "http://jira.projetoSB.consultoriaguiase.com.br1";
    }

}
