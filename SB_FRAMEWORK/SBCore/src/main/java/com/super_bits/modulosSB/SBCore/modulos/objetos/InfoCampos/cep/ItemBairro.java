/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.apiClients.UtilWebService.cep.WebServiceCepRepublicaVirtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ItfCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desenvolvedor
 */
public class ItemBairro extends ItemSimples implements ItfBairro {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;

    private List<Long> coordenadas = new ArrayList<Long>();

    private ItemCidade cidade;

    public ItemBairro(WebServiceCepRepublicaVirtual cepEncontrado) {

    }

    public ItemBairro() {
        cidade = new ItemCidade();
    }

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
    public List<Long> getCordenadas() {

        return this.coordenadas;

    }

    @Override
    public ItfCidade getCidade() {
        return cidade;
    }

}
