/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioEntidadeEditar extends AcaoFormularioEntidade implements ItfAcaoSecundaria {

    

    public AcaoFormularioEntidadeEditar(ItfAcaoGerenciarEntidade pAcaoPrincipal, Class pClasseRelacionada, String pXhtml,ItfFabricaAcoes enumAcao) {
        super(pAcaoPrincipal, pClasseRelacionada, pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR;
    }

}
