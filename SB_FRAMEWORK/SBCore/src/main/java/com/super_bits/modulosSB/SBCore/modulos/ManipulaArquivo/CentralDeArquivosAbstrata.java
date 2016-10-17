/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.ConfigGeral.FabTipoEmpacotamento;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;

/**
 *
 * @author salvioF
 */
public abstract class CentralDeArquivosAbstrata implements ItfCentralDeArquivos {

    protected final FabTipoEmpacotamento tipoEmpacotamento;

    protected String diretorioResourcesJava;

    public void defineVariaveisPadrao() {

    }

    public CentralDeArquivosAbstrata(FabTipoEmpacotamento pTipoEmpacotamento) {
        tipoEmpacotamento = pTipoEmpacotamento;
    }

}
