/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira.Jira;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaDeAcoes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class MapaTarefasProjeto {

    private static boolean tarefasCriadas = false;
    private static MapaTarefas mapaTarefasDoProjeto;

    public static synchronized void criarTarefas() {
        if (tarefasCriadas) {
            return;
        }
        mapaTarefasDoProjeto = new MapaTarefas(new MapaDeAcoes(SBCore.getFabricasDeAcaoDoSistema()), SBPersistencia.getNomeBancoPadrao());
        tarefasCriadas = true;
    }

    public static List<TarefaSuperBits> getTodasTarefas() {
        criarTarefas();

        return mapaTarefasDoProjeto.getTodasTarefas();
    }

    public static List<TarefaSuperBits> getTarefasDaGestao(ItfAcaoGerenciarEntidade pAcaoGestao) {
        criarTarefas();
        return mapaTarefasDoProjeto.getTarefasDaGestao(pAcaoGestao);
    }

    public static List<TarefaSuperBits> getTarefasDaTabela(Class pEntidade) {
        criarTarefas();
        return mapaTarefasDoProjeto.getTarefasDaTabela(pEntidade);
    }

}
