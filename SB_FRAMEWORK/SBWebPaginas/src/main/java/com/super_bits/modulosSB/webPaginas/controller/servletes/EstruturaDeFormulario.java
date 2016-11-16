/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.controller.servletes;

import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ParametroURL;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvioF
 */
public class EstruturaDeFormulario {

    private AcaoGestaoEntidade acaoGestao;
    private List<ParametroURL> parametrosURL;
    private int quantidadeParametrosObrigatorios;

    public void configParametrosUrl(List<ParametroURL> pParametros) {
        if (parametrosURL == null) {
            parametrosURL = pParametros;
            pParametros.stream().filter((pr) -> (pr.isParametroObrigatorio())).forEach((_item) -> {
                quantidadeParametrosObrigatorios++;
            });

        }
    }

    public AcaoGestaoEntidade getAcaoGestao() {
        return acaoGestao;
    }

    public List<ParametroURL> getParametrosURL() {
        return parametrosURL;
    }

    public int getQuantidadeParametrosObrigatorios() {
        return quantidadeParametrosObrigatorios;
    }

}
