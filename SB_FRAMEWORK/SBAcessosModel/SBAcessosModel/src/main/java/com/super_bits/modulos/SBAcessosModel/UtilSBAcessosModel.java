/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel;

import com.super_bits.modulos.SBAcessosModel.model.AcessoSB;
import com.super_bits.modulos.SBAcessosModel.model.AcessoSBWebPaginas;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.UsuarioSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Marcos Vinicius
 */
public class UtilSBAcessosModel {

    public static void criarNovosAcessosNoBanco() {

        Class[] classesControllers = SBCore.getConfiguradorDeAcessos().getClassesController();
        if (classesControllers == null) {
            return;
        }
        for (Class classe : classesControllers) {
            Method[] metodos = classe.getDeclaredMethods();
            for (Method metodo : metodos) {

                String nomeMetodo = classe.getSimpleName() + "." + metodo.getName();
                AcessoSB novoAcesso = new AcessoSB(metodo);
                System.out.println("Adicionando" + nomeMetodo + " para configuração de controle de acesso ");
                AcessoSB acessoEncontrado = (AcessoSB) UtilSBPersistencia.getRegistroByID(AcessoSB.class, novoAcesso.getId());
                if (acessoEncontrado != null) {
                    UtilSBPersistencia.mergeRegistro(novoAcesso);
                }
            }
        }

    }

    public static boolean acessoAPaginaPermitido(ItfUsuario pUsuario, String pRecurso) {

        try {
            if (pUsuario == null) {
                throw new UnsupportedOperationException("Usuario nulo para verificação de acesso a pagina");
            }

            if (pUsuario.equals(new UsuarioSistema())) {
                return true;
            }

            EntityManager em = UtilSBPersistencia.getNovoEM();
            try {
                AcessoSBWebPaginas acessoPagina = (AcessoSBWebPaginas) UtilSBPersistencia.getRegistroByJPQL("from AcessoSBWebPaginas where recurso='" + pRecurso + "'", AcessoSBWebPaginas.class, em);

                if (acessoPagina == null) {
                    throw new UnsupportedOperationException("Ouve um problema ao Tentar localizar informaçoes de acesso da pagina" + pRecurso);

                }
                UsuarioSB usuario = (UsuarioSB) UtilSBPersistencia.getRegistroByID(UsuarioSB.class, pUsuario.getId(), em);
                if (acessoPagina.getUsuarios().contains(usuario)) {
                    return true;
                }
                for (GrupoUsuarioSB grupo : acessoPagina.getGrupoUsuarios()) {
                    if (grupo.getId() == usuario.getGrupo().getId()) {
                        return true;
                    }
                    if (grupo.getUsuarios().contains(usuario)) {
                        return true;
                    }
                }

                return false;

            } finally {
                if (em != null) {
                    em.close();
                }
            }
        } catch (Throwable e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro verificando pemição para acesso a pagina", e);
            return false;
        }

    }

    public static void criaAcessoWebPaginas(List<AcessoSBWebPaginas> pRecursoPagina) {
        for (AcessoSBWebPaginas novaPagina : pRecursoPagina) {

            UtilSBPersistencia.mergeRegistro(novaPagina);

        }
    }

}
