/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import java.util.List;

/**
 *
 * @author cristopher
 */
public interface ItfPaginaGerenciarEntidade<T> extends ItfB_Pagina {

    public ItfAcaoFormularioEntidade getAcaoListarRegistros();

    // Retorna ação de novo registro
    public ItfAcaoFormularioEntidade getAcaoNovoRegistro();

    public ItfAcaoDoSistema getAcaoSalvarAlteracoes();

    public ItfAcaoDoSistema getAcaoSelecionada();

    public List<ItfAcaoDoSistema> getAcoesRegistros();

    public T getEntidadeSelecionada();

    public List<T> getEntidadesListadas();

    public String getXhtmlAcaoAtual();

    public boolean isNovoRegistro();

    public boolean isPodeEditar();

    public boolean isTemVisualizar();

    public boolean isTemNovo();

    public boolean isTemEditar();

    public boolean isTemAlterarStatus();

    public void setAcaoSelecionada(ItfAcaoDoSistema acaoSelecionada);

    public void setEntidadeSelecionada(T entidadeSelecionada);

    public void setEntidadesListadas(List<T> entidadesListadas);

    public void executarAcao(T pCompradorSelecionado);

    public void listarDados();

    public boolean isTemPesquisa();

    public ItfAcaoFormularioEntidade getAcaoVisualisar();

    public ItfAcaoControllerEntidade getAcaoAlterarStatus();

    public ItfAcaoFormularioEntidade getAcaoEditar();

}
