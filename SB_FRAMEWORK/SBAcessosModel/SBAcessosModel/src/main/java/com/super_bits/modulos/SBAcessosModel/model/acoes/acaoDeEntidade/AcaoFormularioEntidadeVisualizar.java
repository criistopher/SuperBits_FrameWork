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
public class AcaoFormularioEntidadeVisualizar extends AcaoFormularioEntidade implements ItfAcaoSecundaria {

    public AcaoFormularioEntidadeVisualizar(ItfAcaoEntidade pAcaoPrincipal) {
        super(pAcaoPrincipal, null, null);
    }

    public AcaoFormularioEntidadeVisualizar(ItfAcaoEntidade pAcaoPrincipal, String pXhtml) {
        super(pAcaoPrincipal, pAcaoPrincipal.getClasseRelacionada(), pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR;
    }

}
