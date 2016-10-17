/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import java.io.IOException;

/**
 *
 * @author salvioF
 */
public class ProjetoJiraSuperBitsAbstrato {

    private String usuario;
    private String senha;
    private final JiraRestClient conexao;
    private boolean conexaoAberta;

    public ProjetoJiraSuperBitsAbstrato(String pUsuario, String pSenha) {
        this.conexao = UtilSBCoreJira.criarConexaoJira(pUsuario, pSenha);
        if (conexao != null) {
            conexaoAberta = true;
        }
    }

    public JiraRestClient getConexao() {
        if (!conexaoAberta) {
            throw new UnsupportedOperationException("A conexão com o Jira não está ativa");
        }
        return conexao;
    }

    public void fecharConexao() {
        conexaoAberta = false;
        try {
            conexao.close();
        } catch (IOException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, senha, ex);
        }
    }

}
