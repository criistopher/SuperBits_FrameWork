/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoClasse;

import javax.persistence.Entity;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoClasse(tags = {"Ação formulario Modal relacionado a entidade"}, plural = "Ações de formulario Modal")
public class AcaoFormularioModalDeEntidade extends AcaoFormularioEntidade {

    private static String formularioPadrao = "/resources/SBComp/resources/formularios/formularioModalGenerico.xhtml";

    public AcaoFormularioModalDeEntidade() {
        super();
    }

    public AcaoFormularioModalDeEntidade(ItfAcaoGerenciarEntidade pAcaoPrincipal, Class pClasseRelacionada, String pXhtml, ItfFabricaAcoes enumAcao) {
        super(pAcaoPrincipal, enumAcao, pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_MODAL;

    }

}
