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
import com.super_bits.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.Ips.Ips;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidadeController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.SEGURANCA)
public enum FabAcaoSeguranca implements ItfFabricaAcoes {

    GRUPO_MB_GERENCIAR,
    GRUPO_FRM_NOVO,
    GRUPO_FRM_EDITAR,
    GRUPO_FRM_LISTAR,
    GRUPO_FRM_VISUALIZAR,
    GRUPO_CTR_ALTERAR_STATUS,
    GRUPO_FRM_LISTAR_USUARIOS,
    GRUPO_CTR_SALVAR_MERGE,
    USUARIO_MB_GERENCIAR,
    USUARIO_FRM_NOVO,
    USUARIO_FRM_LISTAR,
    USUARIO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/usuario/editarUsuario.xhtml", icone = "fa fa-edit",
            nomeAcao = "Editar Usuário", precisaPermissao = true, codigoJira = "UI032.1",
            campos = {"[separador:basico]", "email", "senha", "[separador:detalhes]", "telefone", "nome",
                "apelido", "[separador:endereco]", "CEP", "[separador:dadosCadastrais]", "dataHoraInsersao",
                "dataHoraAlteracao", "usuarioInsercao", "usuarioAlteracao"})
    USUARIO_FRM_EDITAR,
    USUARIO_FRM_VISUALIZAR,
    USUARIO_FRM_LISTAR_GRUPOS,
    USUARIO_CTR_ALTERAR_STATUS,
    ACAO_CTR_INTERNA_DO_SISTEMA,
    IP_MB_GERENCIAR,
    IP_FRM_NOVO,
    IP_CTR_SALVAR_MERGE,
    IP_FRM_LISTAR,
    IP_FRM_EDITAR,
    IP_CTR_REMOVER;

    @Override
    public AcaoDoSistema getAcaoDoSistema() {
        if (MapaAcoesSistema.isMapaCriado()) {
            return (AcaoDoSistema) MapaAcoesSistema.getAcaoDoSistema(this);
        }

        ItfAcaoDoSistema acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
        switch (this) {
            case GRUPO_MB_GERENCIAR:
                AcaoGestaoEntidade grupoGerenciar = new AcaoGestaoEntidade(this, GrupoUsuarioSB.class, "/resources/SBComp/seguranca/grupo.xhtml");
                grupoGerenciar.setNome("Gerenciar grupos");
                grupoGerenciar.setIconeAcao("fa fa-users");
                grupoGerenciar.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/grupo/gerenciarGrupo.xhtml");
                return grupoGerenciar;

            case GRUPO_FRM_NOVO:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoFormularioEntidade acaoNovoRegistro = (AcaoFormularioEntidade) acao;
                acaoNovoRegistro.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/usuario/grupo/editarGrupo.xhtml");

                acaoNovoRegistro.setPrecisaPermissao(true);
                acaoNovoRegistro.setDescricao("Permite criar um grupo de usuários para ser utilizado por administradores do VipKompras");
                acaoNovoRegistro.setPrecisaPermissao(false);
                acaoNovoRegistro.setIdDescritivoJira("UI027");

                break;
            case USUARIO_FRM_LISTAR_GRUPOS:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(
                        this);

                AcaoFormularioEntidade acaoListarGrupo = (AcaoFormularioEntidade) acao;

                acaoListarGrupo.setDescricao("Listar grupos de usuários");
                acaoListarGrupo.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/usuario/gruposDoUsuario.xhtml");
                acaoListarGrupo.setIconeAcao("fa fa-users");
                acaoListarGrupo.setPrecisaPermissao(true);
                acaoListarGrupo.setIdDescritivoJira("UI026");

                break;
            case GRUPO_FRM_EDITAR:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoFormularioEntidade acaoEditar = (AcaoFormularioEntidade) acao;

                acaoEditar.setIconeAcao("fa fa-edit");
                acaoEditar.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/grupo/editarGrupo.xhtml");
                acaoEditar.setPrecisaPermissao(true);
                acaoEditar.setIdDescritivoJira("UC021");

                break;
            case GRUPO_FRM_VISUALIZAR:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoFormularioEntidade grpVisualizar = (AcaoFormularioEntidade) acao;

                grpVisualizar.setIconeAcao("fa fa-search-plus");
                grpVisualizar.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/grupo/editarGrupo.xhtml");
                grpVisualizar.setNomeAcao("Ver Detalhe do Grupo");
                grpVisualizar.setPrecisaPermissao(true);
                grpVisualizar.setIdDescritivoJira("UI028");

                break;
            case GRUPO_CTR_ALTERAR_STATUS:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoDeEntidadeController grpAlterarStatus = (AcaoDeEntidadeController) acao;

                grpAlterarStatus.setNomeAcao("Ativar/Desativar");
                grpAlterarStatus.setIconeAcao("fa fa-retweet");
                grpAlterarStatus.setPrecisaPermissao(true);
                grpAlterarStatus.setIdDescritivoJira("UC021");

                break;

            case GRUPO_FRM_LISTAR_USUARIOS:

                break;
            case GRUPO_CTR_SALVAR_MERGE:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
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
                acaoGestao.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/usuario/adicionarUsuario.xhtml");
                return acaoGestao;

            case USUARIO_FRM_NOVO:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoFormularioEntidade usuarioNovo = (AcaoFormularioEntidade) acao;
                usuarioNovo.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/usuario/editarUsuario.xhtml");
                usuarioNovo.setNomeAcao("Criar Novo Usuário");
                usuarioNovo.setIconeAcao("fa fa-plus");
                usuarioNovo.setPrecisaPermissao(true);
                usuarioNovo.setIdDescritivoJira("UI031");

                break;
            case USUARIO_FRM_LISTAR:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoFormularioEntidade usuarioListar = (AcaoFormularioEntidade) acao;
                usuarioListar.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/usuario/listarUsuario.xhtml");
                usuarioListar.setNomeAcao("Listar Usuários");
                usuarioListar.setIconeAcao("fa fa-users");
                usuarioListar.setPrecisaPermissao(true);
                usuarioListar.setIdDescritivoJira("UI030");

                break;
            case USUARIO_CTR_SALVAR_MERGE:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoDeEntidadeController usuarioSalvar = (AcaoDeEntidadeController) acao;

                usuarioSalvar.setIconeAcao("fa fa-save");
                usuarioSalvar.setNomeAcao("Salvar Alterações");
                usuarioSalvar.setIdDescritivoJira("UC013");

                break;
            case USUARIO_FRM_EDITAR:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);

                break;
            case USUARIO_FRM_VISUALIZAR:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoFormularioEntidade usuarioVisualizar = (AcaoFormularioEntidade) acao;
                usuarioVisualizar.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/usuario/editarUsuario.xhtml");
                usuarioVisualizar.setIconeAcao("fa fa-search-plus");
                usuarioVisualizar.setNomeAcao("Visualizar Usuário");
                usuarioVisualizar.setDescricao("Mostra os detalhes do cadastro de usuario");
                usuarioVisualizar.setPrecisaPermissao(true);
                usuarioVisualizar.setIdDescritivoJira("UI032.0");

                break;
            case USUARIO_CTR_ALTERAR_STATUS:

                acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);
                AcaoDeEntidadeController usuarioAlterarStatus = (AcaoDeEntidadeController) acao;

                usuarioAlterarStatus.setNomeAcao("Ativar/Desativar");
                usuarioAlterarStatus.setIconeAcao("fa fa-retweet");
                usuarioAlterarStatus.setPrecisaPermissao(true);
                usuarioAlterarStatus.setIdDescritivoJira("UC013");
                usuarioAlterarStatus.setDescricao("Mostra os detalhes do cadastro de usuario");

                break;

            case ACAO_CTR_INTERNA_DO_SISTEMA:

                break;
            case GRUPO_FRM_LISTAR:
                ItfAcaoFormularioEntidade acaoFormularioListar = (ItfAcaoFormularioEntidade) acao;
                acao.setIconeAcao("fa fa-align-justify");
                acaoFormularioListar.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/grupo/listarGrupos.xhtml");
                break;

            case IP_MB_GERENCIAR:

                AcaoGestaoEntidade acaoGestaoEntidade = (AcaoGestaoEntidade) acao;

                acaoGestaoEntidade.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/ip/gerenciar.xhtml");
                acaoGestaoEntidade.setDescricao("Gerenciamento de IP's");
                acaoGestaoEntidade.setIconeAcao("fa fa-gears");
                acaoGestaoEntidade.setPrecisaPermissao(true);
                acaoGestaoEntidade.setIdDescritivoJira("");

                break;

            case IP_FRM_NOVO:

                AcaoFormularioEntidade ipNovo = (AcaoFormularioEntidade) acao;

                ipNovo.setNomeAcao("Cadastrar um IP");
                ipNovo.setDescricao("Cadastra um novo IP com autorizacao");
                ipNovo.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/ip/editar.xhtml");
                ipNovo.setPrecisaPermissao(true);

                break;
            case IP_CTR_SALVAR_MERGE:

                AcaoDeEntidadeController ipSalvar = (AcaoDeEntidadeController) acao;

                ipSalvar.setIconeAcao("fa fa-save");
                ipSalvar.setNomeAcao("Salvar IP");
                ipSalvar.setDescricao("Salva um novo IP cadastrado");
                ipSalvar.setPrecisaPermissao(true);

                break;
            case IP_FRM_LISTAR:

                AcaoFormularioEntidade ipListar = (AcaoFormularioEntidade) acao;

                ipListar.setNomeAcao("Listar IP");
                ipListar.setDescricao("Exibe lista de IP já autorizados");
                ipListar.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/ip/listar.xhtml");
                ipListar.setPrecisaPermissao(true);

                break;
            case IP_FRM_EDITAR:

                AcaoFormularioEntidade ipEditar = (AcaoFormularioEntidade) acao;

                ipEditar.setNomeAcao("Alterar IP autorizado");
                ipEditar.setDescricao("Altera os dados de um IP autorizado");
                ipEditar.setXhtml("/resources/SBComp/SBSystemPages/seguranca/simples/ip/editar.xhtml");
                ipEditar.setPrecisaPermissao(true);

                break;
            case IP_CTR_REMOVER:

                AcaoDeEntidadeController ipRemover = (AcaoDeEntidadeController) acao;

                ipRemover.setNomeAcao("Remover IP");
                ipRemover.setDescricao("Remove um IP já autorizado");
                ipRemover.setPrecisaPermissao(true);
                ipRemover.setIconeAcao("fa fa-minus");

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
    public Class getEntidadeDominio() {
        switch (this) {
            case GRUPO_MB_GERENCIAR:
            case GRUPO_FRM_NOVO:
            case GRUPO_FRM_EDITAR:
            case GRUPO_FRM_VISUALIZAR:
            case GRUPO_CTR_ALTERAR_STATUS:
            case GRUPO_FRM_LISTAR_USUARIOS:
            case GRUPO_CTR_SALVAR_MERGE:
            case GRUPO_FRM_LISTAR:
                return GrupoUsuarioSB.class;

            case USUARIO_MB_GERENCIAR:
            case USUARIO_FRM_NOVO:
            case USUARIO_FRM_LISTAR:
            case USUARIO_CTR_SALVAR_MERGE:
            case USUARIO_FRM_EDITAR:
            case USUARIO_FRM_VISUALIZAR:
            case USUARIO_FRM_LISTAR_GRUPOS:
            case USUARIO_CTR_ALTERAR_STATUS:

                return UsuarioSB.class;

            case ACAO_CTR_INTERNA_DO_SISTEMA:
                return UsuarioSB.class;

            case IP_CTR_REMOVER:
            case IP_CTR_SALVAR_MERGE:
            case IP_FRM_EDITAR:
            case IP_FRM_LISTAR:
            case IP_FRM_NOVO:
            case IP_MB_GERENCIAR:
                return Ips.class;

            default:
                throw new AssertionError(this.name());

        }
    }

    @Override
    public String getNomeModulo() {
        return UtilFabricaDeAcoesAcessosModel.getModuloByFabrica(this).getNome();
    }

}
