/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioDeEntidadeNovoRegistro extends AcaoFormularioEntidade implements ItfAcaoSecundaria {

    public AcaoFormularioDeEntidadeNovoRegistro(ItfAcaoEntidade pAcaoPrincipal, String pXhtml) {
        super(pAcaoPrincipal, pAcaoPrincipal.getClasseRelacionada(), pXhtml);
        acaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;
    }

}
