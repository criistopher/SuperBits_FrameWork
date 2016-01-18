/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.util;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.ConfigGeral.SBWebPaginas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Salvio
 */
public abstract class UtilSBWP_JSFTools {

    /**
     * Retorna o caminho absoluto do componente exemplo:
     * :ComponenteAvô:componentePai:componenteFilho.
     *
     * @param componente com nome simples
     * @return The absolute path of the given component
     */
    private static String getAbsoluteComponentPath(UIComponent currentComponent) {
        final char separatorChar = UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance());

        String path = "";
        if (!(currentComponent instanceof NamingContainer)) {
            path = currentComponent.getId();
        }

        do {
            if (currentComponent instanceof NamingContainer) {
                path = currentComponent.getId() + (!path.isEmpty() ? separatorChar : "") + path;
            }
            currentComponent = currentComponent.getParent();
        } while (currentComponent != null);
        path = separatorChar + path;

        return path;
    }

    /**
     * Retorna o caminho absoluto de cada ID encontrado separado por espaço
     *
     * @param components The list of components
     * @return A whitespace-separated list of all absolute paths.
     * @see ComponentResolver#getAbsoluteComponentPath(UIComponent)
     */
    private static String getAbsoluteComponentPaths(Collection<UIComponent> components) {
        String paths = "";
        for (UIComponent c : components) {

            if (!paths.isEmpty()) {
                paths += " ";
            }
            paths += getAbsoluteComponentPath(c);
        }

        return paths;
    }

    /**
     * Localiza todos os ids dos componentes da pagina, e retorna todos eles
     * separado por espaço (e.g.
     * <code>:j_id1:j_id2:myId :j_id1:j_id3:myId</code>).
     *
     * @param id O id do componente procurado
     * @return Lista separada por espaço com o caminho absoluto dos componentes
     * que contem este nome
     * @see #resolveList(String)
     */
    public static String getIDSCaminhoAbsoluto(String pId) {
        List<UIComponent> components = resolveList(pId);

        if (components.size() == 0) {
            mensagens().erroSistema("Nenhum component com a nomeação de ID[" + pId + "] foi encontrado");
        }

        return getAbsoluteComponentPaths(components);
    }

    /**
     * Localiza todos os compoentes com esta ID
     *
     * @param id Id to search for.
     * @return List of components that have got this id.
     */
    private static List<UIComponent> resolveList(String id) {
        return resolveList(id, FacesContext.getCurrentInstance().getViewRoot());
    }

    /**
     * Percorre toda arvore de componentes buscando pelo ID informado
     *
     * @param id Localizado
     * @param currentComponent Arvore inicial onde o componente será localizado
     * @return Accumulation of all components that match the given id
     */
    private static List<UIComponent> resolveList(String id, UIComponent currentComponent) {
        List<UIComponent> accumulator = new LinkedList<UIComponent>();
        //   System.out.println("procurando por" + id + "em:" + currentComponent.getId() + currentComponent.getId());
        if (null != currentComponent.getId() && currentComponent.getId().equals(id)) {

            accumulator.add(currentComponent);
        }

        Iterator<UIComponent> childIt = currentComponent.getFacetsAndChildren();
        while (childIt.hasNext()) {
            UIComponent child = childIt.next();
            accumulator.addAll(resolveList(id, child));
        }

        return accumulator;

    }

    public static void atualizaPorId(String pId) {
        try {

            if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
                SBCore.enviarMensagemUsuario("Atualizando id da pagina" + pId, FabMensagens.AVISO);
                return;
            }
            // System.out.println("iniciando Resolver");

            try {
                RequestContext context = RequestContext.getCurrentInstance();

                String caminhoCompleto = getIDSCaminhoAbsoluto(pId);
                //   System.out.println("resolver full=" + caminhoCompleto);

                String[] camadas = caminhoCompleto.split(" ");
                List<String> lista = new ArrayList<String>();

                if (camadas != null || camadas.length > 0) {
                    for (String comp : camadas) {
                        if (comp.length() > 0) {
                            lista.add(comp.substring(1, comp.length()));
                        }
                    }
                } else {
                    lista.add(caminhoCompleto);
                }
                //   System.out.println("ATUALIZANDO" + lista);

                context.update(lista);
            } catch (UnsupportedOperationException e) {
                System.out.println("Request contexto não encontrado, não é possível atualizar a tela");
                return;
            }
        } catch (Exception e) {
            FabErro.SOLICITAR_REPARO.paraDesenvolvedor("erro tentando atualizar ID de componente JSF" + pId, e);
        }

    }

    public static CentralDeMensagensJSFAPP mensagens() {

        return new CentralDeMensagensJSFAPP();
    }

    public static void vaParaPagina(String pURL) {

        try {

            //	FacesContext.getCurrentInstance().getExternalContext().responseReset();
            FacesContext.getCurrentInstance().getExternalContext().redirect(pURL);

            //Timer t=Timer.getInstance();
            FacesContext.getCurrentInstance().responseComplete();
            FacesContext.getCurrentInstance().renderResponse();
            //	NavigationHandler myNav = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            //    myNav.handleNavigation(FacesContext.getCurrentInstance(),null, pURL);

        } catch (Exception e) {
            mensagens().alertaSistema("erro tentando redirecionar pagina" + e.getMessage() + pURL);
            e.printStackTrace();
        }
    }

    public static void carregaPagina(String pXHTML) {
        try {

            FacesContext.getCurrentInstance().getExternalContext().dispatch(pXHTML);

        } catch (IOException e) {
            mensagens().alertaSistema("erro tentando obter o contexto para redirecionamento de pagina" + pXHTML);
            e.printStackTrace();
        }
    }

    public static void vaParaPaginadeErro(String Mensagem) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(SBWebPaginas.getSiteURL() + "/resources/SBComp/SBSystemPages/tagPageNotFound.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void vaParaPaginaInicial() {

    }

    public static void vaParaPaginadeLogin() {

    }

    public static String getCaminhoLocalRessource() {

        return UtilSBWPServletTools.getCaminhoLocalServlet() + "/resources";
    }

    public static void executarJavaScript(String pcomando) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(pcomando);

    }

}
