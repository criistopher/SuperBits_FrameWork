/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.Ips.Ips;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.UtilFabricaDeAcoesAcessosModel;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.SEGURANCA)
public enum FabAcaoSeguranca implements ItfFabricaAcoes {

    @InfoTipoAcaoGestaoEntidade(nomeAcao = "Gerenciar Grupos",
            icone = "fa fa-users",
            descricao = "",
            codigoJira = "",
            precisaPermissao = false,
            entidade = GrupoUsuarioSB.class,
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/grupo/gerenciarGrupo.xhtml")
    GRUPO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Novo",
            descricao = "Permite criar um grupo de usuários para ser utilizado por administradores do VipKompras",
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/grupo/editarGrupo.xhtml",
            icone = "",
            precisaPermissao = false,
            codigoJira = "UI027",
            entidade = GrupoUsuarioSB.class)
    GRUPO_FRM_NOVO,
    @InfoTipoAcaoFormulario(entidade = GrupoUsuarioSB.class,
            icone = "fa fa-edit",
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/grupo/editarGrupo.xhtml",
            precisaPermissao = true,
            codigoJira = "UC021")
    GRUPO_FRM_EDITAR,
    GRUPO_FRM_LISTAR,
    @InfoTipoAcaoFormulario(entidade = GrupoUsuarioSB.class,
            icone = "fa fa-search-plus",
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/grupo/editarGrupo.xhtml",
            nomeAcao = "Ver Detalhe do Grupo",
            precisaPermissao = true,
            codigoJira = "UI028")
    GRUPO_FRM_VISUALIZAR,
    @InfoTipoAcaoController(entidade = GrupoUsuarioSB.class,
            nomeAcao = "Ativar/Desativar",
            icone = "fa fa-retweet",
            precisaPermissao = true,
            codigoJira = "UC021")
    GRUPO_CTR_ALTERAR_STATUS,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/grupo/usuariosDoGrupo.xhtml",
            entidade = GrupoUsuarioSB.class)
    GRUPO_FRM_LISTAR_USUARIOS,
    @InfoTipoAcaoController(entidade = GrupoUsuarioSB.class,
            icone = "fa fa-save",
            nomeAcao = "Salvar Alterações",
            precisaPermissao = true,
            codigoJira = "UC021")
    GRUPO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(entidade = UsuarioSB.class,
            nomeAcao = "Usuários",
            icone = "fa fa-user",
            descricao = "Gerenciar Usuários",
            precisaPermissao = true,
            codigoJira = "UI030",
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/usuario/adicionarUsuario.xhtml")
    USUARIO_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(entidade = UsuarioSB.class,
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/usuario/editarUsuario.xhtml",
            nomeAcao = "Criar Novo Usuário",
            icone = "fa fa-plus",
            precisaPermissao = true,
            codigoJira = "UI031")
    USUARIO_FRM_NOVO,
    @InfoTipoAcaoFormulario(entidade = UsuarioSB.class,
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/usuario/listarUsuario.xhtml",
            nomeAcao = "Listar Usuários",
            icone = "fa fa-users",
            codigoJira = "UI030",
            precisaPermissao = true)
    USUARIO_FRM_LISTAR,
    @InfoTipoAcaoController(icone = "fa fa-save",
            entidade = UsuarioSB.class,
            nomeAcao = "Salvar Alterações",
            codigoJira = "UC013")
    USUARIO_CTR_SALVAR_MERGE,
    @InfoTipoAcaoFormulario(xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/usuario/editarUsuario.xhtml", icone = "fa fa-edit",
            nomeAcao = "Editar Usuário", precisaPermissao = true, codigoJira = "UI032.1",
            campos = {"[separador:basico]", "email", "senha", "[separador:detalhes]", "telefone", "nome",
                "apelido", "[separador:endereco]", "CEP", "[separador:dadosCadastrais]", "dataHoraInsersao",
                "dataHoraAlteracao", "usuarioInsercao", "usuarioAlteracao"})
    USUARIO_FRM_EDITAR,
    @InfoTipoAcaoFormulario(entidade = UsuarioSB.class,
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/usuario/editarUsuario.xhtml",
            icone = "fa fa-search-plus",
            nomeAcao = "Visualizar Usuário",
            descricao = "Mostra os detalhes do cadastro de usuario",
            precisaPermissao = true,
            codigoJira = "UI032.0"
    )
    USUARIO_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(descricao = "Listar grupos de usuários",
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/usuario/gruposDoUsuario.xhtml",
            icone = "fa fa-users",
            precisaPermissao = true,
            codigoJira = "UI026",
            entidade = UsuarioSB.class)
    USUARIO_FRM_LISTAR_GRUPOS,
    @InfoTipoAcaoController(nomeAcao = "Ativar/Desativar",
            entidade = UsuarioSB.class,
            icone = "fa fa-retweet",
            codigoJira = "UC013",
            precisaPermissao = true,
            descricao = "Mostra os detalhes do cadastro de usuario")
    USUARIO_CTR_ALTERAR_STATUS,
    @InfoTipoAcaoController(nomeAcao = "Ação interna do Sistema", descricao = "Ação interna generica do sistema", icone = "fa fa-save")
    ACAO_CTR_INTERNA_DO_SISTEMA,
    @InfoTipoAcaoGestaoEntidade(xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/ip/gerenciar.xhtml",
            descricao = "Gerenciamento de IP's",
            precisaPermissao = true,
            icone = "fa fa-gears",
            entidade = Ips.class)
    IP_MB_GERENCIAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Cadastrar um IP",
            descricao = "Cadastra um novo IP com autorizacao",
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/ip/editar.xhtml",
            entidade = Ips.class,
            precisaPermissao = true)
    IP_FRM_NOVO,
    @InfoTipoAcaoController(icone = "fa fa-save",
            descricao = "Salvar IP",
            nomeAcao = "Salvar Novo IP cadastrado",
            precisaPermissao = true)
    IP_CTR_SALVAR_MERGE,
    @InfoTipoAcaoFormulario(descricao = "Exibe lista de IP já autorizados",
            nomeAcao = "Listar IP's",
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/ip/listar.xhtml",
            precisaPermissao = true)
    IP_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Alterar IP autorizado",
            descricao = "Altera os dados de um IP autorizado",
            precisaPermissao = true,
            xhtmlDaAcao = "/resources/SBComp/SBSystemPages/seguranca/simples/ip/editar.xhtml")
    IP_FRM_EDITAR,
    @InfoTipoAcaoController(nomeAcao = "Remover IP",
            descricao = "Remove IP autorizado",
            icone = "fa fa-minus",
            precisaPermissao = true)
    IP_CTR_REMOVER;

    @Override
    public AcaoDoSistema getAcaoDoSistema() {

        if (MapaAcoesSistema.isMapaCriado()) {
            return (AcaoDoSistema) MapaAcoesSistema.getAcaoDoSistema(this);
        }

        ItfAcaoDoSistema acao = UtilFabricaDeAcoesAcessosModel.getNovaAcao(this);

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
