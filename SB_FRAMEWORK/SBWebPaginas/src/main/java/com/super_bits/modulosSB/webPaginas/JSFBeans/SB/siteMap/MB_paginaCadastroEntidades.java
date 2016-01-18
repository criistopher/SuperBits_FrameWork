/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.PgUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

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
    protected final AcaoDoSistema acaoSalvarAlteracoes;
    @Inject
    protected PgUtil paginaUtil;

    public enum estadoEdicao {

        ALTERAR, CRIAR, VISUALIZAR
    }

    protected AcaoDoSistema acaoSelecionada;
    protected boolean podeEditar;
    protected boolean novoRegistro;
    protected String xhtmlAcaoAtual;

    /**
     *
     * @param pAcoesRegistro Array de ações para cada registro ex.(new
     * AcaoDoSistema[]{Fabrica.acao.getAcaoDoSistema,Fabrica.acao2.getacaoDoSistema})
     * @param pAcaoNovoRegistro Ação para um novo registro
     * @param pAcaoListar Ação para listar os registros
     * @param pAcaoSalvar Ação para Salvar alterações
     *
     */
    public MB_paginaCadastroEntidades(AcaoDoSistema[] pAcoesRegistro, AcaoDoSistema pAcaoNovoRegistro, AcaoDoSistema pAcaoListar, AcaoDoSistema pAcaoSalvar) {
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

    public List<AcaoDoSistema> getAcoesRegistros() {
        return acoesRegistros;
    }

    // Retorna ação de novo registro
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

    // Define a ação selecionada
    public void setAcaoSelecionada(AcaoDoSistema acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    public AcaoDoSistema getAcaoSalvarAlteracoes() {
        return acaoSalvarAlteracoes;
    }

}
