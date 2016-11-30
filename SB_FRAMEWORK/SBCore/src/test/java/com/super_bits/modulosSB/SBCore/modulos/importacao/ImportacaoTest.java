/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.importacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreOutputs;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author desenvolvedor
 */
public class ImportacaoTest {

    public ImportacaoTest() {
    }

    @Test
    public void testCarregarArquivo() {

        SBCore.configurar(new ConfiguradorProjetoSBCore(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfBeanSimples Teste = new ItemSimplesTeste();
        Map<String, Integer> parametros = new HashMap<>();
        parametros.put("nome", 1);
        parametros.put("descricao", 2);
        System.out.println(SBCore.getCaminhoDesenvolvimento());

        ImportacaoExcel<ItemSimplesTeste> importador = new ImportacaoExcel<>(
                SBCore.getCaminhoDesenvolvimento(), parametros);

        for (ItemSimplesTeste reg : importador.getRegistros()) {

            System.out.println(reg.getNome());
            System.out.println(reg.getDescricao());
            System.out.println("_________________________");
        }

    }

}
