/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoFormulario;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.PaginaSimples;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilTestePagina {

    public static void testaAcaoFormulario(ItfAcaoFormulario pAcao) {

        Contracts.assertNotNull(pAcao, " A ação não foi definida, impossivel testar ação de formulario em " + pAcao.getNomeUnico());
        Contracts.assertNotEmpty(pAcao.getXhtml(), " O xhtml do formulário não foi definido para " + pAcao.getNomeUnico());
        Contracts.assertNotNull(pAcao.getXhtml(), " O xhtml do formulário não foi definido " + pAcao.getNomeUnico());

        if (pAcao.getXhtml().equals(AcaoFormulario.VARIAVEIS_ACAO_DO_SISTEMA.VIEW_NAO_IMPLEMENTADA.toString())) {
            throw new UnsupportedOperationException("O xhtml da ação" + pAcao.getNomeUnico() + " está definido como não implementado");
        }
        Contracts.assertTrue(UtilSBCoreArquivos.isArquivoExiste(SBWebPaginas.getCaminhoWebAppDeveloper() + pAcao.getXhtml()),
                "O arquivo xhtml da ação " + pAcao.getNomeUnico() + " não foi encontrado em " + SBWebPaginas.getCaminhoWebAppDeveloper() + pAcao.getXhtml());

    }

    public static void testaconfigIcone(ItfFabricaAcoes pAcaoDoSistema) {

        String icone = pAcaoDoSistema.getAcaoDoSistema().getIconeAcao();

        Assert.assertNotNull("O Icone da acao " + pAcaoDoSistema.getAcaoDoSistema().getNomeUnico() + " esta nulo", icone);
        Assert.assertNotSame("O Icone da acao " + pAcaoDoSistema.getAcaoDoSistema().getNomeUnico() + " esta nulo", "");

        if (!(icone.startsWith("fa ") || icone.startsWith("icon"))) {
            throw new UnsupportedOperationException("O ícone  da ação " + pAcaoDoSistema.getAcaoDoSistema().getNomeUnico() + icone + " deveria começar com [fa ] (Para os fontawsome) ou [icon] (para os icones nativos)");
        }
        if (icone.startsWith("fa ")) {

            String iconeSemInicio = icone.substring(3);
            String arquivoCSSFOntAnsome = SBWebPaginas.getCaminhoWebResourcesDeveloper() + "/adamantium-layout/css/font-awesome.css";
            Contracts.assertTrue(UtilSBCoreArquivos.isArquivoExiste(arquivoCSSFOntAnsome), "O arquivo font-awesome.css não foi encontrado no sistema");

            if (!UtilSBCoreArquivoTexto.isTemPalavraNoArquivo(arquivoCSSFOntAnsome, iconeSemInicio)) {

                throw new UnsupportedOperationException("O ícone  da ação " + pAcaoDoSistema.getAcaoDoSistema().getNomeUnico() + " com nome [" + iconeSemInicio + "] não foi encontrado no arquivo css do FontAswome e");
            }
        }

    }

    public static void testaConfigPaginaBasico(ItfB_Pagina pagina) throws Throwable {
        String classepagina = "PAgina NULO ENVIADA";
        try {

            if (pagina == null) {
                throw new UnsupportedOperationException("enviado pagina nula para testar config basica");
            }
            classepagina = pagina.getClass().getSimpleName();
            if (!classepagina.equals("PaginaSimples")) {
                try {
                    ItfAcaoGerenciarEntidade acaoGerenciarDominio = pagina.getAcaoVinculada();
                    UtilTestePagina.testaAcaoFormulario((ItfAcaoFormulario) acaoGerenciarDominio);
                    assertNotNull("ação vinculada", acaoGerenciarDominio);

                    UtilTestePagina.testaconfigIcone(acaoGerenciarDominio.getEnumAcaoDoSistema());
                } catch (Throwable t) {
                    throw new UnsupportedOperationException("Erro validando Ação gerenciar dominio", t);

                }
            }

            pagina.getTitulo();
            pagina.getUrlPadrao();
            pagina.getRecursoXHTML();
            pagina.getLinkRotulo();
            pagina.getParametrosURL();

            assertNotNull("Titulo retornou nulo para:" + pagina.getClass().getSimpleName(), pagina.getTitulo());
            assertNotNull("URL retornou nulo para:" + pagina.getClass().getSimpleName(), pagina.getTitulo());
            assertNotNull("link rotulo retornou nulo para:" + pagina.getClass().getSimpleName(), pagina.getTitulo());
            assertNotNull("XHTML da pagina retornou  nulo para:" + pagina.getClass().getSimpleName(), pagina.getTitulo());
            if (!pagina.getClass().getSimpleName().equals(PaginaSimples.class.getSimpleName())) {
                System.out.println("Anotacao para" + pagina.getTitulo() + ":" + pagina.getAcaoVinculada());
                assertNotNull("Ação vinculada retornou nulo para:" + pagina.getClass().getSimpleName(), pagina.getAcaoVinculada());
            }

        } catch (Throwable t) {

            throw new UnsupportedOperationException("Erro validando " + classepagina, t);

        }
    }

}
