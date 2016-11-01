/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoBase;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoCliente;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ArquivoConfiguracaoDistribuicao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.ConfigPermissaoSBCoreAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfCfgPermissoes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ItfInfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.modulos.sessao.Interfaces.ItfControleDeSessao;
import com.super_bits.modulosSB.SBCore.modulos.view.ItfServicoVisualizacao;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ItfCentralComunicacao;

/**
 *
 * Classe que retorna as configurações basicas de sistema como:
 *
 * Estado da aplicação (Desenvolvimento, Testes ou Produção) Nome do projeto
 * Diretorio de Instação
 *
 * possui métodos estaticos mais básicos da aplicação como:
 *
 * LancarErro EnviarMensagem Metodos para Coletar Informações de Runtime
 *
 * @author Sálvio Furbino <salviof@gmail.com>
 * @since 24/05/2014
 *
 */
public class SBCore {

    public static String CAMINHO_BASE_DESENVOLVIMENTO = "/home/superBits";
    public static String CAMINHO_BASE_PROJETOS = "/home/superBits/projetos";

    private static boolean ambienteExecucaoConfigurado = false;

    private static ESTADO_APP estadoAplicativo;
    private static boolean ignorarConfigurcoesDePermissao = false;
    private static boolean ignorarConfigurcoesDeAcoes = false;

    private static ItfCfgPermissoes configuradorDePermissao;
    private static final Map<String, ItfFabricaAcoes> ENUMACAO_BY_NOMEUNICO = new HashMap<>();

    /**
     * INDICA O ESTADO DA APLICAÇÃO: DESENVOLVIMENTO, HOMOLOGACAO, E PRODUÇÃO
     */
    public static enum ESTADO_APP {

        DESENVOLVIMENTO, PRODUCAO, HOMOLOGACAO;

    }

    private static enum TIPO_APLICATIVO {
        JAR_MODELAGEM_REGRAS,
        WEB_SERVICE,
        JAR_WEB_SERVICE_CLIENTE,
        WEB_PAGINAS,
        APP_DESKTOP,
        APP_MOBILE;
    }

    private static ItfConfiguracaoCoreSomenteLeitura infoAplicacao;
    private static ArquivoConfiguracaoBase arquivoConfigBase;
    private static ArquivoConfiguracaoCliente arquivoConfigCliente;
    private static ArquivoConfiguracaoDistribuicao arquivoConfigDistribuicao;
    private static ItfServicoVisualizacao servicoVisualizacao;
    private static ItfCentralDeArquivos centralDeArquivos;
    private static ItfCentralComunicacao centralComunicacao;

    public static boolean isEmModoDesenvolvimento() {

        return getEstadoAPP().equals(ESTADO_APP.DESENVOLVIMENTO);
    }

    public static ItfConfiguracaoCoreSomenteLeitura getInfoAplicacao() {
        return infoAplicacao;
    }

    public static ArquivoConfiguracaoDistribuicao getArquivoDistribuicao() {
        return arquivoConfigDistribuicao;
    }

    private static boolean isAmbienteExecucaoConfigurado() {
        return ambienteExecucaoConfigurado;
    }

    public static ItfServicoVisualizacao getCentralVisualizacao() {
        return servicoVisualizacao;
    }

    private static void fecharSistemaCasoNaoCOnfigurado() {
        if (ambienteExecucaoConfigurado) {
            return;
        }
        soutInfoDebug("É nescessário executar a configuração do core, antes de proceguir ;)");
        soutInfoDebug("Para configurar o core basta chamar o método SBCore.configurar(configurador) ");
        soutInfoDebug("Obs: A classe configurador deve se encontrar dentro do pacote principal do projeto");
        soutInfoDebug("na pasta resources do jar principal deve ser criado o arquivo SBProjeto.prop");
        System.exit(0);

    }

