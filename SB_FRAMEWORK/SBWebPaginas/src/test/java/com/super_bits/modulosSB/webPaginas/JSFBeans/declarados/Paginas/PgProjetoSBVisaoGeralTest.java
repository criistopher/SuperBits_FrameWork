/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas;

import com.super_bits.modulosSB.webPaginas.ConfiguradorSBCoreWebPaginaTestes;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;

import com.super_bits.modulos.SBAcessosModel.fabricas.FabAcaoProjetoSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class PgProjetoSBVisaoGeralTest extends TesteJunit {

    public PgProjetoSBVisaoGeralTest() {
    }

    @Test
    public void testInit() {
        try {

            String xhtml = FabAcaoProjetoSB.PROJETO_GERENCIAR_MB.getAcaoDoSistema().getComoFormularioEntidade().getXhtml();
            PgProjetoSBVisaoGeral paginaProgeto = new PgProjetoSBVisaoGeral();
            paginaProgeto.getAcaoVinculada();
            paginaProgeto.init();

            for (ItfModuloAcaoSistema modulo : paginaProgeto.getModulosDoSistema()) {
                System.out.println(modulo.getNome());
                for (ItfAcaoDoSistema acao : modulo.getAcoes()) {
                    System.out.println(acao.getNome());

                }
            }

            System.out.println(paginaProgeto.getAcaoVinculada().getXhtml());
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorSBCoreWebPaginaTestes(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

}
