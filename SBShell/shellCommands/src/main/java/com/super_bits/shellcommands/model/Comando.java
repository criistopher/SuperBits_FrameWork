/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.shellcommands.model;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreParametrosEmString;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.shellcommands.shel.UtilSBShellScript;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 *
 * A classe comando atende a criação de um comando shell windows e Linux, e
 * scripts em linux;
 *
 *
 * Os Scripts devem ficar na pasta resources, e para executalos, basta setar o
 * comando com o nome do arquivo durante o constructor
 *
 * Os parametros nos scripts devem ser nomeados como em comandos SQL
 * :nomeDoParametro
 *
 * Os parametros dos comandos devem ser nomeados da mesma forma
 *
 * Embora os comandos possam ser criados manualmente, existe uma fabrica de
 * comandos (TIPOCMD)
 *
 *
 * O comando retorna o sucesso ou não da execução baseado na verificação do
 * REtorno recebido, os tipos de retornos possiveis de verificar são:
 *
 * match: (caso seja igual a variavel, ou atenda o regex di atrubuto
 * regexResultadoEsperado)
 *
 * Sem resposta: (caso retorne algo considera falha)
 *
 * os tipos de retornos podem ser OK (atendeu a resposta esperada), ALERTA que
 * não atendeu, ou Falhou, que não conseguiu executar
 *
 *
 *
 *
 * @author sfurbino
 */
public class Comando {

    public enum TIPO_RESPOSTA_ESPERADA {
        SEMRESPOSTA, MACH
    }

    public enum TIPO_EXECUCAO {
        CRIAR_SCRIPTLNX, DIRETO
    }

    private TIPOCMD tipoComando;
    private TIPO_RESPOSTA_ESPERADA tipoRespostaEsperada;
    private String comando;
    private String regexResultadoEsperado;
    private Map<String, Object> parametros;
    private String diretorioExecucao;
    private TIPO_EXECUCAO tipoExecucao;
    private List<String> script;
    private boolean caminhoOBrigatorio;

    /**
     * Função que tratava transformação de parametro nomeado em $1, $2 ,$3
     *
     * private String defineNomeParametro(String pNomeparametro) { i = 1; while
     * (parametroExiste(pNomeparametro)) { pNomeparametro = pNomeparametro + "-"
     * + i; i++; }
     *
     * }
     */
    private void printParametros() {
        System.out.println("INFO para comando: " + getTipoComando() + "");
        System.out.println("Os valores atuais de parametros são:");
        for (String pr : parametros.keySet()) {
            System.out.println(pr + "=" + parametros.get(pr));
        }

    }

    private void criaParametroByCmd() {
        List<String> parametrosEncontrados = null;
        script = new ArrayList<>();
        parametros = new HashMap();
        switch (tipoExecucao) {

            case CRIAR_SCRIPTLNX:
                InputStream inputScript = ClassLoader.getSystemResourceAsStream("scripts/" + getComando());

                if (inputScript == null) {
                    throw new UnsupportedOperationException("Script" + getComando() + " não encontrado em na aplicação");
                }
                List<String> linhasScript = UtilSBCoreStrings.getStringsByInputStream(inputScript);

                parametrosEncontrados = UtilSBCoreParametrosEmString.retornaParamentrosNomeadosEmLista(linhasScript);
                script = linhasScript;

                break;
            case DIRETO:
                parametrosEncontrados = UtilSBCoreParametrosEmString.retornaParamentrosNomeadosEmLista(getComando());
                break;
        }
        for (String pr : parametrosEncontrados) {

            if (!parametroExiste(pr)) {

                parametros.put(pr, "");

            } else {
                printParametros();
                throw new UnsupportedOperationException("Erro na tentativa de adicionar parametro" + pr + " JÁ exite no comando" + getTipoComando());

            }

        }

    }

