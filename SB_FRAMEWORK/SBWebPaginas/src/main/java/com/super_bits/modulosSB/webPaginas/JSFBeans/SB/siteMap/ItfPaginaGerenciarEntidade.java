/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import java.util.List;

/**
 *
 * @author cristopher
 */
public interface ItfPaginaGerenciarEntidade<T> {

    ItfAcaoFormularioEntidade getAcaoListarRegistros();

    // Retorna ação de novo registro
    ItfAcaoFormularioEntidade getAcaoNovoRegistro();

    ItfAcaoDoSistema getAcaoSalvarAlteracoes();

    ItfAcaoDoSistema getAcaoSelecionada();

    List<ItfAcaoDoSistema> getAcoesRegistros();

    T getEntidadeSelecionada();

    List<T> getEntidadesListadas();

    String getXhtmlAcaoAtual();

    boolean isNovoRegistro();

    boolean isPodeEditar();

    // Define a ação selecionada
    void setAcaoSelecionada(ItfAcaoDoSistema acaoSelecionada);

    void setEntidadeSelecionada(T entidadeSelecionada);

    void setEntidadesListadas(List<T> entidadesListadas);

    public void executarAcao(T pCompradorSelecionado);

    public void listarDados();

    public boolean isTemPesquisa();

    public ItfAcaoFormularioEntidade getAcaoVisualisar();

    public ItfAcaoFormularioEntidade getAcaoAlterarStatus();

    public ItfAcaoFormularioEntidade getAcaoEditar();

}
