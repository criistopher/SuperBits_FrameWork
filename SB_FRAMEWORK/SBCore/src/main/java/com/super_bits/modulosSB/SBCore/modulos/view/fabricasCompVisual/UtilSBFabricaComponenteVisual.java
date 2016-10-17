/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBFabricaComponenteVisual {

    private static void loadInfoFabrica(ComponenteVisualSB componente, InfoComponenteVisual infoAnotacao, String pastaBase) {
        componente.setNome(infoAnotacao.nome());
        componente.setDescricao(infoAnotacao.descricao());
        componente.setClasseCSS(infoAnotacao.classesCSS());
        componente.setXhtmlJSF(pastaBase + infoAnotacao.xhtmlJSF());
        componente.setXhtmlAndroid(pastaBase + infoAnotacao.xhtmlAndroi());
        componente.setHtmlWordPress(pastaBase + infoAnotacao.htmlWordPress());
        componente.setDescricao(infoAnotacao.descricao());
        componente.setFamilia(FabFamiliaCompVisual.MENU);
        componente.setXhtmlJSF(pastaBase + infoAnotacao.xhtmlJSF());
        componente.setXhtmlAndroid(pastaBase + infoAnotacao.xhtmlAndroi());
        componente.setHtmlWordPress(pastaBase + infoAnotacao.htmlWordPress());
        componente.setXhtmlJsfCaminhoRelativo(infoAnotacao.xhtmlJSF());
        if (infoAnotacao.idHTMLObjetoPrincipal().isEmpty()) {
            componente.setIdHTMLObjetoPrincipal(infoAnotacao.classesCSS());
        }

    }

    public static ComponenteVisualSB getComponenteVisual(ItfFabTipoComponenteVisual pFabrica) {
        ComponenteVisualSB componente = new ComponenteVisualSB();
        try {
            Field campo = pFabrica.getClass().getField(pFabrica.toString());
            InfoComponenteVisual infoAnotacao = campo.getAnnotation(InfoComponenteVisual.class);
            String codigoId = pFabrica.getFamilia().ordinal() + String.valueOf(((Enum) pFabrica).ordinal());
            componente.setId(Integer.parseInt(codigoId));
            componente.setIdHTMLObjetoPrincipal(codigoId);
            loadInfoFabrica(componente, infoAnotacao, ItfFabTipoComponenteVisual.PASTA_TAG_LIBS);
            componente.setFamilia(pFabrica.getFamilia());
            componente.setIdHTMLObjetoPrincipal(infoAnotacao.idHTMLObjetoPrincipal());

        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(UtilSBFabricaComponenteVisual.class.getName()).log(Level.SEVERE, null, ex);
        }

        return componente;

    }

    public static ComponenteVisualSB getComponenteVisualPersonalizado(ItfFabTipoComponenteVisual pFabrica) {
        ComponenteVisualSB componente = new ComponenteVisualSB();
        try {

            Field campo = pFabrica.getClass().getField(pFabrica.toString());
            try {
                Method ordinal = pFabrica.getClass().getMethod("ordinal");
                InfoComponenteVisual infoAnotacao = campo.getAnnotation(InfoComponenteVisual.class);
                loadInfoFabrica(componente, infoAnotacao, "");
                componente.setId((int) ordinal.invoke(pFabrica));
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(UtilSBFabricaComponenteVisual.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(UtilSBFabricaComponenteVisual.class.getName()).log(Level.SEVERE, null, ex);
        }

        return componente;

    }

}
