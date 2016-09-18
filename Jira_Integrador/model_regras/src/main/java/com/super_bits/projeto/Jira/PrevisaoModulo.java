/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class PrevisaoModulo extends ItemSimples implements ItfPrevisaoModulo {

    @InfoCampo(tipo = FabCampos.ID)
    private final int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private final String nome;
    private final List<PrevisaoGestaoEntidade> gestoesPrevistas;
    private final List<PrevisaoEntidade> entidadesPrevistas;
    private final PrevisaoProjeto previsaoProjeto;
    private final CustosDesenvolvimento custoDesenvolvimento;
    private List<TarefaSuperBits> tarefasVinculadas;

    public PrevisaoModulo(List<PrevisaoGestaoEntidade> gestoesPrevistas,
            List<PrevisaoEntidade> entidadesPrevistas, PrevisaoProjeto pPrevisaoProjeto) {
        this.gestoesPrevistas = gestoesPrevistas;
        this.entidadesPrevistas = entidadesPrevistas;
        nome = getModuloAssociado().getNome();
        id = nome.hashCode();
        previsaoProjeto = pPrevisaoProjeto;
        List<TarefaSuperBits> todasTarefas = new ArrayList<>();

        this.entidadesPrevistas.stream().forEach((prevEntidade) -> {
            todasTarefas.addAll(prevEntidade.getTarefasVinculadas());
        });
        gestoesPrevistas.stream().forEach((prevGestao) -> {
            todasTarefas.addAll(prevGestao.getTarefasVinculadas());
        });
        tarefasVinculadas = todasTarefas;
        custoDesenvolvimento = new CustosDesenvolvimento(todasTarefas, previsaoProjeto.getAmbienteDesenvolvimento());
    }

    @Override
    public PrevisaoProjeto getPrevisaoProjeto() {
        return previsaoProjeto;
    }

    @Override
    public List<PrevisaoGestaoEntidade> getGestoesPrevistas() {
        return gestoesPrevistas;
    }

    @Override
    public List<PrevisaoEntidade> getEntidadesPrevistas() {
        return entidadesPrevistas;
    }

    @Override
    public final ItfModuloAcaoSistema getModuloAssociado() {
        PrevisaoGestaoEntidade gestaoQualquer = getGestoesPrevistas().get(0);
        if (gestaoQualquer == null) {
            return FabModulosSistemaSB.PAGINAS_DO_SISTEMA.getRegistro();
        } else {
            return gestaoQualquer.getModulo();
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public CustosDesenvolvimento getCustoDesenvolvimento() {
        return custoDesenvolvimento;
    }

    /**
     *
     * @return
     */
    @Override
    public List<TarefaSuperBits> getTarefasVinculadas() {
        return tarefasVinculadas;
    }

    @Override
    public int getHorasProgramadas() {
        return getCustoDesenvolvimento().getHorasTotal();
    }

    @Override
    public String getNomeDoAgrupamento() {
        return getModuloAssociado().getNome();
    }

    @Override
    public String getIcone() {
        return getModuloAssociado().getIconeDaClasse();
    }

    @Override
    public String getDescricao() {
        return getModuloAssociado().getDescricao();
    }

    @Override
    public TipoGrupoTarefa getTipoGrupoTarefa() {
        return FabTipoGrupoTarefa.MODULO.getRegistro();
    }

}
