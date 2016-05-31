/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.TratamentoDeErros;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivoTexto;
import com.super_bits.modulosSB.SBCore.Mensagens.MensagemProgramador;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTILSBCoreDesktopApp;
import com.super_bits.modulosSB.SBCore.testesFW.TesteJunit;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public abstract class RelatorioTesteAbstrato extends TesteJunit implements ItfRelatorioTestes {

    @Override
    public void exibirRelatorio() {

        List<ItfInfoErroSB> erros = executarTestes();
        String mensage = "OS SEGUINTES ERROS DE CONFIGURAÇÃO FORAM ENCONTRADOS NO SISTEMA: \n";
        mensage += " Você pode encontrar informações mais detalhadas do erro no SystemOut \n";
        for (ItfInfoErroSB erro : erros) {
            mensage += ("->" + erro.getMsgDesenvolvedorLancou() + "\n");
        }

        UTILSBCoreDesktopApp.showMessageStopProcess(new MensagemProgramador(mensage));
        String arquivoRelatorio = SBCore.getCaminhoDesenvolvimento() + "/temp/errosAcoes.txt";
        UtilSBCoreArquivoTexto.limparArquivoTexto(arquivoRelatorio);
        UtilSBCoreArquivoTexto.printLnNoArquivo(mensage, arquivoRelatorio);
    }

}
