/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.ConfigGeral;

/**
 *
 * @author salvioF
 */
public abstract class ConfiguradorCoreDeProjetoJarAbstrato extends ConfiguradorCoreAbstrato {

    @Override
    public void defineClassesBasicas(ItfConfiguracaoCoreCustomizavel pConfiguracao) {
        UtilConfiguracaoCore.setclassesPadraoJar(pConfiguracao);
    }

    public ConfiguradorCoreDeProjetoJarAbstrato() {
        super(true);
    }

}
