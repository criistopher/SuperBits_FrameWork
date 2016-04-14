/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.Controller.Interfaces.ItfResposta;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.controller.InfoAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.controller.ModuloSeguranca;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces.BP_AutoComplete;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Acao;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Bean;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_IdWidget;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * Managed Bean para pagina de pemissões
 *
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 20/12/2015
 * @version 1.0
 *
 */
@InfoPagina(nomeCurto = "PM", tags = {"Permissoes"})
@Named
@ViewScoped
@InfoAcaoSeguranca(acao = FabAcaoSeguranca.GRUPOS_MB_GERENCIAR)
public class PgPermissoes extends MB_PaginaConversation {

    @Inject
    private PgUtil paginaUtil;
    @InfoMB_Bean(descricao = "Lista todos os modulos")
    private List<ModuloAcaoSistema> modulos;
    @InfoMB_Bean(descricao = "Lista todos os grupos")
    private List<GrupoUsuarioSB> grupos;
    @InfoMB_Bean(descricao = "Armazena o grupo selecionado")
    private GrupoUsuarioSB grupoSelecionado;

    @InfoMB_IdWidget(descricao = "Area onde as definições de segurança serão exibidas")
    private final String idDefinicoesDeSeguranca = "definiçãoDeSeguranca";

    private List<ItfAcaoDoSistema> acoesListarGrupos;

    private AcaoDoSistema acaoselecionada;
    private boolean novogrupo;
    private boolean bloquearEdicao;
    private UsuarioSB usuarioSelecionado;

    private BP_AutoComplete<UsuarioSB> autocompleteUsuario;

    private final AcaoDoSistema acaoListarGrupos = (AcaoDoSistema) FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoDoSistema();
    private final AcaoDoSistema acaoListarUsuarios = (AcaoDoSistema) FabAcaoSeguranca.GRUPO_FRM_LIST_USUARIOS.getAcaoDoSistema();
    private final AcaoDoSistema acaoSalvarPermissoes = (AcaoDoSistema) FabAcaoSeguranca.GRUPO__CTR_SALVAR_MERGE.getAcaoDoSistema();
    private final AcaoDoSistema acaoNovoGrupo = (AcaoDoSistema) FabAcaoSeguranca.GRUPO_FRM_NOVO.getAcaoDoSistema();
    //private final AcaoDoSistema acaoRemoverUsuarioDoGrupo = (AcaoDoSistema) FabAcaoCadastros.GRP_USUARIO_REMOVE.getAcaoDoSistema();
    //private final AcaoDoSistema acaoAdicionarUsuarioNoGrupo = (AcaoDoSistema) FabAcaoCadastros.GRP_USUARIO_ADD.getAcaoDoSistema();
    private final AcaoDoSistema acaoListarGruposDoUsuario = (AcaoDoSistema) FabAcaoSeguranca.USUARIO_FRM_LISTARGRUPOS.getAcaoDoSistema();

    private String xhtmlAcaoAtual = FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoEntidadeFormulario().getXhtml();

    private void configurarSelecaoDeAcoes() {
        for (ModuloAcaoSistema modulo : modulos) {
            modulo.getSelecaoAcoes().clear();
            for (AcaoDoSistema acao : ModuloSeguranca.listarAcoesDoGrupo(grupoSelecionado, modulo)) {
                modulo.getSelecaoAcoes().add(acao);
            }
        }
    }

    public PgPermissoes() {
        super();
    }

    private void atualizarDados() {

        modulos = UtilSBPersistencia.getListaTodos(ModuloAcaoSistema.class, getEMPagina());
        grupos = UtilSBPersistencia.getListaTodos(GrupoUsuarioSB.class, getEMPagina());

    }

    public void listarGrupoUsuario(UsuarioSB pUsuario) {
        usuarioSelecionado = pUsuario;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgVerGruposUsuario').show();");
        paginaUtil.atualizaTelaPorID("formulario");

    }

