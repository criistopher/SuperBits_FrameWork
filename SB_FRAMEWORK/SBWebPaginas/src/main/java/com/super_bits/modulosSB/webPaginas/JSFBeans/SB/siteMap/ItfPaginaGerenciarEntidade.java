/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
import java.util.List;

/**
 *
 * @author cristopher
 */
public interface ItfPaginaGerenciarEntidade<T> {

    AcaoDoSistema getAcaoListarRegistros();

    // Retorna ação de novo registro
    AcaoDoSistema getAcaoNovoRegistro();

    AcaoDoSistema getAcaoSalvarAlteracoes();

    AcaoDoSistema getAcaoSelecionada();

    List<AcaoDoSistema> getAcoesRegistros();

    T getEntidadeSelecionada();

    List<T> getEntidadesListadas();

    String getXhtmlAcaoAtual();

    boolean isNovoRegistro();

    boolean isPodeEditar();

    // Define a ação selecionada
    void setAcaoSelecionada(AcaoDoSistema acaoSelecionada);

    void setEntidadeSelecionada(T entidadeSelecionada);

    void setEntidadesListadas(List<T> entidadesListadas);

    public void executarAcao(T pCompradorSelecionado);

    public void listarDados();

    public boolean isTemPesquisa();

    public ItfAcaoDoSistema getAcaoVisualisar();

    public ItfAcaoDoSistema getAcaoAlterarStatus();

    public ItfAcaoDoSistema getAcaoEditar();

}
