/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class AcaoSelecionarAcao extends AcaoDoSistema {

    private final List<ItfAcaoDoSistema> acoes;

    public AcaoSelecionarAcao(List<ItfAcaoDoSistema> acoes) {
        super(FabTipoAcaoSistema.ACAO_SELECAO_DE_ACAO);
        this.acoes = acoes;
    }

}
