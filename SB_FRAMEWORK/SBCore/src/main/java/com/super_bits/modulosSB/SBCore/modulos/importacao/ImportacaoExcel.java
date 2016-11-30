/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.importacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.TIPO_PRIMITIVO;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
public class ImportacaoExcel<T> {

    private Workbook planilha;

    private Class classeImportacao;

    private Map<String, Integer> mapaDeColunas;
    private List<T> registros;
    private String caminhoArquivo;

    public ImportacaoExcel(String pCaminhoArquivo, Map<String, Integer> mapa, Class classe) {

        carregarArquivo(pCaminhoArquivo);
        classeImportacao = classe;
        mapaDeColunas = mapa;
        caminhoArquivo = pCaminhoArquivo;

    }

    public final void carregarArquivo(String pCaminhoArquivo) {
        try {
            planilha = Workbook.getWorkbook(new File(pCaminhoArquivo));
        } catch (BiffException ex) {
            SBCore.RelatarErro(FabErro.LANCAR_EXCECÃO, "Erro Tentando carregar planilha", ex);
        } catch (IOException ex) {
            Logger.getLogger(ImportacaoExcel.class.getName()).log(Level.SEVERE, null, ex);
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

                        int coluna = mapaDeColunas.get(nomeCampo) - 1;

                        TIPO_PRIMITIVO tipoPrimitivo = novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).getTipoPrimitivoDoValor();

                        Cell valorCampo = aba.getCell(coluna, i);

                        try {

                            if (valorCampo != null) {

                                switch (tipoPrimitivo) {

                                    case INTEIRO:

                                        if (!valorCampo.getContents().equals("")) {

                                            int inteiroRecebido = Integer.parseInt(valorCampo.getContents());

                                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(inteiroRecebido);

                                        } else {

                                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(0);

                                        }

                                        break;

                                    case LETRAS:

                                        novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(valorCampo.getContents());

                                        break;

                                    case DATAS:

                                        if (!valorCampo.getContents().equals("")) {

                                            String dataRecebida = valorCampo.getContents();

                                            Date dataFormatada = UtilSBCoreDataHora.converteStringEmData(dataRecebida);

                                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(dataFormatada);

                                        } else {

                                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(null);

                                        }

                                        break;

                                    case BOOLEAN:

                                        if (!valorCampo.getContents().equals("")) {

                                            boolean booleanRecebido = Boolean.parseBoolean(valorCampo.getContents());

                                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(booleanRecebido);

                                        } else {

                                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(false);

                                        }

                                        break;

                                    case DECIMAL:

                                        if (!valorCampo.getContents().equals("")) {

                                            double doubleRecebido = Double.parseDouble(valorCampo.getContents());

                                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(doubleRecebido);

                                        } else {

                                            novoRegistro.getCampoByNomeOuAnotacao(nomeCampo).setValor(0.0D);

                                        }

                                    case ENTIDADE:

                                        break;

                                    case OUTROS_OBJETOS:

                                        break;

                                    default:

                                        throw new AssertionError(tipoPrimitivo.name());

                                }

                            }

                        } catch (Throwable t) {

                            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "\nNão foi possivel setar o valor do campo: " + nomeCampo + "\n" + "\nTipo do campo: " + tipoPrimitivo + "\n" + "     Valor recebido: " + valorCampo.getContents() + "\n" + "\nPOSIÇÃO NO XML" + "\n" + "\nLinha: " + (i + 1) + "\n" + "\nColuna: " + (coluna + 1), t);

                        }

                    }

                    registros.add((T) novoRegistro);

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
