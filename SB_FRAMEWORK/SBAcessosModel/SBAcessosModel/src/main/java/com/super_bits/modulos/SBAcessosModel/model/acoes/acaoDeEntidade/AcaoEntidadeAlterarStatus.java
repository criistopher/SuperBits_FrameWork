/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;

/**
 *
 * @author desenvolvedor
 */
public class AcaoEntidadeAlterarStatus extends AcaoDeEntidade implements ItfAcaoSecundaria {

    private ItfAcaoSecundaria acaoPrincipal;
    private String icone;

    public AcaoEntidadeAlterarStatus(Class pClasse) {
        super(pClasse, FabTipoAcaoSistema.ACAO_ENTIDADE_CONTROLLER);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR;
        icone = icone;
    }

    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoPrincipal;
    }

}
