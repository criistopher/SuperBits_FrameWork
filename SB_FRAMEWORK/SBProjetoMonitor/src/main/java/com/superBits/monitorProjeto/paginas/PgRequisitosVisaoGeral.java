/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superBits.monitorProjeto.paginas;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.UtilSBCoreFabrica;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaSession;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.projeto.Jira.AmbienteDesenvolvimento;
import com.super_bits.projeto.Jira.DesenvolvedorProjetoSB;
import com.super_bits.projeto.Jira.FabTipoProfissional;
import com.super_bits.projeto.Jira.Jira.ItfTarefaSuperBitsFW;
import com.super_bits.projeto.Jira.Jira.MapaTarefasProjeto;
import com.super_bits.projeto.Jira.PrevisaoEntidade;
import com.super_bits.projeto.Jira.PrevisaoGestaoEntidade;
import com.super_bits.projeto.Jira.PrevisaoModulo;
import com.super_bits.projeto.Jira.PrevisaoProjeto;
import com.super_bits.projeto.Jira.TipoProfissional;
import com.super_bits.projeto.controller.FabAcaoPrevisaoProjeto;
import com.super_bits.projeto.controller.InfoAcaoPrevisaoProjeto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author salvioF
 */
@InfoPagina(nomeCurto = "reqAtual", tags = {"Requisitos em desenvolvimento"})
@InfoAcaoPrevisaoProjeto(acao = FabAcaoPrevisaoProjeto.ACAO_PREVISAO_MB_GESTAO)
@ViewScoped
@Named
public class PgRequisitosVisaoGeral extends MB_PaginaSession {

    private PrevisaoProjeto previsaoProjeto;
    private PrevisaoGestaoEntidade prevGestaoSelecionado;
    private PrevisaoEntidade prevEntidadeSelecionada;

    private ItfTarefaSuperBitsFW tarefaDetalhesSelecionado;

    private PrevisaoModulo prevMooduloSelecionado;

    private ItfAcaoFormulario acaoVisaoGeralGestao;
    private ItfAcaoFormulario acaoDetalhesGestao;
    private ItfAcaoFormulario acaoDetalhesTabela;

    private ItfAcaoFormulario acaoVisaoGeralModulos;
    private ItfAcaoFormulario acaoVisaoGeralTabelas;
    private ItfAcaoFormulario acaoDetalheModulo;

    private final List<ItfAcaoDoSistema> acoesPrincipal;

    private List<TipoProfissional> tiposProfissionais;
    private TipoProfissional tipoProfissionalSelecionado;

    public void adicionarNovoProfissional() {

        if (tipoProfissionalSelecionado == null) {
            SBCore.getCentralDeMensagens().enviarMsgErroAoUsuario("Selecione um tipo de profissional para adicionar");
        }
        int qtdadeDesenvolvedors = previsaoProjeto.getAmbienteDesenvolvimento().getDesenvolvedores().size();
        previsaoProjeto.getAmbienteDesenvolvimento().getDesenvolvedores().add(
                new DesenvolvedorProjetoSB(tipoProfissionalSelecionado.getNome() + qtdadeDesenvolvedors,
                        tipoProfissionalSelecionado, 5));
        previsaoProjeto.calcularValores();
    }

    public PgRequisitosVisaoGeral() {
        this.acoesPrincipal = new ArrayList<>();
    }

