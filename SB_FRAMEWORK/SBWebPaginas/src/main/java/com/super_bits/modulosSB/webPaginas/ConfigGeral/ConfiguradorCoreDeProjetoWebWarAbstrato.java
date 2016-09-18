/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.ConfigGeral;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorCoreDeProjetoJarAbstrato;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreSomenteLeitura;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;

/**
 *
 * @author salvioF
 */
public abstract class ConfiguradorCoreDeProjetoWebWarAbstrato extends ConfiguradorCoreDeProjetoJarAbstrato {

    @Override
    public ItfConfiguracaoCoreSomenteLeitura getConfiguracaoCore(SBCore.ESTADO_APP pEstadoApp) {
        return super.getConfiguracaoCore(pEstadoApp); //To change body of generated methods, choose Tools | Templates.
    }

}
