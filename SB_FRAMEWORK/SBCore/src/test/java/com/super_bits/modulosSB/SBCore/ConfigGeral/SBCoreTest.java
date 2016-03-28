/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfCfgPermissoes;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfUsuario;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.logeventos.ItfCentralEventos;
import com.super_bits.modulosSB.SBCore.sessao.Interfaces.ItfControleDeSessao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author desenvolvedor
 */
public class SBCoreTest {

    public SBCoreTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testGetCliente() {
        System.out.println("getCliente");
        String expResult = "";
        String result = SBCore.getCliente();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsControleDeAcessoDefinido() {
        System.out.println("isControleDeAcessoDefinido");
        boolean expResult = false;
        boolean result = SBCore.isControleDeAcessoDefinido();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testConfigurar() {
        System.out.println("configurar");
        ItfConfiguradorCore configuracoes = null;
        SBCore.configurar(configuracoes);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetEstadoAPP() {
        System.out.println("getEstadoAPP");
        SBCore.ESTADO_APP expResult = null;
        SBCore.ESTADO_APP result = SBCore.getEstadoAPP();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetNomeProjeto() {
        System.out.println("getNomeProjeto");
        String expResult = "";
        String result = SBCore.getNomeProjeto();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDiretorioBase() {
        System.out.println("getDiretorioBase");
        String expResult = "";
        String result = SBCore.getDiretorioBase();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRelatarErro() {
        System.out.println("RelatarErro");
        FabErro pTipoErro = null;
        String pMensagem = "";
        Throwable pErroJava = null;
        SBCore.RelatarErro(pTipoErro, pMensagem, pErroJava);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSalvaInfoThread() {
        System.out.println("salvaInfoThread");
        String pPastaArquivoLog = "";
        boolean milesegundos = false;
        SBCore.salvaInfoThread(pPastaArquivoLog, milesegundos);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCentralDeMensagens() {
        System.out.println("getCentralDeMensagens");
        ItfCentralMensagens expResult = null;
        ItfCentralMensagens result = SBCore.getCentralDeMensagens();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCentralDeEventos() {
        System.out.println("getCentralDeEventos");
        ItfCentralEventos expResult = null;
        ItfCentralEventos result = SBCore.getCentralDeEventos();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsPermitido() {
        System.out.println("isPermitido");
        ItfAcaoDoSistema pAcao = null;
        boolean expResult = false;
        boolean result = SBCore.isPermitido(pAcao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetControleDeSessao() {
        System.out.println("getControleDeSessao");
        ItfControleDeSessao expResult = null;
        ItfControleDeSessao result = SBCore.getControleDeSessao();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetConfiguradorDePermissao() {
        System.out.println("getConfiguradorDePermissao");
        ItfCfgPermissoes expResult = null;
        ItfCfgPermissoes result = SBCore.getConfiguradorDePermissao();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCaminhoDesenvolvimento() {
        System.out.println("getCaminhoDesenvolvimento");
        String expResult = "";
        String result = SBCore.getCaminhoDesenvolvimento();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetGrupoProjeto() {
        System.out.println("getGrupoProjeto");
        String expResult = "";
        String result = SBCore.getGrupoProjeto();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEnviarMensagemUsuario() {
        System.out.println("enviarMensagemUsuario");
        String pMensagem = "";
        FabMensagens pTipoMensagem = null;
        SBCore.enviarMensagemUsuario(pMensagem, pTipoMensagem);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEnviarAvisoAoUsuario() {
        System.out.println("enviarAvisoAoUsuario");
        String pMensagem = "";
        SBCore.enviarAvisoAoUsuario(pMensagem);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUsuarioLogado() {
        System.out.println("getUsuarioLogado");
        ItfUsuario expResult = null;
        ItfUsuario result = SBCore.getUsuarioLogado();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}
