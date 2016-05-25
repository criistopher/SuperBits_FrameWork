/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabConfigCoreSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class MapaAcoesSistemaTest {

    public MapaAcoesSistemaTest() {
    }

    @Test
    public void testMakeMapaAcoesSistema() {

        SBCore.configurar(FabConfigCoreSBCore.DESENVOLVIMENTO.getConfigurador(), true);
        System.out.println(SBCore.getCaminhoDesenvolvimento());
        MapaAcoesSistema.makeMapaAcoesSistema();

    }

    @Test
    public void testGetAcoesByEntidade() {
    }

    @Test
    public void testGetAcoesByDominioEModulo() {
    }

    @Test
    public void testGetAcoesControllersByEntidade() {
    }

    @Test
    public void testGetAcoesControllerByEntidadeEModulo() {
    }

    @Test
    public void testGetAcoesListagemByEntidadeEModulo() {
    }

    @Test
    public void testGetAcaoDoSistema() {
    }

    @Test
    public void testGetAcaoDeEntidade() {
    }

    @Test
    public void testGetAcaoEntidadeFormulario() {
    }

    @Test
    public void testGetAcaoEntidadeController() {
    }

    @Test
    public void testGetAcaoController() {
    }

    @Test
    public void testGeAcaoGerenciarEntidade() {
    }

    @Test
    public void testGetEntidadeDominio() {
    }

    @Test
    public void testGetNomeModulo() {
    }

    @Test
    public void testGetRegistro() {
    }

    public class MapaAcoesSistemaImpl extends MapaAcoesSistema {
    }

}
