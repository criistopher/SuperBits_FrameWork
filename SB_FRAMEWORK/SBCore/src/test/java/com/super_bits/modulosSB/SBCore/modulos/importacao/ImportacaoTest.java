/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.importacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.ConfiguradorProjetoSBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
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

        parametros.put("id", 1);
        parametros.put("nome", 2);
        parametros.put("descricao", 3);

        System.out.println(SBCore.getCaminhoDesenvolvimento());

        ImportacaoExcel<ItemSimplesTeste> importador = new ImportacaoExcel<>(SBCore.getCaminhoDesenvolvimento() + "/src/test/resources/excelTesteXLS.xls", parametros, ItemSimplesTeste.class);

        for (ItemSimplesTeste reg : importador.getRegistrosSucesso()) {

            System.out.println(reg.getId());

            System.out.println(reg.getNome());

            System.out.println(reg.getDescricao());

            System.out.println("_________________________");

        }

        System.out.println(importador.getRelatorioImportacao());

    }

}
