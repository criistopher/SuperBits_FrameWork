/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira.Jira;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.projeto.Jira.Jira.tempo.DataUtilJira;
import com.super_bits.projeto.Jira.Jira.tempo.InvalidDurationException;
import com.super_bits.projeto.Jira.TipoProfissional;

/**
 *
 *
 *
 * @author salvioF
 */
public class TarefaSuperBits extends ItemSimples implements ItfTarefaSuperBitsFW {

    @InfoCampo(tipo = FabCampos.ID)
    private final int id;

    private final TarefaJira tarefaJiraOrigem;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private final String nomeTarefa;
    private final TipoProfissional tipoProfissional;

    @Override
    public int getMinutosPrevistosTrabalho() {

        try {
            return (int) (DataUtilJira.getDuration(tarefaJiraOrigem.getTempoEsperado()) / 60);
        } catch (InvalidDurationException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Duração inválida", ex);
            return 0;
        }

    }

    private int getMinutosTrabalhados() {
        return 0;
    }

    private int getMinutosRestantes() {
        int minutosRestantes = getMinutosTrabalhados() - getMinutosTrabalhados();
        if (minutosRestantes < 0) {
            return minutosRestantes;
        }
        return minutosRestantes;
    }

    private int getHorasExtras() {
        int horasExtras = getMinutosTrabalhados() - getMinutosPrevistosTrabalho();
        if (horasExtras < 0) {
            return 0;
        }
        return horasExtras;

    }

    public TarefaSuperBits(TarefaJira tarefaJiraOrigem) {

        this.tarefaJiraOrigem = tarefaJiraOrigem;
        tipoProfissional = getTarefaJiraOrigem().getTipoTarefa().getTipoProfissional().getRegistro();
        id = tarefaJiraOrigem.getAcaoVinculada().getNomeUnico().hashCode();
        nomeTarefa = tarefaJiraOrigem.getNomeTarefa();
    }

    @Override
    public final TarefaJira getTarefaJiraOrigem() {
        return tarefaJiraOrigem;
    }

    @Override
    public TipoProfissional getTipoProfissionalNescessario() {
        return tipoProfissional;
    }

}
