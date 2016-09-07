/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class UtilSBCoreReflexaoTest extends TesteJunit {

    public UtilSBCoreReflexaoTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of getClassesComEstaAnotacao method, of class UtilSBCoreReflexao.
     */
    @Test
    public void testGetClassesComEstaAnotacao() {

        try {

            List<Class> classes = UtilSBCoreReflexao.getClassesComEstaAnotacao(InfoClasse.class, "com.super_bits");
            assertTrue("nenhuma classe foi encontrada", classes.size() > 0);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

}
