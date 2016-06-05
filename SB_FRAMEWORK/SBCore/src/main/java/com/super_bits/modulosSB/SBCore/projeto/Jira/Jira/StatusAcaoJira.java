/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.projeto.Jira.Jira;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class StatusAcaoJira {

    public static enum TIPO_ACAO_JIRA {
        TESTE,
        IMPLEMENTACAO_TELA,
        IMPLEMENTACAO_CONTROLLER,
        DEFINICAO_REQUISITOS,
        DEFINICAO_MODELAGEM
    }

    private ItfAcaoDoSistema acao;
    private String urlJira;
    private String tituloTarefa;

    private List<Issue> subTarefas;
    private Issue tarefaCriacaoTeste;
    private Issue tarefaPrincipal;

    public StatusAcaoJira(ItfFabricaAcoes fabricaAcao) {
        acao = fabricaAcao.getAcaoDoSistema();

        // em Managed Benans:
        // criar um teste, e uma ação para cada sub Ação.
        /// em Ação de controller: Criar uma Ação com o nome do Modulo e Um teste
        // e uma implementação para cada ação
        if (acao.isUmaAcaoGestaoDominio()) {

        } else {

            if (acao.isUmaAcaoController()) {

            }
            if (acao.isUmaAcaoFormulario()) {

            }

        }

    }

    public ItfAcaoDoSistema getAcao() {
        return acao;
    }

    public String getUrlJira() {
        return urlJira;
    }

    public List<Issue> getSubTarefas() {
        return subTarefas;
    }

    public Issue getTarefaCriacaoTeste() {
        return tarefaCriacaoTeste;
    }

    public Issue getTarefaPrincipal() {
        return tarefaPrincipal;
    }

}