    private static boolean validaConfiguracoes() {
        try {

            if (infoAplicacao == null) {
                throw new UnsupportedOperationException("A aplicação  não foi configurado");
            }

            if (UtilSBCoreStrings.isNuloOuEmbranco(infoAplicacao.getNomeProjeto())) {
                throw new UnsupportedOperationException("O nome do projeto não foi configurado");
            }

            if (UtilSBCoreStrings.isNuloOuEmbranco(infoAplicacao.getCliente())) {
                throw new UnsupportedOperationException("O cliente não foi configurado");
            }
            //     SBCore.enviarAvisoAoUsuario(SBCore.getCaminhoDesenvolvimento());
            if (infoAplicacao.getCentralDeMensagens() == null) {
                throw new UnsupportedOperationException("Central de mensagens não configurada");

            }
            if (infoAplicacao.getEstadoApp() == null) {
                throw new UnsupportedOperationException("Estado do aplicativo não configurado");

            }
            if (infoAplicacao.getClasseErro() == null) {
                throw new UnsupportedOperationException("Classe Erro não configurada");

            }
            if (infoAplicacao.getDiretorioBase() == null) {
                throw new UnsupportedOperationException("Diretorio base não configurado");

            }
            if (estadoAplicativo == ESTADO_APP.DESENVOLVIMENTO) {
                File pastaTemp = new File("/home/developer/temp/servlet");
                pastaTemp.mkdirs();
            }

            if (!ignorarConfigurcoesDePermissao) {
                if (infoAplicacao.getFabricaDeAcoes() == null) {
                    throw new UnsupportedOperationException("As Açoes do Sistema não foram configuradas");
                }
                MapaAcoesSistema.makeMapaAcoesSistema();

                try {
                    // Caso a classe não tenha sido definida na mão, utilizando primeira classe encontrada que extenda ConfigPermissaoSBCoreAbstrato
                    if (infoAplicacao.getConfigPermissoes() == null) {
                        Class configPermissao = UtilSBCoreReflexao.getClasseQueEstendeIsto(ConfigPermissaoSBCoreAbstrato.class, "com.super_bits.configSBFW.acessos");
                        if (configPermissao == null) {
                            throw new UnsupportedOperationException("A classe que configura permissão não foi encontrada, crie uma classe que implemente config permssaoSBcore, ou altere a configuração do core dispensando as configurações de permissão");
                        }
                        configuradorDePermissao = (ItfCfgPermissoes) configPermissao.newInstance();
                    } else {
                        configuradorDePermissao = (ItfCfgPermissoes) infoAplicacao.getConfigPermissoes().newInstance();
                    }

                } catch (InstantiationException | IllegalAccessException t) {
                    if (ignorarConfigurcoesDePermissao) {
                        System.out.println("A Classe de permissões não foi definida");
                    } else {
                        throw new UnsupportedOperationException("Erro tentando encontrar responsavel pela permissao, extenda ao menos uma classe com ConfigPermissaoAbstratoSBCore no sistema, ou utilize o parametro ignorar Classe de permissão neste método ", t);
                    }

                }

            }

            if (estadoAplicativo == ESTADO_APP.DESENVOLVIMENTO) {
                String arquivoPomDoProjeto = SBCore.getCaminhoDesenvolvimento() + "/pom.xml";
                if (!UtilSBCoreArquivos.isArquivoExiste(arquivoPomDoProjeto)) {

                    throw new UnsupportedOperationException("O arquivo pom não foi encontrado em " + SBCore.getCaminhoDesenvolvimento());
                }
            }
            System.out.println("COnfigurando Mapa de Ações do sistema");
            if (!ignorarConfigurcoesDeAcoes) {

                if (MapaAcoesSistema.isMapaCriado()) {
                    System.out.println("Mapa de ações criado com sucesso!");
                }
            }
            ambienteExecucaoConfigurado = true;
            return true;
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro validação de configurações do projeto (SBCore)", t);

            ambienteExecucaoConfigurado = false;
            return false;
        }
    }

    public static boolean isControleDeAcessoDefinido() {
        return infoAplicacao.getConfigPermissoes() != null;

    }

    /**
     *
     *
     *
     * @param configurador
     * @param pEstado
     *
     */
    public static void configurar(ItfConfiguradorCore configurador, ESTADO_APP pEstado) {

        try {

            if (ambienteExecucaoConfigurado) {
                //throw new UnsupportedOperationException("A configuração do ambiente de execução só pode ser realizada uma vez");
                System.out.println("ATENÇÃO, OCORREU UMA TENTATIVA DUPLA  DE CONFIGURAR O CORE, ESTA AÇÃO IRÁ GERAR UMA ERRO PARA_TUDO NAS PROXIMAS VERSÕES");
                // return;
                return;
            }
            ignorarConfigurcoesDeAcoes = configurador.isIgnorarConfiguracaoAcoesDoSistema();
            ignorarConfigurcoesDePermissao = configurador.isIgnorarConfiguracaoPermissoes();
            ItfConfiguracaoCoreSomenteLeitura configuracoes = configurador.getConfiguracaoCore(pEstado);
            infoAplicacao = configuracoes;
            estadoAplicativo = configuracoes.getEstadoApp();
            arquivoConfigBase = configurador.getArquivoConfiguradorBase();
            arquivoConfigCliente = configurador.getArquivoConfiguradorCliente();
            arquivoConfigDistribuicao = configurador.getArquivoConfiguradorDistribuicao();
            servicoVisualizacao = configuracoes.getServicoVisualizacao().newInstance();
            centralComunicacao = configuracoes.getCentralComunicacao().newInstance();
            ambienteExecucaoConfigurado = validaConfiguracoes();
            centralDeArquivos = configuracoes.getCentralDeArquivo().newInstance();

            if (!ambienteExecucaoConfigurado) {
                throw new UnsupportedOperationException("O core não pôde determinar as configurações básicas");
            }

        } catch (Throwable t) {
            ambienteExecucaoConfigurado = true;
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro configurando o Core" + t.getMessage(), t);
            FabErro.PARA_TUDO.paraSistema("Erro configurando o Core" + t.getMessage(), t);
        }

    }

