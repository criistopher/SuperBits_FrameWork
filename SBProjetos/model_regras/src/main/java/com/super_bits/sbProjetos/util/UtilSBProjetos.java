/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDiretorios;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.sbProjetos.Model.Cliente;
import com.super_bits.sbProjetos.Model.Projeto;
import com.super_bits.shellcommands.model.Comando;
import com.super_bits.shellcommands.model.SvnStatusArquivosRepositorio;
import com.super_bits.shellcommands.model.TIPOCMD;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;

/**
 *
 * @author sfurbino
 */
public class UtilSBProjetos {

    public static final String NOME_PROJETO_BASE = "SuperBitsWPStarter";

    public static void criarNovoProjeto(Projeto p) {

        File pastadoProjeto = new File(p.getPastaDoProjetoSource());
        File pastaSourceCliente = new File(p.getPastaSource());
        File pastaSourceTemporaria = new File(p.getPastaSource() + "/" + NOME_PROJETO_BASE);

        if (pastadoProjeto.exists()) {
            SBCore.enviarMensagemUsuario("Não foi possível criar o projeto, A pasta do projeto já existe (apague e tente novamente)", FabMensagens.AVISO);
            return;
        }

        Comando criarPasta = TIPOCMD.LNXDIR_MAKEDIR.getComando();
        criarPasta.configParametro("pastaCriar", p.getPastaSource());

        criarPasta.executarComando();

        Assert.assertTrue("pasta do SOURCE  cliente não encontrada", pastaSourceCliente.exists());

        Comando copiarPastaNovoProjeto = TIPOCMD.LNXDIR_COPIAR_PASTA.getComando();
        copiarPastaNovoProjeto.configParametro("pastaCopOri", "/home/superBits/projetos/Super_Bits/source/SuperBits_FrameWork/" + NOME_PROJETO_BASE + "/");
        copiarPastaNovoProjeto.configParametro("pastaCopDest", p.getPastaSource());
        copiarPastaNovoProjeto.executarComando();
        Assert.assertTrue("pasta temporaria não encontrada", pastaSourceTemporaria.exists());

        Comando renomearPasta = TIPOCMD.LNXDIR_MOVERPASTA.getComando();
        renomearPasta.configParametro("pastaMovOri", p.getPastaSource() + "/" + NOME_PROJETO_BASE);
        renomearPasta.configParametro("pastaMovDest", p.getPastaDoProjetoSource());
        renomearPasta.executarComando();
        Assert.assertTrue("pasta source do cliente não encontrada", pastadoProjeto.exists());

        String extensoesEditaveis[] = new String[]{"*.java", "*.xml"};

        Map<String, String> mapaPalavras = new HashMap<>();

        mapaPalavras.put("InomeProjetoI", UtilSBCoreStrings.makeStrUrlAmigavel(p.getNomeProjeto()));
        mapaPalavras.put("InomeClienteI", UtilSBCoreStrings.makeStrUrlAmigavel(p.getCliente().getNome()));

        for (String extencaoArquivos : extensoesEditaveis) {

            for (String nomeParametro : mapaPalavras.keySet()) {

                Comando renomearNomesProjetoArqJava = TIPOCMD.LNXSUBSTITUIR_PALAVRA_EM_ARQUIVOS.getComando();
                renomearNomesProjetoArqJava.configParametro("pastaRecursiva", p.getPastaDoProjetoSource());
                renomearNomesProjetoArqJava.configParametro("novoTexto", mapaPalavras.get(nomeParametro));
                renomearNomesProjetoArqJava.configParametro("textoAntigo", nomeParametro);
                renomearNomesProjetoArqJava.configParametro("arquivosPesquisados", extencaoArquivos);
                renomearNomesProjetoArqJava.executarComando();
            }
        }

        List<String> subpastaras = UtilSBCoreDiretorios.getDiretoriosRecursivoOrdemMaoirArvore(new File(p.getPastaDoProjetoSource()));
        for (String subpasta : subpastaras) {
            for (String nomeAntigo : mapaPalavras.keySet()) {

                Comando renomearNomesProjetoArqJava = TIPOCMD.LNX_RENOMEAR_TODOS_ARQUIVOS_E_PASTAS_DO_DIRETORIO_.getComando();
                renomearNomesProjetoArqJava.configParametro("diretorio", subpasta);
                renomearNomesProjetoArqJava.configParametro("novoTexto", mapaPalavras.get(nomeAntigo));
                renomearNomesProjetoArqJava.configParametro("textoAntigo", nomeAntigo);
                //   renomearNomesProjetoArqJava.configParametro("arquivosPesquisados", "*");
                renomearNomesProjetoArqJava.executarComando();
            }
        }

        limparPastaDoProjeto(p);
        //  efetuarCheckout(p);
        //     adicionarArquivosSourcenoRepositorio(p);

        //sincronizarSVN.configParametro("pasta", p.getPastaDoProjetoSource());
        //sincronizarSVN.configParametro("endCheckout", p.getLinkSVNSource());
        //sincronizarSVN.configParametro("usuario", "SBAdmin");
        //    System.out.println("pasta do projeto=" + p.getPastaDoProjetoSource());
        //  List<Comando> comandos = new ArrayList();
        //  comandos.add(criarPasta);
        //  comandos.add(copiarPastaNovoProjeto);
        //  comandos.add(renomearPasta);
        //  comandos.add(renomearNomesClienteArqJAva);
        // comandos.add(sincronizarSVN);
        // Script criarNovoProjeto = new Script(comandos);
        //        criarNovoProjeto.executarScript();
        //System.out.println("ResultadoExecucao=" + criarNovoProjeto.getResultadoExecucao());
    }

