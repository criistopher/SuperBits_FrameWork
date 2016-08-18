/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.BeanTodosSelecionados;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreCEP;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Acao;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ValueExpression;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Salvio
 */
@RequestScoped
@Named
public class PgUtil implements Serializable {

    @Inject
    private Cores cores;
    @Inject
    private Tema tema;

    @Inject
    private SessaoAtualSBWP sessao;

    @Inject
    private InfoWebApp infoWeb;

    public void testeMuitoLouco(ItfB_Pagina pagina) {
        pagina.executarAcaoSelecionada();
    }

    private final CampoNaoImplementado camponaoImplementado = new CampoNaoImplementado();

    private BeanTodosSelecionados beanTodosSelecionados = new BeanTodosSelecionados();

    public void mensagemAlerta(String pMensagem) {
        FabMensagens.enviarMensagemUsuario(pMensagem, FabMensagens.ALERTA);
    }

    public void mensagemErro(String pMensagem) {
        FabMensagens.enviarMensagemUsuario(pMensagem, FabMensagens.ERRO);
    }

    public void mensagemInfo(String pMensagem) {
        FabMensagens.enviarMensagemUsuario(pMensagem, FabMensagens.AVISO);
    }

    public BeanTodosSelecionados getBeanTodosSelecionados() {
        return beanTodosSelecionados;
    }

    public void setBeanTodosSelecionados(BeanTodosSelecionados beanTodosSelecionados) {
        this.beanTodosSelecionados = beanTodosSelecionados;
    }

    public String abrirXHTML(String pXHTML) {
        return pXHTML;
    }

    public Tema getTema() {

        if (tema == null) {
            return new Tema();
        }
        return tema;
    }

    public Cores getCores() {
        if (cores == null) {
            cores = new Cores();
        }
        return cores;
    }

    public long getDataHoraLong() {

        return new Date().getTime();
    }

    @InfoMB_Acao(descricao = "Evento de ajax que recebe o atributo idAtualizacao  <p ajax event='onAlgumaCoisa') que atualiza uma parte da tela pelo ID")
    public static void eventAtualizaTelaPorID(AjaxBehaviorEvent event) {

        String id = (String) event.getComponent().getAttributes().get("idAtualizacao");
        System.out.println("Atualizando o id" + id);
        if (id == null) {
            UtilSBWP_JSFTools.mensagens().erroSistema("o atributo idAtualizacao não foi encontrado, é necessário criar o atributo no componente" + event.getComponent().toString());
        }

        UtilSBWP_JSFTools.atualizaPorId(id);

    }

