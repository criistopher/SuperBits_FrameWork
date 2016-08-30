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
public class PrevisaoGestaoEntidade implements ItfPrevisaoGestaoEntidade {

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

    @Override
    public void adicionarTarefa(TarefaSuperBits tr) {
        if (!tarefasVinculadas.contains(tr)) {
            tarefasVinculadas.add(tr);
        }

    }

    @Override
    public final void atualizaHorasProgramadas() {

        int minutosPrevistos = 0;
        for (TarefaSuperBits tr : tarefasVinculadas) {
            minutosPrevistos += tr.getMinutosPrevistosTrabalho();
        }
        horasProgramadas = minutosPrevistos / 60;
    }

    @Override
    public ItfAcaoGerenciarEntidade getGestao() {
        return gestao;
    }

    @Override
    public void setGestao(ItfAcaoGerenciarEntidade gestao) {
        this.gestao = gestao;
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return modulo;
    }

    @Override
    public void setModulo(ItfModuloAcaoSistema modulo) {
        this.modulo = modulo;
    }

    @Override
    public List<TarefaSuperBits> getTarefasVinculadas() {
        return tarefasVinculadas;
    }

    @Override
    public Class getEntidadeVinculada() {
        return entidadeVinculada;
    }

    @Override
    public int getQtdAcoesDeFormulario() {
        return qtdAcoesDeFormulario;
    }

    @Override
    public int getQtdAoesDeController() {
        return qtdAoesDeController;
    }

    @Override
    public int getHorasProgramadas() {

        return horasProgramadas;
    }

}
