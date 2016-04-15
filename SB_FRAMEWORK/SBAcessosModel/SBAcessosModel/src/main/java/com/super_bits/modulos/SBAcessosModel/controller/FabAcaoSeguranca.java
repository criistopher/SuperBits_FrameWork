/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidadeController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoes;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoModulo(nomeDoModulo = "Seguranca", descricao = "Configurações de segurança do sistema")
public enum FabAcaoSeguranca implements ItfFabricaAcoes {

    GRUPOS_MB_GERENCIAR,
    GRUPO_FRM_NOVO,
    GRUPO_FRM_LISTAR,
    GRUPO_FRM_VISUALIZAR,
    GRUPO_CTR_ALTERAR_STATUS,
    GRUPO_FRM_LIST_USUARIOS,
    GRUPO__CTR_SALVAR_MERGE,
    USUARIO_MB_GERENCIAR,
    USUARIO_FRM_NOVO,
    USUARIO_FRM_LISTAR,
    USUARIO_CTR_SALVAR_MERGE,
    USUARIO_FRM_EDITAR,
    USUARIO_FRM_VISUALIZAR,
    USUARIO_FRM_LISTAR_GRUPOS,
    USUARIO_CTR_ALTERAR_STATUS,
    USUARIO_FRM_LISTARGRUPOS,
    ACAO_CTR_INTERNA_DO_SISTEMA;

    @Override
    public AcaoDoSistema getAcaoDoSistema() {
        ItfAcaoDoSistema acao = UtilFabricaDeAcoes.getNovaAcao(this);
        switch (this) {
            case GRUPOS_MB_GERENCIAR:
                AcaoGestaoEntidade grupoGerenciar = new AcaoGestaoEntidade(this, GrupoUsuarioSB.class, "/resources/SBComp/seguranca/grupo.xhtml");
                grupoGerenciar.setNome("Gerenciar grupos");
                return grupoGerenciar;

            case GRUPO_FRM_NOVO:

                acao = UtilFabricaDeAcoes.getNovaAcao(
                        this);
                AcaoFormularioEntidade acaoNovoRegistro = (AcaoFormularioEntidade) acao;

                acaoNovoRegistro.setXhtml("/sistema/seguranca/editarGrupo.xhtml");
                acaoNovoRegistro.setPrecisaPermissao(true);
                acaoNovoRegistro.setDescricao("Permite criar um grupo de usuários para ser utilizado por administradores do VipKompras");
                acaoNovoRegistro.setPrecisaPermissao(false);
                acaoNovoRegistro.setIdDescritivoJira("UI027");

                break;
            case USUARIO_FRM_LISTAR_GRUPOS:

                acao = UtilFabricaDeAcoes.getNovaAcao(
                        this);

                AcaoFormularioEntidade acaoListarGrupo = (AcaoFormularioEntidade) acao;

                acaoListarGrupo.setDescricao("Listar grupos de usuários");
                acaoListarGrupo.setXhtml("/sistema/seguranca/listarGrupos.xhtml");
                acaoListarGrupo.setIconeAcao("fa fa-users");
                acaoListarGrupo.setPrecisaPermissao(true);
                acaoListarGrupo.setIdDescritivoJira("UI026");

                break;
            case GRUPO_FRM_LISTAR:

                acao
                        = UtilFabricaDeAcoes.getNovaAcao(
                                this);
                AcaoFormularioEntidade acaoEditar = (AcaoFormularioEntidade) acao;

                acaoEditar.setIconeAcao("fa fa-edit");
                acaoEditar.setXhtml("/sistema/seguranca/editarGrupo.xhtml");
                acaoEditar.setPrecisaPermissao(true);
                acaoEditar.setIdDescritivoJira("UC021");

                break;
            case GRUPO_FRM_VISUALIZAR:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoFormularioEntidade grpVisualizar = (AcaoFormularioEntidade) acao;

                grpVisualizar.setIconeAcao("fa fa-search-plus");
                grpVisualizar.setXhtml("/sistema/seguranca/editarGrupo.xhtml");
                grpVisualizar.setNomeAcao("Ver Detalhe do Grupo");
                grpVisualizar.setPrecisaPermissao(true);
                grpVisualizar.setIdDescritivoJira("UI028");

                break;
            case GRUPO_CTR_ALTERAR_STATUS:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoDeEntidadeController grpAlterarStatus = (AcaoDeEntidadeController) acao;

                grpAlterarStatus.setNomeAcao("Ativar/Desativar");
                grpAlterarStatus.setIconeAcao("fa fa-retweet");
                grpAlterarStatus.setPrecisaPermissao(true);
                grpAlterarStatus.setIdDescritivoJira("UC021");

                break;

            case GRUPO_FRM_LIST_USUARIOS:

                break;
            case GRUPO__CTR_SALVAR_MERGE:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoDeEntidadeController grpSalvarAlteracoes = (AcaoDeEntidadeController) acao;

                grpSalvarAlteracoes.setIconeAcao("fa fa-save");
                grpSalvarAlteracoes.setNomeAcao("Salvar Alterações");
                grpSalvarAlteracoes.setPrecisaPermissao(true);
                grpSalvarAlteracoes.setIdDescritivoJira("UC021");

                break;
            case USUARIO_MB_GERENCIAR:

                acao = new AcaoGestaoEntidade(USUARIO_MB_GERENCIAR, UsuarioSB.class, "/site/seguranca/usuario.xhtml");
                AcaoGestaoEntidade acaoGestao = (AcaoGestaoEntidade) acao;

                acaoGestao.setNomeAcao("Usuários");
                acaoGestao.setIconeAcao("fa fa-user");
                acaoGestao.setDescricao("Gerenciar Usuários");
                acaoGestao.setPrecisaPermissao(true);
                acaoGestao.setIdDescritivoJira("UI030");

                break;
            case USUARIO_FRM_NOVO:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoFormularioEntidade usuarioNovo = (AcaoFormularioEntidade) acao;

                usuarioNovo.setNomeAcao("Criar Novo Usuário");
                usuarioNovo.setIconeAcao("fa fa-plus");
                usuarioNovo.setPrecisaPermissao(true);
                usuarioNovo.setIdDescritivoJira("UI031");

                break;
            case USUARIO_FRM_LISTAR:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoFormularioEntidade usuarioListar = (AcaoFormularioEntidade) acao;

                usuarioListar.setNomeAcao("Listar Usuários");
                usuarioListar.setIconeAcao("fa fa-users");
                usuarioListar.setPrecisaPermissao(true);
                usuarioListar.setIdDescritivoJira("UI030");

                break;
            case USUARIO_CTR_SALVAR_MERGE:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoDeEntidadeController usuarioSalvar = (AcaoDeEntidadeController) acao;

                usuarioSalvar.setIconeAcao("fa fa-save");
                usuarioSalvar.setNomeAcao("Salvar Alterações");
                usuarioSalvar.setIdDescritivoJira("UC013");

                break;
            case USUARIO_FRM_EDITAR:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoFormularioEntidade usuarioEditar = (AcaoFormularioEntidade) acao;

                usuarioEditar.setIconeAcao("fa fa-edit");
                usuarioEditar.setNomeAcao("Editar Usuário");
                usuarioEditar.setPrecisaPermissao(true);
                usuarioEditar.setIdDescritivoJira("UI032.1");

                break;
            case USUARIO_FRM_VISUALIZAR:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoFormularioEntidade usuarioVisualizar = (AcaoFormularioEntidade) acao;

                usuarioVisualizar.setIconeAcao("fa fa-search-plus");
                usuarioVisualizar.setNomeAcao("Visualizar Usuário");
                usuarioVisualizar.setDescricao("Mostra os detalhes do cadastro de usuario");
                usuarioVisualizar.setPrecisaPermissao(true);
                usuarioVisualizar.setIdDescritivoJira("UI032.0");

                break;
            case USUARIO_CTR_ALTERAR_STATUS:

                acao = UtilFabricaDeAcoes.getNovaAcao(this);
                AcaoDeEntidadeController usuarioAlterarStatus = (AcaoDeEntidadeController) acao;

                usuarioAlterarStatus.setNomeAcao("Ativar/Desativar");
                usuarioAlterarStatus.setIconeAcao("fa fa-retweet ");
                usuarioAlterarStatus.setPrecisaPermissao(true);
                usuarioAlterarStatus.setIdDescritivoJira("UC013");
                usuarioAlterarStatus.setDescricao("Mostra os detalhes do cadastro de usuario");

                break;

            case USUARIO_FRM_LISTARGRUPOS:

                break;
            case ACAO_CTR_INTERNA_DO_SISTEMA:
                acao = new AcaoController(this);

                break;
            default:
                throw new AssertionError(this.name());

        }
        return (AcaoDoSistema) acao;
    }

