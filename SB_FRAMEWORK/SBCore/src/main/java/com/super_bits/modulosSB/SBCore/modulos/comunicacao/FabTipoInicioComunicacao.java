/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.comunicacao;

import java.util.List;

/**
 *
 *
 *
 * @author salvioF
 */
public enum FabTipoInicioComunicacao {

    NOTIFICAR,
    PERGUNTAR_SIM_OU_NAO,
    PERGUNTAR_SIM_NAO_IGNORAR,
    PERGUNTAR_SIM_NAO_MAIS_TARDE,
    SOLICITAR_AUTORIZACAO,;

    public List<FabTipoRespostaComunicacao> respostasEsperadasPadrao() {
        return null;
    }
}
