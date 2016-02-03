/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
public class InfoWebApp implements Serializable {

    private Map<ItfAcaoDoSistema, AcaoManagedBean> acoesManagedBens;

    public InfoWebApp() {
        acoesManagedBens = new HashMap<>();
    }

    public void putNovoManagedBen(ItfAcaoDoSistema pAcao, AcaoManagedBean pMBmanagedBen) {
        acoesManagedBens.put(pAcao, pMBmanagedBen);
    }

    public AcaoManagedBean getAcaoManagedBean(ItfAcaoDoSistema pAcao) {
        return acoesManagedBens.get(pAcao);
    }

    public boolean isAceosMBConfiguradas() {
        return !acoesManagedBens.isEmpty();
    }

}
