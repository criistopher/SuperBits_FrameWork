/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class PrevisaoGestaoEntidade {

    private ItfAcaoGerenciarEntidade gestao;
    private ItfModuloAcaoSistema modulo;
    private final List<TarefaSuperBits> tarefasVinculadas;

    private final Class entidadeVinculada;
    private int qtdAcoesDeFormulario;
    private int qtdAoesDeController;
    private int horasProgramadas;

    public PrevisaoGestaoEntidade(ItfAcaoGerenciarEntidade pAcaoGEstao, List<TarefaSuperBits> pTarefasVinculadas) {
        this.gestao = pAcaoGEstao;
        entidadeVinculada = pAcaoGEstao.getClasseRelacionada();
        modulo = pAcaoGEstao.getModulo();
        for (ItfAcaoDoSistema acao : pAcaoGEstao.getAcoesVinculadas()) {
            if (acao.isUmaAcaoController()) {
                qtdAoesDeController++;
            } else {
                qtdAcoesDeFormulario++;
            }

        }
        tarefasVinculadas = pTarefasVinculadas;
        atualizaHorasProgramadas();

    }

    public void adicionarTarefa(TarefaSuperBits tr) {
        if (!tarefasVinculadas.contains(tr)) {
            tarefasVinculadas.add(tr);
        }

    }

    public final void atualizaHorasProgramadas() {

        int minutosPrevistos = 0;
        for (TarefaSuperBits tr : tarefasVinculadas) {
            minutosPrevistos += tr.getMinutosPrevistosTrabalho();
        }
        horasProgramadas = minutosPrevistos / 60;
    }

    public ItfAcaoGerenciarEntidade getGestao() {
        return gestao;
    }

    public void setGestao(ItfAcaoGerenciarEntidade gestao) {
        this.gestao = gestao;
    }

    public ItfModuloAcaoSistema getModulo() {
        return modulo;
    }

    public void setModulo(ItfModuloAcaoSistema modulo) {
        this.modulo = modulo;
    }

    public List<TarefaSuperBits> getTarefasVinculadas() {
        return tarefasVinculadas;
    }

    public Class getEntidadeVinculada() {
        return entidadeVinculada;
    }

    public int getQtdAcoesDeFormulario() {
        return qtdAcoesDeFormulario;
    }

    public int getQtdAoesDeController() {
        return qtdAoesDeController;
    }

    public int getHorasProgramadas() {

        return horasProgramadas;
    }

}
