/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller;

import com.super_bits.Controller.ControllerAppAbstratoSBCore;
import static com.super_bits.Controller.ControllerAppAbstratoSBCore.getPermissaoPorAcao;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.ItfResposta;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

/**
 *
 * @author desenvolvedor
 */
public class ModuloSeguranca extends ControllerAbstratoSBPersistencia {

    @InfoAcaoSeguranca(acao = FabAcaoSeguranca.GRUPO_LISTAR)
    public static List<AcaoDoSistema> listarAcoesDoGrupo(@NotNull GrupoUsuarioSB pGrpUsuario, @NotNull ModuloAcaoSistema pModulo) {
        List<AcaoDoSistema> resp = new ArrayList<>();

        for (ItfAcaoDoSistema acao : pModulo.getAcoes()) {
            PermissaoSB permissao = (PermissaoSB) getPermissaoPorAcao(acao);
            //TODO sobrescrever metodo permissao no modulo SBPErmissao utilizando loadBY
            //   permissao = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, permissao.getId(), em);
            if (permissao != null) {
                if (permissao.getGruposPermitidos().contains(pGrpUsuario)) {
                    resp.add((AcaoDoSistema) acao);
                }
            }

        }

        return resp;
    }

    @InfoAcaoSeguranca(acao = FabAcaoSeguranca.GRUPO_ALTERAR_STATUS, padraoBloqueado = false)
    public static ItfResposta grupoAlterarStatus(@NotNull GrupoUsuarioSB pGrupo) {
        ItfResposta resp = getNovaRespostaAutorizaChecaNulo(pGrupo);
        if (!resp.isSucesso()) {
            return resp.dispararMensagens();
        }
        EntityManager em = UtilSBPersistencia.getNovoEMIniciandoTransacao();
        if (pGrupo.isAtivo()) {
            if (pGrupo.isTipoGrupoNativo()) {

                return resp.addMensagemAlertaDisparaERetorna("Este grupo é um grupo nativo do sistema, portanto não pode ser alterado");
            }

            pGrupo.setAtivo(false);
        } else {
            pGrupo.setAtivo(true);
        }
        pGrupo = (GrupoUsuarioSB) UtilSBPersistencia.mergeRegistro(pGrupo, em);
        UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
        ControllerAppAbstratoSBCore.reloadAcessos();
        return resp.setRetornoDisparaERetorna(pGrupo);

    }