    public void selecionarUsuarioParaInclusaoNoGrupo() {

        UtilSBWP_JSFTools.executarJavaScript("PF('dlgAddUsuario').show();");
    }

    private void limparSelecaoDeAcoes() {
        for (ModuloAcaoSistema modulo : modulos) {
            modulo.getSelecaoAcoes().clear();

        }
    }

    /**
     *
     *
     *
     *
     *
     * Executa uma ação determinada do grupo
     *
     * @param pgrupoUsuario
     */
    @InfoMB_Acao(descricao = "Atualiza a visualização de ação e Seta o grupo selecionado")
    public void executarAcaoListaGrupo(GrupoUsuarioSB pgrupoUsuario) {

        if (acaoselecionada == null) {
            UtilSBWP_JSFTools.mensagens().enviaMensagem(FabMensagens.ALERTA.getMsgUsuario("Ação não enviada" + pgrupoUsuario + acaoselecionada));
            return;
        } else {
            //  UtilSBWP_JSFTools.mensagens().enviaMensagem(FabMensagens.ALERTA.getMsgUsuario("Ação=" + acaoListarGrupoSelecionada.getNomeAcao() + acaoListarGrupoSelecionada.getUrlAction()));
            //  UtilSBWP_JSFTools.mensagens().enviaMensagem(FabMensagens.ALERTA.getMsgUsuario("ID=" + acaoListarGrupoSelecionada.getId() + "--" + acaoListarGrupos.getId()));

        }

        if (!acaoselecionada.equals(acaoNovoGrupo) & pgrupoUsuario == null) {
            UtilSBWP_JSFTools.mensagens().enviaMensagem(FabMensagens.ALERTA.getMsgUsuario("Grupo não enviado" + pgrupoUsuario + acaoselecionada));
            return;
        }

        if (acaoselecionada.equals(FabAcaoSeguranca.GRUPO_FRM_NOVO.getAcaoDoSistema())) {
            grupoSelecionado = new GrupoUsuarioSB();
            limparSelecaoDeAcoes();
            bloquearEdicao = false;
            novogrupo = true;
            xhtmlAcaoAtual = getXhtmlAcaoAtual();
            paginaUtil.atualizaTelaPorID("formulario");
            return;
        }
        if (pgrupoUsuario != null) {
            grupoSelecionado = pgrupoUsuario;
        }
        // caso opção seja alterar Status
        if (acaoselecionada.getId() == FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getRegistro().getId()) {
            ModuloSeguranca.grupoAlterarStatus(pgrupoUsuario);
            paginaUtil.atualizaTelaPorID("formulario");
            return;
        }

        // caso opção seja Salvar REgistro
        if (acaoselecionada.getId() == FabAcaoSeguranca.GRUPO__CTR_SALVAR_MERGE.getAcaoDoSistema().getId()) {

            ItfResposta resp = ModuloSeguranca.grupoDeUsuariosSalvarAlteracoes(pgrupoUsuario, modulos, getEMPagina());
            if (resp.isSucesso()) {
                xhtmlAcaoAtual = getXhtmlAcaoAtual();
                if (novogrupo) {
                    atualizarDados();

                } else {
                    atualizarDados();
                }
                paginaUtil.atualizaTelaPorID("formulario");
            }

            return;

        }
        if (acaoselecionada.getId() == FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoDoSistema().getId()) {
            configurarSelecaoDeAcoes();
            bloquearEdicao = false;
            novogrupo = false;
        }

        if (acaoselecionada.getId() == FabAcaoSeguranca.GRUPO_FRM_VISUALIZAR.getAcaoDoSistema().getId()) {
            configurarSelecaoDeAcoes();
            novogrupo = false;
            bloquearEdicao = true;

        }

        xhtmlAcaoAtual = getXhtmlAcaoAtual();

        paginaUtil.atualizaTelaPorID("formulario");

    }

    private void atualizarAcoesSelecionadas() {
        //ModuloCadastros.grupoDeUsuarios(grupoSelecionado, acoesSelecionadas);
    }

