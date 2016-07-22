/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Conversation;

/**
 *
 * @author Salvio
 */
public interface ItfB_Pagina {

    public void fecharPagina();

    public void abrePagina();

    public String getTitulo();

    public String getDescricao();

    public String getRecursoXHTML();

    public void setRecursoXHTML(String recursoXHTML);

    public List<String> getTags();

    public void addTag(String pTag);

    public String getNomeCurto();

    public String getLinkRotulo();

    public String getTagUsada();

    public String getUrlPadrao();

    public List<ParametroURL> getParametrosURL();

    public ParametroURL getParametroByNome(String pNome);

    public String getNomeParametroById(int pId);

    public Conversation getConversa();

    public String getXhtmlAcaoAtual();

    /**
     *
     * Identificação numérica da pagina, não precisa ser único (Criado para
     * utilizar em menus)
     *
     * @return
     */
    public int getId();

    public void setTagUsada(String tagUsada);

    public ParametroURL getParametrobyTipoEntidade(String nomeEntidade);

    /**
     *
     * @return @deprecated O controle de acesso agora é configurado pela
     * AcaoVinculada
     */
    @Deprecated
    public boolean isAcessoLivre();

    public AcaoGestaoEntidade getAcaoVinculada();

    /**
     *
     * Aplica valores de parametros passados por url no MB_pagina
     *
     * @param valorStringPorParametro
     */
    public void aplicaValoresDeParametrosModoDesenvolvimento(Map<String, String> valorStringPorParametro);

    public void executarAcaoSelecionada();

    /**
     *
     * @return Todas as ações declaradas no managed Bean
     */
    public List<ItfAcaoDoSistema> getAcoesDaPagina();

    /**
     *
     * @return A ação selecionada no momento
     */
    public ItfAcaoDoSistema getAcaoSelecionada();

}
