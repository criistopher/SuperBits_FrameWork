/*
 *  Super-Bits.com CODE CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.Ips;

import com.super_bits.modulosSB.SBCore.fabrica.ItfFabrica;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoIp implements ItfFabrica {

    FAIXAIP, DNS;

    @Override
    public TipoIp getRegistro() {
        TipoIp tipoEntrega = new TipoIp();
        switch (this) {
            case FAIXAIP:
                tipoEntrega.setId(1);
                tipoEntrega.setNome("FaixaIP");

                break;
            case DNS:
                tipoEntrega.setId(2);
                tipoEntrega.setNome("DNS");

                break;
            default:
                throw new AssertionError(this.name());

        }
        return tipoEntrega;

    }

}
