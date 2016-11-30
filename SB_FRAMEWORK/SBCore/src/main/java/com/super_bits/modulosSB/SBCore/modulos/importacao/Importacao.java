/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.importacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author desenvolvedor
 */
public class Importacao<T> {

    private Workbook planilha;

    private Class classeImportacao;

    private Map<String, Integer> mapaDeColunas;
    private List<T> registros;

    public Importacao(String pCaminhoArquivo, Map<String, Integer> mapa) {
        carregarArquivo(pCaminhoArquivo);

    }

    public void carregarArquivo(String pCaminhoArquivo) {
        try {
            planilha = Workbook.getWorkbook(new File(pCaminhoArquivo));
        } catch (BiffException ex) {
            SBCore.RelatarErro(FabErro.LANCAR_EXCECÃO, "Erro Tentando carregar planilha", ex);
        } catch (IOException ex) {
            Logger.getLogger(Importacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isPlanilhaCarregada() {
        return planilha != null;
    }

    public void processar() {
        registros = new ArrayList<>();
        for (Sheet aba : planilha.getSheets()) {
            try {

                System.out.println("Lendo Aba" + aba.getName());

                for (int i = 0; i < aba.getRows(); i++) {
                    ItfBeanSimples novoRegistro = (ItfBeanSimples) classeImportacao.newInstance();

                    for (String nomeCampo : mapaDeColunas.keySet()) {

                        Cell valorCampo = aba.getCell(mapaDeColunas.get(nomeCampo), i);

                        if (valorCampo != null) {
                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(valorCampo.getContents());
                        }

                    }

                }

            } catch (InstantiationException | IllegalAccessException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro ao ler colna", ex);
            }

        }

    }

    public List<T> getRegistros() {
        if (registros == null) {
            processar();
        }
        return registros;
    }

}
