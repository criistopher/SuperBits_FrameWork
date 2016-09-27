/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.projeto.Jira.ambienteDesenvolvimento.AmbienteDesenvolvimento;
import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.projeto.Jira.Jira.MapaTarefasProjeto;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoClasse(tags = {"Previsao Projeto"}, plural = "Previsões de Projeto")
public class PrevisaoProjeto implements Serializable {

    private final List<TarefaSuperBits> tarefasProximaVersao;
    private final List<TarefaSuperBits> todasTarefas;
    private CustosDesenvolvimento custosDesenvolvimento;
    private final HashMap<ItfModuloAcaoSistema, PrevisaoModulo> modulosPrevistos = new HashMap<>();
    private HashMap<ItfModuloAcaoSistema, List<ItfAcaoDoSistema>> modulosEAcoesAdicionados;
    private final AmbienteDesenvolvimento ambienteDesenvolvimento;

    public PrevisaoProjeto(List<TarefaSuperBits> ptodasTarefas) {
        this.tarefasProximaVersao = new ArrayList<>();
        ambienteDesenvolvimento = new AmbienteDesenvolvimento();
        todasTarefas = ptodasTarefas;
        defineModulosPrevistros();
        calcularValores();

        custosDesenvolvimento = new CustosDesenvolvimento(ptodasTarefas, ambienteDesenvolvimento);

    }

    private void defineModulos() {
        for (TarefaSuperBits tarefa : todasTarefas) {
            switch (tarefa.getTarefaJiraOrigem().getTipoOrigem()) {
                case BANCO_DE_DADOS:
                    break;
                case ACAO_DO_SISTEMA:

                    break;
                default:
                    throw new AssertionError(tarefa.getTarefaJiraOrigem().getTipoOrigem().name());

            }

        }
    }

    public final void defineModulosPrevistros() {

        //Para cada modulo do sistema
        for (ItfModuloAcaoSistema modulo : MapaAcoesSistema.getModulos()) {
            List<PrevisaoEntidade> previsoesEntidade = new ArrayList<>();
            List<PrevisaoGestaoEntidade> previsoesGestaoEntidade = new ArrayList<>();
            //para cada ação de gestão do modulo
            for (ItfAcaoGerenciarEntidade acaoGestao : MapaAcoesSistema.getAcoesGestaoByModulo(modulo)) {
                List<TarefaSuperBits> tarefasGestao = MapaTarefasProjeto.getTarefasDaGestao(acaoGestao);
                PrevisaoGestaoEntidade prevGestao = new PrevisaoGestaoEntidade(acaoGestao, tarefasGestao, this);
                Class classeRelacionada = acaoGestao.getClasseRelacionada();
                previsoesGestaoEntidade.add(prevGestao);
                List<TarefaSuperBits> tarefasTabela = MapaTarefasProjeto.getTarefasDaTabela(classeRelacionada);
                if (tarefasTabela != null) {

                    PrevisaoEntidade prevEntidade = new PrevisaoEntidade(modulo, tarefasTabela, acaoGestao.getClasseRelacionada(), this);
                    previsoesEntidade.add(prevEntidade);
                }

            }
            PrevisaoModulo modPrev = new PrevisaoModulo(previsoesGestaoEntidade, previsoesEntidade, this);
            System.out.println("Previsto modulo " + modulo);
            System.out.println("O modulo " + modulo + " possui" + modPrev.getEntidadesPrevistas().size() + "Etidades com ações previstas");
            System.out.println("O modulo " + modulo + " possui" + modPrev.getGestoesPrevistas().size() + "Gestões com ações previstas");
            modulosPrevistos.put(modulo, modPrev);
        }

    }

    public final void calcularValores() {

        custosDesenvolvimento = new CustosDesenvolvimento(todasTarefas, ambienteDesenvolvimento);
    }

    public void adicionarGestaoEntidadeEmProximaVersao(PrevisaoGestaoEntidade pPrevisao) {
        pPrevisao.getTarefasVinculadas().stream().filter((tr)
                -> (!tarefasProximaVersao.contains(tr))).forEach((tr) -> {
            tarefasProximaVersao.add(tr);
        });
    }

    public void removerGestaoEntidadeEmProximaVersao(PrevisaoGestaoEntidade pPrevisao) {
        pPrevisao.getTarefasVinculadas().stream().filter((tr) -> (tarefasProximaVersao.contains(tr))).forEach((tr) -> {
            tarefasProximaVersao.remove(tr);
        });
    }

    public AmbienteDesenvolvimento getAmbienteDesenvolvimento() {
        return ambienteDesenvolvimento;
    }

    public CustosDesenvolvimento getCustosDesenvolvimento() {
        return custosDesenvolvimento;
    }

    public HashMap<ItfModuloAcaoSistema, PrevisaoModulo> getModuloPrevistosPorModulo() {
        return modulosPrevistos;
    }

    public List<PrevisaoModulo> getModulosPrevistos() {
        return Lists.newArrayList(getModuloPrevistosPorModulo().values());
    }

    public void setCustosDesenvolvimento(CustosDesenvolvimento custosDesenvolvimento) {
        this.custosDesenvolvimento = custosDesenvolvimento;
    }

}
