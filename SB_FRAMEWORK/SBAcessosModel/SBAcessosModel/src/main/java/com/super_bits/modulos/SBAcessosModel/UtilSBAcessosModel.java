/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel;

import com.super_bits.Controller.Interfaces.ItfControlerAPP;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;

import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.view.InfoPagina;
import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Marcos Vinicius
 */
public class UtilSBAcessosModel {

    public static void criarNovosAcessosNoBanco(EntityManager em) {

        try {
            Class[] classesControllers = SBCore.getConfiguradorDePermissao().getClassesController();
            if (classesControllers == null) {
                return;
            }
            for (Class classeModulo : classesControllers) {
                em.getTransaction().begin();

                Method[] metodos = classeModulo.getDeclaredMethods();

                for (Method metodo : metodos) {
                    PermissaoSB novoAcesso = new PermissaoSB(metodo);

                    AcaoDoSistema acao = (AcaoDoSistema) novoAcesso.getAcao();
                    UtilSBPersistencia.mergeRegistro(acao.getModulo(), em);
                    if (acao.isTemAcaoPrincipal()) {

                        UtilSBPersistencia.mergeRegistro(((ItfAcaoSecundaria) acao).getAcaoPrincipal(), em
                        );

                        if (acao.isPrecisaPermissao()) {
                            acao = (AcaoDoSistema) UtilSBPersistencia.mergeRegistro(acao, em);
                        }
                        PermissaoSB acessoEncontrado = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, novoAcesso.getId(), em);
                        if (acessoEncontrado == null) {

                            UtilSBPersistencia.mergeRegistro(acao, em);
                            UtilSBPersistencia.mergeRegistro(novoAcesso, em);

                        }
                    }
                }
                em.getTransaction().commit();

                criaNovasPermissaoParaPaginas(em);

            }

        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando acessos no banco", t);
            FabErro.LANCAR_EXCECÃO.PARA_TUDO.paraSistema(null, t);
        }

    }

    /**
     *
     * ATENÇÃO ESTE MÉTODO SÓ DEVE SER CHAMADO PARA VERIFICAÇÃO DE ACESSO A
     * PAGINAS VINCULADAS A UMA AÇÃO
     *
     *
     * @param pUsuario.
     * @param pAcao
     * @return
     */
    public static boolean acessoAcaoPermitido(ItfUsuario pUsuario, AcaoDoSistema pAcao) {

        if (pUsuario.getEmail().equals(new UsuarioSistemaRoot().getEmail())) {
            return true;
        }

        Class[] classesControllers = SBCore.getConfiguradorDePermissao().getClassesController();
        if (classesControllers == null) {
            return false;
        }
        for (Class classeModulo : classesControllers) {
            try {
                ItfControlerAPP controle = (ItfControlerAPP) classeModulo.newInstance();
                if (controle.possuiEstaAcao(pAcao)) {
                    return controle.isAcessoPermitido(pUsuario, pAcao);
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro identificando acesso a pagina", ex);
            }

        }
        return false;

    }

    private static void criaNovasPermissaoParaPaginas(EntityManager em) {
        try {
            List<Class> paginas = UtilSBCoreReflexao.getClassesComEstaAnotacao(InfoPagina.class, "com.super_bits.vip.superkompras.webPaginas");

            for (Class pg : paginas) {
                ItfFabricaAcoes fabricaAcao = UtilSBController.getFabricaAcaoByClasse(pg);
                if (fabricaAcao != null) {
                    em.getTransaction().begin();
                    AcaoDoSistema acao = (AcaoDoSistema) fabricaAcao.getAcaoDoSistema();
                    UtilSBPersistencia.mergeRegistro(acao, em);
                    PermissaoSB novaPermissao = new PermissaoSB(fabricaAcao);

                    if (acao.isPrecisaPermissao()) {
                        PermissaoSB permissãoEncontrada = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, novaPermissao.getId(), em);
                        if (permissãoEncontrada != null) {
                            UtilSBPersistencia.mergeRegistro(novaPermissao, em);
                        }
                    }
                    em.getTransaction().commit();
                }

            }

        } catch (Throwable t) {
            FabErro.PARA_TUDO.paraSistema("Erro criando Acesos WebPagina", t);
        }

    }

    public ModuloAcaoSistema getModuloByAcaoEnum(ItfFabricaAcoes pAcao) {
        return (ModuloAcaoSistema) pAcao.getAcaoDoSistema().getModulo();
    }

}
