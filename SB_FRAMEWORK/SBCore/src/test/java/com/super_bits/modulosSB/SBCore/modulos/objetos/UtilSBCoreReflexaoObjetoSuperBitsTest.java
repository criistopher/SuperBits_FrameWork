/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvioF
 */
public class UtilSBCoreReflexaoObjetoSuperBitsTest {

    public UtilSBCoreReflexaoObjetoSuperBitsTest() {
    }

    @Test
    public void testGetNomeObjeto() {

        // teste obtendo view por tipo de lista
        List<UsuarioAnonimo> teste = new ArrayList<>();

        Class classse = teste.getClass().getDeclaringClass();
    }

    @Test
    public void testGetNomeObjetoPlural() {
    }

    @Test
    public void testGetCamposDoObjeto() {
    }

}
