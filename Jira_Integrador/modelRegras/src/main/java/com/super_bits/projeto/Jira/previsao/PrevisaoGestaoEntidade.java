/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira.previsao;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.projeto.Jira.CustosDesenvolvimento;
import com.super_bits.projeto.Jira.FabComponenteVisualRequisitos;
import com.super_bits.projeto.Jira.grupoDeTarefas.FabTipoGrupoTarefa;
import com.super_bits.projeto.Jira.ItfPrevisaoGestaoEntidade;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import com.super_bits.projeto.Jira.TipoGrupoTarefa;
import com.super_bits.projeto.Jira.requisito.ItfRequisitoDoSistema;
import com.super_bits.projeto.SBRequisito;
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
    private CustosDesenvolvimento custoDesenvolvimento;
    private ItfRequisitoDoSistema requisitoVinculado;

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

    }

    private void recualcularCustos() {
        custoDesenvolvimento = new CustosDesenvolvimento(getTarefasVinculadas(), getPrevisaoProjeto().getAmbienteDesenvolvimento());
    }

    /**
     *
     * @return
     */
    @Override
    public final CustosDesenvolvimento getCustoDesenvolvimento() {
        if (custoDesenvolvimento == null) {
            recualcularCustos();
        }
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

    @Override
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

    @Override
    public ItfRequisitoDoSistema getRequisitoVinculado() {
        if (requisitoVinculado == null) {
            requisitoVinculado = SBRequisito.getRequisitoServices().getREquisitoGestao(this);
        }
        return requisitoVinculado;
    }

    public void setRequisitoVinculado(ItfRequisitoDoSistema requisitoVinculado) {
        this.requisitoVinculado = requisitoVinculado;
    }

    @Override
    public boolean isTemRequisitoVinculado() {
        return getRequisitoVinculado() != null;
    }

    public ComponenteVisualSB getVisualizacaoOpcoes() {
        if (isTemRequisitoVinculado()) {
            return FabComponenteVisualRequisitos.OPCOES_ELEMENTO_VINCULADO.getComponente();
        } else {
            return FabComponenteVisualRequisitos.OPCOES_ELEMENTO_NAO_VINCULADA.getComponente();
        }
    }

    public ComponenteVisualSB getVisualizacaoDescricao() {
        if (isTemRequisitoVinculado()) {
            return FabComponenteVisualRequisitos.DESCRICAO_ELEMENTO_VINCULADO.getComponente();
        } else {
            return FabComponenteVisualRequisitos.DECRICAO_ELEMENTO_NAO_VINCULADO.getComponente();
        }
    }

}