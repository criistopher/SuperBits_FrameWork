/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class PrevisaoEntidade extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    private ItfModuloAcaoSistema modulo;
    private List<TarefaSuperBits> tarefasVinculadas;
    private Class entidadeVinculada;

    private int HorasTrabalhasdas;

    public PrevisaoEntidade(ItfModuloAcaoSistema modulo, List<TarefaSuperBits> tarefasVinculadas, Class entidadeVinculada) {
        this.modulo = modulo;
        this.tarefasVinculadas = tarefasVinculadas;
        this.entidadeVinculada = entidadeVinculada;
        nome = entidadeVinculada.getSimpleName();
        id = nome.hashCode();
    }

    public ItfModuloAcaoSistema getModulo() {
        return modulo;
    }

    public List<TarefaSuperBits> getTarefasVinculadas() {
        return tarefasVinculadas;
    }

    public Class getEntidadeVinculada() {
        return entidadeVinculada;
    }

    public int getHorasTrabalhasdas() {
        return HorasTrabalhasdas;
    }

}
