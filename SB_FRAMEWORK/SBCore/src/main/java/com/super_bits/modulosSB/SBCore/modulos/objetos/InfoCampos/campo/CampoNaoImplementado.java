/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.ItfComponenteVisualSB;
import com.super_bits.modulosSB.SBCore.modulos.view.fabricasCompVisual.componentes.FabCompVisualInputs;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenvolvedor
 */
public class CampoNaoImplementado extends CampoInstanciadoGenerico implements ItfCampoInstanciado {

    public static final String LABEL_NAO_IMPLEMENTADO = "Não Implementado ou Instanciado";
    @InfoCampo(tipo = FabCampos.AAA_NOME, label = LABEL_NAO_IMPLEMENTADO)
    public final String campoNaoImplementado = "TODO - CampoNão implementado";
    private Field fld;

    private static Field campoteste() {
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

    @Override
    public void configIDPeloNome() {
        System.out.println("Config id pelo nome não se aplica ");
    }

    @Override
    public ItfComponenteVisualSB getComponenteDiferenciado(ItfComponenteVisualSB pComponente) {
        return FabCompVisualInputs.TEXTO_SEM_FORMATACAO.getComponente();
    }

}
