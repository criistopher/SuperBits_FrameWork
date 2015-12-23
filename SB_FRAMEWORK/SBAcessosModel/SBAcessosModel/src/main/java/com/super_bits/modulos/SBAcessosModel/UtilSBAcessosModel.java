/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel;

import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.PermissaoSB;
import com.super_bits.modulos.SBAcessosModel.model.AcessoSBWebPaginas;
import com.super_bits.modulos.SBAcessosModel.model.GrupoUsuarioSB;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.UsuarioSistema;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
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
                ModuloAcaoSistema novoModulo = new ModuloAcaoSistema();
                novoModulo.setNome(classeModulo.getSimpleName());
                novoModulo.setId(classeModulo.getSimpleName().hashCode());
                novoModulo.setDescricao(UtilSBCoreStrings.GetLorenIpsilum(5, UtilSBCoreStrings.TIPO_LOREN.PALAVRAS));
                novoModulo = (ModuloAcaoSistema) UtilSBPersistencia.mergeRegistro(novoModulo, em);

                for (Method metodo : metodos) {
                    PermissaoSB novoAcesso = new PermissaoSB(metodo);
                    novoAcesso.getAcao().setModuloAcaoSistema(novoModulo);
                    AcaoDoSistema acao = (AcaoDoSistema) novoAcesso.getAcao();

                    acao = (AcaoDoSistema) UtilSBPersistencia.mergeRegistro(acao, em);

                    PermissaoSB acessoEncontrado = (PermissaoSB) UtilSBPersistencia.getRegistroByID(PermissaoSB.class, novoAcesso.getId(), em);
                    if (acessoEncontrado == null) {

                        UtilSBPersistencia.mergeRegistro(acao, em);
                        UtilSBPersistencia.mergeRegistro(novoAcesso, em);

                    }
                }
                em.getTransaction().commit();
            }
        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro criando acessos no banco", t);
            FabErro.LANCAR_EXCECÃO.PARA_TUDO.paraSistema(null, t);
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

                if (acessoPagina
                        == null) {
                    throw new UnsupportedOperationException("Ouve um problema ao Tentar localizar informaçoes de acesso da pagina" + pRecurso);

                }
                UsuarioSB usuario = (UsuarioSB) UtilSBPersistencia.getRegistroByID(UsuarioSB.class, pUsuario.getId(), em);

                if (acessoPagina.getUsuarios()
                        .contains(usuario)) {
                    return true;
                }
                for (GrupoUsuarioSB grupo
                        : acessoPagina.getGrupoUsuarios()) {
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

    public static List<AcaoDoSistema> acoesPermitidasDoGrupo(GrupoUsuarioSB pGrupo) {

        return null;
    }

}
