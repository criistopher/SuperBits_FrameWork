/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;

/**
 *
 * @author desenvolvedor
 */
public class FormularioModalDeEntidade extends AcaoFormularioEntidade {

    private static String formularioPadrao = "/resources/SBComp/resources/formularios/formularioModalGenerico.xhtml";

    public FormularioModalDeEntidade(ItfAcaoDoSistema pAcao, Class pClasse) {
        super(pAcao, pClasse, formularioPadrao);
    }

    public FormularioModalDeEntidade(ItfAcaoDoSistema pAcao, Class pClasse, String pFormulario) {
        super(pAcao, pClasse, pFormulario);
    }

}
