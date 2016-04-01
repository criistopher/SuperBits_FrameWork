/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.view.InfoPagina;

/**
 *
 * @author desenvolvedor
 */
public class AcaoGestaoEntidade extends AcaoFormularioEntidade {

    private InfoPagina infoPagina;

    public AcaoGestaoEntidade(ItfAcaoDoSistema pAcaoPrincipal, Class pClasseRelacionada, String pXhtml) {
        super(pAcaoPrincipal, pClasseRelacionada, pXhtml);
        acaoGenerica = FabTipoAcaoSistemaGenerica.ACAO_GESTAO_DE_ENTIDADE;

    }

}
