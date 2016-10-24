/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import java.util.List;

/**
 *
 *
 *
 *
 * @author salvioF
 */
public interface ItfServicoVisualizacao {

    /**
     *
     * Retorna o xhtml padrão para exibição do item.
     *
     *
     * @param pEntidade
     * @return
     */
    public String getCaminhoXhtmlItemCard(Class pEntidade);

    /**
     *
     * @param pEntidade
     * @return
     */
    public String getCaminhoXhtmlItemCardLab(Class pEntidade);

    /**
     *
     * @param pEntidade
     * @param nomeAlternativo
     * @return
     */
    public String getCaminhoXhtmlItemAlternativo(Class pEntidade, String nomeAlternativo);

    /**
     *
     * @param pEntidade
     * @param nomeAlternativo
     * @return
     */
    public String getCaminhoXhtmlItemAlternativoLab(Class pEntidade, String nomeAlternativo);

    /**
     *
     * @param pEntidade
     * @return
     */
    public List<String> getTodasVisualizacoes(Class pEntidade);

    /**
     *
     * Retorna o XHTML padrão de acordo com a ação do sistema
     *
     * @param pAcao
     * @return
     */
    public String getCaminhoXhtmlAcaoDoSistema(ItfAcaoFormulario pAcao);

    /**
     *
     * Abre um formulário de gestão
     *
     * @param acaoForm
     */
    public void visualizarFormularioGestao(ItfAcaoGerenciarEntidade acaoForm);

    public ServicoVisualizacaoAbstrato.TIPOS_INTERFACES_COMUM_VISUALIZACAO getTipoVisualizacao();

    public String getCaminhoLocalPastaImagem();

    public String getRemotoPastaResource();

}
