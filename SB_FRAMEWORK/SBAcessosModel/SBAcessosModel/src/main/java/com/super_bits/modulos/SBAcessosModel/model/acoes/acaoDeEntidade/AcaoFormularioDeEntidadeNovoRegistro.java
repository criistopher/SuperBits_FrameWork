/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioDeEntidadeNovoRegistro extends AcaoFormularioEntidade implements ItfAcaoSecundaria {

    public AcaoFormularioDeEntidadeNovoRegistro(ItfAcaoDoSistema pAcaoPrincipal, Class pClasseRelacionada, String pXhtml) {
        super(pAcaoPrincipal, pClasseRelacionada, pXhtml);
        acaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;
    }

}
