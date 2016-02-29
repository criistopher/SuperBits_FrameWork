/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.InfoCampos.cep.ItemLocal;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBCoreCEPTest extends TesteJunit {

    @Before
    public void setUp() {
    }

    @Test
    public void testConfiguraEndereco() {

        ItemLocal local = new ItemLocal();

        UtilSBCoreCEP.configuraEndereco("31110600", local);

        System.out.println("Loca=" + local.getNome());
        System.out.println("Estado" + local.getBairro().getCidade().getUnidadeFederativa().getNome());
        System.out.println("Bairro" + local.getBairro().getNome());
        System.out.println("Cidade" + local.getBairro().getCidade().getNome());

    }

    @Test
    public void testCepsEncontrados() {

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        configDesenvolvedorPadraoSemClasseDePermissoes();
    }

}
