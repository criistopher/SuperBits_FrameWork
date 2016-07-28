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
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.CalculoDeEntidade;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaCampo;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoMuitosParaMuitos;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoMuitosParaUm;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoUmParaMuitos;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.ListaDeEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.Campo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.FabTipoBeanSBGenerico;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivoTexto;
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

    List<ItfAcaoDoSistema> ACOES_PARA_TESTE = new ArrayList<>();
    List<EstruturaDeEntidade> LISTA_ESTRUTURA_DE_ENTIDADE = new ArrayList<>();

    EstruturaDeEntidade ESTRUTURA_DE_ENTIDADE1 = new EstruturaDeEntidade();
    EstruturaDeEntidade ESTRUTURA_DE_ENTIDADE2 = new EstruturaDeEntidade();
    EstruturaDeEntidade ESTRUTURA_DE_ENTIDADE3 = new EstruturaDeEntidade();

    public void criarEstruturaDeEntidade() {

<<<<<<< HEAD
        EstruturaDeEntidade estruturaDeEntidade = new EstruturaDeEntidade();
        ESTRUTURA_DE_ENTIDADE.setNomeEntidade("Comprador");
        ESTRUTURA_DE_ENTIDADE.setIcone("fa fa-shopping-cart");
        ESTRUTURA_DE_ENTIDADE.setPlural("Compradores");
        ESTRUTURA_DE_ENTIDADE.adicionarTags("Comprador,Cliente,Compra,Colaborador,Parceiro");
        ESTRUTURA_DE_ENTIDADE.setTipoEntidade(FabTipoBeanSBGenerico.BEAN_CONTATO_CORPORATIVO);
        ESTRUTURA_DE_ENTIDADE.getListas().add(new ListaDeEntidade("ENUM_TESTE", "ENUM_TESTE", "Campanha", "Pedido", "/* JavaDocTest* /"));
        ESTRUTURA_DE_ENTIDADE.getCalculos().add(new CalculoDeEntidade("ENUM_TESTE", "ENUM_TESTE", "tipoRetorno"));
        ESTRUTURA_DE_ENTIDADE.adicionarEnum("GRUPO_FRM_LISTAR,USUARIO_FRM_EDITAR,GRUPO_FRM_NOVO,GRUPO_FRM_VISUALIZAR,GRUPO_CTR_ALTERAR_STATUS,GRUPO_MB_GERENCIAR");

        EstruturaCampo campoID = new EstruturaCampo(FabCampos.ID.getRegistro());
=======
        LISTA_ESTRUTURA_DE_ENTIDADE.add(ESTRUTURA_DE_ENTIDADE1);

        // INICIO DA CRIACAO DA ESTRUTURA DE ENTIDADE 1
        //
        //
        ESTRUTURA_DE_ENTIDADE1.setNomeEntidade("Comprador");
        ESTRUTURA_DE_ENTIDADE1.setIcone("fa fa-shopping-cart");
        ESTRUTURA_DE_ENTIDADE1.setPlural("Compradores");
        ESTRUTURA_DE_ENTIDADE1.adicionarTags("Comprador,Cliente,Compra,Colaborador,Parceiro");
        ESTRUTURA_DE_ENTIDADE1.setTipoEntidade(FabTipoBeanSBGenerico.BEAN_CONTATO_CORPORATIVO);
        ESTRUTURA_DE_ENTIDADE1.adicionarEnum("GRUPO_FRM_LISTAR,USUARIO_FRM_EDITAR,GRUPO_FRM_NOVO,GRUPO_FRM_VISUALIZAR,GRUPO_CTR_ALTERAR_STATUS,GRUPO_MB_GERENCIAR");

        EstruturaCampo campoID = new EstruturaCampo(FabCampos.ID.getRegistro(), ESTRUTURA_DE_ENTIDADE1);
>>>>>>> 12d1d7d6360673da586f86d4f85fa0a45aac29aa
        campoID.setNomeDeclarado("id");
        campoID.getMascara();
        campoID.setLabel("Id");
        campoID.setDescricao("Numero de registro");
        ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoID);

        EstruturaCampo campoNome = new EstruturaCampo(FabCampos.AAA_DESCRITIVO.getRegistro(), ESTRUTURA_DE_ENTIDADE1);
        campoNome.setNomeDeclarado("nome");
        campoNome.getMascara();
        campoNome.setLabel("nome descritivo");
        campoNome.setDescricao("Descreve o nome da entidade");
        ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoNome);

        EstruturaCampo campoRazao = new EstruturaCampo(FabCampos.TEXTO_SIMPLES.getRegistro(), ESTRUTURA_DE_ENTIDADE1);
        campoRazao.setNomeDeclarado("razaoSocial");
        campoRazao.getMascara();
        campoRazao.setLabel("texto simples");
        campoRazao.setDescricao("Um campo de texto simples");
        ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoRazao);

        EstruturaCampo campoTelefoneNacional = new EstruturaCampo(FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro(), ESTRUTURA_DE_ENTIDADE1);
        campoTelefoneNacional.setNomeDeclarado("telefone");
        campoTelefoneNacional.getMascara();
        campoTelefoneNacional.setLabel("Telefone");
        campoTelefoneNacional.setDescricao("Telefone fixo nacional");
        ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoTelefoneNacional);

        EstruturaCampo campoCnpj = new EstruturaCampo(FabCampos.CNPJ.getRegistro(), ESTRUTURA_DE_ENTIDADE1);
        campoCnpj.setNomeDeclarado("cnpj");
        campoCnpj.getMascara();
        campoCnpj.setLabel("CNPJ");
        campoCnpj.setDescricao("NUmero de registro da empresa");
        ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoCnpj);

        LigacaoUmParaMuitos campoFiliais = new LigacaoUmParaMuitos();
        campoFiliais.setLabel("Filiais");
        campoFiliais.setDescricao("Filiais do Comprador");
        campoFiliais.setNomeDeclarado("filiais");
        campoFiliais.setNomeEntidade("FilialComprador");
        ESTRUTURA_DE_ENTIDADE1.getUmParaMuitos().add(campoFiliais);

        LigacaoMuitosParaUm campoFilialPrincipal = new LigacaoMuitosParaUm();
        campoFilialPrincipal.setLabel("Filial Principal");
        campoFilialPrincipal.setDescricao("Filial Principal do Comprador");
        campoFilialPrincipal.setNomeDeclarado("filialPrincipal");
        campoFilialPrincipal.setNomeEntidade("FilialComprador");
        ESTRUTURA_DE_ENTIDADE1.getMuitosParaUm().add(campoFilialPrincipal);

        LigacaoMuitosParaMuitos campoPermitidos = new LigacaoMuitosParaMuitos();
        campoPermitidos.setJoinTableName("Compradores_Permitidos");
        campoPermitidos.setJoinColumName("acesso_id");
        campoPermitidos.setInverseJoinColumName("comprador_id");
        campoPermitidos.setNomeDeclarado("compradoresPermitidos");
        campoPermitidos.setNomeEntidade("Comprador");

        ESTRUTURA_DE_ENTIDADE1.getMuitosParaMuitos().add(campoPermitidos);

        ListaDeEntidade campoLista = new ListaDeEntidade("ENUM_TESTE", "ENUM_TESTE", "Campanha", "Pedido");
        campoLista.setNomeDeclaracao("declarado");
        ESTRUTURA_DE_ENTIDADE1.getListas().add(campoLista);

        CalculoDeEntidade campoCalculo = new CalculoDeEntidade("ENUM_TESTE", "ENUM_TESTE", "tipoRetorno", FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro(), ESTRUTURA_DE_ENTIDADE1);
        campoCalculo.setLabel("LABEL");
        campoCalculo.setDescricao("DESCRIÇÃO TESTE");
        campoCalculo.setNomeDeclarado("calculoLeroLero");
        ESTRUTURA_DE_ENTIDADE1.getCalculos().add(campoCalculo);

        //
        //
        // FINAL DA CRIAÇÃO DA ESTRUTURA DE ENTIDADE 1
        //
        //
        //
    }

    @Before
    public void criaSimulaco() {

        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoDoSistema());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.USUARIO_FRM_EDITAR.getAcaoDoSistema());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_NOVO.getAcaoDoSistema());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_VISUALIZAR.getAcaoDoSistema());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_MB_GERENCIAR.getAcaoDoSistema());

        criarEstruturaDeEntidade();

    }

    public UtilSBGeradorDeCodigoTest() {
    }

    //@Test
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
    // @Test

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

    //@Test
    public void testMakeClasseAnotacaoInfoAcao() {

    }

    //@Test
    public void testMakeEnumListas() {
        try {

            String codigoGerado = UtilSBGeradorDeCodigo.makeEnumListas(ESTRUTURA_DE_ENTIDADE1);
            SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    //@Test
    public void testMakeListasAnotacao() {

        try {

            String codigoGerado = UtilSBGeradorDeCodigo.makeListasAnotacao();
            SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    //@Test
    public void testMakeEnumCalculos() {

    }

    //@Test
    public void testMakeCalculoAnotacaos() {

        try {

            String codigoGerado = UtilSBGeradorDeCodigo.makeCalculoAnotacaos();
            SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

    //@Test
    public void testMakeEntidade() {
        try {

            String codigoGerado = UtilSBGeradorDeCodigo.makeEntidade(ESTRUTURA_DE_ENTIDADE1);
            SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

    @Test
    public void testCriarArquivosDoSistema() {

        UtilSBGeradorDeCodigo.criarArquivosDoSistema(LISTA_ESTRUTURA_DE_ENTIDADE, ACOES_PARA_TESTE);
    }

}
