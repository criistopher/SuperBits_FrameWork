/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
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

    public static AcaoFormularioEntidade getAcaoEntidadeFormulario(ItfAcaoDoSistema acao, ItfFabricaAcoes pAcaoPrincipal, ItfFabricaAcoes pAcaoConcluirFormulario, String pXhtml) {
        //AcaoFormularioEntidade acaoEntidadeForm = new AcaoFormularioEntidade(acao, pAcaoPrincipal.getAcaoDeEntidade().getClass(), pXhtml);
        // acaoEntidadeForm.copiarDadosDaAcao(acao);
        return null;
    }

    public static AcaoDoSistema getAcaoDoSistema(FabTipoAcaoSistemaGenerica tipoDeAcao) {

        AcaoDoSistema acao = new AcaoDoSistema();

        TipoAcaoPadrao tipoAcao = new TipoAcaoPadrao();
        acao.setTipoAcaoGenerica(tipoDeAcao);
        return acao;

    }

    public static ItfAcaoGerenciarEntidade getAcaoEntidade(ItfAcaoDoSistema pAcao, Class pClasseRelacionada, String pXhtml) {

        AcaoGestaoEntidade acaoGestaoEntidade = new AcaoGestaoEntidade((ItfFabricaAcoes) pAcao, pClasseRelacionada, pXhtml);

        return (ItfAcaoGerenciarEntidade) acaoGestaoEntidade;

    }

    public static ItfAcaoSecundaria getAcaoSecundaria(FabTipoAcaoSistemaGenerica pTipoAcao, ItfAcaoGerenciarEntidade pAcaoPrincipal, ItfFabricaAcoes pAcao) {
        try {
            if (pAcaoPrincipal == null) {
                throw new UnsupportedOperationException("Erro criando a ação secundária, A acao principal não foi setada criando:" + pAcao);
            }

            AcaoDoSistema acaoBase = getAcaoDoSistema(pTipoAcao);
            ItfAcaoDoSistema novaAcao;
            String diretorioBaseEntidade = "/site/" + pAcaoPrincipal.getClasseRelacionada().getSimpleName().toLowerCase() + "/";
            String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjeto(pAcaoPrincipal.getClasseRelacionada());
            ItfAcaoFormulario novaAcaoRefForm = new AcaoFormulario();
            ItfAcaoController novaAcaoRefController = new AcaoController();
            switch (pTipoAcao) {
                case FORMULARIO_NOVO_REGISTRO:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO);
                    novaAcaoRefForm = (ItfAcaoFormulario) novaAcao;
                    novaAcao.setIcone("fa fa-plus");
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcao.setNome("Novo " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/novoRegistro.xhtml");
                    novaAcao.setDescricao("Cria um novo " + nomeDoObjeto + " no sistema");
                    break;
                case FORMULARIO_EDITAR:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR);
                    novaAcaoRefForm = (ItfAcaoFormulario) novaAcao;
                    novaAcao.configurarPropriedadesBasicas(acaoBase);

                    novaAcao.setNome("Editar " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/editar.xhtml");
                    novaAcao.setDescricao("Editar um " + nomeDoObjeto + " do sistema");
                    novaAcao.setIcone("fa fa-edit");
                    break;
                case FORMULARIO_LISTAR:
                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR);
                    novaAcaoRefForm = (ItfAcaoFormulario) novaAcao;
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcao.setNome("Listar " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/listar.xhtml");
                    novaAcao.setDescricao("Editar um " + nomeDoObjeto + " do sistema");
                    novaAcao.setIcone("fa fa-list-alt");
                    break;

                case SALVAR_EDICAO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, FabTipoAcaoSistemaGenerica.SALVAR_EDICAO, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar edição de um " + nomeDoObjeto + " no sistema");
                    novaAcao.setIcone("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    novaAcaoRefController.setIdMetodo(UtilSBCoreReflexao.getMetodoByAcao(novaAcaoRefController));
                    break;
                case SALVAR_NOVO:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, FabTipoAcaoSistemaGenerica.SALVAR_NOVO, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto + " no sistema");
                    novaAcao.setIcone("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    novaAcaoRefController.setIdMetodo(UtilSBCoreReflexao.getMetodoByAcao(novaAcaoRefController));
                    break;
                case SALVAR_MODO_MERGE:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcao, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Salvar " + nomeDoObjeto);
                    novaAcao.setDescricao("Salvar um novo " + nomeDoObjeto + " no sistema");
                    novaAcao.setIcone("fa fa-save (alias)");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    novaAcaoRefController.setIdMetodo(UtilSBCoreReflexao.getMetodoByAcao(novaAcaoRefController));
                    break;
                case ATIVAR_DESATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcao, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Alterar status " + nomeDoObjeto);
                    novaAcao.setDescricao("Alterar status do " + nomeDoObjeto + " no sistema");
                    novaAcao.setIcone("fa fa-retweet");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    novaAcaoRefController.setIdMetodo(UtilSBCoreReflexao.getMetodoByAcao(novaAcaoRefController));
                    break;
                case ATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcao, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Ativar " + nomeDoObjeto);
                    novaAcao.setDescricao("Ativar " + nomeDoObjeto + " no sistema");
                    novaAcao.setIcone("fa fa-check");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    novaAcaoRefController.setIdMetodo(UtilSBCoreReflexao.getMetodoByAcao(novaAcaoRefController));
                    break;
                case DESATIVAR:
                    novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal, pTipoAcao, pAcao);
                    novaAcao.configurarPropriedadesBasicas(novaAcao);
                    novaAcao.setNome("Desativar " + nomeDoObjeto);
                    novaAcao.setDescricao("Desativar " + nomeDoObjeto + " no sistema");
                    novaAcao.setIcone("fa fa-close");
                    novaAcaoRefController = (ItfAcaoController) novaAcao;
                    novaAcaoRefController.setIdMetodo(UtilSBCoreReflexao.getMetodoByAcao(novaAcaoRefController));
                    break;
                case FORMULARIO_VISUALIZAR:

                    novaAcao = new AcaoFormularioEntidade(pAcaoPrincipal, pAcao, FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR);
                    novaAcaoRefForm = (ItfAcaoFormulario) novaAcao;
                    novaAcao.configurarPropriedadesBasicas(acaoBase);
                    novaAcao.setNome("Visualizar " + nomeDoObjeto);
                    novaAcaoRefForm.setXhtml(diretorioBaseEntidade + "/listar.xhtml");
                    novaAcao.setDescricao("Visualizar um " + nomeDoObjeto + " do sistema");
                    novaAcao.setIcone("fa fa-eye");
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

}
