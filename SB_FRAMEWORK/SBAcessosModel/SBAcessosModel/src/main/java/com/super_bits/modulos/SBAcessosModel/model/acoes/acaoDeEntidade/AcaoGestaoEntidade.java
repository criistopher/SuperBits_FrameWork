/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexao;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;
import com.super_bits.view.InfoPagina;

/**
 *
 * @author desenvolvedor
 */
public class AcaoGestaoEntidade extends AcaoFormularioEntidade {

    private InfoPagina infoPagina;

    public AcaoGestaoEntidade(ItfFabricaAcoes pFabrica,Class pClasse ,String pXhtml) {
        super(pClasse, pFabrica, pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;
    }

    public ItfAcaoDoSistema criarAcaoSecundaria(FabTipoAcaoSistemaGenerica pAcaoGenerica) {
       
        if (true){
            throw  new UnsupportedOperationException("Aind não implementado");
        }
        ItfAcaoDoSistema novaAcao=null;
        String nomeDoObjeto = UtilSBCoreReflexao.getNomeDoObjeto(this.getClasseRelacionada());
        String diretorioFormulariosEntidade = "/site/" + this.getClass().getSimpleName();
        
    
        switch (pAcaoGenerica){
            
        }
        
        return (ItfAcaoDoSistema) novaAcao;
    }

}
