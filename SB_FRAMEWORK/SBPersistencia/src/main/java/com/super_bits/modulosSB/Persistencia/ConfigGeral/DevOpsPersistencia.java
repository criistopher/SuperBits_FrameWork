/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.ConfigGeral;

import static com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia.getPastaExecucaoScriptsSQL;
import static com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia.limparBanco;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.util.UtilSBPersistenciaFabricas;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreResources;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreShellBasico;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author salvioF
 */
public class DevOpsPersistencia {

    private final String nomeArquivoPersistencia;
    private final ItfConfigSBPersistencia configurador;

    private final String getPrexinoNomeArquivo() {
        if (nomeArquivoPersistencia.contains("modelRegras")) {
            return "";
        }
        if (nomeArquivoPersistencia.contains("requisit")) {
            return "req_";
        }
        return nomeArquivoPersistencia.substring(0, 5) + "_";
    }

    private String getARQUIVO_COMPILA_BANCO() {
        return getPrexinoNomeArquivo() + "compilaBanco.sh";
    }

    private String getARQUIVO_CARREGA_BANCO() {
        return getPrexinoNomeArquivo() + "carregaBanco.sh";
    }

    private String getARQUIVO_APAGA_BANCO() {
        return getPrexinoNomeArquivo() + "apagaBanco.sh";
    }

    private String getARQUIVO_CONFIGURACOES() {
        return getPrexinoNomeArquivo() + "SBProjeto.prop";
    }

    private String getARQUIVO() {
        return getPrexinoNomeArquivo() + "hashbanco.info";
    }
    private final String DESTINO_ARQUIVO_CARREGA_BANCO = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + getARQUIVO_CARREGA_BANCO();
    private final String DESTINO_ARQUIVO_COMPILA_BANCO = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + getARQUIVO_COMPILA_BANCO();
    private final String DESTINO_ARQUIVO_APAGA_BANCO = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + getARQUIVO_APAGA_BANCO();
    private final String DESTINO_ARQUIVO_CONFIGURACOES = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + getARQUIVO_CONFIGURACOES();
    private final String DESTINO_ARQUIVO_HASH_BANCO = SBPersistencia.getPastaExecucaoScriptsSQL() + "/" + getARQUIVO();

    public DevOpsPersistencia(ItfConfigSBPersistencia pConfig) {

        nomeArquivoPersistencia = pConfig.bancoPrincipal();
        configurador = pConfig;
        if (SBCore.isEmModoDesenvolvimento()) {
            criaScriptsBancoDeDAdos(configurador);
        }
    }

    private boolean houveAlteracaoHomologacaoBanco(ItfConfigSBPersistencia configurador) {
        long codigoAlteracao = 0;
        for (Class entidade : UtilSBPersistencia.getTodasEntidades()) {
            codigoAlteracao += UtilSBCoreResources.getHashCodeClasseDoPacote(entidade);
        }
        for (Class fabrica : configurador.fabricasRegistrosIniciais()) {
            codigoAlteracao += UtilSBCoreResources.getHashCodeClasseDoPacote(fabrica);
        }
        codigoAlteracao += UtilSBCoreResources.getHashCodeClasseDoPacote(configurador.getClass());

        if (!new File(DESTINO_ARQUIVO_HASH_BANCO).exists()) {
            UtilSBCoreArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(DESTINO_ARQUIVO_HASH_BANCO, "0000");
        }
        //      UtilSBCoreArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(DESTINO_ARQUIVO_HASH_BANCO, provaTXT);
        String alteracaoAnterior = UTilSBCoreInputs.getStringByArquivoLocal(DESTINO_ARQUIVO_HASH_BANCO);

        Long altAnterior = NumberUtils.toLong(alteracaoAnterior);
        System.out.println("Hash   anterior " + altAnterior + "  em" + DESTINO_ARQUIVO_HASH_BANCO);
        System.out.println("Hash Atualizado " + codigoAlteracao);
        codigoAlteracao = Math.abs(codigoAlteracao);
        long diferenca = codigoAlteracao - altAnterior;
        if (diferenca == 0) {
            return false;
        } else {
            UtilSBCoreArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(DESTINO_ARQUIVO_HASH_BANCO, String.valueOf(codigoAlteracao));
            return true;
        }

    }

