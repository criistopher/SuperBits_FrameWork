/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package aux;

import com.super_bits.modulos.SBAcessosModel.controller.FabAcaoSeguranca;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorCoreDeProjetoJarAbstrato;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ControleDeSessaoPadrao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreCustomizavel;

/**
 *
 * @author desenvolvedor
 */
public class ConfiguradorCoreSBAcessosModelTestes extends ConfiguradorCoreDeProjetoJarAbstrato {

    public ConfiguradorCoreSBAcessosModelTestes() {
        setIgnorarConfiguracaoPermissoes(true);
    }

    @Override
    public void defineFabricasDeACao(ItfConfiguracaoCoreCustomizavel pConfig) {
        pConfig.setFabricaDeAcoes(new Class[]{FabAcaoSeguranca.class});
        pConfig.setControleDeSessao(ControleDeSessaoPadrao.class);

    }

}
