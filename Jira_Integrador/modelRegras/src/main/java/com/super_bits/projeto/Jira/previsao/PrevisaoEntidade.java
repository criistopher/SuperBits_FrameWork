/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.projeto.Jira.previsao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemGenerico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.projeto.Jira.CustosDesenvolvimento;
import com.super_bits.projeto.Jira.FabComponenteVisualRequisitos;
import com.super_bits.projeto.Jira.ItfPrevisaoEntidade;
import com.super_bits.projeto.Jira.Jira.TarefaSuperBits;
import com.super_bits.projeto.Jira.TipoGrupoTarefa;
import com.super_bits.projeto.Jira.grupoDeTarefas.FabTipoGrupoTarefa;
import com.super_bits.projeto.Jira.requisito.ItfRequisitoDoSistema;
import com.super_bits.projeto.SBRequisito;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoClasse(plural = "Previsões de Entidades", tags = "Previsão Projeto Entidade")
public class PrevisaoEntidade extends ItemSimples implements ItfPrevisaoEntidade {

    @InfoCampo(tipo = FabCampos.ID)
    private final int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private final String nome;

    private final List<TarefaSuperBits> tarefasVinculadas;
    private final Class entidadeVinculada;
    private CustosDesenvolvimento custoDesenvolvimento;
    private int HorasTrabalhasdas;
    private final PrevisaoProjeto previsaoProjeto;
    private EstruturaDeEntidade estruturaDeEntidade;
    private ItfRequisitoDoSistema requisitoVinculado;
    private double percentualTrabalhado;

    public PrevisaoEntidade(
            List<TarefaSuperBits> pTarefasVinculadas,
            Class entidadeVinculada, PrevisaoProjeto pPrevisaoProjeto) {

        this.tarefasVinculadas = pTarefasVinculadas;
        this.entidadeVinculada = entidadeVinculada;
        try {
            ItemGenerico item = (ItemGenerico) entidadeVinculada.newInstance();
            estruturaDeEntidade = item.getEstruturaDaEntidade();
        } catch (InstantiationException | IllegalAccessException ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro instanciando classe para analize de estrutura", ex);
        }

        nome = entidadeVinculada.getSimpleName();
        id = nome.hashCode();
        previsaoProjeto = pPrevisaoProjeto;

    }

    @Override
    public final PrevisaoProjeto getPrevisaoProjeto() {
        return previsaoProjeto;
    }

    public void recalcularCustos() {
        custoDesenvolvimento = new CustosDesenvolvimento(getTarefasVinculadas(), getPrevisaoProjeto().getAmbienteDesenvolvimento());
    }

    @Override
    public CustosDesenvolvimento getCustoDesenvolvimento() {
        if (custoDesenvolvimento == null) {
            recalcularCustos();
        }
        return custoDesenvolvimento;
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

    @Override
    public int getHorasProgramadas() {
        return getCustoDesenvolvimento().getHorasTotal();
    }

    @Override
    public String getNomeDoAgrupamento() {
        return estruturaDeEntidade.getTags().get(0);
    }

    @Override
    public String getIcone() {
        return estruturaDeEntidade.getIcone();
    }

    @Override
    public String getDescricao() {
        return estruturaDeEntidade.getDescricao();
    }

    @Override
    public TipoGrupoTarefa getTipoGrupoTarefa() {
        return FabTipoGrupoTarefa.TABELA.getRegistro();
    }

    public ItfRequisitoDoSistema getRequisitoVinculado() {
        if (requisitoVinculado == null) {
            requisitoVinculado = SBRequisito.getRequisitoServices().getRequisitoEntidade(this);
        }
        return requisitoVinculado;
    }

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

    public void setPercentualTrabalhado(double percentualTrabalhado) {
        this.percentualTrabalhado = percentualTrabalhado;
    }

    @Override
    public double getPercentualTrabalhado() {
        return percentualTrabalhado;
    }

}
