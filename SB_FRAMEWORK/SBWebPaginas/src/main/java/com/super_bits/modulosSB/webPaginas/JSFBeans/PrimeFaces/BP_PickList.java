/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.B_listaComOrigem;
import java.io.Serializable;
import java.util.List;
import org.primefaces.model.DualListModel;

/**
 *
 * @author SAlvio furnbino
 */
public class BP_PickList<T> extends B_listaComOrigem<T> implements Serializable {

    private DualListModel<T> dualListPrime;

    public BP_PickList(List<T> pLista, List<T> pOrigem) {
        super(pLista, pOrigem);
        dualListPrime = new DualListModel<>((List) getOrigem(), (List) getLista());
    }

    public BP_PickList(List<T> pLista, Class pClasseListaTodos) {
        super((List) pLista, pClasseListaTodos);
        dualListPrime = new DualListModel<>((List) getOrigem(), (List) getLista());
    }

    public DualListModel<T> getDualListPrime() {
        return dualListPrime;
    }

    public void setDualListPrime(DualListModel<T> dualListPrime) {

        System.out.println("Dual Lista Selecionada=" + dualListPrime.getTarget());
        System.out.println("Dual Lista Source=" + dualListPrime.getSource());

        this.dualListPrime = dualListPrime;
        //    atualizaListaOrigem();
    }

    @Override
    public void atualizaPickList() {
        if (dualListPrime != null) {
            dualListPrime.setSource((List) origem);
            dualListPrime.setTarget((List) lista);
        }
    }

    @Override
    public void atualizaListaOrigem() {

        if (dualListPrime != null) {
            origem.clear();
            lista.clear();
            for (T itemSrc : dualListPrime.getSource()) {
                origem.add((ItfBeanSimples) itemSrc);
            }
            for (T itemLista : dualListPrime.getTarget()) {
                lista.add((ItfBeanSimples) itemLista);
            }
        }

    }

}