    @PostConstruct
    public void inicio() {
        System.out.println("PostConstruct Permissoes");
        atualizarDados();
        acoesListarGrupos = new ArrayList<>();
        acoesListarGrupos.add(FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoDoSistema());
        acoesListarGrupos.add(FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema());
        acoesListarGrupos.add(FabAcaoSeguranca.GRUPO_FRM_VISUALIZAR.getAcaoDoSistema());

        autocompleteUsuario = new BP_AutoComplete<>(getEMPagina(), UsuarioSB.class);

    }

    public List<ModuloAcaoSistema> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloAcaoSistema> modulos) {
        this.modulos = modulos;
    }

    public String getIdDefinicoesDeSeguranca() {
        return idDefinicoesDeSeguranca;
    }

    @Override
    protected String defineTitulo() {
        return "Controle de acesso a paginas";
    }

    @Override
    protected String defineNomeLink() {
        return "Acesso a paginas";
    }

    @Override
    protected String defineDescricao() {
        return "";
    }

    @Override
    public int getId() {
        return 2;
    }

    public List<GrupoUsuarioSB> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoUsuarioSB> grupos) {
        this.grupos = grupos;
    }

    public GrupoUsuarioSB getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(GrupoUsuarioSB grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }

    public PgUtil getPaginaUtil() {
        return paginaUtil;
    }

    public void setPaginaUtil(PgUtil paginaUtil) {
        this.paginaUtil = paginaUtil;
    }

    public List<ItfAcaoDoSistema> getAcoesListarGrupos() {
        return acoesListarGrupos;
    }

    public void setAcoesListarGrupos(List<ItfAcaoDoSistema> acoesListarGrupos) {
        this.acoesListarGrupos = acoesListarGrupos;
    }

    public AcaoDoSistema getAcaoselecionada() {
        return acaoselecionada;
    }

    public void setAcaoselecionada(AcaoDoSistema acaoselecionada) {
        this.acaoselecionada = acaoselecionada;
    }

    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    public void setXhtmlAcaoAtual(String xhtmlAcaoAtual) {
        this.xhtmlAcaoAtual = xhtmlAcaoAtual;
    }

    public AcaoDoSistema getAcaoListarGrupos() {
        return acaoListarGrupos;
    }

    public AcaoDoSistema getAcaoListarUsuarios() {
        return acaoListarUsuarios;
    }

    public AcaoDoSistema getAcaoSalvarPermissoes() {
        return acaoSalvarPermissoes;
    }

    public boolean isNovogrupo() {
        return novogrupo;
    }

    public void setNovogrupo(boolean novogrupo) {
        this.novogrupo = novogrupo;
    }

    public boolean isBloquearEdicao() {
        return bloquearEdicao;
    }

    public void setBloquearEdicao(boolean bloquearEdicao) {
        this.bloquearEdicao = bloquearEdicao;
    }

    public Map<String, String> getIdsGerenciaveis() {
        return idsGerenciaveis;
    }

    public void setIdsGerenciaveis(Map<String, String> idsGerenciaveis) {
        this.idsGerenciaveis = idsGerenciaveis;
    }

    public AcaoDoSistema getAcaoNovoGrupo() {
        return acaoNovoGrupo;
    }

    public UsuarioSB getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(UsuarioSB usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public AcaoDoSistema getAcaoRemoverUsuarioDoGrupo() {
        return null;// acaoRemoverUsuarioDoGrupo;
    }

    public AcaoDoSistema getAcaoAdicionarUsuarioNoGrupo() {
        return null;// acaoAdicionarUsuarioNoGrupo;
    }

    public AcaoDoSistema getAcaoListarGruposDoUsuario() {
        return acaoListarGruposDoUsuario;
    }

    public BP_AutoComplete<UsuarioSB> getAutocompleteUsuario() {
        return autocompleteUsuario;
    }

}
