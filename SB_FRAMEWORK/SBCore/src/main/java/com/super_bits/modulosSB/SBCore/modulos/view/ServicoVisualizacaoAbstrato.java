/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.view;

/**
 *
 * @author salvioF
 */
public abstract class ServicoVisualizacaoAbstrato implements ItfServicoVisualizacao {

    public enum TIPOS_INTERFACES_COMUM_VISUALIZACAO {
        MOBILE, WEB_RESPONSIVO, DESKTOP;

        public String getPastaRecursosVisualizacao() {
            switch (this) {
                case MOBILE:
                    return "/AindNãoImplementado";
                case WEB_RESPONSIVO:
                    return "/resources/";
                case DESKTOP:
                    return "/AindNãoImplementado";
                default:
                    throw new AssertionError(this.name());

            }
        }

    }

    public enum TIPO_VISUALIZACAO_ITEM {
        LABORATORIO, PUBLICADO
    }

    private final TIPOS_INTERFACES_COMUM_VISUALIZACAO tipoVisualizacao;

    public ServicoVisualizacaoAbstrato(TIPOS_INTERFACES_COMUM_VISUALIZACAO pTipoVisualizacao) {
        tipoVisualizacao = pTipoVisualizacao;
    }

}
