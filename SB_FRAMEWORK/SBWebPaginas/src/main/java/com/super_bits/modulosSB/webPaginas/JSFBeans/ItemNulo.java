package com.super_bits.modulosSB.webPaginas.JSFBeans;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.io.Serializable;

public class ItemNulo extends ItemSimples implements Serializable {

    @InfoCampo(tipo = FabCampos.AAA_NOME)
    String nomeCurto = "RegistroNulo";
    @InfoCampo(tipo = FabCampos.ID)
    int id = 0;

    public ItemNulo() {
        // TODO Auto-generated constructor stub
    }

}
