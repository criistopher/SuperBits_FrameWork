/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
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
public abstract class MB_paginaCadastroEntidades extends MB_PaginaConversation {

    private final List<AcaoDoSistema> acoesRegistros;

    protected final AcaoDoSistema acaoListarRegistros;
    protected final AcaoDoSistema acaoNovoRegistro;
    protected final AcaoDoSistema acaoEditarRegistros;
    protected final AcaoDoSistema acaoVisualizarRegistros;
    protected AcaoDoSistema acaoSelecionada;
    protected boolean podeEditar;
    protected boolean novoRegistro;
    protected String xhtmlAcaoAtual;

    public MB_paginaCadastroEntidades(AcaoDoSistema[] pAcoesRegistro, AcaoDoSistema pAcaoNovoRegistro, AcaoDoSistema pAcaoListar, AcaoDoSistema pAcaoEditar, AcaoDoSistema pAcaoVisualizar) {
        super();
        acoesRegistros = new ArrayList<>();
        for (AcaoDoSistema acao : pAcoesRegistro) {
            acoesRegistros.add(acao);
        }
        acaoNovoRegistro = pAcaoNovoRegistro;
        acaoListarRegistros = pAcaoListar;
        acaoEditarRegistros = pAcaoEditar;
        acaoVisualizarRegistros = pAcaoVisualizar;
    }

    private void iniciaNovoRegistro() {
        novoRegistro = true;
        podeEditar = true;
    }

    private void iniciaEdicao() {
        novoRegistro = false;
        podeEditar = true;
    }

    private void iniciaVisualizacao() {
        novoRegistro = false;
        podeEditar = false;
    }

    /**
     * Configura as propriedades pode editar, e novo registro, de acordo com a
     * opção selecionada
     */
    protected void atualizaInformacoesDeEdicao() {

        if (acaoSelecionada != null) {
            if (acaoSelecionada.getXHTMLAcao() != null) {
                xhtmlAcaoAtual = acaoSelecionada.getXHTMLAcao();
            }

            if (acaoSelecionada == acaoEditarRegistros) {
                iniciaEdicao();
            }
            if (acaoSelecionada == acaoNovoRegistro) {
                iniciaNovoRegistro();
            }
            if (acaoSelecionada == acaoVisualizarRegistros) {
                iniciaVisualizacao();
            }
        }
    }

    protected abstract void persistirNovoRegistro();

    protected abstract void excluirNovoRegistro();

    public List<AcaoDoSistema> getAcoesRegistros() {
        return acoesRegistros;
    }

    public AcaoDoSistema getAcaoNovoRegistro() {
        return acaoNovoRegistro;
    }

    public AcaoDoSistema getAcaoListarRegistros() {
        return acaoListarRegistros;
    }

    public boolean isPodeEditar() {
        return podeEditar;
    }

    public boolean isNovoRegistro() {
        return novoRegistro;
    }

    public AcaoDoSistema getAcaoSelecionada() {
        return acaoSelecionada;
    }

    public void setAcaoSelecionada(AcaoDoSistema acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

}
