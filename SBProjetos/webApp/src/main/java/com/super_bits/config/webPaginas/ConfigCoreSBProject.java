/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.config.webPaginas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSB;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroSBCoreFW;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.CentralLogEventosArqTextoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.modulos.sessao.Interfaces.ItfControleDeSessao;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.util.CentralDeMensagensJSFAPP;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreSomenteLeitura;

/**
 *
 * @author Salvio
 */
public class ConfigCoreSBProject implements ItfConfiguracaoCoreSomenteLeitura {

    @Override
    public String getNomeProjeto() {
        return "SBProjectMonitor";
    }

    @Override
    public Class<? extends ErroSB> getClasseErro() {
        return ErroSBCoreFW.class;
    }

    @Override
    public SBCore.ESTADO_APP estadoApp() {
        return SBCore.ESTADO_APP.HOMOLOGACAO;
    }

    @Override
    public String getDiretorioBase() {
        return "/home/projetosSB/projetos/orcaSegFinal/src/main/webapp";
    }

    @Override
    public Class<? extends ItfCentralMensagens> getCentralDeMensagens() {
        return CentralDeMensagensJSFAPP.class;
    }

    @Override
    public Class<? extends ItfControleDeSessao> getControleDeSessao() {
        return ControleDeSessaoWeb.class;
    }

    @Override
    public String getCliente() {
        return "SuperBits";
    }

    @Override
    public String getGrupoProjeto() {
        return "SBProject";
    }

    @Override
    public Class<? extends ItfCentralEventos> getCentralDeEventos() {
        return CentralLogEventosArqTextoGenerica.class;
    }

}
