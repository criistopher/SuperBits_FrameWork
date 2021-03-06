/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioEntidadeListar extends AcaoFormularioEntidade implements ItfAcaoSecundaria {

    private String tituloForm;

   

    public AcaoFormularioEntidadeListar(ItfAcaoGerenciarEntidade pAcaoPrincipal,ItfFabricaAcoes pFabrica ,String pXhtml) {
        super(pAcaoPrincipal, pFabrica, pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;
    }

    public String getTituloForm() {
        return tituloForm;
    }

    public void setTituloForm(String tituloForm) {
        this.tituloForm = tituloForm;
    }

}
