/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioDeEntidadeEditar extends AcaoFormularioDeEntidade {

    public AcaoFormularioDeEntidadeEditar(ItfAcaoDoSistema pAcaoPrincipal, Class pClasseRelacionada, String pXhtml) {
        super(pAcaoPrincipal, pClasseRelacionada, pXhtml);
        acaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR;
    }

}
