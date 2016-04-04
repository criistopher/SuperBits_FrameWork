/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDeEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;

/**
 *
 * @author desenvolvedor
 */
public class AcaoEntidadeAlterarStatus extends AcaoDeEntidade implements ItfAcaoSecundaria {

    private ItfAcaoSecundaria acaoPrincipal;
    private String icone;

    public AcaoEntidadeAlterarStatus(ItfAcaoGerenciarEntidade pAcaoPrincipal) {
        super(null, null, null);
    }
   
    public AcaoEntidadeAlterarStatus() {
        super(null, null, null);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR;
        icone = icone;
    }

    @Override
    public ItfAcaoDoSistema getAcaoPrincipal() {
        return acaoPrincipal;
    }

}
