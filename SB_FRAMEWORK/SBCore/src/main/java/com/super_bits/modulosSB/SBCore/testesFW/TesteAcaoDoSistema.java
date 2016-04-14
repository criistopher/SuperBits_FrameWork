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

        if (pAcaoDoSistema.getEnumAcaoDoSistema() == null) {
            throw new UnsupportedOperationException("O enum da ação do sistema" + pAcaoDoSistema + " não foi definido");
        }

        assertNotNull("O domínio da ação " + pAcaoDoSistema.getNomeUnico() + " não foi definido", pAcaoDoSistema.getEnumAcaoDoSistema().getDominio());

        if (pAcaoDoSistema.isAcaoFormulario()) {

            assertNotNull("O Xhtml da ação de formulario" + pAcaoDoSistema.getNomeUnico() + " está nula", ((ItfAcaoFormulario) pAcaoDoSistema).getXhtml());
            assertNotEquals("O xhtml da acao  de formulario" + pAcaoDoSistema.getNomeUnico() + "está em branco", "", ((ItfAcaoFormulario) pAcaoDoSistema).getXhtml());
            assertNotEquals("O xhtml da acao  de formulario" + pAcaoDoSistema.getNomeUnico() + "está em branco", A, ((ItfAcaoFormulario) pAcaoDoSistema).getXhtml());

        }

        if (pAcaoDoSistema.isTemAcaoPrincipal()) {
            assertNotNull("A ação principal não foi definida em " + pAcaoDoSistema.getNomeUnico(), ((ItfAcaoSecundaria) pAcaoDoSistema).getAcaoPrincipal());
        }

        assertNotNull("O icone da ação " + pAcaoDoSistema.getIconeAcao() + " não foi definido", pAcaoDoSistema.getIconeAcao());

        if (pAcaoDoSistema.isConfigurado()) {

            // Verificando Configuração de XHTML
            switch (pAcaoDoSistema.getTipoAcaoSistema()) {

                case ACAO_ENTIDADE_FORMULARIO:
                case ACAO_ENTIDADE_FORMULARIO_MODAL:
                case ACAO_ENTIDADE_GERENCIAR:
                    ItfAcaoFormulario acaoForm = (ItfAcaoFormulario) pAcaoDoSistema;
                    if (acaoForm.getXhtml() == null || acaoForm.getXhtml() == "") {

                        throw new UnsupportedOperationException("A ação de formulário " + acaoForm.getNomeUnico() + " não possui um xhtml cadastrado ");

                    }

                    break;

            }

            // Verificando configuração de Metodo na camada Controller
            switch (pAcaoDoSistema.getTipoAcaoSistema()) {

                case ACAO_ENTIDADE_CONTROLLER:
                case ACAO_CONTROLLER:
                    ItfAcaoController acaocontroller = (ItfAcaoController) pAcaoDoSistema;

                    if (acaocontroller.getIdMetodo() == 0) {

                        throw new UnsupportedOperationException("O id do metodo da ação controller: " + pAcaoDoSistema.getNomeUnico()
                                + "Não possui foi configurado"); //To change body of generated methods, choose Tools | Templates.

                    }
                    Method metodo = SBCore.getConfiguradorDePermissao().getMetodoByAcao(pAcaoDoSistema);
                    if (metodo == null) {

                        throw new UnsupportedOperationException("A ação de controller " + pAcaoDoSistema.getNomeUnico()
                                + "Não possui nenhum metodo vinculado, nas classes de controller do sistema"); //To change body of generated methods, choose Tools | Templates.

                    }
                    break;

            }

            switch (pAcaoDoSistema.getTipoAcaoSistema()) {
                case ACAO_DO_SISTEMA:

                    break;
                case ACAO_ENTIDADE_FORMULARIO:

                case ACAO_ENTIDADE_FORMULARIO_MODAL:

                case ACAO_ENTIDADE_CONTROLLER:

                    ItfAcaoSecundaria acaoEntidadeSecundaria = (ItfAcaoSecundaria) pAcaoDoSistema;

                    if (acaoEntidadeSecundaria.getAcaoPrincipal() == null) {
                        throw new UnsupportedOperationException("esta ação é uma ação secondária, portando deve ser configurada uma ação Principal a ela.." + pAcaoDoSistema.getNomeUnico()); //To change body of generated methods, choose Tools | Templates.
                    }

                    break;

                case ACAO_ENTIDADE_GERENCIAR:
                    ItfAcaoEntidade acaoEntidade = (ItfAcaoEntidade) pAcaoDoSistema;

                    break;

                case ACAO_CONTROLLER:

                    break;
                case ACAO_SELECAO_DE_ACAO:

                    break;
                default:
                    throw new AssertionError(pAcaoDoSistema.getTipoAcaoSistema().name());
            }
            return true;
        } else {
            System.out.println("[SBINFO]: A AÇÃO " + pAcaoDoSistema.getNomeUnico() + " ainda não foi configurada");
            if (validarAcoesNaoConfiguradas) {
                return true;
            } else {
                return false;
            }
        }
    }

}

/**
 *
 * @param pValidarAcoesNaoCOnfiguradas True para validar todas as ações, False
 * para validar apenas aquelas que foram configuradas
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
