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
public class AcaoDeEntidadeDesativar extends AcaoDeEntidade implements ItfAcaoSecundaria {

    private ItfAcaoSecundaria acaoPrincipal;

    public AcaoDeEntidadeDesativar(Class pClasse) {
        super(pClasse, FabTipoAcaoSistema.ACAO_ENTIDADE_CONTROLLER);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.DESATIVAR;
    }

    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoPrincipal;
    }
}
