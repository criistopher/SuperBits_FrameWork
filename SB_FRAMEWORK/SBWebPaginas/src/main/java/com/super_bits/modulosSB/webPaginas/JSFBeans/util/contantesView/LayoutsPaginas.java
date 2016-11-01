/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.util.contantesView;

import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.modelosPagina.FabModelosPagina;
import com.super_bits.modulosSB.webPaginas.JSFBeans.SB.modelosPagina.ModeloPagina;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author salvioF
 */
@ApplicationScoped
@Named
public class LayoutsPaginas implements Serializable {

    /**
     *
     * Mostra um formulário de ação atual deixando apenas o conteúdo e botões de
     * ação inferior para serem incluidos
     *
     * Includes possíves: conteudo e botoesDeAcao
     *
     *
     * /resources/SBComp/template/basico/formularioEntidadeAcaoAtual.xhtml
     *
     * @return
     */
    public ModeloPagina getFormularioAcaoAtualDaEntidade() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_ACAO_ATUAL.getRegistro();
    }

    /**
     *
     * Estrutura básica de um site, com importação de todos os javscripts e css
     * nescessários para execução dos comandos básicos,possui estrutura de topo,
     * conteúdo e rodapé
     *
     * Includes possíveis: divConteudo e head      <br>
     * Formulários incorporados: /site/topo.xhtml folhasDeEstiloBasicas.xhtml
     * /site/rodape.xhtml /site/rodape.xhtml     <br>
     *
     * @see FabModelosPagina#MODELO_GERAL
     * @return
     */
    public ModeloPagina getModeloGeral() {
        return FabModelosPagina.MODELO_GERAL.getRegistro();
    }

    /**
     *
     * /resources/SBComp/template/basico/modeloAnonimo.xhtml
     *
     * O mesmo que o modelo geral, porem sem topo.xhtml e rodape.xhtml
     *
     * Includes possíveis: divConteudo e head
     *
     * @see LayoutsPaginas#getModeloGeral()
     * @see FabModelosPagina#MODELO_GERAL_ANONIMO
     * @return
     */
    public ModeloPagina getModeloGeralAnonimo() {
        return FabModelosPagina.MODELO_GERAL_ANONIMO.getRegistro();
    }

    /**
     *
     * Exibe um formulário modelo CARD, contendo: icone da ação posicionado no
     * lado esquerdo superior, Um título com subtitulo proximo ao icone, da ação
     * atual e 2 areas para definir: conteudoDireita e conteudoCard
     *
     * Includes possíveis: conteudoDireita e conteudoCard
     *
     *
     * @see FabModelosPagina#FORMULARIO_PG_ENTIDADE_ACAO_ATUAL
     * /resources/SBComp/template/basico/formularioAcaoAtual.xhtml
     * @return
     */
    public ModeloPagina getFormularioAcaoAtual() {
        return FabModelosPagina.FORMULARIO_PG_ACAO_ATUAL.getRegistro();
    }

    /**
     *
     *
     * modelo card totalmente personalizado
     *
     *
     * Parametros ui: iconeConteudo                 <br>
     * Includes possíveis: parametros(onde o iconeConteudo deve ser
     * configurado),titulo,subtitulo,conteudoDireita,conteudoCard          <br>
     *
     *
     *
     * @see FabModelosPagina#FORMULARIO_PERSONALIZADO
     *
     *  /resources/SBComp/template/basico/conteudo.xhtml
     *
     * @return
     */
    public ModeloPagina getFormularioPersonalizado() {
        return FabModelosPagina.FORMULARIO_PERSONALIZADO.getRegistro();
    }

    /**
     *
     * Includes possíveis: conteudo
     *
     * @see FabModelosPagina#FORMULARIO_PG_ENTIDADE_LISTAR
     *
     * @return
     */
    public ModeloPagina getFormularioListarGenerico() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_LISTAR.getRegistro();
    }

    /**
     *
     *
     * includes possívesi:conteudoDireita conteudoCard conteudoExtraCard
     *
     *
     * /resources/SBComp/template/basico/formularioAcaoAtual.xhtml
     *
     * @see FabModelosPagina#FORMULARIO_PG_ENTIDADE_EDITAR
     *
     * @return
     */
    public ModeloPagina getFormularioEditarGenerico() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_EDITAR.getRegistro();
    }

    /**
     *
     * Não tem nada no conteúdo em branco por enquanto apenas um include.
     *
     * includes posssíveis: conteudo
     *
     * /resources/SBComp/template/basico/conteudoEmBranco.xhtml
     *
     * @see FabModelosPagina#CONTEUDO_EM_BRANCO
     *
     * @return
     */
    public ModeloPagina getConteudoEmBranco() {
        return FabModelosPagina.CONTEUDO_EM_BRANCO.getRegistro();
    }

}
