/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.acoes.acaoDeEntidade.AcaoFormularioDeEntidade;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public abstract class UtilFabricaDeAcoes {

    public static AcaoFormularioDeEntidade getAcaoEntidadeFormulario(ItfAcaoDoSistema acao, ItfFabricaAcoes pAcaoPrincipal, ItfFabricaAcoes pAcaoConcluirFormulario, String pXhtml) {
        AcaoFormularioDeEntidade acaoEntidadeForm = new AcaoFormularioDeEntidade(acao, pAcaoPrincipal.getAcaoDeEntidade().getClass(), pXhtml);
        acaoEntidadeForm.copiarDadosDaAcao(acao);
        return acaoEntidadeForm;
    }

}
