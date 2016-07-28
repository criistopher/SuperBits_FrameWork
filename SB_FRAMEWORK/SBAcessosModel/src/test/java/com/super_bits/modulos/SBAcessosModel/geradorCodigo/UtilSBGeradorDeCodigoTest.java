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
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.EstruturaDeEntidade;
<<<<<<< HEAD
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoMuitosParaMuitos;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoMuitosParaUm;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.LigacaoUmParaMuitos;
import com.super_bits.modulos.SBAcessosModel.geradorCodigo.model.ListaDeEntidade;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
=======
>>>>>>> 7b695a7bd14e89479c89f3c14177dfffcc3381ab
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.FabTipoBeanSBGenerico;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

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

        LISTA_ESTRUTURA_DE_ENTIDADE.add(ESTRUTURA_DE_ENTIDADE1);
        LISTA_ESTRUTURA_DE_ENTIDADE.add(ESTRUTURA_DE_ENTIDADE2);
        LISTA_ESTRUTURA_DE_ENTIDADE.add(ESTRUTURA_DE_ENTIDADE3);

        // INICIO DA CRIACAO DA ESTRUTURA DE ENTIDADE 1
        //
        //
        ESTRUTURA_DE_ENTIDADE1.setNomeEntidade("CompradorTest1");
        ESTRUTURA_DE_ENTIDADE1.setIcone("fa fa-shopping-cart");
        ESTRUTURA_DE_ENTIDADE1.setPlural("Compradores");
        ESTRUTURA_DE_ENTIDADE1.adicionarTags("Comprador,Cliente,Compra,Colaborador,Parceiro");
        ESTRUTURA_DE_ENTIDADE1.setTipoEntidade(FabTipoBeanSBGenerico.BEAN_CONTATO_CORPORATIVO);
        ESTRUTURA_DE_ENTIDADE1.adicionarEnum("GRUPO_FRM_LISTAR,USUARIO_FRM_EDITAR,GRUPO_FRM_NOVO,GRUPO_FRM_VISUALIZAR,GRUPO_CTR_ALTERAR_STATUS,GRUPO_MB_GERENCIAR");
