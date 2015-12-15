package com.super_bits.modulosSB.webPaginas.JSFBeans.SB;

import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.MensagensJSF;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.util.ArrayList;
import java.util.List;

public class B_SeletorItensFacil<T extends ItfBeanSimples> extends B_SeletorItemFacil<T> {

    private List<ItfBeanSimples> itensSelecionados;
    private final int quantidadeMaxima;
    private int itemRemover;
    private int numeroColunasDesign;

    public List<T> getItensSelecionados() {
        return (List<T>) itensSelecionados;
    }

    public void setItensSelecionados(List<ItfBeanSimples> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public int getItemRemover() {
        return itemRemover;
    }

    public void setItemRemover(int itemRemover) {
        this.itemRemover = itemRemover;
    }

    public int getNumeroColunasDesign() {
        return numeroColunasDesign;
    }

    public void setNumeroColunasDesign(int numeroColunasDesign) {
        this.numeroColunasDesign = numeroColunasDesign;
    }

    public B_SeletorItensFacil(int pQuantidade, List<? extends ItfBeanSimples> pOpcoes, List<B_Lista<? extends ItfBeanSimples>> pOpcoesOrdenadas) {
        super(pOpcoes, pOpcoesOrdenadas);
        quantidadeMaxima = pQuantidade;
        itensSelecionados = new ArrayList<>(quantidadeMaxima);
        numeroColunasDesign = 4;
    }

    public void methodRemoveItem() {
        try {
            itensSelecionados.remove(itemRemover);
            UtilSBWP_JSFTools.atualizaPorId("id-panelGroup-area-itens");
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: B_SeletorItensFacil.java \nMétodo/Atributo/Local: mehtodRemoveItem \n\n", exception);
        }
    }

    public void mathodClearItems() {
        try {
            itensSelecionados.clear();
            UtilSBWP_JSFTools.atualizaPorId("id-panelGroup-area-itens");

        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: B_SeletorItensFacil.java \nMétodo/Atributo/Local: mathodClearItems \n\n", exception);
        }
    }

    public void methodShowMessage() {
        try {
            UtilSBWP_JSFTools.atualizaPorId("id-panelGroup-area-itens");
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: B_SeletorItensFacil.java \nMétodo/Atributo/Local: methodShowMessage \n\n", exception);
        }
    }

    @Override
    public void setItemSelecionado(ItfBeanSimples itemSelecionado) {
        try {
            boolean itemRepetido = false;
            if (itensSelecionados.size() < quantidadeMaxima) {
                if (itemSelecionado != null) {
                    for (ItfBeanSimples itensSelecionado : itensSelecionados) {
                        if (itemSelecionado.equals(itensSelecionado)) {
                            itemRepetido = true;
                            break;
                        } else {
                            itemRepetido = false;
                        }
                    }

                    if (itemRepetido == false) {
                        //System.out.println("\nItem Selecionado = ".concat(itemSelecionado.getNomeCurto()) + "\n");
                        //System.out.println(itensSelecionados.size());
                        itensSelecionados.add(itemSelecionado);

                        MensagensJSF.infoMensagem("ITEM ADICIONADO PARA COMPARAÇÃO");
                    } else {
                        //System.out.println("\nO item escolhido não pode se repetir.\n");

                        MensagensJSF.infoMensagem("ESTE ITEM JÁ FOI ADICIONADO PARA COMPARAÇÃO");
                    }
                } else {
                    //System.out.println("\nO item selecionado está nulo.\n");

                    MensagensJSF.infoMensagem("O ITEM ESTÁ NULO");
                }
            } else {
                //System.out.println("\nA lista selecionada já possui o máximo de itens selecionados.\n");

                MensagensJSF.infoMensagem("SOMENTE 3 ITENS PODEM SER COMPARADOS POR VEZ");
            }
        } catch (Exception exception) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("\n\nERRO GENÉRICO: " + exception + " \nArquivo: B_SeletorItensFacil.java \nMétodo/Atributo/Local: setItemSelecionado \n\n", exception);
        } finally {
            zerarItemSelecionado();
        }
    }
}
