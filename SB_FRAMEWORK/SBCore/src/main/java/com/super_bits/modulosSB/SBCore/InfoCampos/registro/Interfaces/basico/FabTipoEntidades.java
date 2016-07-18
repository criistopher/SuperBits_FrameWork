/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

/**
 *
 * @author desenvolvedor
 */
public enum FabTipoEntidades {

    BEAN_CONTATO_CORPORATIVO,
    BEAN_ENDERECO,
    BEAN_NORMAL,
    BEAN_SIMPLES;

    public String getClassePorTipoEntidade() {

        switch (this) {

            case BEAN_CONTATO_CORPORATIVO:

                return "EntidadeContatoCorporativo";

            case BEAN_ENDERECO:

                return "EntidadeEndereco";

            case BEAN_NORMAL:

                return "EntidadeNormal";
            case BEAN_SIMPLES:

                return "EntidadeSimples";

            default:
                throw new AssertionError(this.name());

        }
    }

}
