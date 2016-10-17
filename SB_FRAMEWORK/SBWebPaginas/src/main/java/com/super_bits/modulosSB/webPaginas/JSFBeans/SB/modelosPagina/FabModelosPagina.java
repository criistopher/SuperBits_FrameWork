/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.webPaginas.JSFBeans.SB.modelosPagina;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author salvioF
 */
public enum FabModelosPagina implements ItfFabrica {
    MODELO_GERAL,
    MODELO_GERAL_ANONIMO,
    FORMULARIO_PERSONALIZADO,
    FORMULARIO_PG_ENTIDADE_LISTAR,
    FORMULARIO_PG_ENTIDADE_EDITAR,
    FORMULARIO_PG_ACAO_ATUAL,
    FORMULARIO_PG_ENTIDADE_ACAO_ATUAL;

    @Override
    public ModeloPagina getRegistro() {
        ModeloPagina mp = new ModeloPagina();
        mp.setId(this.ordinal());
        switch (this) {
            case MODELO_GERAL:
                mp.setNome("Modelo Geral");
                mp.setDescricao("Inclui, toda estrutura basica de CSS topo com menu, conteúdo e rodapé");
                mp.getAreas().add("conteudo");
                mp.getAreas().add("topo");
                mp.getAreas().add("rodape");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/modeloGeral.xhtml");
                break;
            case MODELO_GERAL_ANONIMO:
                mp.setNome("Modelo Geral Anonimo");
                mp.setDescricao("Inclui o toda estrutura basica de css, topo sem menu e conteúdo");
                mp.getAreas().add("divConteudo");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/modeloAnonimo.xhtml");
                break;
            case FORMULARIO_PERSONALIZADO:
                mp.setNome("Conteúdo personalizado ");
                mp.setDescricao("inclui  (Icone, Cabeçalho, botão a Direita e conteúdo) é preciso setar o parametro  UI iconeConteudo  na area de parametros");
                mp.getAreas().add("parametros");
                mp.getAreas().add("iconeConteudo");
                mp.getAreas().add("titulo");
                mp.getAreas().add("subtitulo");
                mp.getAreas().add("conteudoDireita");
                mp.getAreas().add("conteudoCard");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/conteudo.xhtml");
                break;
            case FORMULARIO_PG_ENTIDADE_LISTAR:
                mp.setNome("Conteúdo listar Automático");
                mp.setDescricao("Apresenta listar automatico, não possui areas para serem devinidas, precisa ser usuaro em uma Pagina do tipo Entidade");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/formularioEntidadeEditar.xhtml");
                break;
            case FORMULARIO_PG_ACAO_ATUAL:
                mp.setNome("Formulario da ação Atual");
                mp.setDescricao("Mostra o formato padrão de formulário, contendo o icone da ação atual, o titulo, como o nome da ação,"
                        + " o subtitulo a descrição da ação");
                mp.getAreas().add("conteudo");
                mp.getAreas().add("direita");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/formularioAcaoAtual.xhtml");
                break;
            case FORMULARIO_PG_ENTIDADE_ACAO_ATUAL:
                mp.setDescricao("Mostra o formato padrão de formulário, contendo o icone da ação atual, o titulo, como o nome da ação,"
                        + " o subtitulo a descrição da ação, e o botão da direita definido como: novo em caso de listar e pagina.temNovo == true, e listar nos outros casos ");
                mp.getAreas().add("conteudo");
                mp.setXhtmlVinculado("/resources/SBComp/template/basico/formularioEntidadeAcaoAtual.xhtml");
                break;
            default:
                throw new AssertionError(this.name());

        }
        return mp;
    }

}