    @PostConstruct
    public void inicio() {

        recarregarValores();
        acoesPrincipal.add(FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_VISAO_GERAL_BANCO_DE_DADOS.getAcaoDoSistema());
        acoesPrincipal.add(FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_VISAO_GERAL_GESTAO.getAcaoDoSistema());
        acoesPrincipal.add(FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_VISAO_GERAL_MODULOS.getAcaoDoSistema());
        acoesPrincipal.add(FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_RESUMO_GERAL.getRegistro());
        acoesPrincipal.add((FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_AMBIENTE_DESENVOLVIMENTO.getAcaoDoSistema()));
        acaoVisaoGeralGestao = (ItfAcaoFormulario) FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_VISAO_GERAL_BANCO_DE_DADOS.getAcaoDoSistema();
        acaoDetalhesGestao = (ItfAcaoFormulario) FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_DETALHES_ACAO_GESTAO.getAcaoDoSistema();
        acaoDetalhesTabela = (ItfAcaoFormulario) FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_DETALHES_TABELA_BANCO_DADDOS.getAcaoDoSistema();
        acaoVisaoGeralGestao = (ItfAcaoFormulario) FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_VISAO_GERAL_GESTAO.getAcaoDoSistema();
        acaoVisaoGeralTabelas = (ItfAcaoFormulario) FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_VISAO_GERAL_BANCO_DE_DADOS.getAcaoDoSistema();
        acaoDetalheModulo = (ItfAcaoFormulario) FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_DETALHES_MODULO.getAcaoDoSistema();
        acaoSelecionada = FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_RESUMO_GERAL.getRegistro();

        xhtmlAcaoAtual = FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_RESUMO_GERAL.getRegistro().getComoFormulario().getXhtml();

        tiposProfissionais = UtilSBCoreFabrica.getListaTodosRegistrosDaFabrica(FabTipoProfissional.class);
// executarAcaoSelecionada();
    }

    public void recarregarValores() {
        previsaoProjeto = new PrevisaoProjeto(MapaTarefasProjeto.getTodasTarefas());

    }

    public PrevisaoProjeto getPrevisaoProjeto() {
        return previsaoProjeto;
    }

    public void setPrevisaoProjeto(PrevisaoProjeto previsaoProjeto) {
        this.previsaoProjeto = previsaoProjeto;
    }

    public AmbienteDesenvolvimento getAmbienteDesenvolvimento() {
        return getPrevisaoProjeto().getAmbienteDesenvolvimento();
    }

    public List<ItfAcaoDoSistema> getAcoesPrincipal() {
        return acoesPrincipal;
    }

    public List<TipoProfissional> getTiposProfissionais() {
        return tiposProfissionais;
    }

    public TipoProfissional getTipoProfissionalSelecionado() {
        return tipoProfissionalSelecionado;
    }

    public void setTipoProfissionalSelecionado(TipoProfissional tipoProfissionalSelecionado) {
        this.tipoProfissionalSelecionado = tipoProfissionalSelecionado;
    }

    public PrevisaoGestaoEntidade getPrevGestaoSelecionado() {
        return prevGestaoSelecionado;
    }

    public void setPrevGestaoSelecionado(PrevisaoGestaoEntidade prevGestaoSelecionado) {
        this.prevGestaoSelecionado = prevGestaoSelecionado;
    }

    public PrevisaoModulo getPrevMooduloSelecionado() {
        return prevMooduloSelecionado;
    }

    public void setPrevMooduloSelecionado(PrevisaoModulo prevMooduloSelecionado) {
        this.prevMooduloSelecionado = prevMooduloSelecionado;
    }

    public PrevisaoEntidade getPrevEntidadeSelecionada() {
        return prevEntidadeSelecionada;
    }

    public void setPrevEntidadeSelecionada(PrevisaoEntidade prevEntidadeSelecionada) {
        this.prevEntidadeSelecionada = prevEntidadeSelecionada;
    }

    public ItfAcaoFormulario getAcaoVisaoGeralGestao() {
        return acaoVisaoGeralGestao;
    }

    public ItfAcaoFormulario getAcaoDetalhesGestao() {
        return acaoDetalhesGestao;
    }

    public ItfAcaoFormulario getAcaoDetalhesTabela() {
        return acaoDetalhesTabela;
    }

    public ItfAcaoFormulario getAcaoVisaoGeralModulos() {
        return acaoVisaoGeralModulos;
    }

    public ItfAcaoFormulario getAcaoVisaoGeralTabelas() {
        return acaoVisaoGeralTabelas;
    }

    public ItfAcaoFormulario getAcaoDetalheModulo() {
        return acaoDetalheModulo;
    }

    public void setAcaoDetalheModulo(ItfAcaoFormulario acaoDetalheModulo) {
        this.acaoDetalheModulo = acaoDetalheModulo;
    }

    public ItfTarefaSuperBitsFW getTarefaDetalhesSelecionado() {
        return tarefaDetalhesSelecionado;
    }

    public void setTarefaDetalhesSelecionado(ItfTarefaSuperBitsFW tarefaDetalhesSelecionado) {
        this.tarefaDetalhesSelecionado = tarefaDetalhesSelecionado;
    }

}
