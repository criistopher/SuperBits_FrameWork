/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ItemUnidadeFederativa extends ItemSimples implements ItfUnidadeFederativa {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME)
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

    @Override
    public List<ItfCidade> getCidades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
