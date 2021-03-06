/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.MensagemProgramador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.testes.TestesCore;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author salvioF
 */
public class UtilSBCoreReflexaoObjetoSuperBitsTest extends TesteJunit {

    public UtilSBCoreReflexaoObjetoSuperBitsTest() {
    }

    @Test
    public void testGetNomeObjeto() {
        try {
            // teste obtendo view por tipo de lista
            List<UsuarioAnonimo> teste = new ArrayList<>();
            Class classe = teste.getClass();

            Type[] tipos = classe.getGenericInterfaces();

            Type tipo = classe.getGenericSuperclass();

            Class componente = classe.getComponentType();

            String genercString = classe.toGenericString();

            ParameterizedType parameterizedType = (ParameterizedType) teste.getClass().getGenericSuperclass();
            Class classse = (Class<?>) parameterizedType.getActualTypeArguments()[0];

            UTILSBCoreDesktopApp.showMessageStopProcess(new MensagemProgramador(classse.getSimpleName()));
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    @Test
    public void testGetNomeObjetoPlural() {
    }

    @Test
    public void testGetCamposDoObjeto() {
    }

    @Override
    protected void configAmbienteDesevolvimento() {
        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.PRODUCAO);
    }

}
