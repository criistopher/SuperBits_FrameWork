/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.anotacoes.FabAcaoGenerica;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoController;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.view.InfoPagina;

/**
 *
 * @author desenvolvedor
 */
public class AcaoGestaoEntidade extends AcaoFormularioEntidade {

    private InfoPagina infoPagina;

    public AcaoGestaoEntidade(ItfAcaoDoSistema pAcaoPrincipal, Class pClasseRelacionada, String pXhtml) {
        super(pAcaoPrincipal, pClasseRelacionada, pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.ACAO_GESTAO_DE_ENTIDADE;

    }

    public ItfAcaoDoSistema criarAcaoSecundaria(FabTipoAcaoSistemaGenerica pAcaoGenerica) {
        ItfAcaoDoSistema novaAcao;
        String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjetthiso(this.getClasseRelacionada());
        String diretorioFormulariosEntidade = "/site/" + this.getClass().getSimpleName();
        return pAcaoGenerica.getRegistro();
    }

}
