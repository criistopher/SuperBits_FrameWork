/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
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
public abstract class MB_paginaCadastroEntidades<T> extends MB_PaginaConversation implements ItfPaginaGerenciarEntidade<T> {

    private T entidadeSelecionada;
    private List<T> entidadesListadas;
    private Class classeDaEntidade;

    private boolean temPesquisa;
    private boolean temEditar = true;
    private boolean temNovo = true;
    private boolean temAlterarStatus = true;
    private boolean temVisualizar = true;

    private final List<ItfAcaoDoSistema> acoesRegistros;
    protected final ItfAcaoFormularioEntidade acaoListarRegistros;
    protected final ItfAcaoFormularioEntidade acaoNovoRegistro;
    protected final ItfAcaoControllerEntidade acaoSalvarAlteracoes;

    private ItfAcaoFormularioEntidade acaoEntidadeEditar;
    private ItfAcaoControllerEntidade acaoEntidadeAlterarStatus;
    private ItfAcaoFormularioEntidade acaoEntidadeVisualizar;

    public enum estadoEdicao {

        ALTERAR, CRIAR, VISUALIZAR
    }

    protected ItfAcaoDoSistema acaoSelecionada;
    protected boolean podeEditar;
    protected boolean novoRegistro;
    protected String xhtmlAcaoAtual;
    @Inject
    protected PgUtil paginaUtil;

    /**
     * Constructor simples para pagina de Entidades
     *
     * @param pAcoesRegistro Array de ações para cada registro ex.(new
     * AcaoDoSistema[]{Fabrica.acao.getAcaoDoSistema,Fabrica.acao2.getacaoDoSistema})
     * @param pAcaoNovoRegistro Ação para um novo registro
     * @param pAcaoListar Ação para listar os registros
     * @param pAcaoSalvar Ação para Salvar alterações
     * @param pTempesquisa Informa se vai haver pesquisa na tela de
     * gerenciamento
     * @param pSubChamadaDeConstructor Informa se o constructor é uma subchamada
     * de outro constructor
     *
     */
    public MB_paginaCadastroEntidades(
            AcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ItfAcaoControllerEntidade pAcaoSalvar,
            boolean pTempesquisa,
            boolean pSubChamadaDeConstructor
    ) {
        super();
        acoesRegistros = new ArrayList<>();
        for (AcaoDoSistema acao : pAcoesRegistro) {
            acoesRegistros.add((ItfAcaoDoSistema) acao);
        }

        acaoNovoRegistro = pAcaoNovoRegistro;
        acaoListarRegistros = pAcaoListar;
        acaoSalvarAlteracoes = pAcaoSalvar;
        acaoSelecionada = (ItfAcaoDoSistema) acaoListarRegistros;
        xhtmlAcaoAtual = acaoListarRegistros.getXhtml();
        System.out.println("xhtml Ação atual=" + xhtmlAcaoAtual);
        if (getAcaoVinculada() != null) {
            classeDaEntidade = ((ItfAcaoEntidade) getAcaoVinculada()).getClasseRelacionada();
        }
        entidadesListadas = new ArrayList<>();

        if (SBCore.getEstadoAPP() == SBCore.ESTADO_APP.DESENVOLVIMENTO) {
            paginaUtil = new PgUtil();
        }

        temPesquisa = pTempesquisa;

        // se for uma subchamada deixa para configurar as ações depois
        //  TODO verificar possibilidade de lançar uma exceção caso constate via
        // reflection que este constructor seja chamado direto
        if (!pSubChamadaDeConstructor) {
            configuraAcoes();
        }
    }

    /**
     * Constructor simples para pagina de Entidades
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
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ItfAcaoControllerEntidade pAcaoSalvar,
            boolean pTempesquisa
    ) {
        this(pAcoesRegistro, pAcaoNovoRegistro, pAcaoListar, pAcaoSalvar, pTempesquisa, false);
    }

    /**
     *
     * @param pAcoesRegistro Array de ações para cada registro ex.(new
     * AcaoDoSistema[]{Fabrica.acao.getAcaoDoSistema,Fabrica.acao2.getacaoDoSistema})
     * @param pAcaoNovoRegistro Ação para um novo registro
     * @param pAcaoListar Ação para listar os registros
     * @param pAcaoSalvar Ação para Salvar alterações
     * @param pTempesquisa Informa se vai haver pesquisa na tela de
     * @param pTemVisualizar
     * @param pTemEditar Informa se o formulario tem opção de edição
     * @param pTemNovo Informa se o formulario tem opção de novo registro
     * @param pTemAlterarStatus Informa se o formulario tem opções de Alterar
     * Status
     */
    public MB_paginaCadastroEntidades(AcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ItfAcaoControllerEntidade pAcaoSalvar,
            boolean pTempesquisa,
            boolean pTemVisualizar,
            boolean pTemEditar,
            boolean pTemNovo,
            boolean pTemAlterarStatus) {

        this(pAcoesRegistro, pAcaoNovoRegistro, pAcaoListar, pAcaoSalvar, pTempesquisa, true);

        temEditar = pTemEditar;
        temAlterarStatus = pTemAlterarStatus;
        temNovo = pTemNovo;
        temVisualizar = pTemVisualizar;
        configuraAcoes();
    }

