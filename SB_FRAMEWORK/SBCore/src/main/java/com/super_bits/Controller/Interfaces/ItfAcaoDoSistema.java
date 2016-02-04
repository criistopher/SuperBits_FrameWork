/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces;

import com.super_bits.Controller.fabricas.FabTipoAcaoParao;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author sfurbino
 */
public interface ItfAcaoDoSistema extends ItfBeanSimples {

    public String getNomeAcao();

    /**
     * ícone que representa a ação, os icones do answameFonts devem ser
     * configurados com um fa espaço, o nome do icone, ex: [fa fa-iconeLegal]
     *
     * @return icone da ação
     */
    public String getIconeAcao();

    /**
     *
     * Em alguns casos as açoes devem conter cores e os valores devem ser
     * setados em RGB Hexadecimal
     *
     * @return Cor da ação
     */
    public String getCor();

    /**
     *
     * Descrição detalhada da ação
     *
     * @return uma descrição detalhada da ação
     */
    public String getDescricao();

    /**
     *
     * Caso esta ação precise de um formulário para ser executado, o XHTML é
     * configurado aqui.
     *
     * @return XHTML vinculado a ação
     */
    public String getXHTMLAcao();

    /**
     *
     * O modulo da ação só é setado direto na ação no caso dela não conter uma
     * ação pai.
     *
     * Caso tenha um pai, o getModulo, deve retornar o modulo do pai ou "avô" e
     * assim adiante..
     *
     * @return o modulo da ação
     */
    public ItfModuloAcaoSistema getModulo();

    /**
     *
     * Em geral, o ID do método é o nome da classe Controller.nomedoMetodo
     *
     * @return o Id do método
     */
    public int getIdMetodo();

    /**
     *
     * Indica se para executar esta ação é nescessário o administrador do
     * sistema conceder autorização
     *
     * @return
     */
    public boolean isPrecisaPermissao();

    public void setId(int pId);

    /**
     *
     * ícone que representa a ação, os icones do answameFonts devem ser
     * configurados com um fa espaço, o nome do icone, ex: [fa fa-iconeLegal]
     *
     * @param pIcone
     */
    public void setIcone(String pIcone);

    /**
     *
     * O Id do método retorna um hash do método vinculoado a esta ação na camada
     * controller
     *
     * @param pID (Hash do método vinculado a ação)
     */
    public void setIdMetodo(int pID);

    /**
     *
     *
     * Indica o modulo da ação (este campo só é setado diretamente em uma ação
     * quando uma ação pai é igual a nulo (caso contŕario a ação do pai é
     * retornada no get...
     *
     *
     * @param pmodulo O modulo que a ação pertence
     */
    public void setModuloAcaoSistema(ItfModuloAcaoSistema pmodulo);

    /**
     *
     * Indica que esta ação não precisa de um formulário para ser executada
     *
     * @return
     */
    public boolean isTipoAcaoDireta();

    /**
     *
     * Indica que uma ação é uma ação efetiva, e não uma simples sessão
     *
     * embora ações e sessoes de menu implementem esta inteface, ambas são
     * classes diferentes, sento AcaoDoSistema e outra SessaoMenuSB
     *
     * @return REtorna true se o tipo de classe que implementa esta interface é
     * uma AcaoDoSistema
     */
    public boolean isTipoAcao();

    /**
     *
     * Uma ação do tipo sessão menu é uma ação apenas de agrupamento de ações.
     *
     * embora ações e sessoes de menu implementem esta inteface, ambas são
     * classes diferentes, sento AcaoDoSistema e outra SessaoMenuSB
     *
     * @return retorna true se o tipo da classe que implementa esta interface
     * for do tipo SessaoMenu
     */
    public boolean isTipoSessaoMenu();

    /**
     *
     * Indica se a ação foi criada apenas com constructor ou configurada com
     * icones e nomes específicos
     *
     * @return
     */
    public boolean isConfigurado();

    public ItfAcaoDoSistema getAcaoPrincipal();

    public void validarAcao(boolean pValidarSeNaoConfigurado);

    public boolean isUmaAcaoPrincipal();

    /**
     *
     * Indica se é uma ação principal (que não precisa de ação pai)
     *
     * @param pisAcaoPrincipal
     * @return
     */
    public void setIsAcaoPrincipal(Boolean pisAcaoPrincipal);

    /**
     *
     * Configura a ação principal desta -sub-ação
     *
     * @param pAcaoPrincipal Ação principal da subAcao
     */
    public void setAcaoPrincipal(ItfAcaoDoSistema pAcaoPrincipal);

    /**
     *
     * @return Uma identificação única para esta ação
     */
    public String getNomeUnico();

    /**
     *
     * @return String representando o enum que gerou a ação
     */
    public String getNomeEnumOriginal();

    /**
     * Tipo de ação é um tipo de ação conhecida do sistema
     *
     * @return
     */
    public FabTipoAcaoParao getTipoAcao();

}
