/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.List;

/**
 *
 * @author salvioF
 */
public interface ItfGrupoDeTarefas {

    int getHorasProgramadas();

    List<TarefaSuperBits> getTarefasVinculadas();

    public CustosDesenvolvimento getCustoDesenvolvimento();

    public PrevisaoProjeto getPrevisaoProjeto();

    public String getNomeDoAgrupamento();

    public String getIcone();

    public String getDescricao();

    public TipoGrupoTarefa getTipoGrupoTarefa();

}
