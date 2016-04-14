/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.testesFW;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.lang.reflect.Method;

/**
 *
 * Classe para executar testes em ações do sistemas
 *
 * @author sfurbino
 */
public abstract class TesteAcaoDoSistema extends TesteJunit {

    private boolean validarAcoesNaoConfiguradas = false;

    private boolean validatAcao(ItfAcaoDoSistema pAcaoDoSistema) {
        try {
            if (pAcaoDoSistema.getEnumAcaoDoSistema() == null) {
                throw new UnsupportedOperationException("O enum da ação do sistema" + pAcaoDoSistema + " não foi definido");
            }

            assertNotNull("O domínio da ação " + pAcaoDoSistema.getNomeUnico() + " não foi definido", pAcaoDoSistema.getEnumAcaoDoSistema().getDominio());

            if (pAcaoDoSistema.isAcaoFormulario()) {

                assertNotNull("O Xhtml da ação de formulario" + pAcaoDoSistema.getNomeUnico() + " está nula", ((ItfAcaoFormulario) pAcaoDoSistema).getXhtml());
                assertNotEquals("O xhtml da acao  de formulario" + pAcaoDoSistema.getNomeUnico() + "está em branco", "", ((ItfAcaoFormulario) pAcaoDoSistema).getXhtml());

            }

            if (pAcaoDoSistema.isTemAcaoPrincipal()) {
                assertNotNull("A ação principal não foi definida em " + pAcaoDoSistema.getNomeUnico(), ((ItfAcaoSecundaria) pAcaoDoSistema).getAcaoPrincipal());
            }

            assertNotNull("O icone da ação " + pAcaoDoSistema.getIconeAcao() + " não foi definido", pAcaoDoSistema.getIconeAcao());

            return true;
        } catch (Throwable t) {
            lancarErroJUnit(t);
        }
        return false;
    }

    /**
     *
     * @param pValidarAcoesNaoCOnfiguradas True para validar todas as ações,
     * False para validar apenas aquelas que foram configuradas
     */
    public TesteAcaoDoSistema(boolean pValidarAcoesNaoCOnfiguradas) {
        validarAcoesNaoConfiguradas = pValidarAcoesNaoCOnfiguradas;
    }

    public void testesBasicosDeAcoes(Class pFabricaDeAcoes) {
        boolean proseguiuSemErro = true;

        for (Object obj : pFabricaDeAcoes.getEnumConstants()) {
            try {
                ItfAcaoDoSistema novaAcao = ((ItfFabricaAcoes) obj).getAcaoDoSistema();
                validatAcao(novaAcao);

            } catch (Throwable t) {
                proseguiuSemErro = false;
                lancarErroJUnit(t);
            }

            assertTrue("Aconteceu um erro na ação " + obj, proseguiuSemErro);

        }

    }

}
