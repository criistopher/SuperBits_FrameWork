/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
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

}
