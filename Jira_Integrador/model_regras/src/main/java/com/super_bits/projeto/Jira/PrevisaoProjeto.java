/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
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
@InfoClasse(tags = {"Previsao Projeto"}, plural = "Previsões de Projeto")
public class PrevisaoProjeto implements Serializable {

    private final List<TarefaSuperBits> tarefasProximaVersao;
    private final List<TarefaSuperBits> todasTarefas;
    private CustosDesenvolvimento custoProjetoProximaVersao;
    private CustosDesenvolvimento custoProjetoCompleto;
    private final HashMap<ItfModuloAcaoSistema, PrevisaoModulo> modulosPrevistos = new HashMap<>();

    private final AmbienteDesenvolvimento ambienteDesenvolvimento;

    public PrevisaoProjeto(List<TarefaSuperBits> ptodasTarefas) {
        this.tarefasProximaVersao = new ArrayList<>();
        ambienteDesenvolvimento = new AmbienteDesenvolvimento();
        todasTarefas = ptodasTarefas;
        defineModulosPrevistros();
        calcularValores();

        custoProjetoCompleto = new CustosDesenvolvimento(ptodasTarefas, ambienteDesenvolvimento);
        custoProjetoProximaVersao = new CustosDesenvolvimento(new ArrayList<>(), ambienteDesenvolvimento);

    }

    public final void defineModulosPrevistros() {

        for (ItfModuloAcaoSistema modulo : MapaAcoesSistema.getModulos()) {
            List<PrevisaoEntidade> previsoesEntidade = new ArrayList<>();
            List<PrevisaoGestaoEntidade> previsoesGestaoEntidade = new ArrayList<>();

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
        custoProjetoProximaVersao = new CustosDesenvolvimento(tarefasProximaVersao, ambienteDesenvolvimento);
        custoProjetoCompleto = new CustosDesenvolvimento(todasTarefas, ambienteDesenvolvimento);
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

    public CustosDesenvolvimento getCustoProjetoProximaVersao() {
        return custoProjetoProximaVersao;
    }

    public CustosDesenvolvimento getCustoProjetoCompleto() {
        return custoProjetoCompleto;
    }

    public HashMap<ItfModuloAcaoSistema, PrevisaoModulo> getModuloPrevistosPorModulo() {
        return modulosPrevistos;
    }

    public List<PrevisaoModulo> getModulosPrevistos() {
        return Lists.newArrayList(getModuloPrevistosPorModulo().values());
    }

}
