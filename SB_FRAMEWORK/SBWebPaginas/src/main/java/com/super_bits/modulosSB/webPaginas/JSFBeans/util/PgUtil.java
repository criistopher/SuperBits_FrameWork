/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.InfoCampos.ItensGenericos.basico.BeanTodosSelecionados;
import com.super_bits.modulosSB.SBCore.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Acao;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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

        String id = idAtualizacao;
        System.out.println("Atualizando o id" + id);
        if (id == null) {
            UtilSBWP_JSFTools.mensagens().erroSistema("o atributo idAtualizacao não foi encontrado, é necessário criar o atributo no componente" + idAtualizacao);
        }
        UtilSBWP_JSFTools.atualizaPorId(id);

    }

    public void mostraDialogoByWidgetVar(String idWidget) {

    }

    public void enviaMensagem(String pMensagem) {
        FabMensagens.enviarMensagemUsuario(pMensagem, FabMensagens.AVISO);
        atualizaTelaPorID("mensagemUsuario");
    }

    public String makeCaminhoCompletoID(String pId) {
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
     * Função bastante útil, quando utilizada com component.clientID, pois o client 
     * id retorna um id de componente a mais (provavelmente referenciando a ele mesmo)
     * 
     * @param pClientID O nome completo (onde o ultimo componente será removido do nome)
     * @return O caminho do ID obtido sem nome do ultimo componente 
     */
    public String makeCaminhoComponenteByClientID(String pClientID) {
        
        boolean fim=false;
        boolean encontrouDoisPontos=false;
        String novoCaminho=new String();
        for (int i=pClientID.length()-1;    i>=0 ; i--) {  
          Character novo=  pClientID.charAt(i);
         
          
          if (encontrouDoisPontos){
              novoCaminho=novo.toString()+novoCaminho;
          }
           if (novo.equals(':')){
              encontrouDoisPontos=true;
          }
          
        }  
        
        
       return novoCaminho; 
    };

}
