/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfLocalidade;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfUnidadeFederativa;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ItemCidade extends ItemSimples implements ItfCidade {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME_CURTO)
    private String nome;

    private ItfUnidadeFederativa unidadeFederativa;

    private List<ItfBairro> listaDeBairros = new ArrayList<>();

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
    public ItfUnidadeFederativa getUnidadeFederativa() {

        return this.unidadeFederativa;

    }

    @Override
    public List<ItfBairro> getBairros() {

        return this.listaDeBairros;

    }

    @Override
    public String getEstadoPontoNomeCidade() {
        return getUnidadeFederativa() + getNome();
    }

    @Override
    public ItfLocalidade getLocalidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocalidade(ItfLocalidade pLocalidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
