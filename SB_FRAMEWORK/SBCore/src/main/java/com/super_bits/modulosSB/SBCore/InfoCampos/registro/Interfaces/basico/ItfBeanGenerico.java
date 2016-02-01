/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import java.lang.reflect.Field;

/**
 *
 * @author sfurbino
 */
public interface ItfBeanGenerico {

    public String getNomeCampo(FabCampos pInfocampo);

    public Field getCampo(FabCampos pInfoCampo);

    /**
     * Método utilizado para fazer uploads de arquivos vinculado a este registro
     *
     * @param pTipo Tipo de fonte,
     * @param pRecurso
     */
    public void uploadFoto(TipoFonteUpload pTipo, Object pRecurso);
}
