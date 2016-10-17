package com.super_bits.modulosSB.webPaginas.JSFBeans.PrimeFaces.temas;

import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import java.io.Serializable;

public class TemaPrimeFaces extends EntidadeSimples implements Serializable {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String name;

    @InfoCampo(tipo = FabCampos.IMG_PEQUENA)
    private String image;

    public TemaPrimeFaces() {
    }

    public TemaPrimeFaces(int pid, String name, String image) {
        this.id = pid;
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getImgPequena() {
        return image;
    }

}
