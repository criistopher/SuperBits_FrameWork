/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.Controller.Interfaces.ItfResposta;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.controller.InfoAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.controller.ModuloSeguranca;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_paginaCadastroEntidades;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.PgUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 *
 *
 * @author rjesus
 */
@InfoPagina(nomeCurto = "US", recurso = "/sistema/seguranca/cadastroUsuario.xhtml", tags = {"cadastro de usuarios"}, acessoLivre = false)
@Named
@ViewScoped
@InfoAcaoSeguranca(acao = FabAcaoSeguranca.USUARIO_GERENCIAR)
public class PgCadastroUsuarios extends MB_paginaCadastroEntidades<UsuarioSB> {

    private List<GrupoUsuarioSB> grupoDeUsuarios;
    private List<AcaoDoSistema> acoesDisponiveis;

    @Inject
    private PgUtil pgUtil;

    public PgCadastroUsuarios() {
        super(new AcaoDoSistema[]{
            FabAcaoSeguranca.USUARIO_EDITAR.getAcaoDoSistema(),
            FabAcaoSeguranca.USUARIO_ALTERAR_STATUS.getAcaoDoSistema(),
            FabAcaoSeguranca.USUARIO_VISUALIZAR.getAcaoDoSistema()
        }, FabAcaoSeguranca.USUARIO_NOVO_USUARIO.getAcaoEntidadeFormulario(),
                FabAcaoSeguranca.USUARIO_LISTAR.getAcaoEntidadeFormulario(),
                FabAcaoSeguranca.USUARIO_SALVAR_ALTERACOES.getAcaoEntidadeController(),
                true
        );

    }

    private void atualizarDados() {
        grupoDeUsuarios = UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class, getEMPagina());
        setEntidadesListadas(UtilSBPersistencia.getListaTodos(UsuarioSB.class, getEMPagina()));
    }

    @PostConstruct
    public void inicio() {
        atualizarDados();
        xhtmlAcaoAtual = acaoListarRegistros.getXhtml();

    }

    @Override
    public void executarAcao(UsuarioSB pUsuarioSelecionado) {
        if (pUsuarioSelecionado != null) {
            setEntidadeSelecionada(pUsuarioSelecionado);

        }
        if (acaoSelecionada.equals(FabAcaoSeguranca.USUARIO_ALTERAR_STATUS.getAcaoDoSistema())) {
            ModuloSeguranca.usuarioAlterarStatus(pUsuarioSelecionado);

        }

        if (acaoSelecionada.equals(acaoNovoRegistro)) {
            setEntidadeSelecionada(new UsuarioSB());
            atualizaInformacoesDeEdicao(estadoEdicao.CRIAR);

        }

        if (acaoSelecionada.equals(FabAcaoSeguranca.USUARIO_EDITAR.getAcaoDoSistema())) {
            atualizaInformacoesDeEdicao(estadoEdicao.ALTERAR);
        }
        if (acaoSelecionada.equals(FabAcaoSeguranca.USUARIO_VISUALIZAR.getAcaoDoSistema())) {
            atualizaInformacoesDeEdicao(estadoEdicao.VISUALIZAR);

        }

        if (acaoSelecionada.isAcaoFormulario()) {

            xhtmlAcaoAtual = ((ItfAcaoFormulario) acaoSelecionada).getXhtml();
        }

        pgUtil.atualizaTelaPorID("formulario");

    }

    @Override
    protected String defineTitulo() {
        return "Cadastro de usuários";
    }

    @Override
    protected String defineNomeLink() {
        return "Gerenciar Usuários";
    }

    @Override
    protected String defineDescricao() {
        return "Pagina para gerenciamento de usuário";
    }

    @Override
    public int getId() {
        return 3;
    }

    public UsuarioSB getUsuarioSelecionado() {
        return getEntidadeSelecionada();
    }

    public void setUsuarioSelecionado(UsuarioSB pUsuarioSelecionado) {
        setEntidadeSelecionada(pUsuarioSelecionado);
    }

    public List<UsuarioSB> getUsuariosDoSistema() {
        return getEntidadesListadas();
    }

    public void setUsuariosDoSistema(List<UsuarioSB> pUsuariosDoSistema) {
        setEntidadesListadas(pUsuariosDoSistema);
    }

    public List<GrupoUsuarioSB> getGrupoDeUsuarios() {
        return grupoDeUsuarios;
    }

    public void setGrupoDeUsuarios(List<GrupoUsuarioSB> grupoDeUsuarios) {
        this.grupoDeUsuarios = grupoDeUsuarios;
    }

    public List<AcaoDoSistema> getAcoesDisponiveis() {
        return acoesDisponiveis;
    }

    public void setAcoesDisponiveis(List<AcaoDoSistema> acoesDisponiveis) {
        this.acoesDisponiveis = acoesDisponiveis;
    }

    public ItfAcaoDoSistema getAcaoSelecionada() {
        return acaoSelecionada;
    }

    public void setAcaoSelecionada(AcaoDoSistema acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    public PgUtil getPgUtil() {
        return pgUtil;
    }

    public void setPgUtil(PgUtil pgUtil) {
        this.pgUtil = pgUtil;
    }

    public void salvarAlteracoes() {

        ItfResposta resp = ModuloSeguranca.usuarioPersistirAlteracoes((UsuarioSB) getEntidadeSelecionada(), getEMPagina());

        if (resp.isSucesso()) {

            xhtmlAcaoAtual = acaoListarRegistros.getXhtml();
            atualizarDados();

            pgUtil.atualizaTelaPorID("formulario");
        }
    }

    @Override
    public void listarDados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
