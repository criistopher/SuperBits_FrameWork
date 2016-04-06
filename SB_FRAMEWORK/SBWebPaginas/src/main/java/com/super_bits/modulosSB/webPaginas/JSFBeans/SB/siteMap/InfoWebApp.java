/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author desenvolvedor
 */
@ApplicationScoped
@Named
public class InfoWebApp implements Serializable {

    private final Map<String, AcaoManagedBean> acoesManagedBens;

    public InfoWebApp() {
        acoesManagedBens = new HashMap<>();
    }

    public void putNovoManagedBen(ItfAcaoDoSistema pAcao, AcaoManagedBean pMBmanagedBen) {
        acoesManagedBens.put(pAcao.getNomeUnico(), pMBmanagedBen);
    }

    /**
     *
     * @param pAcao
     * @return Ação managed Ben contendo a URL para acesso
     */
    public AcaoManagedBean getAcaoManagedBean(String pAcao) {
        return acoesManagedBens.get(pAcao);
    }

    public boolean isAceosMBConfiguradas() {
        return !acoesManagedBens.isEmpty();
    }

    public List<AcaoManagedBean> getAcoesMB() {
        List<AcaoManagedBean> acoes = new ArrayList<>();
        for (AcaoManagedBean acao : acoesManagedBens.values()) {
            acoes.add(acao);
        }
        return acoes;
    }

    public String getUrlPagina() {

        return SBWebPaginas.getSiteURL();

    }

}