    /**
     *
     * Adiciona permissão de acesso a um grupo de usuários
     *
     * @param pGrpUsuario GRupo de usuarios onde a permissão será adicionada
     * @param pModulos
     * @param pEM
     *
     * @return
     */
    @InfoAcaoSeguranca(acao = FabAcaoSeguranca.GRUPO_SALVAR_ALTERACOES, padraoBloqueado = false)
    public static ItfResposta grupoDeUsuariosSalvarAlteracoes(@NotNull GrupoUsuarioSB pGrpUsuario, @NotNull List<ModuloAcaoSistema> pModulos, EntityManager pEM) {

        ItfResposta resp = getNovaRespostaAutorizaChecaNulo(pGrpUsuario);
        if (pGrpUsuario == null || pModulos == null) {
            return resp.addMensagemErroDisparaERetorna("Selecione o grupo de usuario e as ações do sistema");
        }

        if (!resp.isSucesso()) {
            return resp.dispararMensagens();
        }
        UtilSBPersistencia.iniciarTransacao(pEM);
        pGrpUsuario = (GrupoUsuarioSB) UtilSBPersistencia.mergeRegistro(pGrpUsuario, pEM);
        if (!UtilSBPersistencia.finalizarTransacao(pEM)) {
            return resp.addMensagemErroDisparaERetorna("Erro Atualizando informações do grupo");
        }
        ControllerAppAbstratoSBCore.reloadAcessos();
        UtilSBPersistencia.iniciarTransacao(pEM);
        if (pGrpUsuario == null) {
            return resp.addMensagemErroDisparaERetorna("Erro ao salvar alterações basicas do grupo");
        }
        for (ModuloAcaoSistema modulo : pModulos) {

            for (ItfAcaoDoSistema acao : modulo.getAcoes()) {
                PermissaoSB permissao = (PermissaoSB) getPermissaoPorAcao(acao);

                //TODO sobrescrever metodo permissao no modulo SBPErmissao utilizando loadBY
                //   permissao = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, permissao.getId(), em);
                if (permissao == null) {
                    FabErro.SOLICITAR_REPARO.paraDesenvolvedor("O registro PermissaoSB não foi encontrado para esta ação", null);
                }
                if (permissao != null) {
                    if (modulo.getSelecaoAcoes().contains(acao)) {
                        if (!permissao.getGruposPermitidos().contains(pGrpUsuario)) {
                            permissao.getGruposPermitidos().add(pGrpUsuario);
                        }

                    } else {
                        permissao.getGruposPermitidos().remove(pGrpUsuario);
                    }
                    System.out.println("Adicionou" + pGrpUsuario + "a permissao" + permissao.getNomeCurto());
                    if (UtilSBPersistencia.mergeRegistro(permissao, pEM) == null) {
                        resp.addAlerta("Aconteceu um erro ao tentar salvar a ação do sistema" + acao.getNomeAcao());
                    }
                }
            }

        }

        if (!UtilSBPersistencia.finalizarTransacao(pEM)) {
            resp.addErro("Erro salvando registro no banco de dados");
        }
        ControllerAppAbstratoSBCore.reloadAcessos();
        if (resp.isSucesso()) {
            resp.addAviso("As definições de segurança do grupo " + pGrpUsuario.getNome() + " foram atualizadas");
        }

        return resp.setRetornoDisparaERetorna(pGrpUsuario);
    }

    @InfoAcaoSeguranca(acao = FabAcaoSeguranca.USUARIO_ALTERAR_STATUS, padraoBloqueado = false)
    public static ItfResposta usuarioAlterarStatus(@NotNull UsuarioSB pUsuario) {
        ItfResposta resp = getNovaRespostaAutorizaChecaNulo(pUsuario);

        EntityManager em = UtilSBPersistencia.getNovoEMIniciandoTransacao();
        if (pUsuario.isAtivo()) {
            pUsuario.setAtivo(false);
        } else {
            pUsuario.setAtivo(true);
        }
        pUsuario = (UsuarioSB) UtilSBPersistencia.mergeRegistro(pUsuario, em);
        UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
        return resp.setRetornoDisparaERetorna(pUsuario);

    }

    @InfoAcaoSeguranca(acao = FabAcaoSeguranca.USUARIO_SALVAR_ALTERACOES, padraoBloqueado = false)
    public static ItfResposta usuarioPersistirAlteracoes(@NotNull UsuarioSB pUsuario, EntityManager pEM) {
        ItfResposta resp = getNovaRespostaAutorizaChecaNulo(pUsuario);
        if (pUsuario.getGrupo() == null) {
            return resp.addMensagemErroDisparaERetorna("Selecione o grupo do usuário");
        }

        pUsuario.ajustaConfiguracoes();
        UtilSBPersistencia.iniciarTransacao(pEM);
        pUsuario = (UsuarioSB) UtilSBPersistencia.mergeRegistro(pUsuario, pEM);

        if (pUsuario == null) {

            return resp.addMensagemErroDisparaERetorna("Erro Salvando usuário no sistema");
        }

        GrupoUsuarioSB grupoUsuario = (GrupoUsuarioSB) UtilSBPersistencia.getRegistroByID(GrupoUsuarioSB.class, pUsuario.getGrupo().getId(), pEM);
        if (!grupoUsuario.getUsuarios().contains(pUsuario)) {
            grupoUsuario.getUsuarios().add(pUsuario);
        }
        UtilSBPersistencia.mergeRegistro(grupoUsuario, pEM);

        if (!UtilSBPersistencia.finalizarTransacao(pEM)) {
            pEM.close();
            return resp.addMensagemErroDisparaERetorna("não foi possível gravar no banco de dados");

        }

        ControllerAppAbstratoSBCore.reloadAcessos();

        return resp.setRetornoDisparaERetorna(pUsuario);

    }

