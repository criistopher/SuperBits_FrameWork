/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfCfgPermissoes;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ErroSBCoreDeveloperSopMessagem;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.InfoErroSBComAcoes;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ItfInfoErroSB;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.ItfInfoErroSBComAcoes;

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

    /**
     * INDICA O ESTADO DA APLICAÇÃO: DESENVOLVIMENTO, HOMOLOGACAO, E PRODUÇÃO
     */
    public static enum ESTADO_APP {

        DESENVOLVIMENTO, PRODUCAO, HOMOLOGACAO
    }

    private static ESTADO_APP estadoAplicativo;
    private static boolean ambienteExecucaoConfigurado = false;
    private static boolean informacoesProjetoConfigurado = false;
    private static Class<? extends InfoErroSBComAcoes> classeErro;
    private static Class<? extends ItfCentralMensagens> centralMensagens;
    private static Class<? extends ItfControleDeSessao> controleDeSessao;
    private static Class<? extends ItfCentralEventos> centralEventos;
    private static String diretorioBase;
    private static String nomeProjeto;
    private static Class<? extends ItfCfgPermissoes> classeConfigPermissoes;
    private static ItfCfgPermissoes configuradorDePermissao;
    private static String cliente = "SuperBits";
    private static String grupoProjeto = "";
    private static boolean controleDeAcessosDefinido;
    private static final Map<String, ItfFabricaAcoes> ENUMACAO_BY_NOMEUNICO = new HashMap<>();
    private static Class<? extends ItfFabricaAcoes>[] acoesDoSistema;
    private static String urlJira;

    private static void ValidaConfigurado() {
        if (ambienteExecucaoConfigurado) {
            return;
        }

        FabErro.PARA_TUDO.paraSistema("A configuração do Core não foi definido,  defina primento através do SBCore.configurar", null);
        try {
            throw new UnsupportedOperationException("CONFIG DO CORE NAO DEFINIDO");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);

    }

    public static String getCliente() {
        return cliente;
    }

    public static boolean isControleDeAcessoDefinido() {
        return controleDeAcessosDefinido;
    }

    public static void configurar(ItfConfiguradorCore configuracoes) {

        if (ambienteExecucaoConfigurado) {
            System.out.println("Ocorreu uma tentativa de reconfigurar o core");
            return;
            //throw new UnsupportedOperationException("A configuração do core só pode ser executada uma única vez");
        }

        configurar(configuracoes, false);
    }

    /**
     *
     *
     *
     * @param configuracoes Confugração do ambiente
     * @param pIgnorarClassePermissao Deve ignorar a configuração relativa a
     * permissoes?
     */
    public static void configurar(ItfConfiguradorCore configuracoes, boolean pIgnorarClassePermissao) {
        try {

            if (ambienteExecucaoConfigurado) {
                //throw new UnsupportedOperationException("A configuração do ambiente de execução só pode ser realizada uma vez");
                System.out.println("ATENÇÃO, OCORREU UMA TENTATIVA DUPLA  DE CONFIGURAR O CORE, ESTA AÇÃO IRÁ GERAR UMA ERRO PARA_TUDO NAS PROXIMAS VERSÕES");
                // return;
            }

            estadoAplicativo = configuracoes.getEstadoApp();
            centralMensagens = configuracoes.getCentralDeMensagens();
            classeErro = configuracoes.getClasseErro();
            diretorioBase = configuracoes.getDiretorioBase();
            nomeProjeto = configuracoes.getNomeProjeto();
            controleDeSessao = configuracoes.getControleDeSessao();
            grupoProjeto = configuracoes.getGrupoProjeto();
            cliente = configuracoes.getCliente();
            // central de eventos e config de permissao podem ser definidos logo após a chamada do configurar
            ambienteExecucaoConfigurado = true;
            acoesDoSistema = configuracoes.getFabricaDeAcoes();
            centralEventos = configuracoes.getCentralDeEventos();
            classeConfigPermissoes = configuracoes.getConfigPermissoes();
            acoesDoSistema = configuracoes.getFabricaDeAcoes();
            urlJira = configuracoes.getUrlJira();

            //     SBCore.enviarAvisoAoUsuario(SBCore.getCaminhoDesenvolvimento());
            if (centralMensagens == null) {
                throw new UnsupportedOperationException("Central de mensagens não configurada");

            }
            if (estadoAplicativo == null) {
                throw new UnsupportedOperationException("Estado do aplicativo não configurado");

            }
            if (classeErro == null) {
                throw new UnsupportedOperationException("Classe Erro não configurada");

            }
            if (diretorioBase == null) {
                throw new UnsupportedOperationException("Diretorio base não configurado");

            }
            if (estadoAplicativo == ESTADO_APP.DESENVOLVIMENTO) {
                File pastaTemp = new File("/home/developer/temp/servlet");
                pastaTemp.mkdirs();
            }

            if (!pIgnorarClassePermissao) {
                if (acoesDoSistema == null) {
                    throw new UnsupportedOperationException("As Açoes do Sistema não foram configuradas");
                }
                MapaAcoesSistema.makeMapaAcoesSistema();

                try {
                    // Definindo configuração de acessos
                    if (classeConfigPermissoes == null) {
                        Class configPermissao = UtilSBCoreReflexao.getClasseQueEstendeIsto(ConfigPermissaoAbstratoSBCore.class, "com.super_bits.configSBFW.acessos");
                        configuradorDePermissao = (ItfCfgPermissoes) configPermissao.newInstance();
                    } else {
                        configuradorDePermissao = (ItfCfgPermissoes) classeConfigPermissoes.newInstance();
                    }

                } catch (Throwable t) {
                    if (pIgnorarClassePermissao) {
                        System.out.println("A Classe de permissões não foi definida");
                    } else {
                        throw new UnsupportedOperationException("Erro tentando encontrar responsavel pela permissao, extenda ao menos uma classe com ConfigPermissaoAbstratoSBCore no sistema, ou utilize o parametro ignorar Classe de permissão neste método ", t);
                    }

                }

                informacoesProjetoConfigurado = true;
            }

            if (estadoAplicativo == ESTADO_APP.DESENVOLVIMENTO) {
                String arquivoPomDoProjeto = SBCore.getCaminhoDesenvolvimento() + "/pom.xml";
                if (!UtilSBCoreArquivos.isArquivoExiste(arquivoPomDoProjeto)) {

                    throw new UnsupportedOperationException("O arquivo pom não foi encontrado em " + SBCore.getCaminhoDesenvolvimento());
                }
            }
            System.out.println("COnfigurando Mapa de Ações do sistema");
            if (!pIgnorarClassePermissao) {

                if (MapaAcoesSistema.isMapaCriado()) {
                    System.out.println("Mapa de ações criado com sucesso!");
                }
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
        ValidaConfigurado();
        return nomeProjeto;
    }

    public static String getDiretorioBase() {
        return diretorioBase;
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
        ValidaConfigurado();
        try {
            if (classeErro == null) {
                System.out.println("a classe de erro não foi definida no core, utilizando classe de erro padrao");
                classeErro = ErroSBCoreDeveloperSopMessagem.class;
            }
            ItfInfoErroSBComAcoes erro = (InfoErroSBComAcoes) classeErro.newInstance();

            erro.configurar(FabMensagens.ERRO.getMsgDesenvolvedor(pMensagem), pTipoErro, pErroJava);

            erro.executarErro();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Erro Criando objeto ErrroSB erro" + ex.getMessage());
        }
    }

    public synchronized static void salvaInfoThread(String pPastaArquivoLog, boolean milesegundos) {

        Runtime runtime = Runtime.getRuntime();

        NumberFormat format = NumberFormat.getInstance();

        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        SimpleDateFormat dateformat = null;
        if (milesegundos) {
            dateformat = new SimpleDateFormat("dd-mm-ss-SSS");
        } else {
            dateformat = new SimpleDateFormat("dd-mm-ss");
        }

        String dia = dateformat.format(new Date());

        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
        Map<String, Integer> totais = new HashMap<>();
        Map<String, Integer> totaisMetodos = new HashMap<>();
        Map<String, Integer> totaisWait = new HashMap<>();
        System.out.println("TOTAL THREADS:" + Thread.activeCount());
        System.out.println("AllStack" + threads.size());
        System.out.println("@@@@TESTE:::");
        String caminhoArquivoLog = pPastaArquivoLog + "/" + dia + ".log";
        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "Quantidade total Threads:" + Thread.activeCount());

        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");

        for (Thread tr : threads.keySet()) {

            UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "DESCRIÇÃO DA THREAD ATIVA:");
            UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, tr.getClass().getSimpleName());
            Integer quantidade = totais.get(tr.getClass().getSimpleName());

            if (quantidade == null) {
                totais.put(tr.getClass().getSimpleName(), 1);
            } else {
                totais.put(tr.getClass().getSimpleName(), quantidade + 1);
            }

            for (StackTraceElement elemento : threads.get(tr)) {
                UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, elemento.getClassName() + "-" + elemento.getFileName() + "-" + elemento.getMethodName());
                String nomeMetodo = elemento.getClassName() + "." + elemento.getMethodName();
                UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, ".");

                Integer quantidadeMetodo = totaisMetodos.get(nomeMetodo);

                if (nomeMetodo.contains("wait")) {
                    String caminhoCompletoWait = "";
                    for (StackTraceElement elemento2 : tr.getStackTrace()) {
                        caminhoCompletoWait = caminhoCompletoWait + "." + elemento2.getMethodName();
                    }
                    Integer quantidadeWait = totaisWait.get(caminhoCompletoWait);
                    if (quantidadeWait == null) {
                        totaisWait.put(caminhoCompletoWait, 1);
                    } else {
                        totaisWait.put(caminhoCompletoWait, quantidadeWait + 1);
                    }
                }

                if (quantidadeMetodo == null) {
                    totaisMetodos.put(nomeMetodo, 1);
                } else {
                    totaisMetodos.put(nomeMetodo, quantidadeMetodo + 1);
                }

            }

        }

        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog,
                "_____________________Classes ATIVAS_________________________________________");

        for (String nomeTr
                : totais.keySet()) {
            UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, nomeTr + ": " + totais.get(nomeTr));
        }

        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog,
                "_______________________METODOS wait:_______________________________________");

        for (String nomeWait : totaisWait.keySet()) {
            UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, nomeWait + ": " + totaisWait.get(nomeWait));
        }

        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog,
                "_______________________METODOS ATIVOS:_______________________________________");

        for (String nomeMet
                : totaisMetodos.keySet()) {

            UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, nomeMet + ": " + totaisMetodos.get(nomeMet));
        }

        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "Memoria livre: " + format.format(freeMemory / 1024));
        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "Memória Alocada:" + format.format(allocatedMemory / 1024));
        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "Máximo memória:" + format.format(maxMemory / 1024) + " ");
        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "total Memmória livre " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));
        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "TOTAL THREADS:" + Thread.activeCount());
        UtilSBCoreArquivoTexto.escreverEmArquivo(caminhoArquivoLog, "AllStack" + threads.size());
    }

    public static ItfCentralMensagens getCentralDeMensagens() {
        try {
            return centralMensagens.newInstance();
        } catch (Throwable ex) {

            FabErro.PARA_TUDO.paraSistema("ERRO CRIANDO CENTRAL DE MENSAGENS", ex);
        }
        return null;
    }

    public static ItfCentralEventos getCentralDeEventos() {

        try {
            return centralEventos.newInstance();
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
            return controleDeSessao.newInstance();
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

    public static String getCaminhoGrupoProjeto() {

        String caminhoDiretorioBase = "";
        String separadorCaminhodiretorioBase = "";

        boolean temCaminhoDiretorioBase = false;
        boolean temCaminhoGrupoProjeto = false;

        if (getGrupoProjeto() != null) {
            if (!getGrupoProjeto().isEmpty()) {
                temCaminhoGrupoProjeto = true;
            }
        }

        if (diretorioBase != null) {
            if (!diretorioBase.isEmpty()) {
                temCaminhoDiretorioBase = true;
            }
        }
        if (temCaminhoDiretorioBase) {
            caminhoDiretorioBase = diretorioBase + "/";

        }

        if (!temCaminhoGrupoProjeto) {

            return "/home/superBits/projetos/" + getCliente() + "/source/" + caminhoDiretorioBase + "/";
        } else {

            return "/home/superBits/projetos/" + getCliente() + "/source/" + caminhoDiretorioBase + "/" + getGrupoProjeto();

        }

    }

    public static String getCaminhoDesenvolvimento() {
        boolean temCaminhoGrupoProjeto = false;
        if (getGrupoProjeto() != null) {
            if (!getGrupoProjeto().isEmpty()) {
                temCaminhoGrupoProjeto = true;
            }
        }
        if (!temCaminhoGrupoProjeto) {
            return getCaminhoGrupoProjeto();
        } else {
            return getCaminhoGrupoProjeto() + "/" + nomeProjeto;
        }

    }

    public static String getGrupoProjeto() {
        return grupoProjeto;
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

        for (Class fabrica : acoesDoSistema) {

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
        return acoesDoSistema;
    }

    public static void soutInfoDebug(String pInfo) {
        if (getEstadoAPP() != ESTADO_APP.PRODUCAO) {
            System.out.println("DebugInfo: " + pInfo);
        }

    }

    public static String getUrlJira() {
        return urlJira;
    }

}
