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

    private final Map<String, AcaoComLink> acoesManagedBens;

    public InfoWebApp() {
        acoesManagedBens = new HashMap<>();
    }

    public void putNovoManagedBen(ItfAcaoDoSistema pAcao, AcaoComLink pMBmanagedBen) {
        acoesManagedBens.put(pAcao.getNomeUnico(), pMBmanagedBen);
    }

    /**
     *
     * @param pAcao
     * @return Ação managed Ben contendo a URL para acesso
     */
    public AcaoComLink getAcaoComLink(ItfAcaoDoSistema pAcao) {

        for (String chave : acoesManagedBens.keySet()) {
            System.out.println(chave);
        }
        if (pAcao.isUmaAcaoGestaoDominio()) {
            System.out.println("ação de dominio, retornando url para" + pAcao.getNomeUnico());
            return acoesManagedBens.get(pAcao.getNomeUnico());
        } else {
            System.out.println("ação secundaria, retornando url para" + pAcao.getComoSecundaria().getAcaoPrincipal().getNomeUnico());
            return acoesManagedBens.get(pAcao.getComoSecundaria().getAcaoPrincipal().getNomeUnico());
        }
    }

    public boolean isAceosMBConfiguradas() {
        return !acoesManagedBens.isEmpty();
    }

    public List<AcaoComLink> getAcoesMB() {
        List<AcaoComLink> acoes = new ArrayList<>();
        for (AcaoComLink acao : acoesManagedBens.values()) {
            acoes.add(acao);
        }
        return acoes;
    }

    public String getUrlPagina() {

        return SBWebPaginas.getSiteURL();

    }

}
