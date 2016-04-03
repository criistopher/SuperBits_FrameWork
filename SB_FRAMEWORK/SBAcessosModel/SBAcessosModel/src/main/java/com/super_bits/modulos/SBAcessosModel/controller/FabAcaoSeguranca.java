/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.controller;

<<<<<<< HEAD
import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
=======
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidadeController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoFormulario;
<<<<<<< HEAD
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeNovoRegistro;
=======
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioDeEntidadeNovoRegistro;
>>>>>>> b32f69250be78a0874d4b266ac51f6912c9c671d
>>>>>>> d3db8acdbb7bc10c2ca647e7ffa225e78bea0951
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfGrupoUsuario;
import com.super_bits.modulosSB.SBCore.fabrica.InfoModulo;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
@InfoModulo(nomeDoModulo = "Seguranca", descricao = "Configurações de segurança do sistema")
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

        switch (this) {
            case GRUPOS_GERENCIAR:
                AcaoDoSistema acaoGerenciarGrupos = new AcaoDeEntidadeController(GrupoUsuarioSB.class);

                acaoGerenciarGrupos.setNomeAcao("Gerenciar Grupos ");
                acaoGerenciarGrupos.setDescricao("Gerenciar grupos de usuários");
                acaoGerenciarGrupos.setIconeAcao("fa fa-users");
                acaoGerenciarGrupos.setPrecisaPermissao(true);
                acaoGerenciarGrupos.setIdDescritivoJira("UI026");

                break;
            case GRUPO_ADICIONAR:
<<<<<<< HEAD
                acao.setIconeAcao("fa fa-plus");
                acao.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acao.setPrecisaPermissao(true);
                acao.setNomeAcao("Criar novo Grupo");
                acao.setDescricao("Permite criar um grupo de usuários para ser utilizado por administradores do VipKompras");
                acao.setPrecisaPermissao(false);
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setIdDescritivoJira("UI027");
                break;
            case GRUPO_LISTAR:
                acao.setNomeAcao("Listar Grupos ");
                acao.setDescricao("Listar grupos de usuários");
                acao.setIsAcaoPrincipal(false);
                acao.setXHTMLAcao("/sistema/seguranca/listarGrupos.xhtml");
                acao.setIconeAcao("fa fa-users");
                acao.setPrecisaPermissao(true);
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setIdDescritivoJira("UI026");
                break;
            case GRUPO_EDITAR:
                acao.setIconeAcao("fa fa-edit");
                acao.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setNomeAcao("Editar Grupo");
                acao.setPrecisaPermissao(true);
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setIdDescritivoJira("UC021");

                break;
            case GRUPO_VISUALIZAR:
                acao.setIconeAcao("fa fa-search-plus");
                acao.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setNomeAcao("Ver Detalhe do Grupo");
                acao.setPrecisaPermissao(true);
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setIdDescritivoJira("UI028");
                break;
            case GRUPO_ALTERAR_STATUS:
                acao.setNomeAcao("Ativar/Desativar");
                acao.setIconeAcao("fa fa-retweet");
                acao.setPrecisaPermissao(true);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
<<<<<<< HEAD
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR);
=======
<<<<<<< HEAD
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR);
=======
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR);
>>>>>>> 4e493a3b613f438b5eb83bcc8979df701d2a86c7
>>>>>>> ddf4fd60c66dadade3668eb59b436b224306d51a
                acao.setIdDescritivoJira("UC021");