    /**
     * @InfoAcaoCadastro(acao = FabAcaoCadastros.GRP_USUARIO_REMOVE,
     * padraoBloqueado = false) public static ItfResposta
     * UsuarioGruporemover(@NotNull GrupoUsuarioSB pGrupo, UsuarioSB
     * pUsuarioRemocao) { ItfResposta resp =
     * getNovaRespostaAutorizaChecaNulo(GrupoUsuarioSB.class);
     *
     * if (pUsuarioRemocao.getGrupo().equals(pGrupo)) { return
     * resp.addMensagemErroDisparaERetorna("Não é possível remover o grupo
     * principal do usuário"); }
     *
     * EntityManager em = UtilSBPersistencia.getNovoEMIniciandoTransacao(); try
     * { pGrupo.getUsuarios().remove(pUsuarioRemocao); } catch (Throwable t) {
     * return resp.addMensagemErroDisparaERetorna("O usuário não foi encontrado
     * nesta lista"); } pGrupo = (GrupoUsuarioSB)
     * UtilSBPersistencia.mergeRegistro(pGrupo, em);
     * UtilSBPersistencia.finzalizaTransacaoEFechaEM(em); return
     * resp.setRetornoDisparaERetorna(pGrupo);
     *
     * }
     *
     * @InfoAcaoCadastro(acao = FabAcaoCadastros.GRP_USUARIO_ADD,
     * padraoBloqueado = false) public static ItfResposta
     * usuarioGrupoAdicionar(@NotNull GrupoUsuarioSB pGrupo, UsuarioSB
     * pUsuarioRemocao) { ItfResposta resp =
     * getNovaRespostaAutorizaChecaNulo(GrupoUsuarioSB.class);
     *
     * EntityManager em = UtilSBPersistencia.getNovoEMIniciandoTransacao();
     *
     * pGrupo.getUsuarios().add(pUsuarioRemocao);
     *
     * pGrupo = (GrupoUsuarioSB) UtilSBPersistencia.mergeRegistro(pGrupo, em);
     * if (pGrupo == null) { return resp.addMensagemErroDisparaERetorna("Não foi
     * possível adicionar o usuário ao grupo"); }
     *
     * UtilSBPersistencia.finzalizaTransacaoEFechaEM(em); return
     * resp.setRetornoDisparaERetorna(pGrupo);
     *
     * }
     *
     * @InfoAcaoCadastro(acao = FabAcaoCadastros.GRP_USUARIO_ADD,
     * padraoBloqueado = false) public static ItfResposta
     * UsuarioGrupoAdicionar(@NotNull GrupoUsuarioSB pGrupo, UsuarioSB pUsuario)
     * { ItfResposta resp =
     * getNovaRespostaAutorizaChecaNulo(GrupoUsuarioSB.class); if (pUsuario ==
     * null || pGrupo == null) { return resp.addMensagemErroDisparaERetorna("O
     * grupo e o usuário são obrigatórios "); } EntityManager em =
     * UtilSBPersistencia.getNovoEMIniciandoTransacao(); if
     * (!pGrupo.getUsuarios().contains(pUsuario)) {
     * pGrupo.getUsuarios().add(pUsuario); } else { return
     * resp.addMensagemAlertaDisparaERetorna("Este usuário já pertence a este
     * grupo"); }
     *
     * pGrupo = (GrupoUsuarioSB) UtilSBPersistencia.mergeRegistro(pGrupo, em);
     * pUsuario.getGruposAdicionais().add(pGrupo);
     * UtilSBPersistencia.mergeRegistro(pUsuario, em); if (pGrupo == null) {
     * return resp.addMensagemErroDisparaERetorna("Não foi possível adicionar o
     * usuário ao grupo"); }
     *
     * UtilSBPersistencia.finzalizaTransacaoEFechaEM(em); return
     * resp.setRetornoDisparaERetorna(pGrupo);
     *
     * }
     */
}
