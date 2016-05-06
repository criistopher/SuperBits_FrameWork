/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.configSBFW;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.ItfConfigSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguradorCore;

/**
 *
 * É Importante criar ambientes de execução diferentes de acordo com o estágio
 * de produção
 *
 * O sistema perimite alterar:  <br>
 * A classe responsável por envio de mensagens ao desenvolvedor, ao usuário, e
 * logs de sistema <br>
 * A Classe responsável por tratamento de erros A Classe responsável pela
 * configuração de acessos do sistema  <br>
 * A classe responsável por armazenamento de logs <br>
 *
 *
 *
 * @author desenvolvedor
 */
public enum FabConfiguracoesDeAmbienteModelExemplo {

    DESENVOLVIMENTO, HOMOLOGACAO, PRODUCAO;

    public ItfConfiguradorCore getConfiguracao() {
//        ConfigCoreCustomizavel configurador = new ConfigCoreCustomizavel();
        return null;
    }

    public ItfConfigSBPersistencia getConfiguracaoPersistencia() {
        return null;
    }

}
