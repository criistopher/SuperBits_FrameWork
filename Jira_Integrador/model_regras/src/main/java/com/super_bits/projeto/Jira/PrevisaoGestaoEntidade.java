/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class PrevisaoGestaoEntidade extends ItemSimples implements ItfPrevisaoGestaoEntidade {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String descricao;
    private ItfAcaoGerenciarEntidade gestao;
    private ItfModuloAcaoSistema modulo;
    private final List<TarefaSuperBits> tarefasVinculadas;
    private final Class entidadeVinculada;
    private int qtdAcoesDeFormulario;
    private int qtdAoesDeController;
    private int horasProgramadas;
    private final PrevisaoProjeto previsaoProjeto;
    private final CustosDesenvolvimento custoDesenvolvimento;

    public PrevisaoGestaoEntidade(ItfAcaoGerenciarEntidade pAcaoGEstao, List<TarefaSuperBits> pTarefasVinculadas, PrevisaoProjeto pPrevisaoProjeto) {
        this.gestao = pAcaoGEstao;
        entidadeVinculada = pAcaoGEstao.getClasseRelacionada();
        modulo = pAcaoGEstao.getModulo();
        descricao = "Previsão de desenvolvimento para " + pAcaoGEstao.getNomeAcao();
        id = descricao.hashCode();
        for (ItfAcaoDoSistema acao : pAcaoGEstao.getAcoesVinculadas()) {
            if (acao.isUmaAcaoController()) {
                qtdAoesDeController++;
            } else {
                qtdAcoesDeFormulario++;
            }

        }
        tarefasVinculadas = pTarefasVinculadas;
        atualizaHorasProgramadas();
        previsaoProjeto = pPrevisaoProjeto;
        custoDesenvolvimento = new CustosDesenvolvimento(getTarefasVinculadas(), getPrevisaoProjeto().getAmbienteDesenvolvimento());
    }

    /**
     *
     * @return
     */
    @Override
    public final CustosDesenvolvimento getCustoDesenvolvimento() {
        return custoDesenvolvimento;
    }

    @Override
    public final PrevisaoProjeto getPrevisaoProjeto() {
        return previsaoProjeto;
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
    public final List<TarefaSuperBits> getTarefasVinculadas() {
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getNomeDoAgrupamento() {
        return getGestao().getNomeAcao();
    }

    @Override
    public String getIcone() {
        return getGestao().getIconeAcao();
    }

    @Override
    public TipoGrupoTarefa getTipoGrupoTarefa() {
        return FabTipoGrupoTarefa.GESTAO.getRegistro();
    }

}
