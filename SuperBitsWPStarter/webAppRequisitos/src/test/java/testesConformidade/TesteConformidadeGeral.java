/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package testesConformidade;

import com.super_bits.config.webPaginas.FabConfiguracoesDeAmbienteInomeProjetoIRequisitos;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.RelatorioTesteWebPaginas;

import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class TesteConformidadeGeral extends RelatorioTesteWebPaginas {

    @Override
    protected void configAmbienteDesevolvimento() {

        SBCore.configurar(FabConfiguracoesDeAmbienteInomeProjetoIRequisitos.DESENVOLVIMENTO.getConfiguracao());
        SBPersistencia.configuraJPA(FabConfiguracoesDeAmbienteInomeProjetoIRequisitos.DESENVOLVIMENTO.getConfiguracaoPersistencia());
        SBWebPaginas.configurar(FabConfiguracoesDeAmbienteInomeProjetoIRequisitos.DESENVOLVIMENTO.getConfiguracaoWebPaginas());
    }

    @Test
    public void testar() {
        exibirRelatorioCompleto();

    }

}
