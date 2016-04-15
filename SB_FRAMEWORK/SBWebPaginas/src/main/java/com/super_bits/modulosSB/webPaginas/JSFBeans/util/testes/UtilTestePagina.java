/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import com.sun.source.tree.AssertTree;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import org.hibernate.validator.internal.util.Contracts;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilTestePagina {

    public static void testaAcaoFormulario(ItfAcaoFormulario pAcao) {
        SBCore.enviarAvisoAoUsuario(SBWebPaginas.getCaminhoWebAppDeveloper());

        Contracts.assertNotNull(pAcao, " A ação não foi definida, impossivel testar ação de formulario ");
        Contracts.assertNotEmpty(pAcao.getXhtml(), " O xhtml do formulário não foi definido para " + pAcao.getNomeUnico());
        Contracts.assertNotNull(pAcao.getXhtml(), " O xhtml do formulário não foi definido " + pAcao.getNomeUnico());
        Contracts.assertTrue(UtilSBCoreArquivos.isArquivoExiste(SBWebPaginas.getCaminhoWebAppDeveloper() + pAcao.getXhtml()),
                "O arquivo xhtml da ação " + pAcao.getNomeUnico() + " não foi encontrado em " + SBWebPaginas.getCaminhoWebAppDeveloper() + pAcao.getXhtml());

    }

    public static void testaconfigIcone(ItfFabricaAcoes pAcaoDoSistema) {

        String icone = pAcaoDoSistema.getAcaoDoSistema().getIconeAcao();
        if (!(icone.startsWith("fa ") || icone.startsWith("icon"))) {
            throw new UnsupportedOperationException("O ícone " + icone + " deveria começar com [fa ] (Para os fontawsome) ou [icon] (para os icones nativos)");
        }
        if (icone.startsWith("fa ")) {

            String iconeSemInicio = icone.substring(3);
            Contracts.assertTrue(UtilSBCoreArquivos.isArquivoExiste(SBWebPaginas.getCaminhoWebResourcesDeveloper() + "/adamantium-layout/css/font-awesome.css"), "O arquivo font-awesome.css não foi encontrado no sistema");
            if (!UtilSBCoreArquivoTexto.isTemPalavraNoArquivo(SBWebPaginas.getCaminhoWebResourcesDeveloper() + "/adamantium-layout/css/font-awesome.css", iconeSemInicio)) {
                throw new UnsupportedOperationException("O ícone " + iconeSemInicio + " não foi encontrado no arquivo css do FontAswome");
            }
        }

    }

}
