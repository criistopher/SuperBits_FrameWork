/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvedor
 */
public class UtilSBFabricaComponenteVisual {

    public static ComponenteVisualSB getComponenteVisual(ItfFabTipoComponenteVisual pFabrica) {
        ComponenteVisualSB componente = new ComponenteVisualSB();
        try {
            Field campo = pFabrica.getClass().getField(pFabrica.toString());
            InfoComponenteVisual infoAnotacao = campo.getAnnotation(InfoComponenteVisual.class);

            componente.setNome(infoAnotacao.nome());
            componente.setDescricao(infoAnotacao.descricao());

            String codigoId = pFabrica.getFamilia().ordinal() + String.valueOf(((Enum) pFabrica).ordinal());
            componente.setId(Integer.parseInt(codigoId));
            componente.setDescricao(infoAnotacao.descricao());
            componente.setFamilia(FabFamiliaCompVisual.MENU);
            componente.setCaminhoIdHTMLObjetoPrincipal(codigoId);
            componente.setXhtmlJSF(ItfFabTipoComponenteVisual.PASTA_TAG_LIBS + infoAnotacao.xhtmlJSF());
            componente.setXhtmlAndroid(ItfFabTipoComponenteVisual.PASTA_TAG_LIBS + infoAnotacao.xhtmlAndroi());
            componente.setHtmlWordPress(ItfFabTipoComponenteVisual.PASTA_TAG_LIBS + infoAnotacao.htmlWordPress());
            componente.setFamilia(pFabrica.getFamilia());
            componente.setCaminhoIdHTMLObjetoPrincipal(infoAnotacao.caminhoIdHTMLObjetoPrincipal());

        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(UtilSBFabricaComponenteVisual.class.getName()).log(Level.SEVERE, null, ex);
        }

        return componente;

    }

}
