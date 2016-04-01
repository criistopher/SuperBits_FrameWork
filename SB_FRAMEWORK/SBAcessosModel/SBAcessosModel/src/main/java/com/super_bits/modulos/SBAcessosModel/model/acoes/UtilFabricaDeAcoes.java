/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoController;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.TipoAcaoPadrao;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoEntidadeAlterarStatus;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeEditar;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeListar;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidadeNovoRegistro;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.SBCore.InfoCampos.UtilSBCoreReflexaoCampos;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilFabricaDeAcoes {

    public static AcaoFormularioEntidade getAcaoEntidadeFormulario(ItfAcaoDoSistema acao, ItfFabricaAcoes pAcaoPrincipal, ItfFabricaAcoes pAcaoConcluirFormulario, String pXhtml) {
        AcaoFormularioEntidade acaoEntidadeForm = new AcaoFormularioEntidade(acao, pAcaoPrincipal.getAcaoDeEntidade().getClass(), pXhtml);
        acaoEntidadeForm.copiarDadosDaAcao(acao);
        return acaoEntidadeForm;
    }

    public static AcaoDoSistema getAcaoDoSistema(FabTipoAcaoSistemaGenerica tipoDeAcao) {

        AcaoDoSistema acao = new AcaoDoSistema();

        TipoAcaoPadrao tipoAcao = new TipoAcaoPadrao();
        acao.setTipoAcaoGenerica(tipoDeAcao);
        return acao;

    }

    public static ItfAcaoGerenciarEntidade getAcaoEntidade(ItfAcaoDoSistema pAcao, Class pClasseRelacionada, String pXhtml) {

        AcaoGestaoEntidade acaoGestaoEntidade = new AcaoGestaoEntidade(pAcao, pClasseRelacionada, pXhtml);

        return (ItfAcaoGerenciarEntidade) acaoGestaoEntidade;

    }

    public static ItfAcaoSecundaria getAcaoSecundaria(FabTipoAcaoSistemaGenerica pTipoAcao, ItfAcaoGerenciarEntidade pAcaoPrincipal) {
        AcaoDoSistema acaoBase = getAcaoDoSistema(pTipoAcao);
        ItfAcaoDoSistema novaAcao;
        String diretorioBaseEntidade = "/site/" + pAcaoPrincipal.getClasseRelacionada().getSimpleName() + "/";
        String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjeto(pAcaoPrincipal.getClasseRelacionada());
        switch (pTipoAcao) {
            case FORMULARIO_NOVO_REGISTRO:
                novaAcao = new AcaoFormularioEntidadeNovoRegistro(pAcaoPrincipal, diretorioBaseEntidade + "/novoRegistro.xhtml");

                /// configura o icone da ação
                novaAcao.configurarPropriedadesBasicas(acaoBase);
                novaAcao.setNome("Novo " + nomeDoObjeto);
                novaAcao.setDescricao("Cria um novo " + nomeDoObjeto + " no sistema");

                break;
            case FORMULARIO_EDITAR:
                AcaoFormularioEntidadeEditar;

                break;
            case FORMULARIO_LISTAR:
                AcaoFormularioEntidadeListar
                break;
            case FORMULARIO_MODAL:
                AcaoFormularioModalDeEntidade

                break;

            case SALVAR_EDICAO:
                novaAcao = new AcaoDeEntidadeController(pAcaoPrincipal);
                ItfAcaoController novaAcaoRefController = (ItfAcaoController) novaAcao;
                novaAcaoRefController.setIdMetodo(UtilSBCoreReflexao.getMetodoByAcao(novaAcaoRefController));

                break;
            case SALVAR_NOVO:
                AcaoDeEntidadeController

                break;
            case SALVAR_MODO_MERGE:
                AcaoDeEntidadeController
                break;
            case ATIVAR_DESATIVAR:
                AcaoDeEntidadeController
                break;
            case ATIVAR:
                AcaoDeEntidadeController
                break;
            case DESATIVAR:
                AcaoDeEntidadeController
                break;
            case FORMULARIO_VISUALIZAR:
                AcaoFormularioEntidade
                break;

            default:
                throw new AssertionError("Este tipo de ação não parece ser uma ação secundária" + pTipoAcao.name());

        }

        return novaAcao;

    }

}
