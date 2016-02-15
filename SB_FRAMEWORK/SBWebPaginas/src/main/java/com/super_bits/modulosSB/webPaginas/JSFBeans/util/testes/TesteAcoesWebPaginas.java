/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.testes;

import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.testesFW.TesteAcoesDoSistema;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;

/**
 *
 * @author desenvolvedor
 */
public abstract class TesteAcoesWebPaginas extends TesteAcoesDoSistema {

    public TesteAcoesWebPaginas(boolean pValidarAcoesNaoCOnfiguradas) {
        super(pValidarAcoesNaoCOnfiguradas);
    }

    protected void testesAcoesWebPAginas(ItfFabricaAcoes pFabricaDeAcoes) {

        for (ItfFabricaAcoes obj : pFabricaDeAcoes.getClass().getEnumConstants()) {

            String icone = obj.getAcaoDoSistema().getIconeAcao();
            icone.startsWith("fa ");
            if (!UtilSBCoreArquivoTexto.isTemPalavraNoArquivo(SBWebPaginas.getCaminhoWebResourcesDeveloper() + "/adamantium-layout/css/font-awesome.css", icone)) {
                throw new UnsupportedOperationException("O ícone " + icone + " não foi encontrado no arquivo css do FontAswome");
            }

        }

    }

}