    @Override
    public List<ItfGrupoUsuario> getGruposVinculadosAoModulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        return getAcaoDoSistema();
    }

    @Override
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        try {
            return (ItfAcaoGerenciarEntidade) getRegistro();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo ItfAcaoControllerEntidade", t);
        }

        return null;

    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        try {
            return (ItfAcaoEntidade) getRegistro();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo ItfAcaoControllerEntidade", t);
        }

        return null;
    }

    @Override
    public ItfAcaoControllerEntidade getAcaoEntidadeController() {

        try {
            return (ItfAcaoControllerEntidade) getRegistro();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo ItfAcaoControllerEntidade", t);
        }

        return null;

    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEntidadeFormulario() {
        try {
            return (ItfAcaoFormularioEntidade) getRegistro();

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo ItfAcaoControllerEntidade", t);
        }

        return null;

    }

    @Override
    public ItfAcaoController getAcaoController() {
        try {
            return (ItfAcaoController) getRegistro();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A ação " + this + " não parece ser do tipo ItfAcaoControllerEntidade", t);
        }

        return null;

    }

    @Override
    public Class getDominio() {
        switch (this) {
            case GRUPOS_MB_GERENCIAR:
            case GRUPO_FRM_NOVO:
            case GRUPO_FRM_LISTAR:
            case GRUPO_FRM_VISUALIZAR:
            case GRUPO_CTR_ALTERAR_STATUS:
            case GRUPO_FRM_LIST_USUARIOS:
            case GRUPO__CTR_SALVAR_MERGE:
                return GrupoUsuarioSB.class;

            case USUARIO_MB_GERENCIAR:
            case USUARIO_FRM_NOVO:
            case USUARIO_FRM_LISTAR:
            case USUARIO_CTR_SALVAR_MERGE:
            case USUARIO_FRM_EDITAR:
            case USUARIO_FRM_VISUALIZAR:
            case USUARIO_FRM_LISTAR_GRUPOS:
            case USUARIO_CTR_ALTERAR_STATUS:
            case USUARIO_FRM_LISTARGRUPOS:
                return UsuarioSB.class;

            case ACAO_CTR_INTERNA_DO_SISTEMA:
                return UsuarioSB.class;

            default:
                throw new AssertionError(this.name());

        }
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoes.getModuloByFabrica(this).getNome();
    }

}
