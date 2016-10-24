/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import com.super_bits.sbProjetos.ConfiguradorCoreSBProjetosModel;

import com.super_bits.sbProjetos.Model.Projeto;
import java.io.File;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBProjetosTest extends TesteJunit {

    private void executaComandoLendoResposta(String pComando, String diretorioExecucao) {

        try {

            // Sun's ProcessBuilder and Process example
            ProcessBuilder pb = new ProcessBuilder(pComando);
            Map<String, String> env = pb.environment();

            pb.directory(new File(diretorioExecucao));

            System.out.println("Executando comando: " + pComando);

            Process p = pb.start();

            String respostaErro = (UtilSBCoreStrings.getStringByInputStream(p.getErrorStream()));
            String respostaPadrao = (UtilSBCoreStrings.getStringByInputStream(p.getInputStream()));

            System.out.println("A resposta Erro foi");
            System.out.println(respostaErro);
            System.out.println("A resposta padrão foi");
            System.out.println(respostaPadrao);

        } catch (Throwable e) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("falha na execucao do comando", e);

        }

    }

    public UtilSBProjetosTest() {
    }

    public void criaProjetoNoServidor() {

    }

    @Test
    public void testCriarNovoProjeto() {

        Projeto moduloRequisitos = FabSBProjectProjetos.MONITOR_REQUISITOS.getRegistro();
        Projeto moduloGestaoArquivos = FabSBProjectProjetos.GESTAO_ARQUIVOS.getRegistro();
        Projeto moduloCriadorCodigo = FabSBProjectProjetos.CRIADOR_CODIGO.getRegistro();
        Projeto moduloSeguranca = FabSBProjectProjetos.CONTROLE_USUARIOS_BASICO.getRegistro();
        Projeto sistemaSantaClara = FabSBProjectProjetos.SISTEMA_SANTA_CLARA.getRegistro();
        Projeto game_super_bits_elemental = FabSBProjectProjetos.ELEMENTAL.getRegistro();

        //UtilSBProjetos.configurarPastaProjeto(FabSBProjectProjetos.SUPER_KOMPRAS.getRegistro());
        //
        UtilSBProjetos.criarNovoProjeto(FabSBProjectProjetos.INTRANET_AQUA_QUALITY.getRegistro());
        //
        //    UtilSBProjetos.criarNovoProjeto(sistemaSantaClara);
//        UtilSBProjetos.criarNovoProjetoOffline(sistemaSantaClara);
        // projetoTeste.setCliente(FabSBProjectClientes.SANTA_CLARA.getRegistro());
        // UtilSBProjetos.criarNovoProjeto(projetoTeste);
        // UtilSBProjetos.configurarPastaProjeto(projetoTeste);
        // UtilSBProjetos.criarREpositorioReleaseDoProjetoNoServidor(projetoTeste);
        //       UtilSBProjetos.configurarPastaProjeto(projetoTeste);
        //UtilSBProjetos.criarNovoProjeto(projetoTeste);
        // UtilSBProjetos.criarNovoProjeto(projetoTeste);
        // UtilSBProjetos.System.out.println("__________________EXISTE PASTA RELEASE__________________");
        //  verificaExistenciaDoProjetoRelease(projetoTeste);
        System.out.println("_______________________EXISTE PASTA SOURCE______________");
        //   verificaExistenciaDoProjetoSource(projetoTeste);
        System.out.println("_______________CRIA REPOSITORIO RELEASE_________________");
        //   criarREpositorioDoProjetoRelease(projetoTeste);
        System.out.println("________________CRIA REPOSITORIO SOURCE_________________");
        //    criarREpositorioDoProjetoSource(projetoTeste);
        System.out.println("____________________________________");

        System.out.println("Pasta Projeto Local");
        System.out.println("Pasta Projeto Local");

        //    UtilSBProjetos.configurarPastaProjeto(FabSBProjectProjetos.SISTEMA_SANTA_CLARA.getRegistro());
        //  UtilSBProjetos.configurarPastaCliente(projetoTeste.getCliente());
        //  UtilSBProjetos.configurarPastaProjeto(projetoTeste);
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorCoreSBProjetosModel(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

}