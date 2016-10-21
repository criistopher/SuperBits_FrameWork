/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoControllerEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormularioEntidade;
import com.super_bits.modulosSB.SBCore.modulos.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.FabErro;
import com.super_bits.modulosSB.webPaginas.JSFBeans.util.PgUtil;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import java.security.cert.CRLReason;
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

    private boolean podeEditar;
    private boolean novoRegistro;

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
    private MB_paginaCadastroEntidades(
            AcaoDoSistema[] pAcoesRegistro,
            ItfAcaoFormularioEntidade pAcaoNovoRegistro,
            ItfAcaoFormularioEntidade pAcaoListar,
            ItfAcaoControllerEntidade pAcaoSalvar,
            boolean pTempesquisa,
            boolean pSubChamadaDeConstructor
    ) {
        super();
        acoesRegistros = new ArrayList<>();

        acaoNovoRegistro = pAcaoNovoRegistro;
        acaoListarRegistros = pAcaoListar;
        acaoSalvarAlteracoes = pAcaoSalvar;
        acaoSelecionada = (ItfAcaoDoSistema) acaoListarRegistros;
        try {

            for (AcaoDoSistema acao : pAcoesRegistro) {
                acoesRegistros.add((ItfAcaoDoSistema) acao);
            }

            xhtmlAcaoAtual = acaoListarRegistros.getXhtml();

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
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "ouve um erro configurando as ações basica do MB de entidade" + this.getClass().getName(), t);

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

    public MB_paginaCadastroEntidades(List pAcoesRegistro,
            AcaoDoSistema pAcaoNovoRegistro,
            AcaoDoSistema pAcaoListar,
            AcaoDoSistema pAcaoSalvar,
            boolean pTempesquisa) {
        this((AcaoDoSistema[]) pAcoesRegistro.toArray(new AcaoDoSistema[pAcoesRegistro.size()]), pAcaoNovoRegistro.getComoFormularioEntidade(),
                pAcaoListar.getComoFormularioEntidade(), pAcaoSalvar.getComoControllerEntidade(), pTempesquisa);
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

        temVisualizar = pTemVisualizar;
        temNovo = pTemNovo;
        temEditar = pTemEditar;
        temAlterarStatus = pTemAlterarStatus;

        configuraAcoes();
    }

    @Override
    public void executarAcao(T pEntidadeSelecionada) {

        if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
            System.out.println("Executando Acao" + acaoSelecionada.getNomeAcao());

        }

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
            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
                System.out.println("O xhtml da ação foi alterado para o XTML:" + xhtmlAcaoAtual + " da açao " + acaoSelecionada.getNomeAcao());

            }

        }

        if (pEntidadeSelecionada != null) {

            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
                //System.out.println("Uma nova Entidade foi enviada no método, a entidade é " + getEntidadeSelecionada().toString());

            }

            setEntidadeSelecionada(pEntidadeSelecionada);
            System.out.println("ALTERANDO A ENTIDADE SELECIONADA TESTE ---------------------- " + pEntidadeSelecionada);
        }

        if (acaoSelecionada.equals(acaoListarRegistros)) {

            atualizaInformacoesDeEdicao(estadoEdicao.VISUALIZAR);

            if (SBCore.getEstadoAPP() != SBCore.ESTADO_APP.PRODUCAO) {
                System.out.println("Definindo modo somente leitura e Atualizando os dados da lista");

            }

            renovarEMPagina();
            listarDados();

            paginaUtil.atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);

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
                paginaUtil.atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);
            }
        }

        if (isTemEditar()) {
            if (acaoSelecionada.equals(getAcaoEditar())) {
                atualizaInformacoesDeEdicao(estadoEdicao.ALTERAR);
                paginaUtil.atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);

            }
        }

        if (acaoSelecionada.equals(getAcaoVisualisar())) {

            atualizaInformacoesDeEdicao(estadoEdicao.VISUALIZAR);
            listarDados();
            paginaUtil.atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);

        }

        if (acaoSelecionada.isUmaAcaoFormulario()) {

            paginaUtil.atualizaTelaPorID(idAreaExbicaoAcaoSelecionada);
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

    protected void setPodeEditar(boolean pParametro) {
        podeEditar = pParametro;
    }

    protected void setTemNovo(boolean pParametro) {
        temNovo = pParametro;
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
    public boolean isTemVisualizar() {
        return temVisualizar;
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
                throw new UnsupportedOperationException("A propriedade temEditar é true mas a ação deste tipo não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR + " nas ações de registro configuradas no constructor da pagina");
            }

            if (temVisualizar & acaoEntidadeVisualizar == null) {
                throw new UnsupportedOperationException("Aa propriedae tem visualizar é true mas uma ação deste tipo não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.FORMULARIO_VISUALIZAR + " nas ações de registro configuradas no constructor da pagina");
            }

            if (temAlterarStatus & acaoEntidadeAlterarStatus == null) {
                throw new UnsupportedOperationException("A prpriedade temAlterar status é true mas a ação deste tipo não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.CONTROLLER_ATIVAR_DESATIVAR + " nas ações de registro configuradas no constructor da pagina");
            }

            if (temNovo & acaoNovoRegistro == null) {
                throw new UnsupportedOperationException("a prpriedade tem novo é true, mas a ação deste tipo não foi encontrada, certifique que exita uma ação do tipo " + FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO + " nas ações de registro configuradas no constructor da pagina");
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

    @Override
    public boolean isSomenteLeitura() {
        return !podeEditar;
    }

}