    public void configParametro(String pNomeParametro, String pValor) {
        if (parametroExiste(pNomeParametro)) {

            parametros.put(":" + pNomeParametro, pValor);

            /**
             * Tratamento para configuração de parametros que serão substituidos
             * por $1 $2 $3 if (tipoExecucao == TIPO_EXECUCAO.CRIAR_SCRIPTLNX) {
             *
             * for (String nomeParametro : parametros.keySet()) { if
             * (nomeParametro.matches(pNomeParametro + "-[0-9]") ||
             * nomeParametro.matches(pNomeParametro + "-[0-9][0-9]")) {
             * parametros.put(nomeParametro, pValor); } } }
             */
        } else {
            printParametros();
            throw new UnsupportedOperationException("Erro ao configurar parametro [" + pNomeParametro + "] não existe no comando" + getTipoComando());

        }
    }

    public Comando(TIPO_EXECUCAO pTipo_execucao, String pComando) {
        parametros = new HashMap<>();
        tipoRespostaEsperada = TIPO_RESPOSTA_ESPERADA.MACH;
        tipoExecucao = pTipo_execucao;
        comando = pComando;
        criaParametroByCmd();

    }

    public TIPOCMD getTipoComando() {
        return tipoComando;
    }

    public void setTipoComando(TIPOCMD pTipoComando) {

        this.tipoComando = pTipoComando;
    }

    public String getNomeArquivoScript() {
        if (tipoExecucao == TIPO_EXECUCAO.DIRETO) {
            throw new UnsupportedOperationException("Este comando não precisa de Script para ser executado");
        }

        return comando;
    }

    public String getComando() {
        if (comando == null) {
            return null;
        }

        String comandoParametrizado = comando;
        for (String nomep : parametros.keySet()) {
            comandoParametrizado = comandoParametrizado.replace(nomep, (String) parametros.get(nomep));
        }

        if (tipoExecucao == TIPO_EXECUCAO.CRIAR_SCRIPTLNX) {
            comandoParametrizado = "./" + comandoParametrizado;
        }
        return comandoParametrizado;
    }

    public String getRegexResultadoEsperado() {
        return regexResultadoEsperado;
    }

    public void setRegexResultadoEsperado(String regexResultadoEsperado) {
        this.regexResultadoEsperado = regexResultadoEsperado;
    }

    private boolean parametroExiste(String pNomeParametro) {
        return parametros.get(":" + pNomeParametro) != null;
    }

    public RespostaCMD executarComando() {

        for (String nomep : parametros.keySet()) {
            if (parametros.get(nomep).equals("")) {
                printParametros();
                throw new UnsupportedOperationException("Tentativa de Executar o comando sem parametro" + nomep);
            }

        }
        if (caminhoOBrigatorio) {
            if (diretorioExecucao == null) {
                throw new UnsupportedOperationException("O diretorio é obrigatório para execução deste comando");
            }
        }

        return UtilSBShellScript.executaComando(this);
    }

    public RespostaCMD executarComando(String... pParametros) {
        int i = 0;
        for (String nomep : parametros.keySet()) {
            parametros.put(":" + nomep, pParametros[i]);
            i++;
        }

        return executarComando();

    }

    public TIPO_RESPOSTA_ESPERADA getTipoRespostaEsperada() {
        return tipoRespostaEsperada;
    }

    public void setTipoRespostaEsperada(TIPO_RESPOSTA_ESPERADA tipoRespostaEsperada) {
        this.tipoRespostaEsperada = tipoRespostaEsperada;
    }

    public String getDiretorioExecucao() {
        return diretorioExecucao;
    }

    public void setDiretorioExecucao(String DiretorioExecucao) {
        this.diretorioExecucao = DiretorioExecucao;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public TIPO_EXECUCAO getTipoExecucao() {
        return tipoExecucao;
    }

    public List<String> getScript() {
        return script;
    }

    public boolean isCaminhoOBrigatorio() {
        return caminhoOBrigatorio;
    }

    public void setCaminhoOBrigatorio(boolean caminhoOBrigatorio) {
        this.caminhoOBrigatorio = caminhoOBrigatorio;
    }

}
