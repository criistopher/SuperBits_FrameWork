package com.super_bits.modulosSB.webPaginas.JSFBeans.SB;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.UtilGeral.ClasseTipada;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStrings;
import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * Classe para lista tipada com título
 *
 *
 * @author Salvio
 *
 */
public class B_Lista<T extends ItfBeanSimples> extends ClasseTipada implements Serializable {

    private List<? extends ItfBeanSimples> itens;
    private String nomeLista;

    public B_Lista(String nomeLista, List<? extends ItfBeanSimples> itens, Class<?> pClasse) {
        super(pClasse);
        this.itens = itens;
        this.nomeLista = nomeLista;
    }

    public String getNomeJsfAmigavel() {
        return UtilSBCoreStrings.makeStrUrlAmigavel(nomeLista);
    }

    public List<? extends ItfBeanSimples> getItens() {
        return itens;
    }

    public void setItens(List<? extends ItfBeanSimples> itens) {
        this.itens = itens;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    private Boolean validaParametrosIniciais() {
        // TODO
        return true;
    }

    public int getQuantCampos() {
        return 4;
    }

    public String getLabel(String campo) {
        return "teste";
    }

}
