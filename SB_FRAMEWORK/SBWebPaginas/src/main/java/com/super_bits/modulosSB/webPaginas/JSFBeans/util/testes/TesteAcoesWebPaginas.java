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

            UtilTestePagina.testaconfigIcone(obj);

        }

    }

}
