/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWPSiteMapa;
import javax.faces.context.FacesContext;

/**
 *
 * @author desenvolvedor
 */
public class AcaoDeContexto extends AcaoComLink {

    private boolean permitidoExecutar;
    private boolean emPaginaDaGestao;

    public AcaoDeContexto(ItfAcaoDoSistema pAcaoDoSistema, FacesContext fc, ItfB_Pagina pPaginaAtual, Object... parametros) {
        super(pAcaoDoSistema.getComoFormulario(), null);
        try {

            if (fc == null) {
                throw new UnsupportedOperationException("O contexto não foi enviado para criação de ação de contexto");
            }
            if (pPaginaAtual.getAcaoVinculada().getNomeUnico().equals(pAcaoDoSistema.getAcaoDeGestaoEntidade().getNomeUnico())) {
                emPaginaDaGestao = true;
            } else {
                emPaginaDaGestao = false;
                UtilSBWPSiteMapa.getUrlParaAcao(pAcaoDoSistema);
                alterarUrl(MapaDeFormularios.getUrlFormulario(pAcaoDoSistema, parametros));
            }

            permitidoExecutar = SBCore.getControleDeSessao().getSessaoAtual().isAcessoPermitido(pAcaoDoSistema);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro Criando ação de contexto", t);

        }
    }

}
