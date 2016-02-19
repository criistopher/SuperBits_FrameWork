/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoPadrao;
import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoModulo(nomeDoModulo = "Segurança", descricao = "Configurações de segurança do sistema")
public enum FabAcaoSeguranca implements ItfFabricaAcoes {

    GRUPOS_GERENCIAR,
    GRUPO_ADICIONAR,
    GRUPO_LISTAR,
    GRUPO_EDITAR,
    GRUPO_VISUALIZAR,
    GRUPO_ALTERAR_STATUS,
    GRUPO_LISTAR_USUARIOS,
    GRUPO_SALVAR_ALTERACOES,
    USUARIO_GERENCIAR,
    USUARIO_NOVO_USUARIO,
    USUARIO_LISTAR,
    USUARIO_SALVAR_ALTERACOES,
    USUARIO_EDITAR,
    USUARIO_VISUALIZAR,
    USUARIO_ALTERAR_STATUS,
    USUARIO_LISTARGRUPOS;

    @Override
    public List<ItfGrupoUsuario> getAcessoGruposLiberadosPadrao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AcaoDoSistema getAcaoDoSistema() {
        AcaoDoSistema acao = new AcaoDoSistema(this, this.name(), "", "", "Descrição não documentada");
        switch (this) {
            case GRUPOS_GERENCIAR:
                acao.setNomeAcao("Gerenciar Grupos ");
                acao.setDescricao("Gerenciar grupos de usuários");
                acao.setIsAcaoPrincipal(true);
                acao.setXHTMLAcao("/sistema/seguranca/permissao.xhtml");
                acao.setIconeAcao("fa fa-users");
                acao.setAcessoAPagina(true);
                acao.setPrecisaPermissao(true);
                acao.setIdDescritivoJira("UI026");
                break;
            case GRUPO_ADICIONAR:
                acao.setIconeAcao("fa fa-plus");
                acao.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setNomeAcao("Criar novo Grupo");
                acao.setDescricao("Permite criar um grupo de usuários para ser utilizado por administradores do VipKompras");
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.NOVO_ABRIR_FORMULARIO);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setIdDescritivoJira("UI027");
                break;
            case GRUPO_LISTAR:
                acao.setNomeAcao("Listar Grupos ");
                acao.setDescricao("Listar grupos de usuários");
                acao.setIsAcaoPrincipal(false);
                acao.setXHTMLAcao("/sistema/seguranca/listarGrupos.xhtml");
                acao.setIconeAcao("fa fa-users");
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.LISTAR_ABRIR_FORMULARIO);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setIdDescritivoJira("UI026");
                break;
            case GRUPO_EDITAR:
                acao.setIconeAcao("fa fa-edit");
                acao.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setNomeAcao("Editar Grupo");
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.EDITAR_ABRIR_FORMULARIO);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setIdDescritivoJira("UC021");

                break;
            case GRUPO_VISUALIZAR:
                acao.setIconeAcao("fa fa-search-plus");
                acao.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setNomeAcao("Ver Detalhe do Grupo");
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.VISUALIZAR_ABRIR_FORMULARIO);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setIdDescritivoJira("UI028");
                break;
            case GRUPO_ALTERAR_STATUS:
                acao.setNomeAcao("Ativar/Desativar");
                acao.setIconeAcao("fa fa-retweet");
                acao.setPrecisaPermissao(false);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.ATIVAR_DESATIVAR);
                acao.setIdDescritivoJira("UC021");
                break;
            case GRUPO_LISTAR_USUARIOS:

                break;
            case GRUPO_SALVAR_ALTERACOES:
                acao.setIconeAcao("fa fa-save");
                acao.setNomeAcao("Salvar Alterações");
                acao.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.SALVAR_MODO_MERGE);
                acao.setIdDescritivoJira("UC021");

                break;
            case USUARIO_GERENCIAR:
                acao.setNomeAcao("Usuários");
                acao.setIconeAcao("fa fa-user");
                acao.setIsAcaoPrincipal(true);
                acao.setDescricao("Gerenciar Usuários");
                acao.setXHTMLAcao("/sistema/seguranca/cadastroUsuario.xhtml");
                acao.setAcessoAPagina(true);
                acao.setPrecisaPermissao(true);
                acao.setIdDescritivoJira("UI030");
                break;
            case USUARIO_NOVO_USUARIO:
                acao.setNomeAcao("Criar Novo Usuário");
                acao.setXHTMLAcao("/sistema/seguranca/usuario/editarUsuario.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setIconeAcao("fa fa-plus");
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.NOVO_ABRIR_FORMULARIO);
                acao.setIdDescritivoJira("UI031");
                acao.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                break;
            case USUARIO_LISTAR:
                acao.setNomeAcao("Listar Usuários");
                acao.setXHTMLAcao("/sistema/seguranca/usuario/listarUsuario.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setIconeAcao("fa fa-users");
                acao.setPrecisaPermissao(false);
                acao.setIdDescritivoJira("UI030");

                break;
            case USUARIO_SALVAR_ALTERACOES:
                acao.setIconeAcao("fa fa-save");
                acao.setNomeAcao("Salvar Alterações");
                acao.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.SALVAR_MODO_MERGE);
                acao.setIdDescritivoJira("UC013");

                break;
            case USUARIO_EDITAR:
                acao.setIconeAcao("fa fa-edit");
                acao.setXHTMLAcao("/sistema/seguranca/usuario/editarUsuario.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setNomeAcao("Editar Usuário");
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.EDITAR_ABRIR_FORMULARIO);
                acao.setIdDescritivoJira("UI032.1");
                break;
            case USUARIO_VISUALIZAR:
                acao.setXHTMLAcao("/sistema/seguranca/usuario/editarUsuario.xhtml");
                acao.setIconeAcao("fa fa-search-plus");
                acao.setIsAcaoPrincipal(false);
                acao.setNomeAcao("Visualizar Usuário");
                acao.setDescricao("Mostra os detalhes do cadastro de usuario");
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.VISUALIZAR_ABRIR_FORMULARIO);
                acao.setIdDescritivoJira("UI032.0");
                break;
            case USUARIO_ALTERAR_STATUS:
                acao.setNomeAcao("Ativar/Desativar");
                acao.setIconeAcao("fa fa-retweet ");
                acao.setPrecisaPermissao(false);

                acao.configurarAcaoPadrao(FabTipoAcaoPadrao.ATIVAR_DESATIVAR);
                acao.setIdDescritivoJira("UC013");
                break;
            case USUARIO_LISTARGRUPOS:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return acao;
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        return getAcaoDoSistema();
    }
}
