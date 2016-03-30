/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.testesFW.TesteAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;

/**
 *
 * @author desenvolvedor
 */
public abstract class TesteAcoesWebPaginas extends TesteAcaoDoSistema {

    public TesteAcoesWebPaginas(boolean pValidarAcoesNaoCOnfiguradas) {
        super(pValidarAcoesNaoCOnfiguradas);
    }

    protected void testesAcoesWebPAginas(ItfFabricaAcoes pFabricaDeAcoes) {

        for (ItfFabricaAcoes obj : pFabricaDeAcoes.getClass().getEnumConstants()) {

            String icone = obj.getAcaoDoSistema().getIconeAcao();
            if (!(icone.startsWith("fa ") || icone.startsWith("icon"))) {
                throw new UnsupportedOperationException("O ícone " + icone + " deveria começar com [fa ] (Para os fontawsome) ou [icon] (para os icones nativos)");
            }
            if (icone.startsWith("fa ")) {
                if (!UtilSBCoreArquivoTexto.isTemPalavraNoArquivo(SBWebPaginas.getCaminhoWebResourcesDeveloper() + "/adamantium-layout/css/font-awesome.css", icone)) {
                    throw new UnsupportedOperationException("O ícone " + icone + " não foi encontrado no arquivo css do FontAswome");
                }
            }

        }

    }

}
