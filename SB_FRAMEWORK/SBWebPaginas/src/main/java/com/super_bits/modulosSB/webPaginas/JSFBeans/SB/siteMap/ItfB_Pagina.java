/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.siteMap;

import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ItfAcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade.AcaoGestaoEntidade;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Salvio
 */
public interface ItfB_Pagina {

    public void fecharPagina();

    public void abrePagina();

    /**
     * Por padrão o titulo da pagina é a descrição da ação vinculada a pagina
     *
     * @return Titulo da Pagina
     */
    public String getTitulo();

    /**
     *
     * Descreve a utilidade da pagina.
     *
     * Ajuda o usuário a encontrar esta pagina, e a entender o que ela faz
     *
     * @return descrição da pagina
     */
    public String getDescricao();

    /**
     *
     * Por motivos de segurança na reinderização dos ajax realizados via JSF, e
     * melhor organização cada Pggina (Managed Beans de paginas), possuem uma
     * pagina principal que executa includes das ações sendo executadas no
     * momento.
     *
     * O Recurso XHTML é o XHTMl fixo que nunca muda e é associado diretamenta a
     * ação vinculada ao managed bean
     *
     * @return Xhtml principal da pagina
     */
    public String getRecursoXHTML();

    /**
     *
     * Altera o recruso XHTMl vinculado,será removido para melhorar questões de
     * segurança
     *
     * @param recursoXHTML
     */
    @Deprecated
    public void setRecursoXHTML(String recursoXHTML);

    /**
     *
     * Uma pagina de gestão (PgManagedBean) possui tags associadas a sua função.
     *
     * estas tags servem para montar os links de pagina e para facilitar
     * recursos de SEO
     *
     * @return Tags vinculadas a pagina
     */
    public List<String> getTags();

    /**
     *
     * Adicioa uma tag
     *
     * @see ItfB_Pagina#getTags()
     *
     * @param pTag String da tag a ser adicionada
     */
    public void addTag(String pTag);

    /**
     *
     * Um nome curto facilita o acesso via url para determinada pagina de gestão
     *
     * @return Nome curto da pagina
     */
    public String getNomeCurto();

    /**
     *
     * REtorna o nome de chamada para criação de links para esta pagina (Função
     * antiga e hoje praticamente descessária, será excluida no futuro)
     *
     * Motivo principal:(para você que ficou curioso) os componentes para
     * ciração de botão de ação como <sbComp:botaoDeAcao fazem este trabalho com
     * mais eficiencia.
     *
     * @return
     */
    @Deprecated
    public String getLinkRotulo();

    /**
     *
     * Retorna a tag de url (do getTags)que foi utilizada para carregar a pagina
     *
     * @see ItfB_Pagina#getTags()
     *
     * @return tag utilizada para carregar a pagina
     */
    public String getTagUsada();

    /**
     *
     *
     *
     * @return Url padrão para acesso a esta pagina
     */
    public String getUrlPadrao();

    /**
     *
     * Parametros de Url são parametros que podem ser configurados via chamada
     * de URL
     *
     * O fato de ser possível executar uma ação da pagina pela url informando um
     * parametro em seguida, tornou este recurso quase obsoleto, porém não
     * chegamos a conclusão sobre sua extinção, comente sobre issso em
     * wiki.superbits.org.br
     *
     * @return a lista de todos os parametros nescessários para esta pagina
     */
    public List<ParametroURL> getParametrosURL();

    /**
     *
     * @see ItfB_Pagina#getParametrosURL()
     *
     *
     * @param pNome nome do parametro
     * @return O parametro especifico levando em conta o nome do parametro :P
     */
    public ParametroURL getParametroByNome(String pNome);

    /**
     *
     * @see ItfB_Pagina#getParametrosURL()
     *
     * @param pId Inteiro relacionado ao parametro
     * @return retorna um parametro especifico baseado em seu id;
     */
    public String getNomeParametroById(int pId);

    /**
     *
     * A area de exibição selecionada é a área que será atualizada após executar
     * uma ação atraves do
     *
     * @see ItfB_Pagina#executarAcaoSelecionada() , ou do
     * executarAcaoSelecionada(Entidade)
     *
     * @return O id da area que será atualizada automaticamente
     */
    public String getIdAreaExbicaoAcaoSelecionada();

    //public Conversation getConversa();
    /**
     *
     * O xhtml da ação atual é o xhtml referente a ação do momento este xhtml é
     * diferente do
     *
     * @see ItfB_Pagina#getRecursoXHTML() , normalmente este é um xhtml filho de
     * uma ação de gestao (vinculada diretamente ao pg) e está vinculado a uma
     * ação de formulario
     *
     *
     * @return
     */
    public String getXhtmlAcaoAtual();

    /**
     *
     * Identificação numérica da pagina, não precisa ser único (Criado para
     * utilizar em menus)
     *
     * @return
     */
    public int getId();

    /**
     *
     * Este método altera a tag utilizada, deveria estar protegido, será
     * excluido no futuro..
     *
     * @param tagUsada
     */
    @Deprecated
    public void setTagUsada(String tagUsada);

    /**
     *
     * Retorna o parametro de acordo com o nome da entidade
     *
     * @see ItfB_Pagina#getParametrosURL()
     *
     * @param nomeEntidade nome da entidade vinculada ao parametro
     * @return O parametro vinculado a entidade
     */
    public ParametroURL getParametrobyTipoEntidade(String nomeEntidade);

    /**
     *
     * @return @deprecated O controle de acesso agora é configurado pela
     * AcaoVinculada
     */
    @Deprecated
    public boolean isAcessoLivre();

    /**
     *
     * Todo PgAlgumacoisa é vinculado a auma ação de gestão esta ação de gestão
     * determina quais ações existirão na pagina, nome da pagina e configurações
     * de segurança, este metodo retorna a ação de gestão vinculada a pagina
     *
     *
     * @return Ação de gestão vinculada a pagina
     */
    public AcaoGestaoEntidade getAcaoVinculada();

    /**
     *
     * Aplica valores de parametros passados por url no MB_pagina
     *
     * #DEveria ser protected
     *
     * @param valorStringPorParametro
     */
    @Deprecated
    public void aplicaValoresDeParametrosModoDesenvolvimento(Map<String, String> valorStringPorParametro);

    public void executarAcaoSelecionada();

    /**
     *
     * @return Todas as ações declaradas no managed Bean
     */
    public List<ItfAcaoDoSistema> getAcoesDaPagina();

    /**
     *
     * @return A ação selecionada no momento
     */
    public ItfAcaoDoSistema getAcaoSelecionada();

    /**
     *
     * @return
     */
    public ItfPaginaGerenciarEntidade<?> getComoPaginaEntidade();

    /**
     *
     * Retorna um bean declarado do Managed bean.
     *
     * Um bean declarado possui informações de exibição de icone
     *
     * @param nomeBean
     * @return
     */
    public B_Pagina.BeanDeclarado getBeanDeclarado(String nomeBean);

}
