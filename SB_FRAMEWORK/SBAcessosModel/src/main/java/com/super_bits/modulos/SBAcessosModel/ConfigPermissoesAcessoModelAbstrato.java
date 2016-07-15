package com.super_bits.modulos.SBAcessosModel;

import com.super_bits.Controller.ConfigPermissaoAbstratoSBCore;
import com.super_bits.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfPermissao;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author desenvolvedor
 */
public abstract class ConfigPermissoesAcessoModelAbstrato extends ConfigPermissaoAbstratoSBCore {

    private static EntityManager emSistemaAcessos;
    private static boolean acoesSistemaCriado = false;

    public ConfigPermissoesAcessoModelAbstrato(Class[] pClassesControllers) {
        super(pClassesControllers);
    }

    public EntityManager getEmPermissoes() {

        if (emSistemaAcessos == null) {
            emSistemaAcessos = UtilSBPersistencia.getNovoEM();
        } else if (!emSistemaAcessos.isOpen()) {
            emSistemaAcessos = UtilSBPersistencia.getNovoEM();
        } else {
            emSistemaAcessos.close();
            emSistemaAcessos = UtilSBPersistencia.getNovoEM();

        }

        return emSistemaAcessos;

    }

    protected void criaAcoesNoBancoDeDados() {
        if (acoesSistemaCriado) {
            return;
        }
        acoesSistemaCriado = true;
        for (ItfAcaoDoSistema acao : acoesByHashMetodo.values()) {
            AcaoDoSistema acaoPersist = (AcaoDoSistema) acao;

            if (acaoPersist.isTemAcaoPrincipal()) {
                ItfAcaoSecundaria acaosecundaria = (ItfAcaoSecundaria) acao;
                UtilSBPersistencia.mergeRegistro(acaosecundaria.getAcaoPrincipal().getModulo(), getEmPermissoes());
                UtilSBPersistencia.mergeRegistro(acaosecundaria.getAcaoPrincipal(), getEmPermissoes());

            }
            UtilSBPersistencia.mergeRegistro(acao.getModulo(), getEmPermissoes());
            UtilSBPersistencia.mergeRegistro(acaoPersist, getEmPermissoes());

        }
    }

    @Override
    public List<ItfPermissao> configuraPermissoes() {
        criaAcoesNoBancoDeDados();
        //Exemplo busca acessos no banco de dados

        UtilSBAcessosModel.criarNovosAcessosNoBanco(getEmPermissoes());
        List<ItfPermissao> resp = new ArrayList<>();

        try {
            resp = (List<ItfPermissao>) UtilSBPersistencia.getListaTodos(PermissaoSB.class, getEmPermissoes());

        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro obtendo lista de acessos", e);
        }
        return resp;

    }

    @Override
    public List<ItfUsuario> configuraUsuarios() {

        List<ItfUsuario> resposta = (List<ItfUsuario>) UtilSBPersistencia.getListaTodos(UsuarioSB.class, getEmPermissoes()
        );

        return resposta;
    }

    @Override
    public void atualizarInformacoesDePermissoesDoSistema() {
        ControllerAppAbstratoSBCore.reloadAcessos();

    }

    public static List<AcaoDoSistema> listarAcoesDoGrupo(@NotNull GrupoUsuarioSB pGrpUsuario, @NotNull ModuloAcaoSistema pModulo) {
        List<AcaoDoSistema> resp = new ArrayList<>();

        for (ItfAcaoDoSistema acao : pModulo.getAcoes()) {
            PermissaoSB permissao = (PermissaoSB) ControllerAppAbstratoSBCore.getPermissaoPorAcao(acao);
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

}
