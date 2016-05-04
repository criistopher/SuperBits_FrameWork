/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.modulo.ItfFabricaModulo;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilFabricaDeAcoesAcessosModel {

    public static void configFormulario(ItfAcaoDoSistema acao, String formulario) {
        try {
            ItfAcaoFormulario acaoformulario = (ItfAcaoFormulario) acao;
            acaoformulario.setXhtml(formulario);
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro setando formulario para ação generica" + acao, t);
        }

    }

    /**
     *
     * Cria o modulo da ação apartir da anotação Info Modulo
     *
     * @param pAcao
     * @return
     */
    public static ModuloAcaoSistema getModuloByFabrica(ItfFabricaAcoes pAcao) {

        ItfFabricaModulo fabModulo = (ItfFabricaModulo) UtilSBCoreReflexao.getFabricaDaClasseByAnotacao(pAcao.getClass(), "modulo", true);

        ModuloAcaoSistema moduloDaAcao = (ModuloAcaoSistema) fabModulo.getModulo();

        if (moduloDaAcao == null) {
            throw new UnsupportedOperationException("A Fabrica de ações não foi anodada com InfoModulo" + pAcao.getClass().getSimpleName());
        }

        moduloDaAcao.setId(pAcao.getClass().getSimpleName().hashCode());

        return moduloDaAcao;

    }

    public static String getNomeDominio(ItfFabricaAcoes pAcao) {

        String nomeAcao = pAcao.toString();
        String dominio = nomeAcao.split("_")[0];
        return dominio;
    }

    public static List<ItfFabricaAcoes> getSubAcoesDaAcaoPrincipal(ItfFabricaAcoes pAcaoPrincipal) {
        List<ItfFabricaAcoes> acoes = new ArrayList<>();
        String nomeDominio = getNomeDominio(pAcaoPrincipal);
        for (ItfFabricaAcoes acao : pAcaoPrincipal.getClass().getEnumConstants()) {

            // Verificando se a ação inicia igual
            if (nomeDominio.equals(getNomeDominio(acao))) {
                //verificando se os dois possuem a mesma classe
                if (acao.getEntidadeDominio().equals(pAcaoPrincipal.getEntidadeDominio())) {
                    if (acao != pAcaoPrincipal) {
                        acoes.add(acao);
                    }
                }
            }
        }
        return acoes;

    }

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
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CTR_REMOVER
     *
     * @param pFabrica
     * @return
     */
    public static FabTipoAcaoSistemaGenerica getTipoAcaoByNome(ItfFabricaAcoes pFabrica) {

        String nome = pFabrica.toString();

        String[] divisoes = nome.split("_");

        List<String> lista = Arrays.asList(divisoes);

        for (String parte : divisoes) {

            if (lista.contains("FRM") && lista.contains("NOVO")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;
            }

            if (lista.contains("FRM") && lista.contains("EDITAR")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR;
            }

            if (lista.contains("FRM") && lista.contains("VISUALIZAR")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR;
            }

            if (lista.contains("CTR") && lista.contains("ALTERAR") && lista.contains("STATUS")) {
                return FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR_DESATIVAR;
            }

            if (lista.contains("CTR") && lista.contains("SALVAR") && lista.contains("MERGE")) {
                return FabTipoAcaoSistemaGenerica.CONTROLLER_SALVAR_MODO_MERGE;
            }

            if (lista.contains("CTR") && lista.contains("ATIVAR")) {
                return FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR;
            }

            if (lista.contains("CTR") && lista.contains("REMOVER")) {
                return FabTipoAcaoSistemaGenerica.CONTROLLER_REMOVER;
            }

            if (lista.contains("CTR") && lista.contains("DESATIVAR")) {
                return FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR;
            }

            if (lista.contains("CTR") && lista.contains("SALVAR") && lista.contains("NOVO")) {
                return FabTipoAcaoSistemaGenerica.CONTROLLER_SALVAR_NOVO;
            }

            if (lista.contains("CTR") && lista.contains("SALVAR") && lista.contains("EDICAO")) {
                return FabTipoAcaoSistemaGenerica.CONTROLLER_SALVAR_EDICAO;
            }

            if (lista.contains("CTR")) {
                return FabTipoAcaoSistemaGenerica.CONTROLLER_PERSONALIZADO;
            }

            if (lista.contains("FRM") && lista.contains("MODAL")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_MODAL;
            }

            if (lista.contains("FRM") && lista.contains("LISTAR")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR;
            }
            if (lista.contains("FRM") && lista.contains("MODAL")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_MODAL;
            }

            if (lista.contains("FRM") && lista.contains("SELECAO") && lista.contains("ACAO")) {
                return FabTipoAcaoSistemaGenerica.SELECAO_DE_ACAO;
            }

            if (lista.contains("FRM")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_PERSONALIZADO;
            }

            if (lista.contains("MB")) {
                return FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO;
            }

        }
        throw new UnsupportedOperationException("Não foi possivel determinar o tipo de ação para " + pFabrica.toString() + "Verifique a nomeclatura de acordo com as instruções ");

    }

    public static ItfFabricaAcoes getAcaoPrincipalDoDominio(ItfFabricaAcoes pAcao) {
        try {
            Class classeDominioDaAcao = pAcao.getEntidadeDominio();
            for (ItfFabricaAcoes acao : pAcao.getClass().getEnumConstants()) {
                // verifica se a classe de dominio é a mesma da ação enviada
                Class dominioDaAcaoAtual = acao.getEntidadeDominio();
                if (dominioDaAcaoAtual != null) {
                    if (dominioDaAcaoAtual.getName().equals(classeDominioDaAcao.getName())) {
                        // verifica se alem de ser o mesmo dominio possui o MB
                        if (acao.toString().contains("_MB_")) {
                            if (acao.equals(pAcao)) {
                                return null;
                            }
                            return acao;
                        }
                    }
                }
            }
            return null;

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro determinando a ação principal da ação" + pAcao, t);

        }
        return null;

    }

    public static ItfAcaoDoSistema getNovaAcao(
            ItfFabricaAcoes pAcao) {

        FabTipoAcaoSistemaGenerica pTipoAcaoGenerica = getTipoAcaoByNome(pAcao);

        ItfFabricaAcoes fabricaDadoPrincipal = getAcaoPrincipalDoDominio(pAcao);
        ItfAcaoGerenciarEntidade pAcaoPrincipal = null;
        try {
            if (fabricaDadoPrincipal != null) {

                pAcaoPrincipal = fabricaDadoPrincipal.geAcaoGerenciarEntidade();
            }

            if (pAcaoPrincipal == null) {
                switch (pTipoAcaoGenerica) {
                    case FORMULARIO_NOVO_REGISTRO:
                    case FORMULARIO_EDITAR:
                    case FORMULARIO_PERSONALIZADO:
                    case FORMULARIO_VISUALIZAR:
                    case FORMULARIO_LISTAR:
                    case FORMULARIO_MODAL:
                        throw new UnsupportedOperationException("A ação " + pAcao + " deveria ter uma ação principal vinculada, por ser do tipo " + pTipoAcaoGenerica);

                }

            }

            AcaoDoSistema acaoBase = criaAcaodoSistemaPorTipoAcao(pTipoAcaoGenerica);
            ItfAcaoDoSistema novaAcao = null;
            String diretorioBaseEntidade = "/site/" + pAcao.getNomeModulo().toLowerCase() + "/" + pAcao.getEntidadeDominio().getSimpleName().toLowerCase();
            String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjeto(pAcao.getEntidadeDominio());
            ItfAcaoFormularioEntidade novaAcaoRefForm = null;
            ItfAcaoController novaAcaoRefController = null;
            AcaoGestaoEntidade acaoPrincipal = null;

            switch (pTipoAcaoGenerica) {
                case FORMULARIO_NOVO_REGISTRO:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, pTipoAcaoGenerica);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    acaoPrincipal = (AcaoGestaoEntidade) novaAcaoRefForm.getAcaoPrincipal();
                    novaAcaoRefForm.setAcaoPrincipal(pAcaoPrincipal);
                    novaAcaoRefForm.setIconeAcao("fa fa-plus");
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcao.setNome("Novo " + nomeDoObjeto);
                    if (acaoPrincipal.isUtilizarMesmoFormEditarInserir()) {
                        novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/editar.xhtml");
                    } else {

                        novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/novoRegistro.xhtml");
                    }
                    novaAcao.setDescricao("Cria um novo " + nomeDoObjeto + " no sistema");

                    break;
                case FORMULARIO_EDITAR:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, pTipoAcaoGenerica);
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

                case CONTROLLER_SALVAR_EDICAO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);

                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar edição de um " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case CONTROLLER_SALVAR_NOVO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);

                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case CONTROLLER_SALVAR_MODO_MERGE:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case CONTROLLER_ATIVAR_DESATIVAR:

                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);

                    novaAcao.configurarPropriedadesBasicas(novaAcao);

                    novaAcao.setNome("Alterar status " + nomeDoObjeto);
                    novaAcao.setDescricao("Alterar status do " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-retweet");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case CONTROLLER_ATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Ativar " + nomeDoObjeto);
                    novaAcao.setDescricao("Ativar " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-check");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case CONTROLLER_DESATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Desativar " + nomeDoObjeto);
                    novaAcao.setDescricao("Desativar " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-close");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case FORMULARIO_VISUALIZAR:

                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR);
                    novaAcaoRefForm = (ItfAcaoFormularioEntidade) novaAcao;
                    acaoPrincipal = (AcaoGestaoEntidade) novaAcaoRefForm.getAcaoPrincipal();
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcao.setNome("Visualizar " + nomeDoObjeto);
                    if (acaoPrincipal.isUtilizarMesmoFormEditarInserir()) {
                        novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/editar.xhtml");
                    } else {

                        novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/visualizar.xhtml");
                    }
                    novaAcao.setDescricao("Visualizar um " + nomeDoObjeto + " do sistema");
                    novaAcao.setIconeAcao("fa fa-eye");
                    break;
                case FORMULARIO_PERSONALIZADO:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, pTipoAcaoGenerica);
                    break;
                case SELECAO_DE_ACAO:
                    break;
                case FORMULARIO_MODAL:
                    break;
                case GERENCIAR_DOMINIO:
                    AcaoGestaoEntidade novaAcaoGestao = new AcaoGestaoEntidade(pAcao, pAcao.getEntidadeDominio(), diretorioBaseEntidade + "/gerenciar.xhtml");
                    novaAcaoGestao.setEnumAcoesVinculadas(getSubAcoesDaAcaoPrincipal(pAcao));
                    return novaAcaoGestao;
                case CONTROLLER_REMOVER:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    break;
                case CONTROLLER_PERSONALIZADO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    break;

                default:
                    throw new AssertionError("Este tipo de ação não parece ser uma ação secundária" + pTipoAcaoGenerica.name());

            }
            if (novaAcao == null) {
                throw new UnsupportedOperationException("Não foi possível determinar um constructor para a acao" + pAcao + " verifique a nomeclatura de acordo com a documentação e tente novamente");
            }

            return novaAcao;
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
