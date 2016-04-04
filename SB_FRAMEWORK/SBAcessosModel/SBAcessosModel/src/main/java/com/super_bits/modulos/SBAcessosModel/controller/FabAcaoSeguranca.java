/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoes;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeEditar;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeListar;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeNovoRegistro;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeVisualizar;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
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
        ItfAcaoDoSistema acao = null;
        switch (this) {
            case GRUPOS_GERENCIAR:

                break;
            case GRUPO_ADICIONAR:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO, GRUPOS_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeNovoRegistro acaoNovoRegistro = (AcaoFormularioEntidadeNovoRegistro) acao;
               
                       
                acaoNovoRegistro.setXhtml("/sistema/seguranca/editarGrupo.xhtml");
                acaoNovoRegistro.setPrecisaPermissao(true);
                acaoNovoRegistro.setDescricao("Permite criar um grupo de usuários para ser utilizado por administradores do VipKompras");
                acaoNovoRegistro.setPrecisaPermissao(false);
                acaoNovoRegistro.setIdDescritivoJira("UI027");

                break;
            case GRUPO_LISTAR:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR, GRUPOS_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeListar acaoListarGrupo = (AcaoFormularioEntidadeListar) acao;

                acaoListarGrupo.setDescricao("Listar grupos de usuários");
                acaoListarGrupo.setXhtml("/sistema/seguranca/listarGrupos.xhtml");
                acaoListarGrupo.setIconeAcao("fa fa-users");
                acaoListarGrupo.setPrecisaPermissao(true);
                acaoListarGrupo.setIdDescritivoJira("UI026");

                break;
            case GRUPO_EDITAR:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR, GRUPOS_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeEditar acaoEditar = (AcaoFormularioEntidadeEditar) acao;

                acaoEditar.setIconeAcao("fa fa-edit");
                acaoEditar.setXhtml("/sistema/seguranca/editarGrupo.xhtml");
                acaoEditar.setPrecisaPermissao(true);
                acaoEditar.setIdDescritivoJira("UC021");

                break;
            case GRUPO_VISUALIZAR:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR, GRUPOS_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeVisualizar grpVisualizar = (AcaoFormularioEntidadeVisualizar) acao;

                grpVisualizar.setIconeAcao("fa fa-search-plus");
                grpVisualizar.setXhtml("/sistema/seguranca/editarGrupo.xhtml");
                grpVisualizar.setNomeAcao("Ver Detalhe do Grupo");
                grpVisualizar.setPrecisaPermissao(true);
                grpVisualizar.setIdDescritivoJira("UI028");

                break;
            case GRUPO_ALTERAR_STATUS:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR, GRUPOS_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeEditar grpAlterarStatus = (AcaoFormularioEntidadeEditar) acao;

                grpAlterarStatus.setNomeAcao("Ativar/Desativar");
                grpAlterarStatus.setIconeAcao("fa fa-retweet");
                grpAlterarStatus.setPrecisaPermissao(true);
                grpAlterarStatus.setIdDescritivoJira("UC021");

                break;

            case GRUPO_LISTAR_USUARIOS:

                break;
            case GRUPO_SALVAR_ALTERACOES:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.SALVAR_MODO_MERGE, GRUPOS_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeEditar grpSalvarAlteracoes = (AcaoFormularioEntidadeEditar) acao;

                grpSalvarAlteracoes.setIconeAcao("fa fa-save");
                grpSalvarAlteracoes.setNomeAcao("Salvar Alterações");
                grpSalvarAlteracoes.setPrecisaPermissao(true);
                grpSalvarAlteracoes.setIdDescritivoJira("UC021");

                break;
            case USUARIO_GERENCIAR:
                
                
               acao =new AcaoGestaoEntidade(USUARIO_GERENCIAR, UsuarioSB.class, "/site/seguranca/usuario.xhtml");

                AcaoGestaoEntidade acaoGestao=(AcaoGestaoEntidade)acao;         
                acaoGestao.setNomeAcao("Usuários");
                acaoGestao.setIconeAcao("fa fa-user");
                acaoGestao.setDescricao("Gerenciar Usuários");
                acaoGestao.setPrecisaPermissao(true);
                acaoGestao.setIdDescritivoJira("UI030");

                break;
            case USUARIO_NOVO_USUARIO:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.SALVAR_NOVO, USUARIO_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeNovoRegistro usuarioNovo = (AcaoFormularioEntidadeNovoRegistro) acao;

                usuarioNovo.setNomeAcao("Criar Novo Usuário");
                usuarioNovo.setIconeAcao("fa fa-plus");
                usuarioNovo.setPrecisaPermissao(true);
                usuarioNovo.setIdDescritivoJira("UI031");

                break;
            case USUARIO_LISTAR:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR, USUARIO_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeListar usuarioListar = (AcaoFormularioEntidadeListar) acao;

                usuarioListar.setNomeAcao("Listar Usuários");
                usuarioListar.setIconeAcao("fa fa-users");
                usuarioListar.setPrecisaPermissao(true);
                usuarioListar.setIdDescritivoJira("UI030");

                break;
            case USUARIO_SALVAR_ALTERACOES:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.SALVAR_MODO_MERGE, USUARIO_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeEditar usuarioSalvar = (AcaoFormularioEntidadeEditar) acao;

                usuarioSalvar.setIconeAcao("fa fa-save");
                usuarioSalvar.setNomeAcao("Salvar Alterações");
                usuarioSalvar.setIdDescritivoJira("UC013");

                break;
            case USUARIO_EDITAR:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR, USUARIO_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeEditar usuarioEditar = (AcaoFormularioEntidadeEditar) acao;

                usuarioEditar.setIconeAcao("fa fa-edit");
                usuarioEditar.setNomeAcao("Editar Usuário");
                usuarioEditar.setPrecisaPermissao(true);
                usuarioEditar.setIdDescritivoJira("UI032.1");

                break;
            case USUARIO_VISUALIZAR:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR, USUARIO_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeVisualizar usuarioVisualizar = (AcaoFormularioEntidadeVisualizar) acao;

                usuarioVisualizar.setIconeAcao("fa fa-search-plus");
                usuarioVisualizar.setNomeAcao("Visualizar Usuário");
                usuarioVisualizar.setDescricao("Mostra os detalhes do cadastro de usuario");
                usuarioVisualizar.setPrecisaPermissao(true);
                usuarioVisualizar.setIdDescritivoJira("UI032.0");

                break;
            case USUARIO_ALTERAR_STATUS:

                acao = UtilFabricaDeAcoes.getAcaoSecundaria(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR, USUARIO_GERENCIAR.geAcaoGerenciarEntidade());
                AcaoFormularioEntidadeEditar usuarioAlterarStatus = (AcaoFormularioEntidadeEditar) acao;

                usuarioAlterarStatus.setNomeAcao("Ativar/Desativar");
                usuarioAlterarStatus.setIconeAcao("fa fa-retweet ");
                usuarioAlterarStatus.setPrecisaPermissao(true);
                usuarioAlterarStatus.setIdDescritivoJira("UC013");
                usuarioAlterarStatus.setDescricao("Mostra os detalhes do cadastro de usuario");

                break;

            case USUARIO_LISTARGRUPOS:

                break;
            default:
                throw new AssertionError(this.name());

        }
        return (AcaoDoSistema) acao;
    }

    @Override
    public ItfAcaoDoSistema getRegistro() {
        return getAcaoDoSistema();
    }

    @Override
    public ItfAcaoFormulario getAcaoEntidadeFormulario(ItfAcaoDoSistema acaoPrincipal, Class classeRelacionada, String pXhtml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoGerenciarEntidade geAcaoGerenciarEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoEntidade getAcaoDeEntidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItfAcaoController getAcaoEntidadeController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
