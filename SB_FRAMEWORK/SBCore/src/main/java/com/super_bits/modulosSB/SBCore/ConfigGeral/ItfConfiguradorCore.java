/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.Controller.Interfaces.permissoes.ItfCfgPermissoes;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.InfoErroSB;
import com.super_bits.modulosSB.SBCore.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;

/**
 *
 * Configurções genericas da Aplicação, como: nome, Status controle de usuários,
 * Controle de mensagem, e outros
 *
 *
 * @author Salvio
 */
public interface ItfConfiguradorCore {

    public Class<? extends ItfCentralMensagens> getCentralDeMensagens();

    /**
     * Classe responsável pelo tratamento de erros, e logs de sistema
     *
     * @return Classe responsável pelo tratamento de erros, e logs de sistema
     */
    public Class<? extends InfoErroSB> getClasseErro();

    /**
     *
     * Classe responsável pelo controle de sessões e autenticação
     *
     * @return Classe responsável pelo controle de sessões e autenticação
     */
    public Class<? extends ItfControleDeSessao> getControleDeSessao();

    /**
     * Classe responsável por configurar as permissões de acesso do sistema
     *
     * @return Classe responsável por configurar as permissões de acesso do
     * sistema
     */
    public Class<? extends ConfigPermissaoAbstratoSBCore> getConfigPermissoes();

    /**
     * Classe responsável por registrar os logs de eventos do sistema.
     *
     * @return
     */
    public Class<? extends ItfCentralEventos> getCentralDeEventos();

    /**
     *
     * Nome do Projeto (utilizado em pastas locais de desenvolvimento e
     * repositórios)
     *
     * @return Nome do Projeto (utilizado em pastas locais de desenvolvimento e
     * repositórios)
     */
    public String getNomeProjeto();

    /**
     * Status do desenvolvimento que pode ser DESENVOLVIMENTO, PRODUCAO ou
     * TESTES
     *
     * @return Status do desenvolvimento que pode ser DESENVOLVIMENTO, PRODUCAO
     * ou TESTES
     */
    public SBCore.ESTADO_APP getEstadoApp();

    /**
     * Diretorio onde o arquivo Jar ou War está localizado
     *
     * @return
     */
    public String getDiretorioBase();

    /**
     * Nome Simples do cliente (utilizado em pastas locais, e repositórios)
     *
     * @return Nome Simples do cliente (utilizado em pastas locais, e
     * repositórios)
     */
    public String getCliente();

    /**
     * Nome do grupo, caso o projeto seja parte de um grupo (utilizado em pastas
     * locais, e repositórios)
     *
     * @return Nome do grupo, caso o projeto seja parte de um grupo (utilizado
     * em pastas locais, e repositórios)
     */
    public String getGrupoProjeto();

}
