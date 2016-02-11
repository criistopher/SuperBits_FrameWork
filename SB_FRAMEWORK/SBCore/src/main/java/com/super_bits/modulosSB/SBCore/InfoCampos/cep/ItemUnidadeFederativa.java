/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
public class ItemUnidadeFederativa extends ItemSimples implements ItfUnidadeFederativa {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nome;

    @Override
    public void setId(int pId) {

        this.id = pId;

    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String pNome) {
        this.nome = pNome;
    }

}
