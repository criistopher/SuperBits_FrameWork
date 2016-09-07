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

    public ModeloPagina getFormularioAcaoAtualDaEntidade() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_ACAO_ATUAL.getRegistro();
    }

    public ModeloPagina getModeloGeral() {
        return FabModelosPagina.MODELO_GERAL.getRegistro();
    }

    public ModeloPagina getModeloGeralAnonimo() {
        return FabModelosPagina.MODELO_GERAL_ANONIMO.getRegistro();
    }

    /**
     *
     * Exibe um formulário modelo CARD, contendo, icone, titulo, subtitulo da
     * ação atual e 2 areas para definir: conteudoDireita e conteudoCard
     *
     * @return
     */
    public ModeloPagina getFormularioAcaoAtual() {
        return FabModelosPagina.FORMULARIO_PG_ACAO_ATUAL.getRegistro();
    }

    public ModeloPagina getFormularioPersonalizado() {
        return FabModelosPagina.FORMULARIO_PERSONALIZADO.getRegistro();
    }

    public ModeloPagina getFormularioListarGenerico() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_LISTAR.getRegistro();
    }

    public ModeloPagina getFormularioEditarGenerico() {
        return FabModelosPagina.FORMULARIO_PG_ENTIDADE_EDITAR.getRegistro();
    }

}
