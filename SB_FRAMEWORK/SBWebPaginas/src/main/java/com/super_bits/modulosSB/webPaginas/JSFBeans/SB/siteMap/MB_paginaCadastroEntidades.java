/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
import java.util.ArrayList;
import java.util.List;

/**
 * ATENÇÃO A DOCUMENTAÇÃO DA CLASSE É OBRIGATÓRIA O JAVADOC DOS METODOS PUBLICOS
 * SÃO OBRIGATÓRIOS E QUANDO EXISTIR UMA INTERFACE DOCUMENTADA UMA REFERENCIA
 * DEVE SER CRIADA, A SINTAXE DE REFERENCIA É: @see_ NomeDAClasse#Metodo
 * DOCUMENTE DE FORMA OBJETIVA E EFICIENTE, NÃO ESQUEÇA QUE VOCÊ FAZ PARTE DE
 * UMA EQUIPE.
 *
 * @author <a href="mailto:salviof@gmail.com">Salvio Furbino</a>
 * @since 26/12/2015
 * @version 1.0
 *
 */
public abstract class MB_paginaCadastroEntidades<T> extends MB_PaginaConversation implements ItfPaginaGerenciarEntidade<T> {

    private T entidadeSelecionada;
    private List<T> entidadesListadas;

    private boolean temPesquisa;

    private final List<AcaoDoSistema> acoesRegistros;
    protected final AcaoDoSistema acaoListarRegistros;
    protected final AcaoDoSistema acaoNovoRegistro;
    protected final AcaoDoSistema acaoSalvarAlteracoes;

    public enum estadoEdicao {

        ALTERAR, CRIAR, VISUALIZAR
    }

    protected AcaoDoSistema acaoSelecionada;
    protected boolean podeEditar;
    protected boolean novoRegistro;
    protected String xhtmlAcaoAtual;

    public abstract void executarAcao(T pEntidadeSelecionada);

    /**
     *
     * @param pAcoesRegistro Array de ações para cada registro ex.(new
     * AcaoDoSistema[]{Fabrica.acao.getAcaoDoSistema,Fabrica.acao2.getacaoDoSistema})
     * @param pAcaoNovoRegistro Ação para um novo registro
     * @param pAcaoListar Ação para listar os registros
     * @param pAcaoSalvar Ação para Salvar alterações
     * @param pTempesquisa Informa se vai haver pesquisa na tela de
     * gerenciamento
     *
     */
    public MB_paginaCadastroEntidades(AcaoDoSistema[] pAcoesRegistro,
            AcaoDoSistema pAcaoNovoRegistro,
            AcaoDoSistema pAcaoListar,
            AcaoDoSistema pAcaoSalvar,
            boolean pTempesquisa
    ) {
        super();
        acoesRegistros = new ArrayList<>();
        for (AcaoDoSistema acao : pAcoesRegistro) {
            acoesRegistros.add(acao);
        }
        acaoNovoRegistro = pAcaoNovoRegistro;
        acaoListarRegistros = pAcaoListar;
        acaoSalvarAlteracoes = pAcaoSalvar;
        acaoSelecionada = acaoListarRegistros;
        xhtmlAcaoAtual = acaoListarRegistros.getXHTMLAcao();

        entidadesListadas = new ArrayList<>();

        temPesquisa = pTempesquisa;

    }

    private void iniciaNovoRegistro() {
        novoRegistro = true;
        podeEditar = true;
    }

    private void iniciaEdicao() {
        novoRegistro = false;
        podeEditar = true;
    }

    protected void iniciaVisualizacao() {
        novoRegistro = false;
        podeEditar = false;
    }

    /**
     * Configura as propriedades pode editar, e novo registro, de acordo com a
     * opção selecionada
     *
     * @param pEstadoEdicao
     */
    protected void atualizaInformacoesDeEdicao(estadoEdicao pEstadoEdicao) {

        switch (pEstadoEdicao) {
            case ALTERAR:
                iniciaEdicao();

                break;
            case CRIAR:
                iniciaNovoRegistro();
                break;
            case VISUALIZAR:
                podeEditar = false;
                novoRegistro = false;
                break;
            default:
                throw new AssertionError(pEstadoEdicao.name());

        }

    }

    @Override
    public List<AcaoDoSistema> getAcoesRegistros() {
        return acoesRegistros;
    }

    // Retorna ação de novo registro
    @Override
    public AcaoDoSistema getAcaoNovoRegistro() {
        return acaoNovoRegistro;
    }

    @Override
    public AcaoDoSistema getAcaoListarRegistros() {
        return acaoListarRegistros;
    }

    @Override
    public boolean isPodeEditar() {
        return podeEditar;
    }

    @Override
    public boolean isNovoRegistro() {
        return novoRegistro;
    }

    @Override
    public AcaoDoSistema getAcaoSelecionada() {
        return acaoSelecionada;
    }

    // Define a ação selecionada
    @Override
    public void setAcaoSelecionada(AcaoDoSistema acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    @Override
    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    @Override
    public AcaoDoSistema getAcaoSalvarAlteracoes() {
        return acaoSalvarAlteracoes;
    }

    @Override
    public T getEntidadeSelecionada() {
        return entidadeSelecionada;
    }

    @Override
    public void setEntidadeSelecionada(T entidadeSelecionada) {
        this.entidadeSelecionada = entidadeSelecionada;
    }

    @Override
    public List<T> getEntidadesListadas() {
        return entidadesListadas;
    }

    @Override
    public void setEntidadesListadas(List<T> entidadesListadas) {
        this.entidadesListadas = entidadesListadas;
    }

    @Override
    public boolean isTemPesquisa() {
        return temPesquisa;
    }

    @Override
    public ItfAcaoDoSistema getAcaoEditar() {
        for (ItfAcaoDoSistema acao : acoesRegistros) {
            if (acao.getTipoAcaoSistema() != null) {
                if (acao.getTipoAcaoSistema().equals(FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR)) {
                    return acao;
                }
            }
        }
        throw new UnsupportedOperationException("a ação de editar ão foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR + " nas ações de registro configuradas no constructor da pagina");
    }

    @Override
    public ItfAcaoDoSistema getAcaoAlterarStatus() {
        for (ItfAcaoDoSistema acao : acoesRegistros) {
            if (acao.getTipoAcaoSistema() != null) {
                if (acao.getTipoAcaoSistema().equals(FabTipoAcaoSistemaGenerica.ATIVAR_DESATIVAR)) {
                    return acao;
                }
            }

        }
        throw new UnsupportedOperationException("a ação de AticarEDesativar não foi encontrada");
    }

    @Override
    public ItfAcaoDoSistema getAcaoVisualisar() {
        for (ItfAcaoDoSistema acao : acoesRegistros) {
            if (acao.getTipoAcaoSistema() != null) {
                if (acao.getTipoAcaoSistema().equals(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR)) {
                    return acao;
                }
            }

        }
        throw new UnsupportedOperationException("a ação de Visualizar não foi encontrada");
    }

}
