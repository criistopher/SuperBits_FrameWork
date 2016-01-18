/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.modulos.SBAcessosModel.model.AcessoSB;
import com.super_bits.modulos.SBAcessosModel.model.AcessoSBWebPaginas;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.CrudDataSet;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.Controller.Interfaces.ItfAcesso;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces.BP_PickList;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaConversation;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.InfoPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Bean;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 *
 *
 * @author Salvio
 */
@InfoPagina(nomeCurto = "AC", recurso = "/resources/SBComp/SBSystemPages/acessos.xhtml", tags = {"acessos"}, acessoLivre = false)
@ViewScoped
@Named
public class PgAcessos extends MB_PaginaConversation implements ItfPgAcessos {

    @InfoMB_Bean(descricao = "Bean SB de usuário para inclusão em um grupo de usuários", exemplo = "{h:inputText value='#{pgAcessos.usuarioSBInclusao.nome}'/}")
    private UsuarioSB usuarioSBInclusao;

    @InfoMB_Bean(descricao = "Bean SB de usuário para exclusão de um grupo de usuários", exemplo = "{h:inputText value='#{pgAcessos.usuarioSBExclusao.nome}'/}")
    private UsuarioSB usuarioSBExclusao;

    @InfoMB_Bean(descricao = "Bean SB de grupo para inclusão em um grupo de usuários", exemplo = "{h:inputText value='#{pgAcessos.grupoUsuarioSBInclusao.nome}'/}")
    private GrupoUsuarioSB grupoUsuarioSBInclusao;

    @InfoMB_Bean(descricao = "Bean SB de grupo para exclusão de um grupo de usuários", exemplo = "{h:inputText value='#{pgAcessos.grupoUsuarioSBExclusao.nome}'/}")
    private GrupoUsuarioSB grupoUsuarioSBExclusao;

    @InfoMB_Bean(descricao = "Bean SB de para gerenciamento de acessos", exemplo = "{h:inputText value='#{pgAcessos.acessoSB.nomeAcesso}'/}")
    private AcessoSB acessoSelecionado;

    private CrudDataSet<UsuarioSB> usuariosCrud;
    private CrudDataSet<GrupoUsuarioSB> grupoUsuariosCrud;
    private BP_PickList<ItfUsuario> pickListUsuarioEmGrupo;
    private CrudDataSet<AcessoSBWebPaginas> crudPaginasAcesso;
    private BP_PickList<UsuarioSB> pickListUsuarioPagina;
    private BP_PickList<GrupoUsuarioSB> pickListGrupoUsuarioPagina;

    @Inject
    private ControleDeSessaoWeb controleSessao;
    @Inject
    private PgUtil paginaUtil;

    public void atualizarGruposEUsuarios() {
        usuariosCrud.refesh();
        grupoUsuariosCrud.refesh();
    }

    public PgAcessos() {
        super();
    }

    private void atualizaBeans() {
        usuariosCrud = new CrudDataSet<>(UsuarioSB.class, getEMPagina());
        grupoUsuariosCrud = new CrudDataSet<>(GrupoUsuarioSB.class, getEMPagina());

        crudPaginasAcesso = new CrudDataSet<>(AcessoSBWebPaginas.class, getEMPagina());

        pickListUsuarioPagina = new BP_PickList<>((List) crudPaginasAcesso.getRegistro().getUsuarios(), UsuarioSB.class);
        pickListGrupoUsuarioPagina = new BP_PickList<>((List) crudPaginasAcesso.getRegistro().getGrupoUsuarios(), GrupoUsuarioSB.class);
        System.out.println("USUARIOS::::::::::::::::::::" + grupoUsuariosCrud.getRegistro().getUsuarios());
        //inclusaoGrupos = new DualListModel<>();
        pickListUsuarioEmGrupo = new BP_PickList<>((List) grupoUsuariosCrud.getRegistro().getUsuarios(), UsuarioSB.class);

    }

    public void atualizarDados() {
        atualizaBeans();

    }

