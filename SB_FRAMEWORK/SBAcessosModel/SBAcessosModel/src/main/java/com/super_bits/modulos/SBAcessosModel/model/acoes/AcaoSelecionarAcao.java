/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSelecionarAcao;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class AcaoSelecionarAcao extends AcaoDoSistema implements ItfAcaoSelecionarAcao {

    private List<ItfAcaoDoSistema> acoes;

    public AcaoSelecionarAcao(List<ItfAcaoDoSistema> acoes) {
        super(FabTipoAcaoSistema.ACAO_SELECAO_DE_ACAO,);
        this.acoes = acoes;
    }

    @Override
    public List<ItfAcaoDoSistema> getAcoes() {
        return acoes;
    }

}
