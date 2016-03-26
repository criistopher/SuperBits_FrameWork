/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.acoes;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.acoes.acaoDeEntidade.AcaoFormularioDeEntidade;

/**
 *
 * @author desenvolvedor
 */
public class FormularioModalDeEntidade extends AcaoFormularioDeEntidade {

    private static String formularioPadrao = "/resources/SBComp/resources/formularios/formularioModalGenerico.xhtml";

    public FormularioModalDeEntidade(ItfAcaoDoSistema pAcao, Class pClasse) {
        super(pAcao, pClasse, formularioPadrao);
    }

    public FormularioModalDeEntidade(ItfAcaoDoSistema pAcao, Class pClasse, String pFormulario) {
        super(pAcao, pClasse, pFormulario);
    }

}