    @PostConstruct
    public void init() {
        try {
            atualizaBeans();
        } catch (Throwable e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro executando init pagina de acessos ", e);
        }
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public UsuarioSB getUsuarioSBInclusao() {
        return usuarioSBInclusao;
    }

    public void setUsuarioSBInclusao(UsuarioSB usuarioSBInclusao) {
        this.usuarioSBInclusao = usuarioSBInclusao;
    }

    @Override
    public UsuarioSB getUsuarioSBExclusao() {
        return usuarioSBExclusao;
    }

    public void setUsuarioSBExclusao(UsuarioSB usuarioSBExclusao) {
        this.usuarioSBExclusao = usuarioSBExclusao;
    }

    @Override
    public GrupoUsuarioSB getGrupoUsuarioSBInclusao() {
        return grupoUsuarioSBInclusao;
    }

    public void setGrupoUsuarioSBInclusao(GrupoUsuarioSB grupoUsuarioSBInclusao) {
        this.grupoUsuarioSBInclusao = grupoUsuarioSBInclusao;
    }

    @Override
    public GrupoUsuarioSB getGrupoUsuarioSBExclusao() {
        return grupoUsuarioSBExclusao;
    }

    public void setGrupoUsuarioSBExclusao(GrupoUsuarioSB grupoUsuarioSBExclusao) {
        this.grupoUsuarioSBExclusao = grupoUsuarioSBExclusao;
    }

    @Override
    public ItfAcesso getAcessoSelecionado() {
        return acessoSelecionado;
    }

    public void setAcessoSelecionado(ItfAcesso acessoSelecionado) {
        this.acessoSelecionado = (AcessoSB) acessoSelecionado;
    }

    @Override
    public void abrePagina() {
        try {
            super.abrePagina();
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: abrePagina \n\n", exception);
        }
    }

    @Override
    public String defineTitulo() {
        try {
            return "Gerenciamento e controle de acessos para usuários";
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: defineTitulo \n\n", exception);
        }
        return null;
    }

    @Override
    public String defineNomeLink() {
        try {
            return "Acessos";
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: defineNomeLink \n\n", exception);
        }
        return null;
    }

    @Override
    public String defineDescricao() {
        try {
            return "Gerencie o acesso que os usuários têm no sistema";
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: defineDescricao \n\n", exception);
        }
        return null;
    }

    @Override
    public List<AcessoSB> getAcessos() {
        try {
            return (List<AcessoSB>) UtilSBPersistencia.getListaTodos(AcessoSB.class, getEMPagina());
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: getAcessos \n\n", exception);
        }
        return null;
    }

    @Override
    public void adicionarUsuarioPermitido() {
        try {
            getAcessoSelecionado().getUsuariosPermitidos().add(usuarioSBInclusao);
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: adicionarUsuarioPermitido \n\n", exception);
        }
    }

    @Override
    public void adicionarUsuarioNegado() {
        try {
            getAcessoSelecionado().getUsuariosNegados().add(usuarioSBInclusao);
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: adicionarUsuarioNegado \n\n", exception);
        }
    }

    @Override
    public void removerUsuarioPermitido() {
        try {
            getAcessoSelecionado().getUsuariosPermitidos().remove(usuarioSBExclusao);
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: removerUsuarioPermitido \n\n", exception);
        }
    }

    @Override
    public void removerUsuarioNegado() {
        try {
            getAcessoSelecionado().getUsuariosNegados().remove(usuarioSBExclusao);
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: removerUsuarioNegado \n\n", exception);
        }
    }

    public void adicionarGrupoPermitido() {
        try {
            getAcessoSelecionado().getGruposPermitidos().add(grupoUsuarioSBInclusao);
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: adicionarGrupoPermitido \n\n", exception);
        }
    }

    public void adicionarGrupoNegado() {
        try {
            getAcessoSelecionado().getGruposNegados().add(grupoUsuarioSBInclusao);
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: adicionarGrupoNegado \n\n", exception);
        }
    }

    @Override
    public void removerGrupoPermitido() {
        try {
            getAcessoSelecionado().getGruposPermitidos().remove(grupoUsuarioSBExclusao);
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: removerGrupoPermitido \n\n", exception);
        }
    }

    @Override
    public void removerGrupoNegado() {
        try {
            getAcessoSelecionado().getGruposNegados().remove(grupoUsuarioSBExclusao);
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: removerGrupoNegado \n\n", exception);
        }
    }

    public void salvarAlteracoes() {
        try {
            if (UtilSBPersistencia.mergeRegistro(getAcessoSelecionado()) != null) {
                FabMensagens.enviarMensagemUsuario("Alterações de acessos salvas", FabMensagens.AVISO);
            } else {
                FabMensagens.enviarMensagemUsuario("Não foi possível salvar as alterações de acesso", FabMensagens.ERRO);
            }
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: PgAcessos.java \nMétodo/Atributo/Local: salvarAlteracoes \n\n", exception);
        }
    }

    public void mudaRegistroPaginas() {
        pickListUsuarioPagina.setLista(crudPaginasAcesso.getRegistro().getUsuarios());
        pickListGrupoUsuarioPagina.setLista(crudPaginasAcesso.getRegistro().getGrupoUsuarios());
    }

    public CrudDataSet<UsuarioSB> getUsuariosCrud() {
        return usuariosCrud;
    }

    public CrudDataSet<GrupoUsuarioSB> getGrupoUsuariosCrud() {
        return grupoUsuariosCrud;
    }

    @Override
    public Conversation getConversa() {
        return getConversation();
    }

    public void setAcessoSelecionado(AcessoSB acessoSelecionado) {
        this.acessoSelecionado = acessoSelecionado;
    }

    public BP_PickList<ItfUsuario> getPickListUsuarioEmGrupo() {
        return pickListUsuarioEmGrupo;
    }

    public void setPickListUsuarioEmGrupo(BP_PickList<ItfUsuario> PickListUsuarioEmGrupo) {
        this.pickListUsuarioEmGrupo = PickListUsuarioEmGrupo;
    }

    public void mudaListaTeste() {
        pickListUsuarioEmGrupo.setLista(grupoUsuariosCrud.getRegistro().getUsuarios());
    }

    public CrudDataSet<AcessoSBWebPaginas> getCrudPaginasAcesso() {

        return crudPaginasAcesso;
    }

    public BP_PickList<UsuarioSB> getPickListUsuarioPagina() {
        return pickListUsuarioPagina;
    }

    public BP_PickList<GrupoUsuarioSB> getPickListGrupoUsuarioPagina() {
        return pickListGrupoUsuarioPagina;
    }

    public void setPickListGrupoUsuarioPagina(BP_PickList<GrupoUsuarioSB> pickListGrupoUsuarioPagina) {
        this.pickListGrupoUsuarioPagina = pickListGrupoUsuarioPagina;
    }

    public void atualizarPermicao() {
        try {
            controleSessao.recarregarPermissoes();

            FabMensagens.enviarMensagemUsuario("Acessos recarregados com sucesso", FabMensagens.AVISO);
        } catch (Throwable e) {
            FabMensagens.enviarMensagemUsuario("Erro recarregando Acessos", FabMensagens.AVISO);
        }
    }

}
