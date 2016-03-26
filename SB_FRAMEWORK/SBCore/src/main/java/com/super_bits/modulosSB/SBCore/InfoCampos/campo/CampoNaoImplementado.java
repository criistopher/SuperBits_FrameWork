/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvedor
 */
public class CampoNaoImplementado extends CampoInstanciadoGenerico implements ItfCampoInstanciado {

    @InfoCampo(tipo = FabCampos.AAA_NOME, label = "Não Implementado")
    public final String campoNaoImplementado = "TODO - CampoNão implementado";
    private Field fld;

    private static final Field campoteste() {
        try {

            Field cp = CampoNaoImplementado.class.getField("campoNaoImplementado");
            return cp;
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(CampoNaoImplementado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CampoNaoImplementado() {
        super(FabCampos.getCampoByAnotacoesSimplesSemPersistencia(campoteste()), campoteste());

    }

    @Override

    public boolean validarCampo() {
        return true;
    }

    @Override
    public Object getParent() {
        return null;
    }

    @Override
    public Object getValor() {
        return "Não Implementado";
    }

    @Override
    public void setValor(Object pValor) {

    }

}