    public static ESTADO_APP getEstadoAPP() {
        //    ValidaConfigurado(); Retirado para permitir consulta de estado da aplicação ao InfoErroSBComAcoes
        // o diretorio do aplicativo o mesmo que o servet
        return estadoAplicativo;
    }

    public static String getNomeProjeto() {
        fecharSistemaCasoNaoCOnfigurado();
        return infoAplicacao.getNomeProjeto();
    }

    public static String getDiretorioBase() {
        return infoAplicacao.getDiretorioBase();
    }

    /**
     *
     *
     *
     *
     * @param pTipoErro Programador: em desenvolvimento faz sout e se possivel
     * mostra na tela, em testes salva em log, em produção não faz nada Usuario:
     * Mostra na tela o erro. ParaTudo: Interrompe a execução do sistema
     * @param pMensagem Mensagem que será exibida
     * @param pErroJava O exception que gerou esse relato de erro
     */
    public static void RelatarErro(FabErro pTipoErro, String pMensagem, Throwable pErroJava) {

        if (!isAmbienteExecucaoConfigurado()) {

            soutInfoDebug("O sistema encontrou um erro antes de configurar a classe que lida com erros");
            soutInfoDebug("O erro encontrado foi:" + pMensagem);
            soutInfoDebug(pErroJava.getMessage());
            soutInfoDebug(pErroJava.getLocalizedMessage());
            pErroJava.printStackTrace();
            fecharSistemaCasoNaoCOnfigurado();
        }

        try {
            if (infoAplicacao.getClasseErro() == null) {
                System.out.println("a classe de erro não foi definida no core, utilizando classe de erro padrao");
                fecharSistemaCasoNaoCOnfigurado();
            }
            ItfInfoErroSBComAcoes erro = (InfoErroSBComAcoes) infoAplicacao.getClasseErro().newInstance();

            erro.configurar(FabMensagens.ERRO.getMsgDesenvolvedor(pMensagem), pTipoErro, pErroJava);

            erro.executarErro();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Erro Criando objeto ErrroSB erro" + ex.getMessage());
        }
    }

    /**
     *
     *
     *
     *
     * @param pTipoErro Programador: em desenvolvimento faz sout e se possivel
     * mostra na tela, em testes salva em log, em produção não faz nada Usuario:
     * Mostra na tela o erro. ParaTudo: Interrompe a execução do sistema
     * @param pMensagem Mensagem que será exibida
     * @param pErroJava O exception que gerou esse relato de erro
     */
    public static void RelatarErroAoUsuario(FabErro pTipoErro, String pMensagem, Throwable pErroJava) {
        fecharSistemaCasoNaoCOnfigurado();
        try {
            if (infoAplicacao.getClasseErro() == null) {
                System.out.println("a classe de erro não foi definida no core, utilizando classe de erro padrao");

            }
            ItfInfoErroSBComAcoes erro = (InfoErroSBComAcoes) infoAplicacao.getClasseErro().newInstance();

            erro.configurar(FabMensagens.ERRO.getMsgUsuario(pMensagem), pTipoErro, pErroJava);

            erro.executarErro();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Erro Criando objeto ErrroSB erro" + ex.getMessage());
        }
    }

    public static ItfCentralMensagens getCentralDeMensagens() {
        try {
            return infoAplicacao.getCentralDeMensagens().newInstance();
        } catch (Throwable ex) {

            FabErro.PARA_TUDO.paraSistema("ERRO CRIANDO CENTRAL DE MENSAGENS", ex);
        }
        return null;
    }

    public static ItfCentralEventos getCentralDeEventos() {

        try {
            return infoAplicacao.getCentralDeEventos().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            FabErro.PARA_TUDO.paraSistema("ERRO CRIANDO CENTRAL DE EVENTOS", ex);
        }
        return null;

    }

