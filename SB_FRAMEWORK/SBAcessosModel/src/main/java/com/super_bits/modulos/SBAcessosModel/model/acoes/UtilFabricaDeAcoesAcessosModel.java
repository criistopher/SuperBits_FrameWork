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
import com.super_bits.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.ModuloAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import java.lang.reflect.Field;
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
        try {
            ItfFabricaModulo fabModulo = (ItfFabricaModulo) UtilSBCoreReflexao.getFabricaDaClasseByAnotacao(pAcao.getClass(), "modulo", true);

            ModuloAcaoSistema moduloDaAcao = (ModuloAcaoSistema) fabModulo.getModulo();

            if (moduloDaAcao == null) {
                throw new UnsupportedOperationException("A Fabrica de ações não foi anodada com InfoModulo" + pAcao.getClass().getSimpleName());
            }

            moduloDaAcao.setId(pAcao.getClass().getSimpleName().hashCode());

            return moduloDaAcao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Obtendo modulo da Ação pela anotação infomodulo, você anotou o nome do modulo na fabrica de ações?", t);
            SBCore.RelatarErro(FabErro.PARA_TUDO, "Erro Obtendo modulo da Ação pela anotação infomodulo, você anotou o nome do modulo na fabrica de ações?", t);
        }
        return null;
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
     * Retorna o tipo de ação generica de acordo com a nomeclatura <br>
     * Ex: <br>
     * MB Ação managed Bean FRMAÇÃO DE FORMULARIO <br>
     * FRM_NOVO <br>
     * FRM_EDITAR <br>
     * FRM_VISUALIZAR <br>
     * CTR AÇÃO CAMADA CONTROLLER <br>
     * CTR_ALTERAR_STATUS <br>
     * CTR_SALVAR_MERGE  <br>
     * CTR_REMOVER <br>
     *
     * @param pFabrica Fabrica de ação referencia
     * @return o Tipo de ação generica de acordo com a fabrica referenia enviada
     */
    public static FabTipoAcaoSistemaGenerica getTipoAcaoByNome(ItfFabricaAcoes pFabrica) {

        String nome = pFabrica.toString();

        String[] divisoes = nome.split("_");

        List<String> lista = Arrays.asList(divisoes);

        if (lista.contains("FRM")) {

            if (lista.contains("FRM") && lista.contains("NOVO")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;
            }
            if (lista.contains("FRM") && lista.contains("EDITAR")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR;
            }
            if (lista.contains("FRM") && lista.contains("VISUALIZAR")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR;
            }

            if (lista.contains("FRM") && lista.contains("LISTAR")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR;
            }
            if (lista.contains("FRM") && lista.contains("MODAL")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_MODAL;
            }

            if (lista.contains("FRM") && lista.contains("MODAL")) {
                return FabTipoAcaoSistemaGenerica.FORMULARIO_MODAL;
            }

            if (lista.contains("FRM") && lista.contains("SELECAO") && lista.contains("ACAO")) {
                return FabTipoAcaoSistemaGenerica.SELECAO_DE_ACAO;
            }

            return FabTipoAcaoSistemaGenerica.FORMULARIO_PERSONALIZADO;
        }
        if (lista.contains("CTR")) {
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
            return FabTipoAcaoSistemaGenerica.CONTROLLER_PERSONALIZADO;

        }
        if (lista.contains("MB")) {
            return FabTipoAcaoSistemaGenerica.GERENCIAR_DOMINIO;
        }

        throw new UnsupportedOperationException("Não foi possível encontar o Tipo da ação " + pFabrica.toString() + " certifique que está usando a nomeclatura estabelecida CTR para controller FRM, para formulario, e MB para gestão de ações");

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
                        if (acao.toString().contains("_MB")) {
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

    private static void configurarAnotacoesAcao(AcaoDoSistema pAcao) throws NoSuchFieldException {

        Field campo = pAcao.getEnumAcaoDoSistema().getClass().getField(pAcao.getEnumAcaoDoSistema().toString());

        FabTipoAcaoSistemaGenerica tipoAcao = getTipoAcaoByNome(pAcao.getEnumAcaoDoSistema());
        Class entidadeDaAcao = pAcao.getEnumAcaoDoSistema().getEntidadeDominio();
        if (tipoAcao.toString().contains("FORMULARIO")) {
            InfoTipoAcaoFormulario anotacaoFormulario = campo.getAnnotation(InfoTipoAcaoFormulario.class);
            if (anotacaoFormulario != null) {

                for (String cp : anotacaoFormulario.campos()) {
                    try {

                        CaminhoCampoReflexao caminhoCampo = UtilSBCoreReflexaoCampos.getCaminhoByStringRelativaEClasse(cp, pAcao.getEnumAcaoDoSistema().getEntidadeDominio());
                        if (caminhoCampo == null) {
                            throw new UnsupportedOperationException("Erro Configurando campos da ação a partir de anotações ,verifique os campos  anotados em: " + pAcao.getNomeUnico());
                        }
                        ((ItfAcaoFormularioEntidade) pAcao).getCampos().add(caminhoCampo);

                    } catch (Throwable t) {
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando campo: " + cp + " da anotação " + pAcao.getNomeUnico(), t);
                    }
                }
                if (anotacaoFormulario.codigoJira().length() > 2) {
                    pAcao.setIdDescritivoJira(anotacaoFormulario.codigoJira());
                }
                if (anotacaoFormulario.icone().length() > 2) {
                    pAcao.setIconeAcao(anotacaoFormulario.icone());
                }
                if (anotacaoFormulario.nomeAcao().length() > 2) {
                    pAcao.setNomeAcao(anotacaoFormulario.nomeAcao());
                }
                if (anotacaoFormulario.xhtmlDaAcao().length() > 2) {
                    ((ItfAcaoFormulario) pAcao).setXhtml(anotacaoFormulario.xhtmlDaAcao());
                }
                pAcao.setPrecisaPermissao(anotacaoFormulario.precisaPermissao());
            } else if (campo.getDeclaredAnnotations().length > 0) {
                throw new UnsupportedOperationException("Erro a anotação encontrada em " + pAcao.getNomeUnico() + " Não parece ser a ação adequada, verifique o tipo de ação da anotação");
            }

        }
        //alcyrnascimento@hotmail.com
        //dep.acarantes@elmg.gov.br

        if (tipoAcao.toString().contains("CONTROLLER")) {

            InfoTipoAcaoController anotacaocontroller = campo.getAnnotation(InfoTipoAcaoController.class);
            if (anotacaocontroller != null) {
                if (anotacaocontroller.icone().length() > 2) {
                    pAcao.setIconeAcao(anotacaocontroller.icone());
                }
                if (anotacaocontroller.nomeAcao().length() > 2) {
                    pAcao.setNomeAcao(anotacaocontroller.nomeAcao());
                }
                if (anotacaocontroller.xhtmlDaAcao().length() > 2) {
                    ((ItfAcaoFormulario) pAcao).setXhtml(anotacaocontroller.xhtmlDaAcao());
                }
                if (anotacaocontroller.descricao().length() > 2) {
                    pAcao.setDescricao(anotacaocontroller.descricao());
                }
                if (anotacaocontroller.codigoJira().length() > 2) {
                    pAcao.setIdDescritivoJira(anotacaocontroller.codigoJira());
                }
                pAcao.setPrecisaPermissao(anotacaocontroller.precisaPermissao());

            } else if (campo.getDeclaredAnnotations().length > 0) {
                throw new UnsupportedOperationException("Erro a anotação encontrada em " + pAcao.getNomeUnico() + " Não parece ser a ação adequada, verifique o tipo de ação da anotação");
            }

        }
        if (tipoAcao.toString().contains("GERENCIAR")) {

            InfoTipoAcaoGestaoEntidade anotacaoGerenciar = campo.getAnnotation(InfoTipoAcaoGestaoEntidade.class);
            if (anotacaoGerenciar != null) {
                if (anotacaoGerenciar.icone().length() > 2) {
                    pAcao.setIconeAcao(anotacaoGerenciar.icone());
                }
                if (anotacaoGerenciar.nomeAcao().length() > 2) {
                    pAcao.setNomeAcao(anotacaoGerenciar.nomeAcao());
                }
                if (anotacaoGerenciar.xhtmlDaAcao().length() > 2) {
                    ((ItfAcaoFormulario) pAcao).setXhtml(anotacaoGerenciar.xhtmlDaAcao());
                }
                if (anotacaoGerenciar.descricao().length() > 2) {
                    pAcao.setDescricao(anotacaoGerenciar.descricao());
                }

                if (anotacaoGerenciar.codigoJira().length() > 2) {
                    pAcao.setIdDescritivoJira(anotacaoGerenciar.codigoJira());
                }

                pAcao.setPrecisaPermissao(anotacaoGerenciar.precisaPermissao());

            } else if (campo.getDeclaredAnnotations().length > 0) {
                throw new UnsupportedOperationException("Erro a anotação encontrada em " + pAcao.getNomeUnico() + " Não parece ser a ação adequada, verifique o tipo de ação da anotação");
            }

        }

    }

    /**
     *
     * @param pAcao Cria uma nova ação do sistema de forma automática por
     * reflexão
     * @return
     */
    public static ItfAcaoDoSistema getNovaAcao(
            ItfFabricaAcoes pAcao) {

        // Verificando se a ação já foi criada
        if (MapaAcoesSistema.isMapaCriado()) {
            AcaoDoSistema acao = (AcaoDoSistema) MapaAcoesSistema.getAcaoDoSistema(pAcao);
            if (acao == null) {
                throw new UnsupportedOperationException("O Mapa de ações foi criado, mas a ação procurada não foi encontra, certifique que a ação chamada esteja no core., a ação foi: " + pAcao.getClass().getSimpleName() + "." + pAcao);
            }

            return acao;
        }

        FabTipoAcaoSistemaGenerica pTipoAcaoGenerica = getTipoAcaoByNome(pAcao);

        ItfFabricaAcoes fabricaDadoPrincipal = getAcaoPrincipalDoDominio(pAcao);
        ItfAcaoGerenciarEntidade pAcaoPrincipal = null;
        try {
            if (fabricaDadoPrincipal != null) {

                pAcaoPrincipal = fabricaDadoPrincipal.getAcaoDoSistema().getComoGestaoEntidade();
            }

            if (pAcaoPrincipal == null) {
                switch (pTipoAcaoGenerica) {
                    case FORMULARIO_NOVO_REGISTRO:
                    case FORMULARIO_EDITAR:
                    case FORMULARIO_PERSONALIZADO:
                    case FORMULARIO_VISUALIZAR:
                    case FORMULARIO_LISTAR:
                    case FORMULARIO_MODAL:
                    case CONTROLLER_PERSONALIZADO:

                        throw new UnsupportedOperationException("A ação " + pAcao + " deveria ter uma ação principal vinculada, por ser do tipo " + pTipoAcaoGenerica);

                }

            }

            AcaoDoSistema acaoBase = criaAcaodoSistemaPorTipoAcao(pTipoAcaoGenerica);
            ItfAcaoDoSistema novaAcao = null;
            String diretorioBaseEntidade = "/site/" + pAcao.getNomeModulo().toLowerCase() + "/" + pAcao.getEntidadeDominio().getSimpleName().toLowerCase();
            String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjetoPorAnotacaoInfoClasse(pAcao.getEntidadeDominio());
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
                    novaAcao.setIconeAcao("fa fa-save");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;

                    break;
                case CONTROLLER_SALVAR_MODO_MERGE:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcaoGenerica, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto + " no sistema");
                    novaAcao.setIconeAcao("fa fa-save");
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
                    novaAcao = new AcaoGestaoEntidade(pAcao, pAcao.getEntidadeDominio(), diretorioBaseEntidade + "/gerenciar.xhtml");
                    AcaoGestaoEntidade novaAcaoGestao = (AcaoGestaoEntidade) novaAcao;
                    novaAcaoGestao.setEnumAcoesVinculadas(getSubAcoesDaAcaoPrincipal(pAcao));
                    break;
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
                throw new UnsupportedOperationException("Não foi possível determinar um constructor para a acao " + pAcao.toString() + " verifique a nomeclatura de acordo com a documentação e tente novamente");
            }
            configurarAnotacoesAcao((AcaoDoSistema) novaAcao);
            return novaAcao;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro instanciando ação automática por nome:" + t.getMessage(), t);
        }
        throw new UnsupportedOperationException("Erro Criando ação do sistema >>" + pAcao);
    }

    public static AcaoDoSistema criaAcaodoSistemaPorTipoAcao(FabTipoAcaoSistemaGenerica tipoDeAcao) {

        AcaoDoSistema acao = new AcaoDoSistema();

        TipoAcaoPadrao tipoAcao = new TipoAcaoPadrao();
        acao.setTipoAcaoGenerica(tipoDeAcao);
        return acao;

    }

}
