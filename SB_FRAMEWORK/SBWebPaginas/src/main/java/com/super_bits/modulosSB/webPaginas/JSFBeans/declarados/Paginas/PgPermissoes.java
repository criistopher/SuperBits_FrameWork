/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.controller.InfoAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.controller.ModuloSeguranca;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces.BP_AutoComplete;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_paginaCadastroEntidades;
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
@InfoPagina(nomeCurto = "GPM", tags = {"Permissoes"})
@Named
@ViewScoped
@InfoAcaoSeguranca(acao = FabAcaoSeguranca.GRUPO_MB_GERENCIAR)
public class PgPermissoes extends MB_paginaCadastroEntidades<GrupoUsuarioSB> {

    @Inject
    private PgUtil paginaUtil;
    @InfoMB_Bean(descricao = "Lista todos os modulos")
    private List<ModuloAcaoSistema> modulos;
    @InfoMB_Bean(descricao = "Lista todos os grupos")
    private List<GrupoUsuarioSB> grupos;

    @InfoMB_IdWidget(descricao = "Area onde as definições de segurança serão exibidas")
    private final String idDefinicoesDeSeguranca = "definiçãoDeSeguranca";

    private List<ItfAcaoDoSistema> acoesListarGrupos;

    private boolean novogrupo;
    private boolean bloquearEdicao;
    private UsuarioSB usuarioSelecionado;

    private BP_AutoComplete<UsuarioSB> autocompleteUsuario;

    public PgPermissoes() {
        super(new AcaoDoSistema[]{
            FabAcaoSeguranca.GRUPO_FRM_EDITAR.getAcaoDoSistema(),
            FabAcaoSeguranca.GRUPO_FRM_VISUALIZAR.getAcaoDoSistema(),
            FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema()
        }, FabAcaoSeguranca.GRUPO_FRM_NOVO.getAcaoDoSistema().getComoFormularioEntidade(),
                FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoDoSistema().getComoFormularioEntidade(),
                FabAcaoSeguranca.GRUPO_CTR_SALVAR_MERGE.getAcaoDoSistema().getComoControllerEntidade(),
                false
        );

    }

    private void configurarSelecaoDeAcoes() {
        for (ModuloAcaoSistema modulo : modulos) {
            modulo.getSelecaoAcoes().clear();
            for (AcaoDoSistema acao : ModuloSeguranca.listarAcoesDoGrupo(getEntidadeSelecionada(), modulo)) {
                modulo.getSelecaoAcoes().add(acao);
            }
        }
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

    @Override
    public void executarAcao(GrupoUsuarioSB pEntidadeSelecionada) {
        super.executarAcao(pEntidadeSelecionada);

        if (acaoSelecionada.getId() == FabAcaoSeguranca.GRUPO_CTR_SALVAR_MERGE.getAcaoDoSistema().getId()) {

            ItfResposta resp = ModuloSeguranca.grupoDeUsuariosSalvarAlteracoes(pEntidadeSelecionada, modulos, getEMPagina());
            if (resp.isSucesso()) {
                xhtmlAcaoAtual = getXhtmlAcaoAtual();
                if (novogrupo) {
                    atualizarDados();

                } else {
                    atualizarDados();
                }
                paginaUtil.atualizaTelaPorID("formulario");
            }

        }

        if (acaoSelecionada.equals(getAcaoAlterarStatus())) {
            ModuloSeguranca.grupoAlterarStatus(pEntidadeSelecionada);
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
     * @param pGrupoSelecionado
     */
    @InfoMB_Acao(descricao = "Atualiza a visualização de ação e Seta o grupo selecionado")
    public void executarAcaoListaGrupo(GrupoUsuarioSB pGrupoSelecionado) {
        this.executarAcao(pGrupoSelecionado);
    }

    @PostConstruct
    public void inicio() {
        System.out.println("PostConstruct Permissoes");
        atualizarDados();
        acoesListarGrupos = new ArrayList<>();
        acoesListarGrupos.add(FabAcaoSeguranca.GRUPO_FRM_EDITAR.getAcaoDoSistema());
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
        return getEntidadeSelecionada();
    }

    public void setGrupoSelecionado(GrupoUsuarioSB grupoSelecionado) {
        setEntidadeSelecionada(grupoSelecionado);
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

    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    public void setXhtmlAcaoAtual(String xhtmlAcaoAtual) {
        this.xhtmlAcaoAtual = xhtmlAcaoAtual;
    }

    public ItfAcaoFormularioEntidade getAcaoListarGrupos() {
        return getAcaoListarRegistros();
    }

    public AcaoDoSistema getAcaoListarUsuarios() {
        return FabAcaoSeguranca.GRUPO_FRM_LISTAR_USUARIOS.getAcaoDoSistema();
    }

    public ItfAcaoDoSistema getAcaoSalvarPermissoes() {
        return getAcaoSalvarAlteracoes();
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

    public ItfAcaoFormulario getAcaoNovoGrupo() {
        return getAcaoNovoRegistro();
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
        return null;
    }

    public BP_AutoComplete<UsuarioSB> getAutocompleteUsuario() {
        return autocompleteUsuario;
    }

    @Override
    public void listarDados() {
        atualizarDados();
    }

    public ItfAcaoDoSistema getAcaoselecionada() {
        return acaoSelecionada;
    }

    public void setAcaoselecionada(ItfAcaoDoSistema pAcao) {
        acaoSelecionada = pAcao;
    }

}
