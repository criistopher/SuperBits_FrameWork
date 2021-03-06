/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ItfPaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_PaginaAtual;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.MB_SiteMapa;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap.ParametroURL;
import com.super_bits.modulosSB.webPaginas.controller.sessao.ControleDeSessaoWeb;
import com.super_bits.modulosSB.webPaginas.controller.sessao.SessaoAtualSBWP;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Salvio
 */
public class UtilSBWPServletTools {

    private enum pathBean {
        APLICACAO, SESSAO, REQUEST
    }

    /**
     *
     * Obtem o Bean por sua nomeação.
     *
     * Obs: Utiliza FaceContext para obter a instancia atual, ou seja, use
     * apenas dentro de MAnaged Bens, nunca direto no servelet
     *
     * @param pNomeBean
     * @param pClasse
     * @return
     */
    public static Object getBeanByNamed(String pNomeBean, Class pClasse) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context == null) {
                throw new UnsupportedOperationException("Impossivel determinar o FacesContext neste momento");
            }
            Object objeto = context.getApplication().evaluateExpressionGet(context, "#{" + pNomeBean + "}", pClasse);
            if (objeto == null) {
                throw new UnsupportedOperationException("Objeto via getEvaluteExpression retornou null");
            } else {
                return objeto;
            }
        } catch (Throwable t) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("erro Tentando obter objeto [" + pNomeBean + " ]de contexto injetado manualmento por evalutionExpressionGet", t);
            return null;
        }
    }

    public static HttpServletRequest getRequestAtual() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     *
     * Retorna a sessão atual via Faces Context
     *
     * @return
     */
    public static SessaoAtualSBWP getSessaoAtual() {
        try {
            if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                return new SessaoAtualSBWP();
            }
            SessaoAtualSBWP sessao = (SessaoAtualSBWP) getBeanByNamed("sessaoAtualSBWP", SessaoAtualSBWP.class);
            return sessao;
        } catch (Exception e) {

            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Tentando obter sesao atual por el", e);
            return null;
        }
    }

    /**
     *
     * retorna o controle de sessao via FacesContext
     *
     * @return
     */
    public static ControleDeSessaoWeb getControleDeSessaoWeb() {
        try {
            ControleDeSessaoWeb controle = (ControleDeSessaoWeb) getBeanByNamed("controleDeSessaoWeb", ControleDeSessaoWeb.class);
            return controle;
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Tentando obter controle de sessao por EL", e);
            return null;
        }
    }

    public MB_SiteMapa getSiteMap() {
        return null;
    }

    public MB_PaginaAtual getPaginaAtual() {
        try {
            ItfPaginaAtual controle = (ItfPaginaAtual) getBeanByNamed("paginaAtual", ControleDeSessaoWeb.class);
            return (MB_PaginaAtual) controle;
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro Tentando obter pagina Atual por EL", e);
            return null;
        }
    }

    public static Map<String, String> getParametrosDaAplicacao(String pURL) {
        return new HashMap<>();
    }

    public static String getIpCliente() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println("ip:" + ipAddress);
        if (ipAddress.equals("0:0:0:0:0:0:0:1")) {
            ipAddress = "127.0.0.1";
        }
        return ipAddress;
    }

    /**
     *
     * Retorna o caminho da pasta WebAPP onde a aplicação SERVLET está
     * instalada. Em caso de não encontrar, retorna o caminho da pasta de
     * desenvolvimento
     *
     * @return Retorna o caminho onde a aplicação SERVLET está instalada. Em
     * caso de não encontrar, retorna o caminho da pasta de desenvolvimento
     */
    public static String getCaminhoLocalServlet() {

        try {

            FacesContext contextoVisualizacaoAtual = FacesContext.getCurrentInstance();
            // String teste = context.getRealPath("/");

            if (contextoVisualizacaoAtual == null) {
                //TODO VA PARA VIEWEXPIRED
                //UtilSBWP_JSFTools.vaParaPaginaInicial();
                throw new UnsupportedOperationException("Contexto não pôde ser obtido utilizando FaceContex para determinar caminho local do servlet");
            }
            ExternalContext contextoExterno = contextoVisualizacaoAtual.getExternalContext();

            ServletContext scontext = (ServletContext) contextoExterno.getContext();

            return scontext.getRealPath("/");
        } catch (Exception e) {

            if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                System.out.println("UTILIZANDO PASTA RESOURCE DE DESENVOLVIMENTO." + e.getMessage() + e.getClass());
                return SBWebPaginas.getCaminhoWebAppDeveloper();
            } else {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve uma tentativa de obter o caminho local do Servlet fi FacesContext, e esta tentativa falhou!", e);

                return null;

            }
        }

    }

    /**
     *
     * @return Retorna o caminho da pasta resources do WEBApp, em caso de não
     * ser possível encontrar utiliza a pasta de desenvolvimento
     */
    public static String getCaminhoLocalServletsResource() {
        return getCaminhoLocalServlet() + "/resources";
    }

    public static String getCaminhoLocalWebAppServlet() {
        return getCaminhoLocalServlet() + "/resources";
    }

    /**
     *
     * Coloca um objeto no request Scoped, utiliza FacesContext para obter o
     * request
     *
     * @param pnome nome do Atributo
     * @param pObjeto Objeto que será amazenado
     */
    public static void putObjetoRequestScope(String pnome, Object pObjeto) {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        request.setAttribute(pnome, pObjeto);
    }

    /**
     *
     * Coloca um objeto no Session Scoped, utiliza FaceContext para obter o
     * request
     *
     * @param pnome nome do atributo
     * @param pObjeto Objeto que será armazenado
     */
    public static void putObjetoSessionScope(String pnome, Object pObjeto) {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        request.getSession().setAttribute(pnome, pObjeto);
    }

    /**
     *
     * @return
     */
    public static String listaMBView() {

        Map<String, Object> viewMap = FacesContext.getCurrentInstance()
                .getViewRoot().getViewMap();
        String resposta = "";
        for (Map.Entry<String, Object> viewBean : viewMap.entrySet()) {
            resposta = resposta.concat(viewBean.getKey());
        }
        return resposta;
    }

    /**
     *
     * Busca um objeto armazenado no request scoped atravez do nome do atributo
     *
     * @param pNomeBean nome do Atributo
     * @return Objeto armazenado
     */
    public static Object getRequestBean(String pNomeBean) {

        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            Object obj = request.getAttribute(pNomeBean);

            return obj;
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro tentando obter o bean RequestScoped:" + pNomeBean, e);
            return null;
        }
    }

    /**
     *
     * Procura um parametro que foi enviado em uma requisição e retorna seu
     * valor (Utiliza facesContext para obter a requisição)
     *
     * @param pNomeBean Nome do parametro
     * @return valor do parametro
     */
    public static Object getRequestParametro(String pNomeBean) {
        Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Object resposta = parametros.get(pNomeBean);
        if (resposta == null) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Parametro de requisição: " + pNomeBean + " não enviado", null);
        }
        return resposta;
    }

    /**
     *
     * Retorna o valor de um atributo de evento
     *
     * Os atributos de eventos são configurados através do <f:attribute dentro
     * de command Buttons
     *
     *
     * @param event
     * @param pNome
     * @return
     */
    public static ItfBeanSimples getActionBeanSimples(ActionEvent event, String pNome) {
        try {
            ItfBeanSimples resposta = (ItfBeanSimples) event.getComponent().getAttributes().get(pNome);
            if (resposta == null) {
                FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Atributo de Evento JSF não encontrado:" + pNome + " para o evento" + event.toString(), null);
            }
            return resposta;
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro ao converter o atributo de Evento JSF " + pNome + " para o evento" + event.toString(), null);
        }

        return null;

    }

    @Deprecated
    private static Object getBean(pathBean pathb, String pNomeBean) {

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        Map<String, Object> mapaBeans = null;
        switch (pathb) {
            case APLICACAO:

                mapaBeans = externalContext.getApplicationMap();
                Set<Map.Entry<String, Object>> appObjts = externalContext
                        .getApplicationMap().entrySet();

                for (Map.Entry<String, Object> registro : appObjts) {
                    String nome = registro.getKey();
                    Object beandeSessao = registro.getValue();
                    if (nome.endsWith(".spi.BeanManager")) {
                        BeanManager testeBM = (BeanManager) beandeSessao;

                        //     Bean bean = testeBM.gegetBeans("dados").iterator().next();
                        //      CreationalContext ctx = testeBM
                        //                .createCreationalContext(bean); // could be inlined
                        // below
                        return testeBM;
                        //          Object o = testeBM.get(, bean.getBeanClass(),
                        //                ctx);

                    }

                }

                break;
            case SESSAO:
                mapaBeans = externalContext.getSessionMap();
                break;
            case REQUEST:
                mapaBeans = externalContext.getRequestMap();
                break;

            default:
                break;
        }

        Set<Map.Entry<String, Object>> teste = mapaBeans.entrySet();

        for (Map.Entry<String, Object> registro : teste) {
            String nome = registro.getKey();

            if (nome.endsWith(pNomeBean)) {
                return registro.getValue();
            }

        }
        return mapaBeans.get(pNomeBean);

    }

    /**
     *
     * @param pNomeBean
     * @return
     * @deprecated Tenta obter um bean instanciado via CDI
     */
    @Deprecated
    public static Object getSessionBean(String pNomeBean) {
        return getBean(pathBean.SESSAO, pNomeBean);
    }

    /**
     *
     * @param pNomeBean
     * @return
     * @deprecated Tenta obter um bean instanciado via CDI
     */
    @Deprecated
    public static Object getAppBean(String pNomeBean) {
        return getBean(pathBean.APLICACAO, pNomeBean);

    }

    public static Object getBean2(String pNomeBean) {

        return FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get(pNomeBean);
    }

    /**
     *
     * @return a Url da requisição, utiliza FacesContext para obter a url
     */
    public static String getUrlDigitada() {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        origRequest.getRequestURL().toString();

        return (String) SBWebPaginas.getSiteHost() + origRequest.getAttribute("javax.servlet.forward.request_uri");
    }

    public static List<Field> getCamposReflexcaoInjetados(Class pClasse) {
        List<Field> camposInjetados = new ArrayList<>();

        for (Field campo : pClasse.getDeclaredFields()) {
            if (campo.getAnnotation(Inject.class) != null) {
                camposInjetados.add(campo);
            }
        }

        return camposInjetados;

    }

    /**
     * private static List<? extends Object> objetosInjetados(Class<?> pTipo,
     * Object pInstancia, Boolean modoOffline) {
     *
     * String nomeClasseProcurada = pTipo.getSimpleName(); List<Object>
     * objetosEncontrados = new ArrayList<Object>(); Class<?> classe =
     * pInstancia.getClass(); Field[] fields = classe.getDeclaredFields();
     * //pInstancia.getClass().getSuperclass();
     *
     * for (Field field : fields) { field.setAccessible(true); Constructor<?>[]
     * constr = field.getType().getConstructors();
     *
     * try { Inject injetado = field.getAnnotation(Inject.class); if (injetado
     * != null) {
     *
     * Boolean encontrou = false; Boolean classeObjeto = false; Object campo =
     * field.get(pInstancia);
     *
     * try { if (modoOffline) { campo = field.getType().newInstance(); }
     *
     * try { if (campo == null) { throw new ErroSBCritico("erro (chamada para
     * criar ObjetosInjetados Online com objetos não injetados)em " +
     * pTipo.getSimpleName() + ":: " + campo.getClass().getSimpleName()); }
     *
     * } catch (Exception e) {
     * UtilSBWP_JSFTools.mensagens().erroSistema(e.getMessage(), e); }
     *
     * } catch (InstantiationException e) { System.out.println("Erro tentando
     * Instanciar Um objeto nulo no BeanUtil getObjetosInjetados");
     * e.printStackTrace(); }
     *
     * if (campo != null) { Class<?> classeCampo = campo.getClass(); while
     * (encontrou == false && classeObjeto == false) {
     * System.out.println(classeCampo.getSimpleName()); if
     * (classeCampo.getSimpleName().equals(nomeClasseProcurada)) { encontrou =
     * true; } if (classeCampo.getSimpleName().equals("Object")) { classeObjeto
     * = true; } if (classeObjeto == false) { classeCampo =
     * classeCampo.getSuperclass(); } }
     *
     * }
     *
     * if (encontrou == true) {
     *
     * }
     *
     * if (encontrou == true & campo != null) { objetosEncontrados.add(campo); }
     *
     * }
     *
     * } catch (IllegalArgumentException | IllegalAccessException e1) {
     * FabErro.SOLICITAR_REPARO.paraDesenvolvedor("Erro obtendo objetos
     * injetados via CDI", e1); }
     *
     * }
     *
     * return objetosEncontrados; }
     */
    public static Map<String, ParametroURL> setValoresParametrosByUrl(Map<String, ParametroURL> pParametros) {
        for (String prStr : pParametros.keySet()) {
            pParametros.get(UtilSBCoreStrings.makeStrUrlAmigavel(prStr)).setValor(getRequestParametro(prStr));
        }

        return pParametros;
    }

}
