/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfPaginaGerenciarEntidade;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author cristopher
 */
public abstract class TestePaginaEntidade<T> extends TesteJunitSBPersistencia {

    protected ItfPaginaGerenciarEntidade<T> pagina;

    @Before
    public void inicio() {
        pagina = definirPagina();

    }

    @Test
    public void testeFluxo() {

        try {
            criarNovaEntidade();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
        try {
            pesquisar();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
        try {
            //         editarDados();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

        try {
            //          alterarStatus();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    public abstract void configurarDAdosInsert();

    public abstract void configurarDadosEditar();

    public abstract void configurarPesquisa();

    public abstract ItfPaginaGerenciarEntidade definirPagina();

    public void criarNovaEntidade() {
        try {
            assertTrue("O XHTML de listar não selecionado ao abrir a pagina",
                    pagina.getXhtmlAcaoAtual().equals(pagina.getAcaoListarRegistros().getXHTMLAcao()));

            // A pagina de gerenciar comprador pega a ação solicitada e define na ação selecionada.(Define a ação selecionada como ação de novo registro)
            pagina.setAcaoSelecionada(pagina.getAcaoNovoRegistro());
            // A pagina executa a ação selecionada.
            pagina.executarAcao(pagina.getEntidadeSelecionada());

<<<<<<< HEAD
            //FabAcaoCadastros.COMPRADOR_NOVO.getAcaoDoSistema().getXHTMLAcao();
            // Testa se o xhtml foi definido como xhtml da ação de novo registro
            assertTrue("O XHTML para cadastrar um novo registro não foi configurado ao executar a ação com um novo registro", pagina.getXhtmlAcaoAtual().equals(pagina.getAcaoNovoRegistro().getXHTMLAcao()));
            assertTrue(" entidade selecionada não foi instanciado", pagina.getEntidadeSelecionada() != null);
            assertTrue("O boolean is novo registro não foi modificado", pagina.isNovoRegistro());
            assertTrue("O boolean  que permite edição não foi modificado", pagina.isPodeEditar());

            // Define os valores para o usuario do novo comprador
            configurarDAdosInsert();
=======
        //FabAcaoCadastros.COMPRADOR_NOVO.getAcaoDoSistema().getXHTMLAcao();
        // Testa se o xhtml foi definido como xhtml da ação de novo registro
        assertTrue("O XHTML para cadastrar um novo registro não foi configurado ao executar a ação com um novo registro", pagina.getXhtmlAcaoAtual().equals(pagina.getAcaoNovoRegistro().getXHTMLAcao()));
        assertTrue(" entidade selecionada não foi instanciado", pagina.getEntidadeSelecionada() != null);
        assertTrue("O boolean is novo registro não foi modificado", pagina.isNovoRegistro());
        assertTrue("O boolean  que permite edição não foi modificado", pagina.isPodeEditar());
        int quantidadeAnterior = pagina.getEntidadesListadas().size();
        // Define os valores para o usuario do novo comprador
        configurarDAdosInsert();
>>>>>>> 819d66f49e69fd45912f566a49c49d83f1c0281f

            // Define que a ação selecionada é salvar alterações
            pagina.setAcaoSelecionada(pagina.getAcaoSalvarAlteracoes());
            pagina.executarAcao(pagina.getEntidadeSelecionada());

<<<<<<< HEAD
            T entidadeCadastrada = pagina.getEntidadeSelecionada();

            assertTrue("A entidade selecionada não foi cadastrada", entidadeCadastrada != null);
            assertTrue("A entidade não foi exibida na lista, a lista não possui um registro único", pagina.getEntidadesListadas().size() == 1);
            assertTrue("A entidade exibida na lista, não parece ser a entidade que foi cadastrada", pagina.getEntidadesListadas().get(0).equals(entidadeCadastrada));
        } catch (Throwable t) {
            lancarErroJUnit(t);
=======
        T entidadeCadastrada = pagina.getEntidadeSelecionada();
        int quantidadePosterior = pagina.getEntidadesListadas().size();
        assertTrue("A entidade selecionada não foi cadastrada", entidadeCadastrada != null);

        if (pagina.isTemPesquisa()) {
            assertTrue(" A  lista de registros/entidades deve ser atualizada com um único registro apos o cadastro com sucesso (Verifique o procedore que atualiza a lista, e o parametro tem pesquisa foi configurado corretamente)", pagina.getEntidadesListadas().size() == 1);
            assertTrue("A entidade exibida na lista, não parece ser a entidade que foi cadastrada", pagina.getEntidadesListadas().get(0).equals(entidadeCadastrada));
        } else {
            assertTrue("O novo registro não está aparecendo na lista, (Verifique o procedore que atualiza a lista, e o parametro tem pesquisa foi configurado corretamente)", quantidadePosterior > quantidadeAnterior);
>>>>>>> 819d66f49e69fd45912f566a49c49d83f1c0281f
        }

    }

<<<<<<< HEAD
    public void pesquisar() {
        try {
            configurarPesquisa();
            pagina.listarDados();

            assertTrue("Nenhum registro foi selecionado na pesquisa", pagina.getEntidadesListadas().size() > 0);
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

    public void editarDados() {
        try {
            //     pagina.setAcaoSelecionada(FabAcaoCadastros.COMPRADOR_ALTERAR.getAcaoDoSistema());
            pagina.executarAcao(pagina.getEntidadesListadas().get(0));
            assertTrue("O boolean is novo registro deve ser igual a false", !pagina.isNovoRegistro());
            assertTrue("O boolean  que permite edição não foi modificado", pagina.isPodeEditar());

            //FabAcaoCadastros.COMPRADOR_NOVO.getAcaoDoSistema().getXHTMLAcao();
            //  assertTrue("O XHTML para Alterar um registro não foi configurado ao executar a ação Alterar Registro", pagina.getXhtmlAcaoAtual().equals(FabAcaoCadastros.COMPRADOR_ALTERAR.getAcaoDoSistema().getXHTMLAcao()));
            assertTrue("O comprador Selecionado está nulo!", pagina.getEntidadeSelecionada() != null);
            assertTrue("O comprador Selecionado não parece ser o que foi configurado ao executar a ação", pagina.getEntidadeSelecionada().equals(pagina.getEntidadesListadas().get(0)));

            //   String cepAntigo = pagina.getEntidadeSelecionada().getCEP();
            //    String cepNovo = "30190030";
            List<T> listaTeste;
=======
    public void editarDados() {

        //  pagina.setParametroPesquisa("nomeFantasia");
        //    pagina.pesquisarComprador();
        assertTrue("Nenhum comprador foi selecionado na pesquisa", pagina.getEntidadesListadas().size() > 0);

        //     pagina.setAcaoSelecionada(FabAcaoCadastros.COMPRADOR_ALTERAR.getAcaoDoSistema());
        pagina.executarAcao(pagina.getEntidadesListadas().get(0));
        assertTrue("O boolean is novo registro deve ser igual a false", !pagina.isNovoRegistro());
        assertTrue("O boolean  que permite edição não foi modificado", pagina.isPodeEditar());

        //FabAcaoCadastros.COMPRADOR_NOVO.getAcaoDoSistema().getXHTMLAcao();
        //  assertTrue("O XHTML para Alterar um registro não foi configurado ao executar a ação Alterar Registro", pagina.getXhtmlAcaoAtual().equals(FabAcaoCadastros.COMPRADOR_ALTERAR.getAcaoDoSistema().getXHTMLAcao()));
        assertTrue("O comprador Selecionado está nulo!", pagina.getEntidadeSelecionada() != null);
        assertTrue("O comprador Selecionado não parece ser o que foi configurado ao executar a ação", pagina.getEntidadeSelecionada().equals(pagina.getEntidadesListadas().get(0)));

        //   String cepAntigo = pagina.getEntidadeSelecionada().getCEP();
        //    String cepNovo = "30190030";
        List<T> listaTeste;
>>>>>>> 819d66f49e69fd45912f566a49c49d83f1c0281f

            pagina.setAcaoSelecionada(pagina.getAcaoSalvarAlteracoes());
            pagina.executarAcao(pagina.getEntidadeSelecionada());

            T entidadeAlterada = pagina.getEntidadeSelecionada();

            assertTrue("O XHTML de listar não foi alterado apos salvar o registro", pagina.getXhtmlAcaoAtual().equals(pagina.getAcaoListarRegistros().getXHTMLAcao()));
            assertTrue("Comprador selecionado não foi cadastrado", entidadeAlterada != null);

<<<<<<< HEAD
            // assertTrue("Os dados da lista não foram atualizados", pagina.getEntidadesListadas().get(0).getCEP().equals(cepNovo));
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
=======
        // assertTrue("Os dados da lista não foram atualizados", pagina.getEntidadesListadas().get(0).getCEP().equals(cepNovo));
>>>>>>> 819d66f49e69fd45912f566a49c49d83f1c0281f
    }

    public void alterarStatus() {

    }

}
