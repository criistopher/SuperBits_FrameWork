/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.view.fabricasCompVisual;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvioF
 */
public class FamiliaComponente extends ItemSimples {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;
    private FabFamiliaCompVisual fabrica;

    private List<ComponenteVisualSB> componentes;

    private Class fabricaDeCamposPadrao;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public boolean isLayoutCompativel(ItfComponenteVisualSB pComponente) {
        return true;
    }

    public FabFamiliaCompVisual getFabrica() {
        return fabrica;
    }

    public void setFabrica(FabFamiliaCompVisual fabrica) {
        this.fabrica = fabrica;
    }

    public boolean getIsUmComponenteDaFamilia(ItfComponenteVisualSB pComponente) {
        return true;

    }

    public boolean getIsUmXHTMLDAFamilia(String pXhtml) {
        return true;
    }

    public List<ComponenteVisualSB> getComponentes() {

        if (componentes == null || componentes.isEmpty()) {
            componentes = new ArrayList<>();
            for (Object obj : fabricaDeCamposPadrao.getEnumConstants()) {
                componentes.add(((ItfFabTipoComponenteVisual) obj).getComponente());
            }
        }
        return componentes;

    }

}