<<<<<<< HEAD

        EstruturaCampo campoID = new EstruturaCampo(FabCampos.ID.getRegistro(), ESTRUTURA_DE_ENTIDADE1);
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

        ListaDeEntidade campoLista = new ListaDeEntidade("ENUM_TESTE", "ENUM_TESTE", "Campanha", "Pedido", "");
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
        // // INICIO DA CRIACAO DA ESTRUTURA DE ENTIDADE 2
        //
        //
        ESTRUTURA_DE_ENTIDADE2.setNomeEntidade("CompradorTest2");
        ESTRUTURA_DE_ENTIDADE2.setIcone("fa fa-shopping-cart");
        ESTRUTURA_DE_ENTIDADE2.setPlural("Compradores");
        ESTRUTURA_DE_ENTIDADE2.adicionarTags("Comprador,Cliente,Compra,Colaborador,Parceiro");
        ESTRUTURA_DE_ENTIDADE2.setTipoEntidade(FabTipoBeanSBGenerico.BEAN_CONTATO_CORPORATIVO);
        ESTRUTURA_DE_ENTIDADE2.adicionarEnum("GRUPO_FRM_LISTAR,USUARIO_FRM_EDITAR,GRUPO_FRM_NOVO,GRUPO_FRM_VISUALIZAR,GRUPO_CTR_ALTERAR_STATUS,GRUPO_MB_GERENCIAR");

        EstruturaCampo campoID2 = new EstruturaCampo(FabCampos.ID.getRegistro(), ESTRUTURA_DE_ENTIDADE2);
        campoID2.setNomeDeclarado("id");
        campoID2.getMascara();
        campoID2.setLabel("Id");
        campoID2.setDescricao("Numero de registro");
        ESTRUTURA_DE_ENTIDADE2.getCampos().add(campoID2);

        EstruturaCampo campoNome2 = new EstruturaCampo(FabCampos.AAA_DESCRITIVO.getRegistro(), ESTRUTURA_DE_ENTIDADE2);
        campoNome2.setNomeDeclarado("nome");
        campoNome2.getMascara();
        campoNome2.setLabel("nome descritivo");
        campoNome2.setDescricao("Descreve o nome da entidade");
        ESTRUTURA_DE_ENTIDADE2.getCampos().add(campoNome2);

        EstruturaCampo campoRazao2 = new EstruturaCampo(FabCampos.TEXTO_SIMPLES.getRegistro(), ESTRUTURA_DE_ENTIDADE2);
        campoRazao2.setNomeDeclarado("razaoSocial");
        campoRazao2.getMascara();
        campoRazao2.setLabel("texto simples");
        campoRazao2.setDescricao("Um campo de texto simples");
        ESTRUTURA_DE_ENTIDADE2.getCampos().add(campoRazao2);

        EstruturaCampo campoTelefoneNacional2 = new EstruturaCampo(FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro(), ESTRUTURA_DE_ENTIDADE2);
        campoTelefoneNacional2.setNomeDeclarado("telefone");
        campoTelefoneNacional2.getMascara();
        campoTelefoneNacional2.setLabel("Telefone");
        campoTelefoneNacional2.setDescricao("Telefone fixo nacional");
        ESTRUTURA_DE_ENTIDADE2.getCampos().add(campoTelefoneNacional2);

        EstruturaCampo campoCnpj2 = new EstruturaCampo(FabCampos.CNPJ.getRegistro(), ESTRUTURA_DE_ENTIDADE2);
        campoCnpj2.setNomeDeclarado("cnpj");
        campoCnpj2.getMascara();
        campoCnpj2.setLabel("CNPJ");
        campoCnpj2.setDescricao("NUmero de registro da empresa");
        ESTRUTURA_DE_ENTIDADE2.getCampos().add(campoCnpj2);

        LigacaoUmParaMuitos campoFiliais2 = new LigacaoUmParaMuitos();
        campoFiliais2.setLabel("Filiais");
        campoFiliais2.setDescricao("Filiais do Comprador");
        campoFiliais2.setNomeDeclarado("filiais");
        campoFiliais2.setNomeEntidade("FilialComprador");
        ESTRUTURA_DE_ENTIDADE2.getUmParaMuitos().add(campoFiliais2);

        LigacaoMuitosParaUm campoFilialPrincipal2 = new LigacaoMuitosParaUm();
        campoFilialPrincipal2.setLabel("Filial Principal");
        campoFilialPrincipal2.setDescricao("Filial Principal do Comprador");
        campoFilialPrincipal2.setNomeDeclarado("filialPrincipal");
        campoFilialPrincipal2.setNomeEntidade("FilialComprador");
        ESTRUTURA_DE_ENTIDADE2.getMuitosParaUm().add(campoFilialPrincipal2);

        LigacaoMuitosParaMuitos campoPermitidos2 = new LigacaoMuitosParaMuitos();
        campoPermitidos2.setJoinTableName("Compradores_Permitidos");
        campoPermitidos2.setJoinColumName("acesso_id");
        campoPermitidos2.setInverseJoinColumName("comprador_id");
        campoPermitidos2.setNomeDeclarado("compradoresPermitidos");
        campoPermitidos2.setNomeEntidade("Comprador");

        ESTRUTURA_DE_ENTIDADE2.getMuitosParaMuitos().add(campoPermitidos2);

        ListaDeEntidade campoLista2 = new ListaDeEntidade("ENUM_TESTE", "ENUM_TESTE", "Campanha", "Pedido", "");
        campoLista2.setNomeDeclaracao("declarado");
        ESTRUTURA_DE_ENTIDADE2.getListas().add(campoLista2);

        CalculoDeEntidade campoCalculo2 = new CalculoDeEntidade("ENUM_TESTE", "ENUM_TESTE", "tipoRetorno", FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro(), ESTRUTURA_DE_ENTIDADE2);
        campoCalculo2.setLabel("LABEL");
        campoCalculo2.setDescricao("DESCRIÇÃO TESTE");
        campoCalculo2.setNomeDeclarado("calculoLeroLero");
        ESTRUTURA_DE_ENTIDADE2.getCalculos().add(campoCalculo2);

        //
        //
        // FINAL DA CRIAÇÃO DA ESTRUTURA DE ENTIDADE 2
        //
        //
        //
        // INICIO DA CRIACAO DA ESTRUTURA DE ENTIDADE 3
        //
        //
        ESTRUTURA_DE_ENTIDADE3.setNomeEntidade("CompradorTest3");
        ESTRUTURA_DE_ENTIDADE3.setIcone("fa fa-shopping-cart");
        ESTRUTURA_DE_ENTIDADE3.setPlural("Compradores");
        ESTRUTURA_DE_ENTIDADE3.adicionarTags("Comprador,Cliente,Compra,Colaborador,Parceiro");
        ESTRUTURA_DE_ENTIDADE3.setTipoEntidade(FabTipoBeanSBGenerico.BEAN_CONTATO_CORPORATIVO);
        ESTRUTURA_DE_ENTIDADE3.adicionarEnum("GRUPO_FRM_LISTAR,USUARIO_FRM_EDITAR,GRUPO_FRM_NOVO,GRUPO_FRM_VISUALIZAR,GRUPO_CTR_ALTERAR_STATUS,GRUPO_MB_GERENCIAR");

        EstruturaCampo campoID3 = new EstruturaCampo(FabCampos.ID.getRegistro(), ESTRUTURA_DE_ENTIDADE3);
        campoID3.setNomeDeclarado("id");
        campoID3.getMascara();
        campoID3.setLabel("Id");
        campoID3.setDescricao("Numero de registro");
        ESTRUTURA_DE_ENTIDADE3.getCampos().add(campoID3);

        EstruturaCampo campoNome3 = new EstruturaCampo(FabCampos.AAA_DESCRITIVO.getRegistro(), ESTRUTURA_DE_ENTIDADE3);
        campoNome3.setNomeDeclarado("nome");
        campoNome3.getMascara();
        campoNome3.setLabel("nome descritivo");
        campoNome3.setDescricao("Descreve o nome da entidade");
        ESTRUTURA_DE_ENTIDADE3.getCampos().add(campoNome3);

        EstruturaCampo campoRazao3 = new EstruturaCampo(FabCampos.TEXTO_SIMPLES.getRegistro(), ESTRUTURA_DE_ENTIDADE3);
        campoRazao3.setNomeDeclarado("razaoSocial");
        campoRazao3.getMascara();
        campoRazao3.setLabel("texto simples");
        campoRazao3.setDescricao("Um campo de texto simples");
        ESTRUTURA_DE_ENTIDADE3.getCampos().add(campoRazao3);

        EstruturaCampo campoTelefoneNacional3 = new EstruturaCampo(FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro(), ESTRUTURA_DE_ENTIDADE3);
        campoTelefoneNacional3.setNomeDeclarado("telefone");
        campoTelefoneNacional3.getMascara();
        campoTelefoneNacional3.setLabel("Telefone");
        campoTelefoneNacional3.setDescricao("Telefone fixo nacional");
        ESTRUTURA_DE_ENTIDADE3.getCampos().add(campoTelefoneNacional3);

        EstruturaCampo campoCnpj3 = new EstruturaCampo(FabCampos.CNPJ.getRegistro(), ESTRUTURA_DE_ENTIDADE3);
        campoCnpj3.setNomeDeclarado("cnpj");
        campoCnpj3.getMascara();
        campoCnpj3.setLabel("CNPJ");
        campoCnpj3.setDescricao("NUmero de registro da empresa");
        ESTRUTURA_DE_ENTIDADE3.getCampos().add(campoCnpj3);

        LigacaoUmParaMuitos campoFiliais3 = new LigacaoUmParaMuitos();
        campoFiliais3.setLabel("Filiais");
        campoFiliais3.setDescricao("Filiais do Comprador");
        campoFiliais3.setNomeDeclarado("filiais");
        campoFiliais3.setNomeEntidade("FilialComprador");
        ESTRUTURA_DE_ENTIDADE3.getUmParaMuitos().add(campoFiliais);

        LigacaoMuitosParaUm campoFilialPrincipal3 = new LigacaoMuitosParaUm();
        campoFilialPrincipal3.setLabel("Filial Principal");
        campoFilialPrincipal3.setDescricao("Filial Principal do Comprador");
        campoFilialPrincipal3.setNomeDeclarado("filialPrincipal");
        campoFilialPrincipal3.setNomeEntidade("FilialComprador");
        ESTRUTURA_DE_ENTIDADE3.getMuitosParaUm().add(campoFilialPrincipal3);

        LigacaoMuitosParaMuitos campoPermitidos3 = new LigacaoMuitosParaMuitos();
        campoPermitidos3.setJoinTableName("Compradores_Permitidos");
        campoPermitidos3.setJoinColumName("acesso_id");
        campoPermitidos3.setInverseJoinColumName("comprador_id");
        campoPermitidos3.setNomeDeclarado("compradoresPermitidos");
        campoPermitidos3.setNomeEntidade("Comprador");

        ESTRUTURA_DE_ENTIDADE3.getMuitosParaMuitos().add(campoPermitidos);

        ListaDeEntidade campoLista3 = new ListaDeEntidade("ENUM_TESTE", "ENUM_TESTE", "Campanha", "Pedido", "");
        campoLista3.setNomeDeclaracao("declarado");
        ESTRUTURA_DE_ENTIDADE3.getListas().add(campoLista3);

        CalculoDeEntidade campoCalculo3 = new CalculoDeEntidade("ENUM_TESTE", "ENUM_TESTE", "tipoRetorno", FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro(), ESTRUTURA_DE_ENTIDADE3);
        campoCalculo3.setLabel("LABEL");
        campoCalculo3.setDescricao("DESCRIÇÃO TESTE");
        campoCalculo3.setNomeDeclarado("calculoLeroLero");
        ESTRUTURA_DE_ENTIDADE3.getCalculos().add(campoCalculo3);

        //
        //
        // FINAL DA CRIAÇÃO DA ESTRUTURA DE ENTIDADE 3
    }

    public void criarFabAcao() {

        ItfModuloAcaoSistema moduloTest1 = new ModuloAcaoSistema();
        moduloTest1.setDescricao("modulo test 1");
        moduloTest1.setId(1);
        moduloTest1.setNome("ModuloTest1");

        ItfAcaoDoSistema acaoTest1 = new AcaoDoSistema();
        acaoTest1.setDescricao("acao test 1");
        acaoTest1.setIconeAcao("fa fa-save");
        acaoTest1.setId(1);
        acaoTest1.setIdDescritivoJira("nao encontrado");
        acaoTest1.setModuloAcaoSistema(moduloTest1);
        acaoTest1.setNome("Test1");
        acaoTest1.setNomeAcao("AcaoTest1");
        acaoTest1.setPrecisaPermissao(true);

        ItfAcaoDoSistema acaoTest2 = new AcaoDoSistema();
        acaoTest2.setDescricao("acao test 2");
        acaoTest2.setIconeAcao("fa fa-retweet");
        acaoTest2.setId(2);
        acaoTest2.setIdDescritivoJira("nao encontrado2");
        acaoTest2.setModuloAcaoSistema(moduloTest1);
        acaoTest2.setNome("Test2");
        acaoTest2.setNomeAcao("AcaoTest2");
        acaoTest2.setPrecisaPermissao(true);

        ItfAcaoDoSistema acaoTest3 = new AcaoDoSistema();
        acaoTest3.setDescricao("acao test 3");
        acaoTest3.setIconeAcao("fa fa-eye");
        acaoTest3.setId(3);
        acaoTest3.setIdDescritivoJira("nao encontrado3");
        acaoTest3.setModuloAcaoSistema(moduloTest1);
        acaoTest3.setNome("Test3");
        acaoTest3.setNomeAcao("AcaoTest3");
        acaoTest3.setPrecisaPermissao(true);

        ACOES_PARA_TESTE.add(acaoTest1);
        ACOES_PARA_TESTE.add(acaoTest2);
        ACOES_PARA_TESTE.add(acaoTest3);

=======
        /**
         * EstruturaCampo campoID = new
         * EstruturaCampo(FabCampos.ID.getRegistro(), ESTRUTURA_DE_ENTIDADE);
         * campoID.setNomeDeclarado("id"); campoID.getMascara();
         * campoID.setLabel("Id"); campoID.setDescricao("Numero de registro");
         * ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoID);
         *
         * EstruturaCampo campoNome = new
         * EstruturaCampo(FabCampos.AAA_DESCRITIVO.getRegistro(),
         * ESTRUTURA_DE_ENTIDADE1); campoNome.setNomeDeclarado("nome");
         * campoNome.getMascara(); campoNome.setLabel("nome descritivo");
         * campoNome.setDescricao("Descreve o nome da entidade");
         * ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoNome);
         *
         * EstruturaCampo campoRazao = new
         * EstruturaCampo(FabCampos.TEXTO_SIMPLES.getRegistro(),
         * ESTRUTURA_DE_ENTIDADE1); campoRazao.setNomeDeclarado("razaoSocial");
         * campoRazao.getMascara(); campoRazao.setLabel("texto simples");
         * campoRazao.setDescricao("Um campo de texto simples");
         * ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoRazao);
         *
         * EstruturaCampo campoTelefoneNacional = new
         * EstruturaCampo(FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro(),
         * ESTRUTURA_DE_ENTIDADE1);
         * campoTelefoneNacional.setNomeDeclarado("telefone");
         * campoTelefoneNacional.getMascara();
         * campoTelefoneNacional.setLabel("Telefone");
         * campoTelefoneNacional.setDescricao("Telefone fixo nacional");
         * ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoTelefoneNacional);
         *
         * EstruturaCampo campoCnpj = new
         * EstruturaCampo(FabCampos.CNPJ.getRegistro(), ESTRUTURA_DE_ENTIDADE1);
         * campoCnpj.setNomeDeclarado("cnpj"); campoCnpj.getMascara();
         * campoCnpj.setLabel("CNPJ"); campoCnpj.setDescricao("NUmero de
         * registro da empresa");
         * ESTRUTURA_DE_ENTIDADE1.getCampos().add(campoCnpj);
         *
         * LigacaoUmParaMuitos campoFiliais = new LigacaoUmParaMuitos();
         * campoFiliais.setLabel("Filiais"); campoFiliais.setDescricao("Filiais
         * do Comprador"); campoFiliais.setNomeDeclarado("filiais");
         * campoFiliais.setNomeEntidade("FilialComprador");
         * ESTRUTURA_DE_ENTIDADE1.getUmParaMuitos().add(campoFiliais);
         *
         * LigacaoMuitosParaUm campoFilialPrincipal = new LigacaoMuitosParaUm();
         * campoFilialPrincipal.setLabel("Filial Principal");
         * campoFilialPrincipal.setDescricao("Filial Principal do Comprador");
         * campoFilialPrincipal.setNomeDeclarado("filialPrincipal");
         * campoFilialPrincipal.setNomeEntidade("FilialComprador");
         * ESTRUTURA_DE_ENTIDADE1.getMuitosParaUm().add(campoFilialPrincipal);
         *
         * LigacaoMuitosParaMuitos campoPermitidos = new
         * LigacaoMuitosParaMuitos();
         * campoPermitidos.setJoinTableName("Compradores_Permitidos");
         * campoPermitidos.setJoinColumName("acesso_id");
         * campoPermitidos.setInverseJoinColumName("comprador_id");
         * campoPermitidos.setNomeDeclarado("compradoresPermitidos");
         * campoPermitidos.setNomeEntidade("Comprador");
         *
         * ESTRUTURA_DE_ENTIDADE1.getMuitosParaMuitos().add(campoPermitidos);
         *
         * ListaDeEntidade campoLista = new ListaDeEntidade("ENUM_TESTE",
         * "ENUM_TESTE", "Campanha", "Pedido", "");
         * campoLista.setNomeDeclaracao("declarado");
         * ESTRUTURA_DE_ENTIDADE1.getListas().add(campoLista);
         *
         * CalculoDeEntidade campoCalculo = new CalculoDeEntidade("ENUM_TESTE",
         * "ENUM_TESTE", "tipoRetorno",
         * FabCampos.TELEFONE_FIXO_NACIONAL.getRegistro(),
         * ESTRUTURA_DE_ENTIDADE1); campoCalculo.setLabel("LABEL");
         * campoCalculo.setDescricao("DESCRIÇÃO TESTE");
         * campoCalculo.setNomeDeclarado("calculoLeroLero");
         * ESTRUTURA_DE_ENTIDADE1.getCalculos().add(campoCalculo);
         *
         * // // // FINAL DA CRIAÇÃO DA ESTRUTURA DE ENTIDADE 1 // // //
         */
>>>>>>> 7b695a7bd14e89479c89f3c14177dfffcc3381ab
    }

    @Before
    public void criaSimulaco() {

        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_MB_GERENCIAR.getAcaoDoSistema());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_LISTAR.getAcaoDoSistema().getComoFormularioEntidade());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.USUARIO_FRM_EDITAR.getAcaoDoSistema().getComoFormularioEntidade());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_NOVO.getAcaoDoSistema().getComoFormularioEntidade());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_FRM_VISUALIZAR.getAcaoDoSistema().getComoFormularioEntidade());
        ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema().getComoControllerEntidade());

        criarEstruturaDeEntidade();
        //criarFabAcao();

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
                ACOES_PARA_TESTE.add(FabAcaoSeguranca.GRUPO_CTR_ALTERAR_STATUS.getAcaoDoSistema().getComoControllerEntidade());

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

    @Test
    public void testMakeEnumCalculos() {
        try {
            String codigoGerado = UtilSBGeradorDeCodigo.makeListasAnotacao();
            SBCore.getCentralDeMensagens().enviarMsgAlertaAoDesenvolvedor("Classe gerada \n" + codigoGerado);
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

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

    //@Test
    public void testCriarArquivosDoSistema() {

        UtilSBGeradorDeCodigo.criarArquivosDoSistema(LISTA_ESTRUTURA_DE_ENTIDADE, ACOES_PARA_TESTE);
    }

}
