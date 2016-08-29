/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import com.super_bits.sbProjetos.FabConfigCoreSBProjetosModel;

import com.super_bits.sbProjetos.Model.Projeto;
import com.super_bits.shellcommands.model.Comando;
import com.super_bits.shellcommands.model.RespostaCMD;
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
        Projeto projetoTeste = FabSBProjectProjetos.SISTEMA_SANTA_CLARA.getRegistro();
        //   projetoTeste.setCliente(FabSBProjectClientes.SANTA_CLARA.getRegistro());
        //  UtilSBProjetos.criarNovoProjeto(projetoTeste);
        //   UtilSBProjetos.configurarPastaProjeto(projetoTeste);

        UtilSBProjetos.criarNovoProjeto(projetoTeste);

        // UtilSBProjetos.criarNovoProjeto(projetoTeste);
        // UtilSBProjetos.System.out.println("__________________EXISTE PASTA RELEASE__________________");
        //  verificaExistenciaDoProjetoRelease(projetoTeste);
        System.out.println("_______________________EXISTE PASTA SOURCE_____________");
        //   verificaExistenciaDoProjetoSource(projetoTeste);
        System.out.println("_______________CRIA REPOSITORIO RELEASE_____________________");
        //   criarREpositorioDoProjetoRelease(projetoTeste);
        System.out.println("________________CRIA REPOSITORIO SOURCE____________________");
        //    criarREpositorioDoProjetoSource(projetoTeste);
        System.out.println("____________________________________");

        System.out.println("Pasta cliente Local" + projetoTeste.getPastaCliente());
        System.out.println("Pasta cliente Remota" + projetoTeste.getPastaCliente());

        System.out.println("Pasta cliente Repositorio Remoto" + projetoTeste.getPastaCliente());
        System.out.println("Pasta cliente Repositorio Source Remoto" + projetoTeste.getPastaCliente());

        System.out.println("Pasta projeto Repositorio Release Remoto" + projetoTeste.getPastaCliente());
        System.out.println("Pasta projeto Repositorio Source Remoto" + projetoTeste.getPastaCliente());

        System.out.println("Pasta projeto Repositorio Source Remoto" + projetoTeste.getPastaCliente());
        System.out.println("Pasta Projeto Local");
        System.out.println("Pasta Projeto Local");

        //    UtilSBProjetos.configurarPastaProjeto(FabSBProjectProjetos.SISTEMA_SANTA_CLARA.getRegistro());
        //  UtilSBProjetos.configurarPastaCliente(projetoTeste.getCliente());
        //  UtilSBProjetos.configurarPastaProjeto(projetoTeste);
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfigCoreSBProjetosModel.DESENVOLVIMENTO.getConfiguracao(), true);
    }

}
