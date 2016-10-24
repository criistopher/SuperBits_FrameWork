/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;

/**
 *
 * @author salvioF
 */
public enum FabTipoRecurso implements ItfFabrica {

    IMAGEM_WEB, VIDEO, DOCUMENTO_WORD, DOCUMENTO_PDF, ARQUIVO_TEXTO;

    @Override
    public TipoRecurso getRegistro() {
        TipoRecurso novoRecurso = new TipoRecurso();
        novoRecurso.setId(this.ordinal() + 1);
        novoRecurso.setNome(this.toString());
        switch (this) {
            case IMAGEM_WEB:

                break;
            case VIDEO:
                break;
            case DOCUMENTO_WORD:
                break;
            case DOCUMENTO_PDF:
                break;
            case ARQUIVO_TEXTO:
                break;
            default:
                throw new AssertionError(this.name());

        }
        return novoRecurso;
    }
}
