/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidade;

/**
 *
 * @author desenvolvedor
 */
public class AcaoDeEntidadeDesativar extends AcaoDeEntidade implements ItfAcaoSecundaria {

    private ItfAcaoSecundaria acaoPrincipal;

    public AcaoDeEntidadeDesativar(ItfAcaoGerenciarEntidade pGerenciar) {
        super(null, null, null);
    }

    
    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoPrincipal;
    }
}
