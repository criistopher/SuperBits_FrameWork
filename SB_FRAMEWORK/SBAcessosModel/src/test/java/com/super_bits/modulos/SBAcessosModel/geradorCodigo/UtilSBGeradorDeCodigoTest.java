/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.geradorCodigo;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.TesteAcessosModelPadrao;
import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.FabTipoEntidades;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBGeradorDeCodigoTest extends TesteAcessosModelPadrao {

    List<AcaoDoSistema> ACOES_PARA_TESTE;

    @Before
    public void criaSimulaco() {

        ACOES_PARA_TESTE = new ArrayList<>();
        //ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoDoSistema());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_EDITAR.getAcaoDoSistema());
        //ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_NOVO.getAcaoDoSistema());
        //ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_VISUALIZAR.getAcaoDoSistema());
        //ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema());
        //ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_MB_GERENCIAR.getAcaoDoSistema());

    }

    public UtilSBGeradorDeCodigoTest() {
    }

    @Test
    public void testMakeAnotacaoDaAcao() {
        try {
            System.out.println("Teste obtendo propriedades");
            for (ItfAcaoDoSistema acao : ACOES_PARA_TESTE) {

                String anotacaoDaAcao = UtilSBGeradorDeCodigo.makeAnotacaoDaAcao(acao);
                System.out.println(acao.getNomeAcao());
                System.out.println(acao.getIconeAcao());
                if (acao.isUmaAcaoFormulario()) {
                    System.out.println(acao.getComoFormulario().getXhtml());
                }
                SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Anotação gerada para " + acao.getNomeAcao() + "= \n " + anotacaoDaAcao);
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo anotação da ação", t);
        }
    }

    public void testMakeEnumFabricaDeAcoes() {
        try {
            System.out.println("Teste obtendo propriedades");
            for (ItfAcaoDoSistema acao : ACOES_PARA_TESTE) {

                System.out.println(acao.getNomeAcao());
                System.out.println(acao.getIconeAcao());
                if (acao.isUmaAcaoFormulario()) {
                    System.out.println(acao.getComoFormulario().getXhtml());
                }
            }
            ItfModuloAcaoSistema modulo = FabAcaoSeguranca.GRUPO_MB_GERENCIAR.getAcaoDoSistema().getModulo();
            String classeEnumformada = UtilSBGeradorDeCodigo.makeEnumFabricaDeAcoes((List) ACOES_PARA_TESTE, modulo);
            SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Enum Gerado= \n " + classeEnumformada);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo anotação da ação", t);
        }
    }

    @Test
    public void testMakeClasseAnotacaoInfoAcao() {

    }

    @Test
    public void testMakeEnumListas() {
    }

    @Test
    public void testMakeListasAnotacao() {
    }

    @Test
    public void testMakeEnumCalculos() {
    }

    @Test
    public void testMakeCalculoAnotacaos() {
    }

    public void testMakeEntidade() {
        try {

            EstruturaDeEntidade comprador = new EstruturaDeEntidade();
<<<<<<< HEAD
            comprador.setNomeEntidade("Comprador");
            comprador.setIcone("fa fa-shopping-cart");
            comprador.setPlural("Compradores");
            comprador.adicionarTags("Comprador,Cliente,Compra,Colaborador,Parceiro");
            comprador.setTipoEntidade(FabTipoEntidades.BEAN_CONTATO_CORPORATIVO);
=======

            comprador.setNomeEntidade("Comprador");
>>>>>>> b4cfc5bac39100a04e530f67238cf91b79512bdc

            EstruturaCampo campoID = new EstruturaCampo(FabCampos.ID.getRegistro());
            campoID.setNomeDeclarado("id");
            campoID.getMascara();
            comprador.getCampos().add(campoID);

            EstruturaCampo campoNome = new EstruturaCampo(FabCampos.AAA_DESCRITIVO.getRegistro());
            campoNome.setNomeDeclarado("nome");
            campoNome.getMascara();
            comprador.getCampos().add(campoNome);

            EstruturaCampo campoRazao = new EstruturaCampo(FabCampos.TEXTO_SIMPLES.getRegistro());
            campoRazao.setNomeDeclarado("razaoSocial");
            campoRazao.getMascara();
            comprador.getCampos().add(campoRazao);

            EstruturaCampo campoTelefoneNacional = new EstruturaCampo(FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro());
            campoTelefoneNacional.setNomeDeclarado("telefone");
            campoTelefoneNacional.getMascara();
            comprador.getCampos().add(campoTelefoneNacional);

            EstruturaCampo campoCnpj = new EstruturaCampo(FabCampos.CNPJ.getRegistro());
            campoCnpj.setNomeDeclarado("cnpj");
            campoCnpj.getMascara();
            comprador.getCampos().add(campoCnpj);

            String codigoGerado = UtilSBGeradorDeCodigo.makeEntidade(comprador);
            SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n " + codigoGerado);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

    @Test
    public void testCriarArquivosDoSistema() {
    }

}