    @Override
    public void executarAcao(T pEntidadeSelecionada) {

        if (acaoSelecionada == null) {
            try {
                UtilSBWP_JSFTools.mensagens().enviaMensagem(FabMensagens.ALERTA.getMsgUsuario("Nenhuma  ação foi selecionada"));
                throw new UnsupportedOperationException("A ação selecionada estava nula ao executar ação na pagina" + this.getClass().getSimpleName());
            } catch (Throwable t) {
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Uma ação nula foi selecionada em executar ação", t);
            }

            return;
        }

        if ((acaoSelecionada.equals(acaoEntidadeEditar)
                || acaoSelecionada.equals(acaoEntidadeAlterarStatus)
                || acaoSelecionada.equals(acaoEntidadeVisualizar))
                & (pEntidadeSelecionada == null)) {
            SBCore.enviarAvisoAoUsuario("Entidade não selecionada para" + acaoSelecionada.getNomeAcao());

        }

        if (acaoSelecionada.isUmaAcaoFormulario()) {
            xhtmlAcaoAtual = ((ItfAcaoFormulario) acaoSelecionada).getXhtml();
        }

        if (pEntidadeSelecionada != null) {
            setEntidadeSelecionada(pEntidadeSelecionada);
        }

        if (acaoSelecionada.equals(acaoListarRegistros)) {
            atualizaInformacoesDeEdicao(estadoEdicao.VISUALIZAR);
            listarDados();
            paginaUtil.atualizaTelaPorID("formulario");

        }

        if (isTemNovo()) {

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
        }

        if (isTemEditar()) {
            if (acaoSelecionada.equals(getAcaoEditar())) {
                atualizaInformacoesDeEdicao(estadoEdicao.ALTERAR);
                paginaUtil.atualizaTelaPorID("formulario");

            }
        }

        if (acaoSelecionada.equals(getAcaoVisualisar())) {

            atualizaInformacoesDeEdicao(estadoEdicao.VISUALIZAR);
            listarDados();
            paginaUtil.atualizaTelaPorID("formulario");

        }

        if (acaoSelecionada.isUmaAcaoFormulario()) {

            paginaUtil.atualizaTelaPorID("formulario");
        }

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
    public boolean isTemEditar() {
        return temEditar;
    }

    @Override
    public boolean isTemNovo() {
        return temNovo;
    }

    @Override
    public boolean isTemAlterarStatus() {
        return temAlterarStatus;
    }

    @Override
    public List<ItfAcaoDoSistema> getAcoesRegistros() {
        return acoesRegistros;
    }

    // Retorna ação de novo registro
    @Override
    public ItfAcaoFormularioEntidade getAcaoNovoRegistro() {
        return acaoNovoRegistro;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoListarRegistros() {
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
    public ItfAcaoDoSistema getAcaoSelecionada() {
        return (ItfAcaoDoSistema) acaoSelecionada;
    }

    // Define a ação selecionada
    @Override
    public void setAcaoSelecionada(ItfAcaoDoSistema acaoSelecionada) {
        this.acaoSelecionada = acaoSelecionada;
    }

    @Override
    public String getXhtmlAcaoAtual() {
        return xhtmlAcaoAtual;
    }

    @Override
    public ItfAcaoDoSistema getAcaoSalvarAlteracoes() {
        return (ItfAcaoDoSistema) acaoSalvarAlteracoes;
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

    private void configuraAcoes() {
        try {

            for (ItfAcaoDoSistema acao : acoesRegistros) {

                if (acao.isUmaAcaoGenerica()) {

                    if (acao.getTipoAcaoGenerica().equals(FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR)) {

                        if (!temEditar) {
                            throw new UnsupportedOperationException("A opção TemEditar está false, mas uma ação do tipo formulário editar foi encontrada entre as ações dos registros de entidade, a ação é " + acao.getNomeAcao());
                        } else {
                            acaoEntidadeEditar = (ItfAcaoFormularioEntidade) acao;
                        }

                    }
                    if (acao.getTipoAcaoGenerica().equals(FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR_DESATIVAR)) {

                        if (!temAlterarStatus) {
                            throw new UnsupportedOperationException("A opção TemAlterarStatus está false, mas uma ação do tipo controller Ativa_desativar foi encontrada entre as ações dos registros de entidade, verifique o constructor, ou a  config da ação" + acao.getNomeAcao());
                        } else {
                            acaoEntidadeAlterarStatus = (ItfAcaoControllerEntidade) acao;
                        }

                    }
                    if (acao.getTipoAcaoGenerica().equals(FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR)) {

                        if (!temVisualizar) {
                            throw new UnsupportedOperationException("A opção TemVisualizar está false, mas uma ação do tipo formulário visualisar foi encontrada entre as ações dos registros de entidade, verifique o constructor ou a config da ação " + acao.getNomeAcao());
                        } else {
                            acaoEntidadeVisualizar = (ItfAcaoFormularioEntidade) acao;
                        }

                    }

                }

            }

            if (temEditar & acaoEntidadeEditar == null) {
                throw new UnsupportedOperationException("uma ação esperada  foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR + " nas ações de registro configuradas no constructor da pagina");
            }

            if (temVisualizar & acaoEntidadeVisualizar == null) {
                throw new UnsupportedOperationException("uma ação  esperada  não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR + " nas ações de registro configuradas no constructor da pagina");
            }

            if (temAlterarStatus & acaoEntidadeAlterarStatus == null) {
                throw new UnsupportedOperationException("uma ação esperada não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR_DESATIVAR + " nas ações de registro configuradas no constructor da pagina");
            }

            if (temNovo & acaoNovoRegistro == null) {
                throw new UnsupportedOperationException("uma ação esperada não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO + " nas ações de registro configuradas no constructor da pagina");
            }

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Ouve um erro configurando as ações da pagina de gestão de entidade: " + this.getClass().toString(), t);
        }
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoEditar() {
        return acaoEntidadeEditar;

    }

    @Override
    public ItfAcaoControllerEntidade getAcaoAlterarStatus() {
        return acaoEntidadeAlterarStatus;
    }

    @Override
    public ItfAcaoFormularioEntidade getAcaoVisualisar() {
        return acaoEntidadeVisualizar;
    }
}
