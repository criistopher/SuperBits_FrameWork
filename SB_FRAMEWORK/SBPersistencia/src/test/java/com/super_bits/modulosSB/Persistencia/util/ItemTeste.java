/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.Persistencia.util;

import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.lang.reflect.Field;

/**
 *
 * @author sfurbino
 */
public class ItemTeste extends ItemSimples {

    private int id;
    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nome;

    public ItemTeste(String pNome) {
        super();
        id = 1;
        nome = pNome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
