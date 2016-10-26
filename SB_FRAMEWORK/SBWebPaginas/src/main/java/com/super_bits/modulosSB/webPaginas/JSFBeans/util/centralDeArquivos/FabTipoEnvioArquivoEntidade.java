/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.centralDeArquivos;

import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.FabFamiliaCompVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.InfoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfFabTipoComponenteVisual;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.UtilSBFabricaComponenteVisual;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoEnvioArquivoEntidade implements ItfFabTipoComponenteVisual {
    @InfoComponenteVisual(classesCSS = "")
    ARQUIVO_DA_ENTIDADE,
    @InfoComponenteVisual(classesCSS = "")
    ARQUIVO_CRIANDO_NOVA_ENTIDADE,
    @InfoComponenteVisual(classesCSS = "")
    IMAGEM_REPRESENTANTE_ENTIDADE;

    @Override
    public FabFamiliaCompVisual getFamilia() {
        return FabFamiliaCompVisual.INPUT;
    }

    @Override
    public ComponenteVisualSB getComponente() {
        return UtilSBFabricaComponenteVisual.getComponenteVisual(this);
    }

    @Override
    public ComponenteVisualSB getRegistro() {
        return getComponente();
    }

}
