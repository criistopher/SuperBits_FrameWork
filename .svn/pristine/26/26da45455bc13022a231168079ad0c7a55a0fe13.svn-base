/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.Mensagens.CentralMensagemArqTexto;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.InfoErroSB;
import com.super_bits.modulosSB.SBCore.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.SBCore.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.sessao.ControleDeSessaoAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;

/**
 *
 * Implementação de configuração de core padrão
 *
 * Implementa envio de mensagem em arquivo log Controle de sessao
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 25/05/2014
 *
 */
public class ConfigCorePadrao implements ItfConfiguradorCore {

    private Class<? extends InfoErroSB> classeErro;
    private Class<? extends ItfCentralMensagens> classeCentralMensagens;
    private Class<? extends ControleDeSessaoAbstratoSBCore> classeControleSessao;

    public ConfigCorePadrao() {
        classeCentralMensagens = CentralMensagemArqTexto.class;
        classeControleSessao = ControleDeSessaoAbstratoSBCore.class;
        classeErro = ErroSBCoreFW.class;

    }

    @Override
    public Class<? extends ItfCentralMensagens> getCentralDeMensagens() {
        return CentralMensagemArqTexto.class;
    }

    @Override
    public Class<? extends InfoErroSB> getClasseErro() {
        return ErroSBCoreFW.class;
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
    public SBCore.ESTADO_APP estadoApp() {
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

}
