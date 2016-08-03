/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public interface ItfComponenteVisualSB {

    /**
     *
     *
     *
     *
     * @return Caminho relativo para acessar o ID
     */
    String getCaminhoIdHTMLObjetoPrincipal();

    /**
     *
     *
     * @return Descreve em palavras o formidabilidade de um componente
     */
    String getDescricao();

    /**
     *
     * @return Retorna a família do componente
     */
    FabFamiliaCompVisual getFamilia();

    /**
     *
     * @return o Html para utilização do compoenente no Wordpress
     */
    String getHtmlWordPress();

    /**
     *
     * @return O componente em uma palavra, sucinta sintética precisa e lacônico
     */
    String getNomeComponente();

    /**
     *
     * @return Os parametros nescessários para exibição do componente
     */
    List<Object> getParametros();

    /**
     *
     * @return O caminho para exibição deste componente no android
     */
    String getXhtmlAndroid();

    /**
     *
     * @return O caminho para o xhtml desde compoenente para JSF
     */
    String getXhtmlJSF();

}