    public static boolean isPermitido(ItfAcaoDoSistema pAcao) {
        return ControllerAppAbstratoSBCore.isAcessoPermitido(pAcao);
    }

    public static ItfControleDeSessao getControleDeSessao() {
        try {
            return infoAplicacao.getControleDeSessao().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            FabErro.PARA_TUDO.paraSistema("Erro Criando controle de Sessao", ex);
        }
        return null;
    }

    public static ItfCfgPermissoes getConfiguradorDePermissao() {
        if (configuradorDePermissao == null) {
            FabErro.PARA_TUDO.paraSistema("Configurador de permissão não foi definido", null);
        }
        return configuradorDePermissao;
    }

    public static String getCaminhoGrupoProjetoSource() {

        String caminho = "";
        boolean temCaminhoDiretorioBase = UtilSBCoreStrings.isNAO_NuloNemBranco(getDiretorioBase());
        boolean temCaminhoGrupoProjeto = UtilSBCoreStrings.isNAO_NuloNemBranco(getGrupoProjeto());

        caminho = arquivoConfigBase.getCaminhoPastaClienteSource();

        if (temCaminhoDiretorioBase) {
            caminho = caminho + "/" + infoAplicacao.getDiretorioBase();
        }

        if (!temCaminhoGrupoProjeto) {
            return caminho + "/" + infoAplicacao.getNomeProjeto();
        } else {
            return caminho + "/" + getGrupoProjeto();
        }
    }

    public static String getCaminhoDesenvolvimento() {

        return arquivoConfigBase.getCaminhoPastaProjetoSource();

    }

    public static String getGrupoProjeto() {
        return infoAplicacao.getGrupoProjeto();
    }

    // Atalhos de acesso
    public static void enviarMensagemUsuario(String pMensagem, FabMensagens pTipoMensagem) {

        getCentralDeMensagens().enviaMensagem(pTipoMensagem.getMsgUsuario(pMensagem));
    }

    public static void enviarAvisoAoUsuario(String pMensagem) {

        getCentralDeMensagens().enviaMensagem(FabMensagens.AVISO.getMsgUsuario(pMensagem));
    }

    /**
     *
     * Atalho para SBCore.getControleDeSessao.getsessaoAtual.getusuarioLogado;
     *
     * @return
     */
    public static ItfUsuario getUsuarioLogado() {
        return getControleDeSessao().getSessaoAtual().getUsuario();
    }

    private static void makeEnumByNomeUnico() {

        ENUMACAO_BY_NOMEUNICO.clear();

        for (Class fabrica : infoAplicacao.getFabricaDeAcoes()) {

            for (Object objAcao : fabrica.getEnumConstants()) {
                ItfFabricaAcoes acao = (ItfFabricaAcoes) objAcao;
                ENUMACAO_BY_NOMEUNICO.put(UtilSBController.gerarNomeUnicoAcaoDoSistema(acao), acao);
            }

        }

    }

    public static ItfFabricaAcoes getFabricaByNOME_UNICO(String pNomeUnico) {
        try {
            if (pNomeUnico == null) {
                throw new UnsupportedOperationException("Tebtativa de obter a fabrica de ação com parametro nulo");
            }
            if ("".equals(pNomeUnico)) {
                throw new UnsupportedOperationException("Tebtativa de obter a fabrica de ação com parametro nulo");
            }

            if (ENUMACAO_BY_NOMEUNICO.isEmpty()) {
                makeEnumByNomeUnico();
            }

            ItfFabricaAcoes acao = ENUMACAO_BY_NOMEUNICO.get(pNomeUnico);
            if (acao == null) {
                throw new UnsupportedOperationException("A ação do sistema não foi encontrada pelo nome único " + pNomeUnico);
            }
            return acao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo fabrica por nome único", t);
        }
        return null;

    }

    public static Class<? extends ItfFabricaAcoes>[] getFabricasDeAcaoDoSistema() {
        return infoAplicacao.getFabricaDeAcoes();
    }

    public static String getUrlJira() {
        return infoAplicacao.getUrlJira();
    }

    public static void soutInfoDebug(String pInfo) {
        if (estadoAplicativo != ESTADO_APP.PRODUCAO) {
            System.out.println("SBCoreInfo:" + pInfo);
        }
    }

    public static ItfCentralDeArquivos getCentralDeArquivos() {
        return centralDeArquivos;
    }

    public static ItfCentralComunicacao getCentralComunicacao() {
        return centralComunicacao;
    }

}
