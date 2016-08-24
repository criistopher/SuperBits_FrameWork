/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
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
public class PrevisaoProjeto implements Serializable {

    private final List<TarefaSuperBits> tarefasProximaVersao;
    private final List<TarefaSuperBits> todasTarefas;
    private CustosProjeto custoProjetoProximaVersao;
    private CustosProjeto custoProjetoCompleto;
    private HashMap<ItfModuloAcaoSistema, ModuloPrevisto> modulosPrevistos = new HashMap<>();

    private AmbienteDesenvolvimento ambienteDesenvolvimento;

    public PrevisaoProjeto(List<TarefaSuperBits> ptodasTarefas) {
        this.tarefasProximaVersao = new ArrayList<>();
        todasTarefas = ptodasTarefas;
        defineModulosPrevistros();
        calcularValores();
    }

    public final void defineModulosPrevistros() {

        for (ItfModuloAcaoSistema modulo : MapaAcoesSistema.getModulos()) {
            List<PrevisaoEntidade> previsoesEntidade = new ArrayList<>();
            List<PrevisaoGestaoEntidade> previsoesGestaoEntidade = new ArrayList<>();

            for (ItfAcaoGerenciarEntidade acaoGestao : MapaAcoesSistema.getAcoesGestaoByModulo(modulo)) {
                List<TarefaSuperBits> tarefasGestao = MapaTarefasProjeto.getTarefasDaGestao(acaoGestao);
                PrevisaoGestaoEntidade prevGestao = new PrevisaoGestaoEntidade(acaoGestao, tarefasGestao);
                List<TarefaSuperBits> tarefasTabela = MapaTarefasProjeto.getTarefasDaTabela(acaoGestao.getClasseRelacionada());
                PrevisaoEntidade prevEntidade
                        = new PrevisaoEntidade(modulo, tarefasTabela, acaoGestao.getClasseRelacionada());
                previsoesEntidade.add(prevEntidade);
                previsoesGestaoEntidade.add(prevGestao);
            }

            ModuloPrevisto modPrev = new ModuloPrevisto(previsoesGestaoEntidade, previsoesEntidade);

            System.out.println("Previsto modulo " + modulo);
            System.out.println("O modulo " + modulo + " possui" + modPrev.getEntidadesPrevistas().size() + "Etidades com ações previstas");
            System.out.println("O modulo " + modulo + " possui" + modPrev.getGestoesPrevistas().size() + "Gestões com ações previstas");
            modulosPrevistos.put(modulo, modPrev);
        }

    }

    public final void calcularValores() {
        custoProjetoProximaVersao = new CustosProjeto(tarefasProximaVersao, ambienteDesenvolvimento);
        custoProjetoCompleto = new CustosProjeto(todasTarefas, ambienteDesenvolvimento);
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

    public CustosProjeto getCustoProjetoProximaVersao() {
        return custoProjetoProximaVersao;
    }

    public CustosProjeto getCustoProjetoCompleto() {
        return custoProjetoCompleto;
    }

    public HashMap<ItfModuloAcaoSistema, ModuloPrevisto> getModulosPrevistos() {
        return modulosPrevistos;
    }

}
