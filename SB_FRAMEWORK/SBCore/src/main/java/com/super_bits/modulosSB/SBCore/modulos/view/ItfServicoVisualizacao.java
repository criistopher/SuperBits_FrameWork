/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;

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
    public String getCaminhoXhtmlItem(Class pEntidade);

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

    public ServicoVisualizacaoAbstrato.TIPOS_COMUM_VISUALIZACAO getTipoVisualizacao();

    public String getCaminhoLocalPastaImagem();

    public String getRemotoPastaImagem();

}
