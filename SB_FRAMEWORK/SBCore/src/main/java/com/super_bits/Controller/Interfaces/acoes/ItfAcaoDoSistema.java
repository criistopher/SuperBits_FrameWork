/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.Interfaces.acoes;

import com.super_bits.Controller.Interfaces.ItfModuloAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.ItfBeanSimples;

/**
 *
 * @author sfurbino
 */
public interface ItfAcaoDoSistema extends ItfBeanSimples {

    public void setNomeAcao(String pNome);

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

    public void setDescricao(String pDescricao);

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
    public void setIconeAcao(String pIcone);

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

    public ItfModuloAcaoSistema getModulo();

    /**
     *
     * Indica se a ação foi criada apenas com constructor ou configurada com
     * icones e nomes específicos
     *
     * @return
     */
    public boolean isConfigurado();

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
    public FabTipoAcaoSistema getTipoAcaoSistema();

    public boolean isTemAcaoPrincipal();

    public void configurarPropriedadesBasicas(ItfAcaoDoSistema pAcaoDoSistema);

    public boolean isAcaoFormulario();

    public String getIdDescritivoJira();

    public void setIdDescritivoJira(String pIdJira);

    public void setPrecisaPermissao(boolean pPermissao);

    public FabTipoAcaoSistemaGenerica getTipoAcaoGenerica();

    public boolean isUmaAcaoGenerica();

    public boolean isAcaoGestaoDominio();
}