    public void atualizaTelaPorID(String idAtualizacao) {
        try {
            String id = idAtualizacao;
            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
                System.out.println("Atualizando a exibição dos componentes nomeados com: " + id);

            }

            if (id == null) {

            }
            UtilSBWP_JSFTools.atualizaPorId(id);
        } catch (Throwable t) {
            UtilSBWP_JSFTools.mensagens().erroSistema("o atributo idAtualizacao não foi encontrado, é necessário criar o atributo no componente" + idAtualizacao);
        }
    }

    public void mostraDialogoByWidgetVar(String idWidget) {

    }

    public void enviaMensagem(String pMensagem) {
        FabMensagens.enviarMensagemUsuario(pMensagem, FabMensagens.AVISO);
        atualizaTelaPorID("mensagemUsuario");
    }

    /**
     *
     * Procura pelos componentes com este id, e retorna o caminho completo do
     * componente
     *
     *
     * Ex. um componente com o id = areaParaAtualizacao pode durante a
     * reinderização do jsf ficar com o nome
     * conteudo.divDaEsquerda.jstdid12.areaparaAtualizacao
     *
     *
     * @param pId O id de componente que deseja saber o caminho completo
     *
     *
     *
     *
     * @return
     */
    public String makeCaminhoCompletoID(String pId) {
        if (pId == null) {
            return null;
        }
        if (pId.length() == 0) {
            return null;
        }
        if (pId.contains("@")) {
            return pId;
        }
        if (pId == null || pId.equals("")) {
            return null;
        }
        return UtilSBWP_JSFTools.getIDSCaminhoAbsoluto(pId);
    }

    public String getEnderecoPagina() {
        return SBWebPaginas.getURLBase();
    }

    public void copiarRessource() {
        if (UtilSBCoreArquivos.copiarArquivos(UtilSBWP_JSFTools.getCaminhoLocalRessource(), SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources")) {
            FabMensagens.enviarMensagemUsuario("Arquivos da pasta Ressource copiados com sucesso", FabMensagens.AVISO);
        } else {
            FabMensagens.enviarMensagemUsuario("Aconteceu um erro ao copiar os resources", FabMensagens.ERRO);
        }
    }

    private Object getMetodoGet(Object item) {
        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTrace = t.getStackTrace();
        final StackTraceElement ste = stackTrace[3];
        final String methodName = ste.getMethodName();
        final String className = ste.getClassName();
        Class<?> kls;
        try {
            kls = Class.forName(className);

            do {
                for (final Method candidate : kls.getDeclaredMethods()) {
                    System.out.println("Metodo:" + methodName);
                    if (candidate.getName().equals(methodName)) {

                        //   return candidate.invoke(item);
                    }
                }
                kls = kls.getSuperclass();
            } while (kls != null);

        } catch (ClassNotFoundException ex) {
            return null;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PgUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     *
     * Função bastante útil, quando utilizada com component.clientID, pois o
     * client id retorna um id de componente a mais (provavelmente referenciando
     * a ele mesmo)
     *
     * @param pClientID O nome completo (onde o ultimo componente será removido
     * do nome)
     * @return O caminho do ID obtido sem nome do ultimo componente
     */
    public String makeCaminhoComponenteByClientID(String pClientID) {

        boolean fim = false;
        boolean encontrouDoisPontos = false;
        String novoCaminho = new String();
        for (int i = pClientID.length() - 1; i >= 0; i--) {
            Character novo = pClientID.charAt(i);

            if (encontrouDoisPontos) {
                novoCaminho = novo.toString() + novoCaminho;
            }
            if (novo.equals(':')) {
                encontrouDoisPontos = true;
            }

        }

        return novoCaminho;
    }

    public String getInfoComponente(String pId) {
        try {
            UIComponent componenteRaiz = FacesContext.getCurrentInstance().getViewRoot();
            UIComponent componenteEncontrador = componenteRaiz.findComponent(pId);
            for (UIComponent comp : componenteRaiz.getChildren()) {
                System.out.println(comp.getId());

            }

            System.out.println("");
            return pId;
        } catch (Throwable t) {
            return "Aconteceu Um Erro";
        }
    }

    public String buscaFilhoComEsteID(UIComponent componente, String atributo) {
        String resultado = UtilSBWP_JSFTools.getAbsoluteComponentPaths(UtilSBWP_JSFTools.resolveList(atributo, componente));
        return resultado.replace(":" + componente.getClientId(), resultado);

    }

    /**
     *
     * @param component
     * @param atributo
     * @return
     */
    public boolean isAtributoPreenchidoComExpressao(UIComponent component, String atributo) {
        try {
            ValueExpression valor = component.getValueExpression(atributo);
            if (valor != null) {
                if (valor.getExpressionString().length() > 3) {
                    System.out.println("O v");
                    return true;
                }
                System.out.println("o componente " + component.getId() + " não possui 3 caracteres no  " + atributo + " está nulo" + valor.getExpressionString());
                return false;
            } else {
                System.out.println("o atributo do componente" + component.getId() + " do atributo " + atributo + " está nulo");
                return false;
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Impossível determinar se o atributo do componente foi configurado", t);
            return false;
        }
    }

    public void preencherEndereco(String pcep, ItfLocal pLocal) {
        System.out.println("CEP ENVIADO:" + pcep);

        UtilSBCoreCEP.configuraEndereco(pcep, pLocal);
    }

    public static List<String> getCepEncontrados() {
        List<String> ceps = new ArrayList<>();
        ceps.add("30190030 - Rua goias ");
        ceps.add("30190030 - Rua Juca fontes ");

        return ceps;
    }

    public String navegar(ItfAcaoDoSistema pAcao) {

        if (pAcao != null) {
            return infoWeb.getAcaoComLink(pAcao).getUrlDeAcesso();
        } else {
            return null;
        }
    }

    public CampoNaoImplementado getCampoNaoImplementado() {
        return camponaoImplementado;
    }

    public boolean isTudoVerdadeiro(boolean... pCondicoes) {

        for (boolean condicao : pCondicoes) {
            if (!condicao) {
                return false;
            }
        }

        return true;
    }

    public String getNomeDoCompPai(UIComponent pComponente) {
        return null;
    }

    public String getSufixoCaminhoAteAqui(UIComponent componente, String caminho) {
        return null;
    }

    public String defineNomeDoFilho(UIComponent componente) {
        System.out.println("Gerando id para" + componente.getClientId());
        return "AssimVcFuncionaNumEDanada";
    }

}