=======

                AcaoFormularioEntidadeNovoRegistro acaoAdicionarGrupo
                        = new AcaoFormularioEntidadeNovoRegistro(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDeEntidade(), "/sistema/seguranca/editarGrupo.xhtml");

                acaoAdicionarGrupo.setIconeAcao("fa fa-plus");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.setNomeAcao("Criar novo Grupo");
                acaoAdicionarGrupo.setDescricao("Permite criar um grupo de usuários para ser utilizado por administradores do VipKompras");
                acaoAdicionarGrupo.setPrecisaPermissao(false);
                acaoAdicionarGrupo.setIdDescritivoJira("UI027");
                break;
            case GRUPO_LISTAR:
                acaoAdicionarGrupo.setNomeAcao("Listar Grupos ");
                acaoAdicionarGrupo.setDescricao("Listar grupos de usuários");
                acaoAdicionarGrupo.setIsAcaoPrincipal(false);
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/listarGrupos.xhtml");
                acaoAdicionarGrupo.setIconeAcao("fa fa-users");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR);
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acaoAdicionarGrupo.setIdDescritivoJira("UI026");
                break;
            case GRUPO_EDITAR:
                acaoAdicionarGrupo.setIconeAcao("fa fa-edit");
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acaoAdicionarGrupo.setIsAcaoPrincipal(false);
                acaoAdicionarGrupo.setNomeAcao("Editar Grupo");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR);
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acaoAdicionarGrupo.setIdDescritivoJira("UC021");

                break;
            case GRUPO_VISUALIZAR:
                acaoAdicionarGrupo.setIconeAcao("fa fa-search-plus");
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acaoAdicionarGrupo.setIsAcaoPrincipal(false);
                acaoAdicionarGrupo.setNomeAcao("Ver Detalhe do Grupo");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR);
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acaoAdicionarGrupo.setIdDescritivoJira("UI028");
                break;
            case GRUPO_ALTERAR_STATUS:
                acaoAdicionarGrupo.setNomeAcao("Ativar/Desativar");
                acaoAdicionarGrupo.setIconeAcao("fa fa-retweet");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR);
                acaoAdicionarGrupo.setIdDescritivoJira("UC021");
>>>>>>> b32f69250be78a0874d4b266ac51f6912c9c671d
                break;
            case GRUPO_LISTAR_USUARIOS:

                break;
            case GRUPO_SALVAR_ALTERACOES:
<<<<<<< HEAD
                acao.setIconeAcao("fa fa-save");
                acao.setNomeAcao("Salvar Alterações");
                acao.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acao.setPrecisaPermissao(true);
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.SALVAR_MODO_MERGE);
                acao.setIdDescritivoJira("UC021");
=======
                acaoAdicionarGrupo.setIconeAcao("fa fa-save");
                acaoAdicionarGrupo.setNomeAcao("Salvar Alterações");
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/editarGrupo.xhtml");
                acaoAdicionarGrupo.setIsAcaoPrincipal(false);
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.GRUPOS_GERENCIAR.getAcaoDoSistema());
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.SALVAR_MODO_MERGE);
                acaoAdicionarGrupo.setIdDescritivoJira("UC021");
>>>>>>> b32f69250be78a0874d4b266ac51f6912c9c671d

                break;
            case USUARIO_GERENCIAR:
                acaoAdicionarGrupo.setNomeAcao("Usuários");
                acaoAdicionarGrupo.setIconeAcao("fa fa-user");
                acaoAdicionarGrupo.setIsAcaoPrincipal(true);
                acaoAdicionarGrupo.setDescricao("Gerenciar Usuários");
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/cadastroUsuario.xhtml");
                acaoAdicionarGrupo.setAcessoAPagina(true);
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.setIdDescritivoJira("UI030");
                break;
            case USUARIO_NOVO_USUARIO:
<<<<<<< HEAD
                acao.setNomeAcao("Criar Novo Usuário");
                acao.setXHTMLAcao("/sistema/seguranca/usuario/editarUsuario.xhtml");
                acao.setIsAcaoPrincipal(false);
                acao.setIconeAcao("fa fa-plus");
                acao.setPrecisaPermissao(true);
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO);
                acao.setIdDescritivoJira("UI031");
                acao.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
