/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoFormularioEntidade;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioModalDeEntidade extends AcaoFormularioEntidade {

    private static String formularioPadrao = "/resources/SBComp/resources/formularios/formularioModalGenerico.xhtml";

    public AcaoFormularioModalDeEntidade(ItfAcaoGerenciarEntidade pAcaoGerenciar) {
        super(null);
    }

    public AcaoFormularioModalDeEntidade(ItfAcaoDoSistema pAcao, Class pClasse) {
        super(pAcao, pClasse, formularioPadrao);
    }

    public AcaoFormularioModalDeEntidade(ItfAcaoDoSistema pAcao, Class pClasse, String pFormulario) {
        super(pAcao, pClasse, pFormulario);
    }

}
