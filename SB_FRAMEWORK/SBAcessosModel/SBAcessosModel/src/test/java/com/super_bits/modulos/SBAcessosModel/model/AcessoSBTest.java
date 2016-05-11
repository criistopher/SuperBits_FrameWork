/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model;

import aux.FabConfigCoreSBSBAcessosModel;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import config.ConfigPersistenciaTestesAcesso;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Eduardo Augusto
 */
public class AcessoSBTest extends TestCase {

    private UsuarioSB novoUsuario;
    private List<UsuarioSB> novosUsuarios;

    public AcessoSBTest(String testName) {
        super(testName);
    }

    @Before
    public void init() {
        SBCore.configurar(FabConfigCoreSBSBAcessosModel.DESENVOLVIMENTO.getConfigurador());
        SBPersistencia.configuraJPA(new ConfigPersistenciaTestesAcesso());

    }

    public void testAddUsuarioPermitido() {
    }

    public void testAddGrupoPermitido() {
    }

    public void testGetNomeAcesso() {
    }

    public void testSetNomeAcesso() {
    }

    public void testGetDescricaoAcesso() {
    }

    public void testPadraoLiberado() {
    }

    public void testGetUsuariosPermitidos() {
    }

    public void testGetGruposPermitidos() {
    }

    @Test
    public void testGetUsuariosDisponiveis() {
        init();
        List<PermissaoSB> acessos = (List<PermissaoSB>) UtilSBPersistencia.getListaTodos(PermissaoSB.class);
        PermissaoSB acessoSelecionado = acessos.get(0);

        acessoSelecionado.getUsuariosDisponiveis();
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("USUARIOS PERMITIDOS");
        System.out.println("#########");
        System.out.println("#########");
        for (ItfUsuario teste : acessoSelecionado.getUsuariosPermitidos()) {
            System.out.println(teste.getNomeCurto());
        }
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("Lista de usuarios disponíveis");
        for (ItfUsuario teste : acessoSelecionado.getUsuariosDisponiveis()) {
            System.out.println(teste.getNomeCurto());

            acessoSelecionado.addUsuarioPermitido((UsuarioSB) teste);
        }
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("NOVOS USUARIOS PERMITIDOS");
        for (ItfUsuario teste : acessoSelecionado.getUsuariosPermitidos()) {
            System.out.println(teste.getNomeCurto());
        }
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("#########");
        System.out.println("Nova lista de usuarios disponíveis");
        for (ItfUsuario teste : acessoSelecionado.getUsuariosDisponiveis()) {
            System.out.println(teste.getNomeCurto());
        }
        UtilSBPersistencia.mergeRegistro(acessoSelecionado);

    }

    public void testGetUsuariosNegados() {
    }

    public void testGetGruposNegados() {
    }

    public void testGetGruposDisponiveis() {
    }

    public void testGetTipoAutenticacao() {
    }

    public void testGetId() {
    }

    public void testSetId() {
    }

    public void testGetDescricao() {
    }

    public void testSetDescricao() {
    }

    public void testSetGruposPermitidos() {
    }

    public void testSetGruposNegados() {
    }

    public void testIsPadraoLiberado() {
    }

    public void testSetPadraoLiberado() {
    }

}
