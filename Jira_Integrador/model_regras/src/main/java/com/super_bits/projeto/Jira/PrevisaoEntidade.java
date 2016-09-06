/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class PrevisaoEntidade extends ItemSimples implements ItfPrevisaoEntidade {

    @InfoCampo(tipo = FabCampos.ID)
    private final int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private final String nome;
    private final ItfModuloAcaoSistema modulo;
    private final List<TarefaSuperBits> tarefasVinculadas;
    private final Class entidadeVinculada;
    private final CustosDesenvolvimento custoDesenvolvimento;
    private int HorasTrabalhasdas;
    private final PrevisaoProjeto previsaoProjeto;
    private EstruturaDeEntidade estruturaDeEntidade;

    public PrevisaoEntidade(ItfModuloAcaoSistema modulo,
            List<TarefaSuperBits> pTarefasVinculadas,
            Class entidadeVinculada, PrevisaoProjeto pPrevisaoProjeto) {
        this.modulo = modulo;
        this.tarefasVinculadas = pTarefasVinculadas;
        this.entidadeVinculada = entidadeVinculada;
        nome = entidadeVinculada.getSimpleName();
        id = nome.hashCode();
        previsaoProjeto = pPrevisaoProjeto;
        custoDesenvolvimento = new CustosDesenvolvimento(getTarefasVinculadas(), getPrevisaoProjeto().getAmbienteDesenvolvimento());
    }

    @Override
    public final PrevisaoProjeto getPrevisaoProjeto() {
        return previsaoProjeto;
    }

    @Override
    public CustosDesenvolvimento getCustoDesenvolvimento() {
        return custoDesenvolvimento;
    }

    @Override
    public ItfModuloAcaoSistema getModulo() {
        return modulo;
    }

    @Override
    public final List<TarefaSuperBits> getTarefasVinculadas() {
        return tarefasVinculadas;
    }

    @Override
    public Class getEntidadeVinculada() {
        return entidadeVinculada;
    }

    @Override
    public int getHorasTrabalhasdas() {
        return HorasTrabalhasdas;
    }

    @Override
    public EstruturaDeEntidade getEstruturaDeEntidade() {
        return estruturaDeEntidade;
    }

}
