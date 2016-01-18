package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.Persistencia.dao.SBNQ;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfCentralMensagens;
import com.super_bits.modulosSB.SBCore.Mensagens.ItfMensagem;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.ErroCritico.InfoErroCritico;
import com.super_bits.modulosSB.webPaginas.JSFBeans.declarados.Paginas.ErroCritico.InfoErroCriticoSQL;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.MensagensJSF;
import com.super_bits.modulosSB.webPaginas.TratamentoDeErros.ErroSBCritico;
import javax.faces.context.FacesContext;

public class CentralDeMensagensJSFAPP implements ItfCentralMensagens {

    private static InfoErroCritico infoErro;

    private void enviaMensagemJSF(ItfMensagem pMensgem) {
        switch (pMensgem.getTipoDeMensagem()) {
            case AVISO:
                MensagensJSF.infoMensagem(pMensgem.getMenssagem());
                break;
            case ALERTA:
                MensagensJSF.alertaMensagem(pMensgem.getMenssagem());
                break;
            case ERRO:
                MensagensJSF.erroMensagem(pMensgem.getMenssagem());
                break;
            case ERRO_FATAL:
                UtilSBWP_JSFTools.vaParaPaginadeErro(pMensgem.getMenssagem());
                break;
            default:
                throw new AssertionError(pMensgem.getTipoDeMensagem().name());
        }

    }

    private void enviaAlertaUsuario(String pMensgem) {
        MensagensJSF.infoMensagem(pMensgem);
    }

    private void enviaErroUsuario(String pMensgem) {
        MensagensJSF.erroMensagem(pMensgem);
    }

    private void mensagemSistema(String pMensagem) {
        System.out.println(pMensagem);
    }

    public void alertaSistema(String pMensagem) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("ALERTA !!!!!!!!!!!!!!!");
        System.out.println(pMensagem);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

    }

    public void erroSistema(String pMensagem) {
        try {
            throw new ErroSBCritico(pMensagem);
        } catch (ErroSBCritico e) {
            erroSistema(pMensagem, e);

        }
    }

    public void erroSistema(String pMensagem, Exception pExcept) {

        InfoErroCritico infoerro = new InfoErroCritico(pMensagem, pExcept);

        UtilSBWP_JSFTools.vaParaPagina(SBWebPaginas.getSiteURL() + "/erroCritico/" + infoerro.getTituloErro() + "/");
        //BeansUtil.putObjetoRequestScope("infoErroCritic",infoerro);
        //NavUtil.carregaPagina("/SBSystemPages/erroCriticoDeSistema.xhtml");
        UtilSBWPServletTools.putObjetoSessionScope("infoErroCritic", infoerro);
        System.out.println("teste executando comando depois de ir para a pagina");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("infoPrin");
    }

    public void erroSQL(String pMensagem, SBNQ qinfo, Exception pExcept) {

        InfoErroCriticoSQL infoerro = new InfoErroCriticoSQL(pMensagem, qinfo, pExcept);
        UtilSBWPServletTools.putObjetoSessionScope("infoErroCritic", infoerro);
        UtilSBWP_JSFTools.vaParaPagina(SBWebPaginas.getSiteURL() + "/erroSQLInfo/" + infoerro.getTituloErro() + "/");
        //BeansUtil.putObjetoRequestScope("infoErroCritic",infoerro);
        //NavUtil.carregaPagina("/SBSystemPages/erroCriticoDeSistema.xhtml");

        System.out.println("teste executando comando depois de ir para a pagina");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("infoPrin");
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().renderResponse();
    }

    @Override
    public void enviaMensagem(ItfMensagem pMensagem) {

        switch (pMensagem.getTipoDestinatario()) {
            case USUARIO:
                enviaMensagemJSF(pMensagem);
                break;
            case SISTEMA:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:

                        enviaMensagemJSF(pMensagem);
                        break;
                    case PRODUCAO:
                        SBCore.getCentralDeEventos().registrarLogDeEvento(pMensagem.getTipoDeMensagem(), pMensagem.getMenssagem());
                        break;
                    case HOMOLOGACAO:
                        enviaMensagemJSF(pMensagem);
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());

                }

                break;
            case DESENVOLVEDOR:
                switch (SBCore.getEstadoAPP()) {
                    case DESENVOLVIMENTO:
                        enviaMensagemJSF(pMensagem);
                        break;
                    case PRODUCAO:
                        // UM ERRO DA CONTA DO DESENVOLVEDOR, DEVE APARECE APENAS QUANDO UM ADMINISTRADOR ESTIVER LOGADO, A DETECÇÃO DESTE ESTADO AINDA NÃO FOI IMPLEMENTADA
                        alertaSistema(pMensagem.getMenssagem());
                        break;
                    case HOMOLOGACAO:
                        enviaMensagemJSF(pMensagem);
                        break;
                    default:
                        throw new AssertionError(SBCore.getEstadoAPP().name());

                }

                break;
            default:
                throw new AssertionError(pMensagem.getTipoDestinatario().name());

        }

    }

}
