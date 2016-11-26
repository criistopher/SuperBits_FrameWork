/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.BeanTodosSelecionados;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CampoNaoImplementado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreCEP;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreComunicacao;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.CaminhoCampoReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.GrupoCampos;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.AcaoDeContexto;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfB_Pagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfPaginaGerenciarEntidade;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.anotacoes.beans.InfoMB_Acao;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Inject
    private ItfPaginaAtual paginaAtual;

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

    /**
     *
     * @param beanTodosSelecionados
     */
    public void setBeanTodosSelecionados(BeanTodosSelecionados beanTodosSelecionados) {
        this.beanTodosSelecionados = beanTodosSelecionados;
    }

    /**
     *
     * @deprecated @param pXHTML
     * @return
     */
    public String abrirXHTML(String pXHTML) {
        return pXHTML;
    }

    public Tema getTema() {

        if (tema == null) {
            return new Tema();
        }
        return tema;
    }

    /**
     *
     * Retorna a lista de cores padrão do sistema
     *
     * @return
     */
    public Cores getCores() {
        if (cores == null) {
            cores = new Cores();
        }
        return cores;
    }

    /**
     *
     * @return Data atual Do servidor em long
     */
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

    /**
     *
     * Atualiza uma area da pagina a partir de um id
     *
     * o caminho do id não precisa ser único, tudo que tiver este id será
     * atualizado
     *
     * @param idAtualizacao
     */
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

    /**
     *
     * Mostra uma mensagem no formato Aviso na tela do usuário
     *
     * @param pMensagem Mensagem que deseja exibir
     */
    public void enviaMensagem(String pMensagem) {
        FabMensagens.enviarMensagemUsuario(pMensagem, FabMensagens.AVISO);
        atualizaTelaPorID("mensagemUsuario");
    }

    /**
     *
     * @param titulo Titulo do Grupo
     * @param campos Campos relacionados
     * @return Um gropo de campos com os campos instanciados
     */
    public GrupoCampos gerarGrupoCamposEntidadePaginaAtual(String titulo, String... campos) {
        ItfPaginaGerenciarEntidade pagina;
        try {
            pagina = (ItfPaginaGerenciarEntidade) paginaAtual.getInfoPagina();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "A paginaatual não é do tipo Gerenciamento de Entidade, impossível determinar a entidade vinculada ao grupo", t);
            return new GrupoCampos("Grupo de Campos inreconhesível");
        }
        String entidade = pagina.getBeanDeclarado("entidadeSelecionada").getInfoBean().getClasse();
        return gerarGrupoCamposEntidade(entidade, titulo, campos);

    }

    /**
     *
     * @param entidade Entidade onde os Campos estão armazenados
     * @param titulo Título do Grupo
     * @param campos Camnpos
     * @return
     */
    public GrupoCampos gerarGrupoCamposEntidade(String entidade, String titulo, String... campos) {
        GrupoCampos grupoCampo = new GrupoCampos();
        try {
            for (String campo : campos) {
                grupoCampo.adicionarCampo(new CaminhoCampoReflexao(entidade + "." + campo));
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Não foi possível gerar um grup de campos com " + campos, t);
        }

        return grupoCampo;

    }

    /**
     *
     *
     *
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
     * @return O caminho complento ex:
     * componenteAvó.componentePai.componenteFilho
     */
    public String gerarCaminhoCompletoID(String pId) {
        if (pId == null) {
            return null;
        }
        if (pId.length() == 0) {
            return null;
        }
        if (pId.contains("@")) {
            return pId;
        }

        return UtilSBWP_JSFTools.getIDSCaminhoAbsoluto(pId);
    }

    /**
     *
     *
     *
     * @param pId
     * @deprecated Nome de método muito feio, será substituido por
     * gerarCaminhoCompletoID
     *
     * @return
     */
    public String makeCaminhoCompletoID(String pId) {

        return gerarCaminhoCompletoID(pId);
    }

    /**
     *
     * @return A URL inicial deste projeto
     */
    public String getEnderecoPagina() {
        return SBWebPaginas.getURLBase();
    }

    /**
     *
     * Copia todos os resources do WebApp atual para pasta de desenvolvimento do
     * projeto
     *
     */
    public void copiarRessource() {
        if (UtilSBCoreArquivos.copiarArquivos(UtilSBWP_JSFTools.getCaminhoLocalRessource(), SBCore.getCaminhoDesenvolvimento() + "/src/main/webapp/resources")) {
            FabMensagens.enviarMensagemUsuario("Arquivos da pasta Ressource copiados com sucesso", FabMensagens.AVISO);
        } else {
            FabMensagens.enviarMensagemUsuario("Aconteceu um erro ao copiar os resources", FabMensagens.ERRO);
        }
    }

    /**
     *
     * Função axiliar para eencomtrar p caminho completo do componente, atravez
     * do component.clientID,
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

    /**
     * @deprecated
     *
     * @param pId
     * @return
     */
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

    // Retorna true se o componente for um componente inpt do primefaces
    private boolean isComponentDeInput(UIComponent comp) {

        return comp.getRendererType().contains("org.primefaces.component.InputTextRenderer")
                || comp.getRendererType().contains("org.primefaces.component.SelectOneMenuRenderer")
                || comp.getRendererType().contains("Input")
                || comp.getRendererType().contains("ColorPicker")
                || comp.getRendererType().contains("Calendar")
                || comp.getRendererType().contains("CkEditor")
                || comp.getRendererType().contains("Slider")
                || comp.getRendererType().contains("Password")
                || comp.getRendererType().contains("select")
                || comp.getRendererType().contains("Select");
    }

    public String getNomeIdComponenteInput(UIComponent componente) {
        try {
            for (UIComponent comp : componente.getParent().getChildren()) {

                if (isComponentDeInput(comp)) {
                    return comp.getId();
                }
                for (UIComponent compNivel2 : comp.getChildren()) {
                    if (isComponentDeInput(compNivel2)) {
                        return compNivel2.getId();
                    }

                }
            }

            throw new UnsupportedOperationException();
        } catch (Throwable t) {

            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "erro obtendo id do input " + componente.getId(), t);

            for (UIComponent comp : componente.getParent().getChildren()) {

                if (isComponentDeInput(comp)) {
                    return comp.getId();
                }
                for (UIComponent compNivel2 : comp.getChildren()) {

                    System.out.println("Nivel 1: " + comp + " nivel 2: " + compNivel2);

                }
            }

            return null;
        }
    }

    public String getIDComponenteFilhoPorCordenada(UIComponent componente, int... indices) {
        if (componente == null) {
            System.out.println("Enviado componente nulo buscando id por cordenada");
            return null;
        }
        try {
            UIComponent componenteAtual = componente;

            for (int id : indices) {
                componenteAtual = componente.getChildren().get(id);
            }
            return componenteAtual.getId();
        } catch (Throwable t) {
            System.out.println("Inposível encontrar componente pela cordenada " + indices);
            return componente.getId();
        }
    }

    public String buscaIdDestaClasse(UIComponent componente, String atributo) {
        UIComponent comp = componente.getChildren().get(3).getChildren().get(1);

        System.out.println(comp.getId());
        System.out.println(comp.getAttributes().keySet());
        System.out.println(comp.getAttributes().values());
        System.out.println(comp.getFamily());
        System.out.println(comp.getRendererType());

        return "Ainda não Implmentado mas o id é:" + comp.getId();
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
                    System.out.println("O atributo:" + atributo + "de " + component.getId() + " foi preenchudo com " + valor);
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

    /**
     *
     * Retonrna a URL vinculada a ação (Está deprecated)
     *
     * @param pAcao Acao
     * @return XHTML que deve ser carregado
     * @deprecated Metodo será substituido por Carregar XHTML
     */
    @Deprecated
    public String navegar(ItfAcaoDoSistema pAcao) {

        if (pAcao != null) {
            return infoWeb.getAcaoComLink(pAcao).getUrlDeAcesso();
        } else {
            return null;
        }
    }

    /**
     *
     * redireciona o usuário da sessão para a pagina correspondente a ação
     * enviada no parametro
     *
     * @param pAcao Ação correspondente a URL desejada
     */
    public void irParaURL(ItfAcaoDoSistema pAcao) {
        try {

            if (!SBCore.isEmModoDesenvolvimento()) {
                if (pAcao == null) {
                    throw new UnsupportedOperationException("Ação não enviada para navegação de URL");
                }
                String url = infoWeb.getAcaoComLink(pAcao).getUrlDeAcesso();
                irParaURL(url);
            } else {
                System.out.println("Enviando usuario para url da anção" + pAcao.getNomeUnico());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "ação não enviada para navegação", t);
        }

    }

    /**
     *
     *
     *
     * @param pUrl Redireciona o usuário para uma nova URL
     */
    public void irParaURL(String pUrl) {

        if (!SBCore.isEmModoDesenvolvimento()) {
            UtilSBWP_JSFTools.vaParaPagina(pUrl);
        } else {
            System.out.println("Enviando usuário para" + pUrl);
        }

    }

    /**
     *
     * Exibe um campo não implementado (um campo com todas as propriedades de
     * campo, desvinculado a qualquer campo existente
     *
     * @return Um campo não implementado
     */
    public CampoNaoImplementado getCampoNaoImplementado() {
        return camponaoImplementado;
    }

    /**
     *
     * Função que retorna true caso todos os booleans enviados no parametros
     * sejam true. (Util para simplificar a utlização do JTDSL em alguns casos)
     *
     * @param pCondicoes Array de booleans para nálise
     * @return Vertadeiro se tudo for true
     */
    public boolean isTudoVerdadeiro(boolean... pCondicoes) {

        for (boolean condicao : pCondicoes) {
            if (!condicao) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @return Retorna um loren Ipsum de uma palavra
     */
    public String getLorrenIpsUmaPalavra() {
        return UtilSBCoreStrings.GetLorenIpsilum(1, UtilSBCoreStrings.TIPO_LOREN.PALAVRAS);
    }

    /**
     *
     * @return Retorna 5 palavras do lorenIpsum
     */
    public String getLorrenIpsUmaFrase() {
        return UtilSBCoreStrings.GetLorenIpsilum(3, UtilSBCoreStrings.TIPO_LOREN.PALAVRAS);
    }

    /**
     *
     * @return Um paragrafro de palavras LorenIpsum
     */
    public String getLorrenIpsUmParagrafo() {
        return UtilSBCoreStrings.GetLorenIpsilum(1, UtilSBCoreStrings.TIPO_LOREN.PARAGRAFO);
    }

    /**
     *
     * @return 3 paragrafos lorenIpsum
     */
    public String getLorrenIps3Paragrafos() {
        return UtilSBCoreStrings.GetLorenIpsilum(3, UtilSBCoreStrings.TIPO_LOREN.PARAGRAFO);
    }

    public String getSaudacao() {
        return UtilSBCoreComunicacao.getSaudacao();
    }

    public AcaoDeContexto getAcaoDeContexto(ItfAcaoDoSistema pAcao) {
        return new AcaoDeContexto(pAcao, FacesContext.getCurrentInstance(), paginaAtual.getInfoPagina());
    }

}
