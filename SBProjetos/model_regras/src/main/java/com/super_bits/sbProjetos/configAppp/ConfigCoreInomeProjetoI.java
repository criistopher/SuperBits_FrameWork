/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.configAppp;

import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.configSBFW.acessos.ConfigAcessos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ControleDeSessaoPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.CentralMensagemArqTexto;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfCentralMensagens;

import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.modulos.sessao.Interfaces.ItfControleDeSessao;
import com.super_bits.sbProjetos.controller.getaoProjeto.FabAcaoGestaoProjetos;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreSomenteLeitura;

/**
 *
 * @author Salvio
 */
public class ConfigCoreInomeProjetoI implements ItfConfiguracaoCoreSomenteLeitura {

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
    public Class<? extends ConfigPermissaoSBCoreAbstrato> getConfigPermissoes() {
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
