/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.InfoCampos.cep;

import com.super_bits.modulosSB.SBCore.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.InfoCampos.campo.FabCampos;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfBairro;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.Interfaces.basico.cep.ItfLocal;
import com.super_bits.modulosSB.SBCore.InfoCampos.registro.ItemSimples;

/**
 *
 * @author desenvolvedor
 */
public class ItemLocal extends ItemSimples implements ItfLocal {

    @InfoCampo(tipo = FabCampos.ID)
    private int id;

    @InfoCampo(tipo = FabCampos.AAA_NOME)
    private String nome;

    private long longitude, latitude;

    private ItfBairro bairro;

    private String complemento;

    public ItemLocal() {
        bairro = new ItemBairro();
    }

    @Override
    public long getLongitude() {

        return this.longitude;

    }

    @Override
    public long getLatitude() {

        return this.latitude;

    }

    @Override
    public void setLatitude(long pLatitude) {

        this.latitude = pLatitude;

    }

    @Override
    public void setLongitude(long pLongitude) {

        this.longitude = pLongitude;

    }

    @Override
    public ItfBairro getBairro() {

        return this.bairro;

    }

    @Override
    public void setBairro(ItfBairro pBairro) {

        this.bairro = pBairro;

    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String pNome) {
        nome = pNome;
    }

    @Override
    public String getComplemento() {

        return this.complemento;

    }

    @Override
    public void setComplemento(String pComplemento) {

        this.complemento = pComplemento;

    }

}
