/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.List;

/**
 *
 * @author salvioF
 */
public interface ItfPrevisaoGestaoEntidade {

    void adicionarTarefa(TarefaSuperBits tr);

    void atualizaHorasProgramadas();

    Class getEntidadeVinculada();

    ItfAcaoGerenciarEntidade getGestao();

    int getHorasProgramadas();

    ItfModuloAcaoSistema getModulo();

    int getQtdAcoesDeFormulario();

    int getQtdAoesDeController();

    List<TarefaSuperBits> getTarefasVinculadas();

    void setGestao(ItfAcaoGerenciarEntidade gestao);

    void setModulo(ItfModuloAcaoSistema modulo);

    public CustosDesenvolvimento getCustoDesenvolvimento();

    public PrevisaoProjeto getPrevisaoProjeto();
}
