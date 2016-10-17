/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package aux;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfigCoreCustomizavel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorCoreDeProjetoJarAbstrato;
import com.super_bits.modulosSB.SBCore.ConfigGeral.ItfConfiguracaoCoreCustomizavel;

/**
 *
 * @author desenvolvedor
 */
public class ConfiguradorCoreSBSBpersistencia extends ConfiguradorCoreDeProjetoJarAbstrato {

    public ConfiguradorCoreSBSBpersistencia() {
        setIgnorarConfiguracaoAcoesDoSistema(true);
        setIgnorarConfiguracaoPermissoes(true);
    }

    @Override
    public void defineFabricasDeACao(ItfConfiguracaoCoreCustomizavel pConfig) {

    }

}
