/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.sbProjetos.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import com.super_bits.sbProjetos.FabConfigCoreSBProjetosModel;
import com.super_bits.sbProjetos.Model.FabSBProjectClientes;
import com.super_bits.sbProjetos.Model.Projeto;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBProjetosTest extends TesteJunit {

    public UtilSBProjetosTest() {
    }

    @Test
    public void testCriarNovoProjeto() {
        Projeto projetoTeste = new Projeto();
        projetoTeste.setCliente(FabSBProjectClientes.SANTA_CLARA.getRegistro());
        projetoTeste.setNome("Sistema Santa Clara");
        UtilSBProjetos.criarNovoProjeto(projetoTeste);
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfigCoreSBProjetosModel.DESENVOLVIMENTO.getConfiguracao(), true);
    }

}
