/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.ERROS.TesteJunitSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfPaginaGerenciarEntidade;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

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
            editarDados();
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

        try {
            visualisarDados();
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

    public void visualisarDados() {
        pagina.setAcaoSelecionada((AcaoDoSistema) pagina.getAcaoVisualisar());
        pagina.executarAcao(pagina.getEntidadesListadas().get(0));

        assertTrue("O XHTML para visualizar um registro não foi configurado ao executar a ação visualizar", pagina.getXhtmlAcaoAtual().equals(pagina.getAcaoVisualisar().getXHTMLAcao()));
        assertTrue(" entidade selecionada está nula, durante a solicitação de visualização", pagina.getEntidadeSelecionada() != null);
        assertTrue("O boolean is novo registro precisa estar falso durante a visualização", !pagina.isNovoRegistro());
        assertTrue("O boolean  que permite a edição deve ser falso durante a visualização", !pagina.isPodeEditar());

    }

    public void criarNovaEntidade() {
        try {

            // A pagina de gerenciar comprador pega a ação solicitada e define na ação selecionada.(Define a ação selecionada como ação de novo registro)
            pagina.setAcaoSelecionada(pagina.getAcaoNovoRegistro());
            // A pagina executa a ação selecionada.
            pagina.executarAcao(pagina.getEntidadeSelecionada());

            //FabAcaoCadastros.COMPRADOR_NOVO.getAcaoDoSistema().getXHTMLAcao();
            // Testa se o xhtml foi definido como xhtml da ação de novo registro
            assertTrue("O XHTML para cadastrar um novo registro não foi configurado ao executar a ação com um novo registro", pagina.getXhtmlAcaoAtual().equals(pagina.getAcaoNovoRegistro().getXHTMLAcao()));
            assertTrue(" entidade selecionada não foi instanciado", pagina.getEntidadeSelecionada() != null);
            assertTrue("O boolean is novo registro deve ser true ao executar a ação novo registro", pagina.isNovoRegistro());
            assertTrue("O boolean  que permite edição deve ser true ao executação a ação novo registro", pagina.isPodeEditar());
            int quantidadeAnterior = pagina.getEntidadesListadas().size();
            // Define os valores para o usuario do novo comprador
            configurarDAdosInsert();

            // Define que a ação selecionada é salvar alterações
            pagina.setAcaoSelecionada(pagina.getAcaoSalvarAlteracoes());
            pagina.executarAcao(pagina.getEntidadeSelecionada());

            T entidadeCadastrada = pagina.getEntidadeSelecionada();
            int quantidadePosterior = pagina.getEntidadesListadas().size();
            assertTrue("A entidade selecionada não foi cadastrada", entidadeCadastrada != null);

            if (pagina.isTemPesquisa()) {
                assertTrue(" A  lista de registros/entidades deve ser atualizada com um único registro apos o cadastro com sucesso (Verifique o procedore que atualiza a lista, e o parametro tem pesquisa foi configurado corretamente)", pagina.getEntidadesListadas().size() == 1);
                assertTrue("A entidade exibida na lista, não parece ser a entidade que foi cadastrada", pagina.getEntidadesListadas().get(0).equals(entidadeCadastrada));
            } else {
                assertTrue("O novo registro não está aparecendo na lista, (Verifique o procedore que atualiza a lista, e o parametro tem pesquisa foi configurado corretamente)", quantidadePosterior > quantidadeAnterior);

            }

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
    }

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
            pagina.setAcaoSelecionada((AcaoDoSistema) pagina.getAcaoEditar());
            pagina.executarAcao(pagina.getEntidadesListadas().get(0));
            assertTrue("O boolean is novo registro deve ser igual a false", !pagina.isNovoRegistro());
            assertTrue("O boolean  que permite edição não foi modificado", pagina.isPodeEditar());

            assertTrue("O XHTML para Alterar um registro não foi configurado ao executar a ação Alterar Registro", pagina.getXhtmlAcaoAtual().equals(pagina.getAcaoEditar().getXHTMLAcao()));

            assertTrue("O comprador Selecionado está nulo para edição!", pagina.getEntidadeSelecionada() != null);
            assertTrue("O comprador Selecionado não parece ser o que foi configurado ao executar a ação", pagina.getEntidadeSelecionada().equals(pagina.getEntidadesListadas().get(0)));

            String nomeAntigo = ((ItfBeanSimples) pagina.getEntidadeSelecionada()).getNomeCurto();
            String nomenovo = " [EDIT]" + ((ItfBeanSimples) pagina.getEntidadeSelecionada()).getNomeCurto();
            int idEntidadeSelecionada = ((ItfBeanSimples) pagina.getEntidadeSelecionada()).getId();
            try {
                ((EntidadeSimples) pagina.getEntidadeSelecionada()).getCampoByNomeOuAnotacao(FabCampos.AAA_NOME_CURTO.toString()).setValor(nomenovo);
                nomenovo = ((ItfBeanSimples) pagina.getEntidadeSelecionada()).getNomeCurto();
            } catch (Throwable t) {
                fail("Ocorreu um erro ao tentar configurar um novo valor para o nome curto da entidade");
                lancarErroJUnit(t);
            }

            pagina.setAcaoSelecionada(pagina.getAcaoSalvarAlteracoes());
            pagina.executarAcao(pagina.getEntidadeSelecionada());
            ItfBeanSimples entidadeAlterada = null;
            for (T entidade : pagina.getEntidadesListadas()) {
                if (idEntidadeSelecionada == ((ItfBeanSimples) entidade).getId()) {
                    entidadeAlterada = (ItfBeanSimples) entidade;
                }
            }

            assertTrue("O XHTML de listar não foi alterado apos salvar o registro", pagina.getXhtmlAcaoAtual().equals(pagina.getAcaoListarRegistros().getXHTMLAcao()));
            assertTrue("Comprador selecionado não foi cadastrado", entidadeAlterada != null);
            assertTrue("Os dados alterado do registro 0 da lista de entidades permanece o mesmo", !entidadeAlterada.getNomeCurto().equals(nomeAntigo));
            assertTrue("O dado alterado do regsitro 0 da lista  de entidades não parece ser o mesmo da alteração realizada", entidadeAlterada.getNomeCurto().equals(nomenovo));

        } catch (Throwable t) {
            lancarErroJUnit(t);
        }

    }

    public void alterarStatus() {

    }

}