=======
                acaoAdicionarGrupo.setNomeAcao("Criar Novo Usuário");
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/usuario/editarUsuario.xhtml");
                acaoAdicionarGrupo.setIsAcaoPrincipal(false);
                acaoAdicionarGrupo.setIconeAcao("fa fa-plus");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO);
                acaoAdicionarGrupo.setIdDescritivoJira("UI031");
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
>>>>>>> b32f69250be78a0874d4b266ac51f6912c9c671d
                break;
            case USUARIO_LISTAR:
                acaoAdicionarGrupo.setNomeAcao("Listar Usuários");
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/usuario/listarUsuario.xhtml");
                acaoAdicionarGrupo.setIsAcaoPrincipal(false);
                acaoAdicionarGrupo.setIconeAcao("fa fa-users");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.setIdDescritivoJira("UI030");
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                break;
            case USUARIO_SALVAR_ALTERACOES:
<<<<<<< HEAD
                acao.setIconeAcao("fa fa-save");
                acao.setNomeAcao("Salvar Alterações");
                acao.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.SALVAR_MODO_MERGE);
                acao.setIdDescritivoJira("UC013");
                acao.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
=======
                acaoAdicionarGrupo.setIconeAcao("fa fa-save");
                acaoAdicionarGrupo.setNomeAcao("Salvar Alterações");
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.SALVAR_MODO_MERGE);
                acaoAdicionarGrupo.setIdDescritivoJira("UC013");
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
>>>>>>> b32f69250be78a0874d4b266ac51f6912c9c671d
                break;
            case USUARIO_EDITAR:
                acaoAdicionarGrupo.setIconeAcao("fa fa-edit");
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/usuario/editarUsuario.xhtml");
                acaoAdicionarGrupo.setIsAcaoPrincipal(false);
                acaoAdicionarGrupo.setNomeAcao("Editar Usuário");
                acaoAdicionarGrupo.setPrecisaPermissao(true);

                acaoAdicionarGrupo.setIdDescritivoJira("UI032.1");
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                break;
            case USUARIO_VISUALIZAR:
<<<<<<< HEAD
                acao.setXHTMLAcao("/sistema/seguranca/usuario/editarUsuario.xhtml");
                acao.setIconeAcao("fa fa-search-plus");
                acao.setIsAcaoPrincipal(false);
                acao.setNomeAcao("Visualizar Usuário");
                acao.setDescricao("Mostra os detalhes do cadastro de usuario");
                acao.setPrecisaPermissao(true);
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR);
                acao.setIdDescritivoJira("UI032.0");
                acao.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                break;
            case USUARIO_ALTERAR_STATUS:
                acao.setNomeAcao("Ativar/Desativar");
                acao.setIconeAcao("fa fa-retweet ");
                acao.setPrecisaPermissao(true);
                acao.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                acao.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR);
                acao.setIdDescritivoJira("UC013");
=======
                acaoAdicionarGrupo.setXHTMLAcao("/sistema/seguranca/usuario/editarUsuario.xhtml");
                acaoAdicionarGrupo.setIconeAcao("fa fa-search-plus");
                acaoAdicionarGrupo.setIsAcaoPrincipal(false);
                acaoAdicionarGrupo.setNomeAcao("Visualizar Usuário");
                acaoAdicionarGrupo.setDescricao("Mostra os detalhes do cadastro de usuario");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR);
                acaoAdicionarGrupo.setIdDescritivoJira("UI032.0");
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                break;
            case USUARIO_ALTERAR_STATUS:
                acaoAdicionarGrupo.setNomeAcao("Ativar/Desativar");
                acaoAdicionarGrupo.setIconeAcao("fa fa-retweet ");
                acaoAdicionarGrupo.setPrecisaPermissao(true);
                acaoAdicionarGrupo.setAcaoPrincipal(FabAcaoSeguranca.USUARIO_GERENCIAR.getAcaoDoSistema());
                acaoAdicionarGrupo.configurarAcaoPadrao(FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR);
                acaoAdicionarGrupo.setIdDescritivoJira("UC013");
>>>>>>> b32f69250be78a0874d4b266ac51f6912c9c671d
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

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoFormulario getAcaoEntidadeFormulario(ItfAcaoDoSistema acaoPrincipal, Class classeRelacionada, String pXhtml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoController getAcaoEntidadeController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
