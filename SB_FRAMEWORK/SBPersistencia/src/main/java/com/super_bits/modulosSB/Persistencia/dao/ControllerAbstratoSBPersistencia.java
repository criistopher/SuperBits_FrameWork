/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.dao;

import com.super_bits.Controller.ControllerAppAbstratoSBCore;
import com.super_bits.Controller.Interfaces.ItfResposta;
import com.super_bits.Controller.UtilSBController;
import com.super_bits.Controller.comunicacao.Resposta;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanNormal;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import javax.persistence.EntityManager;

/**
 *
 *
 * Classe para camada controller que utliza controle de usuário através de
 * persistencia com Hibernate
 *
 *
 * @author Salvio Fubino
 */
public abstract class ControllerAbstratoSBPersistencia extends ControllerAppAbstratoSBCore {

    /**
     *
     * Retorna a resposta verificando as permissões e adicionando mensagens de
     * erro para parametros não enviados (AINDA NÂO IMPLEMENTADO)
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * @param pEntidade
     * @return
     */
    protected static ItfResposta getNovaRespostaAutorizaChecaNulo(ItfBeanSimples pEntidade) {

        Resposta resp = new Resposta(null, UtilSBController.getAcaoByMetodo(getMetodoChamado(), true));

        if (pEntidade == null) {
            return resp.addErro("Erro, detectado, tentativa de cadastrar um registo nulo");
        }
        resp.setTipoRetorno(pEntidade.getClass());
        addMensagemDeAutorizacao(resp);

        return resp;
    }

    protected static void persistirTodasEntidadesVinculadas(ItfResposta pResp, ItfBeanSimples pEntidade, EntityManager pEM) {
        persistirTodasEntidadesVinculadas(pResp, pEntidade, pEM, 3);
    }

    protected static void persistirTodasEntidadesVinculadas(ItfResposta pResp, ItfBeanSimples pEntidade, EntityManager pEM, int quantidadeDemanda) {

        //if (true) {
        //   pResp.addErro("Este metodo está em manutenção!!! ");
        //  return;
        // }
        try {
            if (pEntidade == null) {
                pResp.addErro("Ocorreu um erro ao salvar as informações");
                throw new UnsupportedOperationException("Enviou um objeto nulo para persistir todas entidades vinculadas");
            }
            if (pResp == null) {
                throw new UnsupportedOperationException("Você precisa especificar o objeto de resposta ao persistir todas entidades vinculadas, para que a função possa adicionar a mensagem de erro");
            }
            if (pEM == null) {
                throw new UnsupportedOperationException("é preciso enviar um Entity Manager para peristir todas entidades vinculadas");
            }

            UtilSBPersistencia.iniciarTransacao(pEM);

            for (CaminhoCampoReflexao cm : pEntidade.getEntidadesVinculadas()) {

                if (pEntidade.getItemPorCaminhoCampo(cm) != null) {

                    if (cm.isUmCampoListavel()) {

                    } else {

                    }

                    ItfBeanSimples entidade = pEntidade.getItemPorCaminhoCampo(cm);

                    if (UtilSBPersistencia.mergeRegistro(pEntidade.getItemPorCaminhoCampo(cm), pEM) == null) {
                        pResp.addErro("Ocorreu um erro ao Atualizar as informações de " + cm.getCampoFieldReflection().getType().getSimpleName());
                    }

                }

            }

            if (UtilSBPersistencia.mergeRegistro(pEntidade, pEM) == null) {
                pResp.addErro("Não foi possível salvar o registro " + pEntidade.getClass());
            }

            if (!UtilSBPersistencia.finalizarTransacao(pEM)) {
                pResp.addErro("Não foi possível salvar as alteções! " + pEntidade.getClass());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ocorreu um erro ao Peristir todas entidades vinculadas", t);
        }

    }

    protected static void alterarStatus(ItfResposta pResposta, ItfBeanNormal pEntidade, EntityManager pem) {
        try {
            UtilSBPersistencia.iniciarTransacao(pem);
            String nomeAcao = "Ativar";
            if (pEntidade.isAtivo()) {
                pEntidade.setAtivo(false);
                nomeAcao = "Desativar";
            } else {
                pEntidade.setAtivo(true);
                nomeAcao = "Ativar";
            }

            if (UtilSBPersistencia.mergeRegistro(pEntidade) != null) {
                pResposta.addErro("Não foi possível " + nomeAcao + " o registro" + pEntidade.getClass().getSimpleName());
            }

            if (!UtilSBPersistencia.finalizarTransacao(pem)) {
                pResposta.addErro("Não foi possível " + nomeAcao + " o registro" + pEntidade.getClass().getSimpleName());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ocorreu um erro inesperado ao tentar alterar O Status", t);
        }
    }

}
