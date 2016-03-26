/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes.acaoDeEntidade;

import com.super_bits.Controller.acoes.AcaoDeEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;

/**
 *
 * @author desenvolvedor
 */
public class AcaoEntidadeAlterarStatus extends AcaoDeEntidade {

    private String icone;

    public AcaoEntidadeAlterarStatus(Class pClasse) {
        super(pClasse, FabTipoAcaoSistema.ACAO_ENTIDADE_CONTROLLER);
        acaoGenerica = FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR;
        icone = icone;
    }

}
