/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import aux.FabConfigCoreSBSBWebPaginas;
import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class UtilTestePaginaTest extends TesteJunit {

    public UtilTestePaginaTest() {
    }

    @Test
    public void testTestaAcaoFormulario() {
        try {
            UtilTestePagina.testaAcaoFormulario(FabAcaoSeguranca.GRUPO_FRM_EDITAR.getAcaoDoSistema().comoFormularioEntidade());
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Test
    public void testTestaconfigIcone() {
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(FabConfigCoreSBSBWebPaginas.DESENVOLVIMENTO.getConfigurador(), true);
    }

}
