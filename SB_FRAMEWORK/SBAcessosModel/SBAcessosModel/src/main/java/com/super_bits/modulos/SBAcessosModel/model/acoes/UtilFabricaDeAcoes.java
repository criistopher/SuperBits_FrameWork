/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilFabricaDeAcoes {

    /**
     *
     * Retorna o tipo de ação generica de acordo com a nomeclatura * MB Ação
     * managed Bean >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> FRM
     * AÇÃO DE FORMULARIO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * FRM_NOVO
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * FRM_EDITAR
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * FRM_VISUALIZAR
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CTR AÇÃO
     * CAMADA CONTROLLER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     * CTR_ALTERAR_STATUS
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CTR_SALVAR_MERGE
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     *
     * @param pFabrica
     * @return
     */
    public static FabTipoAcaoSistemaGenerica getTipoAcaoByNome(ItfFabricaAcoes pFabrica) {

        String nome = pFabrica.toString();

        String[] divisoes = nome.split("_");
        String teste = divisoes[0];

        for (String parte : divisoes) {
            return FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;

        }
        throw new UnsupportedOperationException("Não foi possivel determinar o tipo de ação para " + pFabrica.toString() + "Verifique a nomeclatura de acordo com as instruções ");

    }

    public static AcaoGestaoEntidade getAcaoPrincipalDoDominio(ItfFabricaAcoes pAcao) {

        Class classeDominioDaAcao = pAcao.getDominio();
        for (ItfFabricaAcoes acao : pAcao.getClass().getEnumConstants()) {
            // verifica se a classe de dominio é a mesma da ação enviada
            if (acao.getDominio().equals(classeDominioDaAcao.getClass())) {
                // verifica se alem de ser o mesmo dominio possui o MB
                if (acao.toString().contains("_MB_")) {
                    return (AcaoGestaoEntidade) acao.geAcaoGerenciarEntidade();
                }
            }
        }
        return null;

    }

    public static ItfAcaoDoSistema getNovaAcao(
            ItfFabricaAcoes pAcao) {

        FabTipoAcaoSistemaGenerica pTipoAcao = getTipoAcaoByNome(pAcao);
        ItfAcaoGerenciarEntidade pAcaoPrincipal = getAcaoPrincipalDoDominio(pAcao);
        try {
            if (pAcaoPrincipal == null) {
                throw new UnsupportedOperationException("Erro criando a ação secundária, A acao principal não foi setada criando:" + pAcao);
            }

            AcaoDoSistema acaoBase = criaAcaodoSistemaPorTipoAcao(pTipoAcao);
            ItfAcaoDoSistema novaAcao = null;
            String diretorioBaseEntidade = "/site/" + pAcaoPrincipal.getClasseRelacionada().getSimpleName().toLowerCase() + "/";
            String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjeto(pAcaoPrincipal.getClasseRelacionada());
            ItfAcaoFormularioEntidade novaAcaoRefForm = null;
            ItfAcaoController novaAcaoRefController = null;
            switch (pTipoAcao) {
                case FORMULARIO_NOVO_REGISTRO:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    novaAcaoRefForm.setAcaoPrincipal(pAcaoPrincipal);
                    novaAcaoRefForm.setIconeAcao("fa fa-plus");
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcao.setNome("Novo " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/novoRegistro.xhtml");
                    novaAcao.setDescricao("Cria um novo " + nomeDoObjeto + " no sistema");
                    break;
                case FORMULARIO_EDITAR:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcaoRefForm.setAcaoPrincipal(pAcaoPrincipal);
                    novaAcao.setNome("Editar " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/editar.xhtml");
                    novaAcao.setDescricao("Editar um " + nomeDoObjeto + " do sistema");
                    novaAcao.setIconeAcao("fa fa-edit");
                    break;
                case FORMULARIO_LISTAR:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcaoRefForm.setAcaoPrincipal(pAcaoPrincipal);
                    novaAcao.setNome("Listar " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/listar.xhtml");
                    novaAcao.setDescricao("Editar um " + nomeDoObjeto + " do sistema");
                    novaAcao.setIconeAcao("fa fa-list-alt");
                    break;

                case SALVAR_EDICAO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);

                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar edição de um " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case SALVAR_NOVO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);

                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case SALVAR_MODO_MERGE:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case ATIVAR_DESATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Alterar status " + nomeDoObjeto);
                    novaAcao.setDescricao("Alterar status do " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-retweet");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case ATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Ativar " + nomeDoObjeto);
                    novaAcao.setDescricao("Ativar " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-check");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case DESATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Desativar " + nomeDoObjeto);
                    novaAcao.setDescricao("Desativar " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-close");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case FORMULARIO_VISUALIZAR:

                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcao.setNome("Visualizar " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/listar.xhtml");
                    novaAcao.setDescricao("Visualizar um " + nomeDoObjeto + " do sistema");
                    novaAcao.setIconeAcao("fa fa-eye");
                    break;
                case FORMULARIO_PERSONALIZADO:
                    break;
                case SELECAO_DE_ACAO:
                    break;
                case FORMULARIO_MODAL:
                    break;
                case GERENCIAR:
                    novaAcao = new AcaoGestaoEntidade(pAcao, pAcao.getDominio(), diretorioBaseEntidade + "/gerenciar.xhtml");
                    break;

                default:
                    throw new AssertionError("Este tipo de ação não parece ser uma ação secundária" + pTipoAcao.name());

            }

            return (ItfAcaoSecundaria) novaAcao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando ação secontaria:" + t.getMessage(), t);
        }
        return null;
    }

    public static AcaoDoSistema criaAcaodoSistemaPorTipoAcao(FabTipoAcaoSistemaGenerica tipoDeAcao) {

        AcaoDoSistema acao = new AcaoDoSistema();

        TipoAcaoPadrao tipoAcao = new TipoAcaoPadrao();
        acao.setTipoAcaoGenerica(tipoDeAcao);
        return acao;

    }

}
