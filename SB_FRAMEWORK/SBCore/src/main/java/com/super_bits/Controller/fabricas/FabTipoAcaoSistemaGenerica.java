/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.Controller.fabricas;

import com.super_bits.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.Controller.Interfaces.acoes.ItfAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.TipoAcaoPadrao;
import static com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica.FORMULARIO_LISTAR;
import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.fabrica.UtilSBCoreFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoAcaoSistemaGenerica implements ItfFabrica {

    /**
     * Formulários para criação de novo Registro
     */
    FORMULARIO_NOVO_REGISTRO,
    /**
     * Formulário para editar um REgistro
     */
    FORMULARIO_EDITAR,
    /**
     * Formulário para listar os registros com as ações
     */
    FORMULARIO_LISTAR,
    /**
     * Abre um formulário modal com campos para Coletar
     */
    FORMULARIO_MODAL,
    /**
     * Abre uma tela para Gestão de ações da Entidade: exemplo: Listar, Editar,
     * Novo, Formulários modais Etc.
     *
     * Em geral esta ação está vinculada a um Managed Bean
     *
     */
    GESTAO_DE_ENTIDADE,
    /**
     * Botão normalmente localizado dentro de um formulário de edição, com a
     * função de salvar as alterações de um registro existente, atenção, na
     * maioria dos casos o Salvar Modo Merge substitui a nescessidade deste
     * botão
     */
    SALVAR_EDICAO,
    /**
     * Botão normalmente localizado dentro de um formulário de edição, com a
     * função de salvar as alterações de um registro existente, atenção, na
     * maioria dos casos o Salvar Modo Merge substitui a nescessidade deste
     * botão
     */
    SALVAR_NOVO,
    /**
     * Botão normalmente localizado dentro de um formulário de edição e
     * Inserção, com a função de salvar as alterações de um registro existente,
     * ou salvar um novo registro, dependendo se o ID do objeto já exista
     *
     * Substitui SalvarEdicção e Salvar Novo
     *
     */
    SALVAR_MODO_MERGE,
    /**
     *
     * Botão para alterar o atributo ativo do registro, se tiver ativo, fica
     * false, se tiver false fica ativo, normalmente a criação desta Ação
     * substitui a criação de um botão para ativar, e outro para desativar
     *
     *
     */
    ATIVAR_DESATIVAR,
    /**
     *
     * Botão para Ativar um um registro que não esteja ativo *
     */
    ATIVAR,
    /**
     * Botão para desativar um registro caso ele esteja ativo
     *
     */
    DESATIVAR,
    /**
     *
     * Abre um formulário, e exibe os campos configurados caso existam
     *
     *
     */
    FORMULARIO_VISUALIZAR,
    GERENCIAR;

    @Override
    public TipoAcaoPadrao getRegistro() {

        TipoAcaoPadrao acaoPadrao = new TipoAcaoPadrao();

//        return novaAcao;
        switch (this) {
            case FORMULARIO_NOVO_REGISTRO:
                acaoPadrao.setNomePadrao("Criar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de novo registro");
                acaoPadrao.setIconePadrao("fa fa-plus");
                break;
            case FORMULARIO_EDITAR:
                acaoPadrao.setNomePadrao("Editar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de edicao de registro existente");
                acaoPadrao.setIconePadrao("fa fa-pencil");
                break;
            case FORMULARIO_VISUALIZAR:
                acaoPadrao.setNomePadrao("Visualizar registro");
                acaoPadrao.setDescricaoPadrao("Inicia formulario de visualizacao de registro");
                acaoPadrao.setIconePadrao("fa fa-eye");
                break;
            case SALVAR_EDICAO:
                acaoPadrao.setNomePadrao("Salvar registro editado");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao do registro editado ");
                acaoPadrao.setIconePadrao("fa fa-edit (alias)");
                break;
            case SALVAR_NOVO:
                acaoPadrao.setNomePadrao("Salvar registro criado");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao do registro criado");
                acaoPadrao.setIconePadrao("fa fa-save (alias)");
                break;
            case SALVAR_MODO_MERGE:
                acaoPadrao.setNomePadrao("Atualizar registro");
                acaoPadrao.setDescricaoPadrao("Inicia gravacao");
                acaoPadrao.setIconePadrao("fa fa-save");
                break;
            case FORMULARIO_LISTAR:
                acaoPadrao.setNomePadrao("Listar registro");
                acaoPadrao.setDescricaoPadrao("Exibe os registros existentes");
                acaoPadrao.setIconePadrao("fa fa-list-alt");
                break;
            case ATIVAR_DESATIVAR:
                acaoPadrao.setNomePadrao("Alterador de status");
                acaoPadrao.setDescricaoPadrao("Ativa ou desativa conforme o status atual");
                acaoPadrao.setIconePadrao("fa fa-retweet");
                break;
            case ATIVAR:
                acaoPadrao.setNomePadrao("Ativador");
                acaoPadrao.setDescricaoPadrao("Define status como ativo");
                acaoPadrao.setIconePadrao("fa fa-check");
                break;
            case DESATIVAR:
                acaoPadrao.setNomePadrao("Desativador");
                acaoPadrao.setDescricaoPadrao("Define status como desativado");
                acaoPadrao.setIconePadrao("fa-close (alias)");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return acaoPadrao;
    }

    public ItfAcaoSecundaria getAcaoSecundaria(ItfAcaoGerenciarEntidade pEntidade) {
        ItfAcaoEntidade novaAcao;
        switch (this) {
            case FORMULARIO_NOVO_REGISTRO:
                novaAcao = new AcaoFormularioEntidadeNovoRegistro(pEntidade);
                novaAcao.configurarPropriedadesBasicas(this.getRegistro());

                novaAcao.setNome("Novo " + nomeDoObjeto);
                novaAcao.setDescricao("Cria um novo " + nomeDoObjeto);
                break;

                UtilFabri
            case FORMULARIO_EDITAR:
                novaAcao = new AcaoFormularioEntidadeEditar(this, this.getClasseRelacionada(), diretorioFormulariosEntidade + "/editarRegistro.xhtml");
                novaAcao.setNome("Editar " + nomeDoObjeto);
                novaAcao.setDescricao("Exibe a tela de edição para " + nomeDoObjeto);
                break;
            case FORMULARIO_LISTAR:
                novaAcao = new AcaoFormularioEntidadeNovoRegistro(this, nomeDoObjeto);
                novaAcao.setNome("Listar " + nomeDoObjeto);
                novaAcao.setDescricao("Exibe uma lista de " + nomeDoObjeto + " com suas respectivas ações");

                break;
            case FORMULARIO_MODAL:

                break;
            case ACAO_GESTAO_DE_ENTIDADE:
                break;
            case SALVAR_EDICAO:

                novaAcao = new AcaoController();
                novaAcao.setNome("Salvar " + nomeDoObjeto);
                novaAcao.setDescricao("Atualiza um " + nomeDoObjeto + " no sistema.");

                break;
            case SALVAR_NOVO:
                novaAcao = new AcaoController();
                novaAcao.setNome("Salvar " + nomeDoObjeto);
                novaAcao.setDescricao("Cria um " + nomeDoObjeto + " no sistema.");

                break;
            case SALVAR_MODO_MERGE:

                novaAcao = new AcaoController();
                novaAcao.setNome("Salvar " + nomeDoObjeto);
                novaAcao.setDescricao("Atualiza um " + nomeDoObjeto + " no sistema.");
                break;
            case ATIVAR_DESATIVAR:

                novaAcao = new AcaoController();
                novaAcao.setNome("Alterar Status " + nomeDoObjeto);
                novaAcao.setDescricao("Ativa, ou desativa um" + nomeDoObjeto + " dependendo do status atual.");

                break;
            case ATIVAR:

                novaAcao = new AcaoController();
                novaAcao.setNome("Ativar " + nomeDoObjeto);
                novaAcao.setDescricao("Ativa ou desativa um" + nomeDoObjeto + " desativado.");

                break;
            case DESATIVAR:

                novaAcao = new AcaoController();
                novaAcao.setNome("Ativar " + nomeDoObjeto);
                novaAcao.setDescricao("Ativa ou desativa um" + nomeDoObjeto + " desativado.");

                break;
            case FORMULARIO_VISUALIZAR:

                novaAcao = new AcaoController();
                novaAcao.setNome("Ativar " + nomeDoObjeto);
                novaAcao.setDescricao("Ativa ou desativa um" + nomeDoObjeto + " desativado.");

                break;
            case GERENCIAR:
                break;
            default:
                throw new AssertionError(pAcaoGenerica.name());

        }
        return novaAcao;

    }

}
