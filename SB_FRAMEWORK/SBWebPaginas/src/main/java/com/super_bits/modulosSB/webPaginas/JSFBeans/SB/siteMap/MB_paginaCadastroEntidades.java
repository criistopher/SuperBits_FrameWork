/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.ItfAcaoDoSistema;
<<<<<<< HEAD
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
=======
import com.super_bits.Controller.Interfaces.ItfResposta;
import com.super_bits.Controller.fabricas.FabTipoAcaoPadrao;
>>>>>>> 15f0d855306b5cee1cf03415060c449702bb6646
import com.super_bits.modulos.SBAcessosModel.model.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.PgUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public abstract class MB_paginaCadastroEntidades<T> extends MB_PaginaConversation implements ItfPaginaGerenciarEntidade<T> {

    private T entidadeSelecionada;
    private List<T> entidadesListadas;
    private Class classeDaEntidade;

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
    @Inject
    protected PgUtil paginaUtil;

    @Override
    public void executarAcao(T pEntidadeSelecionada) {

        if (acaoSelecionada.getXHTMLAcao() != null) {
            xhtmlAcaoAtual = acaoSelecionada.getXHTMLAcao();
        }

        if (pEntidadeSelecionada != null) {
            setEntidadeSelecionada(pEntidadeSelecionada);
        }

        if (acaoSelecionada.equals(acaoListarRegistros)) {
            listarDados();
            paginaUtil.atualizaTelaPorID("formulario");
        }

        if (acaoSelecionada.equals(acaoNovoRegistro)) {

            try {
                // define que a nova classe será do tipo Newsletter
                setEntidadeSelecionada((T) classeDaEntidade.newInstance());
            } catch (InstantiationException | IllegalAccessException ex) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro instanciando a classe ao criar novo registro no metodo executar em:" + this.getClass().getName(), ex);
            }

            // define o estado de edição como estado de criação
            atualizaInformacoesDeEdicao(estadoEdicao.CRIAR);

            // define que a atualização das informações aconteceram no formulario
            paginaUtil.atualizaTelaPorID("formulario");

        }

        if (acaoSelecionada.equals(getAcaoEditar())) {

            atualizaInformacoesDeEdicao(estadoEdicao.ALTERAR);
            paginaUtil.atualizaTelaPorID("formulario");

        }

        if (acaoSelecionada.equals(getAcaoVisualisar())) {

            atualizaInformacoesDeEdicao(estadoEdicao.VISUALIZAR);
            paginaUtil.atualizaTelaPorID("formulario");

        }

    }

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
    public MB_paginaCadastroEntidades(
            AcaoDoSistema[] pAcoesRegistro,
            AcaoDoSistema pAcaoNovoRegistro,
            AcaoDoSistema pAcaoListar,
            AcaoDoSistema pAcaoSalvar,
            Class pClasseDaEntidade,
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
        classeDaEntidade = pClasseDaEntidade;
        entidadesListadas = new ArrayList<>();
        paginaUtil = new PgUtil();
        temPesquisa = pTempesquisa;

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
                novoRegistro = false;
                podeEditar = true;
                break;
            case CRIAR:
                novoRegistro = true;
                podeEditar = true;
                break;
            case VISUALIZAR:
                novoRegistro = false;
                podeEditar = false;
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