    public void compilaBanco() {

        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("A compilação do banco só pode ser realizada em modo desenvolvimento");
        }
        //  IO.co tring teste;
        File script = new File(DESTINO_ARQUIVO_APAGA_BANCO);
        if (!script.exists()) {
            throw new UnsupportedOperationException("O arquivo de script para apagar banco não foi encontrado em " + script);
        }
        String retornoCompilaBanco = UtilSBCoreShellBasico.executeCommand(DESTINO_ARQUIVO_COMPILA_BANCO);
        System.out.println("Retorno Compila Banco->" + retornoCompilaBanco);

    }

    public void criaScriptsBancoDeDAdos(ItfConfigSBPersistencia pConfigurador) {
        //UtilSBCore String caminhosScript = (SBCore.getCaminhoGrupoProjetoSource() + "/compilaBanco.sh");

        File arquivoApaBanco = new File(DESTINO_ARQUIVO_APAGA_BANCO);
        if (!arquivoApaBanco.exists()) {
            Class classeDoResource = pConfigurador.getClass();

            UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, getARQUIVO_CONFIGURACOES(), DESTINO_ARQUIVO_CONFIGURACOES);
            UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, getARQUIVO_APAGA_BANCO(), DESTINO_ARQUIVO_APAGA_BANCO);
            UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, getARQUIVO_COMPILA_BANCO(), DESTINO_ARQUIVO_COMPILA_BANCO);
            UtilSBCoreArquivos.copiarArquivoResourceJar(classeDoResource, getARQUIVO_CARREGA_BANCO(), DESTINO_ARQUIVO_CARREGA_BANCO);

            String retorno = UtilSBCoreShellBasico.executeCommand("chmod +x " + getPastaExecucaoScriptsSQL() + "/*.sh");
            System.out.println("Retorno CHNOD +X-->" + retorno);
        }
    }

    private void carregaBanco() {
        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            throw new UnsupportedOperationException("o carregamento automatico do banco só pode ser realizado em modo desenvolvimento");
        }
        //  IO.co tring teste;
        File script = new File(DESTINO_ARQUIVO_APAGA_BANCO);
        if (!script.exists()) {
            throw new UnsupportedOperationException("O arquivo de script para apagar banco não foi encontrado em " + script);
        }
        String retornoCarrregaBanco = UtilSBCoreShellBasico.executeCommand(DESTINO_ARQUIVO_CARREGA_BANCO);
        System.out.println("Retorno Carrega Banco" + retornoCarrregaBanco);

    }

    public void iniciarBanco() {
        Map<String, Object> propriedades = new HashMap<>();
        if (SBCore.getEstadoAPP().equals(SBCore.ESTADO_APP.DESENVOLVIMENTO)) {
            // desabilitando criação de banco de dados no início caso o banco seja o mesmo
            propriedades.put("hibernate.hbm2ddl.auto", null);
            // Mostrar SQL
            propriedades.put("hibernate.show_sql", true);
            //Mostrar SQL formatado
            propriedades.put("hibernate.format_sql", true);
            //Mostrar comentários explicativos
            propriedades.put("hibernate.use_sql_comments", true);
            EntityManagerFactory emFacturePadrao = Persistence.createEntityManagerFactory(nomeArquivoPersistencia, propriedades);
            UtilSBPersistencia.defineFabricaEntityManager(emFacturePadrao, propriedades);

            if (houveAlteracaoHomologacaoBanco(configurador)) {
                try {
                    UtilSBPersistencia.getEmfabricaPadrao().close();
                } catch (Throwable t) {
                    System.out.println("Erro tentnaod fechar entitymanager factury para criação de novo banco");
                }
                limparBanco();
                propriedades.put("hibernate.hbm2ddl.auto", "create-drop");
                EntityManager primeiraConexao = null;
                try {
                    emFacturePadrao = Persistence.createEntityManagerFactory(nomeArquivoPersistencia, propriedades);
                    UtilSBPersistencia.defineFabricaEntityManager(emFacturePadrao, propriedades);

                    primeiraConexao = UtilSBPersistencia.getNovoEM();

                    if (configurador.fabricasRegistrosIniciais() != null) {
                        for (Class classe : configurador.fabricasRegistrosIniciais()) {
                            UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(classe, primeiraConexao, UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ORDEM_DE_DECLARCAO);
                        }
                    }

                    configurador.criarBancoInicial();
                    compilaBanco();
                    primeiraConexao.close();
                } catch (Throwable t) {
                    UtilSBCoreArquivoTexto.escreverEmArquivoSubstituindoArqAnterior(DESTINO_ARQUIVO_HASH_BANCO, String.valueOf(0000));
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao construir banco de dados", t);
                    throw new UnsupportedOperationException("Impossível carregar o banco pela primeira vez, cheque as configurações do entityManager");
                }

                //senão houve alterção no banco
            } else {
                limparBanco();
                carregaBanco();
                UtilSBPersistencia.renovarFabrica();
            }

            // SENÃO (ESTÁDO DIFERENTE DE EM DESENVOLVIMENTO): (A FUNÇÃO DE LIMPAR E SUBIR O BANCO CABE AO SCRIPT DE IMPLANTAÇÃO , E NAÕ DURANTE EXECUÇÃO DO CODIGO)
            // TODO remover essa caixa alta..
        } else {
            // desabilitando hbm2dllauto por segurança
            propriedades.put("hibernate.hbm2ddl.auto", null);
            // Mostrar SQL
            propriedades.put("hibernate.show_sql", false);
            //Mostrar SQL formatado
            propriedades.put("hibernate.format_sql", true);
            //Mostrar comentários explicativos
            propriedades.put("hibernate.use_sql_comments", false);
            EntityManagerFactory emFacturePadrao = Persistence.createEntityManagerFactory(nomeArquivoPersistencia, propriedades);
            UtilSBPersistencia.defineFabricaEntityManager(emFacturePadrao, propriedades);
        }

    }

}
