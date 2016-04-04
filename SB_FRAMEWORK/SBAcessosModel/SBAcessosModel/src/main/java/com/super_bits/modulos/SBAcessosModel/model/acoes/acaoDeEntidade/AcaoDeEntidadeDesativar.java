/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidade;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public class AcaoDeEntidadeDesativar extends AcaoDeEntidade implements ItfAcaoSecundaria {

    private ItfAcaoSecundaria acaoPrincipal;

    public AcaoDeEntidadeDesativar(ItfAcaoGerenciarEntidade pGerenciar,ItfFabricaAcoes pAcaoVinculada) {
        super(pGerenciar.getClasseRelacionada(),FabTipoAcaoSistema.ACAO_ENTIDADE_CONTROLLER , pAcaoVinculada);
    }

    
    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoPrincipal;
    }
}
