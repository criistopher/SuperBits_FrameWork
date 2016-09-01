/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.InomeProjetoI.paginas;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.mc.FabAcaoPrevisaoProjeto;
import com.super_bits.mc.InfoAcaoPrevisaoProjeto;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.fabrica.UtilSBCoreFabrica;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaSession;
import com.super_bits.projeto.Jira.AmbienteDesenvolvimento;
import com.super_bits.projeto.Jira.DesenvolvedorProjetoSB;
import com.super_bits.projeto.Jira.FabTipoProfissional;
import com.super_bits.projeto.Jira.Jira.MapaTarefasProjeto;
import com.super_bits.projeto.Jira.PrevisaoProjeto;
import com.super_bits.projeto.Jira.TipoProfissional;
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

    private AmbienteDesenvolvimento ambienteDesenvolvimento;

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

        acaoSelecionada = FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_RESUMO_GERAL.getRegistro();
        xhtmlAcaoAtual = FabAcaoPrevisaoProjeto.ACAO_PREVISAO_FRM_RESUMO_GERAL.getRegistro().getComoFormulario().getXhtml();

        tiposProfissionais = UtilSBCoreFabrica.getListaTodosRegistrosDaFabrica(FabTipoProfissional.class);
// executarAcaoSelecionada();
    }

    public void recarregarValores() {
        previsaoProjeto = new PrevisaoProjeto(MapaTarefasProjeto.getTodasTarefas());
        ambienteDesenvolvimento = new AmbienteDesenvolvimento();
    }

    public PrevisaoProjeto getPrevisaoProjeto() {
        return previsaoProjeto;
    }

    public void setPrevisaoProjeto(PrevisaoProjeto previsaoProjeto) {
        this.previsaoProjeto = previsaoProjeto;
    }

    public AmbienteDesenvolvimento getAmbienteDesenvolvimento() {
        return ambienteDesenvolvimento;
    }

    public void setAmbienteDesenvolvimento(AmbienteDesenvolvimento ambienteDesenvolvimento) {
        this.ambienteDesenvolvimento = ambienteDesenvolvimento;
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

}
