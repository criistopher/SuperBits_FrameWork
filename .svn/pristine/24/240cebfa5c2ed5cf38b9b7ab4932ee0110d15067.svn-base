/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces;

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

    public BP_PickList() {
        super();
    }

    public BP_PickList(List<T> pLista, List<T> pOrigem) {
        super(pLista, pOrigem);
        dualListPrime = new DualListModel<T>((List) getOrigem(), (List) getLista());
    }

    public BP_PickList(List<T> pLista, Class pClasseListaTodos) {
        super((List) pLista, pClasseListaTodos);
        dualListPrime = new DualListModel<T>((List) getOrigem(), (List) getLista());
    }

    public DualListModel<T> getDualListPrime() {
        return dualListPrime;
    }

    private void mudaListaPorDualList() {
        lista.clear();
        lista.addAll((List) dualListPrime.getTarget());

        origem.clear();
        origem.addAll((List) dualListPrime.getSource());

        System.out.println("bean Lista Selecionada=" + dualListPrime.getTarget());
        System.out.println("bem Source=" + dualListPrime.getSource());

        System.out.println("Dual Lista Selecionada=" + dualListPrime.getTarget());
        System.out.println("Dual Lista Source=" + dualListPrime.getSource());
        atualizaBeans();
    }

    public void setDualListPrime(DualListModel<T> dualListPrime) {
        this.dualListPrime = dualListPrime;
        System.out.println("Dual Lista Selecionada=" + dualListPrime.getTarget());
        System.out.println("Dual Lista Source=" + dualListPrime.getSource());
        mudaListaPorDualList();
    }

    @Override
    public void atualizaBeans() {
        if (dualListPrime != null) {
            dualListPrime.setSource((List) origem);
            dualListPrime.setTarget((List) lista);
        }
    }

}