    public static void limparPastaDoProjeto(Projeto p) {
        Comando deletarArquivosTemporarios = TIPOCMD.LNX_REMOVER_TODOS_ARQUIVOS_COM_ESTE_NOME.getComando();
        deletarArquivosTemporarios.setDiretorioExecucao(p.getPastaDoProjetoSource());
        deletarArquivosTemporarios.configParametro("pastaRecursivaExclusaoArquivo", p.getPastaDoProjetoSource());
        deletarArquivosTemporarios.configParametro("pNomeArquivo", "*.*~");
        deletarArquivosTemporarios.executarComando();
        Comando deletarPastaTemporaria = TIPOCMD.LNX_REMOVER_TODAS_PASTAS_COM_ESTE_NOME.getComando();
        deletarPastaTemporaria.configParametro("pastaRecursiva", p.getPastaDoProjetoSource());
        deletarPastaTemporaria.configParametro("nomePastaExclusao", "target");
        deletarPastaTemporaria.executarComando();
    }

    public static void configurarPastaCliente(Cliente pCliente) {

        // Criar a pasta se não existir
        // Criar arquivo cliente.info em Source contendo o servidor de gitHUB ex:SERVIDOR_GIT_SOURCE=https://github.com/salviof
        // criar o arquivo cliente.info em release contendo o endereco do servidor de homologacao ex
        //SERVIDOR_GIT_RELEASE=https://github.com/salviof/NAO_IMPLEMENTADO
    }

    public static void configurarPastaProjeto(Projeto pProjeto) {

    }

    public static void efetuarCheckout(Projeto p) {
        Comando criaRepositorio = TIPOCMD.LNXSVN_CHECKOUT.getComando();

        criaRepositorio.configParametro("pstSource", p.getPastaSource());
        criaRepositorio.configParametro("pasta", p.getNomeCurtoURLAmigavel());
        criaRepositorio.configParametro("usuario", "SBAdmin");
        criaRepositorio.configParametro("senha", "123321@aA");
        criaRepositorio.configParametro("caminhoRepositorio", p.getLinkSVNSource());
        criaRepositorio.executarComando();
    }

    public static void adicionarArquivosSourcenoRepositorio(Projeto p) {

        SvnStatusArquivosRepositorio repostorio = new SvnStatusArquivosRepositorio(p.getPastaDoProjetoSource());

        if (repostorio.getAdicionados().size() > 0) {
            Comando testeAddSVN = TIPOCMD.LNXSVN_ADD_ARQUIVO_REPOSITORIO.getComando();
            testeAddSVN.setDiretorioExecucao(p.getPastaDoProjetoSource());
            testeAddSVN.configParametro("arquivo", repostorio.getAdicionadosSeparadosPorEspaco());
            testeAddSVN.setDiretorioExecucao(p.getPastaDoProjetoSource());
            testeAddSVN.executarComando();
        }

    }

}
